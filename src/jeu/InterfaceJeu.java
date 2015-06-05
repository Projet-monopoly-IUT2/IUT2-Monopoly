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
   
    public void afficherProprietes(ArrayList<CarreauPropriete> c) {
        throw new UnsupportedOperationException();
    }

    public void AfficherLoyer(String infos) {
        System.out.println(infos);
    }
}
