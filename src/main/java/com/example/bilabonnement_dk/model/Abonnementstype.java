package com.example.bilabonnement_dk.model;

public enum Abonnementstype {
    LIMITED(false),
    UNLIMITED(true);

    private final boolean kanForhaandsaelges;

    Abonnementstype(boolean kanForhaandsaelges)
    {
        this.kanForhaandsaelges = kanForhaandsaelges;
    }

    public boolean isKanForhaandsaelges()
    {
        return kanForhaandsaelges;
    }
}
