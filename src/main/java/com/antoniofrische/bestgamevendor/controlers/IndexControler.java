package com.antoniofrische.bestgamevendor.controlers;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexControler {
    @GetMapping({"/","index.html","index"})
    public String goToIndex(Model model){
        model.addAttribute("message", "Hello Kevin!");
        return "index";
    }
}