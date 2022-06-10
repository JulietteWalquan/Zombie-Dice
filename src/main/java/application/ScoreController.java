package application;

import classes.Partie;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreController implements Initializable {
    // TODO revoir le classement

    private static Partie partie;               // Partie en cours
    private static boolean affichage = false;   // Si le classement est affiché ou non

    /*
     * Objets graphiques
     */
    @FXML
    private Button affClassement;
    @FXML
    private Button nouvellePartieBouton;

    @FXML
    private ListView affClassementJoueurs;
    @FXML
    private ListView affClassementScores;

    /*
     * Méthodes de gestion des événements
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {

        /*
         * Action effectuée lorsque le joueur clique sur le bouton "Afficher le classement"
         */
        affClassement.setOnAction(event -> {
            /*
             * Si le classement ne l'est pas déjà, il est affiché
             */
            if (!affichage) {
                partie.faireClassement();   // Constitution du classement
                /*
                 * Pour chaque joueur, son nom et son score sont affichés
                 */
                partie.getClassement().forEach(joueur -> {
                    affClassementJoueurs.getItems().add(joueur.toString());
                    affClassementScores.getItems().add(joueur.getNbCerveaux());
                });
                affichage = true;
            }
        });

        /*
         * Action effectuée lorsque le joueur clique sur le bouton "Nouvelle partie"
         */
        nouvellePartieBouton.setOnAction(event -> {
            /*
             * La fenêtre des scores est fermée
             */
            Stage stage = (Stage) nouvellePartieBouton.getScene().getWindow();
            stage.close();

            /*
             * L'application est relancée
             */
            MainApp mainApp = new MainApp();
            try {
                mainApp.start(new Stage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /*
     * Méthode qui permet de récupérer la partie en cours
     */
    public void setPartie(Partie unePartie) {
        partie = unePartie;
    }
}
