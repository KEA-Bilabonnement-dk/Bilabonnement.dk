package com.example.bilabonnement_dk.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class ForretningsudviklerRepository {
    private JdbcTemplate jdbcTemplate;

    public ForretningsudviklerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int findAntalUdlejedeBiler() {
        String sql = "SELECT COUNT(*) FROM leasing";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public BigDecimal findSamletPrisUdlejedeBiler() {
        String sql = "SELECT SUM(pris) FROM leasing";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }
}


