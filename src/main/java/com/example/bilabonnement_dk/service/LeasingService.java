package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.repository.BilRepository;
import com.example.bilabonnement_dk.repository.KundeRepository;
import com.example.bilabonnement_dk.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeasingService {

    @Autowired
    private LeasingRepository leasingRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private BilRepository bilRepository;

    public void addLeasing(Leasing leasing) {
        int kunde_ID = leasing.getKunde().getKunde_ID();
        Kunde kunde = kundeRepository.findKundeByID(kunde_ID);
        leasing.setKunde(kunde);
        leasingRepository.addLeasing(leasing);
    }

    public List<Leasing> fetchAll()
    {
        return leasingRepository.fetchAll();
    }

    public Leasing findLeasingByID(int leasing_ID)
    {
        return leasingRepository.findLeasingByID(leasing_ID);
    }

    public void updateLeasing(Leasing leasing) {
        int kunde_ID = leasing.getKunde().getKunde_ID();
        leasing.setKunde(kundeRepository.findKundeByID(kunde_ID));

        int bil_ID = leasing.getBil().getBil_ID();
        leasing.setBil(bilRepository.findBilByID(bil_ID));

        leasingRepository.updateLeasing(leasing);
    }

    public void deleteLeasing(int leasing_ID)
    {
        leasingRepository.deleteLeasingByID(leasing_ID);
    }

    public List<Leasing> findEndedLeasing() {
        return leasingRepository.findEndedLeasing();
    }

    public void markAsAfleveret(int leasing_id) {
        leasingRepository.markAsAfleveret(leasing_id);
    }
}
