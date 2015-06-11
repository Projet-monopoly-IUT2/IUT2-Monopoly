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

    /**
     * Déclenche l'action correspondant à la carte
     *
     * @param j Le joueur courant
     * @throws Faillite Si l'action met le joueur en faillite.
     */
    public abstract void action(Joueur j) throws Faillite;

    /**
     *
     * @return Le message inscrit sur la carte.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return L'instance de monopoly pour cette partie.
     */
    public Monopoly getMonopoly() {
        return monopoly;
    }

    /**
     * 
     * @return Le numéro de la carte (arbitraire) 
     */
    public int getNumero() {
        return numero;
    }
}
