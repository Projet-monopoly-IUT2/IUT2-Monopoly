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
     * @throws Faillite si le joueur ne peut pas payer une carte "malus"
     */
    @Override
    public void action(Joueur j) throws Faillite {
        if (montant > 0)
            j.ajouterCash(montant);
        else 
            if (!j.retirerCash(montant))
                throw new Faillite();
    }
}
