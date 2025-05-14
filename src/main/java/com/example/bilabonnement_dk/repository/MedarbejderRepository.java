package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Medarbejder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MedarbejderRepository
{
    @Autowired
    private JdbcTemplate template;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Medarbejder findByBrugerOgKode(String brugernavn, String adgangskode) {
        String sql = "SELECT * FROM medarbejder WHERE brugernavn = ? AND adgangskode = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{brugernavn, adgangskode},
                new BeanPropertyRowMapper<>(Medarbejder.class)
        );
    }
}
