package jeu;

public class Gare extends CarreauPropriete {

    public Gare(Monopoly monopoly) {
        super(monopoly);
        super.setMontantLoyer(25);
    }


    /**
     * Calcule le loyer en fonction du nombre de gares possédées par le
     * propriétaire de cette case.
     * @param j le joueur courant
     * @return le montant su loyer
     */
    @Override
    public int calculLoyer(Joueur j) {
      Joueur jproprio = this.getProprietaire();
      int NbGare = jproprio.getNbGare();
      int loyer = this.getMontantLoyer()*NbGare;
     
      int nouveauCash = j.calculCashApresOperation(loyer);
      super.getMonopoly().InfosLoyer(jproprio, loyer, nouveauCash);
      
      return loyer;
    }
}
