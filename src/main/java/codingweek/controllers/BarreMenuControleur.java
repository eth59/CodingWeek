package codingweek.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import codingweek.models.PageManager;

public class BarreMenuControleur {

    private PageManager pageManager;

    public void initialize() {
        pageManager = PageManager.getInstance();
    }

    @FXML
    private void Charger(ActionEvent event) {
        showNotImplementedError("Charger");
    }

    @FXML
    private void Sauvegarder(ActionEvent event) {
        showNotImplementedError("Sauvegarder");
    }

    @FXML
    private void RetourAcceuil(ActionEvent event) {
        pageManager.loadMenuWindowView();
        pageManager.closeSpyView();
    }

    // Méthode utilitaire pour afficher une erreur
    private void showNotImplementedError(String actionName) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fonctionnalité non implémentée");
        alert.setHeaderText("Action \"" + actionName + "\" non disponible");
        alert.setContentText("Cette fonctionnalité n'a pas encore été implémentée.");
        alert.showAndWait();
    }
}