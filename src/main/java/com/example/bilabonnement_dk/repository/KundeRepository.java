package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Kunde;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {
    @Autowired
    JdbcTemplate template;


    public List<Kunde> fetchAll() {
        String sql = "select * from kunde";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return template.query(sql, rowMapper);
    }
    public void addKunde(Kunde k) {
        String sql = "INSERT INTO kunde (kunde_ID, kfornavn, kefternavn, ktelefonnummer, kmail, adresse_ID) VALUES (?, ?, ?, ?, ?, ?)";
        template.update(sql,k.getKunde_ID(),k.getkFornavn(),k.getkEfternavn(),k.getkTelefonnummer(),k.getkEmail(),k.getAdresse());

    }
    public Kunde getKundeById(int id) {
        String sql = "select * from kunde where kunde_ID = ?";
        RowMapper<Kunde> rowMapper = new BeanPropertyRowMapper<>(Kunde.class);
        return template.queryForObject(sql, rowMapper, id);
    }
    public Boolean deleteKunde(int id) {
        String sql = "delete from kunde where kunde_ID = ?";
       return template.update(sql,id) > 0;

    }
    public void updateKunde(Kunde k) {
        String sql = "UPDATE Kunde SET Kunde_ID, kfornavn, kefternavn, ktelefonnummer, kmail, adresse_ID=? WHERE kunde_ID = ?";
        template.update(sql, k.getKunde_ID(),k.getkFornavn(),k.getkEfternavn(),k.getkTelefonnummer(),k.getkEmail(),k.getAdresse());

    }




}
