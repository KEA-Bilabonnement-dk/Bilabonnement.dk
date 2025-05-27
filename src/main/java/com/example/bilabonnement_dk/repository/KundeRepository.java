package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marker som repository-komponent for Spring
public class KundeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate; // JdbcTemplate til databasekommunikation

    // Tilføjer en ny kunde til databasen
    public void addKunde(Kunde kunde) {
        String sql = "INSERT INTO kunde (kunde_ID, kfornavn, kefternavn, ktelefonnummer, kemail, adresse_ID) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                kunde.getKunde_ID(),
                kunde.getkFornavn(),
                kunde.getkEfternavn(),
                kunde.getkTelefonnummer(),
                kunde.getkEmail(),
                // Sætter adresse_ID hvis adresse eksisterer, ellers null
                kunde.getAdresse() != null ? kunde.getAdresse().getAdresse_ID() : null);
    }

    // Finder en kunde ud fra kunde_ID
    public Kunde findKundeByID(int kunde_ID) {
        String sql = "SELECT * FROM kunde WHERE kunde_ID = ?";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, kunde_ID);
    }

    // Henter alle kunder fra databasen
    public List<Kunde> fetchAll() {
        String sql = "SELECT * FROM kunde";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    // Opdaterer en eksisterende kundes oplysninger
    public void updateKunde(Kunde kunde) {
        String sql = "UPDATE kunde SET kfornavn=?, kefternavn=?, ktelefonnummer=?, kemail=?, adresse_ID=? WHERE kunde_ID = ?";
        jdbcTemplate.update(sql,
                kunde.getkFornavn(),
                kunde.getkEfternavn(),
                kunde.getkTelefonnummer(),
                kunde.getkEmail(),
                // Opdaterer adresse_ID, eller null hvis ikke sat
                kunde.getAdresse() != null ? kunde.getAdresse().getAdresse_ID() : null,
                kunde.getKunde_ID());
    }

    // Sletter en kunde baseret på kunde_ID
    public void deleteKunde(int kunde_ID) {
        String sql = "DELETE FROM kunde WHERE kunde_ID = ?";
        jdbcTemplate.update(sql, kunde_ID);
    }

    // Henter det seneste (største) kunde_ID i databasen
    public int getLatestKundeID() {
        String sql = "SELECT kunde_ID FROM kunde ORDER BY kunde_ID DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}