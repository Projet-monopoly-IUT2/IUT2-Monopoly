package jeu;

import java.util.ArrayList;
import java.util.Scanner;

public class InterfaceJeu {

    public Monopoly monopoly;

    public void afficherAchat(CarreauPropriete c, Joueur j) {
        System.out.println("Joueur : " + j.getNomjoueur() + "\n  cash : " + j.getCash());
        System.out.println("Propriete : " + c.getNomCarreau() + "\n case : " + c.getNumero() +"\n Prix : " + c.getMontantAchat());
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
    
    public boolean ChoixAchat(Joueur j, CarreauPropriete c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez vous acheter cette propriete (1 - oui/ 2 - non)?");
        int rep = sc.nextInt();
        return rep == 1;
    }

}
