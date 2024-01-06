package src.model.observers;

import src.model.Combinaison;

public interface ObservateurUI extends ObservateurPartie, ObservateurManche, ObservateurTentative{

    void affichageCombinaison(Combinaison combinaison);
}
