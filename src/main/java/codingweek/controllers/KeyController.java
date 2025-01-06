package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import codingweek.models.Key;

public class KeyController {

    @FXML
    private GridPane gridPane;

    @FXML
    public void initialize() {
        // Récupérer l'instance unique de Key
        Key key = Key.getInstance();

        // Parcourir la grille et remplir la GridPane avec des rectangles colorés
        for (int i = 0; i < 5; i++) { // Assumer une grille 5x5
            for (int j = 0; j < 5; j++) {
                // Créer un rectangle pour représenter une case
                Rectangle rect = new Rectangle(50, 50);
                rect.setFill(key.getCouleur(i, j)); // Définir la couleur de la case
                rect.setStroke(Color.BLACK); // Bordure noire pour démarquer les cases

                // Ajouter le rectangle à la GridPane
                gridPane.add(rect, j, i);
            }
        }
    }
}
