package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    @GetMapping("/data")
    public String dataDashboard(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/";
        }
        return "home/data";
    }

    @GetMapping("/skade")
    public String skadeDashboard(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("SKADEBEHANDLER")) {
            return "redirect:/";
        }
        return "home/skade";
    }

    @GetMapping("/forretning")
    public String forretningDashboard(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("FORRETNINGSUDVIKLER")) {
            return "redirect:/";
        }
        return "home/forretning";
    }
}
