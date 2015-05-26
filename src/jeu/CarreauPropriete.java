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

	public void setMontantLoyer(Object montantLoyer) {
		throw new UnsupportedOperationException();
	}

	/**
	 * nom + numero + nb hotels et maisons
	 */
	public String getInfosCarreau() {
		throw new UnsupportedOperationException();
	}

	public void achat(Joueur j) {
		throw new UnsupportedOperationException();
	}

	public void setProprietaire(Joueur j) {
		throw new UnsupportedOperationException();
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