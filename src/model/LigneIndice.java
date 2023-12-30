package src.model;

import src.model.enums.Indice;

import java.util.ArrayList;
import java.util.List;

public class LigneIndice {
    private final int tailleCombinaison;
    private final List<Indice> indices = new ArrayList<>();

    /**
     * Créé une instance de LigneIndice
     */
    public LigneIndice() {
        this.tailleCombinaison = 4;
        for (int i = 0; i < tailleCombinaison; i++)
            this.indices.add(Indice.ABSENT);
    }

    /**
     * Créé une instance de LigneIndice selon une taille de combinaison
     *
     * @param tailleCombinaison La taille de la combinaison
     */
    public LigneIndice(int tailleCombinaison) {
        this.tailleCombinaison = tailleCombinaison;
        for (int i = 0; i < tailleCombinaison; i++)
            this.indices.add(Indice.ABSENT);
    }

    /**
     * Renvoi la taille de la combinaison
     *
     * @return La taille de la combinaison
     */
    public int getTailleCombinaison() {
        return tailleCombinaison;
    }

    /**
     * Renvoi les indices sous forme de nombres
     *
     * @return Les indices
     */
    public int[] getIntIndices() {
        int bien = 0;
        int mal = 0;
        for (Indice indice : indices) {
            if (indice == Indice.MAL_PLACE)
                mal++;
            else if (indice == Indice.BIEN_PLACE)
                bien++;
        }
        return new int[] {bien, mal};
    }

    /**
     * Renvoi les indices
     *
     * @return Les indices
     */
    public List<Indice> getIndices() {
        return this.indices;
    }

    /**
     * Calcule le score de la tentative
     *
     * @return Le score de la tentative
     */
    public int calculerScore() {
        int[] info = this.getIntIndices();
        return (info[0] * 3) + info[1];
    }
}
