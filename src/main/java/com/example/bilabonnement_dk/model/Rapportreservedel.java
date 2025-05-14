package com.example.bilabonnement_dk.model;

public class Rapportreservedel {
    private int antalReservedele_ID;
    private Reservedel reservedel;
    private Skaderapport skaderapport;
    private int antal;

    public Rapportreservedel() {
    }

    public Rapportreservedel(int antalReservedele_ID, Reservedel reservedel, Skaderapport skaderapport, int antal) {
        this.antalReservedele_ID = antalReservedele_ID;
        this.reservedel = reservedel;
        this.skaderapport = skaderapport;
        this.antal = antal;
    }

    public int getAntalReservedele_ID() {
        return antalReservedele_ID;
    }

    public void setAntalReservedele_ID(int antalReservedele_ID) {
        this.antalReservedele_ID = antalReservedele_ID;
    }

    public Reservedel getReservedel() {
        return reservedel;
    }

    public void setReservedel(Reservedel reservedel) {
        this.reservedel = reservedel;
    }

    public Skaderapport getSkaderapport() {
        return skaderapport;
    }

    public void setSkaderapport(Skaderapport skaderapport) {
        this.skaderapport = skaderapport;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }
}