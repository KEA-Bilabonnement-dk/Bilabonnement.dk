package com.example.bilabonnement_dk.repository;

import com.example.bilabonnement_dk.model.Abonnementstype;
import com.example.bilabonnement_dk.model.Leasing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LeasingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private KundeRepository kundeRepository;

    @Autowired
    private BilRepository bilRepository;

    public void addLeasing(Leasing leasing) {
        String sql = """
            INSERT INTO leasing (kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris, medarbejder_ID)
            VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

        int medarbejderId = 0;
        jdbcTemplate.update(sql,
                leasing.getKunde().getKunde_ID(),
                leasing.getBil().getBil_ID(),
                leasing.getAbonnementstype().name(),
                leasing.getStartdato(),
                leasing.getSlutdato(),
                leasing.getPris(),
                leasing.getMedarbejder().getMedarbejder_ID()
        );
    }

    public List<Leasing> fetchAll() {
        String sql = """
            SELECT leasing_ID, kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris
            FROM leasing
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> mapRow(rs));
    }

    public Leasing findLeasingByID(int leasing_ID) {
        String sql = """
            SELECT leasing_ID, kunde_ID, bil_ID, abonnementstype, startdato, slutdato, pris
            FROM leasing
            WHERE leasing_ID = ?
        """;

        List<Leasing> resultater = jdbcTemplate.query(sql, new Object[]{leasing_ID}, (rs, rowNum) -> mapRow(rs));
        return resultater.isEmpty() ? null : resultater.get(0);
    }

    // Privat metode til at genbruge mapping
    private Leasing mapRow(ResultSet rs) throws SQLException {
        Leasing leasing = new Leasing();

        leasing.setLeasing_ID(rs.getInt("leasing_ID"));
        leasing.setStartdato(rs.getDate("startdato").toLocalDate());
        leasing.setSlutdato(rs.getDate("slutdato").toLocalDate());
        leasing.setPris(rs.getDouble("pris"));
        leasing.setAbonnementstype(Abonnementstype.valueOf(rs.getString("abonnementstype")));

        int kunde_ID = rs.getInt("kunde_ID");
        int bil_ID = rs.getInt("bil_ID");

        leasing.setKunde(kundeRepository.findKundeByID(kunde_ID));
        leasing.setBil(bilRepository.findBilByID(bil_ID));

        return leasing;
    }

    public void updateLeasing(Leasing leasing)
    {
        String sql = """
                UPDATE leasing
                SET kunde_ID = ?, bil_ID = ?, abonnementstype = ?, startdato = ?, slutdato = ?, pris = ?, medarbejder_ID = ?
                WHERE leasing_ID = ?
                """;

        int medarbejderId = 0;
        jdbcTemplate.update(sql,
                leasing.getKunde().getKunde_ID(),
                leasing.getBil().getBil_ID(),
                leasing.getAbonnementstype().name(),
                leasing.getStartdato(),
                leasing.getSlutdato(),
                leasing.getPris(),
                leasing.getMedarbejder().getMedarbejder_ID(),
                leasing.getLeasing_ID()
        );
    }

    public void deleteLeasingByID(int leasing_ID)
    {
        String sql = "DELETE FROM leasing WHERE leasing_ID = ?";
        jdbcTemplate.update(sql, leasing_ID);
    }
}
