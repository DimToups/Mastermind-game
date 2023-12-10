package src.model;

public interface MastermindObserver {
    void miseEnPlacePlateau();
    void affichageTentative(Combinaison combinaison);
    boolean afficherTentativeComplete();
    void changerCouleur(Combinaison combinaison);
    void afficherIndices(LigneIndice indices);
    Joueur creerJoueur();
    int deciderNbManches();
    int deciderTailleCombinaison();
    int deciderNbTentatives();
    ModeJeu deciderModeJeu();
}
