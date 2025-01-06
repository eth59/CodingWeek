package codingweek.controllers;

import codingweek.Observer;
import codingweek.models.Game;
import codingweek.models.Guess;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TopGuesserController implements Observer {
    @FXML
    Label turnLabel, clueLabel, numberLabel;

    private Game game;

    public void initialize() {
        game = Game.getInstance();
        game.ajouterObservateur(this);
    }

    public void reagir() {
        if (game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion bleu");
        } else if (game.isSpyTurn() && !game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion rouge");
        } else if (!game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour du devin bleu");
        } else {
            turnLabel.setText("C'est au tour du devin rouge");
        }

        Guess lastGuess = game.getLastGuess();
        if (lastGuess != null) {
            clueLabel.setText(lastGuess.getClue());
            numberLabel.setText(Integer.toString(lastGuess.getNbWords()));
        }
    }
}
