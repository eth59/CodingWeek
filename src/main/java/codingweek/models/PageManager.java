package codingweek.models;

import java.io.IOException;
import java.net.URL;

import codingweek.controllers.GuesserBoardController;
import codingweek.controllers.SpyBoardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageManager {

    private Stage primaryStage, spyStage;
    private static PageManager instance;
    private SpyBoardController spyBoardController;
    private GuesserBoardController guesserBoardController;

    private PageManager() {
        primaryStage = new Stage();
    }

    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
            Game.getInstance().setPageManager();
        }
        return instance;
    }

    public void loadGuesserView() {
        try {
            URL guesserViewURL = getClass().getResource("/guesserView.fxml");
            if (guesserViewURL == null) {
                throw new IOException("Could not find guesserView.fxml");
            }
            FXMLLoader loader = new FXMLLoader(guesserViewURL);
            Parent guesserView = loader.load();
            Scene guesserScene = new Scene(guesserView, 800, 600);

            primaryStage.setScene(guesserScene);
            primaryStage.setTitle("Guesser Window");

            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setX(100);
            primaryStage.setY(100);

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load guesserView.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadSpyView() {
        try {
            URL spyViewURL = getClass().getResource("/spyView.fxml");
            if (spyViewURL == null) {
                throw new IOException("Could not find spyView.fxml");
            }
            FXMLLoader loader = new FXMLLoader(spyViewURL);
            Parent spyView = loader.load();
            Scene spyScene = new Scene(spyView, 800, 600);

            spyStage = new Stage();
            spyStage.setScene(spyScene);
            spyStage.setTitle("Spy Window");

            spyStage.setWidth(800);
            spyStage.setHeight(600);
            spyStage.setX(primaryStage.getX() + primaryStage.getWidth() + 20);
            spyStage.setY(primaryStage.getY());

            spyStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load spyView.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadMenuWindowView() {
        try {
            URL menuViewURL = getClass().getResource("/menuWindow.fxml");
            if (menuViewURL == null) {
                throw new IOException("Could not find menuWindow.fxml");
            }
            FXMLLoader loader = new FXMLLoader(menuViewURL);
            Parent menuView = loader.load();
            Scene menuScene = new Scene(menuView, 800, 600);
            primaryStage.setScene(menuScene);
            primaryStage.setTitle("Menu Window");

            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setX(100);
            primaryStage.setY(100);

            primaryStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load menuWindow.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadConfigWindowView(){
        try {
            closeSpyView();
            URL configViewURL = getClass().getResource("/configWindow.fxml");
            if (configViewURL == null){
                System.err.println("Could not find configWindow.fxml");
                System.exit(1);
            }
            Parent configView = FXMLLoader.load(configViewURL);
            Scene configScene = new Scene(configView, 490, 250);
            primaryStage.setScene(configScene);
            primaryStage.setTitle("Configuration Window");
            // Set position and size explicitly
            primaryStage.setWidth(490);
            primaryStage.setHeight(250);
            primaryStage.setX(100);
            primaryStage.setY(100);
            primaryStage.show();
        } catch (IOException e){
            System.err.println("Failed to load configWindow.fxml: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void loadGameOverView(){
        try {
            closeSpyView();
            loadGameOverSpyView();
            URL gameOverViewURL = getClass().getResource("/gameOverView.fxml");
            if (gameOverViewURL == null){
                System.err.println("Could not find gameOverView.fxml");
                System.exit(1);
            }
            Parent gameOverView = FXMLLoader.load(gameOverViewURL);
            Scene gameOverScene = new Scene(gameOverView, 800, 600);
            primaryStage.setScene(gameOverScene);
            primaryStage.setTitle("Guesser Window");
            // Set position and size explicitly
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.setX(100);
            primaryStage.setY(100);
            primaryStage.show();
        } catch (IOException e){
            System.err.println("Failed to load gameOverWindow.fxml: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void loadGameOverSpyView() {
        try {
            URL spyViewURL = getClass().getResource("/gameOverView.fxml");
            if (spyViewURL == null) {
                throw new IOException("Could not find gameOverView.fxml");
            }
            FXMLLoader loader = new FXMLLoader(spyViewURL);
            Parent spyView = loader.load();
            Scene spyScene = new Scene(spyView, 800, 600);

            spyStage = new Stage();
            spyStage.setScene(spyScene);
            spyStage.setTitle("Spy Window");

            spyStage.setWidth(800);
            spyStage.setHeight(600);
            spyStage.setX(primaryStage.getX() + primaryStage.getWidth() + 20);
            spyStage.setY(primaryStage.getY());

            spyStage.show();
        } catch (IOException e) {
            System.err.println("Failed to load spyView.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void closeSpyView() {
        if (spyStage != null) {
            spyStage.close();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public SpyBoardController getSpyBoardController() {
        return spyBoardController;
    }

    public GuesserBoardController getGuesserBoardController() {
        return guesserBoardController;
    }
}
