package codingweek.controllers;

import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GuesserViewController implements codingweek.Observer {
    @FXML
    Button btnTurn;

    @FXML
    private GuesserBoardController guesserBoardController;

    private Game game;

    public void initialize() {
        game = Game.getInstance();
        game.ajouterObservateur(this);
        btnTurn.setOnAction(e -> {
            turn();
        });
        btnTurn.setDisable(true);
    }

    public void reagir() {
        if (game.getNbCardsReturned() < 1) {
            btnTurn.setDisable(true);
        } else {
            btnTurn.setDisable(false);
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
