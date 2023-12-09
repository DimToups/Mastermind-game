package mastermind;

import java.util.ArrayList;
import java.util.List;

public class LigneIndice {
    private int tailleCombi;
    private  List<Indice> indices=new ArrayList<Indice>();

    // Méthodes (Methods)

    // Constructeur
    public LigneIndice(int tailleCombi) {
        this.tailleCombi = tailleCombi;
        for (int i =0;i<tailleCombi;i++)
        {
            this.indices.add(Indice.ABSENT);
        }
    }


    public int getTailleCombi() {
        return tailleCombi;
    }


    // Méthode pour obtenir l'indice à une position spécifique dans la ligne
    public Indice getIndice(int index) {
        return indices.get(index);
    }

    // Méthode pour définir l'indice à une position spécifique dans la ligne
    public void setIndice(Indice indice, int index) {
        indices.set(index, indice);
    }

    // Méthode pour obtenir tous les indices de la ligne
    public int [] getIndices() {
        int bien=0;
        int mal =0;
        for (Indice indice : indices) {
            if (indice==Indice.MAL_PLACE) {mal++;}
            else if (indice==Indice.BIEN_PLACE) {bien++;}
        }
        return new int[]{bien,mal};
    }


    public int calculerScore() {
        int [] info = this.getIndices();
        return (info[0]*3)+info[1];
    }
}
