package codingweek.controllers;

import codingweek.Observer;
import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import javafx.animation.FadeTransition;

public class TopSpyController implements Observer {
    @FXML
    Label turnLabel;

    @FXML
    TextField clueField, numberField;    

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
            startFading();
        } else if (game.isSpyTurn() && !game.isBlueTurn()) {
            turnLabel.setText("C'est au tour de l'espion rouge !");
            turnLabel.setStyle("-fx-text-fill: red;");
            startFading();
        } else if (!game.isSpyTurn() && game.isBlueTurn()) {
            turnLabel.setText("C'est au tour du devin bleu !");
            turnLabel.setStyle("-fx-text-fill: blue;");
            stopFading();
        } else {
            turnLabel.setText("C'est au tour du devin rouge !");
            turnLabel.setStyle("-fx-text-fill: red;");
            stopFading();
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
