package codingweek.controllers;

import codingweek.models.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GuesserViewController implements codingweek.Observer {
    @FXML
    Button btnTurn;

    @FXML
    private GuesserBoardController guesserBoardController;

    @FXML
    private ImageView sandTimerView;

    @FXML
    private Label timeLabel;

    private Game game;

    private static final int FRAME_COUNT = 11; // Nombre de frames dans le sprite
    private Image[] blueFrames;
    private Image[] redFrames;
    private int currentFrame = 0;
    private int timeRemaining;
    private Timeline animationTimeline, countdownTimeline;

    public void initialize() {
        game = Game.getInstance();
        game.ajouterObservateur(this);

        timeRemaining = game.getTimeLimit();
        loadFrames();
        sandTimerView.setPreserveRatio(true); 
        sandTimerView.setVisible(false);

        btnTurn.setOnAction(e -> {
            turn();
        });
        btnTurn.setDisable(true);
    }

    public void reagir() {
        if (game.getNbCardsReturned() < 1) {
            btnTurn.setDisable(true);
        } else {
            btnTurn.setDisable(false);
        }
        if (game.isTimerRunning()) {
            sandTimerView.setVisible(true);
            startAnimationAndCountdown();
        } else {
            sandTimerView.setVisible(false);
        }
    }

    private void loadFrames() {
        blueFrames = new Image[FRAME_COUNT];
        for (int i = 0; i < FRAME_COUNT; i++) {
            System.out.println("/Images/hourglass_blue/frame_" + String.format("%02d", i+1) + ".png"); 
            blueFrames[i] = new Image(getClass().getResourceAsStream("/Images/hourglass_blue/frame_" + String.format("%02d", i+1) + ".png"));
        }
        sandTimerView.setImage(blueFrames[0]);

        redFrames = new Image[FRAME_COUNT];
        for (int i = 0; i < FRAME_COUNT; i++) {
            System.out.println("/Images/hourglass_red/frame_" + String.format("%02d", i+1) + ".png"); 
            redFrames[i] = new Image(getClass().getResourceAsStream("/Images/hourglass_red/frame_" + String.format("%02d", i+1) + ".png"));
        }
        sandTimerView.setImage(redFrames[0]);
    }

    public void startAnimationAndCountdown() {
        animationTimeline = new Timeline(
            new KeyFrame(Duration.seconds((double) game.getTimeLimit() / FRAME_COUNT), event -> {
                if (game.isBlueTurn()) {
                    sandTimerView.setImage(blueFrames[currentFrame]);
                } else {
                    sandTimerView.setImage(redFrames[currentFrame]);
                }
                currentFrame = (currentFrame + 1) % FRAME_COUNT;
            })
        );
        animationTimeline.setCycleCount(FRAME_COUNT);
        animationTimeline.play();

        countdownTimeline = new Timeline(
            new KeyFrame(Duration.seconds(1), event -> {
                timeRemaining--;
                timeLabel.setText("Temps restant : " + String.valueOf(timeRemaining));
                if (timeRemaining <= 0) {
                    animationTimeline.stop();
                    countdownTimeline.stop();
                    game.changeTurn();
                }
            })
        );
        countdownTimeline.setCycleCount(timeRemaining);
        countdownTimeline.play();
    }

    private void turn() {
        if (!game.isSpyTurn()) {
            game.changeTurn();
        }
    }

}
