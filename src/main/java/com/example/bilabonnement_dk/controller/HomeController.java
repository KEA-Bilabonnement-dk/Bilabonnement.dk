package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @Autowired
    AuthService authService;

    @GetMapping("/")
    public String index()
    {
        return "home/index";
    }
}
