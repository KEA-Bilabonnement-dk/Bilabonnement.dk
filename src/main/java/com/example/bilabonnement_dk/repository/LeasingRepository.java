package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Abonnementstype;
import com.example.bilabonnement_dk.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository // Marker som repository-komponent for Spring
public class LeasingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private BilRepository bilRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    // Tilføjer en ny leasingaftale til databasen
    public void addLeasing(Leasing leasing) {
        String sql = """
            INSERT INTO leasing (kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris, medarbejder_ID, afhentningssted_ID)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;

        jdbcTemplate.update(sql,
                leasing.getKunde().getKunde_ID(),
                leasing.getBil().getBil_ID(),
                leasing.getAbonnementstype().name(),
                leasing.getStartdato(),
                leasing.getSlutdato(),
                leasing.getPris(),
                leasing.getMedarbejder().getMedarbejder_ID(),
                leasing.getAfhentningssted().getAdresse_ID()
        );
    }

    // Henter alle leasingaftaler
    public List<Leasing> fetchAll() {
        String sql = """
            SELECT leasing_ID, kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris, medarbejder_ID, afhentningssted_ID, afleveret
            FROM leasing
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    // Finder en leasingaftale ud fra leasing_ID
    public Leasing findLeasingByID(int leasing_ID) {
        String sql = """
            SELECT leasing_ID, kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris, medarbejder_ID, afhentningssted_ID, afleveret
            FROM leasing
            WHERE leasing_ID = ?
        """;

        List<Leasing> resultater = jdbcTemplate.query(sql, new Object[]{leasing_ID}, (rs, rowNum) -> mapRow(rs));
        return resultater.isEmpty() ? null : resultater.get(0);
    }

    // Privat metode til at mappe en række fra ResultSet til et Leasing-objekt
    private Leasing mapRow(ResultSet rs) throws SQLException {
        Leasing leasing = new Leasing();

        leasing.setLeasing_ID(rs.getInt("leasing_ID"));
        leasing.setStartdato(rs.getDate("startdato").toLocalDate());
        leasing.setSlutdato(rs.getDate("slutdato").toLocalDate());
        leasing.setPris(rs.getDouble("pris"));
        leasing.setAbonnementstype(Abonnementstype.valueOf(rs.getString("abonnementstype")));

        int kunde_ID = rs.getInt("kunde_ID");
        int bil_ID = rs.getInt("bil_ID");
        int medarbejder_ID = rs.getInt("medarbejder_ID");
        int afhentningssted_ID = rs.getInt("afhentningssted_ID");

        leasing.setKunde(kundeRepository.findKundeByID(kunde_ID));
        leasing.setBil(bilRepository.findBilByID(bil_ID));
        leasing.setAfhentningssted(adresseRepository.findAdresseByID(afhentningssted_ID));
        leasing.setAfleveret(rs.getBoolean("afleveret"));

        return leasing;
    }

    // Opdaterer en eksisterende leasingaftale
    public void updateLeasing(Leasing leasing) {
        String sql = """
                UPDATE leasing
                SET kunde_ID = ?, bil_ID = ?, abonnementstype = ?, startdato = ?, slutdato = ?, pris = ?, medarbejder_ID = ?, afhentningssted_ID = ?, afleveret = ?
                WHERE leasing_ID = ?
                """;

        jdbcTemplate.update(sql,
                leasing.getKunde().getKunde_ID(),
                leasing.getBil().getBil_ID(),
                leasing.getAbonnementstype().name(),
                leasing.getStartdato(),
                leasing.getSlutdato(),
                leasing.getPris(),
                leasing.getMedarbejder().getMedarbejder_ID(),
                leasing.getAfhentningssted().getAdresse_ID(),
                leasing.isAfleveret(),
                leasing.getLeasing_ID()
        );
    }

    // Sletter en leasingaftale baseret på leasing_ID
    public void deleteLeasingByID(int leasing_ID) {
        String sql = "DELETE FROM leasing WHERE leasing_ID = ?";
        jdbcTemplate.update(sql, leasing_ID);
    }

    // Finder leasingaftaler hvor slutdato er overskredet
    public List<Leasing> findEndedLeasing() {
        String sql = """
                SELECT * FROM leasing
                WHERE slutdato < CURDATE()
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Leasing leasing = new Leasing();
            leasing.setLeasing_ID(rs.getInt("leasing_ID"));
            leasing.setStartdato(rs.getDate("startdato").toLocalDate());
            leasing.setSlutdato(rs.getDate("slutdato").toLocalDate());
            leasing.setPris(rs.getDouble("pris"));

            return leasing;
        });
    }

    // Markerer en leasingaftale som afleveret
    public void markAsAfleveret(int leasing_ID) {
        String sql = "UPDATE leasing SET afleveret = true WHERE leasing_ID = ?";
        jdbcTemplate.update(sql, leasing_ID);
    }

    // Finder leasingaftaler som er afleveret og har slutdato før dags dato
    public List<Leasing> findAfleveredeLeasing() {
        String sql = """
                SELECT * FROM leasing
                WHERE slutdato < CURDATE() AND afleveret = true
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }
}