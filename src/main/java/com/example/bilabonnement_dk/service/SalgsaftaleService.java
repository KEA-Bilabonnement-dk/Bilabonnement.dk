package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Salgsaftale;
import com.example.bilabonnement_dk.repository.SalgsaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalgsaftaleService {

    private final SalgsaftaleRepository salgsaftaleRepository;

    @Autowired
    public SalgsaftaleService(SalgsaftaleRepository salgsaftaleRepository) {
        this.salgsaftaleRepository = salgsaftaleRepository;
    }

    public void addSalgsaftale(Salgsaftale salgsaftale) {
        salgsaftaleRepository.addSalgsaftale(salgsaftale);
    }

    public List<Salgsaftale> fetchAll() {
        return salgsaftaleRepository.fetchAll();
    }

    public Salgsaftale findSalgsaftaleByID(int salgs_ID) {
        return salgsaftaleRepository.findSalgsaftaleByID(salgs_ID);
    }

    public void updateSalgsaftale(Salgsaftale salgsaftale) {
        salgsaftaleRepository.updateSalgsaftale(salgsaftale);
    }

    public void deleteSalgsaftale(int salgs_ID) {
        salgsaftaleRepository.deleteSalgsaftaleByID(salgs_ID);
    }
}