package codingweek.controllers;

import codingweek.models.Game;
import codingweek.models.JsonReader;
import codingweek.models.Key;
import codingweek.models.PageManager;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;

import java.io.IOException;
import java.util.Map;

public class ConfigWindowController {

    @FXML
    private TextField boardSizeInput, timeLimitInput;

    @FXML
    private ComboBox<String> categoryDropdown;

    @FXML
    private CheckBox imagesModeCheckbox;

    private final Game game;
    private final PageManager pageManager;
    private final Key key;

    public ConfigWindowController() {
        this.game = Game.getInstance();
        this.pageManager = PageManager.getInstance();
        this.key = Key.getInstance();
    }

    @FXML
    public void initialize() {
        // Affecte et affiche les categories du JSON dans le dropdown
        try {
            Map<String, ?> categories = JsonReader.getCategories("mots.json");
            categoryDropdown.getItems().addAll(categories.keySet());
            categoryDropdown.setValue("Animaux");
        } catch (IOException e) {
            showError("Error dans le chargement des categories", "Incapable de charger les categories depuis mots.json.");
        }
    }

    @FXML
    private void onStartGame() {
        try {
            // Valider et affecter la taille du plateau
            int boardSize = Integer.parseInt(boardSizeInput.getText());
            if (boardSize < 3 || boardSize > 6) {
                throw new IllegalArgumentException("Le tableau doit avoir entre 3 et 6 colonnes et lignes.");
            }
    
            // Valider et affecter la categorie
            String selectedCategory = categoryDropdown.getValue();
            if (selectedCategory == null || selectedCategory.isEmpty()) {
                throw new IllegalArgumentException("Choisissez une categorie.");
            }

            // Valider et affecter la limite de temps
            String timeLimit = timeLimitInput.getText();
            System.out.println(!timeLimit.equals(timeLimit));
            System.out.println(!(isNumeric(timeLimit) && Integer.parseInt(timeLimit) >= 10));
            if (!timeLimit.equals("illimité") && !(isNumeric(timeLimit) && Integer.parseInt(timeLimit) >= 10)) {
                throw new IllegalArgumentException("Entrez une limite de temps valide. Elle doit valloir illimité ou un nombre entier supérieur à 10.");
            }

            // Valider et affecter le mode d'images
            boolean imagesMode = imagesModeCheckbox.isSelected();
    
            // Initialiser le jeu apres que les configurations sont choisies
            game.initializeGame(boardSize, selectedCategory, timeLimit, imagesMode);

            // Initialiser la key
            key.newKey();
    
            // Charger les autres fenetres
            pageManager.loadGuesserView();
            pageManager.loadSpyView();
    
            System.out.println("Le jeu commence avec un plateau de " + boardSize + "par " + boardSize + " et la categorie " + selectedCategory);
    
        } catch (NumberFormatException e) {
            showError("Saisie invalide", "La taille du plateau doit etre un entier.");
        } catch (IllegalArgumentException e) {
            showError("Saisie invalide", e.getMessage());
        } catch (Exception e) {
            showError("Erreur", "Une erreur inconnue s'est produite: " + e.getMessage());
        }
    }
    
    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
