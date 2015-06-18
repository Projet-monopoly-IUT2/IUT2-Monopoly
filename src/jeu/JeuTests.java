/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu;

import java.util.InputMismatchException;
import java.util.Scanner;
import static jeu.Monopoly.lancerDes;

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
            System.out.println("5 - Tirer une carte");
            System.out.println("6 - Modifier le propriétaire d'une propriété");
            System.out.println("************** SCÉNARIOS *********************************");
            System.out.println("7 - Jouer un coup sur propriété sans proprio (achat)");
            System.out.println("8 - Jouer un coup sur propriete ne nous appartenant pas (loyer)");
            System.out.println("9 - Jouer un coup sur propriete nous appartenant");
            System.out.println("10 - Jouer un coup sur une propriété a construire nous appartenant  ");
            System.out.println("11 - Jouer un coup sur une propriété a construire nous appartenant avec possibilité de construire");
            System.out.println("12 - Aller en prison");
            System.out.println("13 - Faillite et fin du jeu   ");
            System.out.println("********************************************************");
            System.out.println("Note : le plateau n'est pas réinitialisé entre chaque test !");;
            
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
                            + "Case Tirage..............8 (chance)\n"
                            + "Case Tirage..............18(communauté");

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
                    //Tirer une carte
                     System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    System.out.println("Chance (1) ou CdC (2) ?");
                    int choix  = sc.nextInt();
                    CarreauTirage ct = null;
                    switch (choix) {
                        case 1:
                            ct = (CarreauTirage) m.getCarreau(8);
                            ct.action(j);

                            break;
                        case 2:
                            ct = (CarreauTirage) m.getCarreau(3);
                            ct.action(j);

                            break;
                        default:
                            System.out.println("Saisie incorrecte.");
                            break;
                    }
                    break;
                    
                case 6:
                    System.out.println("Éxécuter pour quel n° de propriété ?");
                    Carreau ca = m.getCarreau(sc.nextInt());
                     System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    if (ca instanceof CarreauPropriete) {
                        ((CarreauPropriete) ca).setProprietaire(j);
                        j.setPropriete((CarreauPropriete)ca);
                    }
                    else
                        System.out.println("Ce carreau n'est pas une propriété.");
                    break;
                    
                case 7:
                    //Jouer un coup sur propriété sans proprio (achat)
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    c = (CarreauPropriete) m.getCarreau(16);
                    j.setCarreau(c);
                    c.action(j);
                    break;
                case 8:
                    //Jouer un coup sur propriete ne nous appartenant pas (loyer)
                    ResultatDes nb = lancerDes();
                    int resultatDes = nb.getRes();
                    System.out.println("Éxécution pour le joueur 1 :");
                    System.out.println("Loyer pour une gare :");
                    c = (CarreauPropriete) m.getCarreau(16);
                     System.out.println("Propriété : " + c.getNomCarreau() + " : " + String.valueOf(c.getNumero()));  
                    c.setProprietaire(m.getJoueur(1));
                    m.getJoueur(1).setPropriete(c);
                    m.getJoueur(0).setCarreau(c);
                    c.action(m.getJoueur(0));
                    System.out.println("Loyer pour une propriete :");
                    c = (CarreauPropriete) m.getCarreau(2);
                    System.out.println("Propriété : " + c.getNomCarreau() + " : " + String.valueOf(c.getNumero()) + " Groupe : " + ((ProprieteAConstruire)c).getGroupePropriete().getCouleur().toString());
                    c.setProprietaire(m.getJoueur(1));
                    m.getJoueur(1).setPropriete(c);
                    m.getJoueur(0).setCarreau(c);
                    c.action(m.getJoueur(0));

                
                    break;

                case 9:
                    //Jouer un coup sur propriete nous appartenant
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    c = (CarreauPropriete) m.getCarreau(16);
                    c.setProprietaire(j);
                    j.setCarreau(c);
                    j.setPropriete(c);
                    c.action(j);
                    break;

                case 10:
                    //Jouer un coup sur une propriété a construire nous appartenant
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    ProprieteAConstruire p2 = (ProprieteAConstruire)m.getCarreau(40);
                    p2.setProprietaire(j);
                    j.setPropriete(p2);
                    j.setCarreau(p2);
                    p2.action(j);
                    break;

                case 11:
                    //Jouer un coup sur une propriété a construire nous appartenant
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    ProprieteAConstruire p1 = (ProprieteAConstruire)m.getCarreau(2);
                    ProprieteAConstruire p3 = (ProprieteAConstruire)m.getCarreau(4);
                    p1.setProprietaire(j);
                    p3.setProprietaire(j);
                    j.setPropriete(p1);
                    j.setPropriete(p3);
                    //m.setNbHotels(1);
                    //m.setNbMaisons(5);
                    j.setCarreau(p1);
                    p1.action(j);
                    break;
                    
                case 12:
                    System.out.println("Éxécuter pour quel joueur ?");
                    j = m.getJoueur(sc.nextInt()-1);
                    System.out.println("Donner une carte sortie de prison au joueur ? (oui/non)");
                    String carteP = sc.next();
                    switch(carteP) {
                        case "oui":
                            j.addCarteSortiePrison();
                            break;
                            
                        default:
                            System.out.println("Erreur de saisie. La carte ne sera pas donnée au joueur");
                        case "non":
                            break;
                    }
                    
                    j.setEnPrison(true);
                    j.deplacer(11);
                    System.out.println("Le jeu va commencer avec un joueur en prison...");
                    
                    for(int i = 1; i<= 4; i++)
                        for (Joueur js : m.getJoueurs()){
                            m.jouerUnCoup(js);
                        }
                                
                    break;
                    
                case 13:
                    //Faillite et fin du jeu
                    Joueur j1 = m.getJoueur(0);
                    Joueur j2 = m.getJoueur(1);
                    j1.setCash(1);
                    j1.deplacer(20);
                    for (Carreau cb: m.getCarreaux().values()) //Donner tous les carreaux à j2
                        if (cb instanceof CarreauPropriete) {
                            ((CarreauPropriete) cb).setProprietaire(j2);
                            j2.setPropriete((CarreauPropriete)cb);
                            if (cb instanceof ProprieteAConstruire) {
                                ((ProprieteAConstruire) cb).setNbHotelsC(1);
                            }
                        }
                    System.out.println("Toutes les propriétés appartiennent au joueur 2, le joueur 1 a 10€.");
                    System.out.println("Faillite au prochain paiement de loyer par le joueur 1.");
                    m.jouerPlusieursCoups();
            }
        }
    }
}
