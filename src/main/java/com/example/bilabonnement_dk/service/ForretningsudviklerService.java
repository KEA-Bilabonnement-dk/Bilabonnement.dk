package com.example.bilabonnement_dk.service;


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
    public int hentAntalUdlejdeBiler() {
        return forretningsudviklerRepository.findAntalUdlejedeBiler();
    }
    public BigDecimal hentSamletPrisUdlejdeBiler() {
        BigDecimal pris = forretningsudviklerRepository.findSamletPrisUdlejedeBiler();
        return pris != null ? pris : BigDecimal.ZERO;

    }
}
