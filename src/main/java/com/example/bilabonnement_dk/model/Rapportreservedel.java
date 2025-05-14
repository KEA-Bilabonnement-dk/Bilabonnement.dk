package com.example.bilabonnement_dk.model;

public class Rapportreservedel {
    private int antalReservedeleID;
    private int reservedelID;
    private int skaderapportID;
    private int antal;

    public Rapportreservedel() {}

    public Rapportreservedel(int antalReservedeleID, int reservedelID, int skaderapportID, int antal) {
        this.antalReservedeleID = antalReservedeleID;
        this.reservedelID = reservedelID;
        this.skaderapportID = skaderapportID;
        this.antal = antal;

    }

    public int getAntalReservedeleID() {
        return antalReservedeleID;
    }

    public void setAntalReservedeleID(int antalReservedeleID) {
        this.antalReservedeleID = antalReservedeleID;
    }

    public int getReservedelID() {
        return reservedelID;
    }

    public void setReservedelID(int reservedelID) {
        this.reservedelID = reservedelID;
    }

    public int getSkaderapportID() {
        return skaderapportID;
    }

    public void setSkaderapportID(int skaderapportID) {
        this.skaderapportID = skaderapportID;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }
}
