package com.example.bilabonnement_dk.service;


import com.example.bilabonnement_dk.controller.SalgsaftaleController;
import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.model.Salgsaftale;
import com.example.bilabonnement_dk.repository.SalgsaftaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import java.util.Optional;

@Service
public class SalgsaftaleService {


    @Autowired
    private SalgsaftaleRepository salgsaftaleRepository;

    public Salgsaftale findById(int leasingid) {
        return salgsaftaleRepository.findSalgsaftaleByID(leasingid);
    }
    public List<Salgsaftale> findAll() {
        return salgsaftaleRepository.findAllSalgsaftale();
    }
    public void save(Salgsaftale salgsaftale) {
        salgsaftaleRepository.save(salgsaftale);

    }
    public void deleteById(int leasingid) {
        salgsaftaleRepository.deleteSalgsaftaleByID(leasingid);

    }




}
