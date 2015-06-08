package jeu;

public class Gare extends CarreauPropriete {

    public Gare(Monopoly monopoly) {
        super(monopoly);
    }

    public int CalculMontantLoyer(int nb) {
        int cash = this.getloyerBase()*nb;
        return nb;
    }
       
    @Override
    public int calculLoyer(Joueur j) {
      Joueur proprio = this.getProprietaire();
      int NbGare = proprio.getNbGare();
      int loyer = this.CalculMontantLoyer(NbGare);
      return loyer;
    }
}
