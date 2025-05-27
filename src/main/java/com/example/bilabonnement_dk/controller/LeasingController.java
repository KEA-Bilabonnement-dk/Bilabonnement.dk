package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.AdresseService;
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
    @Autowired
    private AdresseService adresseService;

    // Tjekker om medarbejder har adgang baseret på rolle, returnerer medarbejder hvis OK
    private Medarbejder hentMedarbejderHvisAdgang(HttpSession session, String ønsketRolle) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");
        if (medarbejder != null && medarbejder.getRolle().name().equals(ønsketRolle)) {
            return medarbejder;
        }
        return null;
    }

    // Viser formular til oprettelse af leasingaftale
    @GetMapping("/leasing/create")
    public String visLeasingOpret(HttpSession session, Model model) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        model.addAttribute("leasing", new Leasing());
        model.addAttribute("adresseliste", adresseService.fetchAll());
        return "leasing/create";
    }

    // Opretter leasingaftale og tilknytter medarbejder
    @PostMapping("/leasing/create")
    public String opretLeasing(@ModelAttribute Leasing leasing, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER");
        if (medarbejder == null) {
            return "redirect:/";
        }
        leasing.setMedarbejder(medarbejder);
        leasingService.addLeasing(leasing);
        redirectAttributes.addFlashAttribute("besked", "Leasingaftale oprettet!");
        return "redirect:/data";
    }

    // Viser liste over alle leasingaftaler
    @GetMapping("/leasing/read")
    public String visAlleLeasing(HttpSession session, Model model) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        model.addAttribute("leasingliste", leasingService.fetchAll());
        return "leasing/read";
    }

    // Viser detaljer om en specifik leasingaftale
    @GetMapping("/leasing/readOne")
    public String visEnLeasing(@RequestParam("leasing_ID") int leasing_ID,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        Leasing leasing = leasingService.findLeasingByID(leasing_ID);
        if (leasing == null) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen leasingaftale fundet med det angivne ID: " + leasing_ID);
            return "redirect:/leasing/read";
        }
        model.addAttribute("leasing", leasing);
        return "leasing/readOne";
    }

    // Viser formular til opdatering af leasingaftale
    @GetMapping("/leasing/update")
    public String visOpdateringsFormular(HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        return "leasing/update";
    }

    // Viser detaljer til opdatering af en enkelt leasingaftale
    @GetMapping("/leasing/updateOne")
    public String opdaterEnLeasing(@RequestParam("leasing_ID") int leasing_ID,
                                   Model model,
                                   @ModelAttribute("fejlbesked") String fejlbesked,
                                   HttpSession session,
                                   RedirectAttributes redirectAttributes) {

        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }

        if (!model.containsAttribute("leasing")) {
            Leasing leasing = leasingService.findLeasingByID(leasing_ID);
            if (leasing == null) {
                redirectAttributes.addFlashAttribute("fejlbesked", "Ingen leasingaftale fundet med det angivne ID: " + leasing_ID);
                return "redirect:/leasing/update";
            }
            model.addAttribute("leasing", leasing);
        }

        if (fejlbesked != null && !fejlbesked.isEmpty()) {
            model.addAttribute("fejlbesked", fejlbesked);
        }
        model.addAttribute("adresseliste", adresseService.fetchAll());

        return "leasing/updateOne";
    }

    // Opdaterer leasingaftale efter indsendt formular
    @PostMapping("/leasing/update")
    public String opdaterLeasing(@ModelAttribute Leasing leasing, HttpSession session, RedirectAttributes redirectAttributes) {
        Medarbejder medarbejder = hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER");
        if (medarbejder == null) {
            return "redirect:/";
        }

        if (leasing.getStartdato() == null) {
            redirectAttributes.addFlashAttribute("leasing", leasing);
            redirectAttributes.addFlashAttribute("fejlbesked", "Startdato må ikke være tom.");
            return "redirect:/leasing/updateOne?leasing_ID=" + leasing.getLeasing_ID();
        }

        leasing.setMedarbejder(medarbejder);
        leasingService.updateLeasing(leasing);
        redirectAttributes.addFlashAttribute("besked", "Leasingaftale opdateret.");
        return "redirect:/leasing/read";
    }

    // Viser slet-side med liste over leasingaftaler
    @GetMapping("/leasing/delete")
    public String visSletLeasing(Model model, HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        model.addAttribute("leasingliste", leasingService.fetchAll());
        return "leasing/delete";
    }

    // Sletter leasingaftale baseret på ID
    @PostMapping("/leasing/delete")
    public String sletLeasing(@RequestParam("leasing_ID") int leasing_ID,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        try {
            leasingService.deleteLeasing(leasing_ID);
            redirectAttributes.addFlashAttribute("besked", "Leasingaftale med ID: " + leasing_ID + " er blevet slettet.");
        } catch (EmptyResultDataAccessException e) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen leasingaftale fundet med det angivne ID: " + leasing_ID);
        }
        return "redirect:/leasing/delete";
    }

    // Marker leasingaftale som afleveret
    @PostMapping("/leasing/aflever")
    public String markerSomAfleveret(@RequestParam("leasing_ID") int leasing_ID,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "DATAREGISTRERINGSMEDARBEJDER") == null) {
            return "redirect:/";
        }
        leasingService.markAsAfleveret(leasing_ID);
        redirectAttributes.addFlashAttribute("besked", "Leasingaftale markeret som afleveret.");
        return "redirect:/leasing/read";
    }
}