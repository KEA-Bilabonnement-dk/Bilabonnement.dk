package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.*;
import com.example.bilabonnement_dk.service.LeasingService;
import com.example.bilabonnement_dk.service.ReservedelService;
import com.example.bilabonnement_dk.service.SkadeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SkadeController {

    @Autowired
    private SkadeService skadeService;

    @Autowired
    private LeasingService leasingService;

    @Autowired
    private ReservedelService reservedelService;

    // Tjekker om medarbejder har den rette rolle
    private Medarbejder hentMedarbejderHvisAdgang(HttpSession session, String ønsketRolle) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");
        if (medarbejder != null && medarbejder.getRolle().name().equals(ønsketRolle)) {
            return medarbejder;
        }
        return null;
    }

    // Viser formular til oprettelse af skaderapport
    @GetMapping("/skade/create")
    public String visSkadeOpret(HttpSession session, Model model) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null) return "redirect:/";
        model.addAttribute("skaderapport", new Skaderapport());
        model.addAttribute("reservedelliste", reservedelService.fetchAll());
        model.addAttribute("leasingliste", leasingService.findReturnedLeasing());
        return "skade/create";
    }

    // Opretter ny skaderapport med tilhørende reservedelsdata
    @PostMapping("/skade/create")
    public String opretSkade(@ModelAttribute Skaderapport skaderapport,
                             @RequestParam(name = "reservedel_ID", required = false) List<Integer> reservedelIDs,
                             @RequestParam(name = "antal", required = false) List<Integer> antalListe,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        Medarbejder medarbejder = hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER");
        if (medarbejder == null) return "redirect:/";

        Leasing valgtLeasing = leasingService.findLeasingByID(skaderapport.getLeasing().getLeasing_ID());
        // Tjekker om leasing er afsluttet og afleveret
        if (valgtLeasing.getSlutdato().isAfter(LocalDate.now()) || !valgtLeasing.isAfleveret()) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Du kan kun oprette skader på biler med afsluttet og afleveret leasingperiode.");
            return "redirect:/skade/create";
        }

        skaderapport.setMedarbejder(medarbejder);
        int skaderapport_ID = skadeService.addSkade(skaderapport);
        skaderapport.setSkaderapport_ID(skaderapport_ID);

        double samletPris = 0;
        Map<Integer, Rapportreservedel> reservedelsMap = new HashMap<>();

        // Håndterer reservedel og antal, summerer hvis flere af samme reservedel
        if (reservedelIDs != null && antalListe != null && !reservedelIDs.isEmpty() && !antalListe.isEmpty()) {
            for (int i = 0; i < reservedelIDs.size(); i++) {
                int reservedelID = reservedelIDs.get(i);
                int antal = antalListe.get(i);
                Reservedel reservedel = reservedelService.findByID(reservedelID);

                if (reservedelsMap.containsKey(reservedelID)) {
                    Rapportreservedel eksisterende = reservedelsMap.get(reservedelID);
                    eksisterende.setAntal(eksisterende.getAntal() + antal);
                } else {
                    Rapportreservedel rr = new Rapportreservedel();
                    rr.setSkaderapport(skaderapport);
                    rr.setReservedel(reservedel);
                    rr.setAntal(antal);
                    reservedelsMap.put(reservedelID, rr);
                }
            }

            // Gemmer reservedel data og beregner pris
            for (Rapportreservedel rr : reservedelsMap.values()) {
                System.out.println("Indsætter reservedel med skaderapport_ID: " + rr.getSkaderapport().getSkaderapport_ID());
                skadeService.addRapportreservedel(rr);
                samletPris += rr.getReservedel().getPris() * rr.getAntal();
            }
        }

        // Lægger arbejdstid til samlet pris (500 kr/time)
        samletPris += skaderapport.getArbejdstid() * 500;
        skadeService.updatePrice(skaderapport_ID, samletPris);

        redirectAttributes.addFlashAttribute("besked", "Skaderapport oprettet!");
        return "redirect:/skade/create";
    }

    // Viser alle skaderapporter
    @GetMapping("/skade/read")
    public String visAlleSkader(Model model, HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null) return "redirect:/";
        List<Skaderapport> skaderapporter = skadeService.fetchAll();
        model.addAttribute("skaderapporter", skaderapporter);
        return "skade/read";
    }

    // Viser en enkelt skaderapport detaljer
    @GetMapping("/skade/readOne")
    public String visSkadeRapport(@RequestParam("skaderapport_ID") int skaderapport_ID,
                                  Model model, HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null) return "redirect:/";
        Skaderapport skaderapport = skadeService.findBySkaderapportID(skaderapport_ID);
        if (skaderapport == null) {
            model.addAttribute("fejlbesked", "Ingen skaderapport fundet med det angivne ID: " + skaderapport_ID);
            return "skade/readOne";
        }
        model.addAttribute("skaderapport", skaderapport);
        return "skade/readOne";
    }

    // Viser opdateringsformular
    @GetMapping("/skade/update")
    public String visOpdateringFormular(HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null) return "redirect:/";
        return "skade/update";
    }

    // Henter data til opdatering af en specifik skaderapport
    @GetMapping("/skade/updateOne")
    public String opdaterEnSkade(@RequestParam("skaderapport_ID") int skaderapport_ID,
                                 Model model,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null)
            return "redirect:/";

        Skaderapport skaderapport = skadeService.findBySkaderapportID(skaderapport_ID);
        if (skaderapport == null) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen skaderapport fundet med det angivne ID: " + skaderapport_ID);
            return "redirect:/skade/update";
        }
        model.addAttribute("skaderapport", skaderapport);
        model.addAttribute("reservedelliste", reservedelService.fetchAll());
        return "skade/updateOne";
    }

    // Opdaterer skaderapport og reservedelsdata
    @PostMapping("/skade/update")
    public String opdaterSkade(@RequestParam("skaderapport_ID") int skaderapport_ID,
                               @RequestParam("arbejdstid") int arbejdstid,
                               @RequestParam(name = "reservedel_ID", required = false) List<Integer> reservedelIDs,
                               @RequestParam(name = "antal", required = false) List<Integer> antalListe,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        Medarbejder medarbejder = hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER");
        if (medarbejder == null) return "redirect:/";

        Skaderapport skaderapport = skadeService.findBySkaderapportID(skaderapport_ID);
        if (skaderapport == null) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen skaderappor fundet med det angivne ID:" + skaderapport_ID);
            return "redirect:/skade/update";
        }
        skaderapport.setSkaderapport_ID(skaderapport_ID);
        skaderapport.setArbejdstid(arbejdstid);

        // Sletter gamle reservedelsdata tilknyttet rapporten
        skadeService.deleteOnlyRapportreservedel(skaderapport_ID);

        double samletPris = 0;
        Map<Integer, Rapportreservedel> reservedelsMap = new HashMap<>();

        // Behandler ny reservedelsliste og antal, summerer duplikater
        if (reservedelIDs != null && antalListe != null && !reservedelIDs.isEmpty() && !antalListe.isEmpty()) {
            for (int i = 0; i < reservedelIDs.size(); i++) {
                int reservedelID = reservedelIDs.get(i);
                int antal = antalListe.get(i);
                Reservedel reservedel = reservedelService.findByID(reservedelID);

                if (reservedelsMap.containsKey(reservedelID)) {
                    Rapportreservedel eksisterende = reservedelsMap.get(reservedelID);
                    eksisterende.setAntal(eksisterende.getAntal() + antal);
                } else {
                    Rapportreservedel rr = new Rapportreservedel();
                    rr.setSkaderapport(skaderapport);
                    rr.setReservedel(reservedel);
                    rr.setAntal(antal);
                    reservedelsMap.put(reservedelID, rr);
                }
            }

            // Gemmer reservedelsdata og beregner pris
            for (Rapportreservedel rr : reservedelsMap.values()) {
                System.out.println("Indsætter reservedel med skaderapport_ID: " + rr.getSkaderapport().getSkaderapport_ID());
                skadeService.addRapportreservedel(rr);
                samletPris += rr.getReservedel().getPris() * rr.getAntal();
            }
        }

        // Lægger arbejdstid til samlet pris
        samletPris += arbejdstid * 500;
        skadeService.updateSkaderapport(skaderapport_ID, arbejdstid, samletPris);

        redirectAttributes.addFlashAttribute("besked", "Skaderapport opdateret!");
        return "redirect:/skade/read";
    }

    // Viser slet-side med liste af skaderapporter
    @GetMapping("/skade/delete")
    public String visSletSkadeSide(Model model, HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null)
            return "redirect:/";
        model.addAttribute("skaderapporter", skadeService.fetchAll());
        return "skade/delete";
    }

    // Sletter skaderapport ud fra ID
    @PostMapping("/skade/delete")
    public String sletSkaderapport(@RequestParam("skaderapport_ID") int skaderapport_ID,
                                   RedirectAttributes redirectAttributes,
                                   HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null)
            return "redirect:/";

        Skaderapport skaderapport = skadeService.findBySkaderapportID(skaderapport_ID);
        if (skaderapport == null) {
            redirectAttributes.addFlashAttribute("fejlbesked", "Ingen skaderappor fundet med det angivne ID:" + skaderapport_ID);
            return "redirect:/skade/delete";
        }

        skadeService.deleteSkaderapport(skaderapport_ID);
        redirectAttributes.addFlashAttribute("besked", "Skaderapport med ID " + skaderapport_ID + " blev slettet.");
        return "redirect:/skade/delete";
    }
}