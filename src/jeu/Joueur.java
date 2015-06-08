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

    public String getNomJoueur() {
        return nomJoueur;
    }
    
    public int getCash() {
        return this.cash;
    }
    /**
     * /!\ Utiliser les méthodes ajouterCash et retirerCash autant que possible /!\
     * @param cash nouveau solde du joueur
     */
    private void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * @return numero du carreau courant du joueur
     */
    public int getPositionCourante() {
        return positionCourante.getNumero();
    }

    /**
     * Déplace au carreau passé en paramètre
     * @param c carreau sur lequel se déplacer
     */
    public void deplacer(Carreau c) {
        this.setCarreau(c);
    }
    
    /**
     * Déplace au carreau dont le numéro est passé en paramètre.
     * @param numc numéro de la case sur laquelle se déplacer.
     */
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

    /**
     *  ???
     * @param c 
     */
    //L'achat n'est-il pas lancé par le carreauPropriete via action() ? Merci de compléter la doc.
    public void achat(Carreau c) {
        throw new UnsupportedOperationException();
    }

 

    /**
     * Retourne la liste des propriétés détenues par le joueur.
     * @return liste de propriétés détenues par le joueur
     */
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
    
    /**
     * Retourne la liste de propriétés d'une certane couleur
     * @param couleur la couleur des propriétes voulue
     * @return liste des propriétés de couleur couleur
     */
    public ArrayList<ProprieteAConstruire> getProprietes(CouleurPropriete couleur) {
        ArrayList<ProprieteAConstruire> res = new ArrayList<>();
        for (ProprieteAConstruire p: proprietesAConstruire) {
            if (p.getGroupePropriete().getCouleur() == couleur)
                res.add(p);
        }
        return res;
    }


    
    /**
     * Retourne le nombre de gares
     * @return nombre de gares
     */
    public int getNbGare() {
        return gares.size();
    }
    
    /**
     * Simule le retrait d'argent.
     * @param argent somme à retirer
     * @return cash après opération de retrtait
     */
    public int calculCashApresOperation(int argent) {
        return cash - argent;
    }
    
    /**
     * Effectue un retrait sur le cash du joueur
     * @param montant montant du retrait
     */
    public void retirerCash(int montant) {
        setCash(getCash()-montant);
    }
    
    /**
     * Effectue une rentrée d'argent pour le joueur
     * @param montant le montant du crédit
     */
    public void ajouterCash(int montant) {
        setCash(getCash()+montant);
    }

    public void recevoirLoyer(int l) {
        ajouterCash(l);
    }

    public void payerLoyer(int l) {
        retirerCash(l);
    }
    
    /**
     * Retourne le nombre de compagnies
     * @return nombre de compagnies
     */
    public int getNbCompagnies () {
        return compagnies.size();
    }
    
    /**
     * Ajouter une propriété a la liste de propriétés correspondante (gare, compagnie,
     * propriété à construire). 
     * @param c propriété à ajouter
     */
    public void setPropriete(CarreauPropriete c){
        if (c instanceof ProprieteAConstruire)
            proprietesAConstruire.add((ProprieteAConstruire)c);
        else if (c instanceof Gare)
            gares.add((Gare)c);
        else if (c instanceof Compagnie)
            compagnies.add((Compagnie)c);

    }
}
