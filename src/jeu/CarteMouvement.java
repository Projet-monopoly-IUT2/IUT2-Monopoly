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

    /**
     * 
     * @return Case sur laquelle pointe la carte. 
     */
    public int getCaseCible() {
        return caseCible;
    }
    
    /**
     * Déplace un joueur et éxécute l'action de la case cible.
     * @param j Joueur courant
     * @throws Faillite Si le joueur est en faillite suite à l'action.
     */
    @Override
    public void action(Joueur j) {        
        if (estRelatif()){
                int nouvPos = j.getPositionCourante() + getCaseCible();
                if(nouvPos <= 0 ){
                    nouvPos = nouvPos +40;
                    j.setCarreau(this.getMonopoly().getCarreau(nouvPos));
                }
                else {
                    j.setCarreau(this.getMonopoly().getCarreau(nouvPos));                  
                }
            } 
        
        else {
                if (getCaseCible() < j.getPositionCourante() && !(getCaseCible() == 1) && getCaseCible()!=11) {
                    //Si le n° de case après le déplacement est < à celui avant, on est passé par la case départ. Le cas ou l'on tombe directement sur la case départ est déjà géré.
                    j.ajouterCash(200);
                }
                if (getCaseCible()==11){
                    j.setEnPrison(true);
                }
                j.deplacer(getCaseCible());
            }
    }
    
    public boolean estRelatif(){
        return relatif;
    }
    
}
