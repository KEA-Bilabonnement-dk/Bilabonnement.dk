package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BilController {
    @Autowired
    BilService bilService;

    @GetMapping("bil")
    public String index(Model model) {
        // Fetch all cars from the service
        List<Bil> bil = bilService.fetchAll();
        // Add the list of cars to the model attribute
        model.addAttribute("bil", bil);
        // Return the name of the Thymeleaf template to render
        return "home/bil"; // Matches /src/main/resources/templates/home/bil.html
    }
}