package jeu;

import java.util.ArrayList;

public class Groupe {

    private int prixAchatMaison;
    private int prixAchatHotel;
    private CouleurPropriete couleur;
    private ArrayList<ProprieteAConstruire> proprietes = new ArrayList<ProprieteAConstruire>();

    public Groupe(CouleurPropriete c) {
        couleur = c;
    }
    
    public CouleurPropriete getCouleur() {
        return couleur;
    }
}
