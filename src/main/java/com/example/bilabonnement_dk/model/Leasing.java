package com.example.bilabonnement_dk.model;

import java.time.LocalDate;

public class Leasing
{
    private int leasing_ID;
    private double pris;
    private LocalDate startdato;
    private LocalDate slutdato;
    private Kunde kunde;
    private Medarbejder medarbejder;
    private Bil bil;
    private Abonnementstype abonnementstype;

    public Leasing()
    {
    }

    public Leasing(int leasing_ID, double pris, LocalDate startdato, LocalDate slutdato, Kunde kunde, Medarbejder medarbejder, Bil bil, Abonnementstype abonnementstype) {
        this.leasing_ID = leasing_ID;
        this.pris = pris;
        this.startdato = startdato;
        this.slutdato = slutdato;
        this.kunde = kunde;
        this.medarbejder = medarbejder;
        this.bil = bil;
        this.abonnementstype = abonnementstype;
    }

    public int getLeasing_ID() {
        return leasing_ID;
    }

    public void setLeasing_ID(int leasing_ID) {
        this.leasing_ID = leasing_ID;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
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

    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
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
}
