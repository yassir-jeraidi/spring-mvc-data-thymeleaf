package com.spring.springmvcdatathymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("access-denied")
    public String accessDenied() {
        return "security/exceptions/access-denied";
    }

}
