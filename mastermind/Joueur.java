package mastermind;

public class Joueur {
    private String nom;
    private  int score;

    public Joueur(String nom) {
        this.nom = nom;
        // Initialisation du tableau des scores
        this.score = 0;
    }

    public String getNom() {
        return nom;
    }

    public Integer getScores() {
        return score;
    }

    public void ajoutScorePartie(Integer score) {
        // Logique pour ajouter un score à la liste des scores
      this.score=this.score+score;
    }
}
