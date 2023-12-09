package model;

public class Classique implements ModeJeu {

    public  void afficherIndices(LigneIndice ligne, String titre) {
        System.out.print(titre + " : ");
           for (int i = 0; i <ligne.getIndices()[0]; i++) {
            System.out.print(Indice.BIEN_PLACE+ " ");
        }
        for (int i = 0; i <ligne.getIndices()[1]; i++) {
            System.out.print(Indice.MAL_PLACE+ " ");
        }
        System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
    }

}
