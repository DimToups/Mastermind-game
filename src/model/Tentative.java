package src.model;

import src.model.enums.Couleur;
import src.model.enums.Indice;
import src.view.Classique;

import java.util.*;

public class Tentative {
    private Combinaison combinaisonEntree;
    private LigneIndice ligneIndice;
    private final ModeJeu modeJeu;
    public Tentative(int tailleCombi, ModeJeu modeJeu) {
        this.combinaisonEntree = new Combinaison(tailleCombi);
        this.ligneIndice = new LigneIndice(tailleCombi);
        this.modeJeu = modeJeu;
    }
    public void lancerTentative() {
        Scanner in = new Scanner(System.in);
        boolean fini = false;

        while (!fini) {
            //Affichage de la combinaison entrée de la tentative
            System.out.println("\nVoici votre combinaison :");
            for(int i = 0; i < combinaisonEntree.getCombinaison().size(); i++)
                System.out.print(i + " : "
                        + combinaisonEntree.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                        + combinaisonEntree.getCombinaison().get(i).name().substring(1).toLowerCase()
                        + "  | ");
            System.out.println();

            // Début de la tentative
            String reponse = "";
            int reponseInt = -1;

            //Gestion du cas où la combinaison est complète
            if(this.combinaisonEntree.estComplet()){
                while(!reponse.equals("oui") && !reponse.equals("non")) {
                    System.out.println("Votre combinaison est complète. Voulez vous la valider ?\n - oui\n - non");
                    reponse = in.nextLine().toLowerCase().strip();

                    //Gestion d'une mauvaise réponse
                    if(!reponse.equals("oui") && !reponse.equals("non"))
                        System.out.println("La valeur entrée est invalide.");
                }
                if(reponse.equals("oui"))
                    fini = true;
            }

            //Gestion du cas où le joueur veut modifier sa combinaison
            if(!reponse.equals("oui")){
                //Obtention de la réponse de l'utilisateur
                while(reponseInt < 0 || reponseInt >= combinaisonEntree.getTailleCombinaison()) {
                    try{
                        System.out.println("Veuillez entrer l'index de la combinaison à modifier.");
                        reponseInt = Integer.parseInt(in.nextLine().strip());
                    }
                    catch (Exception e){
                        System.out.println("La valeur entrée est invalide.");
                    }
                }

                // Obtention de la couleur à placer et placement de cette couleur
                boolean estPlace = false;
                while (!estPlace) {
                    System.out.print("Quelle couleur voulez-vous placer ?\nCouleurs disponibles : \n");
                    for (Couleur couleurValides : Couleur.getVraiesCouleurs())
                        System.out.print(couleurValides.name().substring(0,1).toUpperCase()
                                + couleurValides.name().substring(1).toLowerCase()
                                + " | ");
                    System.out.println();

                    try {
                        Couleur couleurChoisie = Couleur.valueOf(in.nextLine().strip().toUpperCase());
                        combinaisonEntree.setCouleur(reponseInt, couleurChoisie);
                        estPlace = true;
                    }
                    catch (Exception e){
                        System.out.println("La valeur entrée est invalide.");
                    }
                }
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
        System.out.println("Combinaison entrée :");
        for(int i = 0; i < combinaisonEntree.getCombinaison().size(); i++)
            System.out.print(combinaisonEntree.getCombinaison().get(i).name().substring(0, 1).toUpperCase()
                    + combinaisonEntree.getCombinaison().get(i).name().substring(1).toLowerCase()
                    + "  | ");
        System.out.println("\n");

        //Affichage des indices
        System.out.println("Voici les indices :");
        modeJeu.afficherIndices(ligneIndice);

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
