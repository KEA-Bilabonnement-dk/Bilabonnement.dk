    package com.example.bilabonnement_dk.service;

    import com.example.bilabonnement_dk.model.Adresse;
    import com.example.bilabonnement_dk.repository.AdresseRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;

    @Service
    public class AdresseService {
        @Autowired
        AdresseRepository adresseRepository;

        public void addAdresse(Adresse adresse) {
            adresseRepository.addAdresse(adresse);
        }

        public int getLatestAdresseID(){
            return adresseRepository.getLatestAdresseID();
        }

        public Adresse findAdresseByID(int adresse_ID){
            return adresseRepository.findAdresseByID(adresse_ID);
        }
    }
