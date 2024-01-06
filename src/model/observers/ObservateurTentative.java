package src.model.observers;

import src.model.Combinaison;
import src.model.LigneIndice;

public interface ObservateurTentative {
    /**
     * Permet de changer la couleur dans une combinaison
     *
     * @param combinaison La combinaison à modifier
     */
    void changerCouleur(Combinaison combinaison);

    /**
     * Affiche les indices passés en paramètres
     *
     * @param indices Les indices à afficher
     */
    void afficherIndices(LigneIndice indices);

    /**
     * Affiche le résumé d'une tentative
     *
     * @param combinaison La combinaison à afficher
     */
    void affichageTentative(Combinaison combinaison);

    /**
     * Demande à l'utilisateur s'il veut mettre fin à la tentative
     */
    void demanderFinTentative();

    /**
     * Fini la manche suite à la réussite du joueur
     */
    void finirManche();

    /**
     * Affiche une combinaison
     *
     * @param combinaison La combinaison à afficher
     */
    void affichageCombinaison(Combinaison combinaison);
}
