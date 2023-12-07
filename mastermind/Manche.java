package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Manche {
    private int tentativeActuelle;
    private int nbTentatives;
    private List<Tentative> tentatives;

    private Combinaison combinaisonSecrete ;

    // Constructeur
    public Manche(int tailleCombi, int nbTentativesn ,ModeJeu modeJeu) {
        this.tentativeActuelle = 0;
        this.nbTentatives = nbTentativesn;
        this.tentatives = new ArrayList<>();
        combinaisonSecrete=getCombinaisonSecrete(tailleCombi);

        // Initialiser les tentatives
        for (int i = 0; i < nbTentativesn; i++) {
            this.tentatives.add(new Tentative(tailleCombi, modeJeu));
        }
    }

    // Méthode pour jouer la manche
    public void jouerManche() {
        boolean fini=false;
        while (!fini) {
            Tentative tentative = tentatives.get(tentativeActuelle);
            tentative.lancerTentative();
            if (tentative.evaluerTentative(combinaisonSecrete))
            {
                fini=true;
            } else if (tentativeActuelle > nbTentatives) {
                fini=true;

            } else {
                tentativeActuelle++;
            }





        }


    }

    // Méthode pour calculer le score de la manche
    public int calculerScore() {

        return tentatives.get(tentativeActuelle).calculerScore();
    }

    // Méthode pour obtenir la combinaison secrète de la manche
    public Combinaison getCombinaisonSecrete(int taille) {
        return Combinaison.genererCombinaisonSecrete(taille);
    }

    // Méthode pour obtenir une tentative spécifique
    public Tentative getTentative(int index) {
        if (index >= 0 && index < tentatives.size()) {
            return tentatives.get(index);
        } else {
            // Gérer l'erreur, par exemple, retourner null ou lever une exception
            return null;
        }
    }
}
