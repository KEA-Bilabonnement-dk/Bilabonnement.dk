package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.BilService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BilController {

    @Autowired
    BilService bilService;

    // Opretter ny bil, kun for dataregistreringsmedarbejder
    @PostMapping("/Dataregistrere/CreateBil")
    public String createBil(@ModelAttribute Bil bil, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        // Tjekker rolle, redirect til login hvis ikke korrekt
        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/";
        }

        bilService.addBil(bil);
        redirectAttributes.addFlashAttribute("besked", "Bil oprettet!");
        return "redirect:/Dataregistrere/viewBil";
    }

    // Viser formular til oprettelse af bil
    @GetMapping("/Dataregistrere/CreateBil")
    public String showCreateBilForm(Model model) {
        model.addAttribute("bil", new Bil());
        return "Dataregistrere/CreateBil";
    }

    // Viser liste over alle biler
    @GetMapping("/Dataregistrere/viewBil")
    public String visAlleBiler(Model model) {
        model.addAttribute("bilListe", bilService.fetchAll());
        return "Dataregistrere/viewBil";
    }

    // Sletter bil efter ID
    @GetMapping("/Dataregistrere/deleteBil")
    public String deleteBil(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        bilService.deleteBil(id);
        redirectAttributes.addFlashAttribute("besked", "Bil slettet!");
        return "redirect:/Dataregistrere/viewBil";
    }

    // Søger biler på tværs af felter, eller viser alle hvis tom søgning
    @GetMapping("/Dataregistrere/searchAll")
    public String searchAll(@RequestParam(value = "q", required = false) String query, Model model) {
        List<Bil> results;

        if (query == null || query.trim().isEmpty()) {
            results = bilService.fetchAll();
        } else {
            results = bilService.searchAllFields(query);
        }

        model.addAttribute("bilListe", results);
        model.addAttribute("searchQuery", query);
        return "Dataregistrere/viewBil";
    }

    // Søger biler til modal fragment, returnerer kun fragment
    @GetMapping("/Dataregistrere/searchAllFragment")
    public String searchAllCarsModal(@RequestParam(value = "q", required = false) String query, Model model) {
        List<Bil> results;

        if (query == null || query.trim().isEmpty()) {
            results = bilService.fetchAll();
        } else {
            results = bilService.searchAllFields(query);
        }

        model.addAttribute("bilListe", results);
        return "leasing/fragments :: carRows";
    }
}