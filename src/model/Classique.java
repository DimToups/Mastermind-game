package src.model;

import src.model.enums.Indice;

public class Classique implements ModeJeu {
    public  void afficherIndices(LigneIndice ligne) {
        for (int i = 0; i < ligne.getIntIndices()[0]; i++)
            System.out.print(Indice.BIEN_PLACE + " | ");
        for (int i = 0; i < ligne.getIntIndices()[1]; i++)
            System.out.print(Indice.MAL_PLACE + " | ");
        System.out.println();
    }
}
