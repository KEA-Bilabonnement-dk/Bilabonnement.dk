package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.model.Salgsaftale;
import com.example.bilabonnement_dk.service.KundeService;
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

    @Autowired
    KundeService kundeService;

    // Tjekker om medarbejder har den rette rolle
    private Medarbejder hentMedarbejderHvisAdgang(HttpSession session, String ønsketRolle) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");
        if (medarbejder != null && medarbejder.getRolle().name().equals(ønsketRolle)) {
            return medarbejder;
        }
        return null;
    }

    // Viser formular til oprettelse af salgsaftale (evt. med kunde valgt)
    @GetMapping("/Dataregistrere/createSalgsaftale")
    public String showCreateSalgsaftale(@RequestParam(value = "kundeId", required = false) Integer kundeId, Model model) {
        Salgsaftale salgsaftale = new Salgsaftale();
        if (kundeId != null) {
            Kunde kunde = kundeService.findKundeByID(kundeId);
            salgsaftale.setKunde(kunde);
        }
        model.addAttribute("salgsaftale", salgsaftale);
        return "Dataregistrere/createSalgsaftale";
    }

    // Opretter en ny salgsaftale
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

    // Viser alle salgsaftaler
    @GetMapping("/Dataregistrere/visSalgsaftaler")
    public String visAlleSalgsaftale(Model model) {
        model.addAttribute("salgsaftaleliste", salgsaftaleService.fetchAll());
        return "Dataregistrere/visSalgsaftaler";
    }

    // Sletter en salgsaftale ud fra ID, håndterer fejl hvis ID ikke findes
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
        return "redirect:/Dataregistrere/visSalgsaftaler";
    }

    // Viser detaljer for en enkelt salgsaftale
    @GetMapping("/viewOne/{salgs_ID}")
    public String visSalgsaftaleDetaljer(@PathVariable int salgs_ID, Model model) {
        Salgsaftale salgsaftale = salgsaftaleService.findSalgsaftaleByID(salgs_ID);
        model.addAttribute("salgsaftale", salgsaftale);
        return "Dataregistrere/viewOneSalgsaftale";
    }
}