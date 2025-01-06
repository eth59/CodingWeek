package codingweek.controllers;

import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GuesserViewController {
    @FXML
    Button btnTurn;

    private Game game;

    public void initialize() {
        game = Game.getInstance();
        btnTurn.setOnAction(e -> {
            turn();
        });
    }

    private void turn() {
        if (!game.isSpyTurn()) {
            game.changeTurn();
        }
    }
}
