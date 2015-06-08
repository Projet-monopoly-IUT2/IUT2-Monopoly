package jeu;

public class CarreauMouvement extends CarreauAction {

    private int caseCible;

    public CarreauMouvement(Monopoly monopoly) {
        super(monopoly);
    }

    public int getCaseCible() {
        return caseCible;
    }

    public void setCaseCible(int caseCible) {
        this.caseCible = caseCible;
    }
    /**
     * Déplace le joueur sur la case cible.
     * @param j le joueur courant
     */
    public void action(Joueur j){
        j.deplacer(caseCible);
    }
}
