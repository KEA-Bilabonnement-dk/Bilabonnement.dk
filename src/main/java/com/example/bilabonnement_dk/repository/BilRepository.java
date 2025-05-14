package com.example.bilabonnement_dk.repository;
import com.example.bilabonnement_dk.model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BilRepository {
    @Autowired
    JdbcTemplate template;

    public List<Bil> fetchAll(){
        String sql = "SELECT * FROM Bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public void addBil(Bil a){
        String sql = "INSERT INTO Bil (bil_ID, indkoebsdato, vognnr, stelnr, udstyrsniveau, staalpris, regafg, co2udl, type, mærke, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, a.getBil_ID(), a.getIndkoebdato(), a.getVognnr(), a.getStelnr(), a.getUdstyrsniveau(), a.getStaalpris(), a.getRegafg(), a.getCo2udl(), a.getType(), a.getMaerke(), a.getModel());
    }
}
