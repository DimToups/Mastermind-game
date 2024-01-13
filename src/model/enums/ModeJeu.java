package src.model.enums;

/**
 * Enumération représentant les modes de jeux possibles
 */
public enum ModeJeu {
    /**
     * Le mode de jeu facile. Les indices indiquent la case visée
     */
    FACILE,
    /**
     * Le mode de jeu classique. Les indices affichés donnent juste le nombre de couleurs bien placées et mal placées
     */
    CLASSIQUE,
    /**
     * Le mode de jeu numérique. Les indices affichés donnent juste le nombre de couleurs bien placées et mal placées
     */
    NUMERIQUE;




    public static ModeJeu StringToMJ(String modeStr) {


        try {
            return ModeJeu.valueOf(modeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Aucun mode de jeu ne correspond à la chaîne de caractères fournie.");
            return null; // Ou toute autre gestion d'erreur appropriée
        }
    }


}
