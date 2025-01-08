package codingweek;
import javafx.application.Application;
import javafx.stage.Stage;
import codingweek.models.PageManager;
import codingweek.models.Game;
 javafx.application.Application;
import javafx.stage.Stage;
import codingweek.models.PageManager;
import codingweek.models.Game;
// C'C'eC'eC'C'eC'esC'C'eC'esC'C'eC'eC'C'eC'esC'C'eC'estC'C'eC'eC'C'eC'esC'C'eC'est llllelle mmmmammammmmammaimmmmammaimmmmammammmmammaimmmmammainmmmmammammmmammaimmmmammain
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        PageManager pageManager = PageManager.getInstance();
        // Load and display the Menu Window View
        pageManager.setPrimaryStage(primaryStage);
        pageManager.loadMenuWindowView();
        // Load and display the Guesser View
        // loadGuesserView(primaryStage);
        // Load and display the Spy View in a new stage
        // loadSpyView(primaryStage);
    }

    public static void main(String[] args) {
        Game.getInstance(); // Initialize the game
        launch();
    }
}
