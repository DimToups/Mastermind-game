package src.model;

import src.model.enums.Indice;

import java.util.ArrayList;
import java.util.List;

public class LigneIndice {
    private final int tailleCombi;
    private List<Indice> indices = new ArrayList<>();
    public LigneIndice(int tailleCombi) {
        this.tailleCombi = tailleCombi;
        for (int i = 0; i < tailleCombi; i++)
            this.indices.add(Indice.ABSENT);
    }
    public int getTailleCombi() {
        return tailleCombi;
    }
    // Méthode pour définir l'indice à une position spécifique dans la ligne
    public void setIndice(Indice indice, int index) {
        indices.set(index, indice);
    }
    // Méthode pour obtenir tous les indices de la ligne
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
    public List<Indice> getIndices() {
        return this.indices;
    }
    public int calculerScore() {
        int[] info = this.getIntIndices();
        return (info[0] * 3) + info[1];
    }
}
