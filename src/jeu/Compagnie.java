package jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(Monopoly monopoly) {
        super(monopoly);
    }


    public Joueur getProprietaire() {
        return super.getProprietaire();
    }
    /**
     * Calcule le loyer dû en fonction d'un lancer de dés et du nombre de compagnies
     * possédées par le propriétaire de la case.
     * @param j le joueur courant
     * @return le montant du loyer
     */
    @Override
    public int calculLoyer(Joueur j) {
          Joueur jproprio = this.getProprietaire();
          int nbCompagnies = jproprio.getNbCompagnies();
          
          int loyer = 0;
          int nouveauCash;
          
          if (nbCompagnies == 1) {
              loyer = 4* super.getMonopoly().getResultatDes();
         
          }
          else if (nbCompagnies == 2) {
              loyer = 10*  super.getMonopoly().getResultatDes();
          

          }
           nouveauCash = j.calculCashApresOperation(loyer);
           super.getMonopoly().InfosLoyer(jproprio, loyer, nouveauCash);
         
         return loyer; 
   }
    
    /**
     * En fonction du propriétaire actuel, ne rien faire, proposer l'achat de
     * la case ou faire payer le loyer.
     * @param j le joueur courant
     */
    @Override
    public void action(Joueur j) {
        Joueur jproprio = this.getProprietaire();
        if (jproprio ==  null) {
            super.achatPropriete(j);
            }
        else {
            if (jproprio != j) {
                int loyer = calculLoyer(j);
                j.payerLoyer(loyer);
                jproprio.recevoirLoyer(loyer);
                
            }
        }
    }
}
