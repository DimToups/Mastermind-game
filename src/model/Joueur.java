package src.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modèle représentant le profil d'un joueur
 */
public class Joueur {
    private final String nom;
    private List<Integer> score = new ArrayList<>(1);

    /**
     * Créé une instance de Joueur
     *
     * @param nom Le nom du joueur
     */
    public Joueur(String nom) {
        this.nom = nom;
    }

    /**
     * Renvoi le nom du joueur
     *
     * @return Le nom du joueur
     */
    public String getNom() {
        return nom;
    }

    /**
     * Renvoi la liste des scores du joueur
     *
     * @return La liste des scores du joueur
     */
    public List<Integer> getScores() {
        return score;
    }

    /**
     * Ajoute un score au joueur
     *
     * @param score Le score effectué
     */
    public void ajouterScorePartie(int score) {
      this.score.add(score);
    }
}
