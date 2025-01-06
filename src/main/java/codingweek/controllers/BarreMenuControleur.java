package codingweek.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

import codingweek.Main;

public class BarreMenuControleur {

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
        try {
            // Fermer la fenêtre actuelle
            Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
            currentStage.close(); // Ferme la fenêtre actuelle

            // Charger la scène du menu
            Main mainApp = new Main();
            mainApp.start(new Stage());  // Créer une nouvelle scène et l'afficher
        } catch (Exception e) {
            e.printStackTrace();
        }
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