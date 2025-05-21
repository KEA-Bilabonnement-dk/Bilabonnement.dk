package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.SkadeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SkadeController {

    @Autowired
    private SkadeService skadeService;

    private Medarbejder hentMedarbejderHvisAdgang(HttpSession session, String ønsketRolle) {
        Medarbejder medarbejder = (Medarbejder) session.getAttribute("bruger");
        if (medarbejder != null && medarbejder.getRolle().name().equals(ønsketRolle)) {
            return medarbejder;
        }
        return null;
    }
}
