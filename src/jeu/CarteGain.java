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
    
    public CarteGain(Monopoly mono, int numero, String message, int montant) {
        super(mono,numero,message);
        this.montant = montant;
    }
    //Cas particuliers :
    public CarteGain(Monopoly mono, int numero, String message, int montantParMaison, int montantParHotel) {
        super(mono, numero, message);
        this.montantParMaison = montantParMaison;
        this.montantParHotel = montantParHotel;
    }

    public int getMontant() {
        return montant;
    }
    
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
