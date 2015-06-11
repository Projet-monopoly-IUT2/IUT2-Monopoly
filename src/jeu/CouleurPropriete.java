package jeu;

public enum CouleurPropriete {
    bleuFonce, orange, mauve, violet, bleuCiel, jaune, vert, rouge;
    
    /**
     * Affiche les labels, colorés avec la couleur qu'ils désignent.
     * @return Nom du label coloré
     */
        @Override
    public String toString() {
        // 34 bleu 31 rouge
        String couleur = "";
        if (this == bleuFonce) {
            couleur = "34";
        } 
        else if ( this == rouge){
            couleur = "31";
        }
        else if ( this == vert){
            couleur = "32";
        }
        else if ( this == jaune){
            couleur = "33";
        }
        else if ( this == violet){
            couleur = "35";
        }
        else if ( this == bleuCiel){
            couleur = "36";
        }
        else if ( this == orange){
            couleur = "31";
        }
        else if ( this == mauve){
            couleur = "35";
        }
        return "\033[" + couleur + "m" + super.toString() + "\033[0m";
    }
    
    
    
}
