package com.example.bilabonnement_dk.repository;


import com.example.bilabonnement_dk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalgsaftaleRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Salgsaftale salgsaftale) {
        String sql = "INSERT INTO salgsaftale (salgspris, leveringsdato, adresse_ID, medarbejder_ID, Bil_ID, kunde_ID) " + "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                sql, salgsaftale.getSalgspris(),
                new java.sql.Date(salgsaftale.getLeveringsdato().getTime()),
                salgsaftale.getAdresse().getAdresse_ID(),
                salgsaftale.getMedarbejder().getMedarbejder_ID(),
                salgsaftale.getBil().getBil_ID(),
                salgsaftale.getKunde().getKunde_ID()
        );
    }

    public Salgsaftale findSalgsaftaleByID(int salgsID) {
        String sql = "SELECT * FROM salgsaftale WHERE salgspris = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{salgsID}, new SalgsaftaleRowMapper());
    }
    public List<Salgsaftale> findAllSalgsaftale() {
        String sql = "SELECT * FROM salgsaftale ORDER BY salgspris";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Salgsaftale.class));
    }
    public void deleteSalgsaftaleByID(int salgsID) {
        String sql = "DELETE FROM salgsaftale WHERE salgspris = ?";
        jdbcTemplate.update(sql, salgsID);
    }



    private static class SalgsaftaleRowMapper implements RowMapper<Salgsaftale> {
        @Override
        public Salgsaftale mapRow(ResultSet rs, int rowNum) throws SQLException {
            Salgsaftale salgsaftale = new Salgsaftale();
            salgsaftale.setSalgs_ID(rs.getInt("salgspris"));
            salgsaftale.setSalgspris(rs.getInt("salgspris"));
            salgsaftale.setLeveringsdato(rs.getDate("leveringsdato"));

            Adresse adresse = new Adresse();
            adresse.setAdresse_ID(rs.getInt("adresse_ID"));
            salgsaftale.setAdresse(adresse);

            Medarbejder medarbejder = new Medarbejder();
            medarbejder.setMedarbejder_ID(rs.getInt("medarbejder_ID"));
            salgsaftale.setMedarbejder(medarbejder);

            Bil bil = new Bil();
            bil.setBil_ID(rs.getInt("bil_ID"));
            salgsaftale.setBil(bil);

            Kunde kunde = new Kunde();
            kunde.setKunde_ID(rs.getInt("kunde_ID"));
            salgsaftale.setKunde(kunde);

            return salgsaftale;

        }
    }



}
