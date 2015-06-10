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
public class CarteMouvement extends Cartes {

    private Carreau caseCible;
            
    public CarteMouvement(Monopoly mono) {
        super(mono);
    }

    public Carreau getCaseCible() {
        return caseCible;
    }

    public void setCaseCible(Carreau caseCible) {
        this.caseCible = caseCible;
    }
    
    public void action(Joueur j){
        
    }
    
    
}
