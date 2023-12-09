package model;

public class Numerique implements ModeJeu{


        public  void afficherIndices(LigneIndice ligne, String titre) {
            System.out.print(titre + " : "+ligne.getIndices()[0]+" bien placer  et"+ ligne.getIndices()[1] + " mal placer");

            System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
        }


}
