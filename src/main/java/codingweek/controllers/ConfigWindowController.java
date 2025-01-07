package codingweek.controllers;

import codingweek.models.PageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class ConfigWindowController {

    // Attributes
    @FXML
    private ToggleGroup myToggleGroup;
    @FXML
    private RadioButton theme1, theme2, theme3;
    @FXML
    private Button quitButton;
    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Button playButton;

    private PageManager pageManager;

    @FXML
    private void initialize() {
        toggleButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                System.out.println("ToggleButton est enclenché !");
            } else {
                System.out.println("ToggleButton est relâché !");
            }
        });

        myToggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            // newToggle est le Toggle (RadioButton) sélectionné
            if (newToggle == theme1) {
                System.out.println("Thème 1 sélectionné");
            }
            else if (newToggle == theme2) {
                System.out.println("Thème 2 sélectionné");
            }
            else if (newToggle == theme3) {
                System.out.println("Thème 3 sélectionné");
            }
        });

        quitButton.setOnAction(e -> {
            System.out.println("Le bouton Quit a été cliqué !");
            System.exit(0);
        });

        playButton.setOnAction(e -> {
            System.out.println("Le bouton Play a été cliqué !");
            pageManager.loadGuesserView();
            pageManager.loadSpyView();
        });
    }


}

