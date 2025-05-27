package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Medarbejder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MedarbejderRepository
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Finder en medarbejder ud fra brugernavn og adgangskode
    public Medarbejder findByBrugerOgKode(String brugernavn, String adgangskode) {
        String sql = "SELECT * FROM medarbejder WHERE brugernavn = ? AND adgangskode = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{brugernavn, adgangskode},
                new BeanPropertyRowMapper<>(Medarbejder.class)
        );
    }

    // Tilføjer en ny medarbejder i databasen
    public void addMedarbejder(Medarbejder medarbejder) {
        String sql = "INSERT INTO medarbejder (medarbejder_ID, fornavn, efternavn, telefonnummer, mail, brugernavn, adgangskode, rolle, adresse_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,medarbejder.getMedarbejder_ID(),medarbejder.getFornavn(),
                medarbejder.getEfternavn(),medarbejder.getTelefonnummer(),
                medarbejder.getEmail(),medarbejder.getBrugernavn(),medarbejder.getAdgangskode(),medarbejder.getRolle(),medarbejder.getAdresse().getAdresse_ID());
    }

    // Henter alle medarbejdere
    public List<Medarbejder> fetchAll() {
        String sql = "SELECT * FROM medarbejder";
        RowMapper<Medarbejder> rowMapper = new BeanPropertyRowMapper<>(Medarbejder.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Opdaterer en eksisterende medarbejder
    public void updateMedarbejder(Medarbejder medarbejder) {
        String sql = "UPDATE Medarbejder SET fornavn = ?, efternavn = ?, telefonnummer = ?, mail = ?, brugernavn = ?, adgangskode = ?, rolle = ?, adresse_ID = ? WHERE medarbejder_ID = ?";
        jdbcTemplate.update(sql,
                medarbejder.getFornavn(),
                medarbejder.getEfternavn(),
                medarbejder.getTelefonnummer(),
                medarbejder.getEmail(),
                medarbejder.getBrugernavn(),
                medarbejder.getAdgangskode(),
                medarbejder.getRolle(),
                medarbejder.getRolle() != null ? medarbejder.getRolle().toString() : null,
                medarbejder.getAdresse() != null ? medarbejder.getAdresse().getAdresse_ID() : null);
    }

    // Finder medarbejder på ID
    public Medarbejder findMedarbejderByID(int medarbejder_ID)
    {
        String sql = "SELECT * FROM medarbejder WHERE medarbejder_ID = ?";
        RowMapper<Medarbejder> rowMapper = new BeanPropertyRowMapper<>(Medarbejder.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, medarbejder_ID);
    }

    // Sletter medarbejder ud fra ID
    public void deleteMedarbejder(int medarbejder_ID)
    {
        String sql = "DELETE FROM medarbejder WHERE medarbejder_ID = ?";
        jdbcTemplate.update(sql,medarbejder_ID);
    }
}