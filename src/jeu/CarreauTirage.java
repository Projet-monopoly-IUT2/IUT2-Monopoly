package jeu;

import java.util.Random;

public class CarreauTirage extends CarreauAction {

    private String typeTirage;
    Random rand;
    
    /**
     * Initialise la case et les tas de cartes : population et mélange.
     * @param mono l'instance de Monopoly
     */
    public CarreauTirage(Monopoly mono) {
        super(mono);
        rand = new Random();
    }
    
    /**
     * Définir si la case est une case Chance ou Caisse de communauté.
     * @param type le type de case : chance ou caisse de communauté 
     */
    public void setTypeTirage(String type) {
        this.typeTirage = type;
    }
    
    /**
     * Tire une carte et en applique les effets.
     * @param j le joueur courant
     */
    @Override
    public void action(Joueur j) {
        Carte c;
        if (typeTirage.equalsIgnoreCase("chance")) {
            c = getMonopoly().getCartesChance().poll();
            c.action(j);
            getMonopoly().getCartesChance().offerLast(c);
            
/*            switch (c) {
                case 1: 
                   super.getMonopoly().interfaceJeu.afficher("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
                    super.getMonopoly().getJCourant().setCarteSortiePrison(true);
                    break;
                case 2:
                    super.getMonopoly().interfaceJeu.afficher("Reculez de trois cases");
                    j.deplacer(j.getPositionCourante()-3);
                    break;
                case 3:
                    super.getMonopoly().interfaceJeu.afficher("Vous êtes imposé pour des réparations de voirie à raison de : 40€ par maison et 115€ par hôtel.");
                    for (CarreauPropriete p: j.getProprietes()) {
                        
                        
                    }
                    break;
                case 4:
                    super.getMonopoly().interfaceJeu.afficher("Amende pour excès de vitesse : 15€");
                    j.retirerCash(15);
                    break;
                case 5:
                    super.getMonopoly().interfaceJeu.afficher("Faites des réparations dans toutes vos maisons : versez pour chaque maison 25€ et pour chaque hotel 100€.");
                    int prix = 0;
                    for (CarreauPropriete p: j.getProprietes()) {
                        if (p instanceof ProprieteAConstruire) {
                            prix += (((ProprieteAConstruire)p).getNbMaisonsC()*25);
                            prix += (((ProprieteAConstruire)p).getNbHotelsC()*100);
                        }
                    }
                    j.retirerCash(prix);
                    break;
                case 6:
                   super.getMonopoly().interfaceJeu.afficher("Amende pour ivresse : 20€");
                    j.retirerCash(20);
                    break;
                case 7:
                    super.getMonopoly().interfaceJeu.afficher("Allez à la case départ");
                    j.deplacer(1);
                    break;
                case 8:
                    super.getMonopoly().interfaceJeu.afficher("Allez en prison. Allez directement en prison, ne passez pas par la case départ, ne recevez pas 200€.");
                    j.deplacer(11); // Prison
                    j.setEnPrison(true);
                    break;
                case 9:
                    super.getMonopoly().interfaceJeu.afficher("Rendez-vous à l'avenue Henri-Martin. Si vous passez par la case départ, recevez 200€.");
                    if (j.getPositionCourante() > 25)
                        j.ajouterCash(200);
                    j.deplacer(25);
                    break;
                case 10: 
                    super.getMonopoly().interfaceJeu.afficher("Allez à la gare de Lyon, Si vous passez par la case départ, recevez 200€");
                    if (j.getPositionCourante() > 16)
                        j.ajouterCash(200);
                    j.deplacer(16);
                    break;
                case 11:
                    super.getMonopoly().interfaceJeu.afficher("Payez pour frais de scolarité : 150€");
                    j.retirerCash(150);
                    break;
                case 12:
                   super.getMonopoly().interfaceJeu.afficher("Vous avez gagné le prix de mots croisés. Recevez 100€");
                    j.ajouterCash(100);
                    break;
                case 13:
                   super.getMonopoly().interfaceJeu.afficher("La banque vous verse un dividende de 50€");
                    j.ajouterCash(50);
                    break;
                case 14:
                    super.getMonopoly().interfaceJeu.afficher("Rendez-vous rue de la Paix");
                    j.deplacer(40);
                    break;
                case 15:
                    super.getMonopoly().interfaceJeu.afficher("Votre immeuble et votre appartement vous rapportent. Vous devez toucher 150€.");
                    j.ajouterCash(150);
                    break;
                case 16:
                    super.getMonopoly().interfaceJeu.afficher("Accédez au Boulevard de la Vilette. Si vous passez par la case départ, recevez 200€.");
                    if (j.getPositionCourante() > 12)
                        j.ajouterCash(200);
                    j.deplacer(12);     
                    break;
            }  
        } else {
            c = cartesCommu.poll();
            cartesCommu.offerLast(c);
            switch (c) {
                case 1: 
                    System.out.println("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
                    // ???
                    break;
                case 2:
                    super.getMonopoly().interfaceJeu.afficher("Payez une amende de 10€");
                    j.retirerCash(10);
                    break;
                case 3:
                   super.getMonopoly().interfaceJeu.afficher("C'est votre anniversaire, chaque joueur doit vous donner 10€.");
                    int cagnotte = 0;
                    for (Joueur j2: getMonopoly().getJoueurs()) {
                        j2.retirerCash(10) ; cagnotte += 10;
                    }
                    j.ajouterCash(cagnotte);
                    break;
                case 4:
                    super.getMonopoly().interfaceJeu.afficher("Erreur de la banque en votre faveur. Recevez 200€");
                    j.ajouterCash(200);
                    break;
                case 5:
                    super.getMonopoly().interfaceJeu.afficher("Retournez à Bellevile");
                    j.deplacer(2);
                    break;
                case 6:
                    super.getMonopoly().interfaceJeu.afficher("Payez la note du medecin : 50€");
                    j.retirerCash(50);
                    break;
                case 7:
                    super.getMonopoly().interfaceJeu.afficher("Les contributions vous remboursent la somme de 20€.");
                    j.ajouterCash(20);
                    break;
                case 8:
                   super.getMonopoly().interfaceJeu.afficher("Payez à l'hopital 100€");
                    j.retirerCash(100);
                    break;
                case 9:
                    super.getMonopoly().interfaceJeu.afficher("Vous héritez de 100€");
                    j.ajouterCash(100);
                    break;
                case 10:
                    super.getMonopoly().interfaceJeu.afficher("Vous allez en prison. Avancez tout droit en prison, ne passez pas par la case départ, ne recevez pas 200€.");
                    j.deplacer(11);
                    j.setEnPrison(true);
                    super.getMonopoly().jouerPrison(j);
                    break;
                case 11:
                    super.getMonopoly().interfaceJeu.afficher("Payez votre police d'assurance : 50€");
                    j.retirerCash(50);
                    break;
                case 12:
                   super.getMonopoly().interfaceJeu.afficher("La vente de votre stock vous rapporte 50€.");
                    j.ajouterCash(50);
                    break;
                case 13:
                   super.getMonopoly().interfaceJeu.afficher("Avancez jusqu'à la case départ");
                    j.deplacer(1);
                    j.ajouterCash(200);
                    break;
                case 14:
                    super.getMonopoly().interfaceJeu.afficher("Recevez votre interet sur l'emprunt à 7% : 25€");
                    j.ajouterCash(25);
                    break;
                case 15:
                   super.getMonopoly().interfaceJeu.afficher("Recevez votre revenu annuel : 100€");
                    j.ajouterCash(100);
                    break;
                case 16:
                    super.getMonopoly().interfaceJeu.afficher("Vous avez gagné le deuxième prix de beauté : recevez 10€");
                    j.ajouterCash(10);
                    break;
            }
*/        }
    }
}

