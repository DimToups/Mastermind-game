package src.controller;

import src.model.*;
import src.model.observers.ObservateurUI;

public class GestionnaireJeu {
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
     * Lance la partie
     */
    public void demarrerPartie() {
        this.partie.lancerPartie();
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
}
