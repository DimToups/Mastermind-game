package src.model.enums;

/**
 * Enumération représentant les indices possibles dans une partie de Mastermind
 */
public enum Indice {
    /**
     * Indice indiquant que la couleur de la combinaison est correcte par rapport à la combinaison secrète
     */
    BIEN_PLACE,
    /**
     * Indice indiquant que la couleur de la combinaison est mal placée par rapport à la combinaison secrète
     */
    MAL_PLACE,
    /**
     * Indice indiquant que la couleur de la combinaison est absente dans la combinaison secrète
     */
    ABSENT
    ;
    /**
     * Retourne le nom de l'indice en minuscules et avec une majuscule au début du mot
     *
     * @param indice L'indice à traiter
     * @return Le nom de l'indice
     */
    public static String nomValide(Indice indice){
        String nomValide = indice.name().toUpperCase().charAt(0) + indice.name().toLowerCase().substring(1);
        if(!nomValide.contains("_"))
            return nomValide;
        for(int i = 0; i < nomValide.length(); i++)
            if(nomValide.charAt(i) == '_')
                nomValide = nomValide.substring(0, i) + " " + nomValide.substring(i + 1);
        return nomValide;
    }
}
