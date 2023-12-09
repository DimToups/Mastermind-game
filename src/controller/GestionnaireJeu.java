package controller;

import model.*;

import java.util.Scanner;

public class GestionnaireJeu {
     private Joueur creerJoueur()
     {
         System.out.println("Veuiller entrer votre pseudo");
         Scanner in = new Scanner(System.in);
         String mot = in.nextLine();
         return new Joueur(mot);
     }
    private Integer getNbManche()
    {
        System.out.println("Veuiller entrer le nombre de manche que vous vouler fair (MIN 3 ET MAX 6)");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if ((nb>=3)&&(nb<=5)) {
            return nb;
        }
        else
        {
            System.out.println("valeur invalide");
            return getNbManche();
        }

    }
    private Integer getTailleCombi()
    {
        System.out.println("Veuiller entrer la taille de la combinaison que vous vouler fair (MIN 4 ET MAX 6)");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if ((nb>=4)&&(nb<=6)) {
            return nb;
        }
        else
        {
            System.out.println("valeur invalide");
            return getTailleCombi();
        }
    }
    private Integer getNbTentatives()
    {
        System.out.println("Veuiller entrer le nombre de tentatives que vous vouler fair (MIN 10 ET MAX 12)");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if ((nb>=10)&&(nb<=12)) {
            return nb;
        }
        else
        {
            System.out.println("valeur invalide");
            return getNbTentatives();
        }
    }
    private ModeJeu getModeJeu()
    {
        System.out.println("mode de jeu :   Default Classique (1) , Numerique (2) , Facile(3)");
        Scanner in = new Scanner(System.in);
        int nb = Integer.parseInt(in.nextLine());
        if (nb== 3)  {
            return new Facile();
        } else if (nb==2) {
            return new Numerique();

        } else  {
            return  new Classique();

        }


    }
    public    void demarrerPartie()
    {
        Partie partie = new Partie(creerJoueur(),getNbManche(),getTailleCombi(),getNbTentatives(),getModeJeu());
        partie.lancerPartie();
    }
}
