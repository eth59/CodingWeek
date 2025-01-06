import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

package codingweek;



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
        launch();
    }
}
