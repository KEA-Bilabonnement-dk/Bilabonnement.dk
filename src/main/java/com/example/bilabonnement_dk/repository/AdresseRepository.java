package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Adresse;
import com.example.bilabonnement_dk.model.Bil;
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
        String sql = "INSERT INTO adresse (vejnavn, vejnr, land, postnummer, bynavn) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                adresse.getVejnavn(),
                adresse.getVejnr(),
                adresse.getLand(),
                adresse.getPostnummer(),
                adresse.getBynavn());
    }

    public Adresse findAdresseByID(int adresse_ID){
        String sql = "SELECT * FROM adresse WHERzE adresse_ID = ?";
        RowMapper<Adresse> rowMapper = new BeanPropertyRowMapper<>(Adresse.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, adresse_ID);
    }

    public int getLatestAdresseID()
    {
        String sql = "SELECT adresse_ID FROM adresse ORDER BY adresse_ID DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
