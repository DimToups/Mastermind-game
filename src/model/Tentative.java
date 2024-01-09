package src.model;

import src.controller.GestionnaireJeu;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurTentative;
import src.model.userInterfaces.ObservateurUI;
import src.model.enums.Couleur;
import src.model.enums.Indice;

import java.util.*;

/**
 * Classe modèle représentant une tentative dans un plateau de Mastermind
 */
public class Tentative {
    private Combinaison combinaisonEntree;
    private LigneIndice ligneIndice;
    private ModeJeu modeJeu;
    private ObservateurTentative observateur;
    private GestionnaireJeu jeu;

    /**
     * Créé une Tentative
     *
     * @param jeu Le contrôleur du jeu
     */
    public Tentative(GestionnaireJeu jeu){
        this.jeu = jeu;
        this.combinaisonEntree = new Combinaison();
        this.ligneIndice = new LigneIndice();
    }

    /**
     * Créé une Tentative avec tous les paramètres pour ses attributs
     *
     * @param observateur L'observateur servant d'interface utilisateur
     * @param tailleCombinaison La taille des combinaisons
     * @param modeJeu Le mode de jeu
     * @param jeu Le contrôleur du jeu
     */
    public Tentative(ObservateurUI observateur, int tailleCombinaison, ModeJeu modeJeu, GestionnaireJeu jeu){
        this.observateur = observateur;
        this.combinaisonEntree = new Combinaison(tailleCombinaison);
        this.ligneIndice = new LigneIndice(tailleCombinaison);
        this.modeJeu = modeJeu;
        this.jeu = jeu;
    }

    /**
     * Ajoute une couleur dans la combinaison entrée
     *
     * @param index L'index de la couleur à modifier
     * @param couleur La couleur voulue
     */
    public void ajoutCouleur(int index, Couleur couleur) {
        combinaisonEntree.setCouleur(index,couleur);
    }

    /**
     * Calcule le bonus pour le score
     *
     * @return Le bonus obtenu
     */
    private int calculBonus() {
        if (this.modeJeu == ModeJeu.CLASSIQUE)
            return 4;
        return 0;
    }

    /**
     * Calcule le score de la tentative
     *
     * @return Le score de la tentative
     */
    public int calculScore() {
        return ligneIndice.calculerScore() + calculBonus();
    }

    /**
     * Evalue la tentative selon une combinaison de comparaison
     *
     * @param combinaisonSecrete La combinaison de comparaison
     * @return L'état de la manche suite à l'évaluation (true : combinaison secrète trouvée, false : le cas contraire)
     */
    public boolean evaluerTentative(Combinaison combinaisonSecrete) {
        List<Couleur> resteCombiEntree = new ArrayList<>(combinaisonEntree.getCombinaison());
        List<Couleur> resteCombiSecrete = new ArrayList<>(combinaisonSecrete.getCombinaison());

        //Recherche des pions bien placés
        for(int i = 0; i < combinaisonEntree.getTailleCombinaison(); i++){
            if(combinaisonEntree.getCombinaison().get(i).equals(combinaisonSecrete.getCombinaison().get(i))) {
                this.ligneIndice.getIndices().set(i, Indice.BIEN_PLACE);
                resteCombiEntree.set(i, Couleur.ABSENT);
                resteCombiSecrete.set(i, Couleur.ABSENT);
            }
        }

        //Recherche des pions mal placés
        for(int i = 0; i < combinaisonEntree.getTailleCombinaison(); i++){
            if(!resteCombiEntree.get(i).equals(Couleur.ABSENT)){
                for(int j = 0; j < combinaisonEntree.getTailleCombinaison(); j++){
                    if(!resteCombiSecrete.get(j).equals(Couleur.ABSENT)
                            && resteCombiSecrete.get(j).equals(resteCombiEntree.get(i))){
                        this.ligneIndice.getIndices().set(i, Indice.MAL_PLACE);
                        resteCombiEntree.set(i, Couleur.ABSENT);
                        resteCombiSecrete.set(j, Couleur.ABSENT);
                    }
                }
            }
        }

        for(int i = 0; i < this.ligneIndice.getIndices().size(); i++)
            if(this.ligneIndice.getIndices().get(i) == null)
                this.ligneIndice.getIndices().set(i, Indice.ABSENT);

        //Affichage de la combinaison
        this.observateur.affichageCombinaison(combinaisonEntree);

        //Affichage des indices
        this.observateur.afficherIndices(this.ligneIndice);

        //Vérification des indices
        for(int i = 0; i < this.ligneIndice.getIndices().size(); i++)
            if(!this.ligneIndice.getIndices().get(i).equals(Indice.BIEN_PLACE))
                return false;
        return true;
    }

    /**
     * Donne l'attention à la tentative
     *
     * @param modificationVoulue Un booléen obligeant la modification de la tentative
     */
    public void jouerTentative(boolean modificationVoulue) {
        this.observateur.affichageTentative(this.combinaisonEntree);
        if(!this.combinaisonEntree.estVide())
            if(this.observateur.demanderRemiseAZero())
                this.observateur.affichageTentative(this.combinaisonEntree);
        if(!this.combinaisonEntree.estComplete() ^ modificationVoulue)
            this.observateur.changerCouleur(this.combinaisonEntree);
        else
            this.observateur.demanderFinTentative();
    }

    /**
     * Réinitialise la tentative avec les paramètres actuels
     */
    public void remiseAZero(){
        this.combinaisonEntree = new Combinaison(this.combinaisonEntree.getTailleCombinaison());
    }

    /**
     * Renvoi l'observateur servant d'interface utilisateur
     *
     * @return L'observateur servant d'interface utilisateur
     */
    public ObservateurTentative getObservateurUI() {
        return observateur;
    }

    /**
     * Défini l'observateur servant d'interface utilisateur
     *
     * @param observateur L'interface utilisateur voulue
     */
    public void setObservateurUI(ObservateurTentative observateur) {
        this.observateur = observateur;
    }

    /**
     * Renvoi la combinaison entrée
     *
     * @return La combinaison entrée
     */
    public Combinaison getCombinaisonEntree() {
        return combinaisonEntree;
    }

    /**
     * Renvoi les indices de la tentative
     *
     * @return Les indices de la tentative
     */
    public LigneIndice getLigneIndice() {
        return ligneIndice;
    }

    /**
     * Renvoi le mode de jeu de la partie
     *
     * @return Le mode de jeu de la partie
     */
    public ModeJeu getModeJeu() {
        return modeJeu;
    }

    /**
     * Défini le mode de jeu de la tentative
     *
     * @param modeJeu Le mode de jeu voulu
     */
    public void setModeJeu(ModeJeu modeJeu) {
        this.modeJeu = modeJeu;
    }

    /**
     * Défini la taille des combinaisons
     * Cette méthode réinitialise la tentative. Il est préférable d'utiliser cette méthode avant le début d'une partie pour éviter toute perte de données
     *
     * @param tailleCombinaison La taille de combinaison voulue
     */
    public void setTailleCombinaison(int tailleCombinaison){
        this.combinaisonEntree = new Combinaison(tailleCombinaison);
        this.ligneIndice = new LigneIndice(tailleCombinaison);
    }

    /**
     * Renvoi le contrôleur de la tentative
     * @return Le contrôleur de la tentative
     */
    public GestionnaireJeu getGestionnaireJeu(){
        return this.jeu;
    }

    /**
     * Défini le contrôleur de la tentative
     * @param jeu Le contrôleur voulu
     */
    public void setGestionnaireJeu(GestionnaireJeu jeu){
        this.jeu = jeu;
    }
}
