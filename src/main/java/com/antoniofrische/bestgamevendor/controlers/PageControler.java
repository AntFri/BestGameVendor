package com.antoniofrische.bestgamevendor.controlers;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageControler {
    @GetMapping({"","/","/inicio","/index"})
    public String goToIndex(Model model){
        model.addAttribute("message", "Hello Antonio!");
        return "index";
    }

    @GetMapping({"/login"})
    public String goToLogin(Model model){
        model.addAttribute("message", "Hello Antonio!");
        return "security/login";
    }
    @GetMapping({"/register"})
    public String goToregister(Model model){
        model.addAttribute("message", "Hello Antonio!");
        return "security/register";
    }

    @GetMapping({"/user"})
    public String goToUser(Model model){
        model.addAttribute("message", "Hello Antonio!");
        return "security/user";
    }
}