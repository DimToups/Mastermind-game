package src.model.observers;

public interface ObservateurManche {
    /**
     * Affiche le plateau de jeu
     */
    void miseEnPlacePlateau();

    /**
     * Demande à l'utilisateur s'il veut mettre fin à la tentative actuelle
     *
     * @return La réponse de l'utilisateur
     */
    boolean demanderValidationTentative();
}
