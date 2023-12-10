package src.model;

public class Facile implements ModeJeu{
    public  void afficherIndices(LigneIndice ligne) {
        for (int i = 0; i < ligne.getTailleCombi(); i++)
            System.out.print(ligne.getIndices().get(i) + " | ");
        System.out.println();
    }
}
