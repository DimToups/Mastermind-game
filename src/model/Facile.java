package model;

public class Facile implements ModeJeu{
    public  void afficherIndices(LigneIndice ligne, String titre) {
        System.out.print(titre + " : ");
        for (int i = 0; i < ligne.getTailleCombi(); i++)
            System.out.print(ligne.getIndice(i) + " ");
        System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
    }
}
