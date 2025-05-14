package com.example.bilabonnement_dk.model;

public class Skaderapport {
    private int skaderapport_ID;
    private double pris;
    private Leasing leasing;
    private Medarbejder medarbejder;

    public Skaderapport()
    {
    }

    public Skaderapport(int skaderapport_ID, double pris, Leasing leasing, Medarbejder medarbejder) {
        this.skaderapport_ID = skaderapport_ID;
        this.pris = pris;
        this.leasing = leasing;
        this.medarbejder = medarbejder;
    }

    public int getSkaderapport_ID() {
        return skaderapport_ID;
    }

    public void setSkaderapport_ID(int skaderapport_ID) {
        this.skaderapport_ID = skaderapport_ID;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }

    public Leasing getLeasing() {
        return leasing;
    }

    public void setLeasing(Leasing leasing) {
        this.leasing = leasing;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public void setMedarbejder(Medarbejder medarbejder) {
        this.medarbejder = medarbejder;
    }
}
