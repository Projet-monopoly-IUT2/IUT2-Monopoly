package jeu;

import java.util.ArrayList;

public class Joueur {

    private String nomJoueur;
    private int cash = 1500;
    private boolean enPrison;
    private Monopoly monopoly;
    private ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
    private ArrayList<Gare> gares = new ArrayList<Gare>();
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietesAConstruire = new ArrayList<ProprieteAConstruire>();
    
    public Joueur( Monopoly monopoly) {
        this.monopoly = monopoly;
        enPrison = false;
        
    }
    
    public int getCash() {
        return this.cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * @return numero du carreau courant du joueur
     */
    public int getPositionCourante() {
        return positionCourante.getNumero();
    }

    public void deplacer(Carreau c) {
        this.setCarreau(c);
    }
    
    public void deplacer(int numc) {
        setCarreau(monopoly.getCarreau(numc));
    }

    public void setCarreau(Carreau c) {
        positionCourante = c;
    }

    public void setNomJoueur(String nomJ) {
        nomJoueur = nomJ;
    }

    public void setEnPrison(boolean enPrison) {
        this.enPrison = enPrison;
    }
    
    public boolean isEnPrison() {
        return enPrison;
    }

    public void achat(Carreau c) {
        throw new UnsupportedOperationException();
    }

    /**
     * nom + cash + position du joueur
     */
    public String getInfosJoueur() {
        String nomPositionCourante = monopoly.getCarreau(getPositionCourante()).getNomCarreau();
        return getNomjoueur() + " - " + getCash() + "€ - Position : " + nomPositionCourante + " (case " + getPositionCourante() + ")";
    }          // plop - 1200€ - Position : Rue de la Paix (case 42)


    public String getInfosProprietes() {
        throw new UnsupportedOperationException();
    }

    public ArrayList<CarreauPropriete> getProprietes() {
        
        ArrayList<CarreauPropriete> proprietes = new ArrayList<>();
        for (CarreauPropriete c : compagnies) {
            proprietes.add(c);
    }
        for (CarreauPropriete c : proprietesAConstruire) {
            proprietes.add(c);
        }
        for (CarreauPropriete c : gares) {
            proprietes.add(c);

        }   
        //On récupère toutes les propriétés du joueur
        return proprietes;
  
    }

    public String getNomjoueur() {
        return nomJoueur;
    }

    public int getNbGare() {
        return gares.size();
    }

    public int calculCashApresOperation(int argent) {
        throw new UnsupportedOperationException();
        // + ou - ?????
    }
    
    public void retirerCash(int montant) {
        setCash(getCash()-montant);
    }
    
    public void ajouterCash(int montant) {
        setCash(getCash()+montant);
    }

    public void recevoirLoyer(int l) {
        setCash(getCash() + l);
    }

    public void payerLoyer(int l) {
        setCash(getCash() - l);
    }
}
