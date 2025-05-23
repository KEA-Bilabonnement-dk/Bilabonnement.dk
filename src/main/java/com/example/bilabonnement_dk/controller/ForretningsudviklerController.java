package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.service.ForretningsudviklerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
@RequestMapping("/forretningsudvikler")
public class ForretningsudviklerController {

    private ForretningsudviklerService forretningsudviklerService;

    public ForretningsudviklerController(ForretningsudviklerService forretningsudviklerService) {
        this.forretningsudviklerService = forretningsudviklerService;
    }
    @GetMapping("/forretningsudvikler/read")
    public String visDashboard(Model model) {
        int antalUdlejde = forretningsudviklerService.hentAlleLeasninger();
        model.addAttribute("antalUdlejde", antalUdlejde);
        int aktiveUdlejninger = forretningsudviklerService.hentAntalAktiveBiler();
        model.addAttribute("antalAktive",aktiveUdlejninger);
        return "forretningsudvikler/read";

    }

    @GetMapping("/forretningsudvikler/readpris")
    public String visDashboardPris(Model model) {
        BigDecimal samletPris = forretningsudviklerService.hentSamletPrisUdlejdeBiler();
        model.addAttribute("samletPris", samletPris);
        BigDecimal aktivPris = forretningsudviklerService.hentSamletPrisAktiveUdlejdeBiler();
        model.addAttribute("aktivPris", aktivPris);
        return "forretningsudvikler/readpris";

    }





}
