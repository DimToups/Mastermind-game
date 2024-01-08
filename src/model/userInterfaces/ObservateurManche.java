package src.model.userInterfaces;

import src.model.Combinaison;

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

    /**
     * Affiche les informations de la manche avant de passer à la prochaine
     */
    void prochaineManche(Combinaison combinaisonSecrete);
}
