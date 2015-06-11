package jeu;

import java.util.Random;

public class CarreauTirage extends CarreauAction {

    private String typeTirage;
    Random rand;

    /**
     * Initialise la case et les tas de cartes : population et mélange.
     *
     * @param mono l'instance de Monopoly
     */
    public CarreauTirage(Monopoly mono) {
        super(mono);
        rand = new Random();
    }

    /**
     * Définir si la case est une case Chance ou Caisse de communauté.
     *
     * @param type le type de case : chance ou caisse de communauté
     */
    public void setTypeTirage(String type) {
        this.typeTirage = type;
    }

    /**
     * Tire une carte, en applique les effets et replace la carte en bas de la
     * pile.
     *
     * @param j Le joueur courant
     */
    @Override
    public void action(Joueur j) {
        Carte c = this.getMonopoly().tirerCarte(typeTirage);
        this.getMonopoly().getInterfaceJeu().messageCarte(c, typeTirage);
        
        if (c instanceof CarteGain) {
            CarteGain carte;
            carte = (CarteGain) c;
            int prix = carte.getMontant();
            if((carte.getNumero()==3 || carte.getNumero()==5) && typeTirage.equalsIgnoreCase("chance")){
                carte.setcasParticuler("prixParConstruction");
                carte.action(j);
            }
            
            if(carte.getNumero()==3 && typeTirage.equalsIgnoreCase("Caisse de Communauté")){
                carte.setcasParticuler("anniversaire");
                carte.action(j);
            } 
            
            else if (prix==0) {    // prix = 0, ce qui veut dire que la carte est une carte sortie de prison
                j.ajouterCarteSortiePrison(carte);
                this.getMonopoly().retirerCartePrison(typeTirage);
            }
            
            else {
                carte.action(j);
            }
        } 
        
        else if (c instanceof CarteMouvement){
            CarteMouvement mouvement;
            mouvement = (CarteMouvement) c;
            int posCouranteJoueur = j.getPositionCourante();
            mouvement.action(j);
            //Le joueur subit l'action de la case sur laquelle il atterit.
        }
        this.getMonopoly().RemettreCarte(typeTirage);
    }
}
