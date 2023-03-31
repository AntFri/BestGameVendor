package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.exceptions.UserAgeToLow;
import com.antoniofrische.bestgamevendor.exceptions.UserAlreadyExists;
import com.antoniofrische.bestgamevendor.models.*;
import com.antoniofrische.bestgamevendor.repositorios.*;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
import com.antoniofrische.bestgamevendor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.MalformedURLException;
import java.util.List;

@Controller
public class PageControler {
    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IProductRepository iProductRepository;
    @Autowired
    private IListaRebajasRepository iListaRebajasRepository;
    @Autowired
    private IRegionRepository iRegionRepository;
    @Autowired
    private IListaFavoritos iListaFavoritos;
    @Autowired
    private IReviewRepository iReviewRepository;
    @Autowired
    private UserService userInter;

    Logger logger = LoggerFactory.getLogger(PageControler.class);

    @GetMapping({"","/","/inicio","/index"})
    public String goToIndex(Model model) throws MalformedURLException {
        model.addAttribute("title","Inicio");
        List<ProductosEntity> products = iProductRepository.findAll();
        model.addAttribute("products", products);

        model.addAttribute("fav", new ListaFavoritosEntity());
        return "index";
    }

    @GetMapping("/profile/lista_favorito/{id}")
    public String getListaFav(@PathVariable("id") Long id, Model model){
        ListaFavoritosEntity fav = iListaFavoritos.findById(id).orElse(null);
        model.addAttribute("datosfav", fav);
        return "security/listaFav";
    }

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable("id") Long id, Model model) {
        ProductosEntity product = iProductRepository.findById(id).orElse(null);
        List<ListaRebajasproductosEntity> lres = iListaRebajasRepository.findByProductID(product);
        List<ReviewEntity> reviews = iReviewRepository.findByProduct(product);
        model.addAttribute("websites",lres);
        model.addAttribute("product", product);
        model.addAttribute("reviews",reviews);
        model.addAttribute("review", new ReviewEntity());
        model.addAttribute("title", "Game, "+product.getNombre());
        return "products/product";
    }
    @PostMapping("/addReview")
    public String addReview(ReviewEntity review){
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        assert detailUser != null;
        UserEntity currentUser;
        if(detailUser == null){
            return "redirect:/login";
        } else{
            currentUser = iUserRepository.findByEmail(detailUser.getUsername());
            review.setUser(currentUser);
            iReviewRepository.save(review);
            return  "redirect:/index";
        }
    }


    @GetMapping("/profile")
    public String getUser( Model model) {
        //se saca el usuario que se ha Iniciado.
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = iUserRepository.findByEmail(detailUser.getUsername());

        ListaFavoritosEntity favList = iListaFavoritos.findNameByUser(currentUser);

        model.addAttribute("user", currentUser);
        model.addAttribute("favList", favList);
        model.addAttribute("listaFav", new ListaFavoritosEntity());
        model.addAttribute("title", currentUser.getNombre()+" Profile");
        return "security/user";
    }
    @PostMapping("/addFav")
    public String addFav(@RequestParam("idP") Long idP, RedirectAttributes redirectAttributes){
        ProductosEntity productosEntity = iProductRepository.findById(idP).orElse(null);
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(detailUser == null){
            redirectAttributes.addFlashAttribute("regSuccess", "Login to add a fav!");
            return "redirect:/login";
        } else{
            UserEntity currentUser = iUserRepository.findByEmail(detailUser.getUsername());
            ListaFavoritosEntity listaFavorito = iListaFavoritos.findNameByUser(currentUser);
            if(listaFavorito==null){
                redirectAttributes.addFlashAttribute("Message", "Creat a Favorit list first!");
                return "redirect:/profile";
            }
            boolean isExist = listaFavorito.getProductlist().equals(productosEntity);
            if(isExist){
                redirectAttributes.addFlashAttribute("Message", "is allready added to list!");
                return "redirect:/profile";
            }
            listaFavorito.setProductlist(productosEntity);
            iListaFavoritos.save(listaFavorito);
            return  "redirect:/index";
        }
    }
    @PostMapping("/addListaFav")
    public String addListaFav(ListaFavoritosEntity listaFav, RedirectAttributes redirectAttributes){
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = iUserRepository.findByEmail(detailUser.getUsername());
        listaFav.setUser(currentUser);
        ListaFavoritosEntity searchifexist = iListaFavoritos.findNameByUser(currentUser);
        if(searchifexist == null){
            iListaFavoritos.save(listaFav);
            redirectAttributes.addFlashAttribute("Message", "Favorit List created!");
            return "redirect:/profile";
        }
        redirectAttributes.addFlashAttribute("Message", "You have already a list, 1 List is enought!");
        return "redirect:/profile";
    }
    @PostMapping("/removeFav")
    public String removeFav(@RequestParam("idP") Long idP, RedirectAttributes redirectAttributes){
        ProductosEntity product = iProductRepository.findById(idP).orElse(null);
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity currentUser = iUserRepository.findByEmail(detailUser.getUsername());
        ListaFavoritosEntity listaFav = iListaFavoritos.findNameByUser(currentUser);
        if(listaFav == null){
            redirectAttributes.addFlashAttribute("Message", "No tienes lista de Favoritos!");
            return "redirect:/profile";
        }
        listaFav.deleteProduct(product);
        iListaFavoritos.save(listaFav);
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
        List<RegionEntity> regiones = iRegionRepository.findAll();
        model.addAttribute("regiones", regiones);
        model.addAttribute("title", "User register");
        return "security/register";
    }
    @PostMapping("/process_register")
    public String processRegister(UserEntity user, RedirectAttributes redirectAttributes) {
        try {
            userInter.processReg(user);
            redirectAttributes.addFlashAttribute("regSuccess", "Sucessfully created!");
            return "redirect:/login";
        }catch (UserAlreadyExists | UserAgeToLow uae){
            redirectAttributes.addFlashAttribute("errorForm", uae.getMessage());
            return "redirect:/register";
        }
    }
}