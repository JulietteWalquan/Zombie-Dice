package application;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    Utiles utile = new Utiles();

    @Override
    public void start(Stage stage) throws IOException {
        utile.ouvrirFenetre("accueil-view.fxml", "Zombie Dice - Accueil");
    }

    public static void main(String[] args) {
        launch();
    }
}