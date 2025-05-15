package com.example.bilabonnement_dk.repository;


import com.example.bilabonnement_dk.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeasingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addLeasing(Leasing leasing) {
        String sql = """
                INSERT INTO leasing (pris, startdato, slutdato, kunde_ID, medarbejder_ID, bil_ID, abonnementstype_ID)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(sql,
                leasing.getPris(),
                leasing.getStartdato(),
                leasing.getSlutdato(),
                leasing.getKunde().getKunde_ID(),
                leasing.getMedarbejder().getMedarbejder_ID(),
                leasing.getBil().getBil_ID(),
                leasing.getAbonnementstype().getAbonnementstype_ID()
        );
    }
}
