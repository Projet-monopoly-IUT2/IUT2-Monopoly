package jeu;

public abstract class CarreauPropriete extends Carreau {

    private int loyerBase;
    private int prixAchat;
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

    /**
     * @return chaine de caractère contenant nom et numéro de la case
     */
 

    public void achat(Joueur j) {
        throw new UnsupportedOperationException();
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

    //public abstract int calculLoyer(Joueur j);

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
     * Propose la construction d'une maison sur un terrain de la même couleur 
     * que celui ou se trouve le joueur courant, et la construit si nécéssaire.
     */

    public int getloyerBase(){
        return loyerBase;
    }

    public void setLoyerBase(int loyerBase) {
        this.loyerBase = loyerBase;
    }
    
    
    /**
     * Calcule le loyer dû.
     * @param j le joueur courant
     * @return le loyer à payer
     */
    public abstract int calculLoyer(Joueur j); 
       
   
    
    
    
}
