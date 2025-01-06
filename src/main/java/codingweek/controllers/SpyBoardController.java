package codingweek.controllers;

import codingweek.models.Board;
import codingweek.models.Card;
import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class SpyBoardController {

    @FXML
    private GridPane boardGrid;

    private final Game game = Game.getInstance();
    private final Board board = game.getBoard();

    public void initialize() {
        int gridSize = game.getBoardSize();
        populateBoard(gridSize);
    }

    private void populateBoard(int gridSize) {
        List<Card> cards = board.getCards();
        int cardIndex = 0;

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                StackPane cardPane = new StackPane();
                Card card = cards.get(cardIndex++);

                // Use card's color for the tile's background
                cardPane.setStyle("-fx-border-color: black; -fx-background-color: " + card.getColor() + "; -fx-padding: 10;");
                cardPane.setPrefSize(100, 100);

                Label wordLabel = new Label(card.getWord());
                wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
                cardPane.getChildren().add(wordLabel);

                boardGrid.add(cardPane, col, row);
            }
        }
    }
}
