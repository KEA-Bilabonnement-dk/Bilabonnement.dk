package com.example.bilabonnement_dk.service;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ForretningsudviklerService {
    private JdbcTemplate jdbcTemplate;
    public ForretningsudviklerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public int hentAntalUdledeBiler() {
        String sql = "SELECT COUNT(*) FROM leasing";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    public BigDecimal hentSamletPrisUdlejedeBiler() {
        String sql = "SELECT SUM(pris) FROM leasing";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);

    }

}
