package codingweek.controllers;

import codingweek.models.Board;
import codingweek.models.Card;
import codingweek.models.Game;
import codingweek.models.Key;
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
    private final Key key = Key.getInstance(); // Recupere l'instance de la clef pour les couleurs

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

                // Couleur par defaut des tiles pas revelees
                cardPane.setStyle("-fx-border-color: black; -fx-background-color: lightgrey; -fx-padding: 10;");
                cardPane.setPrefSize(100, 100);

                Label wordLabel = new Label(card.getWord());
                wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
                cardPane.getChildren().add(wordLabel);

                // Fonction onclick pour changer la couleur des tiles
                final int currentRow = row;
                final int currentCol = col;
                cardPane.setOnMouseClicked(event -> onTileClicked(cardPane, currentRow, currentCol));

                boardGrid.add(cardPane, col, row);
            }
        }
    }

    private void onTileClicked(StackPane tile, int row, int col) {
        // Recupere la couleur des tiles depuis la clef pour les afficher
        String color = toHex(key.getCouleur(row, col));
        tile.setStyle("-fx-border-color: black; -fx-background-color: " + color.toString() + "; -fx-padding: 10;");
    }

    // Fonction pour reconvertir les couleurs reelles en hexadecimales
    private String toHex(javafx.scene.paint.Color color) {
        int r = (int) (color.getRed() * 255);
        int g = (int) (color.getGreen() * 255);
        int b = (int) (color.getBlue() * 255);
        return String.format("#%02X%02X%02X", r, g, b);
    }
}
