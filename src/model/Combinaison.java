package src.model;

import src.model.enums.Couleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combinaison {
    private final int tailleCombi;
    private List<Couleur> combinaison = new ArrayList<>();
    // Constructeur
    public Combinaison(int tailleCombi) {
        this.tailleCombi = tailleCombi;
        for (int i = 0; i < tailleCombi; i++)
            this.combinaison.add(Couleur.ABSENT);
    }

    //methode static pour pouvoir genere une combinaison secret
    public static Combinaison genererCombinaison(int taille) {
        Combinaison secret = new Combinaison(taille);
        Random r = new Random();
        for (int i = 0; i < taille; i++) {
            int n = r.nextInt(7);
            Couleur[] couleur = Couleur.values();
            secret.setCouleur(i, couleur[n+1]);
        }
        return secret;
    }
    // Méthode pour obtenir la couleur d'une position spécifique dans la combinaison
    public List<Couleur> getCombinaison() {
        return this.combinaison;
    }
    // Méthode pour définir la couleur à une position spécifique dans la combinaison
    public void setCouleur(int index, Couleur couleur) {
        combinaison.set(index, couleur);
    }
    // Méthode pour obtenir la taille de la combinaison
    public int getTailleCombinaison() {
        return tailleCombi;
    }
    // Méthode pour vérifier si la combinaison est complète
    public boolean estComplet() {
        for (Couleur couleur : combinaison)
            if (couleur == Couleur.ABSENT)
                return false;
        return true;
    }
}
