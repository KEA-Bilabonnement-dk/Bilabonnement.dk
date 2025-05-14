package com.example.bilabonnement_dk.model;

public class Abonnementstype {
    private String abonnementstype_ID;
    private Enum type;
    private boolean kanForhaandSealges;

    public Abonnementstype() {}

    public Abonnementstype(int abonnementstype_ID, Enum type, boolean kanForhaandSealges) {
        this.abonnementstype_ID = String.valueOf(abonnementstype_ID);
        this.type = type;
        this.kanForhaandSealges = kanForhaandSealges;
    }
    public String getAbonnementstype_ID() {
        return abonnementstype_ID;

    }
    public void setAbonnementstype_ID(String abonnementstype_ID) {
        this.abonnementstype_ID = abonnementstype_ID;
    }
    public Enum getType() {
        return type;
    }
    public void setType(Enum type) {
        this.type = type;
    }
    public boolean isKanForhaandSealges() {
        return kanForhaandSealges;
    }
    public void setKanForhaandSealges(boolean kanForhaandSealges) {
        this.kanForhaandSealges = kanForhaandSealges;
    }

}

