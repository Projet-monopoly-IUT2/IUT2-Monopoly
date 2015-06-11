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
public abstract class Carte {
    private String description;
    private Monopoly monopoly;
    private int numero;
    
    /**
     * 
     * @param mono instance de monopoly utilisée
     * @param numero numéro de la carte
     * @param description message inscrit sur la carte
     */
    public Carte(Monopoly mono, int numero, String description) {
        monopoly = mono;
        this.description = description;
        this.numero = numero;
        this.description = description;
    }
    
    public abstract void action(Joueur j) throws Faillite ;

    public String getDescription() {
        return description;
    }

    public Monopoly getMonopoly() {
        return monopoly;
    }

    public int getNumero() {
        return numero;
    }   
}
