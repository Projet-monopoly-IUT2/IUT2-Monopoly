/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Ui;

import jeu.*;
/**
 *
 * @author gorkat
 */
public class Interface {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Monopoly monopoly = new Monopoly("/users/info/etu-s2/gorkat/IUT2-Monopoly/src/data/data.txt");
        
        monopoly.initialiserPartie();
    }
    
}
