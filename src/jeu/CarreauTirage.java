package jeu;

import java.util.Random;

public class CarreauTirage extends CarreauAction {

    private String typeTirage;
    Random rand;

    /**
     * Initialise la case et les tas de cartes : population et mélange.
     *
     * @param mono l'instance de Monopoly
     */
    public CarreauTirage(Monopoly mono) {
        super(mono);
        rand = new Random();
    }

    /**
     * Définir si la case est une case Chance ou Caisse de communauté.
     *
     * @param type le type de case : chance ou caisse de communauté
     */
    public void setTypeTirage(String type) {
        this.typeTirage = type;
    }

    /**
     * Tire une carte, en applique les effets et replace la carte en bas de la
     * pile.
     *
     * @param j Le joueur courant
     */
    @Override
    public void action(Joueur j) throws Faillite {
        Carte c;
        if (typeTirage.equalsIgnoreCase("chance")) {
            c = getMonopoly().getCartesChance().poll();
            c.action(j);
            getMonopoly().getCartesChance().offerLast(c);
        }
    }
}
