package application;

import classes.De;
import classes.Partie;
import classes.Tour;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JeuController implements Initializable {

    private static Partie partie;               // Partie en cours
    private Tour tour;                          // Tour en cours
    private De[] des = new De[3];               // liste des dés que le joueur possède dans sa main
    private static boolean debutTour = true;    // si c'est le début du tour du joueur
    private static int nbTours = 0;             // nombre de tours joués
    private static int numJoueur = 0;           // numéro du joueur qui joue
    private String[] affFaces = new String[3];  // liste des faces obtenues lorsque le joueur lance ses dés
    ScoreController scoreController = new ScoreController();
    Utiles utile = new Utiles();

    /*
     * Objets graphiques
     */
    @FXML
    private Button jouerBouton;
    @FXML
    private Button reglesBouton;
    @FXML
    private Button piocherBouton;
    @FXML
    private Button lancerBouton;
    @FXML
    private Button finirTourBouton;
    @FXML
    private Button finirPartieBouton;

    @FXML
    private Label joueurLabel;
    @FXML
    private Label nbCerveauxJoueurLabel;
    @FXML
    private Label couleurDe1;
    @FXML
    private Label couleurDe2;
    @FXML
    private Label couleurDe3;
    @FXML
    private Label resultatDe1;
    @FXML
    private Label resultatDe2;
    @FXML
    private Label resultatDe3;
    @FXML
    private Label nbCerveauxTour;
    @FXML
    private Label nbFusilsTour;
    @FXML
    private Label infosLabel;

    @FXML
    private Text facesDe1;
    @FXML
    private Text facesDe2;
    @FXML
    private Text facesDe3;

    @FXML
    private Rectangle rectangle;

    /*
     * Méthodes de gestion des événements
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Règles"
         */
        reglesBouton.setOnAction(event -> {
            utile.ouvrirFenetre("regles-view.fxml", "Zombie Dice - Règles");
        });

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Jouer"
         */
        jouerBouton.setOnAction(actionEvent -> {
            setTour();
            /*
             * Suppresion du bouton "Jouer" et du rectangle noir
             */
            jouerBouton.setVisible(false);
            rectangle.setVisible(false);

            /*
             * Affichage des informations du joueur :
             * - son nom
             * - son nombre de cerveaux
             */
            joueurLabel.setText(partie.getJoueur(numJoueur).toString());
            nbCerveauxJoueurLabel.setText(String.valueOf(partie.getJoueur(numJoueur).getNbCerveaux()));

            /*
             * Mise à zero du nombre de cerveaux et de fusils du joueur obtenus pendant le tour
             */
            tour.zeroCerveau();
            tour.zeroFusil();
        });

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Piocher"
         */
        piocherBouton.setOnAction(actionEvent -> {
            /*
             * Si le joueur peut, il pioche
             */
            if (partie.getJoueur(numJoueur).getPeutPiocher()) {
                // Effacer les informations sur ce qu'il doit faire
                infosLabel.setText("");

                /* Le joueur pioche les dés
                 * - si il manque un dé dans sa main, il en pioche un nouveau
                 * - assignation du dé pioché au bon emplacement dans sa main
                 */
                des = tour.piocherDes();
                for (int i = 0; i < 3; i++) {
                    if (des[i] == null) {
                        des[i] = partie.getJoueur(numJoueur).piocherDe(partie.getGobelet());
                    }
                    switch (i) {
                        case 0: {
                            couleurDe1.setText(des[i].getCouleur());
                            facesDe1.setText(String.valueOf(des[i].getListeFaces()));
                            break;
                        }
                        case 1: {
                            couleurDe2.setText(des[i].getCouleur());
                            facesDe2.setText(String.valueOf(des[i].getListeFaces()));
                            break;
                        }
                        case 2: {
                            couleurDe3.setText(des[i].getCouleur());
                            facesDe3.setText(String.valueOf(des[i].getListeFaces()));
                            break;
                        }
                    }
                }
            }
            /*
             * Sinon un message est affiché
             */
            else {
                infosLabel.setText("Veuillez finir votre tour");
            }
        });

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Lancer"
         */
        lancerBouton.setOnAction(actionEvent -> {
            /*
             * Si le joueur peut, il lance ses dés
             */
            if (partie.getJoueur(numJoueur).getPeutLancer()) {
                infosLabel.setText("");

                /*
                 * Le joueur lance ses 3 dés
                 */
                for (int i = 0; i < 3; i++) {
                    String face = tour.lancerDe(des[i]);
                    /*
                     * Traitement différent en fonction de la face obtenue
                     */
                    switch (face) {
                        case "Cerveau": {
                            tour.ajouterCerveau();  // Le compteur de cerveau du tour est incrémenté de 1
                            des[i] = null;          // Le dé est retiré de la main du joueur
                            affFaces[i] = face;     // La face est ajoutée dans la liste des faces
                            break;
                        }
                        case "Fusil": {
                            tour.ajouterFusil();    // Le compteur de fusil du tour est incrémenté de 1
                            des[i] = null;          // Le dé est retiré de la main du joueur
                            affFaces[i] = face;     // La face est ajoutée dans la liste des faces
                            break;
                        }
                        case "Empreintes": {
                            affFaces[i] = face;     // La face est ajoutée dans la liste des faces
                            break;
                        }
                    }
                }

                /*
                 * Affichage des différentes faces obtenues
                 */
                resultatDe1.setText(affFaces[0]);
                resultatDe2.setText(affFaces[1]);
                resultatDe3.setText(affFaces[2]);

                /*
                 * Affichage ou mise à jour du nombre de cerveau et de fusils obtenus pendant le tour
                 */
                nbCerveauxTour.setText(String.valueOf(tour.getNbCerveaux()));
                nbFusilsTour.setText(String.valueOf(tour.getNbFusils()));

                infosLabel.setText(tour.verifTour());
            }
            /*
             * Sinon un message est affiché
             */
            else {
                infosLabel.setText("Veuillez piocher des dés ou finir votre tour");
            }
        });

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Finir mon tour"
         */
        finirTourBouton.setOnAction(actionEvent -> {
            /*
             * Si le joueur peut, il finit son tour
             */
            if (partie.getJoueur(numJoueur).getPeutFinirTour()) {
                tour.arreterTour();

                /*
                 * Si la fin de la partie a été annoncée (un joueur a obtenu 13 cerveaux ou plus)
                 */
                if (partie.getFinPartie()) {
                    /*
                     * Si le joueur qui finit son tour est le dernier de la liste,
                     * la partie se finit et l'écran des scores est affiché
                     */
                    if (partie.getJoueur(numJoueur).equals(partie.getJoueur(partie.getListeJoueurs().size() - 1))) {
                        utile.ouvrirFenetre("scores-view.fxml", "Zombie Dice - Scores");
                        scoreController.setPartie(partie);
                        Stage stage;
                        stage = (Stage) finirTourBouton.getScene().getWindow();
                        stage.close();
                    }
                    /*
                     * Sinon, c'est au tour du joueur suivant
                     */
                    else {
                        joueurSuivant();
                    }
                }
                /*
                 * Sinon, le joueur suivant joue
                 */
                else {
                    joueurSuivant();
                }
            }
            /*
             * Sinon un message est affiché
             */
            else {
                infosLabel.setText("Veuillez lancer les dés avant de finir votre tour");
            }
        });

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Finir la partie"
         */
        finirPartieBouton.setOnAction(actionEvent -> {
            /*
             * Si la partie a été commencée, elle peut se finir
             */
            if (partie != null) {
                tour.arreterTour(); // le tour actuel est fini
                utile.ouvrirFenetre("scores-view.fxml", "Zombie Dice - Scores");
                scoreController.setPartie(partie);
                Stage stage;
                stage = (Stage) finirPartieBouton.getScene().getWindow();
                stage.close();
            }
            /*
             * Sinon un message est affiché
             */
            else {
                infosLabel.setText("Veuillez commencer jouer au moins un tour avant de finir la partie");
            }
        });
    }

    /*
     * Méthode qui initialise une partie
     */
    public void initPartie(Partie unePartie) {
        partie = unePartie;
    }

    /*
     * Méthode qui récupère le numéro du joueur actuel
     */
    public int getNumJoueur() {
        return numJoueur;
    }

    /*
     * Méthode qui change le numéro du joueur actuel
     */
    public void setNumJoueur(int num) {
        numJoueur = num;
    }

    /*
     * Méthode qui passe au tour du joueur suivant
     */
    public void joueurSuivant() {
        setNumJoueur(++nbTours % partie.getNbJoueurs());    // on incrémente le numéro du joueur actuel

        /*
         * Mise à jour des autorisations
         */
        partie.getJoueur(numJoueur).setPeutPiocher(true);
        partie.getJoueur(numJoueur).setPeutLancer(false);
        partie.getJoueur(numJoueur).setPeutFinirTour(true);

        /*
         * Ouvre une nouvelle fenêtre de jeu pour le joueur suivant
         * Puis ferme la fenêtre actuelle
         */
        utile.ouvrirFenetre("jeu-view.fxml", "Zombie Dice - Jeu");
        Stage stage;

        stage = (Stage) finirTourBouton.getScene().getWindow();
        stage.close();
    }

    /*
     * Méthode qui permet d'initialiser le tour du joueur
     */
    public void setTour() {
        tour = new Tour(partie, partie.getJoueur(numJoueur), partie.getGobelet());
    }
}
