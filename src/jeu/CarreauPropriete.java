package jeu;

public abstract class CarreauPropriete extends Carreau {

    private int loyerBase;
    private int prixAchat;
    private int montantAchat;
    private int montantLoyer;
    private Joueur proprietaire;

    public void achatPropriete() {
        throw new UnsupportedOperationException();
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

    public abstract int calculLoyer();

    /**
     *
     * @param j joueur courant
     */
    @Override
    public void action(Joueur j) {
        Joueur jProprio = getProprietaire();
        if (jProprio == null) {
            achatPropriete();
        } else if (jProprio != j) {
                j.payerLoyer(calculLoyer());
                jProprio.recevoirLoyer(calculLoyer());
        }
    }

    public void construire() {
        throw new UnsupportedOperationException();
    }

}
