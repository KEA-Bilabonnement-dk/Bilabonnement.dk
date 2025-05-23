package com.example.bilabonnement_dk.model;

public class Adresse {
    private int adresse_ID;
    private String vejnr;
    private String vejnavn;
    private String land;
    private int postnummer;
    private String bynavn;



    public Adresse() {
    }

public Adresse(int adresse_ID, String vejnr, String vejnavn, String land, int postnummer) {
    this.adresse_ID = adresse_ID;
    this.vejnr = vejnr;
    this.vejnavn = vejnavn;
    this.land = land;
    this.postnummer = postnummer;
    }

    public int getAdresse_ID() {
        return adresse_ID;
    }

    public String getVejnr() {
        return vejnr;
    }

    public String getVejnavn() {
        return vejnavn;
    }

    public String getLand() {
        return land;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setAdresse_ID(int adresse_ID) {
        this.adresse_ID = adresse_ID;
    }

    public void setVejnr(String vejnr) {
        this.vejnr = vejnr;
    }

    public void setVejnavn(String vejnavn) {
        this.vejnavn = vejnavn;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public String getBynavn() {
        return bynavn;
    }

    public void setBynavn(String bynavn) {
        this.bynavn = bynavn;
    }
}