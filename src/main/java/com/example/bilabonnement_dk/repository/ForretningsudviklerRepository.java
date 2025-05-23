package com.example.bilabonnement_dk.repository;


import com.example.bilabonnement_dk.model.Bil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class ForretningsudviklerRepository {
    private JdbcTemplate jdbcTemplate;

    public ForretningsudviklerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int fetchAllLeasninger() {
        String sql = "SELECT COUNT(*) FROM leasing";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int findSamletAntalAktivtUdlejede() {
        String sql = "SELECT COUNT(*) FROM leasing WHERE slutdato > CURRENT_DATE";
        return jdbcTemplate.queryForObject(sql, Integer.class);

    }
    public BigDecimal findSamletPrisUdlejedeBiler() {
        String sql = "SELECT SUM(pris) FROM leasing ";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }
    public BigDecimal findSamletPrisAktiveUdlejedeBiler() {
        String sql = "SELECT SUM(pris) FROM leasing WHERE slutdato > CURRENT_DATE";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }
    public List<Bil> findUdlejedeBiler() {
        String sql = "SELECT b.* FROM bil b JOIN leasing l ON b.bil_ID = l.bil_ID WHERE l.slutdato > CURRENT_DATE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bil.class));
    }
    public List<Bil> findBilerPaaLager() {
        String sql = "SELECT * FROM bil WHERE bil_ID NOT IN (SELECT bil_ID From leasing WHERE slutdato > CURRENT_DATE)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bil.class));
    }
    public double hentUdlejningsgrad() {
        String sql = """
                SELECT 
                    COUNT(DISTINCT l.bil_ID) * 100.0 / 
                    (SELECT COUNT(*) FROM bil) AS udlejningsgrad
                FROM leasing l
                WHERE CURDATE() BETWEEN l.startdato AND l.slutdato;
                """;

        Double result = jdbcTemplate.queryForObject(sql, Double.class);
        return result != null ? result : 0.0;
    }
}