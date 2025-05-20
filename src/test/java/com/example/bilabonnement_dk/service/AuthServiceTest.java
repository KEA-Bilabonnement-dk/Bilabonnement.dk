package com.example.bilabonnement_dk.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    AuthService authService = new AuthService();

    @Test
    void isValidPassword_happyFlow() {
        assertTrue(authService.isValidPassword("chrismerrykrisjona1234"));
    }

    @Test
    void testInvalidPassword_exceptionFlow() {
        assertFalse(authService.isValidPassword("MCKJ"));
    }

    @Test
    void testNullPassword_exceptionFlow() {
        assertFalse(authService.isValidPassword(null));
    }
}