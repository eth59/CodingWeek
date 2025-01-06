package codingweek;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

import codingweek.models.Game;



public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Load and display the Menu Window View
        loadMenuWindowView(primaryStage);
        // Load and display the Guesser View
        // loadGuesserView(primaryStage);
        // Load and display the Spy View in a new stage
        // loadSpyView(primaryStage);
    }

    public void loadMenuWindowView(Stage primaryStage) {
        try {
            URL menuViewURL = getClass().getResource("/menuWindow.fxml");
            if (menuViewURL == null) {
                System.err.println("Could not find menuWindow.fxml");
                System.exit(1);
            }
            Parent menuView = FXMLLoader.load(menuViewURL);
            Scene menuScene = new Scene(menuView, 800, 600);
            primaryStage.setScene(menuScene);
            primaryStage.setTitle("Menu Window");
            // Set position and size explicitly
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setX(100);
            primaryStage.setY(100);
            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load menuWindow.fxml: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        Game game = Game.getInstance(); // Initialize the game
        launch();
    }
}