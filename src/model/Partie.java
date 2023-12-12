package src.model;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int score = 0 ;
    private final Joueur joueur;
    private final List<Manche> manches = new ArrayList<>();
    private final MastermindObserver observer;
    public Partie(MastermindObserver observer, Joueur joueur, int nbManche, int tailleCombi, int nbTentatives, ModeJeu modeJeu) {
        this.joueur = joueur;
        this.observer = observer;
        // Initialisation des manches
        for (int i = 0; i < nbManche; i++)
            this.manches.add(new Manche(observer, tailleCombi, nbTentatives,modeJeu));
    }
    public void lancerPartie() {
        for (Manche manche : manches) {
            manche.jouerManche();
            score += manche.calculerScore();
        }
        finirPartie();
    }
    public List<Manche> getManches() {
        return this.manches;
    }
    private void finirPartie() {
        System.out.println("La partie est terminée.\nScore final : " + score);
        this.joueur.ajouterScorePartie(score);
    }
}
