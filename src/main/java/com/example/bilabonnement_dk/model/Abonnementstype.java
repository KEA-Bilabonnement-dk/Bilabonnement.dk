package com.example.bilabonnement_dk.model;

public class Abonnementstype {
    private int abonnementstype_ID;
<<<<<<< HEAD
    private Enum type;
=======
    private Type type;
>>>>>>> 584d4486cbcb855ade1b883444967f8638f5a7c1
    private boolean kanForhaandSealges;

    public Abonnementstype() {
    }

<<<<<<< HEAD
    public Abonnementstype(int abonnementstype_ID, Enum type, boolean kanForhaandSealges) {
=======
    public Abonnementstype(int abonnementstype_ID, Type type, boolean kanForhaandSealges) {
>>>>>>> 584d4486cbcb855ade1b883444967f8638f5a7c1
        this.abonnementstype_ID = abonnementstype_ID;
        this.type = type;
        this.kanForhaandSealges = kanForhaandSealges;
    }
<<<<<<< HEAD
    public int getAbonnementstype_ID() {
        return abonnementstype_ID;
=======
>>>>>>> 584d4486cbcb855ade1b883444967f8638f5a7c1

    public int getAbonnementstype_ID() {
        return abonnementstype_ID;
    }
<<<<<<< HEAD
=======

>>>>>>> 584d4486cbcb855ade1b883444967f8638f5a7c1
    public void setAbonnementstype_ID(int abonnementstype_ID) {
        this.abonnementstype_ID = abonnementstype_ID;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isKanForhaandSealges() {
        return kanForhaandSealges;
    }

    public void setKanForhaandSealges(boolean kanForhaandSealges) {
        this.kanForhaandSealges = kanForhaandSealges;
    }
}
