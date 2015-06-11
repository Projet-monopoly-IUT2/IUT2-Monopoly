package jeu;

public abstract class Carreau {

    private int numero;
    private String nomCarreau;
    protected Monopoly monopoly;

    /**
     *
     * @param monopoly Instance de monopoly de la partie en cours
     */
    public Carreau(Monopoly monopoly) {
        this.monopoly = monopoly;
    }

    /**
     *
     * @return le numéro de la case
     */
    public int getNumero() {
        return this.numero;
    }

    /**
     *
     * @param num Numéro du carreau
     */
    public void setNumero(int num) {
        this.numero = num;
    }

    /**
     *
     * @param nomCarreau Le nom de la rue ou de l'action associée au carreau.
     */
    public void setNomCarreau(String nomCarreau) {
        this.nomCarreau = nomCarreau;
    }

    /**
     *
     * @return le nom du carreau : le nom de la proporiété, de la taxe, ou du
     * type de tirage
     */
    public String getNomCarreau() {
        return nomCarreau;
    }

    /**
     *
     * @return l'instance de monopoly à laquelle apprtient le carreau
     */
    public Monopoly getMonopoly() {
        return this.monopoly;
    }

    /**
     * Effectue l'action prévue par le type de carreau.
     *
     * @param j le joueur courant
     * @throws Faillite si le joueur ne peut pas payer
     */
    public abstract void action(Joueur j) throws Faillite;
}
