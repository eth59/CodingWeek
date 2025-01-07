package codingweek.controllers;

import codingweek.Observer;
import codingweek.models.Game;
import codingweek.models.Guess;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class TopGuesserController implements Observer {
    @FXML
    Label turnLabel, clueLabel, numberLabel;

    private Game game;
    private FadeTransition fadeTransition;

    public void initialize() {
        game = Game.getInstance();
        game.ajouterObservateur(this);
        fadeTransition = new FadeTransition(Duration.seconds(0.5), turnLabel);
        fadeTransition.setFromValue(1.0); // Opacité maximale (visible)
        fadeTransition.setToValue(0.0);   // Opacité minimale (invisible)
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);   // Le texte disparaît puis réapparaît
        fadeTransition.setAutoReverse(true); // Revenir à l'état initial (visible)
        reagir();
    }

    public void reagir() {
        if (game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion bleu !");
            turnLabel.setStyle("-fx-text-fill: blue;");
            stopFading();
        } else if (game.isSpyTurn() && !game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion rouge !");
            turnLabel.setStyle("-fx-text-fill: red;");
            stopFading();
        } else if (!game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour du devin bleu !");
            turnLabel.setStyle("-fx-text-fill: blue;");
            startFading();
        } else {
            turnLabel.setText("C'est au tour du devin rouge !");
            turnLabel.setStyle("-fx-text-fill: red;");
            startFading();
        }

        Guess lastGuess = game.getLastGuess();
        if (lastGuess != null) {
            clueLabel.setText("Indice : "+lastGuess.getClue());
            numberLabel.setText("          Nombre de mot à deviner : "+Integer.toString(lastGuess.getNbWords()));
        }
    }

    private void startFading() {
        fadeTransition.play();
    }

    // Méthode pour arrêter l'animation de fondu
    private void stopFading() {
        if (fadeTransition != null) {
            fadeTransition.stop();  // Arrêter l'animation
            turnLabel.setOpacity(1.0);  // Assurer que le label reste visible
        }
    }
}
