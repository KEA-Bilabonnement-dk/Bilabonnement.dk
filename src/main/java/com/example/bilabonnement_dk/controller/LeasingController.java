package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.LeasingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LeasingController {

    @Autowired
    private LeasingService leasingService;

    @GetMapping("/leasing/create")
    public String visLeasingOpret(HttpSession session, Model model) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/";
        }
        model.addAttribute("leasing", new Leasing());
        return "leasing/create";
    }

    @PostMapping("/leasing/create")
    public String opretLeasing(@ModelAttribute Leasing leasing, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/";
        }

        leasing.setMedarbejder(medarbejder);
        leasingService.addLeasing(leasing);

        redirectAttributes.addFlashAttribute("besked", "Leasingaftale oprettet!");

        return "redirect:/data";
    }

    @GetMapping("/leasing/read")
    public String visAlleLeasingAftaler(Model model) {
        model.addAttribute("leasingliste", leasingService.fetchAll());
        return "leasing/read";
    }
}