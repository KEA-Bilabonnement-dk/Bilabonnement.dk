package com.example.bilabonnement_dk.service;


import com.example.bilabonnement_dk.controller.ForretningsudviklerController;
import com.example.bilabonnement_dk.repository.ForretningsudviklerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ForretningsudviklerService {
    private ForretningsudviklerRepository forretningsudviklerRepository;

    public ForretningsudviklerService(ForretningsudviklerRepository repository) {
        this.forretningsudviklerRepository = repository;
    }
    public int hentAlleLeasninger(){
        return forretningsudviklerRepository.fetchAllLeasninger();
    }
    public int hentAntalAktiveBiler() {
        return forretningsudviklerRepository.findSamletAntalAktivtUdlejede();
    }
    public BigDecimal hentSamletPrisUdlejdeBiler() {
        BigDecimal pris = forretningsudviklerRepository.findSamletPrisUdlejedeBiler();
        return pris != null ? pris : BigDecimal.ZERO;

    }
    public BigDecimal hentSamletPrisAktiveUdlejdeBiler() {
        BigDecimal pris = forretningsudviklerRepository.findSamletPrisAktiveUdlejedeBiler();
        return pris != null ? pris : BigDecimal.ZERO;

    }
}
