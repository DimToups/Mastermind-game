package src.model.userInterfaces;

import src.model.Combinaison;
import src.model.LigneIndice;
import src.model.enums.ModeJeu;

/**
 * Une interface modèle implémentant toutes les méthodes dont une tentative a besoin
 */
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

    /**
     * Décide de la méthode d'affichage des indices
     * @param ui L'interface utilisateur utilisée
     * @param modeJeu Le mode de jeu de la partie
     * @param indices Les indices de la tentative courrante
     */
    static void deciderMethodeAffichageIndices(ObservateurUI ui, ModeJeu modeJeu, LigneIndice indices){
        switch(modeJeu){
            case FACILE -> ui.afficherIndicesFacile(indices);
            case CLASSIQUE -> ui.afficherIndicesClassique(indices);
            case NUMERIQUE -> ui.afficherIndicesNumerique(indices);
        }
    }
}
