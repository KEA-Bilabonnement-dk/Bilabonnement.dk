package com.example.bilabonnement_dk.model;

public class Reservedel {
    private int reservedel_id;
    private String type;
    private double pris;

    public Reservedel() {}

    public Reservedel(int reservedel_id, String type, double pris) {
        this.reservedel_id = reservedel_id;
        this.type = type;
        this.pris = pris;

    }

    public int getReservedel_id() {
        return reservedel_id;
    }

    public void setReservedel_id(int reservedel_id) {
        this.reservedel_id = reservedel_id;
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
