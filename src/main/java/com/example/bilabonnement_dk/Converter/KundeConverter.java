package com.example.bilabonnement_dk.Converter;

import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.service.KundeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class KundeConverter implements Converter<String, Kunde> {

    @Autowired
    private KundeService kundeService;

    @Override
    public Kunde convert(String source) {
        if (source == null || source.isEmpty()) return null;
        return kundeService.findKundeByID(Integer.parseInt(source));
    }
}