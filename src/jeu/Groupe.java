package jeu;

import java.util.ArrayList;

public class Groupe {

    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes = new ArrayList<>();

    public Groupe(CouleurPropriete c) {
        couleur = c;
    }
    
    /**
     * @return CouleurPropriete associée au groupe
     */
    public CouleurPropriete getCouleur() {
        return couleur;
    }
    
    /**
     * @return propriétés du groupe 
     */
    public ArrayList<ProprieteAConstruire> getProprietes() {
        return proprietes;
    }
}
