package com.example.bilabonnement_dk.Converter;

import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.service.MedarbejderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MedarbejderConverter implements Converter<String, Medarbejder> {

    @Autowired
    private MedarbejderService medarbejderService;

    @Override
    public Medarbejder convert(String source) {
        if (source == null || source.isEmpty()) return null;
        return medarbejderService.findMedarbejderByID(Integer.parseInt(source));
    }
}