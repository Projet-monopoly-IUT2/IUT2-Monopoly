package jeu;

import java.util.ArrayList;
import java.util.InputMismatchException;
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
 
        System.out.println();
//        System.out.println("☆● ☆● ☆● ☆● Tour du joueur : ");
        if (j.enFaillite()) {
            System.out.println(j.getNomJoueur() + " est en faillite !");
        } else {
            Carreau PositionCourante = monopoly.getCarreau(j.getPositionCourante());
            String nomPositionCourante = PositionCourante.getNomCarreau();
            if (monopoly.getCarreau(j.getPositionCourante()) instanceof ProprieteAConstruire) {
                System.out.println("Joueur : " + j.getNomJoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")" + " - Groupe : " + ((ProprieteAConstruire) monopoly.getCarreau(j.getPositionCourante())).getGroupePropriete().getCouleur().toString());
            } else {
                System.out.println("Joueur : " + j.getNomJoueur() + " - " + j.getCash() + "€ - Position : " + nomPositionCourante + " (case " + j.getPositionCourante() + ")");
            }
        }
    }
    
    /**
     * Afficher l'état d'un groupe de joueurs ainsi que leurs propriétés.
     * @param joueurs liste des joueurs à afficher 
     */
    public void afficherEtatJoueurs(LinkedList<Joueur> joueurs) {
        System.out.println("***************************");
        System.out.println("Etat de tous les joueurs : ");
        for (Joueur js : joueurs) {
            if (js.enFaillite()) {
                System.out.println(js.getNomJoueur() + " est en faillite !");
            } else {
                afficherJoueur(js);
                ArrayList<CarreauPropriete> proprietes = js.getProprietes();
                if (proprietes != null) {
                    afficherProprietes(proprietes);
                }
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
       if (res.isDble())  System.out.println("\033[31m Double !\u001B[0m");
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
        System.out.println("--------------------------------------");
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
        System.out.println("Paiement à : "+jproprio.getNomJoueur() + "- Loyer : " + loyer + "- Cash après payement : " +nouveauCash);
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
        boolean entreeCorrecte = false;
        int choix = 1;
        
        System.out.println("***************************");
        while (!entreeCorrecte) {
            System.out.println("Voulez vous acheter cette propriete (1 - oui/ 2 - non)?");
            try {
                choix = sc.nextInt();
                entreeCorrecte = true;
            } catch (InputMismatchException e) {
                entreeCorrecte = false;
            }
                if (choix != 1 && choix != 2) {
                entreeCorrecte = false;
            }
        }
        System.out.println("***************************");
        return choix;

    }
    /**
     * Informe l'utilisateur qu'il est en prison pour avoir fait 3 doubles de suite
     * @param j Joueur courant
     * @param nb Résultat du 3e lancer.
     */
    public void EstPrisonPourDouble (Joueur j, ResultatDes nb ) {
         System.out.println("***************************"); 
         System.out.println("Résultat des dés : "+ nb.getRes());
          System.out.println("\033[31m le joueur " + j.getNomJoueur()+ " est en prison car il a fait 3 doubles à la suite ! \u001B[0m");
         System.out.println("***************************");
    }
    
public void MessageErreur(int i) {
        switch (i) {
            case 1:
                System.out.println("Le choix n'est pas équilibré.\nChoisir une autre Propriete");
                break;
            case 2:
                System.out.println("Vous n'avez pas assez d'argent !");
                break;
            case 3:
                System.out.println("Le Monopoly n'as plus assez de maisons");
                break;
            case 4:
                System.out.println("Le Monopoly n'as plus assez d'hotels");
                break;
            case 5:
                System.out.println("Vous ne possedez pas toute les proprietes du groupe ");
                break;
        }
    }
    
/**
 * Demande de choisir la propriété du groupe sur laquelle construire
 * @param j joueur courant
 * @param proprietes Liste de propriétés sur lesquelles j peut construire.
 * @return 
 */
    public int affichageChoixConstruction(Joueur j, ArrayList<ProprieteAConstruire> proprietes) {       
        Scanner sc = new Scanner(System.in);
        for(ProprieteAConstruire PaC : proprietes){
                   
            System.out.println("Propriété : " + PaC.getNomCarreau() + " : " + String.valueOf(PaC.getNumero()) + " Groupe : " + PaC.getGroupePropriete().getCouleur().toString());             
            System.out.println("Nb Maison : " + PaC.getNbMaisonsC());
            System.out.println("Nb Hotel : " + PaC.getNbHotelsC());
        }
                
        System.out.println("Cash Joueur : " + j.getCash());
        System.out.println("Choisir une propriete par son numero");
        int rep = sc.nextInt();
        boolean Vrai = false;
        
        while(!Vrai){
                
            for  (ProprieteAConstruire p : proprietes) {
                if (p.getNumero() == rep) {
                    Vrai = true;
                }
            }
            if (!Vrai){
                System.out.println("Choisir un bon numero de propriete parmi la liste proposé");
                rep = sc.nextInt();
            }   
          
        }
              
        return rep;          
    }
    
    /**
     * Demande à l'utilisateur s'il veut construire.
     * @param i 1: demande si l'utilisateur veut construire.<br/>2: signale la complétion de la construction.
     * @return 1 si l'utilisateur accepte, 2 si l'utilisateur refuse.
     */
    public int MessageConstruction(int i) {
        if (i==1){
            System.out.println("Voulez vous construire ? (1-oui/2-non)");
            Scanner sc = new Scanner(System.in);
            String rep1 = sc.nextLine();
            while(!rep1.equalsIgnoreCase("oui") && !rep1.equalsIgnoreCase("non")){
                System.out.println("Saisir oui/non :");
                rep1 = sc.nextLine();
            }
            if (rep1.equalsIgnoreCase("oui")){
                return 2;
            }
            else{
                return 1;
            }
        }        
        else{
            System.out.println("Construction faite"); 
            return 2;
        }        
    }

    /**
     * Propose à l'utilisateur d'utiliser sa carte sortie de prison.
     * @return Vrai si l'utilisateur utilise sa carte, faux sinon. 
     */
    public boolean  utiliserCarteSortiePrison() {
           Scanner sc = new Scanner(System.in);
           boolean sortie = false;
           System.out.println("Voulez vous utiliser votre carte libéré de prison ?  - 1/Oui 2/Non");
           int rep = sc.nextInt();
           while (rep < 1 && rep > 2) {
             rep = sc.nextInt();  
             System.out.println("Voulez vous utiliser votre carte libéré de prison ?  - 1/Oui 2/Non");  
           }   
             
            if (rep == 1) {
                    System.out.println("Vous êtes libéré de prison");
                    sortie = true;
                }
             else if (rep == 2) {
                    sortie = false;
                }
            
            
            
           System.out.println(sortie);
           return sortie;
       }
    
    /**
     * Demande le nom d'un joueur.
     * @return Le nom du joueur
     */
    public String SaisieNomJ (int i) {
      
            System.out.println("Joueur " + i);
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Nom du joueur :" );
            String nj = sc2.nextLine();
            return nj;
        
    }
    
    /**
     * Demande de saisir le nombre de joueurs.
     * @return Le nombre de joueurs, (2 <= nbJoueurs <= 6).
     */
    public int SaisienbJoueurs() {
        int nbJoueurs = 0;
        Scanner sc = new Scanner(System.in);
        
        System.out.println(" Inscription des joueurs : ");
        
        boolean saisieCorrecte = false;
        while (!saisieCorrecte) {
            try {
                System.out.print("Nombre de joueurs (2-6) : ");
                nbJoueurs = sc.nextInt();
                
                
                if (nbJoueurs >= 2 && nbJoueurs <= 6) { saisieCorrecte = true; }                
            }
            catch (InputMismatchException e){
                saisieCorrecte = false;
                sc.nextLine();
            }
            
            if (!saisieCorrecte) {
                System.out.println(" Saisie Incorrecte :");
            }
        }
    return nbJoueurs;  
    }
    
    public void messageCarte(Carte c, String typeTirage) {
        System.out.println("***************************");
        System.out.println("Carte " + typeTirage + " n° " + c.getNumero() + " : " + c.getDescription());
        System.out.println("***************************");
    }
    
    /**
     * Informe de la faillite d'un joueur. (pas de chance)
     * @param j le joueur faisant faillite.
     */
    public void faillite(Joueur j) {
        System.out.println("\033[31m Le joueur " + j.getNomJoueur() + " a fait faillite ! \u001B[0m");
    }
    
    /**
     * Informe que la partie est finie et donne le joueur gagnant.
     * @param j joueur gagnant
     */
    public void afficherFinJeu( Joueur j) {
        System.out.println();
        System.out.println("\033[31m Le joueur gagnant est " + j.getNomJoueur() + " Bravo ! \u001B[0m");
        System.out.println("▁ ▂ ▃ ▄ ▅ ▆ ▇ Fin du jeu █ ▇ ▆ ▅ ▄ ▂ ▁ ");   
    }
    
    /**
     * Message remplaçant le jeu normal pour les joueurs en faillite.
     * @param j 
     */
    public void jouerFaillite(Joueur j) {
        System.out.println(j.getNomJoueur() +" est en faillite !");
    }
 
    public void MessageAchat(int i){
        if(i==1){
            System.out.println("Vous venez d'acheter cette propriété, bravo !");
            System.out.println("***************************");
        }
        else{
            System.out.println("Vous n'avez pas acheté cette propriete");
            System.out.println("***************************");
        }
    }
    
}
