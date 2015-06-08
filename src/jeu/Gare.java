package jeu;

public class Gare extends CarreauPropriete {

    public Gare(Monopoly monopoly) {
        super(monopoly);
        super.setLoyerBase(25);
    }


       
    @Override
    public int calculLoyer(Joueur j) {
      Joueur proprio = this.getProprietaire();
      int NbGare = proprio.getNbGare();
      int loyer = this.getloyerBase()*NbGare;
      Joueur jproprio = super.getProprietaire();
      int nouveauCash = jproprio.calculCashApresOperation(loyer);
      super.getMonopoly().InfosLoyer(jproprio, loyer, nouveauCash);
      
      return loyer;
    }
}
