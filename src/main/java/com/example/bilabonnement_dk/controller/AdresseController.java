package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Adresse;
import com.example.bilabonnement_dk.service.AdresseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdresseController {

    @Autowired
    private AdresseService adresseService;

    @GetMapping("home/adresse")
    public String showCreateAdresseForm(Model model) {
        model.addAttribute("adresse", new Adresse());
        return "home/adresse";
    }

    @PostMapping("home/adresse")
    public String createAdresse(@ModelAttribute Adresse adresse) {
        adresseService.addAdresse(adresse);
        int latestAdresseId = adresseService.getLatestAdresseID();
        return "redirect:/home/kunde?adresseId=" + latestAdresseId;
    }
}