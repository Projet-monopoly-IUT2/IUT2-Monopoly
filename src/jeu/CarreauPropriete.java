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

    public abstract void action();
}
