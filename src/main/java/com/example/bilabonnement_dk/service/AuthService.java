package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.repository.MedarbejderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    MedarbejderRepository medarbejderRepository;

    public Medarbejder login(String brugernavn, String adgangskode) {
        try {
            return medarbejderRepository.findByBrugerOgKode(brugernavn, adgangskode);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public boolean isValidPassword(String adgangskode) {
        return adgangskode != null && adgangskode.length() >= 6;
    }
}
