package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.service.BilService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BilController {
    @Autowired
    BilService bilService;

    @GetMapping("home/bil")
    public String index(HttpSession Session, Model model){
        List<Bil> bil = BilService.fetchAll();
        model.addAttribute("bil", bil);
        return "home";

//    @GetMapping("home/bil")
//    public String index(Model model){
//        List<Bil> bil = bilService.fetchAll();
//        model.addAttribute("Bil", bil);
//        return "home/Bil";
//    }
//
//    @PostMapping("/create")
//    public String create(@ModelAttribute Bil bil){
//        bilService.addBil(bil);
//        return "create";
//    }
}
