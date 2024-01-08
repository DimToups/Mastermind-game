package src.model;

import src.controller.GestionnaireJeu;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurManche;
import src.model.userInterfaces.ObservateurUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modèle représentant une manche d'une partie.
 */
public class Manche {
    private int tentativeActuelle = -1;
    private int nbTentatives = 10;
    private List<Tentative> tentatives = new ArrayList<>();
    private Combinaison combinaisonSecrete;
    private ObservateurManche observateur;
    private GestionnaireJeu jeu;

    /**
     * Créé une instance de Manche
     *
     * @param jeu Le contrôleur du jeu
     */
    public Manche(GestionnaireJeu jeu) {
        this.jeu = jeu;
        for (int i = 0; i < 10; i++)
            this.tentatives.add(new Tentative(this.jeu));
    }

    /**
     * Créé une instance de Manche avec tous les paramètres dont ses attributs ont besoin
     *
     * @param observateur L'observateur servant d'interface utilisateur
     * @param nbTentatives Le nombre de tentatives de la manche
     * @param tailleCombinaison La taille des combinaisons
     * @param modeJeu Le mode de jeu de la partie
     * @param jeu Le contrôleur du jeu
     */
    public Manche(ObservateurUI observateur, int nbTentatives, int tailleCombinaison, ModeJeu modeJeu, GestionnaireJeu jeu){
        this.observateur = observateur;
        this.nbTentatives = nbTentatives;
        this.jeu = jeu;
        for(int i = 0; i < nbTentatives; i++)
            this.tentatives.add(new Tentative(observateur, tailleCombinaison, modeJeu, jeu));
    }

    /**
     * Joue la manche avec les paramètres choisis
     */
    public void jouerManche() {
        tentativeActuelle++;
        if(tentativeActuelle == 0)
            this.observateur.miseEnPlacePlateau();
        if(tentativeActuelle < tentatives.size())
            tentatives.get(tentativeActuelle).jouerTentative(false);
        else{
            this.observateur.miseEnPlacePlateau();
            this.observateur.prochaineManche(this.combinaisonSecrete);
        }
    }

    /**
     * Calcule le score de la manche
     *
     * @return Le score de la manche
     */
    public int calculerScore() {
        return tentatives.get(tentativeActuelle).calculScore();
    }

    /**
     * Renvoi l'observateur servant d'interface utilisateur
     *
     * @return L'interface utilisateur
     */
    public ObservateurManche getObservateurUI(){
        return this.observateur;
    }

    /**
     * Défini l'observateur servant d'interface utilisateur
     *
     * @param observateur L'interface utilisateur voulue
     */
    public void setObservateurUI(ObservateurUI observateur){
        this.observateur = observateur;
        for (Tentative tentative : this.tentatives)
            tentative.setObservateurUI(observateur);
    }

    /**
     * Renvoi la combinaison secrète de la manche
     *
     * @return La combinaison secrète
     */
    public Combinaison getCombinaisonSecrete() {
        return combinaisonSecrete;
    }

    /**
     * Défini la combinaison secrète de la manche
     *
     * @param combinaisonSecrete La combinaison voulue
     */
    public void setCombinaisonSecrete(Combinaison combinaisonSecrete) {
        this.combinaisonSecrete = combinaisonSecrete;
    }

    /**
     * Renvoi le nombre de tentatives de la manche
     *
     * @return Le nombre de tentatives de la manche
     */
    public int getNbTentatives() {
        return nbTentatives;
    }

    /**
     * Défini le nombre de tentatives de la manche
     *
     * @param nbTentatives Le nombre de tentatives voulu
     */
    public void setNbTentatives(int nbTentatives) {
        this.nbTentatives = nbTentatives;

        // Sauvegarde des informations utiles
        int tailleCombinaison = this.tentatives.getFirst().getCombinaisonEntree().getTailleCombinaison();
        ModeJeu modeJeu = this.tentatives.getFirst().getModeJeu();

        // Mise à jour des tentatives
        this.tentatives = new ArrayList<>();
        for(int i = 0; i < nbTentatives; i++)
            this.tentatives.add(new Tentative((ObservateurUI) this.observateur, tailleCombinaison, modeJeu, jeu));
    }

    /**
     * Renvoi la tentative actuelle
     *
     * @return La tentative actuelle
     */
    public int getTentativeActuelle() {
        return tentativeActuelle;
    }

    /**
     * Renvoi les tentatives de la manche
     *
     * @return Les tentatives de la manche
     */
    public List<Tentative> getTentatives() {
        return tentatives;
    }

    /**
     * Renvoi le contrôleur de la manche
     * @return Le contrôleur de la manche
     */
    public GestionnaireJeu getGestionnaireJeu(){
        return this.jeu;
    }

    /**
     * Défini le contrôleur de la manche
     * @param jeu Le contrôleur voulu
     */
    public void setGestionnaireJeu(GestionnaireJeu jeu){
        this.jeu = jeu;
    }
}
