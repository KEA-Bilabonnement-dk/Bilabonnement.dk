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


    @PostMapping("/Dataregistrere/CreateBil")
    public String createBil(@ModelAttribute Bil bil, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/";
        }

        bilService.addBil(bil);

        redirectAttributes.addFlashAttribute("besked", "Bil oprettet!");

        return "redirect:/Dataregistrere/viewBil";
    }

    @GetMapping("/Dataregistrere/CreateBil")
    public String showCreateBilForm(Model model) {
        model.addAttribute("bil", new Bil());
        return "Dataregistrere/CreateBil";
    }

    @GetMapping("/Dataregistrere/viewBil")
    public String visAlleBiler(Model model) {
        model.addAttribute("bilListe", bilService.fetchAll());
        return "Dataregistrere/viewBil";
    }

    @GetMapping("/Dataregistrere/deleteBil")
    public String deleteBil(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        bilService.deleteBil(id); // deletes the car with this ID
        redirectAttributes.addFlashAttribute("besked", "Bil slettet!");
        return "redirect:/Dataregistrere/viewBil";
    }


    @GetMapping("/Dataregistrere/searchAll")
    public String searchAll(@RequestParam(value = "q", required = false) String query, Model model) {
        List<Bil> results;

        if (query == null || query.trim().isEmpty()) {
            results = bilService.fetchAll(); // Vis alle biler, hvis ingen søgning
        } else {
            results = bilService.searchAllFields(query);
        }

        model.addAttribute("bilListe", results);
        model.addAttribute("searchQuery", query);
        return "Dataregistrere/viewBil";
    }

    @GetMapping("/Dataregistrere/searchAllFragment")
    public String searchAllCarsModal(@RequestParam(value = "q", required = false) String query, Model model) {
        List<Bil> results;
        if (query == null || query.trim().isEmpty()) {
            results = bilService.fetchAll(); // Fetch all cars if no query
        } else {
            results = bilService.searchAllFields(query);
        }
        model.addAttribute("bilListe", results);
        return "leasing/fragments :: carRows";
    }
}