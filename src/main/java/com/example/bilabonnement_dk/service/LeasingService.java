package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.repository.KundeRepository;
import com.example.bilabonnement_dk.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeasingService {
    @Autowired
    private LeasingRepository leasingRepository;

    @Autowired
    private KundeRepository kundeRepository;

    public void addLeasing(Leasing leasing)
    {
        int kunde_ID = leasing.getKunde().getKunde_ID();
        Kunde kunde = kundeRepository.findKundeByID(kunde_ID);
        leasing.setKunde(kunde);
        leasingRepository.addLeasing(leasing);
    }
}
