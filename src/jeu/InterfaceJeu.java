package jeu;

import java.util.ArrayList;

public class InterfaceJeu {

    public Monopoly monopoly;

    public void afficherAchat(Carreau c, Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void afficherJoueur(Joueur j) {
       System.out.println("Joueur : " + j.getInfosJoueur());
    }
   
    public void afficherResDEs( int res) {
       System.out.println(" Resultat des dès : " + res); 
    }
    public void afficherProprietes(ArrayList<CarreauPropriete> cs) {
        for (CarreauPropriete c : cs) {
            System.out.println("Propriété : " + c.getInfosCarreau() + " Groupe : " 
            );
        }
    }

    public void AfficherLoyer(String infos) {
        System.out.println(infos);
    }
    
    public void afficher(String infos) {
        System.out.println(infos);
    }
}
