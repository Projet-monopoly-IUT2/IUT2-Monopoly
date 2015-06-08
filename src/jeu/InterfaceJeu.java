package jeu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class InterfaceJeu {


    private Monopoly monopoly;
    
    public InterfaceJeu (Monopoly mono) {
        monopoly = mono;
    }

    public void afficherAchat(CarreauPropriete c, Joueur j) {
        System.out.println("Joueur : " + j.getNomJoueur() + "\n  cash : " + j.getCash());
        System.out.println("Propriete : " + c.getNomCarreau() + "\n case : " + c.getNumero() +"\n Prix : " + c.getMontantAchat());
    }

    public void afficherJoueur(Joueur j) {
    
       String nomPositionCourante = monopoly.getCarreau(j.getPositionCourante()).getNomCarreau();
       System.out.println( "Joueur : " + j.getNomJoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" );      
        
    
    }
    
    public void afficherEtatJoueurs(LinkedList<Joueur>joueurs) {
         System.out.println("Etat de tous les joueurs : ");
        for (Joueur js : joueurs) {
            
            afficherJoueur(js);
            ArrayList<CarreauPropriete> proprietes = js.getProprietes();
            if (proprietes != null) {
                afficherProprietes(proprietes);
            }
            
        }
    }
   
    public void afficherResDEs( int res) {
       System.out.println(" Resultat des dès : " + res); 
       
    }
    public void afficherProprietes(ArrayList<CarreauPropriete> cs) {
        for (CarreauPropriete c : cs) {
            System.out.println("Propriété : " + c.getInfosCarreau() + " Groupe : "  //A MODIFIER
            );
        }
    }
  

    public void AfficherLoyer(Joueur jproprio, int loyer, int nouveauCash) {
        System.out.println("Payement de loyer : ");
        System.out.println("Proprio : "+jproprio.getNomJoueur() + " Loyer : " + loyer + " Cash après payement : " +nouveauCash);
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
