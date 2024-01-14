package src.view;

import src.controller.GestionnaireJeu;
import src.model.Combinaison;
import src.model.LigneIndice;
import src.model.enums.Couleur;
import src.model.enums.Indice;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurTentative;
import src.model.userInterfaces.ObservateurUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Vector;

/**
 * Classe vue servant d'interface utilisateur utilisant uniquement Java Swing pour communiquer avec l'utilisateur
 */
public class AffichageFenetre extends JFrame implements ObservateurUI {
    //
    // Variables globales
    //
    private final GestionnaireJeu jeu;
    private int coefficient = 10;

    //
    // Images utilisées
    //

    // Pions
    BufferedImage biPionAbsent;
    BufferedImage biPionBleu;
    BufferedImage biPionJaune;
    BufferedImage biPionRouge;
    BufferedImage biPionVert;
    BufferedImage biPionOrange;
    BufferedImage biPionViolet;
    BufferedImage biPionCyan;
    BufferedImage biPionRose;

    // Indices
    BufferedImage biIndiceAbsent;
    BufferedImage biIndiceBlanc;
    BufferedImage biIndiceNoir;

    // Plateau
    BufferedImage biPlateauCaseCombinaison;
    BufferedImage biPlateauCaseMilieu;
    BufferedImage biPlateauCaseIndice;
    BufferedImage biPlateauCaseCombinaisonSecrete;
    BufferedImage biPlateauBordHautIndice;
    BufferedImage biPlateauBordGauche;
    BufferedImage biPlateauBordDroit;
    BufferedImage biPlateauBordHautGauche;
    BufferedImage biPlateauBordHautDroit;
    BufferedImage biPlateauBordBas;
    BufferedImage biPlateauBordBasMilieu;
    BufferedImage biPlateauBordBasGauche;
    BufferedImage biPlateauBordBasDroit;


    //
    // Composants du plateau
    //

    //
    private JPanel[][] cases;
    private JButton[] buttons;
    JLabel[] mJNumerique;
    private JPanel[][] indices;
    private int tentativeActuelle = 0;
    private int tailleCombi;
    private int nbTentatives;


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

        // Initialisation de la partie
        this.entrerModeInitialisation();
        this.creerJoueur();
        this.deciderTailleCombinaison();
        this.deciderNbTentatives();
        this.deciderNbManches();
        this.deciderModeJeu();
        this.demanderFinInitialisation();

        // Attribution des images aux bufferedImages
        initialisationImages();
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

                mj = ModeJeu.StringToMJ(Objects.requireNonNull(modeJeu));
                jeu.setParametres(nomJoueur, nbManches, nbTentatives, tailleCombinaison, modeJeu);

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

    private void initialisationImages(){
        try {
            // Pions
            biPionAbsent = ImageIO.read(new File("ressources/img/pions/absent.png"));
            biPionBleu = ImageIO.read(new File("ressources/img/pions/pion_bleu.png"));
            biPionJaune = ImageIO.read(new File("ressources/img/pions/pion_jaune.png"));
            biPionRouge = ImageIO.read(new File("ressources/img/pions/pion_rouge.png"));
            biPionVert = ImageIO.read(new File("ressources/img/pions/pion_vert.png"));
            biPionOrange = ImageIO.read(new File("ressources/img/pions/pion_orange.png"));
            biPionViolet = ImageIO.read(new File("ressources/img/pions/pion_violet.png"));
            biPionCyan = ImageIO.read(new File("ressources/img/pions/pion_cyan.png"));
            biPionRose = ImageIO.read(new File("ressources/img/pions/pion_rose.png"));

            // Indices
            biIndiceAbsent = ImageIO.read(new File("ressources/img/indices/absent.png"));
            biIndiceBlanc = ImageIO.read(new File("ressources/img/indices/indice_blanc.png"));
            biIndiceNoir = ImageIO.read(new File("ressources/img/indices/indice_noir.png"));

            // Plateau
            biPlateauCaseCombinaison = ImageIO.read(new File("ressources/img/plateau/case_combinaison.png"));
            biPlateauCaseMilieu = ImageIO.read(new File("ressources/img/plateau/case_milieu.png"));
            biPlateauCaseIndice = ImageIO.read(new File("ressources/img/plateau/case_indice.png"));
            biPlateauCaseCombinaisonSecrete = ImageIO.read(new File("ressources/img/plateau/bord_haut_combinaison.png"));
            biPlateauBordHautIndice = ImageIO.read(new File("ressources/img/plateau/bord_haut_indice.png"));
            biPlateauBordGauche = ImageIO.read(new File("ressources/img/plateau/bord_combinaison_gauche.png"));
            biPlateauBordDroit = ImageIO.read(new File("ressources/img/plateau/bord_combinaison_droit.png"));
            biPlateauBordHautGauche = ImageIO.read(new File("ressources/img/plateau/bord_haut_gauche.png"));
            biPlateauBordHautDroit = ImageIO.read(new File("ressources/img/plateau/bord_haut_droit.png"));
            biPlateauBordBas = ImageIO.read(new File("ressources/img/plateau/bord_bas_combinaison_indice.png"));
            biPlateauBordBasMilieu = ImageIO.read(new File("ressources/img/plateau/bord_bas_milieu.png"));
            biPlateauBordBasGauche = ImageIO.read(new File("ressources/img/plateau/bord_bas_gauche.png"));
            biPlateauBordBasDroit = ImageIO.read(new File("ressources/img/plateau/bord_bas_droit.png"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void initialisationManche(String nomJoueur, int MancheActuelle, int nbTentatives, int tailleCombinaison) {
        getRootPane().getContentPane().removeAll();
        tailleCombi = (tailleCombinaison);
        tentativeActuelle = 0;

        this.nbTentatives = (nbTentatives);

        getContentPane().setLayout(new GridLayout(1, 2));
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panneau de gauche pour la sélection de couleurs
        DefaultListModel<String> colorModel = new DefaultListModel<>();

        for (Couleur couleurValide : Couleur.getVraiesCouleurs())
            colorModel.addElement(couleurValide.name());
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
        JButton btnFinirManche = new JButton("terminer la manche");
        btnFinirManche.setSize(new Dimension(100,100));

        btnFinirManche.addActionListener(e -> {
            try {
                jeu.resumerPartie();
            } catch (Exception ex) {
                // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                JOptionPane.showMessageDialog(AffichageFenetre.this,
                        "Une erreur est survenue : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        JLabel topLabel = new JLabel(nomJoueur + ", vous êtes a la manche " + MancheActuelle);
        JButton btnRemiseAZero = new JButton("Réinitialiser la combinaison");
        btnRemiseAZero.addActionListener(l -> this.jeu.remiseAZeroTentativeActuelle());

        topBox.add(btnFinirManche);
        topBox.add(topLabel);
        topBox.add(btnRemiseAZero);
        plateauZoneBox.add(topBox, BorderLayout.NORTH);

        // Box de plateau (englobe les Box de combinaison et d'indices)
        JPanel plateauBox = new JPanel();
        plateauBox.setLayout(new BoxLayout(plateauBox, BoxLayout.Y_AXIS));
        plateauBox.setSize(new Dimension(600, 200));

        System.out.println(tailleCombi + tailleCombinaison % 4);

        cases = new JPanel[this.nbTentatives][tailleCombi];
        buttons = new JButton[tailleCombi];
        indices = new JPanel[this.nbTentatives][tailleCombi + tailleCombinaison % 4];
        mJNumerique = new JLabel[this.nbTentatives];

        // Panneau principal avec BorderLayout
        JPanel tentativePanel = new JPanel();
        tentativePanel.setLayout(new BoxLayout(tentativePanel, BoxLayout.Y_AXIS));

        getContentPane().add(tentativePanel);

        // Panneau pour les cases avec GridLayout

        // Panneau pour les boutons avec GridLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        tentativePanel.add(buttonPanel);

        // Initialisation et ajout des cases
        for (int j = 0; j < this.nbTentatives; j++) {
            // Cases des combinaisons
            JPanel casePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            for (int i = 0; i < tailleCombi; i++) {
                cases[j][i] = new JPanel();
                cases[j][i].setPreferredSize(new Dimension(biPlateauCaseCombinaison.getWidth() / coefficient, biPlateauCaseCombinaison.getHeight() / coefficient));
                changeColor(cases[j][i], Couleur.ABSENT);
                casePanel.add(cases[j][i]);
            }
            tentativePanel.add(casePanel);

            // Cases des indices
            JPanel indicePanel = new JPanel(new GridLayout(1, tailleCombinaison / 4));
            List<JPanel> casesIndices = new ArrayList<>();
            for(int i = 0; i <= tailleCombinaison / 4 + tailleCombinaison % 4; i++) {
                casesIndices.add(new JPanel(new GridLayout(2, 2)));
                casesIndices.get(i).setPreferredSize(new Dimension(biPlateauCaseCombinaison.getWidth() / coefficient, biPlateauCaseCombinaison.getHeight() / coefficient));
                indicePanel.add(casesIndices.get(i));
            }
            indicePanel.setSize(new Dimension(336, 482));

            casePanel.add(indicePanel, BorderLayout.EAST);

            for (int i = 0; i < tailleCombi + tailleCombi % 4; i++) {
                indices[j][i] = new JPanel();
                indices[j][i].setSize(new Dimension(indicePanel.getWidth() / 2, indicePanel.getHeight() / 2));
                changerIndice(indices[j][i], Indice.ABSENT);
                casesIndices.get(i / 4).add(indices[j][i]);
            }
        }
        // Liste de couleurs


        // Initialisation et ajout des boutons
        for (int i = 0; i < tailleCombi; i++) {
            buttons[i] = new JButton("Case " + (i + 1));
            // Réduisez les valeurs de largeur et de hauteur ici pour rendre les boutons plus petits
            buttons[i].setSize(new Dimension(336, 482)); // Taille réduite par exemple
            int caseIndex = i; // Indice de la case pour l'utilisation dans l'expression lambda
            buttons[i].addActionListener(e -> {
                try {
                    Couleur couleur = Couleur.valueOf(colorList.getSelectedValue());
                    jeu.changerCouleur(couleur, caseIndex);
                } catch (Exception ex) {
                    // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                    JOptionPane.showMessageDialog(AffichageFenetre.this,
                            "Une erreur est survenue : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
            buttonPanel.add(buttons[i]);
        }

        JButton btnProchaineTentative = new JButton("Valider Tentative");
        btnProchaineTentative.setSize(new Dimension(336, 482));
        btnProchaineTentative.addActionListener(e -> {
            try {
                if ((tentativeActuelle == nbTentatives - 1)) {
                    if (estRemplit(cases[tentativeActuelle])) {
                        jeu.passerProchaineTentative();
                        jeu.resumerPartie();
                    }
                } else {
                    if (estRemplit(cases[tentativeActuelle])) {
                        jeu.passerProchaineTentative();

                        tentativeActuelle++;
                        defaultColor(cases[tentativeActuelle]);
                    }
                }
            } catch (Exception ex) {
                // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                JOptionPane.showMessageDialog(AffichageFenetre.this,
                        "Une erreur est survenue : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        defaultColor(cases[0]);

        buttonPanel.add(btnProchaineTentative); // Ajouter la Box de plateau au centre de la Box de zone de plateau


        // Ajout de la liste de couleurs
        plateauBox.add(tentativePanel);


        // Ajout de l'InnerPanel à la Box de zone de plateau
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
        for (JPanel jPanel : aCase)
            changeColor(jPanel, Couleur.ABSENT);
    }


    public void updateCouleur(Couleur couleur, int index) {
        changeColor(cases[tentativeActuelle][index], couleur);
    }

    private void changerIndice(JPanel panel, Indice indice){
        for(Component component : panel.getComponents()){
            component.setVisible(false);
            panel.remove(component);
        }
        //panel.add(new JLabel(new ImageIcon(biPlateauCaseIndice.getScaledInstance(biPlateauCaseIndice.getWidth() / coefficient, biPlateauCaseIndice.getHeight() / coefficient, Image.SCALE_SMOOTH))));
        switch (indice){
            case BIEN_PLACE :
                panel.add(new JLabel(new ImageIcon(biIndiceBlanc.getScaledInstance(biIndiceBlanc.getWidth() / coefficient, biIndiceBlanc.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case MAL_PLACE :
                panel.add(new JLabel(new ImageIcon(biIndiceNoir.getScaledInstance(biIndiceNoir.getWidth() / coefficient, biIndiceNoir.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            default :
                panel.add(new JLabel(new ImageIcon(biIndiceAbsent.getScaledInstance(biIndiceAbsent.getWidth() / coefficient, biIndiceAbsent.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
        }
    }
    private void changeColor(JPanel panel, Couleur couleur) {
        for(Component component : panel.getComponents()) {
            component.setVisible(false);
            panel.remove(component);
        }
        //panel.add(new JLabel(new ImageIcon(biPlateauCaseCombinaison.getScaledInstance(biPlateauCaseCombinaison.getWidth() / coefficient, biPlateauCaseCombinaison.getHeight() / coefficient, Image.SCALE_SMOOTH))));
        switch (couleur) {
            case Couleur.BLEU :
                panel.add(new JLabel(new ImageIcon(biPionBleu.getScaledInstance(biPionBleu.getWidth() / coefficient, biPionBleu.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.JAUNE :
                panel.add(new JLabel(new ImageIcon(biPionJaune.getScaledInstance(biPionJaune.getWidth() / coefficient, biPionJaune.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.ROUGE :
                panel.add(new JLabel(new ImageIcon(biPionRouge.getScaledInstance(biPionRouge.getWidth() / coefficient, biPionRouge.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.VERT :
                panel.add(new JLabel(new ImageIcon(biPionVert.getScaledInstance(biPionVert.getWidth() / coefficient, biPionVert.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.ORANGE :
                panel.add(new JLabel(new ImageIcon(biPionOrange.getScaledInstance(biPionOrange.getWidth() / coefficient, biPionOrange.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.VIOLET :
                panel.add(new JLabel(new ImageIcon(biPionViolet.getScaledInstance(biPionViolet.getWidth() / coefficient, biPionViolet.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.CYAN :
                panel.add(new JLabel(new ImageIcon(biPionCyan.getScaledInstance(biPionCyan.getWidth() / coefficient, biPionCyan.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            case Couleur.ROSE :
                panel.add(new JLabel(new ImageIcon(biPionRose.getScaledInstance(biPionRose.getWidth() / coefficient, biPionRose.getHeight() / coefficient, Image.SCALE_SMOOTH))));
                break;
            default:
                panel.add(new JLabel(new ImageIcon(biPionAbsent.getScaledInstance(biPionAbsent.getWidth() / coefficient, biPionAbsent.getHeight() / coefficient, Image.SCALE_SMOOTH))));
        }
    }


    /**
     * Permet de changer la couleur dans une combinaison
     *
     * @param combinaison La combinaison à modifier
     */
    @Override
    public void changerCouleur(Combinaison combinaison) {
        for(int i = 0; i < combinaison.getTailleCombinaison(); i++){
            changeColor(cases[this.jeu.getTentativeActuelle()][i], combinaison.getCouleurs().get(i));
        }
    }

    /**
     * Affiche les indices passés en paramètres
     *
     * @param indices Les indices à afficher
     */
    @Override
    public void afficherIndices(LigneIndice indices) {
        ObservateurTentative.deciderMethodeAffichageIndices(this, mj, indices);
    }

    public void resumerManche(int score, List<Couleur> couleurs, boolean b) {
        getRootPane().getContentPane().removeAll();

        getContentPane().removeAll(); // Efface tous les composants de la fenêtre
        getContentPane().setLayout(new GridLayout(4, 0, 10, 10)); // Définit le gestionnaire de disposition

        JPanel[] combinaisonSecret = new JPanel[tailleCombi];
        String resume;
        if (b) {
            resume = "vous avez réussi a trouver";
        } else {
            resume = "vous n'avez malheureusement pas trouvé";
        }
        JLabel messageLabel = new JLabel(resume + " votre score et de " + score, SwingConstants.CENTER); // Crée un JLabel avec le message
        getContentPane().add(messageLabel);

        JLabel MessageFinal = new JLabel("Votre combinaison est : " + SwingConstants.SOUTH); // Crée un JLabel avec le message
        getContentPane().add(MessageFinal);

        JPanel caseCombinaison = new JPanel(new GridLayout(1, 6));

        for (int i = 0; i < tailleCombi; i++) {
            combinaisonSecret[i] = new JPanel();
            combinaisonSecret[i].setSize(new Dimension(100, 100));
            changeColor(combinaisonSecret[i], couleurs.get(i));
            caseCombinaison.add(combinaisonSecret[i]);
        }
        getContentPane().add(caseCombinaison); // Ajoute le JLabel à la fenêtre

        JButton btnProchaineManche = new JButton("Continuer");
        btnProchaineManche.setSize(new Dimension(50, 50));
        btnProchaineManche.addActionListener(e -> {
            try {
                jeu.mancheSuivante();
            } catch (Exception ex) {
                // Gestion de l'exception (affichage d'un message d'erreur, etc.)
                JOptionPane.showMessageDialog(AffichageFenetre.this,
                        "Une erreur est survenue : " + ex.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        getContentPane().add(btnProchaineManche); // Ajoute le JLabel à la fenêtre

        revalidate(); // Rafraîchit la fenêtre pour afficher les changements
        repaint(); // Repaint la fenêtre pour s'assurer que tout est bien affiché
    }
    private BufferedImage getImagePion(Couleur couleur){
        return switch (couleur) {
            case BLEU -> biPionBleu;
            case JAUNE -> biPionJaune;
            case ROUGE -> biPionRouge;
            case VERT -> biPionVert;
            case ORANGE -> biPionOrange;
            case VIOLET -> biPionViolet;
            case CYAN -> biPionCyan;
            case ROSE -> biPionRose;
            case null, default -> biPionAbsent;
        };
    }
    private BufferedImage getImageIndice(Indice indice){
        return switch (indice){
            case BIEN_PLACE -> biIndiceBlanc;
            case MAL_PLACE -> biIndiceNoir;
            default -> biIndiceAbsent;
        };
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
        for (int i = 0; i < indices.getTailleCombinaison(); i++) {
            System.out.println("i = " + i + "\nprochain emplacement : " + prochainIndexIndice(i));
            switch (indices.getIndices().get(i)) {
                case BIEN_PLACE -> changerIndice(this.indices[tentativeActuelle][prochainIndexIndice(i)], Indice.BIEN_PLACE);
                case MAL_PLACE -> changerIndice(this.indices[tentativeActuelle][prochainIndexIndice(i)], Indice.MAL_PLACE);
                default -> changerIndice(this.indices[tentativeActuelle][prochainIndexIndice(i)], Indice.ABSENT);
            }
        }
    }

    /**
     *
     */
    @Override
    public void afficherIndicesClassique(LigneIndice indices) {
        int index = 0;
        for (int i = 0; i < indices.getIntIndices()[0]; i++) {
            System.out.println("prochain emplacement : " + prochainIndexIndice(index));
            changerIndice(this.indices[tentativeActuelle][prochainIndexIndice(index)], Indice.BIEN_PLACE);
            index++;
        }
        for (int i = 0; i < indices.getIntIndices()[1]; i++) {
            System.out.println("prochain emplacement : " + prochainIndexIndice(index));
            changerIndice(this.indices[tentativeActuelle][prochainIndexIndice(index)], Indice.MAL_PLACE);
            index++;
        }
    }
    private int prochainIndexIndice(int i){
        return i;
        //return (i / ((tailleCombi + tailleCombi % 4) / 4)) % ((tailleCombi + tailleCombi % 4) / 2) * (tailleCombi + tailleCombi % 4) / 2 + i % 2;
    }

    /**
     *
     */
    @Override
    public void afficherIndicesNumerique(LigneIndice indices) {
        ;
    }
}