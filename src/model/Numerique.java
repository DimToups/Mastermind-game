package src.model;

public class Numerique implements ModeJeu{
    public  void afficherIndices(LigneIndice ligne) {
        System.out.println("Vous avez "
            + ligne.getIntIndices()[0]
            + " pions bien placé(s) et "
            + ligne.getIntIndices()[1]
            + " pions mal placé(s).");
    }
}
