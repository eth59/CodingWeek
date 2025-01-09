package codingweek.controllers;

import codingweek.models.Stats;
import codingweek.models.Stats.ClueStats;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class StatsController {

    @FXML
    private Label blueTeamWinsLabel;

    @FXML
    private Label redTeamWinsLabel;

    @FXML
    private Label gamesLaunchedLabel;

    @FXML
    private Label totalCorrectGuessesLabel;

    @FXML
    private Label blueTeamClueSubmissionsLabel;

    @FXML
    private Label redTeamClueSubmissionsLabel;

    @FXML
    private ListView<String> blueTeamClueStatsList;

    @FXML
    private ListView<String> redTeamClueStatsList;

    private Stats stats;

    public void setStats(Stats stats) {
        this.stats = stats;
        updateStatsDisplay();
    }

    private void updateStatsDisplay() {
        // Update labels
        blueTeamWinsLabel.setText("Wins: " + stats.getBlueTeamWins());
        redTeamWinsLabel.setText("Wins: " + stats.getRedTeamWins());
        gamesLaunchedLabel.setText("Games Launched: " + stats.getGamesLaunched());
        totalCorrectGuessesLabel.setText("Total Correct Guesses: " + stats.getTotalCorrectGuesses());
        blueTeamClueSubmissionsLabel.setText("Clue Submissions: " + stats.getBlueTeamClueSubmissions());
        redTeamClueSubmissionsLabel.setText("Clue Submissions: " + stats.getRedTeamClueSubmissions());

        // Update list views
        blueTeamClueStatsList.getItems().clear();
        for (ClueStats clue : stats.getBlueTeamClueStats()) {
            blueTeamClueStatsList.getItems().add(clue.toString());
        }

        redTeamClueStatsList.getItems().clear();
        for (ClueStats clue : stats.getRedTeamClueStats()) {
            redTeamClueStatsList.getItems().add(clue.toString());
        }
    }
}
