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

    public List<Bil> fetchAll(){
        return bilRepository.fetchAll();
    }

    public void addBil(Bil a){
        bilRepository.addBil(a);
    }
/*
    public Bil findBilById(int id){
        return bilRepository.findBilById(id);
    }

    public Boolean deleteBil(int id){
        return bilRepository.deleteBil(id);
    }

    public void updateBil(Bil a){
        bilRepository.updateBil(a);
    }
    */
}