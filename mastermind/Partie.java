package mastermind;

import java.util.ArrayList;
import java.util.List;

public class Partie {

    private int score;
    private Joueur joueur;
    private List<Manche> manches;

    public Partie(Joueur joueur, int nbManche, int tailleCombi, int nbTentatives, ModeJeu modeJeu) {
        this.joueur = joueur;

        this.manches = new ArrayList<Manche>();
        // Initialiser les manche
        for (int i = 0; i < nbManche; i++) {
            this.manches.add(new Manche(tailleCombi, nbTentatives,modeJeu));
        }
        this.score = 0;
    }

    public void lancerPartie() {
        for (Manche manche : manches)
        {
            manche.jouerManche();
            score += manche.calculerScore();
            finirPartie();

        }
    }

    public Manche getManche(int index) {
        return manches.get(index);
    }

    private void finirPartie() {
        System.out.println("La partie est terminée. Score final : " + score);
    }
}
