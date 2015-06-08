package jeu;

public enum CouleurPropriete {
    bleuFonce, orange, mauve, violet, bleuCiel, jaune, vert, rouge;
    
        @Override
    public String toString() {
        // 34 bleu 31 rouge
        String couleur = "";
        if (this == bleuFonce) {
            couleur = "34";
        } else {
            couleur = "31";
        }
        return "\033[" + couleur + "m" + super.toString() + "\033[0m";
    }
}
