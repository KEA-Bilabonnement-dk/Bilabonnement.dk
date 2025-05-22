package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Kunde;
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

    public Medarbejder findByBrugerOgKode(String brugernavn, String adgangskode) {
        String sql = "SELECT * FROM medarbejder WHERE brugernavn = ? AND adgangskode = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{brugernavn, adgangskode},
                new BeanPropertyRowMapper<>(Medarbejder.class)
        );
    }

    public List<Medarbejder> fetchAll() {
        String sql = "SELECT * FROM medarbejder";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Medarbejder m = new Medarbejder();
            m.setMedarbejder_ID(rs.getInt("medarbejder_ID"));
            m.setBrugernavn(rs.getString("brugernavn"));
            m.setAdgangskode(rs.getString("adgangskode"));
            return m;
        });
    }
    public Medarbejder findMedarbejderByID(int medarbejder_ID)
    {
        String sql = "SELECT * FROM medarbejder WHERE medarbejder_ID = ?";
        RowMapper<Medarbejder> rowMapper = new BeanPropertyRowMapper<>(Medarbejder.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, medarbejder_ID);
    }

}
