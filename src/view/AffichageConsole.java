package src.view;

import src.model.*;
import src.model.enums.Couleur;
import java.util.Scanner;

public class AffichageConsole implements MastermindObserver {
    private static final Scanner in = new Scanner(System.in);
    private int tailleCombi;
    private int nbTentatives;
    private int TentativeActuelle;
    private ModeJeu modeJeu;
    @Override
    public void miseEnPlacePlateau() {

    }
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

    @Override
    public boolean afficherTentativeComplete() {
        String reponse = "";
        while(!reponse.equals("oui") && !reponse.equals("non")) {
            System.out.println("Votre combinaison est complète. Voulez vous la valider ?\n - oui\n - non");
            reponse = in.nextLine().toLowerCase().strip();

            //Gestion d'une mauvaise réponse
            if(!reponse.equals("oui") && !reponse.equals("non"))
                System.out.println("La valeur entrée est invalide.");
        }
        if(reponse.equals("oui"))
            return true;

        return false;
    }

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

    @Override
    public void afficherIndices(LigneIndice indices) {
        System.out.println("Voici les indices :");
        modeJeu.afficherIndices(indices);
    }

    @Override
    public Joueur creerJoueur() {
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

    @Override
    public int deciderNbManches() {
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

    @Override
    public int deciderTailleCombinaison() {
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

    @Override
    public int deciderNbTentatives() {
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

    @Override
    public ModeJeu deciderModeJeu() {
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
            return new Facile();
        }
        else if(nb == 2) {
            this.modeJeu = new Classique();
            return new Classique();
        }
        else {
            this.modeJeu = new Numerique();
            return new Numerique();
        }
    }
}
