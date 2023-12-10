package src.model;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int score = 0;
    private Joueur joueur;
    private List<Manche> manches = new ArrayList<>();
    public Partie(Joueur joueur, int nbManche, int tailleCombi, int nbTentatives, ModeJeu modeJeu) {
        this.joueur = joueur;
        // Initialisation des manches
        for (int i = 0; i < nbManche; i++)
            this.manches.add(new Manche(tailleCombi, nbTentatives,modeJeu));
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
