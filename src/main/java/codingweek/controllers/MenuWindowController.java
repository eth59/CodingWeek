package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;


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
        try {
            // 2) Si Desktop non supporté ou browse non disponible,
            //    on tente manuellement selon l'OS.
            String osName = System.getProperty("os.name").toLowerCase();
            System.out.println(osName);

            if (osName.contains("mac")) {
                // Sur MacOS, la commande 'open' lance le navigateur par défaut
                Runtime.getRuntime().exec("open " + url);
            }
            else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
                // Sur Linux, la commande 'xdg-open' lance le navigateur par défaut
                Runtime.getRuntime().exec("xdg-open " + url);
            }
            else if (osName.contains("win")) {
                // Sur Windows, la commande 'rundll32' ou 'start'
                // peut être utilisée, mais en général Desktop devrait marcher
                // Si besoin, on peut utiliser :
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            }
            else {
                System.err.println("Système non supporté pour l'ouverture de lien : " + osName);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

}