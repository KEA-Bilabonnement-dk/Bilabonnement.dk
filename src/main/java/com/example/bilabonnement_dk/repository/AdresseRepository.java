package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Adresse;
import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdresseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void addAdresse(Adresse adresse) {
        String sql = "INSERT INTO adresse (vejnavn, vejnr, land, postnummer, bynavn) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                adresse.getVejnavn(),
                adresse.getVejnr(),
                adresse.getLand(),
                adresse.getPostnummer(),
                adresse.getBynavn());
    }

    public Adresse findAdresseByID(int adresse_ID){
        String sql = "SELECT * FROM adresse WHERE adresse_ID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{adresse_ID}, (rs, rowNum) -> {
            Adresse adresse = new Adresse();
            adresse.setAdresse_ID(rs.getInt("adresse_ID"));
            adresse.setVejnavn(rs.getString("vejnavn"));
            adresse.setVejnr(rs.getString("vejnr"));
            adresse.setLand(rs.getString("land"));
            adresse.setPostnummer(rs.getInt("postnummer"));
            adresse.setBynavn(rs.getString("bynavn"));
            return adresse;
        });
    }

    public List<Adresse> fetchAll() {
        String sql = "SELECT * FROM adresse";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Adresse adresse = new Adresse();
            adresse.setAdresse_ID(rs.getInt("adresse_ID"));
            adresse.setVejnavn(rs.getString("vejnavn"));
            adresse.setVejnr(rs.getString("vejnr"));
            adresse.setLand(rs.getString("land"));
            adresse.setPostnummer(rs.getInt("postnummer"));
            adresse.setBynavn(rs.getString("bynavn"));
            return adresse;
        });
    }
}