package com.example.bilabonnement_dk.model;

import java.time.LocalDate;

public class Bil {
    private int bil_ID;
    private LocalDate indkoebdato;
    private String vognnr;
    private String stelnr;
    private String udstyrsniveau;
    private double staalpris;
    private double regafg;
    private int co2udl;
    private BilType Biltype;
    private String maerke;
    private String model;

    public Bil() {
    }

    public Bil(int bil_ID, LocalDate indkoebdato, String vognnr, String stelnr, String udstyrsniveau, double staalpris, double regafg, int co2udl, BilType BilType, String maerke, String model) {
        this.bil_ID = bil_ID;
        this.indkoebdato = indkoebdato;
        this.vognnr = vognnr;
        this.stelnr = stelnr;
        this.udstyrsniveau = udstyrsniveau;
        this.staalpris = staalpris;
        this.regafg = regafg;
        this.co2udl = co2udl;
        this.Biltype = BilType;
        this.maerke = maerke;
        this.model = model;
    }

    public int getBil_ID() {
        return bil_ID;
    }

    public void setBil_ID(int bil_ID) {
        this.bil_ID = bil_ID;
    }

    public LocalDate getIndkoebdato() {
        return indkoebdato;
    }

    public void setIndkoebdato(LocalDate indkoebdato) {
        this.indkoebdato = indkoebdato;
    }

    public String getVognnr() {
        return vognnr;
    }

    public void setVognnr(String vognnr) {
        this.vognnr = vognnr;
    }

    public String getStelnr() {
        return stelnr;
    }

    public void setStelnr(String stelnr) {
        this.stelnr = stelnr;
    }

    public String getUdstyrsniveau() {
        return udstyrsniveau;
    }

    public void setUdstyrsniveau(String udstyrsniveau) {
        this.udstyrsniveau = udstyrsniveau;
    }

    public double getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(double staalpris) {
        this.staalpris = staalpris;
    }

    public double getRegafg() {
        return regafg;
    }

    public void setRegafg(double regafg) {
        this.regafg = regafg;
    }

    public int getCo2udl() {
        return co2udl;
    }

    public void setCo2udl(int co2udl) {
        this.co2udl = co2udl;
    }

    public BilType getBilType() {
        return Biltype;
    }

    public void setBilType(BilType Biltype) {
        this.Biltype = Biltype;
    }

    public String getMaerke() {
        return maerke;
    }

    public void setMaerke(String maerke) {
        this.maerke = maerke;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}