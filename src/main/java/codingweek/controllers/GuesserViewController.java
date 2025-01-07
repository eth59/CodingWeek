package codingweek.controllers;

import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GuesserViewController {
    @FXML
    Button btnTurn;

    @FXML
    private GuesserBoardController guesserBoardController;

    private Game game;

    public void initialize() {
        game = Game.getInstance();
        btnTurn.setOnAction(e -> {
            turn();
        });
        System.out.println("GuesserViewController initialized.");
        if (guesserBoardController != null) {
            System.out.println("GuesserBoardController successfully linked.");
        }
    }

    private void turn() {
        if (!game.isSpyTurn()) {
            game.changeTurn();
        }
    }

    // Expose methods to interact with GuesserBoardController if needed
    public GuesserBoardController getGuesserBoardController() {
        return guesserBoardController;
    }
}
