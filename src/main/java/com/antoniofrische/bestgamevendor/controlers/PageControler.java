package com.antoniofrische.bestgamevendor.controlers;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControler {
    @GetMapping({"","/","/inicio","/index"})
    public String goToIndex(Model model){
        model.addAttribute("message", "Hello Antonio!");
        model.addAttribute("dominio","Inicio");
        return "index";
    }

    @GetMapping({"/login"})
    public String goToLogin(Model model){
        model.addAttribute("message", "Hello Antonio!");
        model.addAttribute("dominio","Login");
        return "security/login";
    }
    @GetMapping({"/register"})
    public String goToregister(Model model){
        model.addAttribute("message", "Hello Antonio!");
        model.addAttribute("dominio","Registrar");
        return "security/register";
    }

    @GetMapping({"/game"})
    public String goToGame(Model model){
        model.addAttribute("message", "Hello Antonio!");
        model.addAttribute("dominio","Game");
        return "products/game";
    }

    @GetMapping({"/user"})
    public String goToUser(Model model){
        model.addAttribute("message", "Hello Antonio!");
        model.addAttribute("dominio","user");
        return "security/user";
    }
}