package jeu;

import java.util.LinkedList;
import java.util.Random;
import Ui.Interface;

public class CarreauTirage extends CarreauAction {

    private String typeTirage;
    private LinkedList<Integer> cartesChance;    
    private LinkedList<Integer> cartesCommu;
    Random rand;
    
    public CarreauTirage() {
        cartesChance = new LinkedList<>();
        cartesCommu = new LinkedList<>();
        rand = new Random();
        Integer r = 0;
        for (Integer i = 0 ; i<16 ; ++i) {
            r = rand.nextInt(16)+1;
            cartesChance.add(r);
            r = rand.nextInt(16);
            cartesCommu.add(r);
        }
    }
    
    public void setTypeTirage(String type) {
        this.typeTirage = type;
    }
    
    @Override
    public void action(Joueur j) {
        if (typeTirage.equals("chance")) {
            int c;
            c = cartesChance.poll();
            cartesChance.offerLast(c);
            switch (c) {
                case 1: 
                    Interface.afficher("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
                    // ???
                    break;
                case 2:
                    Interface.afficher("Reculez de trois cases");
                    j.deplacer(j.getPositionCourante()-3);
                    break;
                case 3:
                    Interface.afficher("Vous êtes imposé pour des réparations de voirie à raison de : 40€ par maison et 115€ par hôtel.");
                    for (CarreauPropriete p: j.getProprietes()) {
                        
                    }
                    break;
                case 4:
                    Interface.afficher("Amende pour excès de vitesse : 15€");
                    j.retirerCash(15);
                    break;
                case 5:
                    Interface.afficher("Faites des réparations dans toutes vos maisons : versez pour chaque maison 25€ et pour chaque hotel 100€.");
                    int prix = 0;
                    for (CarreauPropriete p: j.getProprietes()) {
                        if (p.getClass().getSimpleName().equalsIgnoreCase("ProprieteAConstruire")) {
                            prix += (((ProprieteAConstruire)p).getNbMaisons()*25);
                            prix += (((ProprieteAConstruire)p).getNbHotels()*100);
                        }
                    }
                    j.retirerCash(prix);
                    break;
                case 6:
                    Interface.afficher("Amende pour ivresse : 20€");
                    j.retirerCash(20);
                case 7:
                    Interface.afficher("Allez à la case départ");
                    j.deplacer(1);
                case 8:
                    Interface.afficher("Allez en prison. Allez directement en prison, ne passez pas par la case départ, ne recevez pas 200€.");
                    j.deplacer(11); // Prison
                    j.setEnPrison(true);
                case 9:
                    Interface.afficher("Rendez-vous à l'avenue Henri-Martin. Si vous passez par la case départ, recevez 200€.");
                    if (j.getPositionCourante() > 25)
                        j.ajouterCash(200);
                    j.deplacer(25);
                    break;
                case 10: 
                    Interface.afficher("Allez à la gare de Lyon, Si vous passez par la case départ, recevez 200€");
                    if (j.getPositionCourante() > 16)
                        j.ajouterCash(200);
                    j.deplacer(16);
                    break;
                case 11:
                    Interface.afficher("Payez pour frais de scolarité : 150€");
                    j.retirerCash(150);
                    break;
                case 12:
                    Interface.afficher("Vous avez gagné le prix de mots croisés. Recevez 100€");
                    j.ajouterCash(100);
                    break;
                case 13:
                    Interface.afficher("La banque vous verse un dividende de 50€");
                    j.ajouterCash(50);
                    break;
                case 14:
                    Interface.afficher("Rendez-vous rue de la Paix");
                    j.deplacer(40);
                    break;
                case 15:
                    Interface.afficher("Votre immeuble et votre appartement vous rapportent. Vous devez toucher 150€.");
                    j.ajouterCash(150);
                    break;
                case 16:
                    Interface.afficher("Accédez au Boulevard de la Vilette. Si vous passez par la case départ, recevez 200€.");
                    if (j.getPositionCourante() > 12)
                        j.ajouterCash(200);
                    j.deplacer(12);     
                    break;
            }  
        } else {
            int c;
            c = cartesCommu.poll();
            cartesCommu.offerLast(c);
            switch (c) {
                case 1: 
                    System.out.println("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
                    // ???
                    break;
                case 2:
                    Interface.afficher("Payez une amende de 10€");
                    j.retirerCash(10);
                case 3:
                    Interface.afficher("C'est votre anniversaire, chaque joueur doit vous donner 10€.");
                    int cagnotte = 0;
                    for (Joueur j2: getMonopoly().getJoueurs()) {
                        j2.retirerCash(10) ; cagnotte += 10;
                    }
                    j.ajouterCash(cagnotte);
                    break;
                case 4:
                    Interface.afficher("Erreur de la banque en votre faveur. Recevez 200€");
                    j.ajouterCash(200);
                    break;
                case 5:
                    Interface.afficher("Retournez à Bellevile");
                    j.deplacer(2);
                    break;
                case 6:
                    Interface.afficher("Payez la note du medecin : 50€");
                    j.retirerCash(50);
                    break;
                case 7:
                    Interface.afficher("Les contributions vous remboursent la somme de 20€.");
                    j.ajouterCash(20);
                    break;
                case 8:
                    Interface.afficher("Payez à l'hopital 100€");
                    j.retirerCash(100);
                    break;
                case 9:
                    Interface.afficher("Vous héritez de 100€");
                    j.ajouterCash(100);
                    break;
                case 10:
                    Interface.afficher("Vous allez en prison. Avancez tout droit en prison, ne passez pas par la case départ, ne recevez pas 200€.");
                    j.deplacer(11);
                    j.setEnPrison(true);
                    break;
                case 11:
                    Interface.afficher("Payez votre police d'assurance : 50€");
                    j.retirerCash(50);
                    break;
                case 12:
                    Interface.afficher("La vente de votre stock vous rapporte 50€.");
                    j.ajouterCash(50);
                    break;
                case 13:
                    Interface.afficher("Avancez jusqu'à la case départ");
                    j.deplacer(1);
                    break;
                case 14:
                    Interface.afficher("Recevez votre interet sur l'emprunt à 7% : 25€");
                    j.ajouterCash(25);
                    break;
                case 15:
                    Interface.afficher("Recevez votre revenu annuel : 100€");
                    j.ajouterCash(100);
                    break;
                case 16:
                    Interface.afficher("Vous avez gagné le deuxième prix de beauté : recevez 10€");
                    j.ajouterCash(10);
                    break;
            }
        }
    }
}

