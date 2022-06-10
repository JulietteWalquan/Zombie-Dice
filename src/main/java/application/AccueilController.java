package application;

import classes.Partie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {

    private JeuController jeuController; // pour initialiser la partie
    Utiles utile = new Utiles();

    /*
     * Objets graphiques
     */
    @FXML
    private Button jouerBouton;
    @FXML
    private Button quitterBouton;
    @FXML
    private Button reglesBouton;

    @FXML
    private TextField nbJoueurs;

    @FXML
    private RadioButton diffFacile;
    @FXML
    private RadioButton diffMoyen;
    @FXML
    private RadioButton diffDifficile;


    /*
     * Méthodes de gestion des événements
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Jouer"
         */
        jouerBouton.setOnAction(event -> {

            /* Une fenêtre d'erreur est affichée si :
             * - le nombre de joueurs n'est pas renseigné
             * - le nombre de joueurs n'est pas un entier
             * - la difficulté n'est pas sélectionnée
             */
            if (nbJoueurs.getText().isEmpty() || !nbJoueurs.getText().chars().allMatch(Character::isDigit) || !diffFacile.isSelected() && !diffMoyen.isSelected() && !diffDifficile.isSelected()) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Veuillez renseigner un nombre de joueurs et une difficulté.", ButtonType.OK);
                alert.showAndWait();
            }

            /*
             * Sinon on initialise la partie, affiche la fenêtre de jeu et ferme la fenêtre d'accueil
             */
            else {
                // Initialisation des paramètres de la partie de jeu
                Partie partie;
                if (diffFacile.isSelected()) {
                    partie = new Partie(Integer.parseInt(nbJoueurs.getText()), "Facile");
                }
                else if (diffMoyen.isSelected()) {
                    partie = new Partie(Integer.parseInt(nbJoueurs.getText()), "Moyen");
                }
                else {
                    partie = new Partie(Integer.parseInt(nbJoueurs.getText()), "Difficile");
                }

                // Affichage de la fenêtre de jeu et fermeture de la fenêtre d'accueil
                utile.ouvrirFenetre("jeu-view.fxml", "Zombie Dice - Jeu");
                jeuController = new JeuController();
                jeuController.initPartie(partie);
                Stage stage;
                stage = (Stage) jouerBouton.getScene().getWindow();
                stage.close();
            }
        }
        );

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Quitter"
         */
        quitterBouton.setOnAction(event -> {
            // Ferme la fenêtre d'accueil
            Stage stage = (Stage) quitterBouton.getScene().getWindow();
            stage.close();
        });

        /*
         * Action effectuée lorsque l'utilisateur clique sur le bouton "Règles"
         */
        reglesBouton.setOnAction(event -> {
            utile.ouvrirFenetre("regles-view.fxml", "Zombie Dice - Règles");
        });
    }
}
