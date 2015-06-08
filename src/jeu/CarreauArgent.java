package jeu;

public class CarreauArgent extends CarreauAction {

    private int montant;

    public CarreauArgent(Monopoly monopoly) {
        super(monopoly);
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    @Override
    public void action(Joueur j){
         j.ajouterCash(montant);
    }
}
