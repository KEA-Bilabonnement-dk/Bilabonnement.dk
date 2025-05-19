package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.LeasingService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String visAlleLeasing(Model model) {
        model.addAttribute("leasingliste", leasingService.fetchAll());
        return "leasing/read";
    }

    @GetMapping("/leasing/readOne")
    public String visEnLeasing(@RequestParam("leasing_ID") int leasing_ID, Model model, RedirectAttributes redirectAttributes) {
        try {
            Leasing leasing = leasingService.findLeasingByID(leasing_ID);
            model.addAttribute("leasing", leasing);
            return "leasing/readOne";
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen leasingaftale fundet med det angivne ID: " + leasing_ID);
            return "leasing/read";
        }
    }

    @GetMapping("/leasing/update")
    public String visOpdateringsFormular() {
        return "leasing/update";
    }

    @GetMapping("/leasing/updateOne")
    public String opdaterEnLeasing(@RequestParam("leasing_ID") int leasing_ID, Model model, RedirectAttributes redirectAttributes) {
        try {
            Leasing leasing = leasingService.findLeasingByID(leasing_ID);
            model.addAttribute("leasing", leasing);
            return "leasing/updateOne";
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen leasingaftale fundet med det angivne ID: " + leasing_ID);
            return "redirect:/leasing/update";
        }
    }

    @PostMapping("/leasing/update")
    public String opdaterLeasing(@ModelAttribute Leasing leasing) {
        leasingService.updateLeasing(leasing);
        return "leasing/read";
    }

    @GetMapping("/leasing/delete")
    public String visSletLeasing(Model model) {
        model.addAttribute("leasingliste", leasingService.fetchAll());
        return "leasing/delete";
    }

    @PostMapping("/leasing/delete")
    public String sletLeasing(@RequestParam("leasing_ID") int leasing_ID, RedirectAttributes redirectAttributes) {
        try {
            leasingService.deleteLeasing(leasing_ID);
            redirectAttributes.addFlashAttribute("besked", "Leasingaftale med ID: " + leasing_ID + " er blevet slettet.");
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen leasingaftale fundet med det angivne ID: " + leasing_ID);
        }
        return "redirect:/leasing/delete";
    }
}
