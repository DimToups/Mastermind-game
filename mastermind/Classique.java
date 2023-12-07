package mastermind;

public class Classique implements ModeJeu {
    public void afficherCouleurs(Combinaison combinaison, String titre) {
        System.out.print(titre + " : ");
        for (int i = 0; i < combinaison.getTailleCombi(); i++) {
            System.out.print(combinaison.getCouleurCombinaison(i) + " ");
        }
        System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
    }

    public  void afficherIndex(LigneIndice ligne, String titre) {
        System.out.print(titre + " : " +ligne.getIndices()[2] + " mal placer");
        for (int i = 0; i <ligne.getIndices()[0]; i++) {
            System.out.print(Indice.BIEN_PLACE+ " ");
        }
        for (int i = 0; i <ligne.getIndices()[1]; i++) {
            System.out.print(Indice.MAL_PLACE+ " ");
        }
        System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
    }

}
