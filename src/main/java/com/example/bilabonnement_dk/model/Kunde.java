package com.example.bilabonnement_dk.model;

public class Kunde {
    private int kunde_ID;
    private String kFornavn;
    private String kEfternavn;
    private int kTelefonnummer;
    private String kEmail;
    private Adresse adresse;

    public Kunde() {
    }

    public Kunde(int kunde_ID, String kFornavn, String kEfternavn, int kTelefonnummer, String kEmail, Adresse adresse) {
        this.kunde_ID = kunde_ID;
        this.kFornavn = kFornavn;
        this.kEfternavn = kEfternavn;
        this.kTelefonnummer = kTelefonnummer;
        this.kEmail = kEmail;
        this.adresse = adresse;
    }

    public int getKunde_ID() {
        return kunde_ID;
    }

    public void setKunde_ID(int kunde_ID) {
        this.kunde_ID = kunde_ID;
    }

    public String getkFornavn() {
        return kFornavn;
    }

    public void setkFornavn(String kFornavn) {
        this.kFornavn = kFornavn;
    }

    public String getkEfternavn() {
        return kEfternavn;
    }

    public void setkEfternavn(String kEfternavn) {
        this.kEfternavn = kEfternavn;
    }

    public int getkTelefonnummer() {
        return kTelefonnummer;
    }

    public void setkTelefonnummer(int kTelefonnummer) {
        this.kTelefonnummer = kTelefonnummer;
    }

    public String getkEmail() {
        return kEmail;
    }

    public void setkEmail(String kEmail) {
        this.kEmail = kEmail;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}