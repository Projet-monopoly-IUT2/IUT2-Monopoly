package jeu;

public class CarreauArgent extends CarreauAction {

    private int montant;

    public void setMontant(int montant) {
        this.montant = montant;
    }
    
    @Override
    public void action(Joueur j){
         j.setCash(j.getCash()+montant);
    }
}
