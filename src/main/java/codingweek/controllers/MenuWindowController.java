package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

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
            Stage primaryStage = (Stage) playButton.getScene().getWindow();
            loadGuesserView(primaryStage);
            loadSpyView(primaryStage);
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

    private void loadGuesserView(Stage primaryStage) {
        try {
            URL guesserViewURL = getClass().getResource("/guesserView.fxml");
            if (guesserViewURL == null) {
                System.err.println("Could not find guesserView.fxml");
                System.exit(1);
            }
            Parent guesserView = FXMLLoader.load(guesserViewURL);
            Scene guesserScene = new Scene(guesserView, 800, 600);
            primaryStage.setScene(guesserScene);
            primaryStage.setTitle("Guesser Window");

            // Set position and size explicitly
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setX(100);
            primaryStage.setY(100);

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load guesserView.fxml: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void loadSpyView(Stage primaryStage) {
        try {
            URL spyViewURL = getClass().getResource("/spyView.fxml");
            if (spyViewURL == null) {
                System.err.println("Could not find spyView.fxml");
                System.exit(1);
            }
            Parent spyView = FXMLLoader.load(spyViewURL);
            Scene spyScene = new Scene(spyView, 800, 600);

            // Create a new stage for the Spy View
            Stage spyStage = new Stage();
            spyStage.setScene(spyScene);
            spyStage.setTitle("Spy Window");

            // Set position and size explicitly
            spyStage.setWidth(800);
            spyStage.setHeight(600);
            spyStage.setX(primaryStage.getX() + primaryStage.getWidth() + 20); // 20px gap
            spyStage.setY(primaryStage.getY()); // Align vertically with Guesser

            spyStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load spyView.fxml: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

//    private void openWebpage(String url) {
//        if (Desktop.isDesktopSupported()) {
//            try {
//                Desktop.getDesktop().browse(new URI(url));
//            } catch (IOException | URISyntaxException e) {
//                e.printStackTrace();
//            }
//        } else {
//            System.out.println("Desktop non supporté. Impossible d'ouvrir le lien : " + url);
//        }
//    }

    @SuppressWarnings("deprecation")
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
