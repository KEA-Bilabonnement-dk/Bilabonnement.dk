package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KundeController {
    @GetMapping("home/kunde")
    public String kundeController(HttpSession Session, Model model) {
        return "home/kunde";
    }
}
