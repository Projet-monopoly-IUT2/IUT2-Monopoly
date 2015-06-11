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
public class CarteMouvement extends Carte {

    private int caseCible;
    private boolean relatif;
    /**
     * 
     * @param mono instance de monopoly
     * @param numero numéro de la carte
     * @param message massage inscrit sur la carte
     * @param caseCible exacly what it says on the can
     * @param relatif vrai si la caseCible est relative au joueur
     */
    public CarteMouvement(Monopoly mono, int numero, String message, int caseCible, boolean relatif) {
        super(mono,numero,message);
        this.caseCible = caseCible;
        this.relatif = relatif;
    }

    public int getCaseCible() {
        return caseCible;
    }
    
    @Override
    public void action(Joueur j) throws Faillite {
        super.getMonopoly().interfaceJeu.messageCarte(this);
        
        if (relatif)
            caseCible = (j.getPositionCourante()+caseCible-1)%40 +1;
        
        if (caseCible < j.getPositionCourante() && caseCible != 1) // Passage par la case départ
            j.ajouterCash(200);
        
        j.deplacer(caseCible);
        super.getMonopoly().getCarreau(j.getPositionCourante()).action(j);
    } 
}
