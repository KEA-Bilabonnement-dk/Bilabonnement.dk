package com.example.bilabonnement_dk.controller;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BilController {
    @Autowired
    BilService BilService;

    @GetMapping("/")
    public String index(Model model){
        List<Bil> bil = BilService.fetchAll();
        model.addAttribute("bil", bil);
        return "home/index";
    }


}
