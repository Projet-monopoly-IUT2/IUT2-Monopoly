package jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(Monopoly monopoly) {
        super(monopoly);
    }

    public int calculMontantLoyer(int nbcompagnies) {
        throw new UnsupportedOperationException();
    }

    public Joueur getProprietaire() {
        return super.getProprietaire();
    }

    public int calculLoyer() {
        throw new UnsupportedOperationException();
    }

    public void action() {
        throw new UnsupportedOperationException();
    }
}
