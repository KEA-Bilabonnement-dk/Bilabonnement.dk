package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.repository.KundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KundeService {
    @Autowired
    KundeRepository kundeRepository;

    public void addKunde(Kunde kunde)
    {
        kundeRepository.addKunde(kunde);
    }

    public Kunde findKundeByID(int kunde_ID)
    {
        return kundeRepository.findKundeByID(kunde_ID);
    }

    public List<Kunde> fetchAll()
    {
        return kundeRepository.fetchAll();
    }

    public void updateKunde(Kunde kunde)
    {
        kundeRepository.updateKunde(kunde);
    }

    public void deleteKunde(int kunde_ID)
    {
        kundeRepository.deleteKunde(kunde_ID);
    }

    public int getLatestKundeID(){
        return kundeRepository.getLatestKundeID();
    }

}
