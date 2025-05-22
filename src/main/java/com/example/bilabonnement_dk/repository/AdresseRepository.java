package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Adresse;
import com.example.bilabonnement_dk.model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addAdresse(Adresse adresse) {
        String sql = "INSERT INTO adresse (vejnr, vejnavn, land, postnr) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,adresse.getVejnr(),
                adresse.getVejnavn(),adresse.getLand(),adresse.getPostnr());
    }
}
