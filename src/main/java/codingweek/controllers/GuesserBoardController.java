package codingweek.controllers;

import codingweek.models.Board;
import codingweek.models.Card;
import codingweek.models.Game;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GuesserBoardController implements codingweek.Observer {

    @FXML
    private GridPane boardGrid;

    private final Game game = Game.getInstance();
    private final Board board = game.getBoard();

    public void initialize() {
        int gridSize = game.getBoardSize();
        populateBoard(gridSize);
        updateBackgroundColor();
        game.ajouterObservateur(this);
    }

    @Override
    public void reagir() {
        updateBackgroundColor();
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
                        "-fx-padding: 10; " +
                        "-fx-background-radius: 15; " +
                        "-fx-border-radius: 15;");
                cardPane.setPrefSize(100, 100);

                if (game.getImagesMode()) {
                    try {
                        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(card.getWord());
                        imageView.setFitWidth(80);
                        imageView.setFitHeight(80);
                        imageView.setPreserveRatio(true);
                        cardPane.getChildren().add(imageView);
                    } catch (Exception e) {
                        System.out.println(card.getWord());
                        // On récupère le filename pour afficher le mot à la place de l'image
                        Path path = Paths.get(card.getWord());
                        String fileNameWithExtension = path.getFileName().toString();
                        int lastDotIndex = fileNameWithExtension.lastIndexOf('.');
                        String fileNameWithoutExtension = lastDotIndex == -1 ? 
                                                        fileNameWithExtension : 
                                                        fileNameWithExtension.substring(0, lastDotIndex);
                        Label wordLabel = new Label(fileNameWithoutExtension);
                        wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
                        cardPane.getChildren().add(wordLabel);
                    }
                } else {
                    Label wordLabel = new Label(card.getWord());
                    wordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
                    cardPane.getChildren().add(wordLabel);
                }

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
            System.out.println("La carte '" + card.getWord() + "' a ete revelee.");
            return;
        }

        
        String cssColor = convertColorToCSS(card.getColor());
        tile.setStyle("-fx-border-color: black; " +
                "-fx-background-color: " + cssColor + "; " +
                "-fx-padding: 10; " +
                "-fx-background-radius: 15; " +
                "-fx-border-radius: 15;");

        
        card.setRevealed(true);

        game.returnCard(card);

        // Notifie les observateurs que la carte a ete revelee
        game.notifierObservateurs();
    }

    private String convertColorToCSS(String javafxColor) {
        if (javafxColor.startsWith("0x")) {
            return "#" + javafxColor.substring(2, 8); 
        }
        return javafxColor; 
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
