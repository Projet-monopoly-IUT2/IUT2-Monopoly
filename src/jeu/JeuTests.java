/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.Scanner;

/**
 *
 * @author bethouxa
 */
public class JeuTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Faillite {
        Scanner sc = new Scanner(System.in);

        Monopoly m = new Monopoly("./src/data/data.txt");
        CarreauPropriete c;
        Joueur j;

        while (true) {
            System.out.println("");
            System.out.println("");
            
            System.out.println("Menu de test : ");
            System.out.println("************* ACTIONS DE BASE *****************************");
            System.out.println("0 - (Ré)Initialiser la partie  ");
            System.out.println("1 - Afficher l'état de tous les joueurs  ");
            System.out.println("2 - Déplacer un joueur");
            System.out.println("3 - Éxécuter l'action");
            System.out.println("4 - Modifier cash");
            System.out.println("************** SCÉNARIOS *********************************");
            System.out.println("5 - Jouer un coup sur propriété sans proprio (achat)");
            System.out.println("6 - Jouer un coup sur propriete ne nous appartenant pas (loyer)");
            System.out.println("7 - Jouer un coup sur propriete nous appartenant");
            System.out.println("8 - Jouer un coup sur une propriété a construire nous appartenant  ");
            System.out.println("9 - Faillite et fin du jeu   ");
            System.out.println("********************************************************");
            System.out.println("Note : le plateau n'est pas réinitialisé entre chaque test !");;

            Scanner sc = new Scanner(System.in);
            int scenario = sc.nextInt();
            
            System.out.println("");
            System.out.println("");
            
            switch (scenario) {
                case 0:
                    System.out.println("/!\\ Pour les besoins du test, merci de ne déclarer que 2 joueurs /!\\");
                    m = new Monopoly("src/data/data.txt");
                    m.initialiserPartie();

                    break;
                case 1:
                    m.getInterfaceJeu().afficherEtatJoueurs(m.getJoueurs());//afficher etat
                    break;
                case 2:
                    System.out.println("Exemples : \n"
                            + "Propriété à construire...2 (bd de Belleville)\n"
                            + "Gare.....................6 (gare montparnasse)\n"
                            + "Case Argent..............5 (impot sur le revenu)\n"
                            + "Case Tirage..............8 (chance)");

                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);

                    System.out.print("Case sur laquelle se déplacer : ");
                    Carreau caseCible = m.getCarreau(sc.nextInt());

                    j.setCarreau(caseCible);
                    
                    break;

                case 3:
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    m.getCarreau(j.getPositionCourante()).action(j);
                    break;

                case 4:
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    System.out.println("Nouvau solde du joueur : ");
                    j.setCash(sc.nextInt());
                    break;
                case 5:
                    //Jouer un coup sur propriété sans proprio (achat)
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    c = (CarreauPropriete) m.getCarreau(16);
                    j.setCarreau(c);
                    c.action(j);
                    break;
                case 6:
                    //Jouer un coup sur propriete ne nous appartenant pas (loyer)
                    System.out.println("Éxécution pour le joueur 1 :");
                    c = (CarreauPropriete) m.getCarreau(16);
                    c.setProprietaire(m.getJoueur(1));
                    m.getJoueur(1).setPropriete(c);
                    m.getJoueur(0).setCarreau(c);
                    c.action(m.getJoueur(0));
                    break;

                case 7:
                    //Jouer un coup sur propriete nous appartenant
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    c = (CarreauPropriete) m.getCarreau(16);
                    c.setProprietaire(j);
                    j.setCarreau(c);
                    j.setPropriete(c);
                    c.action(j);
                    break;

                case 8:
                    //Jouer un coup sur une propriété a construire nous appartenant
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    c = (CarreauPropriete) m.getCarreau(2);
                    c.setProprietaire(j);
                    j.setCarreau(c);
                    c.action(j);
                    break;

                case 9:
                    //Faillite et fin du jeu
                    j = m.getJoueur(1);
                    j.setCash(1);
                    ProprieteAConstruire p = (ProprieteAConstruire) m.getCarreau(40);
                    p.setProprietaire(m.getJoueur(2));
                    p.setNbHotelsC(1);
                    j.setCarreau(p);
                    System.out.println("Faillite dans deux tours maximum.");
                    m.jouerPlusieursCoups();
            }
        }
    }
}
