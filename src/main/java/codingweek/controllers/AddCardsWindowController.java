package codingweek.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import codingweek.models.JsonReader;
import codingweek.models.PageManager;

public class AddCardsWindowController {

    @FXML
    private Button returnMenuButton;

    @FXML
    private ComboBox<String> categoryDropdown;

    @FXML
    private TextField newCard;

    private PageManager pageManager;

    @FXML
    private void initialize() {
        pageManager = PageManager.getInstance();

        returnMenuButton.setOnAction(e -> {
            System.out.println("Le bouton Retour menu a été cliqué !");
            pageManager.loadMenuWindowView();
        });

        try {
            // Charger les catégories à partir du fichier JSON
            Map<String, ?> categories = JsonReader.getCategories("mots.json");
            for (String categoryName : categories.keySet()) {
                categoryDropdown.getItems().add(categoryName);
            }
            categoryDropdown.setValue("Animaux");
        } catch (IOException e) {
            showError("Erreur dans le chargement des catégories", "Incapable de charger les catégories depuis mots.json.");
        }
    }

    @FXML
    private void addCardToCategory() {
        String selectedCategory = categoryDropdown.getValue();
        String newCardText = newCard.getText().trim();

        if (selectedCategory == null || newCardText.isEmpty()) {
            showError("Erreur de saisie", "Veuillez sélectionner une catégorie et entrer un mot.");
            return;
        }

        try {
            // Ajouter le mot à la catégorie
            JsonReader.addWordToCategory("mots.json", selectedCategory, newCardText);

            // Afficher un message de succès
            showSuccess("Succès", "Le mot a été ajouté avec succès à la catégorie " + selectedCategory + ".");
            newCard.clear();
        } catch (IOException e) {
            showError("Erreur de fichier", "Impossible de lire ou écrire dans le fichier JSON.");
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showSuccess(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
