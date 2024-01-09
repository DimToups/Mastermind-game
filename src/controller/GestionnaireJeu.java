package src.controller;

import src.model.*;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurUI;

/**
 * Classe contrôleuse manipulant le déroulement d'une partie
 */
public class GestionnaireJeu {
    /**
     * Créée une instance de GestionnaireJeu pour gérer une partie
     */
    public GestionnaireJeu(){

    }
    private Partie partie;

    /**
     * Défini la partie avec laquelle l'instance va jouer
     *
     * @param partie La partie a manipuler
     */
    public void setPartie(Partie partie){
        this.partie = partie;
    }

    /**
     * Défini l'interface utilisateur utilisée par la partie
     *
     * @param observateur L'interface utilisateur voulue
     */
    public void setUI(ObservateurUI observateur){
        this.partie.setObservateurUI(observateur);
    }

    /**
     * Initialise la partie en utilisant l'interface utilisateur
     */
    public void initialiserPartie(){
        this.partie.initialiser();
    }

    /**
     * Lance la prochaine manche de la partie
     */
    public void demarrerProchaineManche() {
        this.partie.lancerProchaineManche();
    }

    /**
     * Met à jour la partie pour qu'il y ait le bon nombre de manches
     * Cette méthode réinitialise toutes les manches. Il est préférable d'utiliser cette méthode avant le début d'une partie pour éviter toute perte de données
     *
     * @param n Le nombre de manches voulu
     */
    public void miseAJourNbManches(int n){
        this.partie.setNbManches(n);
    }

    /**
     * Met à jour toutes les combinaisons pour qu'elles aient la taille voulue
     * Cette méthode réinitialise toutes les manches. Il est préférable d'utiliser cette méthode avant le début d'une partie pour éviter toute perte de données
     *
     * @param n La taille de combinaison voulue
     */
    public void miseAJourTailleCombinaison(int n){
        for(Manche manche : this.partie.getManches())
            manche.getTentatives().forEach(tentative -> tentative.setTailleCombinaison(n));
    }

    /**
     * Met à jour toutes les manches pour qu'elles aient le nombre de tentatives voulu
     * Cette méthode réinitialise toutes les manches. Il est préférable d'utiliser cette méthode avant le début d'une partie pour éviter toute perte de données
     *
     * @param n Le nombre de tentatives voulu
     */
    public void miseAJourNbTentatives(int n){
        for (Manche manche : this.partie.getManches())
            manche.setNbTentatives(n);
    }

    /**
     * Met à jour toutes les tentatives pour qu'elles aient le mode de jeu voulu
     *
     * @param mode Le mode de jeu voulu
     */
    public void miseAJourModeJeu(ModeJeu mode){
        for(Manche manche : this.partie.getManches())
            for(Tentative tentative : manche.getTentatives())
                tentative.setModeJeu(mode);
    }

    /**
     * Défini le joueur de la partie
     *
     * @param joueur Le joueur
     */
    public void miseAJourJoueur(Joueur joueur){
        this.partie.setJoueur(joueur);
    }

    /**
     * Appelle la méthode jouerTentative() de la tentative actuelle
     *
     * @param modificationVoulue Un booléen obligeant la modification de la tentative actuelle
     */
    public void modifierTentativeActuelle(boolean modificationVoulue){
        Manche mancheActuelle = this.partie.getManches().get(partie.getMancheActuelle());
        mancheActuelle.getTentatives().get(mancheActuelle.getTentativeActuelle()).jouerTentative(modificationVoulue);
    }

    /**
     * Joue la prochaine tentative s'il en reste
     */
    public void passerProchaineTentative(){
        Manche mancheActuelle = this.partie.getManches().get(partie.getMancheActuelle());

        // Evaluation de la tentative
        boolean mancheFinie = mancheActuelle.getTentatives().get(mancheActuelle.getTentativeActuelle()).evaluerTentative(mancheActuelle.getCombinaisonSecrete());

        // Passage à la prochaine tentative
        if(mancheActuelle.getTentativeActuelle() < mancheActuelle.getTentatives().size() && !mancheFinie)
            mancheActuelle.jouerManche();
        else
            this.demarrerProchaineManche();
    }

    /**
     * Renvoi la combinaison secrète de la manche actuelle
     *
     * @return La combinaison secrète de la manche actuelle
     */
    public Combinaison getCombinaisonSecreteActuelle(){
        return this.partie.getManches().get(this.partie.getMancheActuelle()).getCombinaisonSecrete();
    }
}
