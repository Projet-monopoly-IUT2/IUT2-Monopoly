package jeu;

public abstract class CarreauPropriete extends Carreau {

 
    private int montantAchat;
    private int montantLoyer;
    private Joueur proprietaire;

    public CarreauPropriete(Monopoly monopoly) {
        super(monopoly);
    }
    /**
     * Méthode invoquée pour l'achat d'une case par un joueur
     * @param j le joueur achetant la propriété
     */
    public void achatPropriete(Joueur j) {
        int cash = j.getCash();
        int PrixApayer = this.getMontantAchat();
        if (cash >= PrixApayer){
            this.getMonopoly().possibiliteAchat(j, this);
        }
        else {
            System.out.println("Vous n'avez pas assez d'argent");
        }
        
    }

    public void setMontantLoyer(int montantLoyer) {
        this.montantLoyer = montantLoyer;
    }

    public int getMontantLoyer() {
        return montantLoyer;
    }
    


  

    public void setProprietaire(Joueur j) {
        this.proprietaire = j;
    }

    public Joueur getProprietaire() {
        return proprietaire;
    }

    public int getMontantAchat() {
        return this.montantAchat;
    }

    public void setMontantAchat(int montantAchat) {
        this.montantAchat = montantAchat;
    }


    /**
     * Selon le propriétaire, va proposer d'acheter la case, construire 
     * dessus ou payer le loyer à son propriétaire
     * @param j joueur courant
     */
    @Override
    public void action(Joueur j) {
        Joueur jProprio = getProprietaire();
        if (jProprio == null) {
            achatPropriete(j);
        } else if (jProprio != j) {
             
                int loyer = calculLoyer(j);
                j.payerLoyer(loyer);
                jProprio.recevoirLoyer(loyer);
        }
    }




    
    
    /**
     * Calcule le loyer dû.
     * @param j le joueur courant
     * @return le loyer à payer
     */
    public abstract int calculLoyer(Joueur j); 
       
   
    
    
    
}
