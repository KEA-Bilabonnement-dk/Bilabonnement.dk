package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // Angiver at denne klasse er en repository-komponent, som håndterer dataadgang
public class AdresseRepository {

    @Autowired // Automatisk indsætter JdbcTemplate, som bruges til at kommunikere med databasen
    private JdbcTemplate jdbcTemplate;

    // Metode til at indsætte en ny adresse i databasen
    public void addAdresse(Adresse adresse) {
        // SQL-sætning der indsætter de relevante felter i adresse-tabellen
        String sql = "INSERT INTO adresse (vejnavn, vejnr, land, postnummer, bynavn) VALUES (?, ?, ?, ?, ?)";

        // Udfører SQL-indsættelsen med værdier fra Adresse-objektet
        jdbcTemplate.update(sql,
                adresse.getVejnavn(),
                adresse.getVejnr(),
                adresse.getLand(),
                adresse.getPostnummer(),
                adresse.getBynavn());
    }

    // Finder en adresse ud fra dens ID i databasen
    public Adresse findAdresseByID(int adresse_ID){
        String sql = "SELECT * FROM adresse WHERE adresse_ID = ?";

        // Udfører forespørgslen og bruger en lambda til at omsætte databaseresultatet til et Adresse-objekt
        return jdbcTemplate.queryForObject(sql, new Object[]{adresse_ID}, (rs, rowNum) -> {
            Adresse adresse = new Adresse(); // Opretter et nyt Adresse-objekt
            adresse.setAdresse_ID(rs.getInt("adresse_ID")); // Sætter ID'et på objektet
            adresse.setVejnavn(rs.getString("vejnavn"));    // Sætter vejnavn
            adresse.setVejnr(rs.getString("vejnr"));        // Sætter vejnr
            adresse.setLand(rs.getString("land"));          // Sætter land
            adresse.setPostnummer(rs.getInt("postnummer")); // Sætter postnummer
            adresse.setBynavn(rs.getString("bynavn"));      // Sætter bynavn
            return adresse; // Returnerer det færdige Adresse-objekt
        });
    }

    // Henter alle adresser fra adresse-tabellen i databasen
    public List<Adresse> fetchAll() {
        String sql = "SELECT * FROM adresse";

        // Udfører forespørgslen og omsætter hver række til et Adresse-objekt vha. lambda
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Adresse adresse = new Adresse(); // Opretter nyt Adresse-objekt for hver række
            adresse.setAdresse_ID(rs.getInt("adresse_ID")); // Sætter ID
            adresse.setVejnavn(rs.getString("vejnavn"));    // Sætter vejnavn
            adresse.setVejnr(rs.getString("vejnr"));        // Sætter vejnr
            adresse.setLand(rs.getString("land"));          // Sætter land
            adresse.setPostnummer(rs.getInt("postnummer")); // Sætter postnummer
            adresse.setBynavn(rs.getString("bynavn"));      // Sætter bynavn
            return adresse; // Returnerer det færdige objekt til listen
        });
    }

    // Henter det senest oprettede adresse-ID fra adresse-tabellen
    public int getLatestAdresseID()
    {
        String sql = "SELECT adresse_ID FROM adresse ORDER BY adresse_ID DESC LIMIT 1";

        // Returnerer det højeste adresse_ID (typisk det nyeste indsatte)
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}