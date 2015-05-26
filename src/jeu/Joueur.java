package jeu;

import java.util.ArrayList;

public class Joueur {
	private String nomJoueur;
	private int cash = 1500;
	private Monopoly monopoly;
	private ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
	private ArrayList<Gare> gares = new ArrayList<Gare>();
	private Carreau positionCourante;
	private ArrayList<ProprieteAConstruire> proprietesAConstruire = new ArrayList<ProprieteAConstruire>();

	public int getCash() {
		return this.cash;
	}

	public void setCash(int cash) {
		this.cash = cash;
	}

	/**
	 * Renvoit le numero du carreau courant du joueur
	 */
	public int getPositionCourante() {
		throw new UnsupportedOperationException();
	}

	public void deplacer(Carreau c) {
		throw new UnsupportedOperationException();
	}

	public void setCarreau(Carreau c) {
		positionCourante = c;
	}

        public void setNomJoueur(String nomJ){
            nomJoueur = nomJ;
        }
        
	public void achat(Carreau c) {
		throw new UnsupportedOperationException();
	}

	public void operation(int prix) {
		throw new UnsupportedOperationException();
	}

	/**
	 * nom + cash + position du joueur
	 */
	public String getInfosJoueur() {
		throw new UnsupportedOperationException();
	}

	public String getInfosProprietes() {
		throw new UnsupportedOperationException();
	}

	public CarreauPropriete getProprietes() {
		throw new UnsupportedOperationException();
	}

	public String getNomjoueur() {
		throw new UnsupportedOperationException();
	}

	public int getNbGare() {
		throw new UnsupportedOperationException();
	}

	public int calculNCash(Object argent) {
		throw new UnsupportedOperationException();
	}

}