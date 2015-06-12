package jeu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Joueur {

    private String nomJoueur;
    private int cash = 1500;
    private boolean enPrison = false;
    private int toursEnPrison = 0;
    private boolean carteSortiePrison;
    private Monopoly monopoly;
    private ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
    private ArrayList<Gare> gares = new ArrayList<Gare>();
    private Carreau positionCourante;
    private ArrayList<ProprieteAConstruire> proprietesAConstruire = new ArrayList<ProprieteAConstruire>();
    private boolean faillite = false;
    private ArrayList<CarteGain> cartesSortiePrison = new ArrayList<>();
    
    public Joueur( Monopoly monopoly) {
        this.monopoly = monopoly;
        
        
    }

    /**
     * 
     * @return Le nom du joueur 
     */
    public String getNomJoueur() {
        return nomJoueur;
    }
    
    
   
    
    /**
     * 
     * @return L'argent restant au joueur 
     */
    public int getCash() {
        return this.cash;
    }
    /**
     * /!\ Utiliser les méthodes ajouterCash et retirerCash autant que possible /!\
     * @param cash nouveau solde du joueur
     */
    public void setCash(int cash) {
        this.cash = cash;
    }

    /**
     * @return numero du carreau courant du joueur
     */
    public int getPositionCourante() {
        if (this.positionCourante != null) {
        return positionCourante.getNumero(); }
        return 0;
    }
    
    /**
     * Déplace au carreau dont le numéro est passé en paramètre.
     * @param numc numéro de la case sur laquelle se déplacer.
     */
    public void deplacer(int numc) {
      
        setCarreau(monopoly.getCarreau(numc));
    }
    
    /**
     * 
     * @param c nouvelle position du joueur 
     */
    public void setCarreau(Carreau c) {
        positionCourante = c;
    }
    
    /**
     * 
     * @param nomJ nouveau nom du joueur
     */
    public void setNomJoueur(String nomJ) {
        nomJoueur = nomJ;
    }
    
    /**
     * Emprisonne le joueur et réinitialise son compteur de jours en prison.
     * @param enPrison vrai si le joueur doit être emprisonné, faux s'il doit être libéré.
     */
    public void setEnPrison(boolean enPrison) {
        setToursEnPrison(0);
        this.enPrison = enPrison;
    }
    
    /**
     * 
     * @return vrai si le joueur est en prison 
     */
    public boolean isEnPrison() {
        return enPrison;
    }

    /**
     * 
     * @return Le nombre de tours passés en prison. 
     */
    public int getToursEnPrison() {
        return toursEnPrison;
    }

    /**
     * 
     * @param toursEnPrison nouveau nombre de tours en prison. 
     */
    public void setToursEnPrison(int toursEnPrison) {
        this.toursEnPrison = toursEnPrison;
    }

    /**
     * 
     * @param toursEnPrison Nombre de tours en prison à ajouter. 
     */
    public void addToursEnPrison(int toursEnPrison) {
        this.toursEnPrison += toursEnPrison;
    }

    /**
     * 
     * @return vrai si le joueur a une carte "sortie de prison", faux sinon 
     */
    public boolean isCarteSortiePrison() {
        return carteSortiePrison;
    }

    /**
     * 
     * @param carteSortiePrison vrai si le joueur obtient une carte, faux si on la lui retire. 
     */
    public void setCarteSortiePrison(boolean carteSortiePrison) {
        this.carteSortiePrison = carteSortiePrison;
    }

    /**
     * Ajoute la carte Sortie de Prison que le joueur à tiré à sa collection
     * @param c 
     */
    public void ajouterCarteSortiePrison(CarteGain c){
        addCarteSortiePrison(c);
    }
    
    private void addCarteSortiePrison(CarteGain c){
        cartesSortiePrison.add(c);
    }
    /**
     * retire la dernière carte de la liste des Cartes de Sortie de Prison que le joueur possède
     */
    public void retirerCarteSortiePrison(){
        removeCarteSortiePrison();
    }
    
    private void removeCarteSortiePrison(){
        cartesSortiePrison.remove(cartesSortiePrison.size() - 1);
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
     * @return vrai si le paiement a été effectué (false = faillite ou pas assez d'argent)
     */
    public boolean retirerCash(int montant) {
        if (getCash()-montant > 0) {    
            setCash(getCash()-montant);
            return true;
        } else {
            return false;
        }   
    }
    
    /**
     * 
     * @param montant
     * @return vrai si le joueur serait en faillite après le paiement
     */
    public boolean testFaillite(int montant) {
        return (getCash()-montant <= 0);
    }
    
    /**
     * Effectue une rentrée d'argent pour le joueur
     * @param montant le montant du crédit
     */
    public void ajouterCash(int montant) {
        setCash(getCash()+montant);
    }

    /**
     * Recoit le loyer d'u montant l.
     * @param l Montant du loyer à recevoir
     */
    public void recevoirLoyer(int l) {
        ajouterCash(l);
    }

    /**
     * Paie le loyer.
     * @param l Montant du loyer à payer
     * @throws Faillite Si le joueur ne peut pas payer son loyer.
     */
    public void payerLoyer(int l) throws Faillite {
        boolean paiement = retirerCash(l);
        if (!paiement) {
            throw new Faillite();
        }
    }
    
    /**
     * Déclare le joueur comme étant en faillite et abandonne toutes ses propriétés.
     */
    public void setFaillite() {
        this.faillite = true;
        // Abandonner toutes les propriétés
        for (CarreauPropriete c : getProprietes()) {
            if (c instanceof ProprieteAConstruire) {
                ((ProprieteAConstruire) c).resetPropriete();
            }
            c.resetPropriete();
        }
    }
   
    
    /**
     * 
     * @return Vrai si le joueur est en faillite.
     */
    public boolean enFaillite() {
        return faillite;
    }
    /**
     * 
     * @return nombre de compagnies appartenant au joueur.
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
