package codingweek.controllers;

import codingweek.Observer;
import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TopSpyController implements Observer {
    @FXML
    Label turnLabel;

    @FXML
    TextField clueField, numberField;    

    private Game game;

    public void initialize() {
        game = Game.getInstance();
        if (game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion bleu");
        } else if (game.isSpyTurn() && !game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion rouge");
        } else if (!game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour du devin bleu");
        } else {
            turnLabel.setText("C'est au tour du devin rouge");
        }
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
    }

    public void submitClue() {
        try {
            int number = Integer.parseInt(numberField.getText());
            if (game.submitClue(clueField.getText(), number) == 1) {
                clueField.clear();
                numberField.clear();
            } else {
                // L'indice n'est pas valide   
            }
        } catch (NumberFormatException e) {
            // Gérer l'erreur de conversion en integer
            numberField.clear();
            numberField.setPromptText("Veuillez entrer un nombre valide");
        }
    }
}
