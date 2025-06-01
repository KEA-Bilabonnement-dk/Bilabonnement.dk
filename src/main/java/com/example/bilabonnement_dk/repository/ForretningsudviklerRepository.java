package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Bil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Repository // Angiver, at dette er en repository-klasse til dataadgang
public class ForretningsudviklerRepository {

    private JdbcTemplate jdbcTemplate;

    // Konstruktor med dependency injection af JdbcTemplate
    public ForretningsudviklerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Henter det samlede antal leasingaftaler i databasen
    public int fetchAllLeasninger() {
        String sql = "SELECT COUNT(*) FROM leasing";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Finder antal aktive leasingaftaler, dvs. hvor slutdato er frem i tiden
    public int findSamletAntalAktivtUdlejede() {
        String sql = "SELECT COUNT(*) FROM leasing WHERE slutdato > CURRENT_DATE";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Finder alle biler, der aktuelt er udlejet (aktive leasingaftaler)
    public List<Bil> findUdlejedeBiler() {
        String sql = "SELECT b.* FROM bil b JOIN leasing l ON b.bil_ID = l.bil_ID WHERE l.slutdato > CURRENT_DATE";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bil.class));
    }

    // Finder alle biler, som ikke er udlejet lige nu (ikke i aktiv leasing)
    public List<Bil> findBilerPaaLager() {
        String sql = "SELECT * FROM bil WHERE bil_ID NOT IN (SELECT bil_ID FROM leasing WHERE slutdato > CURRENT_DATE)";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Bil.class));
    }

    // Finder den samlede pris af alle leasingaftaler (uanset status)
    public BigDecimal findSamletPrisUdlejedeBiler() {
        String sql = "SELECT SUM(pris) FROM leasing";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }

    // Finder den samlede pris af aktive leasingaftaler (slutdato i fremtiden)
    public BigDecimal findSamletPrisAktiveUdlejedeBiler() {
        String sql = "SELECT SUM(pris) FROM leasing WHERE slutdato > CURRENT_DATE";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class);
    }

    // Beregner udlejningsgraden i procent (antal udlejede biler ift. samlet antal biler)
    public double hentUdlejningsgrad() {
        String sql = """
                SELECT 
                    COUNT(DISTINCT l.bil_ID) * 100.0 / 
                    (SELECT COUNT(*) FROM bil) AS udlejningsgrad
                FROM leasing l
                WHERE CURDATE() BETWEEN l.startdato AND l.slutdato;
                """;

        Double result = jdbcTemplate.queryForObject(sql, Double.class);
        return result != null ? result : 0.0; // Returnerer 0 hvis null
    }

    // Finder top 3 kunder med flest leasingaftaler, returnerer listen som Map med kolonnenavne og værdier
    public List<Map<String, Object>> findTop3KunderMedFlestLeasingaftaler() {
        String sql = """
        SELECT 
            k.kunde_ID,
            k.kfornavn,
            k.kefternavn,
            COUNT(l.leasing_ID) AS antal_leasingaftaler
        FROM 
            kunde k
        JOIN 
            leasing l ON k.kunde_ID = l.kunde_ID
        GROUP BY 
            k.kunde_ID, k.kfornavn, k.kefternavn
        ORDER BY 
            antal_leasingaftaler DESC
        LIMIT 3
        """;
        return jdbcTemplate.queryForList(sql);
    }
}