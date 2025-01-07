package codingweek.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;
import javafx.stage.FileChooser;

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
    
    public void saveGame() {
        // Sauvegarde de la partie dans un fichier .game

        // On ouvre un FileChooser pour choisir le fichier de sauvegarde
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder la partie");

        // On ajoute un filtre pour n'afficher que les fichiers .game
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Game", "*.game"));

        // On ouvre le FileChooser dans le répertoire du .jar
        String userDirectory = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userDirectory));


        // On propose un nom de fichier par défaut
        fileChooser.setInitialFileName("partie.game");

        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                oos.writeObject(this);
            } catch (Exception e) {
                System.err.println("Erreur lors de la sauvegarde de la partie : " + e.getMessage());
            }
        } else {
            System.err.println("Aucun fichier sélectionné.");
        }
    }

    public void loadGame() {
        // On ouvre un FileChooser pour sélectionner un fichier .game
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger une partie");

        // On filtre pour n'avoir que les fichiers .game
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Game", "*.game"));

        // On propose le répertoire courant comme le répertoire du jar
        String userDirectory = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userDirectory));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Chargement de la partie depuis le fichier .game
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                Game loadedGame = (Game) ois.readObject();
                this.boardSize = loadedGame.getBoardSize();
                this.timeLimit = loadedGame.getTimeLimit();
                this.blueTurn = loadedGame.isBlueTurn();
                System.out.println("Blue turn: " + this.blueTurn);
                this.spyTurn = loadedGame.isSpyTurn();
                System.out.println("Spy turn: " + this.spyTurn);
                this.guesses = loadedGame.guesses;
                this.board = loadedGame.board;
                this.notifierObservateurs(); // Notifie les observateurs que la partie a été modifiée
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement de la partie : " + e.getMessage());
            }
        } else {
            System.err.println("Aucun fichier sélectionné.");
        }
    }
}
