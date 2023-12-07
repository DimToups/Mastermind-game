package mastermind;

public class numerique implements ModeJeu{

        public void afficherCouleurs(Combinaison combinaison, String titre) {
            System.out.print(titre + " : ");
            for (int i = 0; i < combinaison.getTailleCombi(); i++) {
                System.out.print(combinaison.getCouleurCombinaison(i) + " ");
            }
            System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
        }

        public  void afficherIndex(LigneIndice ligne, String titre) {
            System.out.print(titre + " : ");
            for (int i = 0; i < ligne.getTailleCombi(); i++) {
                System.out.print(ligne.getIndice(i) + " ");
            }
            System.out.println(); // Pour passer à la ligne après l'affichage des couleurs
        }


}
