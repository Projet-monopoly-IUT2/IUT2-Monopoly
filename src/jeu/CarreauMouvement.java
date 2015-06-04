package jeu;

public class CarreauMouvement extends CarreauAction {

    private int caseCible;

    public int getCaseCible() {
        return caseCible;
    }

    public void setCaseCible(int caseCible) {
        this.caseCible = caseCible;
    }
    
    public void action(Joueur j){
        j.deplacer(caseCible);
    }
}
