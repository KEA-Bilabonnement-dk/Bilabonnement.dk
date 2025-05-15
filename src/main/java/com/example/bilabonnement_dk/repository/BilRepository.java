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
    private static JdbcTemplate template;

    public List<Bil> fetchAll(){
        String sql = "SELECT * FROM Bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public void addBil(Bil a){
        String sql = "INSERT INTO bil (indkoebsdato, vognnr, stelnr, udstyrsniv, staalpris, regafg, co2udl, Biltype, maerke, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql, a.getIndkoebdato(), a.getVognnr(), a.getStelnr(), a.getUdstyrsniveau(), a.getStaalpris(), a.getRegafg(), a.getCo2udl(), a.getBilType(), a.getMaerke(), a.getModel());
    }


    public Bil findBilById(int id){
        String sql = "SELECT * FROM bil WHERE bil_ID = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.queryForObject(sql, rowMapper, id);
    }

    public Boolean deleteBil(int id){
        String sql = "DELETE FROM bil WHERE bil_ID = ?";
        return template.update(sql, id) > 0;
    }

    public void updateBil(Bil a){
        String sql = "UPDATE bil SET indkoebsdato = ?, vognnr = ?, stelnr = ?, udstyrsniveau = ?, staalpris = ?, regafg = ?, co2udl = ?, Biltype = ?, maerke = ?, model = ? WHERE bil_ID = ?";
        template.update(sql, a.getIndkoebdato(), a.getVognnr(), a.getStelnr(), a.getUdstyrsniveau(), a.getStaalpris(), a.getRegafg(), a.getCo2udl(), a.getBilType(), a.getMaerke(), a.getModel());
    }

}
