package src.model.enums;

public enum Indice {
    BIEN_PLACE,
    MAL_PLACE,
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
