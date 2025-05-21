package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Reservedel;
import com.example.bilabonnement_dk.repository.ReservedelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservedelService {

    @Autowired
    private ReservedelRepository reservedelRepository;

    public List<Reservedel> fetchAll() {
        return reservedelRepository.fetchAll();
    }

    public Reservedel findByID(int reservedel_ID) {
        return reservedelRepository.findByID(reservedel_ID);
    }
}
