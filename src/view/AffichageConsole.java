package src.view;

import src.controller.GestionnaireJeu;
import src.model.*;
import src.model.enums.Couleur;
import java.util.Scanner;

public class AffichageConsole implements ObservateurUI {
    private static final Scanner in = new Scanner(System.in);
    private ModeJeu modeJeu;
    private GestionnaireJeu jeu;

    /**
     * Construit et initialise l'ObservateurUI
     *
     * @param jeu L'instance contrôleuse du jeu
     */
    public AffichageConsole(GestionnaireJeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void miseEnPlacePlateau() {

    }

    /**
     * Demande à l'utilisateur s'il veut mettre fin à la tentative actuelle
     *
     * @return La réponse de l'utilisateur
     */
    @Override
    public boolean demanderValidationTentative() {
        return false;
    }

    /**
     * Affiche le résumé d'une tentative
     *
     * @param combinaison La combinaison à afficher
     */
    @Override
    public void affichageTentative(Combinaison combinaison) {
        System.out.println("\nVoici votre combinaison :");
        for(int i = 0; i < combinaison.getCombinaison().size(); i++)
            System.out.print(i + " : "
                    + combinaison.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                    + combinaison.getCombinaison().get(i).name().substring(1).toLowerCase()
                    + "  | ");
        System.out.println();
    }

    /**
     * Demande à l'utilisateur s'il veut mettre fin à la tentative
     *
     * @return La réponse de l'utilisateur
     */
    @Override
    public boolean demanderFinTentative() {
        String reponse = "";
        while(!reponse.equals("oui") && !reponse.equals("non")) {
            System.out.println("Votre combinaison est complète. Voulez vous la valider ?\n - oui\n - non");
            reponse = in.nextLine().toLowerCase().strip();

            //Gestion d'une mauvaise réponse
            if(!reponse.equals("oui") && !reponse.equals("non"))
                System.out.println("La valeur entrée est invalide.");
        }
        return reponse.equals("oui");
    }

    /**
     * Permet de changer la couleur dans une combinaison
     *
     * @param combinaison La combinaison à modifier
     */
    @Override
    public void changerCouleur(Combinaison combinaison) {
        int reponseInt = -1;
        //Obtention de la réponse de l'utilisateur
        while(reponseInt < 0 || reponseInt >= combinaison.getTailleCombinaison()) {
            try{
                System.out.println("Veuillez entrer l'index de la combinaison à modifier.");
                reponseInt = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e){
                System.out.println("La valeur entrée est invalide.");
            }
        }

        // Obtention de la couleur à placer et placement de cette couleur
        boolean estPlace = false;
        while (!estPlace) {
            System.out.print("Quelle couleur voulez-vous placer ?\nCouleurs disponibles : \n");
            for (Couleur couleurValides : Couleur.getVraiesCouleurs())
                System.out.print(couleurValides.name().substring(0,1).toUpperCase()
                        + couleurValides.name().substring(1).toLowerCase()
                        + " | ");
            System.out.println();

            try {
                Couleur couleurChoisie = Couleur.valueOf(in.nextLine().strip().toUpperCase());
                combinaison.setCouleur(reponseInt, couleurChoisie);
                estPlace = true;
            }
            catch (Exception e){
                System.out.println("La valeur entrée est invalide.");
            }
        }
    }

    /**
     * Affiche les indices passés en paramètres
     *
     * @param indices Les indices à afficher
     */
    @Override
    public void afficherIndices(LigneIndice indices) {
        System.out.println("Voici les indices :");
        modeJeu.afficherIndices(indices);
    }

    /**
     * Demande à l'utilisateur toutes les questions pour créer une instance de Joueur
     */
    @Override
    public void creerJoueur() {
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
        this.jeu.miseAJourJoueur(new Joueur(nom));
    }

    /**
     * Demande à l'utilisateur le nombre de manches qu'il veut faire
     */
    @Override
    public void deciderNbManches() {
        int n = 0;
        while (n < 1 || n > 5){
            try {
                System.out.println("Veuillez entrer un nombre entre 1 et 5 de manches que vous voulez jouer.");
                Scanner in = new Scanner(System.in);
                n = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        this.jeu.miseAJourNbManches(n);
    }

    /**
     * Demande à l'utilisateur la taille des combinaisons à composer
     */
    @Override
    public void deciderTailleCombinaison() {
        int n = 0;
        while (n < 2 || n > 6){
            try {
                System.out.println("Veuillez entrer un nombre entre 2 et 6 de pions à placer par combinaison.");
                Scanner in = new Scanner(System.in);
                n = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        this.jeu.miseAJourTailleCombinaison(n);
    }

    /**
     * Demande à l'utilisateur le nombre de tentatives qu'il veut pour ses manches
     */
    @Override
    public void deciderNbTentatives() {
        int n = 0;
        while (n < 2 || n > 12){
            try {
                System.out.println("Veuillez entrer un nombre entre 2 et 12 de tentatives pour trouver la combinaison secrète.");
                Scanner in = new Scanner(System.in);
                n = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        this.jeu.miseAJourNbTentatives(n);
    }

    /**
     * Demande à l'utilisateur le mode de jeu auquel il veut jouer
     */
    @Override
    public void deciderModeJeu() {
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
        if(nb == 1) {
            this.modeJeu = new Facile();
            this.jeu.miseAJourModeJeu(new Facile());
        }
        else if(nb == 2) {
            this.modeJeu = new Classique();
            this.jeu.miseAJourModeJeu(new Classique());
        }
        else {
            this.modeJeu = new Numerique();
            this.jeu.miseAJourModeJeu(new Numerique());
        }
    }

    /**
     * Met fin à la partie
     */
    @Override
    public void finirPartie() {

    }
}
