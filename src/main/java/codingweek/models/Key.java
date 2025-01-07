package codingweek.models;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {

    // Singleton instance
    private static Key instance;

    // Matrice grille pour representer la grille
    private Color[][] grille;

    // Dimension des grilles recuperer depuis Game
    private int lignes;
    private int colonnes;

    private Key() {
        Game game = Game.getInstance(); // Recupere la session du Game
        this.lignes = game.getBoardSize(); // Taille du plateau
        this.colonnes = game.getBoardSize(); // Meme valeurs que lignes pour avoir un carre
        grille = new Color[lignes][colonnes];
        initialiserGrille();
    }

    // Method pour recuperer l'instance de Key
    public static Key getInstance() {
        if (instance == null) {
            instance = new Key();
        }
        return instance;
    }

    // Initialise la grille avec des couleurs aleatoires 
    private void initialiserGrille() {
        // Create a list of colors with exactly 8 blue, 8 red, 1 black, and the rest neutral
        List<Color> couleurs = new ArrayList<>();
        boolean blueTurn = Game.getInstance().isBlueTurn();

        // Ajoute les couleurs specifiques
        for (int i = 0; i < 8; i++) {
            couleurs.add(Color.web(Card.BLUE_COLOR)); // 8 tiles bleues
            couleurs.add(Color.web(Card.RED_COLOR));  // 8 tiles rouges
        }
        couleurs.add(Color.BLACK); // 1 tile noire

        // Ajoute des tiles neutrales pour le reste des cellules
        int totalCases = lignes * colonnes;
        int nombreNeutres = totalCases - couleurs.size();
        for (int i = 0; i < nombreNeutres-1; i++) {
            couleurs.add(Color.web(Card.NEUTRAL_COLOR));
        }

        if (blueTurn) {
            couleurs.add(Color.web(Card.BLUE_COLOR));
        } else {
            couleurs.add(Color.web(Card.RED_COLOR));
        }

        // Shuffle couleurs aleatoirement
        Collections.shuffle(couleurs, new Random());

        // Rempli la grille avec les couleurs aleatoires
        int index = 0;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                grille[i][j] = couleurs.get(index++);
            }
        }
    }

    // Defini la couleur d'une cellule specifique
    public void setCouleur(int ligne, int colonne, Color couleur) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            grille[ligne][colonne] = couleur;
        }
    }

    // Recupere la couleur d'une cellule specifique
    public Color getCouleur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            return grille[ligne][colonne];
        }
        return Color.TRANSPARENT; // Return transparent color if the index is invalid
    }

    // Affiche la grille dans le terminal pour debugger
    public void afficherGrille() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print(grille[i][j].toString() + " ");
            }
            System.out.println();
        }
    }
}
