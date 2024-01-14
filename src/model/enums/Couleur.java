package src.model.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumération représentant toutes les couleurs possibles dans une combinaison
 */
public enum Couleur {
    /**
     * Instance servant à différencier les cases vides d'une combinaison
     */
    ABSENT,
    /**
     * La couleur de case de combinaison bleue
     */
    BLEU,
    /**
     * La couleur de case de combinaison jaune
     */
    JAUNE,
    /**
     * La couleur de case de combinaison rouge
     */
    ROUGE,
    /**
     * La couleur de case de combinaison vert
     */
    VERT,
    /**
     * La couleur de case de combinaison orange
     */
    ORANGE,
    /**
     * La couleur de case de combinaison violet
     */
    VIOLET,
    /**
     * La couleur de case de combinaison turquoise
     */
    CYAN,
    /**
     * La couleur de case de combinaison rose
     */
    ROSE
    ;

    /**
     * Renvoi les vraies couleurs de l'énumération
     *
     * @return Les vraies couleurs de l'énumération
     */
    public static List<Couleur> getVraiesCouleurs(){
        List<Couleur> vraiesCouleurs = new ArrayList<>();
        for (Couleur couleur : Couleur.values())
            if(couleur != Couleur.ABSENT)
                vraiesCouleurs.add(couleur);

        return vraiesCouleurs;
    }

    /**
     * Retourne le nom de la couleur en minuscules et avec une majuscule au début du mot
     *
     * @param couleur La couleur à traiter
     * @return Le nom de la couleur
     */
    public static String nomValide(Couleur couleur){
        return couleur.name().toUpperCase().charAt(0) + couleur.name().toLowerCase().substring(1);
    }
}
