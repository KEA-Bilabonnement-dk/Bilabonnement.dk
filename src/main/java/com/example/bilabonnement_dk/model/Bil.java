package com.example.bilabonnement_dk.model;

import java.time.LocalDate;

public class Bil {
    private int bil_ID;
    private LocalDate indkoebdato;
    private String vognnr;
    private String stelnr;
    private String udstyrsniv;
    private String staalpris;
    private String regafg;
    private String co2udl;
    private BilType type;
    private String maerke;
    private String model;

    private Bil() {
    }

    public Bil(int bil_ID, LocalDate indkoebdato, String vognnr, String stelnr, String udstyrsniv, String staalpris, String regafg, String co2udl, BilType type, String maerke, String model) {
        this.bil_ID = bil_ID;
        this.indkoebdato = indkoebdato;
        this.vognnr = vognnr;
        this.stelnr = stelnr;
        this.udstyrsniv = udstyrsniv;
        this.staalpris = staalpris;
        this.regafg = regafg;
        this.co2udl = co2udl;
        this.type = type;
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

    public String getUdstyrsniv() {
        return udstyrsniv;
    }

    public void setUdstyrsniv(String udstyrsniv) {
        this.udstyrsniv = udstyrsniv;
    }

    public String getStaalpris() {
        return staalpris;
    }

    public void setStaalpris(String staalpris) {
        this.staalpris = staalpris;
    }

    public String getRegafg() {
        return regafg;
    }

    public void setRegafg(String regafg) {
        this.regafg = regafg;
    }

    public String getCo2udl() {
        return co2udl;
    }

    public void setCo2udl(String co2udl) {
        this.co2udl = co2udl;
    }

    public BilType getType() {
        return type;
    }

    public void setType(BilType type) {
        this.type = type;
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