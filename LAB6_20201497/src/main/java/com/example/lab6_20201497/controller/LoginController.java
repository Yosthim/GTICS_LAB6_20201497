package com.example.lab6_20201497.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }
}
