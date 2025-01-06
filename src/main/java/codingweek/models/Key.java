package codingweek.models;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {

    // Instance unique de la classe Key (Singleton)
    private static Key instance;

    // Matrice de couleurs représentant la grille
    private Color[][] grille;

    // Dimensions de la grille (prises depuis Game)
    private int lignes;
    private int colonnes;

    // Constructeur privé pour empêcher la création d'instances en dehors de la classe
    private Key() {
        Game game = Game.getInstance(); // Récupérer l'instance du jeu
        this.lignes = game.getBoardSize(); // Taille du plateau
        this.colonnes = game.getBoardSize(); // Même valeur pour une grille carrée
        grille = new Color[lignes][colonnes];
        initialiserGrille();
    }

    // Méthode pour récupérer l'instance unique
    public static Key getInstance() {
        if (instance == null) {
            instance = new Key();
        }
        return instance;
    }

    // Méthode d'initialisation de la grille avec des couleurs aléatoires contrôlées
    private void initialiserGrille() {
        // Créer une liste de couleurs avec exactement 8 bleues, 8 rouges, 1 noire et le reste blanches
        List<Color> couleurs = new ArrayList<>();

        // Ajouter les couleurs spécifiques dans la liste
        for (int i = 0; i < 8; i++) {
            couleurs.add(Color.BLUE);  // 8 cases bleues
            couleurs.add(Color.RED);   // 8 cases rouges
        }
        couleurs.add(Color.BLACK);    // 1 case noire

        // Ajouter des cases blanches pour remplir le reste de la grille
        int totalCases = lignes * colonnes;
        int nombreBlanches = totalCases - couleurs.size();
        for (int i = 0; i < nombreBlanches; i++) {
            couleurs.add(Color.WHITE);  // Cases blanches
        }

        // Mélanger les couleurs pour qu'elles soient placées aléatoirement
        Collections.shuffle(couleurs, new Random());

        // Remplir la grille avec les couleurs mélangées
        int index = 0;
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                grille[i][j] = couleurs.get(index++);
            }
        }
    }

    // Méthode pour définir la couleur d'une case donnée
    public void setCouleur(int ligne, int colonne, Color couleur) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            grille[ligne][colonne] = couleur;
        }
    }

    // Méthode pour récupérer la couleur d'une case
    public Color getCouleur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < lignes && colonne >= 0 && colonne < colonnes) {
            return grille[ligne][colonne];
        }
        return Color.TRANSPARENT;  // Retourne une couleur transparente si l'index est invalide
    }

    // Méthode pour afficher la grille dans la console (juste à titre d'exemple)
    public void afficherGrille() {
        for (int i = 0; i < lignes; i++) {
            for (int j = 0; j < colonnes; j++) {
                System.out.print(grille[i][j].toString() + " ");
            }
            System.out.println();
        }
    }
}
