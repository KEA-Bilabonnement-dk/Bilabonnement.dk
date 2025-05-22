package com.example.bilabonnement_dk.Converter;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.service.BilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BilConverter implements Converter<String, Bil> {

    @Autowired
    private BilService bilService;

    @Override
    public Bil convert(String source) {
        if (source == null || source.isEmpty()) return null;
        return bilService.findBilByID(Integer.parseInt(source));
    }
}