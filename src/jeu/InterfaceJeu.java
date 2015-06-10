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

       Carreau PositionCourante = monopoly.getCarreau(j.getPositionCourante());
       String  nomPositionCourante = PositionCourante.getNomCarreau();
        System.out.println(nomPositionCourante);   
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
                if (rep < proprietes.get(1).getNumero() || rep > proprietes.get(proprietes.size()-1).getNumero()){
                    System.out.println("Choisir un bon numero de propriete parmi la liste proposé");
                    rep = sc.nextInt();
                    Vrai = true;
                }    
        }
              
        return rep;          
    }
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
    
    public String SaisieNomJ () {
      
            Scanner sc2 = new Scanner(System.in);
            System.out.print("Nom du joueur :" );
            String nj = sc2.nextLine();
            return nj;
        
    }
    
    public int SaisienbJoueurs() {
         int nbJoueurs;
        Scanner sc = new Scanner(System.in);
        
        
        System.out.println(" Inscription des joueurs : ");

        
        nbJoueurs = 0;
        boolean saisieIncorrecte = true;
        
        while (saisieIncorrecte && (nbJoueurs < 2 || nbJoueurs > 6)) {
            try {
                System.out.print("Nombre de joueurs (2-6) : ");
                nbJoueurs = sc.nextInt();
                sc.nextLine();
                if (nbJoueurs >= 2 && nbJoueurs <= 6) { saisieIncorrecte = false; }
                else {
                    System.out.println(" Saisie Incorrecte :");
                }
                
            }
            catch (Exception InputMismatchException){
                System.out.println(" Saisie Incorrecte :");
                System.out.println(" Entrez un nombre entre 2 et 6 : ");
                sc.nextLine();
               
            }
         
        
        }
    return nbJoueurs;  
    }
    
}
