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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BilController {
    @Autowired
    BilService bilService;

    @GetMapping("bil")
    public String index(Model model) {
        // Henter alle biler fra Service laget
        List<Bil> bil = bilService.fetchAll();
        // Tilføjer til model attribute
        model.addAttribute("bil", bil);
        // Returnere navnet på HTML filen så den kan render
        return "home/bil";
    }

    @PostMapping("/Dataregistrere/CreateBil")
    public String createBil(@ModelAttribute Bil bil, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");

        if (medarbejder == null || !medarbejder.getRolle().name().equals("DATAREGISTRERINGSMEDARBEJDER")) {
            return "redirect:/";
        }

        bilService.addBil(bil);

        redirectAttributes.addFlashAttribute("besked", "Bil oprettet!");

        return "redirect:/data";
    }

    @GetMapping("/Dataregistrere/CreateBil")
    public String showCreateBilForm(Model model) {
        model.addAttribute("bil", new Bil());
        return "Dataregistrere/CreateBil";
    }

    @GetMapping("/Dataregistrere")
    public String visAlleLeasingAftaler(Model model) {
        model.addAttribute("bilListe", bilService.fetchAll());
        return "Dataregistrere/ViewAllBil";
    }
}