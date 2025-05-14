package com.example.bilabonnement_dk.model;

public class Kunde {
    private int kunde_id;
    private String kfornavn;
    private String kefternavn;
    private int ktelefonnummer;
    private String kmail;
    private String adresse_ID;

    public Kunde() {}

    public Kunde(int kunde_id, String kfornavn, String kefternavn, int ktelefonnummer, String kmail, String adresse_ID) {
        this.kunde_id = kunde_id;
        this.kfornavn = kfornavn;
        this.kefternavn = kefternavn;
        this.ktelefonnummer = ktelefonnummer;
        this.kmail = kmail;
        this.adresse_ID = adresse_ID;
    }
    public int getKunde_id() {
        return kunde_id;
    }
    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }
    public String getKfornavn() {
        return kfornavn;
    }
    public void setKfornavn(String kfornavn) {
        this.kfornavn = kfornavn;
    }
    public String getKfternavn() {
        return kefternavn;
    }
    public void setKfternavn(String kefternavn) {
        this.kefternavn = kefternavn;
    }
    public int getKtelefonnummer() {
        return ktelefonnummer;
    }
    public void setKtelefonnummer(int ktelefonnummer) {
        this.ktelefonnummer = ktelefonnummer;
    }
    public String getKmail() {
        return kmail;
    }
    public void setKmail(String kmail) {
        this.kmail = kmail;
    }
    public String getAdresse_ID() {
        return adresse_ID;
    }
    public void setAdresse_ID(String adresse_ID) {
        this.adresse_ID = adresse_ID;
    }

}
