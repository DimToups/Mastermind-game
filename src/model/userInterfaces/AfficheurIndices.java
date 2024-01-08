package src.model.userInterfaces;

import src.model.LigneIndice;

public interface AfficheurIndices {
    /**
     * Affiche les indices suivant le mode de jeu "Facile"
     * @param indices Les indices à afficher
     */
    void afficherIndicesFacile(LigneIndice indices);

    /**
     * Affiche les indices suivant le mode de jeu "Classique"
     * @param indices Les indices à afficher
     */
    void afficherIndicesClassique(LigneIndice indices);

    /**
     * Affiche les indices suivant le mode de jeu "Numerique"
     * @param indices Les indices à afficher
     */
    void afficherIndicesNumerique(LigneIndice indices);
}
