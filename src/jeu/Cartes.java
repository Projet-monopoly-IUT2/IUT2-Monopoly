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
public abstract class Cartes {
    private String description;
    private String type;
    private Monopoly monopoly;
    private int numero;
    
    public Cartes(Monopoly mono) {
        monopoly = mono;
    }
    
    public void action(Joueur j) {
        
    }
    
}
