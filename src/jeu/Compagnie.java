package jeu;

public class Compagnie extends CarreauPropriete {

    public Compagnie(Monopoly monopoly) {
        super(monopoly);
    }


    public Joueur getProprietaire() {
        return super.getProprietaire();
    }

    @Override
    public int calculLoyer(Joueur j) {
          Joueur jproprio = this.getProprietaire();
          int nbCompagnies = jproprio.getNbCompagnies();
          String nomP = jproprio.getNomJoueur();
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
