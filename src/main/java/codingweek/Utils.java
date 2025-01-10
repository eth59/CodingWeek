package codingweek;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Utils {
    // Classe utilitaire pour les fonctions communes à plusieurs classes

    public static int saveConfirmation() {
        // Confirmation de sauvegarde

        // Création de la fenêtre de confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Voulez-vous enregistrer l'album actuel ?");
        alert.setHeaderText(null);
        alert.setGraphic(null);
        alert.setContentText("Si vous ne l'enregistrez pas, vous perdrez les modifications apportées à l'album actuel.");

        // Ajout des boutons
        ButtonType saveButton = new ButtonType("Enregistrer");
        ButtonType dontSaveButton = new ButtonType("Ne pas enregistrer");
        ButtonType cancelButton = new ButtonType("Annuler");
        alert.getButtonTypes().setAll(saveButton, dontSaveButton, cancelButton);

        // Affichage de la fenêtre et récupération du bouton cliqué
        ButtonType result = alert.showAndWait().orElse(cancelButton);
        if (result == saveButton) {
            return 0;
        } else if (result == dontSaveButton) {
            return 1;
        } else {
            return 2;
        }
    }
}
