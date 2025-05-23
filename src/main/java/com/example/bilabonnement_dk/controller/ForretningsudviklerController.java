package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.service.BilService;
import com.example.bilabonnement_dk.service.ForretningsudviklerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/forretningsudvikler")
public class ForretningsudviklerController {

    private final BilService bilService;
    private ForretningsudviklerService forretningsudviklerService;

    public ForretningsudviklerController(ForretningsudviklerService forretningsudviklerService, BilService bilService) {
        this.forretningsudviklerService = forretningsudviklerService;
        this.bilService = bilService;
    }
    @GetMapping("forretningsudvikler/read")
    public String visDashboard(Model model) {
        int antalUdlejde = forretningsudviklerService.hentAlleLeasninger();
        model.addAttribute("antalUdlejde", antalUdlejde);
        int aktiveUdlejninger = forretningsudviklerService.hentAntalAktiveBiler();
        model.addAttribute("antalAktive",aktiveUdlejninger);
        double udlejningsgrad = forretningsudviklerService.hentUdlejningsgrad();
        if (antalUdlejde > 0) {
            udlejningsgrad = ((double) aktiveUdlejninger / antalUdlejde) * 100;
        }
        model.addAttribute("udlejningsgrad", udlejningsgrad);
        List<Bil> udlejedeBiler = forretningsudviklerService.hentUdlejedeBiler();
        List<Bil> bilerPaaLager = forretningsudviklerService.hentBilerPaaLager();

        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("bilerPaaLager", bilerPaaLager);

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