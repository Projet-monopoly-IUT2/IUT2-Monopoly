package jeu;

public abstract class Carreau {
	private int numero;
	private String nomCarreau;
	private Monopoly monopoly;

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int num) {
		this.numero = num;
	}
        
        public void setNomCarreau(String nomCarreau) {
            this.nomCarreau = nomCarreau;
        }
        
        public String getNomCarreau() {
            return nomCarreau;
        }
}