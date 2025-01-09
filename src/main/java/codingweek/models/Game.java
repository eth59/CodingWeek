package codingweek.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
    private boolean[][] revealedTiles;
    private int nbCardReturned;
    private int clueNb; // Nombre donné par l'espion
    private PageManager pageManager;
    private boolean isTimerRunning;
    private int blueReturned; // Nombre de carte bleu retournée
    private int redReturned; // Nombre de carte rouge retournée
    private boolean blueBegin;
    private boolean imagesMode;

    private Game() {
        this.board = Board.getInstance();
        this.spyTurn = true;
        this.blueTurn = Math.random() > 0.5;
        this.blueBegin = blueTurn;
        this.category = "Métier";
        this.guesses = new Stack<Guess>();
        revealedTiles = new boolean[boardSize][boardSize];
        this.nbCardReturned = 0;
        this.blueReturned = 0;
        this.redReturned = 0;
    }

    public void initializeGame(int boardSize, String category, String timeLimit, boolean imagesMode) {
        this.boardSize = boardSize;
        this.category = category;
        this.guesses.clear();
        this.revealedTiles = new boolean[boardSize][boardSize];
        if (timeLimit.equals("illimité")) {
            this.timeLimit = 0;
        } else {
            this.timeLimit = Integer.parseInt(timeLimit);
        }
        this.blueReturned = 0;
        this.redReturned = 0;
        this.blueTurn = Math.random() > 0.5;
        this.blueBegin = blueTurn;
        this.spyTurn = true;
        this.imagesMode = imagesMode;
        initializeRevealedTiles();
        initializeBoard();
    }

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

    // Affectation des categories
    public void setCategory(String category) {
        this.category = category;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        initializeRevealedTiles();
        initializeBoard();
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

    private void initializeRevealedTiles() {
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                revealedTiles[row][col] = false;
            }
        }
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
            startTimer();
        } else if (blueTurn) {
            blueTurn = false;
            spyTurn = true;
            stopTimer();
        } else {
            blueTurn = true;
            spyTurn = true;
            stopTimer();
        }
        notifierObservateurs();
    }

    private void initializeBoard() {
        try {
            int totalCards = boardSize * boardSize; // Calcule le nombre de cartes necessaire
            
            // Recupere les cartes et les melange
            ArrayList<Card> cards = getShuffledCards(this.category, totalCards);
    
            // Efface et peuple le plateau
            populateBoard(cards, totalCards);
    
            // Reinitialise l'array des revealedTiles pour le plateau de nouvelle taille
            initializeRevealedTiles();
    
            System.out.println("Plateau initialise avec " + totalCards + " cartes pour le pplateau de taille " + boardSize + "x" + boardSize);
        } catch (Exception e) {
            System.err.println("Erreur lors de l'initialisation du plateau: " + e.getMessage());
        }
    }
    
    private ArrayList<Card> getShuffledCards(String category, int totalCards) throws IOException {
        ArrayList<Card> cards = JsonReader.jsonReader("mots.json", category, imagesMode);
        if (cards.size() < totalCards) {
            throw new IllegalArgumentException("Pas assez de cartes pour peupler le plateau.");
        }
        Collections.shuffle(cards);
        return new ArrayList<>(cards.subList(0, totalCards));
    }
    
    private void populateBoard(ArrayList<Card> cards, int totalCards) {
        board.cleanCards();
        ArrayList<String> cardNames = new ArrayList<>();
        for (int i = 0; i < totalCards; i++) {
            cardNames.add(cards.get(i).getWord());
        }
        for (int i = 0; i < totalCards; i++) {
            cards.get(i).addForbiddenWords(cardNames);
            board.addCard(cards.get(i));
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
            this.blueReturned +=1;
            if (this.nbCardReturned == this.clueNb + 1) {
                changeTurn();
            }
        } else if (!blueTurn && card.getColor().equals("0xc1121fff")) {
            // red team's turn and card is red
            this.nbCardReturned += 1;
            this.redReturned +=1;
            if (this.nbCardReturned == this.clueNb + 1) {
                changeTurn();
            }
        } else if (card.getColor().equals("0xf0ead2ff")) {
            // neutral
            changeTurn();
            
        } else if (card.getColor().equals("0x000000ff")) {
            // assassin
            if (blueTurn) {
                pageManager.loadGameOverViewRedWin();
            } else {
                pageManager.loadGameOverViewBlueWin();
            }
        } else  if (!blueTurn && card.getColor().equals("0x003566ff")) {
            this.blueReturned +=1;
            // opponent's card
            changeTurn();
        } else {
            this.redReturned +=1;
            changeTurn();
        }
        if (blueBegin) { // Bleu commençe
            if (blueReturned == boardSize*boardSize/3+1) {
                pageManager.loadGameOverViewBlueWin();
            } else if (redReturned == boardSize*boardSize/3) {
                pageManager.loadGameOverViewRedWin();
            }
        } else if (!blueBegin) { // Rouge commençe
            if (redReturned == boardSize*boardSize/3 + 1) {
                pageManager.loadGameOverViewRedWin();
            } else if (blueReturned == boardSize*boardSize/3) {
                pageManager.loadGameOverViewBlueWin();
            }
        }
    }

    public int getNbCardsReturned() {
        return this.nbCardReturned;
    }

    public void setPageManager() {
        pageManager = PageManager.getInstance();
    }

    public void startTimer() {
        if (this.timeLimit > 0) {
            isTimerRunning = true;
            notifierObservateurs();    
        }
    }

    private void stopTimer() {
        isTimerRunning = false;
        notifierObservateurs();
    }

    public boolean isTimerRunning() {
        return isTimerRunning;
    }

    public int getBlueReturned(){
        return blueReturned;
    }

    public int getRedReturned(){
        return redReturned;
    }

    public boolean getBlueBegin(){
        return blueBegin;
    }

    public boolean getImagesMode(){
        return this.imagesMode;
    }
}
