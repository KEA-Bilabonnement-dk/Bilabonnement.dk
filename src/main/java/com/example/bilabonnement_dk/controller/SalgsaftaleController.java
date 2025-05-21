package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.*;

import com.example.bilabonnement_dk.repository.SalgsaftaleRepository;
import com.example.bilabonnement_dk.service.SalgsaftaleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller

public class SalgsaftaleController {

    @Autowired
    private SalgsaftaleRepository salgsaftaleRepository;

    @GetMapping("/salgsaftaler")
    public String getAllSalgsaftaler(Model model, HttpSession session) {
        List<Salgsaftale> salgsaftaler = salgsaftaleRepository.findAllSalgsaftale();
        model.addAttribute("salgsaftale", salgsaftaler);
        return "salgsaftale";
    }
    @GetMapping("/salgsaftale")
    public String getSalgsaftale(@RequestParam("id") int leasingId, Model model, HttpSession session) {
        Salgsaftale salgsaftale = salgsaftaleRepository.findSalgsaftaleByID(leasingId);
        model.addAttribute("salgsaftale", salgsaftale);
        return "salgsaftale";
    }
    @GetMapping("/salgsaftale/opret")
    public String AddSalgsaftale(Model model, HttpSession session) {
        model.addAttribute("salgsaftale", new Salgsaftale());
        return "opretSalgsaftale";
    }
    @PostMapping("/salgsaftale/gem")
    public String gemsalgsaftale(@RequestParam("salgspris") double salgspris,
                                 @RequestParam("leveringsdato") Date leveringsdato,
                                 @RequestParam("adresse_ID") int adresseId,
                                 @RequestParam("medarbejder_ID") int medarbejderId,
                                 @RequestParam("bil_ID") int bilId,
                                 @RequestParam("kunde_ID") int kundeId)
    {
        Salgsaftale salgsaftale = new Salgsaftale();
        salgsaftale.setSalgspris(salgspris);
        salgsaftale.setLeveringsdato(leveringsdato);

       Adresse adresse = new Adresse();
       adresse.setAdresse_ID(adresseId);
       salgsaftale.setAdresse(adresse);

       Medarbejder medarbejder = new Medarbejder();
       medarbejder.setMedarbejder_ID(medarbejderId);
       salgsaftale.setMedarbejder(medarbejder);

        Bil bil = new Bil();
        bil.setBil_ID(bilId);
        salgsaftale.setBil(bil);

        Kunde kunde = new Kunde();
        kunde.setKunde_ID(kundeId);
        salgsaftale.setKunde(kunde);

        return "redirect:/salgsaftaler";
    }


    @PostMapping("/salgsaftale/slet")
    public String sletsalgsaftale(@RequestParam("id") int Salgs_ID, Model model, HttpSession session) {
        salgsaftaleRepository.deleteSalgsaftaleByID(Salgs_ID);
        return "redirect:/salgsaftaler";

}
}
