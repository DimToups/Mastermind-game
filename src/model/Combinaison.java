package src.model;

import src.model.enums.Couleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe modèle représentant une combinaison de couleurs
 */
public class Combinaison {
    private final int tailleCombi;
    private final List<Couleur> combinaison = new ArrayList<>();

    /**
     * Créé une instance de Combinaison
     */
    public Combinaison() {
        this.tailleCombi = 4;
        for (int i = 0; i < tailleCombi; i++)
            this.combinaison.add(Couleur.ABSENT);
    }
    /**
     * Créé une instance de Combinaison
     *
     * @param tailleCombi La taille de la combinaison
     */
    public Combinaison(int tailleCombi) {
        this.tailleCombi = tailleCombi;
        for (int i = 0; i < tailleCombi; i++)
            this.combinaison.add(Couleur.ABSENT);
    }

    /**
     * Génère une combinaison aléatoire selon une taille donnée
     *
     * @param taille La taille de la combinaison
     * @return La combinaison générée
     */
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

    /**
     * Renvoi un booléen sur l'état de la combinaison
     *
     * @return Un booléen sur l'état de la combinaison (true : la combinaison est vide, false dans le cas contraire)
     */
    public boolean estVide(){
        boolean estVide = true;
        int i = 0;
        while(estVide && i < combinaison.size()) {
            if (combinaison.get(i) != Couleur.ABSENT)
                estVide = false;
            i++;
        }

        return estVide;
    }

    /**
     * Renvoi les couleurs formant la combinaison
     *
     * @return Les couleurs de la combinaison
     */
    public List<Couleur> getCombinaison() {
        return this.combinaison;
    }

    /**
     * Défini la couleur de la combinaison à un index donné
     *
     * @param index L'index de la combinaison à modifier
     * @param couleur La couleur voulue
     */
    public void setCouleur(int index, Couleur couleur) {
        combinaison.set(index, couleur);
    }

    /**
     * Renvoi la taille de la combinaison
     *
     * @return La taille de la combinaison
     */
    public int getTailleCombinaison() {
        return tailleCombi;
    }

    /**
     * Renvoi l'état de la combinaison
     *
     * @return L'état de la combinaison (true pour complet et false pour incomplet
     */
    public boolean estComplete() {
        for (Couleur couleur : combinaison)
            if (couleur == Couleur.ABSENT)
                return false;
        return true;
    }

    public  List<Couleur>  getCouleurs() {
        return combinaison;
    }
}
