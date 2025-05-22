package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addKunde(Kunde kunde) {
        String sql = "INSERT INTO kunde (kunde_ID, kfornavn, kefternavn, ktelefonnummer, kmail, adresse_ID) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,kunde.getKunde_ID(),kunde.getkFornavn(),
                kunde.getkEfternavn(),kunde.getkTelefonnummer(),
                kunde.getkEmail(),kunde.getAdresse());
    }

    public Kunde findKundeByID(int kunde_ID)
    {
        String sql = "SELECT * FROM kunde WHERE kunde_ID = ?";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, kunde_ID);
    }

    public List<Kunde> fetchAll()
    {
        String sql = "SELECT * FROM kunde";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void updateKunde(Kunde kunde)
    {
        String sql = "UPDATE Kunde SET Kunde_ID, kfornavn, kefternavn, ktelefonnummer, kmail, adresse_ID=? WHERE kunde_ID = ?";
        jdbcTemplate.update(sql, kunde.getKunde_ID(),kunde.getkFornavn(),
                kunde.getkEfternavn(),kunde.getkTelefonnummer(),
                kunde.getkEmail(),kunde.getAdresse());
    }

    public void deleteKunde(int kunde_ID)
    {
        String sql = "DELETE FROM kunde WHERE kunde_ID = ?";
        jdbcTemplate.update(sql,kunde_ID);
    }




}
