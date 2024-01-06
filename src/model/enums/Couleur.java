package src.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum Couleur {
    ABSENT,
    BLEU,
    JAUNE,
    ROUGE,
    VERT,
    ORANGE,
    VIOLET,
    TURQUOISE,
    ROSE
    ;

    /**
     * Retourne le nom de la couleur en minuscules et avec une majuscule au début du mot
     *
     * @param couleur La couleur à traiter
     * @return Le nom de la couleur
     */
    public static String nomValide(Couleur couleur){
        return couleur.name().toUpperCase().charAt(0) + couleur.name().toLowerCase().substring(1);
    }
    public static List<Couleur> getVraiesCouleurs(){
        List<Couleur> vraiesCouleurs = new ArrayList<>();
        for (Couleur couleur : Couleur.values())
            if(couleur != Couleur.ABSENT)
                vraiesCouleurs.add(couleur);

        return vraiesCouleurs;
    }
}
