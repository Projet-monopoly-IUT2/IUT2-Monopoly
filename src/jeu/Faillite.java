/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jeu;

/**
 *
 * @author bethouxa
 */
public class Faillite extends Exception {

    /**
     * Creates a new instance of <code>Faillite</code> without detail message.
     */
    public Faillite() {
    }

    /**
     * Constructs an instance of <code>Faillite</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public Faillite(String msg) {
        super(msg);
    }
}
