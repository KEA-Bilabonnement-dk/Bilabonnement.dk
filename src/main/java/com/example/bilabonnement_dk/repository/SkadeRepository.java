package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Rapportreservedel;
import com.example.bilabonnement_dk.model.Skaderapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository
public class SkadeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertAndReturnID(Skaderapport skaderapport) {
        String sql = """
                INSERT INTO skaderapport (pris, arbejdstid, leasing_ID, medarbejder_ID)
                VALUES (?, ?, ?, ?)
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, skaderapport.getPris());
            ps.setInt(2, skaderapport.getArbejdstid());
            ps.setInt(3, skaderapport.getLeasing().getLeasing_ID());
            ps.setInt(4, skaderapport.getMedarbejder().getMedarbejder_ID());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public void opdaterPris(int skadeRapport_ID, double nyPris)
    {
        String sql = "UPDATE skaderapport SET pris = ? WHERE skaderapport_ID = ?";
        jdbcTemplate.update(sql, nyPris, skadeRapport_ID);
    }
}
