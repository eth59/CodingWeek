package codingweek.models;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageManager {

    private Stage primaryStage, spyStage;
    private static PageManager instance;

    private PageManager() {
        primaryStage = new Stage();
    }
    
    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    public void loadGuesserView() {
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

    public void loadSpyView() {
        try {
            URL spyViewURL = getClass().getResource("/spyView.fxml");
            if (spyViewURL == null) {
                System.err.println("Could not find spyView.fxml");
                System.exit(1);
            }
            Parent spyView = FXMLLoader.load(spyViewURL);
            Scene spyScene = new Scene(spyView, 800, 600);

            // Create a new stage for the Spy View
            spyStage = new Stage();
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

    public void loadMenuWindowView() {
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

    public void closeSpyView() {
        spyStage.close();
    }

    public void setPrimaryStage(Stage primaryStage2) {
        this.primaryStage = primaryStage2;
    }
}
