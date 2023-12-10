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
    ROSE;
    public static List<Couleur> getVraiesCouleurs(){
        List<Couleur> vraiesCouleurs = new ArrayList<>();
        for (Couleur couleur : Couleur.values())
            if(couleur != Couleur.ABSENT)
                vraiesCouleurs.add(couleur);

        return vraiesCouleurs;
    }
}
