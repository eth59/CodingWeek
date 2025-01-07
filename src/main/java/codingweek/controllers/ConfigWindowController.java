package codingweek.controllers;

import codingweek.models.PageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class ConfigWindowController {

    // Attributes
    private ToggleGroup myToggleGroup1 = new ToggleGroup();
    private ToggleGroup myToggleGroup2 = new ToggleGroup();
    @FXML
    private RadioButton theme1, theme2, theme3;
    @FXML
    private RadioButton mode1, mode2;
    @FXML
    private Button quitButton;

    @FXML
    private ToggleButton toggleButton;
    @FXML
    private Button playButton;

    private PageManager pageManager;

    public ConfigWindowController() {
        pageManager = PageManager.getInstance();
    }

    @FXML
    private void initialize() {

        playButton.setOnAction(e -> {
            System.out.println("Le bouton Play a été cliqué !");
            pageManager.loadGuesserView();
            pageManager.loadSpyView();
        });

        toggleButton.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                System.out.println("ToggleButton est enclenché !");
            } else {
                System.out.println("ToggleButton est relâché !");
            }
        });

        theme1.setToggleGroup(myToggleGroup1);
        theme2.setToggleGroup(myToggleGroup1);
        theme3.setToggleGroup(myToggleGroup1);

        myToggleGroup1.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
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

        mode1.setToggleGroup(myToggleGroup2);
        mode2.setToggleGroup(myToggleGroup2);

        myToggleGroup2.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
            // newToggle est le Toggle (RadioButton) sélectionné
            if (newToggle == mode1) {
                System.out.println("Mode 'mots' sélectionné");
            }
            else if (newToggle == mode2) {
                System.out.println("Mode 'images' sélectionné");
            }
        });

        quitButton.setOnAction(e -> {
            System.out.println("Le bouton Quit a été cliqué !");
            System.exit(0);
        });

    }


}

