package model;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private final String nom;
    private List<Integer> score = new ArrayList<>(1);
    public Joueur(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public List<Integer> getScores() {
        return score;
    }
    public void ajoutScorePartie(Integer score) {
      this.score.add(score);
    }
}
