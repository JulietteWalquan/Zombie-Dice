package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/*
 * Classe contenant des fonctions utilisées plusieurs fois à travers l'application
 */
public class Utiles {

    /*
     * Méthode pour afficher une nouvelle fenêtre en renseignant le chemin du fichier FXML et son titre
     */
    public void ouvrirFenetre(String vue, String titre) {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(vue));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(titre);
        stage.show();
    }
}
