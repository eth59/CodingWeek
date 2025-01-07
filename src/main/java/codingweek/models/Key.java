package codingweek.models;
import codingweek.Observer;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {

    // Singleton instance
    private static Key instance;

    // Grid to represent the grid
    private Color[][] grille;

    // Grid dimensions from Game
    private int lignes;
    private int colonnes;

    // Observers (Cards)
    private final List<Observer> cards = new ArrayList<>();

    private Key() {
        Game game = Game.getInstance();
        this.lignes = game.getBoardSize();
        this.colonnes = game.getBoardSize();
        grille = new Color[lignes][colonnes];
        initialiserGrille();
        assignColorsToCards(); // Assign colors directly to cards
    }

    // Singleton getInstance method
    public static Key getInstance() {
        if (instance == null) {
            instance = new Key();
        }
        return instance;
    }

    public int getLignes() { return lignes; }
    public int getColonnes() { return colonnes; }

    // Register a card as an observer
    public void addObserver(Observer observer) {
        cards.add(observer);
    }

    // Notify all card observers
    private void notifyObservers() {
        for (Observer observer : cards) {
            observer.reagir();
        }
    }

    // Initialize the grid with random colors
    private void initialiserGrille() {
        List<Color> couleurs = new ArrayList<>();
        boolean blueTurn = Game.getInstance().isBlueTurn();

        for (int i = 0; i < 8; i++) {
            couleurs.add(Color.web(Card.BLUE_COLOR));
            couleurs.add(Color.web(Card.RED_COLOR));
        }
        couleurs.add(Color.BLACK);

        int totalCases = lignes * colonnes;
        int nombreNeutres = totalCases - couleurs.size();
        for (int i = 0; i < nombreNeutres - 1; i++) {
            couleurs.add(Color.web(Card.NEUTRAL_COLOR));
        }

        if (Game.getInstance().isBlueTurn()) {
            couleurs.add(Color.web(Card.BLUE_COLOR));
        } else {
            couleurs.add(Color.web(Card.RED_COLOR));
        }

        Collections.shuffle(couleurs, new Random());

        int index = 0;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                grille[i][j] = couleurs.get(index++);
            }
        }
    }

    // Assign colors from the grid to the corresponding cards
    private void assignColorsToCards() {
        Game game = Game.getInstance();
        Board board = game.getBoard();
        List<Card> cards = board.getCards();

        for (int i = 0; i < cards.size(); i++) {
            int row = i / lignes;
            int col = i % colonnes;
            String color = getCouleur(row, col).toString();
            cards.get(i).setColor(color);
        }

        // Notify all observers
        notifyObservers();
    }

    public Color getCouleur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            return grille[ligne][colonne];
        }
        return Color.TRANSPARENT;
    }

}
