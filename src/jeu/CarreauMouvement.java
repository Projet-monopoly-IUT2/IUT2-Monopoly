package jeu;

public class CarreauMouvement extends CarreauAction {

    private int caseCible;

    public CarreauMouvement(Monopoly monopoly) {
        super(monopoly);
    }

    /**
     *
     * @return La case sur laquelle ce carreau pointe.
     */
    public int getCaseCible() {
        return caseCible;
    }

    /**
     *
     * @param caseCible La case sur laquelle le carreau doit pointer.
     */
    public void setCaseCible(int caseCible) {
        this.caseCible = caseCible;
    }

    /**
     * Déplace le joueur sur la case cible.
     *
     * @param j le joueur courant
     */
    public void action(Joueur j) {
        j.deplacer(11);
    }
}
