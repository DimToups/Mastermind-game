package src.model.userInterfaces;

import src.model.LigneIndice;
import src.model.enums.ModeJeu;

public interface ObservateurUI extends ObservateurPartie, ObservateurManche, ObservateurTentative, AfficheurIndices {
    static void deciderMethodeAffichageIndices(ObservateurUI ui, ModeJeu modeJeu, LigneIndice indices){
        switch(modeJeu){
            case FACILE -> ui.afficherIndicesFacile(indices);
            case CLASSIQUE -> ui.afficherIndicesClassique(indices);
            case NUMERIQUE -> ui.afficherIndicesNumerique(indices);
        }
    }
}
