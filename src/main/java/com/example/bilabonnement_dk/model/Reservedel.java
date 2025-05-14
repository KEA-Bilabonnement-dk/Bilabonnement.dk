package com.example.bilabonnement_dk.model;

public class Reservedel {
    private int reservedel_ID;
    private String type;
    private double pris;

    public Reservedel() {}

    public Reservedel(int reservedel_ID, String type, double pris) {
        this.reservedel_ID = reservedel_ID;
        this.type = type;
        this.pris = pris;

    }

    public int getReservedel_ID() {
        return reservedel_ID;
    }

    public void setReservedel_id(int reservedel_ID) {
        this.reservedel_ID = reservedel_ID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPris() {
        return pris;
    }

    public void setPris(double pris) {
        this.pris = pris;
    }
}
