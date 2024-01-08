package src.model.userInterfaces;

/**
 * Une interface modèle implémentant toutes les méthodes dont une partie a besoin
 */
public interface ObservateurPartie {
    /**
     * Fait entrer l'observateur en mode initialisation de partie
     */
    void entrerModeInitialisation();

    /**
     * Demande à l'utilisateur toutes les questions pour créer une instance de Joueur
     */
    void creerJoueur();

    /**
     * Demande à l'utilisateur le nombre de manches qu'il veut faire
     */
    void deciderNbManches();

    /**
     * Demande à l'utilisateur la taille des combinaisons à composer
     */
    void deciderTailleCombinaison();

    /**
     * Demande à l'utilisateur le nombre de tentatives qu'il veut pour ses manches
     */
    void deciderNbTentatives();

    /**
     * Demande à l'utilisateur le mode de jeu auquel il veut jouer
     */
    void deciderModeJeu();

    /**
     * Demande à l'utilisateur s'il veut valider les paramètres de jeu
     */
    void demanderFinInitialisation();

    /**
     * Met fin à la partie
     *
     * @param score Le score de la partie
     */
    void finirPartie(int score);
}
