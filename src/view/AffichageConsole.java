package src.view;

import src.controller.GestionnaireJeu;
import src.model.*;
import src.model.enums.Indice;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurTentative;
import src.model.userInterfaces.ObservateurUI;
import src.model.enums.Couleur;
import java.util.Scanner;

/**
 * Classe vue servant d'interface utilisateur utilisant uniquement la console pour communiquer avec l'utilisateur
 */
public class AffichageConsole implements ObservateurUI {
    private static final Scanner in = new Scanner(System.in);
    private ModeJeu modeJeu;
    private final GestionnaireJeu jeu;

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
     * Affiche les informations de la manche avant de passer à la prochaine
     */
    @Override
    public void prochaineManche(Combinaison combinaisonSecrete) {
        System.out.println("La manche est terminée ! Voici la combinaison secrète :");
        this.affichageCombinaison(combinaisonSecrete);
        System.out.println("Appuyez sur entrer pour passer à la prochaine manche.");
        in.nextLine();
        jeu.demarrerProchaineManche();
    }

    /**
     * Affiche le résumé d'une tentative
     *
     * @param combinaison La combinaison à afficher
     */
    @Override
    public void affichageTentative(Combinaison combinaison) {
        System.out.print("Voici votre combinaison :\n| ");
        for(int i = 0; i < combinaison.getCombinaison().size(); i++)
            System.out.print(i + " : "
                    + combinaison.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                    + combinaison.getCombinaison().get(i).name().substring(1).toLowerCase()
                    + "  | ");
        System.out.println();
    }

    /**
     * Affiche une combinaison
     *
     * @param combinaison La combinaison à afficher
     */
    @Override
    public void affichageCombinaison(Combinaison combinaison) {
        System.out.print("| ");
        for(int i = 0; i < combinaison.getCombinaison().size(); i++)
            System.out.print(combinaison.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                    + combinaison.getCombinaison().get(i).name().substring(1).toLowerCase()
                    + "  | ");
        System.out.println();
    }

    /**
     * Remet à zéro la combinaison actuelle
     *
     * @return Indique si la combinaison a été réinitialisée ou non à l'aide d'un booléen
     */
    @Override
    public boolean demanderRemiseAZero() {
        String reponse = "";
        while(!reponse.equals("oui") && !reponse.equals("non")){
            System.out.println("Voulez recommencer la tentative ?\n - Oui\n - Non");
            reponse = in.nextLine().strip().toLowerCase();

            if(!reponse.equals("oui") && !reponse.equals("non"))
                System.out.println("La valeur entrée n'est pas valide");
        }
        if(reponse.equals("oui")) {
            System.out.println();
            this.jeu.remiseAZeroTentativeActuelle();
            return true;
        }
        return false;
    }

    /**
     * Demande à l'utilisateur s'il veut mettre fin à la manche
     *
     * @return La réponse de l'utilisateur (true : fin de la manche, false sinon)
     */
    @Override
    public boolean demanderFinManche() {
        String reponse = "";
        while(!reponse.equals("oui") && !reponse.equals("non")){
            System.out.println("Voulez-vous abandonner cette manche?\n - Oui\n - Non");
            reponse = in.nextLine().strip().toLowerCase();

            if(!reponse.equals("oui") && !reponse.equals("non"))
                System.out.println("La valeur entrée n'est pas valide");
        }

        if(reponse.equals("oui")) {
            jeu.demarrerProchaineManche();
            return true;
        }
        return false;
    }

    /**
     * Demande à l'utilisateur s'il veut mettre fin à la tentative
     */
    @Override
    public void demanderFinTentative() {
        String reponse = "";
        while(!reponse.equals("oui") && !reponse.equals("non")) {
            System.out.println("Votre combinaison est complète. Voulez vous la valider ?\n - oui\n - non");
            reponse = in.nextLine().toLowerCase().strip();

            //Gestion d'une mauvaise réponse
            if(!reponse.equals("oui") && !reponse.equals("non"))
                System.out.println("La valeur entrée est invalide.");
        }
        if(reponse.equals("oui"))
            jeu.passerProchaineTentative();
        else
            jeu.modifierTentativeActuelle(true);
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
            for (Couleur couleurValide : Couleur.getVraiesCouleurs())
                System.out.print(Couleur.nomValide(couleurValide) + " | ");
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
        this.jeu.modifierTentativeActuelle(false);
    }

    /**
     * Affiche les indices passés en paramètres
     *
     * @param indices Les indices à afficher
     */
    @Override
    public void afficherIndices(LigneIndice indices) {
        System.out.println("Voici les indices :");
        ObservateurTentative.deciderMethodeAffichageIndices(this, modeJeu, indices);
    }

    /**
     * Fini la manche suite à la réussite du joueur
     */
    @Override
    public void finirManche(){
        System.out.println("Vous avez trouvé la combinaison secrète !\n\nAppuyez sur entrer pour passer à la prochaine manche.");
        in.nextLine();
        jeu.demarrerProchaineManche();
    }

    /**
     * Fait entrer l'observateur en mode initialisation de partie
     */
    @Override
    public void entrerModeInitialisation() {

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
                System.out.println("Veuillez choisir votre mode de jeu :\n - 1 : Facile\n - 2 : Classique\n - 3 : Numerique");
                nb = Integer.parseInt(in.nextLine().strip());
            }
            catch (Exception e) {
                System.out.println("La valeur entrée n'est pas valide.");
            }
        }

        //Retour du mode de jeu choisi
        if(nb == 1) {
            this.modeJeu = ModeJeu.FACILE;
            this.jeu.miseAJourModeJeu(ModeJeu.FACILE);
        }
        else if(nb == 2) {
            this.modeJeu = ModeJeu.CLASSIQUE;
            this.jeu.miseAJourModeJeu(ModeJeu.CLASSIQUE);
        }
        else {
            this.modeJeu = ModeJeu.NUMERIQUE;
            this.jeu.miseAJourModeJeu(ModeJeu.NUMERIQUE);
        }
    }

    /**
     * Demande à l'utilisateur s'il veut valider les paramètres de jeu
     */
    @Override
    public void demanderFinInitialisation() {
        System.out.println("Valider les paramètres et commencer la partie ?\n - Oui\n - Non");
        String reponse = "";
        while(!reponse.equals("oui") && !reponse.equals("non")) {
            reponse = in.nextLine().toLowerCase().strip();
            if (reponse.equals("oui"))
                jeu.demarrerProchaineManche();
            else if (reponse.equals("non"))
                jeu.initialiserPartie();
        }
    }

    /**
     * Met fin à la partie
     */
    @Override
    public void finirPartie(int score) {
        System.out.println("La partie est terminée.\nScore final : " + score);
    }

    /**
     *
     */
    @Override
    public void afficherIndicesFacile(LigneIndice indices) {
        if(!indices.getIndices().contains(Indice.BIEN_PLACE) && !indices.getIndices().contains(Indice.MAL_PLACE))
            System.out.println("/");
        for (int i = 0; i < indices.getTailleCombinaison(); i++)
            System.out.print(Indice.nomValide(indices.getIndices().get(i)) + " | ");
        System.out.println();
    }

    /**
     *
     */
    @Override
    public void afficherIndicesClassique(LigneIndice indices) {
        if(!indices.getIndices().contains(Indice.BIEN_PLACE) && !indices.getIndices().contains(Indice.MAL_PLACE))
            System.out.println("/");
        for (int i = 0; i < indices.getIntIndices()[0]; i++)
            System.out.print(Indice.nomValide(Indice.BIEN_PLACE) + " | ");
        for (int i = 0; i < indices.getIntIndices()[1]; i++)
            System.out.print(Indice.nomValide(Indice.MAL_PLACE) + " | ");
        System.out.println();
    }

    /**
     *
     */
    @Override
    public void afficherIndicesNumerique(LigneIndice indices) {
        System.out.println("Vous avez "
                + indices.getIntIndices()[0]
                + " pions bien placé(s) et "
                + indices.getIntIndices()[1]
                + " pions mal placé(s).");
    }
}
