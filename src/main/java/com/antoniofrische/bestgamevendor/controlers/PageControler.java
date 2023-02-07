package com.antoniofrische.bestgamevendor.controlers;

import com.antoniofrische.bestgamevendor.models.UsuarioEntity;
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

@Controller
public class PageControler {



    @Autowired
    private IUserRepository iUserRepository;

    @GetMapping({"","/","/inicio","/index"})
    public String goToIndex(Model model) throws MalformedURLException {
        model.addAttribute("dominio","Inicio");
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

    @GetMapping({"/game"})
    public String goToGame(Model model){
        model.addAttribute("message", "Hello Antonio!");
        model.addAttribute("dominio","Game");
        return "products/game";
    }

    @GetMapping("/user")
    public String getUser( Model model) {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UsuarioEntity user = iUserRepository.findByEmail(currentUser.getUsername());
        model.addAttribute("user", user);
        return "security/user";
    }
    @GetMapping({"/users"})
    public String goToUser(Model model){
        model.addAttribute("dominio","user");
        return "security/users";
    }

    @PostMapping("/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {

        return "Saved";
    }

    @GetMapping(path = "/all")
    public @ResponseBody List<UsuarioEntity> getAllUsers() {
        // This returns a JSON or XML with the users
        return iUserRepository.findAll();
    }
}