package model;

import model.enums.Couleur;
import model.enums.Indice;

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
        boolean fini=true;
        while (fini) {
            System.out.println("*************************************************************************************************");
            combinaisonEntree.afficherCouleurs("votre combinaison actuelle ");
            System.out.println("valider(oui) ou sinon veuiller mettre un index entre 0 et "+(combinaisonEntree.getTailleCombinaison()-1));
            Scanner in = new Scanner(System.in);
            String mot = in.nextLine();
            if (Objects.equals(mot, "oui")) {
                if (this.estRemplie())
                 fini = false;
                else
                    System.out.println("veuiller remplir tout les combinaison");
            }
            else {
                int index = Integer.parseInt(mot);
                System.out.println("Entrez une  Couleur.");
                mot = in.nextLine().toUpperCase();
                this.ajoutCouleur(index, Couleur.valueOf(mot));
            }
        }
        //return  combi;
    }
    public void ajoutCouleur(int index, Couleur couleur) {
        combinaisonEntree.setClouleur(index,couleur);
    }
    public boolean evaluerTentative(Combinaison combinaisonSecrete) {
        if (combinaisonEntree.estComplet()) {
            boolean estCorrecte = true ;
            for (int i = 0; i < combinaisonEntree.getTailleCombinaison(); i++) {
                if (combinaisonSecrete.getCouleur(i) != combinaisonEntree.getCouleur(i))
                    estCorrecte = false;
                else
                    ligneIndice.setIndice(Indice.BIEN_PLACE, i);
            }

            List<Integer> contienDeja = new ArrayList<>();
            for (int i = 0; i < combinaisonEntree.getTailleCombinaison(); i++) {
                if (ligneIndice.getIndice(i) != Indice.BIEN_PLACE) {
                    for (int j = 0; j < combinaisonEntree.getTailleCombinaison(); j++) {
                        if (ligneIndice.getIndice(j) != Indice.BIEN_PLACE) {
                            if (combinaisonSecrete.getCouleur(j) == combinaisonEntree.getCouleur(i)) {
                                if (!contienDeja.contains(j)) {
                                    ligneIndice.setIndice(Indice.MAL_PLACE, i);
                                    contienDeja.add(j);
                                }
                            }
                        }
                    }
                }
            }

            System.out.println();
            combinaisonEntree.afficherCouleurs("Combinaison final");
            modeJeu.afficherIndices(ligneIndice,"votre ligne d'indice ");

            return estCorrecte;
        }
        else
            return false;
    }

    public Boolean estRemplie()
    {
        return combinaisonEntree.estComplet();
    }
    public Combinaison getCombinaisonEntree()
    {
        return combinaisonEntree;
    }

    public LigneIndice getLigneIndice()
    {
        return ligneIndice;
    }
    private int calculerBonus() {
        if (modeJeu instanceof Classique)
            return 4;
        return 0;
    }

    public int calculerScore() {
        return ligneIndice.calculerScore() + calculerBonus();
    }
}
