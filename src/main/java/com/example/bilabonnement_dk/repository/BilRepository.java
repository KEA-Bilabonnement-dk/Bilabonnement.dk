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

    public void addBil(Bil bil){
        String sql = "INSERT INTO bil (indkoebsdato, vognnr, stelnr, udstyrsniveau, staalpris, regafg, co2udl, Biltype, maerke, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        template.update(sql,
                bil.getIndkoebsdato(),
                bil.getVognnr(),
                bil.getStelnr(),
                bil.getUdstyrsniveau(),
                bil.getStaalpris(),
                bil.getRegafg(),
                bil.getCo2udl(),
                bil.getBiltype().name(),
                bil.getMaerke(),
                bil.getModel());
    }

    public Bil findBilByID(int bil_ID){
        String sql = "SELECT * FROM bil WHERE bil_ID = ?";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.queryForObject(sql, rowMapper, bil_ID);
    }

    public List<Bil> fetchAll(){
        String sql = "SELECT * FROM bil";
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper);
    }

    public void updateBil(Bil bil){
        String sql = "UPDATE bil SET indkoebsdato = ?, vognnr = ?, stelnr = ?, udstyrsniveau = ?, staalpris = ?, regafg = ?, co2udl = ?, Biltype = ?, maerke = ?, model = ? WHERE bil_ID = ?";
        template.update(sql, bil.getIndkoebsdato(), bil.getVognnr(), bil.getStelnr(),
                bil.getUdstyrsniveau(), bil.getStaalpris(), bil.getRegafg(), bil.getCo2udl(),
                    bil.getBiltype().name(), bil.getMaerke(), bil.getModel(), bil.getBil_ID());
    }

    public void deleteBil(int bil_ID){
        String sql = "DELETE FROM bil WHERE bil_ID = ?";
        template.update(sql, bil_ID);
    }

    public List<Bil> searchAllFields(String query) {
        String sql = """
        SELECT * FROM bil WHERE
            CAST(bil_ID AS CHAR) LIKE ? OR
            LOWER(maerke) LIKE ? OR
            LOWER(model) LIKE ? OR
            LOWER(stelnr) LIKE ? OR
            LOWER(vognnr) LIKE ? OR
            LOWER(udstyrsniveau) LIKE ? OR
            CAST(staalpris AS CHAR) LIKE ? OR
            CAST(regafg AS CHAR) LIKE ? OR
            CAST(co2udl AS CHAR) LIKE ? OR
            LOWER(biltype) LIKE ? OR
            DATE_FORMAT(indkoebsdato, '%Y-%m-%d') LIKE ?
    """;
        String pattern = "%" + query.toLowerCase() + "%";
        Object[] params = {
                pattern, pattern, pattern, pattern, pattern,
                pattern, pattern, pattern, pattern, pattern, pattern
        };
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class);
        return template.query(sql, rowMapper, params);
    }
}
