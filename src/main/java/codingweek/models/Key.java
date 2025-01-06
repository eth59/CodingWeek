package codingweek.models;

import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Key {

    // Dimensions de la grille (5x5 pour cet exemple)
    private static final int LIGNES = 5;
    private static final int COLONNES = 5;

    // Matrice de couleurs représentant la grille
    private Color[][] grille;

    // Instance unique de la classe GrilleCodeNames (Singleton)
    private static Key instance;

    // Constructeur privé pour empêcher la création d'instances en dehors de la classe
    private Key() {
        grille = new Color[LIGNES][COLONNES];
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
        int nombreBlanches = LIGNES * COLONNES - couleurs.size();
        for (int i = 0; i < nombreBlanches; i++) {
            couleurs.add(Color.WHITE);  // Cases blanches
        }

        // Mélanger les couleurs pour qu'elles soient placées aléatoirement
        Collections.shuffle(couleurs, new Random());

        // Remplir la grille avec les couleurs mélangées
        int index = 0;
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                grille[i][j] = couleurs.get(index++);
            }
        }
    }

    // Méthode pour définir la couleur d'une case donnée
    public void setCouleur(int ligne, int colonne, Color couleur) {
        if (ligne >= 0 && ligne < LIGNES && colonne >= 0 && colonne < COLONNES) {
            grille[ligne][colonne] = couleur;
        }
    }

    // Méthode pour récupérer la couleur d'une case
    public Color getCouleur(int ligne, int colonne) {
        if (ligne >= 0 && ligne < LIGNES && colonne >= 0 && colonne < COLONNES) {
            return grille[ligne][colonne];
        }
        return Color.TRANSPARENT;  // Retourne une couleur transparente si l'index est invalide
    }

    // Méthode pour afficher la grille dans la console (juste à titre d'exemple)
    public void afficherGrille() {
        for (int i = 0; i < LIGNES; i++) {
            for (int j = 0; j < COLONNES; j++) {
                System.out.print(grille[i][j].toString() + " ");
            }
            System.out.println();
        }
    }
}
