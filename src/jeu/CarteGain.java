/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

/**
 *
 * @author laugiera
 */
public class CarteGain extends Carte {

    private int montant;
    
    private String casParticuler = new String();
    private int montantParMaison;
    private int montantParHotel;

    /**
     * 
     * @param mono Instance de monopoly de la partie en cours.
     * @param numero Numéro de la carte
     * @param message Message communiqué au joueur
     * @param montant Montant crédité sur le solde du joueur
     */
    public CarteGain(Monopoly mono, int numero, String message, int montant) {
        super(mono, numero, message);
        this.montant = montant;
    }

    /**
     * Gère le cas particuliers d'un montant en fonction du nombre de maisons/hotels.
     * @param mono Instance de monopoly de la partie en cours.
     * @param numero Numéro de la carte
     * @param message Message communiqué au joueur
     * @param montantParMaison
     * @param montantParHotel 
     */
    public CarteGain(Monopoly mono, int numero, String message, int montantParMaison, int montantParHotel) {
        super(mono, numero, message);
        this.montantParMaison = montantParMaison;
        this.montantParHotel = montantParHotel;
    }

    /**
     * 
     * @return le montant <em>crédité</em> sur le solde du joueur. 
     */
    public int getMontant() {
        return montant;
    }
    /**
     * 
     * @param j le joueur courant. 
     */
    @Override
    public void action(Joueur j) {
        if (casParticuler.equalsIgnoreCase("prixParConstruction")) {
            for (CarreauPropriete c : j.getProprietes()) {
                if (c instanceof ProprieteAConstruire) {
                    montant += ((ProprieteAConstruire) c).getNbMaisonsC() * montantParMaison;
                    montant += ((ProprieteAConstruire) c).getNbHotelsC() * montantParHotel;
                }
            }
            j.retirerCash(montant);
        }
        
        else if(montant>0){
            j.ajouterCash(montant);
        }
        
        else if (casParticuler.equalsIgnoreCase("anniversaire")){
            for(Joueur jo : this.getMonopoly().getJoueurs() ){
                    if(jo!=j){
                        jo.retirerCash(10);
                        montant += 10;
                    }
                }        
            j.ajouterCash(montant);
        }
        
        else{
            j.retirerCash(-montant);
        }
        
    }
    
    public void setcasParticuler(String casParticuler){
        this.casParticuler = casParticuler;
    }
    
}
