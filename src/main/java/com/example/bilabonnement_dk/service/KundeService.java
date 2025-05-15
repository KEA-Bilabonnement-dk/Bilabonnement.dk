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


    public List<Kunde> fetchAll() {return kundeRepository.fetchAll();}

    public Kunde getKundeById(int id) {return kundeRepository.getKundeById(id);}

    public boolean deleteKunde(int id) {return kundeRepository.deleteKunde(id);}

    public void updateKunde(Kunde k) {kundeRepository.updateKunde(k);}

    public void addKunde(Kunde k) {kundeRepository.addKunde(k);}



}
