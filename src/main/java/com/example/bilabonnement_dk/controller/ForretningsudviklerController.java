package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.service.BilService;
import com.example.bilabonnement_dk.service.ForretningsudviklerService;
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
    private final ForretningsudviklerService forretningsudviklerService;

    // Constructor injection for services
    public ForretningsudviklerController(ForretningsudviklerService forretningsudviklerService, BilService bilService) {
        this.forretningsudviklerService = forretningsudviklerService;
        this.bilService = bilService;
    }

    // Viser dashboard med statistik om leasing og biler
    @GetMapping("/read")
    public String visDashboard(Model model) {
        int antalUdlejde = forretningsudviklerService.hentAlleLeasninger();
        int aktiveUdlejninger = forretningsudviklerService.hentAntalAktiveBiler();
        double udlejningsgrad = 0;

        if (antalUdlejde > 0) {
            udlejningsgrad = ((double) aktiveUdlejninger / antalUdlejde) * 100; // beregn udlejningsgrad i procent
        }

        List<Bil> udlejedeBiler = forretningsudviklerService.hentUdlejedeBiler();
        List<Bil> bilerPaaLager = forretningsudviklerService.hentBilerPaaLager();

        model.addAttribute("antalUdlejde", antalUdlejde);
        model.addAttribute("antalAktive", aktiveUdlejninger);
        model.addAttribute("udlejningsgrad", udlejningsgrad);
        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("bilerPaaLager", bilerPaaLager);

        return "forretningsudvikler/read";
    }

    // Viser samlet pris for udlejede biler og aktive udlejede biler
    @GetMapping("/readPris")
    public String visDashboardPris(Model model) {
        BigDecimal samletPris = forretningsudviklerService.hentSamletPrisUdlejdeBiler();
        BigDecimal aktivPris = forretningsudviklerService.hentSamletPrisAktiveUdlejdeBiler();

        model.addAttribute("samletPris", samletPris);
        model.addAttribute("aktivPris", aktivPris);

        return "forretningsudvikler/readPris";
    }

    // Viser liste over udlejede biler og biler på lager
    @GetMapping("/bilerliste")
    public String bilerListe(Model model) {
        List<Bil> udlejedeBiler = forretningsudviklerService.hentUdlejedeBiler();
        List<Bil> bilerPaaLager = forretningsudviklerService.hentBilerPaaLager();

        model.addAttribute("udlejedeBiler", udlejedeBiler);
        model.addAttribute("bilerPaaLager", bilerPaaLager);

        return "forretningsudvikler/bilerliste";
    }

    // Viser top 3 kunder med flest leasingaftaler
    @GetMapping("/topKunder")
    public String visTopKunder(Model model) {
        List<Map<String, Object>> topKunder = forretningsudviklerService.hentTop3KunderMedFlestLeasingaftaler();
        model.addAttribute("topKunder", topKunder);
        return "forretningsudvikler/topKunder";
    }
}