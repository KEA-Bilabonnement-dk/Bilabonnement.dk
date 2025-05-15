package com.example.bilabonnement_dk.repository;


import com.example.bilabonnement_dk.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeasingRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Leasing> fetchAll(){
        String sql = "select * from leasing_ID";
        BeanPropertyRowMapper<Leasing> rowMapper = new BeanPropertyRowMapper<>(Leasing.class);
        return jdbcTemplate.query(sql, rowMapper);
    }
    public Leasing getLeasingByID(int ID){
        String sql = "select * from leasing_ID where ID = ?";
        BeanPropertyRowMapper<Leasing> rowMapper = new BeanPropertyRowMapper<>(Leasing.class);
        return jdbcTemplate.queryForObject(sql, rowMapper, ID);

    }
    public void addLeasing(Leasing l){
        String sql = "insert into leasing (Leasing_ID, pris, startdato, slutdato, kunde, medarbejder, bil, abonnomentstype) values (?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql, l.getLeasing_ID(), l.getPris(), l.getStartdato(), l.getSlutdato(), l.getKunde(), l.getMedarbejder(), l.getBil(), l.getAbonnementstype());


    }

}
