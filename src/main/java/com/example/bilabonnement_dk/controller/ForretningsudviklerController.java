package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.service.ForretningsudviklerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/forretningsudvikling")
public class ForretningsudviklerController {

    private ForretningsudviklerService forretningsudviklerService;

    public ForretningsudviklerController(ForretningsudviklerService forretningsudviklerService) {
        this.forretningsudviklerService = forretningsudviklerService;
    }
    @GetMapping("/dashboard")
    public String visDashboard(Model model) {
        int antalUdlejde = forretningsudviklerService.hentAntalUdlejdeBiler();
        var samletPris = forretningsudviklerService.hentSamletPrisUdlejdeBiler();
        model.addAttribute("antalUdlejde", antalUdlejde);
        model.addAttribute("samletPris", samletPris);
        return "dashboard";

    }




}
