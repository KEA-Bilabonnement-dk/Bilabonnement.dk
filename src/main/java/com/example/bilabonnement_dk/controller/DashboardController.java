package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    // Viser dataregistreringsmedarbejderens dashboard, tjekker session og rolle
    @GetMapping("/data")
    public String dataDashboard(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/"; // Redirect til login hvis ikke logget ind eller forkert rolle
        }
        return "home/data";
    }

    // Viser skadebehandlerens dashboard med samme sikkerhedstjek
    @GetMapping("/skade")
    public String skadeDashboard(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("SKADEBEHANDLER")) {
            return "redirect:/";
        }
        return "home/skade";
    }

    // Viser forretningsudviklerens dashboard med samme sikkerhedstjek
    @GetMapping("/forretning")
    public String forretningDashboard(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("FORRETNINGSUDVIKLER")) {
            return "redirect:/";
        }
        return "home/forretning";
    }
}