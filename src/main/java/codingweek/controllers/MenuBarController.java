package codingweek.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import codingweek.models.GameSave;
import codingweek.models.PageManager;

public class MenuBarController {

    private PageManager pageManager;
    private GameSave gamesave;

    public void initialize() {
        pageManager = PageManager.getInstance();
        gamesave = GameSave.getInstance();
    }

    @FXML
    private void Charger(ActionEvent event) {
        gamesave.loadGame();
    }

    @FXML
    private void Sauvegarder(ActionEvent event) {
        gamesave.saveGame();
    }

    @FXML
    private void RetourAcceuil(ActionEvent event) {
        pageManager.loadMenuWindowView();
        pageManager.closeSpyView();
    }
}