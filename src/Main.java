import controller.GestionnaireJeu;

public class Main {
    public static void main(String[] args) {
        GestionnaireJeu jeu = new GestionnaireJeu();
        jeu.demarrerPartie();


    /*    // Définir la taille de la combinaison
        int tailleCombi = 4;
        int nbTentative=11;

      //////////////////////////////////////////////  //Créer un manche
        Manche manche= new Manche(tailleCombi,nbTentative,new Classique());
        manche.jouerManche();

        System.out.println("voitre score : "+manche.calculerScore());

        //////////////////////////////// main pour tentative
        //Créer une tentative
        Tentative tent=new Tentative(tailleCombi , new Classique());

        // Créer une combinaison secrète
      Combinaison combinaisonSecrete = Combinaison.genererCombinaisonSecrete(tailleCombi);
        System.out.println("Combinaison secrète générée : ");

        // Créer une combinaison initiale
            Combinaison combinaisonInitiale = new Combinaison(tailleCombi);
        System.out.println("Combinaison initiale : ");
       combinaisonInitiale.setCouleurCombinaison(0, Couleur.Vert);
        combinaisonInitiale.setCouleurCombinaison(1, Couleur.Rouge);
        combinaisonInitiale.setCouleurCombinaison(2, Couleur.Vert);
        combinaisonInitiale.setCouleurCombinaison(3, Couleur.Bleu);
        // Modifier quelques couleurs dans la combinaison initiale (à des fins de test)
      tent.lancerTentative();

        //    System.out.println("Combinaison modifiée : " + combinaisonInitiale.getCombinaison());

        // Vérifier si la combinaison initiale est complète
        if (tent.evaluerTentative(combinaisonInitiale)) {
            System.out.println("gg ");
        } else {
            System.out.println("rip");
        }

       afficherCouleurs(combinaisonSecrete, "Couleurs de la combinaison secrète");

        // Afficher les couleurs de la combinaison initiale
        afficherCouleurs(tent.getCombinaisonEntree(), "Couleurs de la combinaison initiale");

        afficherIndex(tent.getLigneIndice(),"ligne indice :");

*/
    }
}
