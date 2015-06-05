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
                    System.out.println("Vous êtes libéré de prison. Cette carte peut être conservée jusqu'à ce qu'elle soit utilisée.");
                    // ???
                    break;
                case 2:
                    System.out.println("Reculez de trois cases");
                    j.deplacer(j.getPositionCourante()-3);
                    break;
                case 3:
                    System.out.println("Vous êtes imposé pour des réparations de voirie à raison de : 40€ par maison et 115€ par hôtel.");
                    for (CarreauPropriete p: j.getProprietes()) {
                        
                    }
                    break;
                case 4:
                    Interface.affiche("Amende pour excès de vitesse : 15€");
                    j.setCash(j.getCash()-15);
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:

                
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
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:

        }
    }
}
}
