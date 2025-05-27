package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    AuthService authService;

    // Viser login-side
    @GetMapping("/")
    public String visLoginSide() {
        return "home/login";
    }

    // Håndterer loginforsøg
    @PostMapping("/login")
    public String login(@RequestParam String brugernavn,
                        @RequestParam String adgangskode,
                        HttpSession session,
                        Model model) {

        Medarbejder medarbejder = authService.login(brugernavn, adgangskode);

        // Hvis login fejler, vis fejl på login-side
        if (medarbejder == null) {
            model.addAttribute("fejl", "Forkert brugernavn eller adgangskode.");
            return "home/login";
        }

        // Gem medarbejder i session
        session.setAttribute("bruger", medarbejder);

        // Redirect baseret på medarbejders rolle
        switch (medarbejder.getRolle()) {
            case DATAREGISTRERINGSMEDARBEJDER:
                return "redirect:/data";
            case SKADEBEHANDLER:
                return "redirect:/skade";
            case FORRETNINGSUDVIKLER:
                return "redirect:/forretning";
            default:
                return "redirect:/dashboard";
        }
    }

    // Logger ud ved at invalidere sessionen
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}