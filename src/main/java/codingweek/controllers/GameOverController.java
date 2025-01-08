package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import codingweek.models.Game;
import codingweek.models.PageManager;


public class GameOverController {
    @FXML
    private ImageView imageCenter;

    @FXML
    private Button accueilButton;

    private PageManager pageManager;
    private Game game;

    public GameOverController() {
        pageManager = PageManager.getInstance();
        game = Game.getInstance();
    }

    @FXML
    public void initialize() {
        Image img;
        if (game.isBlueTurn()) {
            img = new Image(getClass().getResourceAsStream("/Images/GameOverBleu.png"));
        } else {
            img = new Image(getClass().getResourceAsStream("/Images/GameOverRouge.png"));
        }
        imageCenter.setImage(img);

        // Gérer l'action sur le bouton Accueil
        accueilButton.setOnAction(e -> {
            System.out.println("Bouton Accueil cliqué !");
            pageManager.loadMenuWindowView();
        });
    }
}
