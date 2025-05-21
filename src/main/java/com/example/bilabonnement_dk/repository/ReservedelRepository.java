package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Reservedel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservedelRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Reservedel> fetchAll() {
        String sql = "SELECT * FROM reservedel";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Reservedel (
                rs.getInt("reservedel_ID"),
                rs.getString("type"),
                rs.getDouble("pris")
        ));
    }

    public Reservedel findByID(int reservedel_ID) {
        String sql = "SELECT * FROM reservedel WHERE reservedel_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{reservedel_ID}, (rs, rowNum) -> new Reservedel(
                rs.getInt("reservedel_ID"),
                rs.getString("type"),
                rs.getDouble("pris")
        ));
    }
}
