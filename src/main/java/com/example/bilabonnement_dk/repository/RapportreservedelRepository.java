package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Rapportreservedel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RapportreservedelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertRapportreservedel(Rapportreservedel rapportreservedel) {
        String sql = """
                INSERT INTO rapportreservedel (reservedel_ID, skaderapport_ID, antal)
                VALUES (?, ?, ?)
                """;
        jdbcTemplate.update(sql,
                rapportreservedel.getReservedel().getReservedel_ID(),
                rapportreservedel.getSkaderapport().getSkaderapport_ID(),
                rapportreservedel.getAntal()
        );
    }
}
