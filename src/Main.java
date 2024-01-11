package src;

import src.controller.GestionnaireJeu;
import src.model.Partie;
import src.view.AffichageConsole;
import src.view.AffichageFenetre;

/**
 * Classe servant de point de départ pour le programme
 */
public class Main {
    /**
     * Lance le jeu mastermind
     *
     * @param args Les arguments de lancement du programme
     *             -f ou --fenetre : affichage avec une fenêtre
     *             -c ou --console : affichage avec la console
     */
    public static void main(String[] args) {
        GestionnaireJeu jeu = new GestionnaireJeu();
        jeu.setPartie(new Partie(jeu));

        // Définition de l'interface utilisateur à utiliser
        if(args.length == 0)
            jeu.setUI(new AffichageFenetre(jeu));
        else if(args[0].equals("-c") || args[0].equals("--console"))
            jeu.setUI(new AffichageConsole(jeu));
        else if(args[0].equals("-f") || args[0].equals("--fenetre"))
            jeu.setUI(new AffichageFenetre(jeu));
        jeu.initialiserPartie();
    }
}
