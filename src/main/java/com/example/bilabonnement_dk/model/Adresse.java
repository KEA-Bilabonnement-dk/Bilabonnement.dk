package com.example.bilabonnement_dk.model;

public class Adresse {
    private int adresse_ID;
    private int vejnr;
    private String vejnavn;
    private String land;
    private int postnr;


    public Adresse() {
    }

public Adresse(int adresse_ID, int vejnr, String vejnavn, String land, int postnr) {
    this.adresse_ID = adresse_ID;
    this.vejnr = vejnr;
    this.vejnavn = vejnavn;
    this.land = land;
    this.postnr = postnr;
    }

    public int getAdresse_ID() {
        return adresse_ID;
    }

    public int getVejnr() {
        return vejnr;
    }

    public String getVejnavn() {
        return vejnavn;
    }

    public String getLand() {
        return land;
    }

    public int getPostnr() {
        return postnr;
    }

    public void setAdresse_ID(int adresse_ID) {
        this.adresse_ID = adresse_ID;
    }

    public void setVejnr(int vejnr) {
        this.vejnr = vejnr;
    }

    public void setVejnavn(String vejnavn) {
        this.vejnavn = vejnavn;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setPostnr(int postnr) {
        this.postnr = postnr;
    }
}