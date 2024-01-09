package src.model.userInterfaces;

import src.model.LigneIndice;

/**
 * Interface modèle servant de modèle pour les modes d'affichages selon le mode de jeu
 */
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
