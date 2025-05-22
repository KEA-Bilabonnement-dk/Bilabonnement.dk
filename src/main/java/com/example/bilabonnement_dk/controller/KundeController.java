package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Adresse;
import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.service.AdresseService;
import com.example.bilabonnement_dk.service.KundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KundeController {

    @Autowired
    private KundeService kundeService;

    @GetMapping("home/kunde")
    public String showCreateKundeForm(Model model) {
        model.addAttribute("kunde", new Kunde());
        return "home/kunde";
    }

    @PostMapping("home/kunde")
    public String createAdresse(@ModelAttribute Kunde kunde) {
        kundeService.addKunde(kunde);
        return "redirect:/Dataregistrere/createSalgsaftale";
    }
}