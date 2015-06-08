package jeu;

import java.util.ArrayList;

public class InterfaceJeu {


    private Monopoly monopoly;
    
    public InterfaceJeu (Monopoly mono) {
        monopoly = mono;
    }

    public void afficherAchat(Carreau c, Joueur j) {
        throw new UnsupportedOperationException();
    }

    public void afficherJoueur(Joueur j) {
    
       String nomPositionCourante = monopoly.getCarreau(j.getPositionCourante()).getNomCarreau();
//       ProprieteAConstruire p = new ProprieteAConstruire(monopoly);
//        if (j.getCarreauCourant().equals(p)) {
//            p = (ProprieteAConstruire)j.getCarreauCourant();
//       System.out.println( "Joueur : " + j.getNomjoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" + p.getGroupePropriete().getCouleur().toString()); 
//        }
//        else {
       System.out.println( "Joueur : " + j.getNomjoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" );      
        
    
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
