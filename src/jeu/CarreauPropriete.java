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
     * @return nom + numero
     */
    public String getInfosCarreau() {
        return super.getNomCarreau() + " : " + String.valueOf(super.getNumero());
    }

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
     *
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

    public void construire() {
        throw new UnsupportedOperationException();
    }

    public int getloyerBase(){
        return loyerBase;
    }

    public void setLoyerBase(int loyerBase) {
        this.loyerBase = loyerBase;
    }
    
    
    
   public abstract int calculLoyer(Joueur j); 
       
   
    
    
    
}
