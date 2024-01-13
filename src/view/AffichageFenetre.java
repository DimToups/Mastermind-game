    package src.view;

    import src.controller.GestionnaireJeu;
    import src.model.Combinaison;
    import src.model.LigneIndice;
    import src.model.enums.Couleur;
    import src.model.enums.ModeJeu;
    import src.model.userInterfaces.ObservateurUI;

    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.util.ArrayList;
    import java.util.List;

    import javax.swing.*;
    import java.awt.*;
    import java.util.Vector;

    /**
     * Classe vue servant d'interface utilisateur utilisant uniquement Java Swing pour communiquer avec l'utilisateur
     */
    public class AffichageFenetre extends JFrame implements ObservateurUI {
        private final GestionnaireJeu jeu;


        //
        // Composants du menu
        //

        // Composants de demande de fin d'initialisation
        private final JButton jbFinInitialisation = new JButton("Lancer la partie");

        // Composants de création d'un joueur
        private final JLabel jlCreationJoueur = new JLabel("Veuillez entrer votre pseudo");
        private final JTextField jtfCreationJoueur = new JTextField();

        // Composants de décision sur le nombre de manches
        private final JLabel jlNbManches = new JLabel("Nombre de manches");
        private final List<JRadioButton> jrbListNbManches = new ArrayList<>();
        private final ButtonGroup bgNbManches = new ButtonGroup();

        // Composants de décision sur le nombre de tentatives
        private final JLabel jlNbTentatives = new JLabel("Nombre de tentatives");
        private final List<JRadioButton> jrbListNbTentatives = new ArrayList<>();
        private final ButtonGroup bgNbTentatives = new ButtonGroup();

        // Composants de décision sur la taille des combinaisons
        private final JLabel jlTailleCombinaison = new JLabel("Taille des combinaisons à remplir :");
        private final List<JRadioButton> jrbListTailleCombi = new ArrayList<>();
        private final ButtonGroup bgTailleCombi = new ButtonGroup();

        // Composants de décision sur le mode de jeu
        private final JLabel jlDecisionModeJeu = new JLabel("Le mode de jeu initial");
        private final JComboBox<String> jcbModeJeu = new JComboBox<>(new Vector<>(List.of("Facile", "Classique", "Numerique")));

        private ModeJeu mj;

        /**
         * Crée une instance d'AffichageFenetre
         *
         * @param jeu Le contrôleur du jeu
         */
        public AffichageFenetre(GestionnaireJeu jeu) {
            super("Mastermind");
            this.jeu = jeu;

            // Initialisation des paramètres de la page
            setSize(1080, 720);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
            setLayout(new GridLayout(1, 1));
        }

        /**
         * Affiche le plateau de jeu
         */
        @Override
        public void miseEnPlacePlateau() {
            for (Component component : getContentPane().getComponents()) {
                component.setVisible(false);
                remove(component);
            }
            setLayout(new GridLayout(1, 2));
        }

        /**
         * Demande à l'utilisateur s'il veut mettre fin à la tentative actuelle
         *
         * @return La réponse de l'utilisateur
         */
        @Override
        public boolean demanderValidationTentative() {
            return false;
        }

        /**
         * Affiche les informations de la manche avant de passer à la prochaine
         */
        @Override
        public void prochaineManche(Combinaison combinaisonSecrete) {

        }

        /**
         * Fait entrer l'observateur en mode initialisation de partie
         */
        @Override
        public void entrerModeInitialisation() {
            getRootPane().getContentPane().removeAll();
            setLayout(new GridLayout(6, 1));


            // Attribution des actions des composants de base
            this.jbFinInitialisation.addActionListener(e -> {
                if (initialisationRemplie()) {
                    String nomJoueur = jtfCreationJoueur.getText().strip();
                    String nbManches = "";
                    for (JRadioButton radioButton : jrbListNbManches) {
                        if (radioButton.isSelected()) {
                            nbManches = radioButton.getText();
                            break;
                        }
                    }
                    String nbTentatives = "";
                    for (JRadioButton radioButton : jrbListNbTentatives) {
                        if (radioButton.isSelected()) {
                            nbTentatives = radioButton.getText();
                            break;
                        }
                    }
                    String tailleCombinaison = "";
                    for (JRadioButton radioButton : jrbListTailleCombi) {
                        if (radioButton.isSelected()) {
                            tailleCombinaison = radioButton.getText();
                            break;
                        }
                    }
                    String modeJeu = (String) jcbModeJeu.getSelectedItem();

                    // Affichage des données

                    mj=ModeJeu.StringToMJ(modeJeu);
                    jeu.setParamettre(nomJoueur, nbManches, nbTentatives, tailleCombinaison, modeJeu);

                } else {
                    JOptionPane.showMessageDialog(this,
                            "Toutes les informations nécessaires pour commencer la partie n'ont pas été renseignées",
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
        }


        /**
         * Demande à l'utilisateur toutes les questions pour créer une instance de Joueur
         */
        @Override
        public void creerJoueur() {
            Box b = new Box(BoxLayout.X_AXIS);
            jlCreationJoueur.setOpaque(true);
            jtfCreationJoueur.setOpaque(true);
            b.add(jlCreationJoueur);
            b.add(jtfCreationJoueur);
            add(b);
        }

        /**
         * Demande à l'utilisateur le nombre de manches qu'il veut faire
         */
        @Override
        public void deciderNbManches() {
            Box b = new Box(BoxLayout.X_AXIS);
            jlNbManches.setOpaque(true);
            b.add(jlNbManches);
            jrbListNbManches.clear();
            for (int i = 1; i < 6; i++)
                jrbListNbManches.add(new JRadioButton(String.valueOf(i)));
            for (JRadioButton radioButton : jrbListNbManches) {
                b.add(radioButton);
                bgNbManches.add(radioButton);
            }
            add(b);
        }

        /**
         * Demande à l'utilisateur la taille des combinaisons à composer
         */
        @Override
        public void deciderTailleCombinaison() {
            Box b = new Box(BoxLayout.X_AXIS);
            jlTailleCombinaison.setOpaque(true);
            b.add(jlTailleCombinaison);
            jrbListTailleCombi.clear();
            for (int i = 2; i < 7; i++)
                jrbListTailleCombi.add(new JRadioButton(String.valueOf(i)));
            for (JRadioButton radioButton : jrbListTailleCombi) {
                b.add(radioButton);
                bgTailleCombi.add(radioButton);
            }
            add(b);
        }

        /**
         * Demande à l'utilisateur le nombre de tentatives qu'il veut pour ses manches
         */
        @Override
        public void deciderNbTentatives() {
            Box b = new Box(BoxLayout.X_AXIS);
            jlNbTentatives.setOpaque(true);
            b.add(jlNbTentatives);
            jrbListNbTentatives.clear();
            for (int i = 2; i < 13; i++)
                jrbListNbTentatives.add(new JRadioButton(String.valueOf(i)));
            for (JRadioButton radioButton : jrbListNbTentatives) {
                b.add(radioButton);
                bgNbTentatives.add(radioButton);
            }
            add(b);
        }

        /**
         * Demande à l'utilisateur le mode de jeu auquel il veut jouer
         */
        @Override
        public void deciderModeJeu() {
            Box b = new Box(BoxLayout.X_AXIS);
            jcbModeJeu.setSelectedIndex(0);
            jlDecisionModeJeu.setOpaque(true);
            jcbModeJeu.setOpaque(true);
            b.add(jlDecisionModeJeu);
            b.add(jcbModeJeu);
            add(b);
        }

        /**
         * Demande à l'utilisateur s'il veut valider les paramètres de jeu
         */
        @Override
        public void demanderFinInitialisation() {
            add(jbFinInitialisation);
        }

        /**
         * Met fin à la partie
         */
        @Override
        public void finirPartie(int score) {
            getContentPane().removeAll(); // Efface tous les composants de la fenêtre
            getContentPane().setLayout(new BorderLayout()); // Définit le gestionnaire de disposition
            JLabel messageLabel = new JLabel("Merci d'avoir jouer \n votre score est de " + score, SwingConstants.CENTER); // Crée un JLabel avec le message
            getContentPane().add(messageLabel); // Ajoute le JLabel à la fenêtre
            revalidate(); // Rafraîchit la fenêtre pour afficher les changements
            repaint(); // Repaint la fenêtre pour s'assurer que tout est bien affiché
        }

        private JPanel[][] cases;
        private JButton[] buttons;
        JLabel mJNumerique[] ;

        private JPanel[][] indice;
        private int tentativActuell = 0;

        private int tailleCombi;
        private int nbTentatives;


        public void initialisationManche(String nomJoueur, int MancheActuelle, int nbTentatives, int tailleCombinaison) {
            setTitle("mastermind");


            getRootPane().getContentPane().removeAll();
            tailleCombi = (tailleCombinaison);
            tentativActuell = 0;

            this.nbTentatives = (nbTentatives);

            getContentPane().setLayout(new GridLayout(1, 2, 10, 10));  // Espacement entre les composants
            JPanel mainPanel = new JPanel(new BorderLayout());

            // Panneau de gauche pour la sélection de couleurs
            DefaultListModel<String> colorModel = new DefaultListModel<>();


            for (Couleur couleurValide : Couleur.getVraiesCouleurs())
                colorModel.addElement(Couleur.nomValide(couleurValide));
            JList<String> colorList = new JList<>(colorModel);


            mainPanel.add(new JScrollPane(colorList), BorderLayout.WEST);

            // Panneau de droite pour les boxes
            JPanel rightPanel = new JPanel();
            rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

            // Box de zone de plateau (englobe tout le reste)
            JPanel plateauZoneBox = new JPanel(new BorderLayout());

            // Box de haut de plateau
            JPanel topBox = new JPanel();
            // Ajout du bouton et du label
            JButton topButton = new JButton("terminer la manche");

            topButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {

                        jeu.resumerGame();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                        JOptionPane.showMessageDialog(AffichageFenetre.this,
                                "Une erreur est survenue : " + ex.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            JLabel topLabel = new JLabel("    " + nomJoueur + " Vous etes a la manche : " + MancheActuelle);

            topBox.add(topButton);
            topBox.add(topLabel);
            plateauZoneBox.add(topBox, BorderLayout.NORTH);

            // Box de plateau (englobe les Box de combinaison et d'indices)
            JPanel plateauBox = new JPanel();
            plateauBox.setLayout(new BoxLayout(plateauBox, BoxLayout.Y_AXIS));
            plateauBox.setPreferredSize(new Dimension(600, 200));

            cases = new JPanel[this.nbTentatives][tailleCombi];
            buttons = new JButton[tailleCombi];
            indice = new JPanel[this.nbTentatives][tailleCombi];
            mJNumerique=new JLabel[this.nbTentatives];

            // Panneau principal avec BorderLayout
            JPanel tentativePanel = new JPanel();
            tentativePanel.setLayout(new BoxLayout(tentativePanel, BoxLayout.Y_AXIS));

            getContentPane().add(tentativePanel);

            // Panneau pour les cases avec GridLayout


            // Panneau pour les boutons avec GridLayout
            JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 10, 10)); // 1 ligne, 6 colonnes
            tentativePanel.add(buttonPanel);

            // Initialisation et ajout des cases
            for (int j = 0; j < this.nbTentatives; j++) {
                JPanel casePanel = new JPanel(new GridLayout(1, tailleCombi, 10, 10)); // 1 ligne, tailleCombi colonnes
                for (int i = 0; i < tailleCombi; i++) {
                    cases[j][i] = new JPanel();
                    cases[j][i].setPreferredSize(new Dimension(100, 100));
                    cases[j][i].setBackground(Color.WHITE);
                    casePanel.add(cases[j][i]);
                }
                tentativePanel.add(casePanel);

                JPanel indicPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // 1 ligne, 6 colonnes
                indicPanel.setPreferredSize(new Dimension(10, 10));

                casePanel.add(indicPanel, BorderLayout.EAST);

                if (mj==ModeJeu.NUMERIQUE)
                {
                    mJNumerique[j]=new JLabel();
                    indicPanel.add(mJNumerique[j]);
                }
                else {
                    for (int i = 0; i < tailleCombi; i++) {
                        indice[j][i] = new JPanel();
                        indice[j][i].setPreferredSize(new Dimension(10, 10));
                        indice[j][i].setBackground(Color.WHITE);
                        indicPanel.add(indice[j][i]);
                    }
                }

            }
            // Liste de couleurs


            // Initialisation et ajout des boutons
            // Initialisation et ajout des boutons
            for (int i = 0; i < tailleCombi; i++) {
                buttons[i] = new JButton("Case " + (i + 1));
                // Réduisez les valeurs de largeur et de hauteur ici pour rendre les boutons plus petits
                buttons[i].setPreferredSize(new Dimension(50, 50)); // Taille réduite par exemple
                int caseIndex = i; // Indice de la case pour l'utilisation dans l'expression lambda
                buttons[i].addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        try {

                            Couleur couleur = Couleur.valueOf((colorList.getSelectedValue().toUpperCase()));

                            jeu.ChangerCouleur(couleur, caseIndex);

                            //  changeColor(cases[tentativActuell][caseIndex], colorList.getSelectedValue());

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                            JOptionPane.showMessageDialog(AffichageFenetre.this,
                                    "Une erreur est survenue : " + ex.getMessage(),
                                    "Erreur",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                buttonPanel.add(buttons[i]);
            }
            final boolean[] a = {true};

            JButton buton = new JButton("Valider Tentative");
            buton.setPreferredSize(new Dimension(50, 50));
            buton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if ((!a[0])) {
                        } else if ((tentativActuell == nbTentatives - 1) && (a[0])) {
                            if (estRemplit(cases[tentativActuell])) {

                                jeu.passerProchaineTentative();
                                jeu.resumerGame();
                                a[0] = false;
                            }


                        } else {
                            if (estRemplit(cases[tentativActuell])) {

                                jeu.passerProchaineTentative();

                                tentativActuell++;
                                defaultColor(cases[tentativActuell]);

                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                        JOptionPane.showMessageDialog(AffichageFenetre.this,
                                "Une erreur est survenue : " + ex.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            defaultColor(cases[0]);

            buttonPanel.add(buton); // Ajouter la Box de plateau au centre de la Box de zone de plateau


            // Ajout de la liste de couleurs
            //  tentativePanel.add(colorList, BorderLayout.EAST);
            plateauBox.add(tentativePanel);


            // Ajout du innerPanel à la Box de zone de plateau
            plateauZoneBox.add(plateauBox, BorderLayout.CENTER);

            rightPanel.add(plateauZoneBox);
            mainPanel.add(rightPanel, BorderLayout.CENTER);

            getContentPane().add(mainPanel);
            setSize(1080, 720);
            pack();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            setSize(screenSize);

        }

        private boolean estRemplit(JPanel[] aCase) {
            for (JPanel panel : aCase) {
                if (panel.getBackground().equals(Color.GRAY)) {
                    return false; // Un des panneaux a un fond gris
                }
            }
            return true; // Aucun panneau n'a un fond gris
        }


        private void defaultColor(JPanel[] aCase) {
            for (int i = 0; i < aCase.length; i++) {
                changeColor(aCase[i], "");
            }
        }


        public void updateCouleur(Couleur couleur, int indiex) {
            changeColor(cases[tentativActuell][indiex], couleur.name());
        }

        private void changeColor(JPanel panel, String colorName) {
            switch (colorName.toLowerCase()) {
                case "bleu":
                    panel.setBackground(Color.BLUE);
                    break;
                case "jaune":
                    panel.setBackground(Color.YELLOW);
                    break;
                case "rouge":
                    panel.setBackground(Color.RED);
                    break;
                case "vert":
                    panel.setBackground(Color.GREEN);
                    break;
                case "orange":
                    panel.setBackground(Color.ORANGE);
                    break;
                case "violet":
                    panel.setBackground(Color.MAGENTA);
                    break;
                case "cyan":
                    panel.setBackground(Color.CYAN);
                    break;
                case "rose":
                    panel.setBackground(Color.PINK);
                    break;

                default:
                    panel.setBackground(Color.GRAY);
            }
        }


        /**
         * Permet de changer la couleur dans une combinaison
         *
         * @param combinaison La combinaison à modifier
         */
        @Override
        public void changerCouleur(Combinaison combinaison) {

        }

        /**
         * Affiche les indices passés en paramètres
         *
         * @param indices Les indices à afficher
         */
        @Override
        public void afficherIndices(LigneIndice indices) {

            if (mj==ModeJeu.FACILE) {
                for (int i = 0; i < indices.getTailleCombinaison(); i++) {

                    switch (indices.getIndices().get(i)) {
                        case BIEN_PLACE -> indice[tentativActuell][i].setBackground((Color.GREEN));
                        case ABSENT -> indice[tentativActuell][i].setBackground((Color.WHITE));
                        case MAL_PLACE -> indice[tentativActuell][i].setBackground((Color.RED));
                        default -> indice[tentativActuell][i].setBackground((Color.white));
                    }

                }
            } else if (mj==ModeJeu.CLASSIQUE) {

                for (int i = 0; i < indices.getIntIndices()[1]; i++) {

                    indice[tentativActuell][i].setBackground((Color.GREEN));

                }
                for (int i = 0; i < indices.getIntIndices()[2]; i++) {
                    indice[tentativActuell][i+indices.getIntIndices()[1]].setBackground((Color.GREEN));

                }

            }
            else {
                mJNumerique[tentativActuell-1]=new JLabel("bien placé :"+indices.getIntIndices()[1]+"mal placé"+indices.getIntIndices()[2]);
            }


        }


        public void resumerManche(int score, List<Couleur> couleurs, boolean b) {


            setTitle("mastermind");


            getRootPane().getContentPane().removeAll();

            getContentPane().removeAll(); // Efface tous les composants de la fenêtre
            getContentPane().setLayout(new GridLayout(4, 0, 10, 10)); // Définit le gestionnaire de disposition

            JPanel[] combinaisonSecret = new JPanel[tailleCombi];
            String ressumer;
            if (b) {
                ressumer = "vous avez reussi a trouver";
            } else {
                ressumer = "vous n'avez malheureusement pas trouver ";
            }
            JLabel messageLabel = new JLabel(ressumer + "              votre score et de " + score, SwingConstants.CENTER); // Crée un JLabel avec le message
            getContentPane().add(messageLabel);

            JLabel MessageFinal = new JLabel("votre combinaison est : " + SwingConstants.SOUTH); // Crée un JLabel avec le message
            getContentPane().add(MessageFinal);

            JPanel caseCombinaison = new JPanel(new GridLayout(1, 6, 10, 10)); // 1 ligne, 6 colonnes

            for (int i = 0; i < tailleCombi; i++) {
                combinaisonSecret[i] = new JPanel();
                combinaisonSecret[i].setPreferredSize(new Dimension(100, 100));
                changeColor(combinaisonSecret[i], couleurs.get(i).name());
                caseCombinaison.add(combinaisonSecret[i]);
            }
            getContentPane().add(caseCombinaison); // Ajoute le JLabel à la fenêtre


            JButton buton = new JButton("Continuer");
            buton.setPreferredSize(new Dimension(50, 50));
            buton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        jeu.mancheSuivant();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                        JOptionPane.showMessageDialog(AffichageFenetre.this,
                                "Une erreur est survenue : " + ex.getMessage(),
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            getContentPane().add(buton); // Ajoute le JLabel à la fenêtre


            revalidate(); // Rafraîchit la fenêtre pour afficher les changements
            repaint(); // Repaint la fenêtre pour s'assurer que tout est bien affiché


        }


        /**
         * Affiche le résumé d'une tentative
         *
         * @param combinaison La combinaison à afficher
         */
        @Override
        public void affichageTentative(Combinaison combinaison) {

        }

        /**
         * Demande à l'utilisateur s'il veut mettre fin à la tentative
         */
        @Override
        public void demanderFinTentative() {

        }

        /**
         * Donne l'état du formulaire d'initialisation de partie
         *
         * @return L'état du formulaire
         */
        private boolean initialisationRemplie() {
            return jcbModeJeu.getSelectedItem() != null && !jtfCreationJoueur.getText().isBlank();
        }

        /**
         * Affiche une combinaison
         *
         * @param combinaison La combinaison à afficher
         */
        @Override
        public void affichageCombinaison(Combinaison combinaison) {
        }

        /**
         * Remet à zéro la combinaison actuelle
         *
         * @return Indique si la combinaison a été réinitialisée ou non à l'aide d'un booléen
         */
        @Override
        public boolean demanderRemiseAZero() {

            return false;
        }

        /**
         * Demande à l'utilisateur s'il veut mettre fin à la manche
         *
         * @return La réponse de l'utilisateur (true : fin de la manche, false sinon)
         */
        @Override
        public boolean demanderFinManche() {
            return false;
        }

        /**
         * Fini la manche suite à la réussite du joueur
         */
        @Override
        public void finirManche() {

        }

        /**
         *
         */
        @Override
        public void afficherIndicesFacile(LigneIndice indices) {

        }

        /**
         *
         */
        @Override
        public void afficherIndicesClassique(LigneIndice indices) {

        }

        /**
         *
         */
        @Override
        public void afficherIndicesNumerique(LigneIndice indices) {

        }
    }