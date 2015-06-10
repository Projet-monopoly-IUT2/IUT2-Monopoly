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
    
    private String casParticuler = null;
    private int montantParMaison;
    private int montantParHotel;
    
    public CarteGain(Monopoly mono, int numero, String message, int montant) {
        super(mono,numero,message);
        this.montant = montant;
    }
    //Cas particuliers :
    public CarteGain(Monopoly mono, int numero, String message, int montantParMaison, int montantParHotel) {
        super(mono, numero, message);
        this.casParticuler = "prixParConstruction";
        this.montantParMaison = montantParMaison;
        this.montantParHotel = montantParHotel;
    }

    public int getMontant() {
        return montant;
    }
    
    public void action(Joueur j) {
        if (casParticuler.equals("prixParConstruction")) {
            for (CarreauPropriete c : j.getProprietes()) {
                if (c instanceof ProprieteAConstruire) {
                    montant += ((ProprieteAConstruire) c).getNbMaisonsC() * montantParMaison;
                    montant += ((ProprieteAConstruire) c).getNbHotelsC() * montantParHotel;
                }
            }
        } else {
            super.getMonopoly().interfaceJeu.messageCarte(this);
            j.ajouterCash(montant);
        }
    }
    
}
