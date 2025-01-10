package codingweek.models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.stage.FileChooser;

public class GameSave implements Serializable {
    private Game game;
    private Key key;
    private static GameSave instance;

    private GameSave() {
        game = Game.getInstance();
        key = Key.getInstance();
    }

    public static GameSave getInstance() {
        if (instance == null) {
            instance = new GameSave();
        }
        return instance;
    }

    public void saveGame() {
        // Sauvegarde de la partie dans un fichier .game

        // On ouvre un FileChooser pour choisir le fichier de sauvegarde
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Sauvegarder la partie");

        // On ajoute un filtre pour n'afficher que les fichiers .game
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Game", "*.game"));

        // On ouvre le FileChooser dans le répertoire du .jar
        String userDirectory = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userDirectory));


        // On propose un nom de fichier par défaut
        fileChooser.setInitialFileName("partie.game");

        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                oos.writeObject(this);
                game.setSaved(true);
            } catch (Exception e) {
                System.err.println("Erreur lors de la sauvegarde de la partie : " + e.getMessage());
            }
        } else {
            System.err.println("Aucun fichier sélectionné.");
        }
    }

    public void loadGame() {
        // On ouvre un FileChooser pour sélectionner un fichier .game
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger une partie");

        // On filtre pour n'avoir que les fichiers .game
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers Game", "*.game"));

        // On propose le répertoire courant comme le répertoire du jar
        String userDirectory = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userDirectory));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            // Chargement de la partie depuis le fichier .game
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                GameSave loadedGame = (GameSave) ois.readObject();
                this.key.grille = loadedGame.key.grille;
                this.game.loadGame(loadedGame.game);
            } catch (Exception e) {
                System.err.println("Erreur lors du chargement de la partie : " + e.getMessage());
            }
        } else {
            System.err.println("Aucun fichier sélectionné.");
        }
    }

}
