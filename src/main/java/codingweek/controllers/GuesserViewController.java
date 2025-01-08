package codingweek.controllers;

import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class GuesserViewController implements codingweek.Observer {
    @FXML
    Button btnTurn;

    @FXML
    private GuesserBoardController guesserBoardController;

    @FXML
    private BorderPane rootPane;

    private Game game;

    public void initialize() {
        game = Game.getInstance();
        game.ajouterObservateur(this);
        btnTurn.setOnAction(e -> {
            turn();
        });
        btnTurn.setDisable(true);
        updateBackground();
    }

    public void reagir() {
        if (game.getNbCardsReturned() < 1) {
            btnTurn.setDisable(true);
        } else {
            btnTurn.setDisable(false);
        }
        updateBackground();
    }

    private void turn() {
        if (!game.isSpyTurn()) {
            game.changeTurn();
        }
    }

    private void updateBackground() {
        Boolean currentTeam = game.isBlueTurn();
        String backgroundColor;

        if (currentTeam) {
            backgroundColor = "#85C4FF";
        } else {
            backgroundColor = "#F6A2A7";
        }

        rootPane.setStyle("-fx-background-color: " + backgroundColor + ";");
    }

}
