package com.example.bilabonnement_dk.model;

public class Medarbejder {
    private int medarbejder_ID;
    private String fornavn;
    private String efternavn;
    private String telefonnummer;
    private String email;
    private String brugernavn;
    private String adgangskode;
    private Rolle rolle;
    private Adresse adresse;

    public Medarbejder()
    {
    }

    public Medarbejder(int medarbejder_ID, String fornavn, String efternavn, String telefonnummer, String email, String brugernavn, String adgangskode, Rolle rolle, Adresse adresse) {
        this.medarbejder_ID = medarbejder_ID;
        this.fornavn = fornavn;
        this.efternavn = efternavn;
        this.telefonnummer = telefonnummer;
        this.email = email;
        this.brugernavn = brugernavn;
        this.adgangskode = adgangskode;
        this.rolle = rolle;
        this.adresse = adresse;
    }

    public int getMedarbejder_ID() {
        return medarbejder_ID;
    }

    public void setMedarbejder_ID(int medarbejder_ID) {
        this.medarbejder_ID = medarbejder_ID;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBrugernavn() {
        return brugernavn;
    }

    public void setBrugernavn(String brugernavn) {
        this.brugernavn = brugernavn;
    }

    public String getAdgangskode() {
        return adgangskode;
    }

    public void setAdgangskode(String adgangskode) {
        this.adgangskode = adgangskode;
    }

    public Rolle getRolle() {
        return rolle;
    }

    public void setRolle(Rolle rolle) {
        this.rolle = rolle;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}