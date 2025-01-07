package codingweek.controllers;

import codingweek.models.Board;
import codingweek.models.Card;
import codingweek.models.Game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class GuesserBoardController {

    @FXML
    private GridPane boardGrid;

    private final Game game = Game.getInstance();
    private final Board board = game.getBoard();

    public void initialize() {
        int gridSize = game.getBoardSize();
        populateBoard(gridSize);
    }

    private void populateBoard(int gridSize) {
        List<Card> cards = board.getCards(); // Get the list of cards from the board
        int cardIndex = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                StackPane cardPane = new StackPane();
                Card card = cards.get(cardIndex++); // Get the corresponding card

                // Default style for unrevealed cards
                cardPane.setStyle("-fx-border-color: black; -fx-background-color: lightgrey; -fx-padding: 10;");
                cardPane.setPrefSize(100, 100);

                Label wordLabel = new Label(card.getWord());
                wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
                cardPane.getChildren().add(wordLabel);

                // Set click handler for revealing the card
                cardPane.setOnMouseClicked(event -> onCardClicked(cardPane, card));

                boardGrid.add(cardPane, col, row);
            }
        }
    }

    private void onCardClicked(StackPane tile, Card card) {
        if (game.isSpyTurn()) {
            System.out.println("C'est au tour des espions.");
            return;
        }
        // Check if the card is already revealed
        if (card.isRevealed()) {
            System.out.println("Card '" + card.getWord() + "' is already revealed.");
            return;
        }

        // Reveal the card by changing its appearance
        String cssColor = convertColorToCSS(card.getColor());
        tile.setStyle("-fx-border-color: black; -fx-background-color: " + cssColor + "; -fx-padding: 10;");

        // Set the card's isRevealed property to true
        card.setRevealed(true);

        game.returnCard(card);

        // Notify observers about the card being revealed
        game.notifierObservateurs();
    }

    // Utility method to convert JavaFX color strings to CSS-compatible format
    private String convertColorToCSS(String javafxColor) {
        if (javafxColor.startsWith("0x")) {
            return "#" + javafxColor.substring(2, 8); // Extract #RRGGBB part
        }
        return javafxColor; // Return as-is if already in correct format
    }
}
