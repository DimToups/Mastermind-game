package src.view;

import src.controller.GestionnaireJeu;
import src.model.Combinaison;
import src.model.Joueur;
import src.model.LigneIndice;
import src.model.enums.ModeJeu;
import src.model.userInterfaces.ObservateurUI;

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

    /**
     * Crée une instance d'AffichageFenetre
     *
     * @param jeu Le contrôleur du jeu
     */
    public AffichageFenetre(GestionnaireJeu jeu) {
        super("Mastermind");
        this.jeu = jeu;

        // Initialisation des paramètres de la page
        setSize(1080,720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(1,1));
    }

    /**
     * Affiche le plateau de jeu
     */
    @Override
    public void miseEnPlacePlateau() {
        for(Component component : getContentPane().getComponents()){
            component.setVisible(false);
            remove(component);
        }
        setLayout(new GridLayout(1,2));
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
        setLayout(new GridLayout(6,1));

        // Attribution des actions des composants de base
        this.jbFinInitialisation.addActionListener(e -> {
            if(initialisationRemplie()){
                // Mise à jour de la partie
                jeu.miseAJourJoueur(new Joueur(jtfCreationJoueur.getText().strip()));
                for(JRadioButton radioButton : jrbListNbManches)
                    if(radioButton.isSelected())
                        jeu.miseAJourNbManches(Integer.parseInt(radioButton.getText()));
                for(JRadioButton radioButton : jrbListNbTentatives)
                    if(radioButton.isSelected())
                        jeu.miseAJourNbTentatives(Integer.parseInt(radioButton.getText()));
                for(JRadioButton radioButton : jrbListTailleCombi)
                    if(radioButton.isSelected())
                        jeu.miseAJourTailleCombinaison(Integer.parseInt(radioButton.getText()));
                switch (jcbModeJeu.getSelectedIndex()){
                    case 0 : jeu.miseAJourModeJeu(ModeJeu.FACILE); break;
                    case 1 : jeu.miseAJourModeJeu(ModeJeu.CLASSIQUE); break;
                    case 2 : jeu.miseAJourModeJeu(ModeJeu.NUMERIQUE); break;
                }
                jeu.demarrerProchaineManche();
            }
            else{
                //
                System.out.println("Toutes les informations nécessaires pour commencer la partie n'ont pas été renseignées");
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
        for(int i = 1; i < 6; i++)
            jrbListNbManches.add(new JRadioButton(String.valueOf(i)));
        for(JRadioButton radioButton : jrbListNbManches) {
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
        for(int i = 2; i < 7; i++)
            jrbListTailleCombi.add(new JRadioButton(String.valueOf(i)));
        for(JRadioButton radioButton : jrbListTailleCombi) {
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
        for(int i = 2; i < 13; i++)
            jrbListNbTentatives.add(new JRadioButton(String.valueOf(i)));
        for(JRadioButton radioButton : jrbListNbTentatives) {
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
    private boolean initialisationRemplie(){
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
