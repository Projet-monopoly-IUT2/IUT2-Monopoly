package jeu;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProprieteAConstruire extends CarreauPropriete {

    private int nbMaisons = 0;
    private int nbHotels = 0;
    private int prixMaison;
    private int prixHotel;
    private int nbMaisonsC;
    private int nbHotelsC;
    private LinkedList<Integer> loyerParMaison;
    private Groupe groupePropriete;

    public int CalculMontantLoyer(ArrayList<Carreau> proprietes) {
        throw new UnsupportedOperationException();
    }

    public void construire() {
        throw new UnsupportedOperationException();
    }

    public int getPrixHotel() {
        return this.prixHotel;
    }

    public void setPrixHotel(int prixHotel) {
        this.prixHotel = prixHotel;
    }

    public int getPrixMaison() {
        return this.prixMaison;
    }

    public void setPrixMaison(int prixMaison) {
        this.prixMaison = prixMaison;
    }

    public void setLoyerParMaison(LinkedList<Integer> loyerParMaison) {
        this.loyerParMaison = loyerParMaison;
    }

    public int calculLoyer() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void action(Joueur j) {
        super.action(j);
        if (getProprietaire() == j) {
            construire();
        }
    }

    public void setGroupe(Groupe g) {
        groupePropriete = g;
    }

    public int getNbMaisons() {
        return nbMaisons;
    }

    public int getNbHotels() {
        return nbHotels;
    }
    
    
}
