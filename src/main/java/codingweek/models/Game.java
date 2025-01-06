package codingweek.models;

import java.io.Serializable;
import java.util.Stack;

public class Game extends Subject implements Serializable {
    private int boardSize;
    private int timeLimit; // in seconds
    private boolean blueTurn; // true for blue, false for red
    private boolean spyTurn; // true for spy, false for guesser
    private Stack<Guess> guesses;
    private Board board;
    private static Game instance;

    private Game() {
        this.board = Board.getInstance();
        this.boardSize = 5;
        this.timeLimit = 60;
        this.spyTurn = true;
        this.blueTurn = true;
        this.guesses = new Stack<Guess>();

        initializeBoard();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Board getBoard() {
        return board;
    }

    public void addGuess(Guess guess) {
        // Fonction pour ajouter un nouveau guess fait par un espion
        guesses.push(guess);
    }

    public Guess getLastGuess() {
        if (guesses.isEmpty()) {
            return null;
        }
        return guesses.peek();
    }

    public boolean isBlueTurn() {
        return blueTurn;
    }

    public boolean isSpyTurn() {
        return spyTurn;
    }

    public int submitClue(String clue, int clueNb) {
        if (clueIsValid(clue) && 0 < clueNb && clueNb <= (int) Math.pow(this.boardSize, 2) && this.spyTurn) {
            Guess guess = new Guess(clue, clueNb);
            addGuess(guess);
            changeTurn();
            notifierObservateurs();
            return 1;
        } else {
            return 0;
        }
    }
    
    private boolean clueIsValid(String clue) {
        // TO DO Vérifier que l'indice est valide
        return true;
    }

    public void changeTurn() {
        if (spyTurn) {
            spyTurn = false;
        } else if (blueTurn) {
            blueTurn = false;
            spyTurn = true;
        } else {
            blueTurn = true;
            spyTurn = true;
        }
        notifierObservateurs();
    }

    private void initializeBoard() {
        String[] words = {
            "APPLE", "BANANA", "ORANGE", "WATER", "FIRE",
            "EARTH", "WIND", "STORM", "TREE", "MOUNTAIN",
            "HOUSE", "BRIDGE", "LIGHT", "SHADOW", "STONE",
            "CAR", "PLANE", "SHIP", "TRAIN", "SPACE",
            "ROBOT", "KNIGHT", "CASTLE", "PRINCE", "PRINCESS"
        };
    
        // Initialize all cards as neutral
        for (String word : words) {
            board.addCard(new Card(word, Card.NEUTRAL_COLOR));
        }
    }
    
    
}
