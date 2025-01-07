package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import codingweek.models.PageManager;


public class GameOverController {
    @FXML
    private ImageView imageCenter;

    @FXML
    private Button accueilButton;

    private PageManager pageManager;

    public GameOverController() {
        pageManager = PageManager.getInstance();
    }

    @FXML
    public void initialize() {
        Image img = new Image(getClass().getResourceAsStream("/figures/GameOVer.png"));
        imageCenter.setImage(img);

        // Gérer l'action sur le bouton Accueil
        accueilButton.setOnAction(e -> {
            System.out.println("Bouton Accueil cliqué !");
            pageManager.loadMenuWindowView();
        });
    }
}
