package com.example.bilabonnement_dk.model;

import java.time.LocalDate;

public class Leasing {
    private int leasing_ID;
    private Kunde kunde;
    private Bil bil;
    private Abonnementstype abonnementstype;
    private LocalDate startdato;
    private LocalDate slutdato;
    private double pris;
    private Medarbejder medarbejder;
    private Adresse afhentningssted;
    private boolean afleveret;

    public Leasing() {
    }

    public Leasing(int leasing_ID, Kunde kunde, Bil bil, Abonnementstype abonnementstype, LocalDate startdato, LocalDate slutdato, double pris, Medarbejder medarbejder, Adresse afhentningssted, boolean afleveret) {
        this.leasing_ID = leasing_ID;
        this.kunde = kunde;
        this.bil = bil;
        this.abonnementstype = abonnementstype;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.pris = pris;
        this.medarbejder = medarbejder;
        this.afhentningssted = afhentningssted;
        this.afleveret = afleveret;
    }

    public int getLeasing_ID() {
        return leasing_ID;
    }

    public void setLeasing_ID(int leasing_ID) {
        this.leasing_ID = leasing_ID;
    }

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Bil getBil() {
        return bil;
    }

    public void setBil(Bil bil) {
        this.bil = bil;
    }

    public Abonnementstype getAbonnementstype() {
        return abonnementstype;
    }

    public void setAbonnementstype(Abonnementstype abonnementstype) {
        this.abonnementstype = abonnementstype;
    }

    public LocalDate getStartdato() {
        return startdato;
    }

    public void setStartdato(LocalDate startdato) {
        this.startdato = startdato;
    }

    public LocalDate getSlutdato() {
        return slutdato;
    }

    public void setSlutdato(LocalDate slutdato) {
        this.slutdato = slutdato;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }

    public Adresse getAfhentningssted() {
        return afhentningssted;
    }

    public void setAfhentningssted(Adresse afhentningssted) {
        this.afhentningssted = afhentningssted;
    }

    public boolean isAfleveret() {
        return afleveret;
    }

    public void setAfleveret(boolean afleveret) {
        this.afleveret = afleveret;
    }
}