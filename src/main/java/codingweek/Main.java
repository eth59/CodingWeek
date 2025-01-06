package codingweek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Charger la première vue (GuesserView)
        URL guesserViewURL = getClass().getResource("/GuesserView.fxml");
        if (guesserViewURL == null) {
            System.err.println("Could not find GuesserView.fxml");
            System.exit(1);
        }
        Parent guesserView = FXMLLoader.load(guesserViewURL);
        Scene guesserScene = new Scene(guesserView, 800, 600);
        primaryStage.setScene(guesserScene);
        primaryStage.setTitle("Guesser Window");
        primaryStage.show();

        // Charger la deuxième vue (SpyView) dans une nouvelle fenêtre
        URL spyViewURL = getClass().getResource("/SpyView.fxml");
        if (spyViewURL == null) {
            System.err.println("Could not find SpyView.fxml");
            System.exit(1);
        }
        Parent spyView = FXMLLoader.load(spyViewURL);
        Scene spyScene = new Scene(spyView, 800, 600);

        // Créer une nouvelle fenêtre (Stage) pour SpyView
        Stage spyStage = new Stage();
        spyStage.setScene(spyScene);
        spyStage.setTitle("Spy Window");
        spyStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
