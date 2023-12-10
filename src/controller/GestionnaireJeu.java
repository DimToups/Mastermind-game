package src.controller;

import src.model.*;
import src.view.AffichageConsole;

public class GestionnaireJeu {
    public void demarrerPartie() {
        MastermindObserver observer = new AffichageConsole();
        Partie partie = new Partie(observer, observer.creerJoueur(), observer.deciderNbManches(), observer.deciderTailleCombinaison(), observer.deciderNbTentatives(), observer.deciderModeJeu());
        partie.lancerPartie();
    }
}
