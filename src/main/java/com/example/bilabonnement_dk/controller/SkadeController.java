package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.model.Rapportreservedel;
import com.example.bilabonnement_dk.model.Reservedel;
import com.example.bilabonnement_dk.model.Skaderapport;
import com.example.bilabonnement_dk.repository.MedarbejderRepository;
import com.example.bilabonnement_dk.service.LeasingService;
import com.example.bilabonnement_dk.service.ReservedelService;
import com.example.bilabonnement_dk.service.SkadeService;
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
public class SkadeController {

    @Autowired
    private SkadeService skadeService;

    @Autowired
    private LeasingService leasingService;

    @Autowired
    private MedarbejderRepository medarbejderRepository;

    @Autowired
    private ReservedelService reservedelService;

    // Adgangstjek-metode + return af medarbejder
    private Medarbejder hentMedarbejderHvisAdgang(HttpSession session, String ønsketRolle) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");
        if (medarbejder != null && medarbejder.getRolle().name().equals(ønsketRolle)) {
            return medarbejder;
        }
        return null;
    }

    @GetMapping("/skade/create")
    public String visSkadeOpret(HttpSession session, Model model) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null) {
            return "redirect:/";
        }
        model.addAttribute("skaderapport", new Skaderapport());
        model.addAttribute("leasingliste", leasingService.fetchAll());
        model.addAttribute("reservedelliste", reservedelService.fetchAll());

        return "skade/create";
    }

    @PostMapping("/skade/create")
    public String opretSkade(@ModelAttribute Skaderapport skaderapport,
                             @RequestParam(name = "reservedel_ID", required = false) List<Integer> reservedelIDs,
                             @RequestParam(name = "antal", required = false) List<Integer> antalListe,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        Medarbejder medarbejder = hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER");
        if (medarbejder == null)
            return "redirect:/";

        skaderapport.setMedarbejder(medarbejder);
        int rapport_ID = skadeService.addSkade(skaderapport);
        double samletPris = 0;

        if (reservedelIDs != null && antalListe != null && !reservedelIDs.isEmpty() && !antalListe.isEmpty()) {
            for (int i = 0; i < reservedelIDs.size(); i++) {
                int reservedelID = reservedelIDs.get(i);
                int antal = antalListe.get(i);

                Reservedel reservedel = reservedelService.findByID(reservedelID);
                double delpris = reservedel.getPris() * antal;
                samletPris += delpris;

                Rapportreservedel rr = new Rapportreservedel();
                rr.setSkaderapport(new Skaderapport());
                rr.getSkaderapport().setSkaderapport_ID(rapport_ID);
                rr.setReservedel(reservedel);
                rr.setAntal(antal);

                skadeService.addRapportreservedel(rr);
            }
        }

        samletPris += skaderapport.getArbejdstid() * 500;
        skadeService.updatePrice(rapport_ID, samletPris);

        redirectAttributes.addFlashAttribute("besked", "Skaderapport oprettet!");
        return "redirect:/skade/create";
    }

    @GetMapping("/skade/read")
    public String visAlleSkader(Model model, HttpSession session) {
        if (hentMedarbejderHvisAdgang(session, "SKADEBEHANDLER") == null) {
            return "redirect:/";
        }

        List<Skaderapport> skaderapporter = skadeService.hentAlleSkaderapporterMedReservedele();
        model.addAttribute("skaderapporter", skaderapporter);
        return "skade/read";
    }
}
