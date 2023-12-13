package src.model;

import java.util.ArrayList;
import java.util.List;

public class Manche {
    private int tentativeActuelle = 0;
    private final int nbTentatives;
    private final List<Tentative> tentatives = new ArrayList<>();
    private final Combinaison combinaisonSecrete;
    private final MastermindObserver observer;
    public Manche(MastermindObserver observer, int tailleCombi, int nbTentatives, ModeJeu modeJeu) {
        this.observer = observer;
        this.nbTentatives = nbTentatives;
        this.combinaisonSecrete = Combinaison.genererCombinaison(tailleCombi);

        // Initialisation des tentatives
        for (int i = 0; i < nbTentatives; i++)
            this.tentatives.add(new Tentative(observer, tailleCombi, modeJeu));
    }
    // Méthode pour jouer la manche
    public void jouerManche() {
        boolean fini = false;
        while (!fini){
            Tentative tentative = tentatives.get(tentativeActuelle);
            tentative.lancerTentative();

            // Evaluation de la tentative
            if (tentative.evaluerTentative(combinaisonSecrete)) {
                fini = true;
                System.out.println("Vous avez trouvé la combinaison, bravo !");
            }
            else if (tentativeActuelle > nbTentatives) {
                fini = true;

                System.out.println("Vous n'avez pas trouvé la combinaison secrète. La voici :");
                for(int i = 0; i < combinaisonSecrete.getCombinaison().size(); i++)
                    System.out.print(this.combinaisonSecrete.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                            + this.combinaisonSecrete.getCombinaison().get(i).name().substring(1).toLowerCase()
                            + "  | ");
            }
            else
                tentativeActuelle++;
        }
    }
    // Méthode pour calculer le score de la manche
    public int calculerScore() {return tentatives.get(tentativeActuelle).calculerScore();}

    public Combinaison getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    public int getNbTentatives() {
        return nbTentatives;
    }

    public int getTentativeActuelle() {
        return tentativeActuelle;
    }

    public List<Tentative> getTentatives() {
        return tentatives;
    }
}
