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
    /**
     * Construit une maison sur cette propriété
     */
    // Nème duplicata de construire... ménage à faire.
    public void construire(Joueur j) {
        boolean Sortie = false;
        int numCarreau = 0;
        int NbMaisonMonopoly= this.getMonopoly().getNbMaisons();
        int NbHotelsMonopoly=this.getMonopoly().getNbHotels();
        
        CouleurPropriete couleur = groupePropriete.getCouleur();
        ArrayList<ProprieteAConstruire> proprietes = j.getProprietes(couleur);
        while(!Sortie){    
            if (proprietes.size()==groupePropriete.getProprietes().size()){
                 ProprieteAConstruire c = this.getMonopoly().possibiliteConstruire(j,proprietes);
                if (c==null){
                    Sortie = true;
                }
                else {
                    if(c.getNbMaisonsC()==4 && c.getNbHotelsC()==0){
                        NbMaisonMonopoly=NbMaisonMonopoly+4;
                        NbHotelsMonopoly--;
                        this.getMonopoly().setNbMaisons(NbMaisonMonopoly);
                        this.getMonopoly().setNbHotels(NbHotelsMonopoly);
                        c.setNbHotelsC(1);
                        c.setNbMaisonsC(0);
                        Sortie = true;
                    }
                    else if (c.getNbMaisonsC()<4 && c.getNbHotelsC()==0){
                        NbMaisonMonopoly--;
                        this.getMonopoly().setNbMaisons(NbMaisonMonopoly);
                        c.setNbMaisonsC(0);
                        Sortie = true;
                    }
                }
            }
            else {
                this.getMonopoly().getInterfaceJeu().MessageErreur(5);
                Sortie = true;
            }
        } 
    }
    public void setNbMaisonsC(int i) {
        nbMaisonsC = i;
    }
    public void setNbHotelsC(int i) {
        nbHotelsC = i;
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
    /**
     * Effectue les actions de base et propose de construire le cas échéant
     * @param j joueur courant
     */
    @Override
    public void action(Joueur j) {        

            
            super.action(j);
       
//       else if (getProprietaire() == j){
//             construire();
//        }
        
    }

    public void setGroupe(Groupe g) {
        groupePropriete = g;
        g.addPropriete(this);
        
    }
    /**
     * Retourne le nombre de maisons actuellement construites sur ce carreau
     * @return nombre de maisons construites
     */
    public int getNbMaisonsC() {
        return nbMaisonsC;
    }
    /**
     * Retourne le nombre d'hotels construites sur ce carreau
     * @return nombre d'hotels
     */
    public int getNbHotelsC() {
        return nbHotelsC;
    }
    /**
     * Calcule le loyer en fonction du nombre de maisons et du nombre de terrains
     * du groupe appartenant au proprio de cette case.
     * @param j joueur courant
     * @return montant du loyer
     */
    @Override
    public int calculLoyer(Joueur j) {
        int loyer;
        loyer = loyerParMaison.get(getNbMaisonsC());
        Joueur jproprio = super.getProprietaire();
        
        if (getNbMaisonsC() == 0 && getNbHotelsC() == 0) { // Éviter couteuse vérification si le terrain n'est pas nu
         
            int nbPropJoueur = jproprio.getProprietes(this.getGroupePropriete().getCouleur()).size();
            int nbPropDansGroupe = this.getGroupePropriete().getProprietes().size();
            if (nbPropJoueur == nbPropDansGroupe){
          
                loyer = loyer*2;
            }
        }
        
      
      int nouveauCash = j.calculCashApresOperation(loyer);
      super.getMonopoly().InfosLoyer(jproprio, loyer, nouveauCash);
      return loyer;
    }

}
