package src.model;

import src.model.enums.Couleur;
import src.model.enums.Indice;
import src.view.Classique;

import java.util.*;

public class Tentative {
    private final Combinaison combinaisonEntree;
    private final LigneIndice ligneIndice;
    private final ModeJeu modeJeu;
    private final MastermindObserver observer;
    public Tentative(MastermindObserver observer, int tailleCombi, ModeJeu modeJeu) {
        this.observer = observer;
        this.combinaisonEntree = new Combinaison(tailleCombi);
        this.ligneIndice = new LigneIndice(tailleCombi);
        this.modeJeu = modeJeu;
    }
    public void lancerTentative() {
        boolean fini = false;

        while (!fini) {
            //Affichage de la combinaison entrée de la tentative
            observer.affichageTentative(combinaisonEntree);

            //Gestion du cas où la combinaison est complète
            if(this.combinaisonEntree.estComplet()){
                fini = this.observer.afficherTentativeComplete();
            }

            //Gestion du cas où le joueur veut modifier sa combinaison
            if(!fini){
                this.observer.changerCouleur(this.combinaisonEntree);
            }
        }
    }
    public void ajoutCouleur(int index, Couleur couleur) {
        combinaisonEntree.setCouleur(index,couleur);
    }
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
        this.observer.affichageTentative(combinaisonEntree);

        //Affichage des indices
        this.observer.afficherIndices(this.ligneIndice);

        //Vérification des indices
        for(int i = 0; i < this.ligneIndice.getIndices().size(); i++){
            if(!this.ligneIndice.getIndices().get(i).equals(Indice.BIEN_PLACE))
                return false;
        }

        return true;
    }
    public Combinaison getCombinaisonEntree() {
        return combinaisonEntree;
    }
    public LigneIndice getLigneIndice() {
        return ligneIndice;
    }
    private int calculBonus() {
        if (modeJeu instanceof Classique)
            return 4;
        return 0;
    }
    public int calculerScore() {
        return ligneIndice.calculerScore() + calculBonus();
    }
}
