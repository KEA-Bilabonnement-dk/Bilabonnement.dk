package com.example.bilabonnement_dk.model;
import java.time.LocalDate;

public class Salgsaftale {

    private int salg_ID;
    private double salgspris;
    private LocalDate leveringsdato;
    private Adresse adresse;
    private Medarbejder medarbejder;
    private Bil bil;
    private Kunde kunde;



    public Salgsaftale(){}

    public Salgsaftale(int salg_ID, double salgspris, LocalDate leveringsdato, Adresse adresse, Medarbejder medarbejder, Bil bil, Kunde kunde){
        this.salg_ID = salg_ID;
        this.salgspris = salgspris;
        this.leveringsdato = leveringsdato;
        this.adresse = adresse;
        this.medarbejder = medarbejder;
        this.bil = bil;
        this.kunde = kunde;
    }

    public int getSalg_ID() {
        return salg_ID;
    }

    public double getSalgspris() {
        return salgspris;
    }

    public LocalDate getLeveringsdato() {
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

    public void setSalg_ID(int salg_ID) {
        this.salg_ID = salg_ID;
    }

    public void setSalgspris(double salgspris) {
        this.salgspris = salgspris;
    }

    public void setLeveringsdato(LocalDate leveringsdato) {
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
