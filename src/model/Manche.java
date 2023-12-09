package model;

import java.util.ArrayList;
import java.util.List;

public class Manche {
    private int tentativeActuelle;
    private final int nbTentatives;
    private List<Tentative> tentatives;
    private final Combinaison combinaisonSecrete ;
    public Manche(int tailleCombi, int nbTentatives, ModeJeu modeJeu) {
        this.tentativeActuelle = 0;
        this.nbTentatives = nbTentatives;
        this.tentatives = new ArrayList<>();
        combinaisonSecrete = Combinaison.genererCombinaisonSecrete(tailleCombi);
        combinaisonSecrete.afficherCouleurs("pour t'aider si ta la flemme de fair tt le  jeu et que tu veux voir si les classe fonctionne   Combinaison secret :" );
        // Initialiser les tentatives
        for (int i = 0; i < nbTentatives; i++)
            this.tentatives.add(new Tentative(tailleCombi, modeJeu));
    }
    // Méthode pour jouer la manche
    public void jouerManche() {
        boolean fini = false;
        while (!fini){
            Tentative tentative = tentatives.get(tentativeActuelle);
            tentative.lancerTentative();
            if (tentative.evaluerTentative(combinaisonSecrete))
                fini=true;
            else if (tentativeActuelle > nbTentatives)
                fini=true;
            else
                tentativeActuelle++;
        }
    }
    // Méthode pour calculer le score de la manche
    public int calculerScore() {
        return tentatives.get(tentativeActuelle).calculerScore();
    }

    // Méthode pour obtenir la combinaison secrète de la manche
    public Combinaison getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    // Méthode pour obtenir une tentative spécifique
    public Tentative getTentative(int index) {
        if (index >= 0 && index < tentatives.size())
            return tentatives.get(index);
        else
            return null; // Gérer l'erreur, par exemple, retourner null ou lever une exception
    }
}
