package jeu;

import java.util.ArrayList;
import java.util.LinkedList;

public class ProprieteAConstruire extends CarreauPropriete {

    private int prixMaison;
    private int prixHotel;
    private int nbMaisonsC = 0;
    private int nbHotelsC = 0;
    private LinkedList<Integer> loyerParMaison;
    private Groupe groupePropriete;

    public ProprieteAConstruire(Monopoly monopoly) {
        super(monopoly);
    }

    public void construire() {
        throw new UnsupportedOperationException();
    }

    public Groupe getGroupePropriete() {
        return groupePropriete;
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

    @Override
    public void action(Joueur j) {        
        if (getProprietaire() != j){
            super.action(j);
        }
        else {
            construire();
        }
    }

    public void setGroupe(Groupe g) {
        groupePropriete = g;
    }

    public int getNbMaisonsC() {
        return nbMaisonsC;
    }

    public int getNbHotelsC() {
        return nbHotelsC;
    }

    @Override
    public int calculLoyer(Joueur j) {
        int loyer;
        loyer = getNbMaisonsC() * getPrixMaison() + getNbHotelsC() + getPrixHotel();

        if (getNbMaisonsC() == 0 && getNbHotelsC() == 0) { // Éviter couteuse vérification si le terrain n'est pas nu
            int nbPropJoueur = j.getProprietes(this.getGroupePropriete().getCouleur()).size();
            int nbPropDansGroupe = this.getGroupePropriete().getProprietes().size();
            loyer = (nbPropJoueur == nbPropDansGroupe) ? loyer * 2 : loyer;
        }
        return loyer;
    }

}
