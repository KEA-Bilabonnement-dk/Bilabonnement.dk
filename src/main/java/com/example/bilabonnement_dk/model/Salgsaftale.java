package com.example.bilabonnement_dk.model;
import java.util.Date;

public class Salgsaftale {

    private int salgs_ID;
    private double salgspris;
    private Date leveringsdato;
    private Adresse adresse;
    private Medarbejder medarbejder;
    private Bil bil;
    private Kunde kunde;

    public Salgsaftale(){}

    public Salgsaftale(int salgs_ID, double salgspris, Date leveringsdato, Adresse adresse, Medarbejder medarbejder, Bil bil, Kunde kunde){
        this.salgs_ID = salgs_ID;
        this.salgspris = salgspris;
        this.leveringsdato = leveringsdato;
        this.adresse = adresse;
        this.medarbejder = medarbejder;
        this.bil = bil;
        this.kunde = kunde;
    }

    public int getSalgs_ID() {
        return salgs_ID;
    }

    public double getSalgspris() {
        return salgspris;
    }

    public Date getLeveringsdato() {
        return leveringsdato;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public Bil getBil() {
        return bil;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setSalgs_ID(int salgs_ID) {
        this.salgs_ID = salgs_ID;
    }

    public void setSalgspris(double salgspris) {
        this.salgspris = salgspris;
    }

    public void setLeveringsdato(Date leveringsdato) {
        this.leveringsdato = leveringsdato;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

}
