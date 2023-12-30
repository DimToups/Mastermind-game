package src.model;

import src.controller.GestionnaireJeu;

public abstract class ObservateurUI implements ObservateurPartie, ObservateurManche, ObservateurTentative{
    private GestionnaireJeu gestionnaire;

    /**
     * Construit et initialise l'ObservateurUI
     *
     * @param gestionnaire L'instance contrôleuse du jeu
     */
    public ObservateurUI(GestionnaireJeu gestionnaire) {
        this.gestionnaire = gestionnaire;
    }

    /**
     * Renvoi le contrôleur utilisé
     *
     * @return Le contrôleur utilisé
     */
    public GestionnaireJeu getGestionnaire() {
        return gestionnaire;
    }

    /**
     * Défini le contrôleur utilisé
     *
     * @param gestionnaire Le contrôleur voulu
     */
    public void setGestionnaire(GestionnaireJeu gestionnaire) {
        this.gestionnaire = gestionnaire;
    }
}
