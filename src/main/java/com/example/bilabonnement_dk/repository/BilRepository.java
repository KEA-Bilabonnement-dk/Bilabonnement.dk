package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Bil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Marker klassen som en Spring-komponent, der håndterer databaseoperationer
public class BilRepository {

    @Autowired // Automatisk dependency injection af JdbcTemplate fra Spring containeren
    JdbcTemplate template;

    // Metode til at tilføje en bil til databasen
    public void addBil(Bil bil){
        // SQL INSERT-sætning med 10 værdier svarende til felterne i bil-tabellen
        String sql = "INSERT INTO bil (indkoebsdato, vognnr, stelnr, udstyrsniveau, staalpris, regafg, co2udl, Biltype, maerke, model) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Udfør indsættelse med værdier fra Bil-objektet
        template.update(sql,
                bil.getIndkoebsdato(),
                bil.getVognnr(),
                bil.getStelnr(),
                bil.getUdstyrsniveau(),
                bil.getStaalpris(),
                bil.getRegafg(),
                bil.getCo2udl(),
                bil.getBiltype().name(), // Enum konverteres til tekst (navn)
                bil.getMaerke(),
                bil.getModel());
    }

    // Finder én bil i databasen baseret på dens ID
    public Bil findBilByID(int bil_ID){
        String sql = "SELECT * FROM bil WHERE bil_ID = ?"; // SQL SELECT med WHERE
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class); // Automatisk mapping mellem kolonner og felter i Bil
        return template.queryForObject(sql, rowMapper, bil_ID); // Returnerer én bil
    }

    // Henter alle biler fra bil-tabellen
    public List<Bil> fetchAll(){
        String sql = "SELECT * FROM bil"; // SQL SELECT uden filter
        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class); // Mapper resultatet til liste af Bil-objekter
        return template.query(sql, rowMapper); // Returnerer listen
    }

    // Opdaterer en bil i databasen baseret på bilens ID
    public void updateBil(Bil bil){
        // SQL UPDATE-sætning med alle felter, og betingelse på bil_ID
        String sql = "UPDATE bil SET indkoebsdato = ?, vognnr = ?, stelnr = ?, udstyrsniveau = ?, staalpris = ?, regafg = ?, co2udl = ?, Biltype = ?, maerke = ?, model = ? WHERE bil_ID = ?";

        // Udfør opdatering med data fra Bil-objektet
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
                bil.getModel(),
                bil.getBil_ID());
    }

    // Sletter en bil fra databasen baseret på dens ID
    public void deleteBil(int bil_ID){
        String sql = "DELETE FROM bil WHERE bil_ID = ?"; // SQL DELETE med WHERE
        template.update(sql, bil_ID); // Udfører sletning
    }

    // Søger efter biler hvor ét eller flere felter matcher det angivne søgeord
    public List<Bil> searchAllFields(String query) {
        // SQL-søgning hvor vi søger bredt i mange felter ved brug af LIKE og CAST
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

        // Søgemønstret konstrueres (lowercase for ensartethed)
        String pattern = "%" + query.toLowerCase() + "%";

        // Parametrene sættes til samme mønster for alle felter
        Object[] params = {
                pattern, pattern, pattern, pattern, pattern,
                pattern, pattern, pattern, pattern, pattern, pattern
        };

        RowMapper<Bil> rowMapper = new BeanPropertyRowMapper<>(Bil.class); // Mapper hver række til et Bil-objekt
        return template.query(sql, rowMapper, params); // Returnerer liste over matchende biler
    }
}