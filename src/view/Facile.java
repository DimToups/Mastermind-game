package src.view;

import src.model.LigneIndice;
import src.model.ModeJeu;
import src.model.enums.Indice;

public class Facile implements ModeJeu {
    /**
     * Affiche les indices selon le mode de jeu
     *
     * @param ligne La ligne d'indices visée
     */
    public  void afficherIndices(LigneIndice ligne) {
        if(!ligne.getIndices().contains(Indice.BIEN_PLACE) && !ligne.getIndices().contains(Indice.MAL_PLACE))
            System.out.println("/");
        for (int i = 0; i < ligne.getTailleCombinaison(); i++)
            System.out.print(Indice.nomValide(ligne.getIndices().get(i)) + " | ");
        System.out.println();
    }
}
