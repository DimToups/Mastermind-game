package src.view;

import src.model.LigneIndice;
import src.model.ModeJeu;

public class Facile implements ModeJeu {
    public  void afficherIndices(LigneIndice ligne) {
        for (int i = 0; i < ligne.getTailleCombinaison(); i++)
            System.out.print(ligne.getIndices().get(i) + " | ");
        System.out.println();
    }
}
