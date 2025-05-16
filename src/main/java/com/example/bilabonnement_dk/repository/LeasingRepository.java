package com.example.bilabonnement_dk.repository;


import com.example.bilabonnement_dk.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LeasingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addLeasing(Leasing leasing) {
        String sql = """
        INSERT INTO leasing (kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris, medarbejder_ID)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                leasing.getKunde().getKunde_ID(),
                leasing.getBil().getBil_ID(),
                leasing.getAbonnementstype().name(),
                leasing.getStartdato(),
                leasing.getSlutdato(),
                leasing.getPris(),
                leasing.getMedarbejder().getMedarbejder_ID()
        );
    }
}
