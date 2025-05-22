package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.model.Salgsaftale;
import com.example.bilabonnement_dk.service.SalgsaftaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SalgsaftaleController {

    @Autowired
    SalgsaftaleService salgsaftaleService;

    private Medarbejder hentMedarbejderHvisAdgang(HttpSession session, String ønsketRolle) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");
        if (medarbejder != null && medarbejder.getRolle().name().equals(ønsketRolle)) {
            return medarbejder;
        }
        return null;
    }

    @GetMapping("/Dataregistrere/createSalgsaftale")
    public String showCreateSalgsaftaleForm(Model model) {
        model.addAttribute("salgsaftale", new Salgsaftale());
        return "Dataregistrere/createSalgsaftale";
    }

    @PostMapping("/Dataregistrere/createSalgsaftale")
    public String opretSalgsaftale(@ModelAttribute Salgsaftale salgsaftale, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER");
        if (medarbejder == null) {
            return "redirect:/";
        }
        salgsaftale.setMedarbejder(medarbejder);
        salgsaftaleService.addSalgsaftale(salgsaftale);
        redirectAttributes.addFlashAttribute("besked", "Salgsaftale oprettet!");
        return "redirect:/data";
    }

    @GetMapping("/Dataregistrere/VisSalgsaftaler")
    public String showOpretSalgsaftaleForm(Model model) {
        model.addAttribute("salgsaftale", new Salgsaftale());
        return "Dataregistrere/VisSalgsaftale";
    }

    @GetMapping("/Dataregistrere/Vissalgsaftaler")
    public String visAlleSalgsaftale(Model model) {
        model.addAttribute("salgsaftaleliste", salgsaftaleService.fetchAll());
        return "Dataregistrere/visSalgsaftale";
    }

    @PostMapping("/Dataregistrere/sletSalgsaftale")
    public String sletSalgsaftale(@RequestParam("salgs_ID") int salgs_ID, RedirectAttributes redirectAttributes, HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        try {
            salgsaftaleService.deleteSalgsaftale(salgs_ID);
            redirectAttributes.addFlashAttribute("besked", "Salgsaftale med ID: " + salgs_ID + " er blevet slettet.");
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen salgsaftale fundet med det angivne ID: " + salgs_ID);
        }
        return "redirect:/Dataregistrere/sletSalgsaftale";
    }
}