package com.example.restful_demo_app.controller;

import com.example.restful_demo_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private UserRepository userRepository;

    @Autowired
    public HomeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public String freeEndpoint() {
        return "This endpoint is free";
    }

    @GetMapping("/secured")
    public String securedEndpoint() {
        var auth = SecurityContextHolder.getContext().getAuthentication();

        DefaultOAuth2User user = (DefaultOAuth2User) auth.getPrincipal();
        var attr = user.getAttributes();
        return "This is secured endpoint!!! You logged in as: " + attr.get("login");
    }
}
