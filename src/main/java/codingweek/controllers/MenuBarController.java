package codingweek.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import codingweek.models.Game;
import codingweek.models.PageManager;

public class MenuBarController {

    private PageManager pageManager;
    private Game game;

    public void initialize() {
        pageManager = PageManager.getInstance();
        game = Game.getInstance();
    }

    @FXML
    private void Charger(ActionEvent event) {
        game.loadGame();
    }

    @FXML
    private void Sauvegarder(ActionEvent event) {
        game.saveGame();
    }

    @FXML
    private void RetourAcceuil(ActionEvent event) {
        pageManager.loadMenuWindowView();
        pageManager.closeSpyView();
    }

    @FXML
    private void AfficherStatistiques(ActionEvent event) {
        pageManager.closeSpyView();
        pageManager.loadStatsView();
    }
}
