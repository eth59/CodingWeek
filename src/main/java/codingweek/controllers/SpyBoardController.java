package codingweek.controllers;

import codingweek.Observer;
import codingweek.models.Board;
import codingweek.models.Card;
import codingweek.models.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.util.List;

public class SpyBoardController implements Observer {

    @FXML
    private GridPane boardGrid;

    private final Game game = Game.getInstance();
    private final Board board = game.getBoard();

    @Override
    public void reagir() {
        updateBackgroundColor();
        updateRevealedTiles();
    }

    private void updateRevealedTiles() {
        List<Card> cards = board.getCards();
        int gridSize = game.getBoardSize();
    
        int cardIndex = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Card card = cards.get(cardIndex++);
                if (card.isRevealed()) {
                    StackPane cardPane = (StackPane) getNodeByRowColumnIndex(row, col, boardGrid);
    
                    if (cardPane != null) {
                        String backgroundColor = convertColorToCSS(card.getColor());
                        cardPane.setStyle("-fx-border-color: black; -fx-background-color: " +
                                backgroundColor +
                                "; -fx-padding: 10;" +
                                "-fx-background-radius: 15; " +
                                "-fx-border-radius: 15;");
                    }
                }
            }
        }

    }

    private StackPane getNodeByRowColumnIndex(int row, int col, GridPane gridPane) {
        for (javafx.scene.Node node : gridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col) {
                return (StackPane) node;
            }
        }
        return null;
    }

    private String convertColorToCSS(String javafxColor) {
        if (javafxColor.startsWith("0x")) {
            return "#" + javafxColor.substring(2, 8); 
        }
        return javafxColor; 
    }

    public void initialize() {
        game.ajouterObservateur(this);
        updateBackgroundColor();
        int gridSize = game.getBoardSize();
        populateBoard(gridSize);
        board.addCardObservers(this);

    }

    private void populateBoard(int gridSize) {
        List<Card> cards = board.getCards();
        int totalCards = gridSize * gridSize; // Verifie le nombre de cartes
        if (cards.size() != totalCards) {
            throw new IllegalStateException("Il n'y a pas assez de cartes.");
        }
    
        int cardIndex = 0;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                StackPane cardPane = new StackPane();
                Card card = cards.get(cardIndex++);
    
                cardPane.setStyle("-fx-border-color: black; " +
                        "-fx-background-color: lightgrey; " +
                        "-fx-padding: 10;" +
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15;");
                cardPane.setPrefSize(100, 100);
    
                Label wordLabel = new Label(card.getWord());
                wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
                cardPane.getChildren().add(wordLabel);
    
                boardGrid.add(cardPane, col, row);
            }
        }
    }

    private void updateBackgroundColor() {
        Boolean currentTeam = game.isBlueTurn();
        if (currentTeam) {
            boardGrid.setStyle("-fx-padding: 20; -fx-background-color: #85C4FF;");
        } else {
            boardGrid.setStyle("-fx-padding: 20; -fx-background-color: #F6A2A7;");
        }
    }

}
