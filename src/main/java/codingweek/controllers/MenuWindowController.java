package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuWindowController {

    // Attributes
    @FXML
    private Button playButton;
    @FXML
    private Button rulesButton;
    @FXML
    private Button quitButton;

    @FXML
    private void initialize() {
        // Cette méthode est appelée automatiquement après le chargement du FXML

        playButton.setOnAction(e -> {
            System.out.println("Le bouton Play a été cliqué !");
            // Logique pour lancer le jeu
        });

        rulesButton.setOnAction(e -> {
            System.out.println("Le bouton Règles a été cliqué !");
            // Logique pour afficher les règles
        });

        quitButton.setOnAction(e -> {
            System.out.println("Le bouton Quit a été cliqué !");
            // Logique pour quitter l'application
            System.exit(0);
        });
    }

}
