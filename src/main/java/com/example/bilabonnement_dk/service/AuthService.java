package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.repository.MedarbejderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    MedarbejderRepository medarbejderRepository;
}
