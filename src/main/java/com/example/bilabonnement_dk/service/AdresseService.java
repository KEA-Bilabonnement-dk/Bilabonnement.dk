    package com.example.bilabonnement_dk.service;

    import com.example.bilabonnement_dk.model.Adresse;
    import com.example.bilabonnement_dk.model.Bil;
    import com.example.bilabonnement_dk.repository.AdresseRepository;
    import com.example.bilabonnement_dk.repository.BilRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    public class AdresseService {
        @Autowired
        AdresseRepository adresseRepository;

        public void addAdresse(Adresse adresse) {
            adresseRepository.addAdresse(adresse);
        }
    }