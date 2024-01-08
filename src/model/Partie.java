package src.model;

import src.controller.GestionnaireJeu;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurPartie;
import src.model.userInterfaces.ObservateurUI;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe modèle représentant une partie de Mastermind
 */
public class Partie {
    private int score = 0 ;
    private int mancheActuelle = -1;
    private Joueur joueur;
    private List<Manche> manches = new ArrayList<>();
    private ObservateurPartie observateur;
    private GestionnaireJeu jeu;

    /**
     * Créé une instance de Partie
     *
     * @param jeu Le contrôleur du jeu
     */
    public Partie(GestionnaireJeu jeu) {
        this.jeu = jeu;
        // Initialisation des manches
        for(int i = 0; i < 3; i++)
            manches.add(new Manche(this.jeu));
    }

    /**
     * Initialise la partie à l'aide de l'interface utilisateur
     */
    public void initialiser(){
        this.observateur.entrerModeInitialisation();
        this.observateur.creerJoueur();
        this.observateur.deciderTailleCombinaison();
        this.observateur.deciderNbTentatives();
        this.observateur.deciderNbManches();
        this.observateur.deciderModeJeu();
        this.observateur.demanderFinInitialisation();
    }

    /**
     * Lance la manche suivante. Si aucune manche n'a été lancé, la partie lancera automatiquement la première manche
     */
    public void lancerProchaineManche() {
        mancheActuelle++;
        if(mancheActuelle >= manches.size())
            finirPartie();
        else {
            manches.get(mancheActuelle).setCombinaisonSecrete(Combinaison.genererCombinaison(this.manches.getFirst().getTentatives().getFirst().getCombinaisonEntree().getTailleCombinaison()));
            manches.get(mancheActuelle).jouerManche();
        }
    }

    /**
     * Met fin à la partie
     */
    private void finirPartie() {
        this.observateur.finirPartie(score);
        this.joueur.ajouterScorePartie(score);
    }

    /**
     * Renvoi les manches de la partie
     *
     * @return Les manches de la partie
     */
    public List<Manche> getManches() {
        return this.manches;
    }

    /**
     * Défini le nombre de manches de la partie
     * Cette méthode réinitialise toutes les manches. Il est préférable d'utiliser cette méthode avant le début d'une partie pour éviter toute perte de données
     *
     * @param n Le nombre de manches voulu
     */
    public void setNbManches(int n){
        // Sauvegarde des informations utiles
        int nbTentatives = this.manches.getFirst().getNbTentatives();
        int tailleCombinaison = this.manches.getFirst().getTentatives().getFirst().getCombinaisonEntree().getTailleCombinaison();
        ModeJeu modeJeu = this.manches.getFirst().getTentatives().getFirst().getModeJeu();

        // Réinitialisation des manches
        this.manches = new ArrayList<>();
        for(int i = 0; i < n; i++)
            this.manches.add(new Manche((ObservateurUI) this.observateur, nbTentatives, tailleCombinaison, modeJeu, this.jeu));
    }

    /**
     * Renvoi le score de la partie
     *
     * @return Le score de la partie
     */
    public int getScore(){
        return this.score;
    }

    /**
     * Défini le score de la partie
     *
     * @param score Le score voulu
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Renvoi le joueur de la partie
     *
     * @return Le joueur de la partie
     */
    public Joueur getJoueur(){
        return this.joueur;
    }

    /**
     * Défini le joueur de la partie
     *
     * @param joueur Le joueur
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    /**
     * Renvoi l'observateur servant d'interface utilisateur
     *
     * @return L'interface utilisateur
     */
    public ObservateurPartie getUI(){
        return this.observateur;
    }

    /**
     * Défini l'observateur servant d'interface utilisateur
     *
     * @param observer L'interface utilisateur voulue
     */
    public void setObservateurUI(ObservateurUI observer) {
        this.observateur = observer;
        for (Manche manche : this.manches)
            manche.setObservateurUI(observer);
    }

    /**
     * Renvoi l'index de la manche actuelle
     * @return L'index de la manche actuelle
     */
    public int getMancheActuelle() {
        return mancheActuelle;
    }

    /**
     * Renvoi le contrôleur de la partie
     * @return Le contrôleur de la partie
     */
    public GestionnaireJeu getGestionnaireJeu(){
        return this.jeu;
    }

    /**
     * Défini le contrôleur de la partie
     * @param jeu Le contrôleur voulu
     */
    public void setGestionnaireJeu(GestionnaireJeu jeu){
        this.jeu = jeu;
    }
}
