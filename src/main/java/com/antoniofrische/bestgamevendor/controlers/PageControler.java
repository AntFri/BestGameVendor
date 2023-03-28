package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.models.*;
import com.antoniofrische.bestgamevendor.repositorios.*;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

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

    Logger logger = LoggerFactory.getLogger(PageControler.class);
    private UsuarioEntity currentUser = null;

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
        System.out.println(review.getReviewText());
        if(currentUser == null){
            return "security/login";
        } else{
            review.setUser(currentUser);
            iReviewRepository.save(review);
            return  "redirect:/index";
        }
    }


    @GetMapping("/profile")
    public String getUser( Model model) {
        //se saca el usuario que se ha Iniciado.
        CustomUserDetails detailUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.warn(detailUser.getAuthorities().toString());
        currentUser = iUserRepository.findByEmail(detailUser.getUsername());
        List<ProductosEntity> listProd  = iListaFavoritos.findByUser(currentUser);
        ListaFavoritosEntity fav = iListaFavoritos.findNameByUser(currentUser);
        model.addAttribute("user", currentUser);
        if(fav==null){
            fav = new ListaFavoritosEntity();
            fav.setNombre("no tienes Lista de fav");
        }
        logger.info("Logged In!");
        model.addAttribute("fav", fav);
        model.addAttribute("ListaProd", listProd);
        model.addAttribute("listaFav", new ListaFavoritosEntity());
        model.addAttribute("title", currentUser.getNombre()+" Profile");
        return "security/user";
    }
    @PostMapping("/addFav")
    public String addFav(@RequestParam("idP") Long idP){
        ListaFavoritosEntity fav = new ListaFavoritosEntity();
        ProductosEntity productosEntity = iProductRepository.findById(idP).orElse(null);
        if(currentUser == null){
            return "security/login";
        } else{
            ListaFavoritosEntity listaFavorito = iListaFavoritos.findNameByUser(currentUser);
            if(listaFavorito==null){
                return "redirect:/profile";
            }
            fav.setNombre(listaFavorito.getNombre());
            fav.setProduct(productosEntity);
            fav.setUser(currentUser);
            iListaFavoritos.save(fav);
            return  "redirect:/index";
        }
    }
    @PostMapping("/addListaFav")
    public String addListaFav(ListaFavoritosEntity listaFav){
        listaFav.setUser(currentUser);
        ListaFavoritosEntity searchifexist = iListaFavoritos.findListByProd(listaFav.getProduct(),currentUser);
        if(searchifexist == null){
            iListaFavoritos.save(listaFav);
            return "redirect:/profile";
        }
        return "redirect:/error";
    }
    @PostMapping("/removeFav")
    public String removeFav(@RequestParam("idP") Long idP){
        ProductosEntity product = iProductRepository.findById(idP).orElse(null);
        ListaFavoritosEntity favoritosEntity = iListaFavoritos.findListByProd(product,currentUser);
        if(favoritosEntity == null){
            return "redirect:/";
        }
        iListaFavoritos.delete(favoritosEntity);
        return "redirect:/profile";
    }

    //LOgin y Register para usuarios.
    @GetMapping({"/login"})
    public String goToLogin(Model model){
        currentUser=null;
        model.addAttribute("title","Login");
        return "security/login";
    }
    @GetMapping({"/register"})
    public String goToregister(Model model){
        model.addAttribute("user", new UsuarioEntity());
        List<RegionEntity> regiones = iRegionRepository.findAll();
        model.addAttribute("regiones", regiones);
        model.addAttribute("title", "User register");
        return "security/register";
    }
    @PostMapping("/process_register")
    public String processRegister(UsuarioEntity user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setRole("user");
        iUserRepository.save(user);
        return "redirect:/index";
    }
}