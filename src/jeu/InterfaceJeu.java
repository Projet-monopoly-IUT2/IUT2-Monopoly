package jeu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class InterfaceJeu {


    private Monopoly monopoly;
    
    public InterfaceJeu (Monopoly mono) {
        monopoly = mono;
    }

    /**
     * Affiche les informations nécéssaire à un joueur pour prendre la décision
     * d'acheter une propriété : cash, identité de la propriété et prix.
     * @param c la propriété à acheter
     * @param j le joueur souhaitant acheter
     */
    public void afficherAchat(CarreauPropriete c, Joueur j) {
        System.out.println("***************************");
        System.out.println("Achat : ");
        System.out.println("Joueur : " + j.getNomJoueur() + "\n  cash : " + j.getCash());
        System.out.println("Propriete : " + c.getNomCarreau() + "\n case : " + c.getNumero() +"\n Prix : " + c.getMontantAchat());
        System.out.println("***************************");
    }

    /**
     * Afficher les informations relatives à un joueur.
     * @param j le joueur a afficher
     */
    public void afficherJoueur(Joueur j) {
    
       String nomPositionCourante = monopoly.getCarreau(j.getPositionCourante()).getNomCarreau();
            
       if (monopoly.getCarreau(j.getPositionCourante()) instanceof ProprieteAConstruire)
            { System.out.println( "Joueur : " + j.getNomJoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" + " - Groupe : " + ((ProprieteAConstruire)monopoly.getCarreau(j.getPositionCourante())).getGroupePropriete().getCouleur().toString());  }
            else {
              System.out.println( "Joueur : " + j.getNomJoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" ); 
            } 
    
    }
    
    /**
     * Afficher l'état d'un groupe de joueurs ainsi que leurs propriétés.
     * @param joueurs liste des joueurs à afficher 
     */
    public void afficherEtatJoueurs(LinkedList<Joueur>joueurs) {
        System.out.println("***************************");
        System.out.println("Etat de tous les joueurs : ");
        for (Joueur js : joueurs) {
            
            afficherJoueur(js);
            ArrayList<CarreauPropriete> proprietes = js.getProprietes();
            if (proprietes != null) {
                afficherProprietes(proprietes);
            }
        }
        System.out.println("***************************");
    }
    
    /**
     * Affiche le résultat du lancer de dés et, le cas échéant, la mention "Double".
     * @param res le resultat du lancer de dés 
     */
    public void afficherResDes(ResultatDes res) {
       System.out.println("***************************"); 
       System.out.print(" Resultat des dés : " + res.getRes());
       if (res.isDble()) System.out.print(" (Double !)");
       System.out.println();
       
    }
    
    /**
     * Affiche, pour un groupe de cases, leur nom, numéro et groupe.
     * @param cs groupe de cases à afficher
     */
    public void afficherProprietes(ArrayList<CarreauPropriete> cs) {
        for (CarreauPropriete c : cs) {
            if (c instanceof ProprieteAConstruire)
            { System.out.println("Propriété : " + c.getNomCarreau() + " : " + String.valueOf(c.getNumero()) + " Groupe : " + ((ProprieteAConstruire)c).getGroupePropriete().getCouleur().toString()); }
            else {
              System.out.println("Propriété : " + c.getNomCarreau() + " : " + String.valueOf(c.getNumero()));  
            }
        }
    }
  
    /**
     * Affiche les informations relatives à un paiement de loyer
     * @param jproprio propriétaire de la case
     * @param loyer montant du loyer
     * @param nouveauCash argent restant au payeur
     */
    public void AfficherLoyer(Joueur jproprio, int loyer, int nouveauCash) {
        System.out.println("***************************");
        System.out.println("Payement de loyer : ");
        System.out.println("Proprio : "+jproprio.getNomJoueur() + "- Loyer : " + loyer + "- Cash après payement : " +nouveauCash);
        System.out.println("***************************");
    }
   
    /**
     * Affiche la chaine passée en apramètre
     * @param infos chaine à afficher
     */
    public void afficher(String infos) {
        System.out.println(infos);
    }
     /**
      * Demande à l'utilisateur s'il souhaite acheter la propriété passée en paramètre.
      * @param j le joueur pouvant acheter
      * @param c la propriété pouvant être achetée
      * @return l'accord du joueur
      */
    public int ChoixAchat(Joueur j, CarreauPropriete c) {
        Scanner sc = new Scanner(System.in);
        System.out.println("***************************");
        System.out.println("Voulez vous acheter cette propriete (1 - oui/ 2 - non)?");
        
     
        int rep = sc.nextInt();
        while (rep < 1 && rep > 2) {
            System.out.println("Voulez vous acheter cette propriete (1 - oui/ 2 - non)?");
            rep = sc.nextInt();
            
        }
        System.out.println("***************************");
        return rep;
        
    }
    
    public void EstPrisonPourDouble (Joueur j ) {
         System.out.println("***************************"); 
         System.out.println("le joueur " + j.getNomJoueur()+ " est en prison car il a fait 3 doubles à la suite ! ");
         System.out.println("***************************");
    }

}
