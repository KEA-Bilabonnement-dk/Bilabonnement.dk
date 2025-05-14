package com.example.bilabonnement_dk.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MedarbejderRepository {
    @Autowired
    private JdbcTemplate template;
}
