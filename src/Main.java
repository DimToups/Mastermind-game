package src;

import src.controller.GestionnaireJeu;
import src.model.Partie;
import src.view.AffichageConsole;
import src.view.AffichageFenetre;

public class Main {
    public static void main(String[] args) {
        GestionnaireJeu jeu = new GestionnaireJeu();
        jeu.setPartie(new Partie());
        jeu.setUI(new AffichageFenetre(jeu));
        jeu.initialiserPartie();
        jeu.demarrerPartie();
    }
}
