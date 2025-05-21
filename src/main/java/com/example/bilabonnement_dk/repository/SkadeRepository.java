package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Leasing;
import com.example.bilabonnement_dk.model.Medarbejder;
import com.example.bilabonnement_dk.model.Rapportreservedel;
import com.example.bilabonnement_dk.model.Skaderapport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

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

    public void updatePrice(int skadeRapport_ID, double nyPris) {
        String sql = "UPDATE skaderapport SET pris = ? WHERE skaderapport_ID = ?";
        jdbcTemplate.update(sql, nyPris, skadeRapport_ID);
    }

    public List<Skaderapport> fetchAll() {
        String sql = """
                SELECT s.*,
                       l.leasing_ID,
                       m.medarbejder_ID, m.fornavn, m.efternavn
                FROM skaderapport s
                JOIN leasing l ON s.leasing_ID = l.leasing_ID
                JOIN medarbejder m ON s.medarbejder_ID = m.medarbejder_ID
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Leasing leasing = new Leasing();
            leasing.setLeasing_ID(rs.getInt("leasing_ID"));

            Medarbejder medarbejder = new Medarbejder();
            medarbejder.setMedarbejder_ID(rs.getInt("medarbejder_ID"));
            medarbejder.setFornavn(rs.getString("fornavn"));
            medarbejder.setEfternavn(rs.getString("efternavn"));

            return new Skaderapport(
                    rs.getInt("skaderapport_ID"),
                    rs.getDouble("pris"),
                    rs.getInt("arbejdstid"),
                    leasing,
                    medarbejder,
                    new ArrayList<>()
            );
        });
    }

    public Skaderapport findByID(int skaderapport_ID) {
        String sql = """
                SELECT s.*, l.leasing_ID,
                       m.medarbejder_ID, m.fornavn, m.efternavn
                FROM skaderapport s
                JOIN leasing l ON s.leasing_ID = l.leasing_ID
                JOIN medarbejder m ON s.medarbejder_ID = m.medarbejder_ID
                WHERE s.skaderapport_ID = ?
                """;

        List<Skaderapport> result = jdbcTemplate.query(sql, new Object[]{skaderapport_ID}, (rs, rowNum) -> {
            Leasing leasing = new Leasing();
            leasing.setLeasing_ID(rs.getInt("leasing_ID"));

            Medarbejder medarbejder = new Medarbejder();
            medarbejder.setMedarbejder_ID(rs.getInt("medarbejder_ID"));
            medarbejder.setFornavn(rs.getString("fornavn"));
            medarbejder.setEfternavn(rs.getString("efternavn"));

            return new Skaderapport(
                    rs.getInt("skaderapport_ID"),
                    rs.getDouble("pris"),
                    rs.getInt("arbejdstid"),
                    leasing,
                    medarbejder,
                    new ArrayList<>()
            );
        });

        return result.isEmpty() ? null : result.get(0);
    }
}