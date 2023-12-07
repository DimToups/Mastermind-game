    package mastermind;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Random;

    public class Combinaison {
            // Champs (Fields)
            private int tailleCombi;
            private List<Couleur> combinaison=new ArrayList<Couleur> ();


            // Constructeur
            public Combinaison(int tailleCombi) {
                this.tailleCombi = tailleCombi;
                for (int i =0;i<tailleCombi;i++)
                {
                    this.combinaison.add(Couleur.Absent);
                }
            }

            //methode static pour pouvoir genere un combinaison secret
            public static Combinaison genererCombinaisonSecrete(int taille) {
                Combinaison secret=new Combinaison(taille);
                 Random r = new Random();
                for (int i =0;i<taille;i++)
                {
                    int n = r.nextInt(7);
                     ;
                    Couleur[] couleur = Couleur.values();
                    secret.setCouleurCombinaison(i, couleur[n+1]);
                }
                return secret;
            }

            // Méthode pour obtenir la couleur d'une position spécifique dans la combinaison
            public Couleur getCouleurCombinaison(int index) {
                return combinaison.get(index);
            }

            // Méthode pour définir la couleur à une position spécifique dans la combinaison
            public void setCouleurCombinaison(int index, Couleur couleur) {
                combinaison.set(index, couleur);
            }

            // Méthode pour obtenir la combinaison complète
            public Combinaison getCombinaison() {
                return this;
            }

            // Méthode pour obtenir la taille de la combinaison
            public int getTailleCombi() {
                return tailleCombi;
            }

            // Méthode pour vérifier si la combinaison est complète
            public boolean estComplet() {
                for (Couleur couleur : combinaison) {
                    if (couleur == Couleur.Absent) {
                        return false;
                    }
                }
                return true;
            }
        }


