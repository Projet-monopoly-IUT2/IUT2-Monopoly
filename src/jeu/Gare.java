package jeu;

public class Gare extends CarreauPropriete {

    public Gare(Monopoly monopoly) {
        super(monopoly);
        super.setLoyerBase(25);
    }


       
    @Override
    public int calculLoyer(Joueur j) {
      Joueur jproprio = this.getProprietaire();
      int NbGare = jproprio.getNbGare();
      int loyer = this.getloyerBase()*NbGare;
     
      int nouveauCash = j.calculCashApresOperation(loyer);
      super.getMonopoly().InfosLoyer(jproprio, loyer, nouveauCash);
      
      return loyer;
    }
}
