package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RapportController {
    @GetMapping("home/rapport")
    public String rapport(HttpSession Session, Model model) {
        return "home/rapport";


    }
}
