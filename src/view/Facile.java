package src.view;

import src.model.LigneIndice;
import src.model.ModeJeu;

public class Facile implements ModeJeu {
    /**
     * Affiche les indices selon le mode de jeu
     *
     * @param ligne La ligne d'indices visée
     */
    public  void afficherIndices(LigneIndice ligne) {
        for (int i = 0; i < ligne.getTailleCombinaison(); i++)
            System.out.print(ligne.getIndices().get(i) + " | ");
        System.out.println();
    }
}
