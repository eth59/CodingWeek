package codingweek.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    private String category;
    private final boolean[][] revealedTiles;
    private int nbCardReturned;
    private int clueNb; // Nombre donné par l'espion
    private PageManager pageManager;

    private Game() {
        this.board = Board.getInstance();
        this.boardSize = 5;
        this.timeLimit = 60;
        this.spyTurn = true;
        this.blueTurn = Math.random() > 0.5;
        this.category = "Métier";
        this.guesses = new Stack<Guess>();
        revealedTiles = new boolean[boardSize][boardSize];
        this.nbCardReturned = 0;
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
            this.clueNb = clueNb; 
            changeTurn();
            notifierObservateurs();
            return 1;
        } else {
            return 0;
        }
    }
    
    private boolean clueIsValid(String clue) {
        clue = clue.toLowerCase();
        for (Card card : board.getCards()) {
            if (!card.isRevealed() && (card.getWord().equals(clue) || card.getForbiddenWords().contains(clue) || Arrays.asList("gauche", "droite", "haut", "bas", "centre").contains(clue))) {
                return false;
            }
        }
        return true;
    }

    // Marque qu'une case du plateau a ete revelee
    public void revealTile(int row, int col) {
        revealedTiles[row][col] = true;
    }

    // Verfier si une case du plateau est revelée
    public boolean isTileRevealed(int row, int col) {
        return revealedTiles[row][col];
    }

    public void changeTurn() {
        this.nbCardReturned = 0;
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
        try {
            ArrayList<String> cardName = new ArrayList<String>();
            ArrayList<Card> cards = JsonReader.jsonReader("mots.json", this.category);
            Collections.shuffle(cards);
            for (int i = 0; i < 25; i++) {
                cardName.add(cards.get(i).getWord());
            }
            for (int i = 0; i < 25; i++) {
                cards.get(i).addForbiddenWords(cardName);
                board.addCard(cards.get(i));
            }
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation du plateau : " + e.getMessage());
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

    public void returnCard(Card card) {
        // Black : 0x000000ff
        // Red : 0xc1121fff
        // Blue : 0x003566ff
        // Neutral : 0xf0ead2ff
        if (blueTurn && card.getColor().equals("0x003566ff")) {
            // blue team's turn and card is blue
            this.nbCardReturned += 1;
            if (this.nbCardReturned == this.clueNb + 1) {
                changeTurn();
            }
        } else if (!blueTurn && card.getColor().equals("0xc1121fff")) {
            // red team's turn and card is red
            this.nbCardReturned += 1;
            if (this.nbCardReturned == this.clueNb + 1) {
                changeTurn();
            }
        } else if (card.getColor().equals("0xf0ead2ff")) {
            // neutral
            changeTurn();
            
        } else if (card.getColor().equals("0x000000ff")) {
            // assassin
            pageManager.loadGameOverView();
        } else {
            // opponent's card
            changeTurn();
        }
    }

    public int getNbCardsReturned() {
        return this.nbCardReturned;
    }

    public void setPageManager() {
        pageManager = PageManager.getInstance();
    }

}
