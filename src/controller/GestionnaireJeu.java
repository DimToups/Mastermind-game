package controller;

import model.*;

import java.util.Scanner;

public class GestionnaireJeu {
     private Joueur creerJoueur()
     {
         System.out.println("Veuillez entrer votre pseudo.");
         Scanner in = new Scanner(System.in);
         String mot = in.nextLine();
         return new Joueur(mot);
     }
    private Integer getNbManche()
    {
        System.out.println("Veuillez entrer le nombre entre 3 et 6 de manches que vous voulez faire.");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if ((nb >= 3) && (nb <= 5))
            return nb;
        else {
            System.out.println("valeur invalide");
            return getNbManche();
        }
    }
    private Integer getTailleCombi() {
        System.out.println("Veuillez entrer la taille entre 4 et 6 de la combinaison que vous voulez faire");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if ((nb >= 4) && ( nb <= 6))
            return nb;
        else {
            System.out.println("valeur invalide");
            return getTailleCombi();
        }
    }
    private Integer getNbTentatives()
    {
        System.out.println("Veuillez entrer le nombre de tentatives entre 10 et 12 que vous voulez faire.");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if ((nb>=10)&&(nb<=12)) {
            return nb;
        }
        else {
            System.out.println("Valeur invalide");
            return getNbTentatives();
        }
    }
    private ModeJeu getModeJeu() {
        System.out.println("Mode de jeu :\n1 : Classique\n2 : Numérique\n3 : Facile");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if (nb== 3)
            return new Facile();
        else if (nb==2)
            return new Numerique();
        else
            return  new Classique();
    }
    public void demarrerPartie() {
        Partie partie = new Partie(creerJoueur(),getNbManche(),getTailleCombi(),getNbTentatives(),getModeJeu());
        partie.lancerPartie();
    }
}
