package jeu;

public class Gare extends CarreauPropriete {

    public int CalculMontantLoyer(int nb) {
        int cash = this.getloyerBase()*nb;
        return nb;
    }
       
    @Override
    public int calculLoyer() {
      Joueur proprio = this.getProprietaire();
      int NbGare = proprio.getNbGare();
      int loyer = this.CalculMontantLoyer(NbGare);
      return loyer;
    }
}
