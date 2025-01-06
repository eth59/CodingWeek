package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class SpyBoardController {

    @FXML
    private GridPane boardGrid;

    // Define the game board size (5x5 grid for Code Names)
    private static final int GRID_ROWS = 5;
    private static final int GRID_COLS = 5;

    // Sample words for the board
    private static final String[] WORDS = {
        "APPLE", "BANANA", "ORANGE", "WATER", "FIRE",
        "EARTH", "WIND", "STORM", "TREE", "MOUNTAIN",
        "HOUSE", "BRIDGE", "LIGHT", "SHADOW", "STONE",
        "CAR", "PLANE", "SHIP", "TRAIN", "SPACE",
        "ROBOT", "KNIGHT", "CASTLE", "PRINCE", "PRINCESS"
    };

    public void initialize() {
        // Populate the board with words
        populateBoard();
    }

    private void populateBoard() {
        int wordIndex = 0;

        for (int row = 0; row < GRID_ROWS; row++) {
            for (int col = 0; col < GRID_COLS; col++) {
                // Create a StackPane to represent each card
                StackPane cardPane = new StackPane();
                cardPane.setStyle("-fx-border-color: black; -fx-background-color: lightblue; -fx-padding: 10;");
                cardPane.setPrefSize(100, 100);

                // Create a Label for the word
                Label wordLabel = new Label(WORDS[wordIndex++]);
                wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");

                // Add the label to the card
                cardPane.getChildren().add(wordLabel);

                // Add the card to the grid
                boardGrid.add(cardPane, col, row);
            }
        }
    }
}
