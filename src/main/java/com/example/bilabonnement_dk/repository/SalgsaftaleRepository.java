package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Bil;
import com.example.bilabonnement_dk.model.Kunde;
import com.example.bilabonnement_dk.model.Salgsaftale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SalgsaftaleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BilRepository bilRepository;

    @Autowired
    private MedarbejderRepository medarbejderRepository;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    // Tilføj en ny salgsaftale til databasen
    public void addSalgsaftale(Salgsaftale salgsaftale) {
        String sql = """
            INSERT INTO salgsaftale (salg_ID, salgspris, leveringsdato, adresse_ID, medarbejder_ID, bil_ID, kunde_ID)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                salgsaftale.getSalg_ID(),
                salgsaftale.getSalgspris(),
                salgsaftale.getLeveringsdato(),
                salgsaftale.getAdresse().getAdresse_ID(),
                salgsaftale.getMedarbejder().getMedarbejder_ID(),
                salgsaftale.getBil().getBil_ID(),
                salgsaftale.getKunde().getKunde_ID()
        );
    }

    // Hent alle salgsaftaler
    public List<Salgsaftale> fetchAll() {
        String sql = """
            SELECT salg_ID, salgspris, leveringsdato, adresse_ID, medarbejder_ID, bil_ID, kunde_ID
            FROM salgsaftale
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    // Find en salgsaftale ud fra ID
    public Salgsaftale findSalgsaftaleByID(int salg_ID) {
        String sql = """
            SELECT salg_ID, salgspris, leveringsdato, adresse_ID, medarbejder_ID, bil_ID, kunde_ID
            FROM salgsaftale
            WHERE salg_ID = ?
        """;

        List<Salgsaftale> resultater = jdbcTemplate.query(sql, new Object[]{salg_ID}, (rs, rowNum) -> mapRow(rs));
        return resultater.isEmpty() ? null : resultater.get(0);
    }

    // Privat metode til at mappe ResultSet til Salgsaftale objekt
    private Salgsaftale mapRow(ResultSet rs) throws SQLException {
        Salgsaftale salgsaftale = new Salgsaftale();

        salgsaftale.setSalg_ID(rs.getInt("salg_ID"));
        salgsaftale.setSalgspris(rs.getDouble("salgspris"));
        salgsaftale.setLeveringsdato(rs.getDate("leveringsdato").toLocalDate());

        int medarbejder_ID = rs.getInt("medarbejder_ID");
        int bil_ID = rs.getInt("bil_ID");
        int kunde_ID = rs.getInt("kunde_ID");
        int adresse_ID = rs.getInt("adresse_ID");

        salgsaftale.setAdresse(adresseRepository.findAdresseByID(adresse_ID));
        salgsaftale.setMedarbejder(medarbejderRepository.findMedarbejderByID(medarbejder_ID));
        salgsaftale.setKunde(kundeRepository.findKundeByID(kunde_ID));
        salgsaftale.setBil(bilRepository.findBilByID(bil_ID));

        return salgsaftale;
    }

    // Opdater en eksisterende salgsaftale
    public void updateSalgsaftale(Salgsaftale salgsaftale) {
        String sql = """
            UPDATE salgsaftale
            SET salgspris = ?, leveringsdato = ?, adresse_ID = ?, medarbejder_ID = ?, bil_ID = ?, kunde_ID = ?
            WHERE salg_ID = ?
        """;
        jdbcTemplate.update(sql,
                salgsaftale.getSalgspris(),
                salgsaftale.getLeveringsdato(),
                salgsaftale.getAdresse().getAdresse_ID(),
                salgsaftale.getMedarbejder().getMedarbejder_ID(),
                salgsaftale.getBil().getBil_ID(),
                salgsaftale.getKunde().getKunde_ID(),
                salgsaftale.getSalg_ID()
        );
    }

    // Slet en salgsaftale ud fra ID
    public void deleteSalgsaftaleByID(int salg_ID) {
        String sql = "DELETE FROM salgsaftale WHERE salg_ID = ?";
        jdbcTemplate.update(sql, salg_ID);
    }
}