package codingweek.models;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {

    // Singleton instance
    private static Key instance;

    // Grid matrix representing the key
    private Color[][] grille;

    // Grid dimensions (taken from Game)
    private int lignes;
    private int colonnes;

    // Private constructor to prevent instantiation
    private Key() {
        Game game = Game.getInstance(); // Get the Game instance
        this.lignes = game.getBoardSize(); // Board size
        this.colonnes = game.getBoardSize(); // Same value for a square grid
        grille = new Color[lignes][colonnes];
        initialiserGrille();
    }

    // Method to get the singleton instance
    public static Key getInstance() {
        if (instance == null) {
            instance = new Key();
        }
        return instance;
    }

    // Initialize the grid with controlled random colors
    private void initialiserGrille() {
        // Create a list of colors with exactly 8 blue, 8 red, 1 black, and the rest neutral
        List<Color> couleurs = new ArrayList<>();

        // Add specific colors to the list
        for (int i = 0; i < 8; i++) {
            couleurs.add(Color.web(Card.BLUE_COLOR)); // 8 blue tiles
            couleurs.add(Color.web(Card.RED_COLOR));  // 8 red tiles
        }
        couleurs.add(Color.BLACK); // 1 black tile

        // Add neutral tiles to fill the rest of the grid
        int totalCases = lignes * colonnes;
        int nombreNeutres = totalCases - couleurs.size();
        for (int i = 0; i < nombreNeutres; i++) {
            couleurs.add(Color.web(Card.NEUTRAL_COLOR)); // Neutral tiles
        }

        // Shuffle colors randomly
        Collections.shuffle(couleurs, new Random());

        // Fill the grid with the shuffled colors
        int index = 0;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                grille[i][j] = couleurs.get(index++);
            }
        }
    }

    // Set the color of a specific grid cell
    public void setCouleur(int ligne, int colonne, Color couleur) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            grille[ligne][colonne] = couleur;
        }
    }

    // Get the color of a specific grid cell
    public Color getCouleur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            return grille[ligne][colonne];
        }
        return Color.TRANSPARENT; // Return transparent color if the index is invalid
    }

    // Display the grid in the console (for debugging purposes)
    public void afficherGrille() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print(grille[i][j].toString() + " ");
            }
            System.out.println();
        }
    }
}
