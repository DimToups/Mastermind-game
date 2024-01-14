package src.controller;

import src.model.*;
import src.model.enums.Couleur;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurUI;

/**
 * Classe contrôleuse manipulant le déroulement d'une partie
 */
public class GestionnaireJeu {
    private Partie partie;
    /**
     * Créée une instance de GestionnaireJeu pour gérer une partie
     */
    public GestionnaireJeu(){

    }

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
     * Lance la prochaine manche de la partie
     */
    public void demarrerProchaineManche() {
        this.partie.lancerProchaineManche();
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
     * Joue la prochaine tentative s'il en reste
     */
    public void passerProchaineTentative(){
        Manche mancheActuelle = this.partie.getManches().get(partie.getMancheActuelle());

        // Evaluation de la tentative
        boolean mancheFinie = mancheActuelle.getTentatives().get(mancheActuelle.getTentativeActuelle()).evaluerTentative(mancheActuelle.getCombinaisonSecrete());

        // Passage à la prochaine tentative
        if(mancheActuelle.getTentativeActuelle() < mancheActuelle.getTentatives().size() && !mancheFinie)
            mancheActuelle.TentativeSuivante();
        else
            this.demarrerProchaineManche();
    }

    /**
     * Réinitialise la tentative actuelle
     */
    public void remiseAZeroTentativeActuelle(){
        Manche mancheActuelle = this.partie.getManches().get(partie.getMancheActuelle());
        mancheActuelle.getTentatives().get(mancheActuelle.getTentativeActuelle()).remiseAZero();
    }

    /**
     * Renvoi la combinaison secrète de la manche actuelle
     *
     * @return La combinaison secrète de la manche actuelle
     */
    public Combinaison getCombinaisonSecreteActuelle(){
        return this.partie.getManches().get(this.partie.getMancheActuelle()).getCombinaisonSecrete();
    }

    public void setParametres(String nomJoueur, String nbManches, String nbTentatives, String tailleCombinaison, String modeJeu) {
        this.miseAJourJoueur(new Joueur(nomJoueur));
        int intNbManches = 3;
        if(!nbManches.isBlank())
            intNbManches = Integer.parseInt(nbManches);
        int intNbTentatives = 10;
        if(!nbTentatives.isBlank())
            intNbTentatives = Integer.parseInt(nbTentatives);
        int intTailleCombinaison = 4;
        if(!tailleCombinaison.isBlank())
            intTailleCombinaison = Integer.parseInt(tailleCombinaison);
        this.partie.setParametres(intNbManches, intNbTentatives, intTailleCombinaison, ModeJeu.StringToMJ(modeJeu));
        this.partie.lancerProchaineManche();

    }

    public void mancheSuivante() {
        partie.lancerProchaineManche();
    }

    public void resumerPartie() {
        partie.resumerManche();
    }

    public void changerCouleur(Couleur couleur, int index) {
        this.partie.getManches().get(partie.getMancheActuelle()).getTentatives().get(this.partie.getManches().get(partie.getMancheActuelle()).getTentativeActuelle()).ajoutCouleur(index,couleur);
    }
    public int getTentativeActuelle(){
        return this.partie.getManches().get(this.partie.getMancheActuelle()).getTentativeActuelle();
    }
}
