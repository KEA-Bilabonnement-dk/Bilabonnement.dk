package com.example.bilabonnement_dk.service;

import com.example.bilabonnement_dk.model.Rapportreservedel;
import com.example.bilabonnement_dk.model.Skaderapport;
import com.example.bilabonnement_dk.repository.RapportreservedelRepository;
import com.example.bilabonnement_dk.repository.SkadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkadeService {

    @Autowired
    private SkadeRepository skadeRepository;

    @Autowired
    private RapportreservedelRepository rapportreservedelRepository;

    public int addSkade(Skaderapport skaderapport) {
        return skadeRepository.insertAndReturnID(skaderapport);
    }

    public void addRapportreservedel(Rapportreservedel rapportreservedel) {
        rapportreservedelRepository.insertRapportreservedel(rapportreservedel);
    }

    public void updatePrice(int skaderapport_ID, double nyPris) {
        skadeRepository.updatePrice(skaderapport_ID, nyPris);
    }

    public List<Skaderapport> hentAlleSkaderapporterMedReservedele() {
        List<Skaderapport> skaderapporter = skadeRepository.fetchAll();

        for (Skaderapport skaderapport : skaderapporter) {
            List<Rapportreservedel> rapportreservedele = rapportreservedelRepository.findBySkaderapportID(skaderapport.getSkaderapport_ID());
            skaderapport.setReservedele(rapportreservedele);
        }
        return skaderapporter;
    }
}
