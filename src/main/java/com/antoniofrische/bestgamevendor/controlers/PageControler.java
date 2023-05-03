package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.exceptions.EntityNotFound;
import com.antoniofrische.bestgamevendor.exceptions.FormFieldEmpty;
import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.EntityAlreadyExists;
import com.antoniofrische.bestgamevendor.models.*;
import com.antoniofrische.bestgamevendor.repositorios.*;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
import com.antoniofrische.bestgamevendor.services.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PageControler {

    @Autowired
    private ListSalesService listSaleServ;
    @Autowired
    private RegionService regionServ;

    @Autowired
    private ListFavService listFavServ;
    @Autowired
    private ReviewService reviewServ;
    @Autowired
    private UserService userServ;
    @Autowired
    private ProductService prodServ;

    private UserEntity getCurrentUser(){
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userServ.userFindByEmail(detailUser.getUsername());
    }

    //Logger logger = LoggerFactory.getLogger(PageControler.class);

    @GetMapping({"","/","/inicio","/index"})
    public String goToIndex(Model model) {
        model.addAttribute("title","Inicio");
        List<ProductosEntity> products = prodServ.prodAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable("id") Long id, Model model) {
        ProductosEntity product = prodServ.prodFindByID(id);
        List<ListaRebajasproductosEntity> lres = listSaleServ.salesFindByProduct(product);
        List<ReviewEntity> reviews = reviewServ.reviewFindByProduct(product);
        Collections.sort(lres);
        model.addAttribute("websites",lres);
        model.addAttribute("product", product);
        model.addAttribute("reviews",reviews);
        model.addAttribute("review", new ReviewEntity());
        assert product != null;
        model.addAttribute("title", "Game, "+ product.getNombre());
        return "products/product";
    }

    @PostMapping("/addReview")
    public String addReview(ReviewEntity review, RedirectAttributes redirectAttributes){
        UserEntity currentUser = getCurrentUser();

        try {
            reviewServ.reviewSave(review, currentUser);
        } catch (EntityAlreadyExists  | FormFieldEmpty e) {
            redirectAttributes.addFlashAttribute("Message", e.getMessage());

        } catch (EntityNotFound enf){
            redirectAttributes.addFlashAttribute("Message", enf.getMessage());
            return "redirect:/login";
        }
        redirectAttributes.addAttribute("id", review.getProduct().getIdProductos());
        return  "redirect:/product/{id}";

    }
    @PostMapping("/removereview")
    public String removeReview(@RequestParam("idR") Long idR, RedirectAttributes redirectAttributes){
        ReviewEntity review = reviewServ.reviewFindByID(idR);
        UserEntity currentUser = getCurrentUser();

        try {
            reviewServ.reviewDelete(review,currentUser);
        } catch (EntityNotFound e) {
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/login";
        }
        redirectAttributes.addAttribute("id", review.getProduct().getIdProductos());
        return "redirect:/product/{id}";
    }

    @GetMapping("/profile")
    public String getUser( Model model) {
        //se saca el usuario que se ha Iniciado.
        UserEntity currentUser = getCurrentUser();

        ListaFavoritosEntity favList = listFavServ.favFindByUsername(currentUser);
        List<RegionEntity> regiones = regionServ.regionFindAll();

        model.addAttribute("user", currentUser);
        model.addAttribute("favList", favList);
        model.addAttribute("listaFav", new ListaFavoritosEntity());
        model.addAttribute("regiones", regiones);
        model.addAttribute("title", currentUser.getNombre()+" Profile");
        return "security/user";
    }
    @PostMapping("/profile/useredit")
    public String editUser(UserEntity userEdit, RedirectAttributes redirectAttributes){
        try {
            userServ.userEdit(userEdit,(long)userEdit.getIdUsuario());
        } catch (EntityNotFound | FormFieldEmpty | EntityAlreadyExists e) {
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/profile";
        }
        redirectAttributes.addFlashAttribute("Message", "User Data edited!");
        return "redirect:/profile";
    }


    @PostMapping("/addFav")
    public String addFav(@RequestParam("idP") Long idP, RedirectAttributes redirectAttributes){
        ProductosEntity productDB = prodServ.prodFindByID(idP);
        UserEntity currentUser = getCurrentUser();

        try {
            listFavServ.favAddProd(productDB,currentUser);
        } catch (EntityAlreadyExists |EntityNotFound e) {
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/profile";
        }
        redirectAttributes.addFlashAttribute("Message", "Product added successfully to Favorite List!");
        return  "redirect:/index";

    }
    @PostMapping("/removeFav")
    public String removeFav(@RequestParam("idP") Long idP, RedirectAttributes redirectAttributes){
        ProductosEntity product = prodServ.prodFindByID(idP);
        UserEntity currentUser = getCurrentUser();
        try {
            listFavServ.favRemoveProd(product,currentUser);
        } catch (EntityNotFound e) {
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/profile";
        }
        redirectAttributes.addFlashAttribute("Message", "Product removed!");
        return "redirect:/profile";
    }

    @PostMapping("/addListaFav")
    public String addListaFav(ListaFavoritosEntity listaFav, RedirectAttributes redirectAttributes){
        UserEntity currentUser = getCurrentUser();

        try {
            listFavServ.favAdd(listaFav,currentUser);
        } catch (EntityAlreadyExists | EntityNotFound e) {
            redirectAttributes.addFlashAttribute("Message", e.getMessage());
            return "redirect:/profile";
        }
        redirectAttributes.addFlashAttribute("Message", "Favorite List Created!");
        return "redirect:/profile";
    }

    //LOgin y Register para usuarios.
    @GetMapping({"/login"})
    public String goToLogin(Model model){
        model.addAttribute("title","Login");
        return "security/login";
    }
    @GetMapping("/register")
    public String goToregister(Model model){
        model.addAttribute("user", new UserEntity());
        List<RegionEntity> regiones = regionServ.regionFindAll();
        model.addAttribute("regiones", regiones);
        model.addAttribute("title", "User register");
        return "security/register";
    }
    @PostMapping("/process_register")
    public String processRegister(UserEntity user, RedirectAttributes redirectAttributes) {
        try {
            userServ.processReg(user);
            redirectAttributes.addFlashAttribute("regSuccess", "Sucessfully created!");
            return "redirect:/login";
        }catch (EntityAlreadyExists | UserAgeToLow | FormFieldEmpty uae){
            redirectAttributes.addFlashAttribute("errorForm", uae.getMessage());
            return "redirect:/register";
        }
    }

    @PostMapping("/search")
    public String searchProd(@RequestParam("searchKey") Optional<String> searchKey, @RequestParam("order") String order, RedirectAttributes redirectAttributes){
        String search = searchKey.orElse(null);

        List<ProductosEntity> products = prodServ.searchByKey(search);
        switch (order){
            case "pas":
                Collections.sort(products);
                break;
            case "pde":
                Collections.sort(products,Collections.reverseOrder());
                break;
        }
        redirectAttributes.addFlashAttribute("products", products);
        redirectAttributes.addFlashAttribute("searchKey", searchKey);
        return "redirect:/searching";
    }
}