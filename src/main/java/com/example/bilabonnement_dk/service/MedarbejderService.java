package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.repository.KundeRepository;
import com.example.bilabonnement_dk.repository.MedarbejderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedarbejderService {
    @Autowired
    MedarbejderRepository medarbejderRepository;

    public void addMedarbejder(Medarbejder medarbejder)
    {
        medarbejderRepository.addMedarbejder(medarbejder);
    }

    public Medarbejder findMedarbejderByID(int medarbejder_ID)
    {
        return medarbejderRepository.findMedarbejderByID(medarbejder_ID);
    }

    public List<Medarbejder> fetchAll()
    {
        return medarbejderRepository.fetchAll();
    }

    public void updateMedarbejder(Medarbejder medarbejder)
    {
        medarbejderRepository.updateMedarbejder(medarbejder);
    }

    public void deleteMedarbejder(int medarbejder_ID)
    {
        medarbejderRepository.deleteMedarbejder(medarbejder_ID);
    }

}
