package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import codingweek.models.PageManager;

public class MenuWindowController {

    // Attributes
    @FXML
    private Button playButton;
    @FXML
    private Button rulesButton;
    @FXML
    private Button quitButton;

    private PageManager pageManager;

    @FXML
    private void initialize() {
        pageManager = PageManager.getInstance();
        // Cette méthode est appelée automatiquement après le chargement du FXML

        playButton.setOnAction(e -> {
            System.out.println("Le bouton Play a été cliqué !");
            pageManager.loadGuesserView();
            pageManager.loadSpyView();
        });

        rulesButton.setOnAction(e -> {
            System.out.println("Le bouton Règles a été cliqué !");
            String url = "https://iello.fr/wp-content/uploads/2016/10/Codenames_rulebook_FR-web_2018.pdf";
            openWebpage(url);
        });

        quitButton.setOnAction(e -> {
            System.out.println("Le bouton Quit a été cliqué !");
            // Logique pour quitter l'application
            System.exit(0);
        });
    }

    private void openWebpage(String url) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Desktop non supporté. Impossible d'ouvrir le lien : " + url);
        }
    }

    

}