package jeu;

import java.util.ArrayList;
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
//       ProprieteAConstruire p = new ProprieteAConstruire(monopoly);
//        if (j.getCarreauCourant().equals(p)) {
//            p = (ProprieteAConstruire)j.getCarreauCourant();
//       System.out.println( "Joueur : " + j.getNomjoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" + p.getGroupePropriete().getCouleur().toString()); 
//        }
//        else {
       System.out.println( "Joueur : " + j.getNomJoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" );      
        
    
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

    public void AfficherLoyer(Joueur jproprio, int loyer, int nouveauCash) {
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
