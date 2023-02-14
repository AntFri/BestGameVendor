package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.models.ProductosEntity;
import com.antoniofrische.bestgamevendor.models.PublisherEntity;
import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
import com.antoniofrische.bestgamevendor.repositorios.IProductRepository;
import com.antoniofrische.bestgamevendor.repositorios.IPublisherRepository;
import com.antoniofrische.bestgamevendor.repositorios.IUserRepository;
import com.antoniofrische.bestgamevendor.security.models.CustomUserDetails;
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
    private IPublisherRepository iPublisherRepository;

    @GetMapping({"","/","/inicio","/index"})
    public String goToIndex(Model model) throws MalformedURLException {
        model.addAttribute("dominio","Inicio");
        List<ProductosEntity> products = iProductRepository.findAll();
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping({"/login"})
    public String goToLogin(Model model){
        model.addAttribute("dominio","Login");
        return "security/login";
    }
    @GetMapping({"/register"})
    public String goToregister(Model model){
        model.addAttribute("user", new UsuarioEntity());
        return "security/register";
    }
    @PostMapping("/process_register")
    public String processRegister(UsuarioEntity user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        iUserRepository.save(user);
        return "index";
    }

    @GetMapping("/product/{id}")
    public String getProductPage(@PathVariable("id") Long id, Model model) {
        ProductosEntity product = iProductRepository.findById(id).orElse(null);
        model.addAttribute("product", product);
        return "products/product";
    }

    @GetMapping("/profile")
    public String getUser( Model model) {
        //se saca el usuario que se ha aniciado.
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioEntity user = iUserRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "security/user";
    }


}