package jeu;

public class CarreauArgent extends CarreauAction {

    private int montant;

    public CarreauArgent(Monopoly monopoly) {
        super(monopoly);
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    /**
     * Ajoute ou retire de l'argent au joueur (montant selon la case).
     * @param j le joueur courant
     */
    @Override
    public void action(Joueur j){
         j.ajouterCash(montant);
    }
}
