package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.repository.LeasingRepository;
import com.example.bilabonnement_dk.repository.MedarbejderRepository;
import com.example.bilabonnement_dk.repository.SkadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkadeService {

    @Autowired
    private SkadeRepository skadeRepository;

    @Autowired
    private LeasingRepository leasingRepository;

    @Autowired
    private MedarbejderRepository medarbejderRepository;
}
