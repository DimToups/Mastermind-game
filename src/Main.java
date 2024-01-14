package src;

import src.controller.GestionnaireJeu;
import src.model.Partie;
import src.view.AffichageFenetre;

/**
 * Classe servant de point de départ pour le programme
 */
public class Main {
    /**
     * Lance le jeu mastermind
     *
     * @param args Les arguments de lancement du programme
     */
    public static void main(String[] args) {
        GestionnaireJeu jeu = new GestionnaireJeu();
        jeu.setPartie(new Partie(jeu));
        jeu.setUI(new AffichageFenetre(jeu));
    }
}
