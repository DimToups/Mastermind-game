package src.controller;

import src.model.*;

import java.util.Scanner;

public class GestionnaireJeu {
    public void demarrerPartie() {
        Partie partie = new Partie(creerJoueur(), getNbManche(), getTailleCombi(), getNbTentatives(), getModeJeu());
        partie.lancerPartie();
    }
    private Joueur creerJoueur() {
        //Obtention du pseudo du joueur
        String nom = "";
        while(nom.isEmpty()) {
            try {
                System.out.println("Veuillez entrer votre pseudo.");
                Scanner in = new Scanner(System.in);
                if((nom = in.nextLine()).isEmpty())
                    System.out.println("Votre pseudo ne peux pas être vide.");
            }
            catch (Exception e) {
                System.out.println("Une erreur est survenue.");
            }
        }

        // Retour du nom du joueur
        return new Joueur(nom);
    }
    private Integer getNbManche() {
        int nb = 0;
        while (nb < 3 || nb > 5){
            try {
                System.out.println("Veuillez entrer un nombre entre 3 et 5 de manches que vous voulez jouer.");
                Scanner in = new Scanner(System.in);
                nb = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        return nb;
    }
    private Integer getTailleCombi() {
        int nb = 0;
        while (nb < 4 || nb > 6){
            try {
                System.out.println("Veuillez entrer un nombre entre 4 et 6 de pions à placer par combinaison.");
                Scanner in = new Scanner(System.in);
                nb = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        return nb;
    }
    private Integer getNbTentatives() {
        int nb = 0;
        while (nb < 10 || nb > 12){
            try {
                System.out.println("Veuillez entrer un nombre entre 10 et 12 de tentatives pour trouver la combinaison secrète.");
                Scanner in = new Scanner(System.in);
                nb = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        return nb;
    }
    private ModeJeu getModeJeu() {
        int nb = 0;
        while (nb < 1 || nb > 3){
            try {
                System.out.println("Veuillez choisir votre mode de jeu :\n - 1 : Facile\n - 2 : Classique\n - 3 : Numérique");
                Scanner in = new Scanner(System.in);
                nb = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        //Retour du mode de jeu choisi
        if(nb == 1)
            return new Facile();
        else if(nb == 2)
            return new Classique();
        else
            return new Numerique();
    }
}
