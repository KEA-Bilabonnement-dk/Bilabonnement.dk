package com.example.bilabonnement_dk.service;


import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.repository.LeasingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

@Service
public class LeasingService {
    @Autowired
    LeasingRepository LeasingRepository;

    public List<Leasing> fetchAll() {return LeasingRepository.fetchAll();}

    public Leasing getLeasingByID(int ID) {return LeasingRepository.getLeasingByID(ID);}

    public void addLeasing(Leasing l) {LeasingRepository.addLeasing(l);}




}
