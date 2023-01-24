package com.antoniofrische.bestgamevendor.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class McvConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("security/login").setViewName("login");
        registry.addViewController("security/user").setViewName("user");
    }

}

