package src.model;

import java.util.ArrayList;
import java.util.List;

public class Manche {
    private int tentativeActuelle = 0;
    private int nbTentatives = 10;
    private List<Tentative> tentatives = new ArrayList<>();
    private Combinaison combinaisonSecrete;
    private ObservateurManche observateur;

    /**
     * Créé une instance de Manche
     */
    public Manche() {
        for (int i = 0; i < 10; i++)
            this.tentatives.add(new Tentative());
    }

    /**
     * Créé une instance de Manche avec tous les paramètres dont ses attributs ont besoin
     *
     * @param observateur L'observateur servant d'interface utilisateur
     * @param nbTentatives Le nombre de tentatives de la manche
     * @param tailleCombinaison La taille des combinaisons
     * @param modeJeu Le mode de jeu de la partie
     */
    public Manche(ObservateurUI observateur, int nbTentatives, int tailleCombinaison, ModeJeu modeJeu){
        this.observateur = observateur;
        for(int i = 0; i < nbTentatives; i++)
            this.tentatives.add(new Tentative(observateur, tailleCombinaison, modeJeu));
    }

    /**
     * Joue la manche avec les paramètres choisis
     */
    public void jouerManche() {
        boolean fini = false;
        while (!fini){
            Tentative tentative = tentatives.get(tentativeActuelle);
            tentative.lancerTentative();

            // Evaluation de la tentative
            if (tentative.evaluerTentative(combinaisonSecrete)) {
                fini = true;
                System.out.println("Vous avez trouvé la combinaison, bravo !");
            }
            else if (tentativeActuelle > nbTentatives) {
                fini = true;

                System.out.println("Vous n'avez pas trouvé la combinaison secrète. La voici :");
                for(int i = 0; i < combinaisonSecrete.getCombinaison().size(); i++)
                    System.out.print(this.combinaisonSecrete.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                            + this.combinaisonSecrete.getCombinaison().get(i).name().substring(1).toLowerCase()
                            + "  | ");
            }
            else
                tentativeActuelle++;
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
            this.tentatives.add(new Tentative((ObservateurUI) this.observateur, tailleCombinaison, modeJeu));
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
}
