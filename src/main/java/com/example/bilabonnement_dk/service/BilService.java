package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.repository.BilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BilService {
    @Autowired
    BilRepository bilRepository;

    public void addBil(Bil bil){
        bilRepository.addBil(bil);
    }

    public Bil findBilById(int bil_ID){
        return bilRepository.findBilById(bil_ID);
    }

    public List<Bil> fetchAll(){
        return bilRepository.fetchAll();
    }

    public void updateBil(Bil bil){
        bilRepository.updateBil(bil);
    }

    public void deleteBil(int bil_ID)
    {
        bilRepository.deleteBil(bil_ID);
    }
}