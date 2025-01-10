package codingweek.controllers;

import codingweek.models.Stats;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class StatsController {

    @FXML
    private Label gamesLaunchedLabel;

    @FXML
    private Label blueTeamWinsLabel;

    @FXML
    private Label redTeamWinsLabel;

    @FXML
    private Label blueTeamClueSubmissionsLabel;

    @FXML
    private Label redTeamClueSubmissionsLabel;

    @FXML
    private ComboBox<String> dataSelector;

    @FXML
    private PieChart pieChart;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis categoryAxis;

    @FXML
    private NumberAxis numberAxis;

    private Stats stats;

    public void setStats(Stats stats) {
        this.stats = stats;
        updateStatsDisplay();
        setupDataSelector();
    }

    private void updateStatsDisplay() {
        if (stats == null) {
            System.err.println("Stats object is null. Unable to update display.");
            return;
        }

        // Update general stats labels
        if (gamesLaunchedLabel != null) {
            gamesLaunchedLabel.setText("Games Launched: " + stats.getGamesLaunched());
        }
        if (blueTeamWinsLabel != null) {
            blueTeamWinsLabel.setText("Blue Team Wins: " + stats.getBlueTeamWins());
        }
        if (redTeamWinsLabel != null) {
            redTeamWinsLabel.setText("Red Team Wins: " + stats.getRedTeamWins());
        }

        // Generate the phrase for Blue Team
        int blueTotalClues = stats.getBlueTeamClueStats().size();
        int blueCorrectGuesses = stats.getBlueTeamClueStats().stream()
            .mapToInt(clue -> clue.getCorrectGuesses()).sum();
        String bluePhrase = "For " + blueTotalClues + " clues given: " + blueCorrectGuesses + " correctly guessed";

        // Generate the phrase for Red Team
        int redTotalClues = stats.getRedTeamClueStats().size();
        int redCorrectGuesses = stats.getRedTeamClueStats().stream()
            .mapToInt(clue -> clue.getCorrectGuesses()).sum();
        String redPhrase = "For " + redTotalClues + " clues given: " + redCorrectGuesses + " correctly guessed";

        // Set the phrases in the labels
        if (blueTeamClueSubmissionsLabel != null) {
            blueTeamClueSubmissionsLabel.setText(bluePhrase);
        }
        if (redTeamClueSubmissionsLabel != null) {
            redTeamClueSubmissionsLabel.setText(redPhrase);
        }
    }

    private void setupDataSelector() {
        if (dataSelector == null) {
            System.err.println("Data selector ComboBox is null. Unable to initialize.");
            return;
        }

        // Populate dropdown with options
        dataSelector.setItems(FXCollections.observableArrayList(
            "Games Launched",
            "Team Wins",
            "Clues Given",
            "Correct Guesses"
        ));

        // Add listener to update charts dynamically
        dataSelector.valueProperty().addListener((observable, oldValue, newValue) -> updateChart(newValue));
    }

    private void updateChart(String selectedData) {
        if (selectedData == null || stats == null) {
            System.err.println("Invalid data selection or stats object is null.");
            return;
        }

        pieChart.setVisible(false);
        barChart.setVisible(false);

        switch (selectedData) {
            case "Games Launched":
                displayGamesLaunchedChart();
                break;
            case "Team Wins":
                displayTeamWinsChart();
                break;
            case "Clues Given":
                displayCluesGivenChart();
                break;
            case "Correct Guesses":
                displayCorrectGuessesChart();
                break;
            default:
                System.err.println("Unknown data selection: " + selectedData);
        }
    }

    private void displayGamesLaunchedChart() {
        pieChart.setData(FXCollections.observableArrayList(
            new PieChart.Data("Games Launched", stats.getGamesLaunched())
        ));
        pieChart.setVisible(true);
    }

    private void displayTeamWinsChart() {
        barChart.getData().clear();
        var series = new BarChart.Series<String, Number>();
        series.setName("Team Wins");
        series.getData().add(new BarChart.Data<>("Blue Team", stats.getBlueTeamWins()));
        series.getData().add(new BarChart.Data<>("Red Team", stats.getRedTeamWins()));
        barChart.getData().add(series);
        barChart.setVisible(true);
    }

    private void displayCluesGivenChart() {
        barChart.getData().clear();
        var series = new BarChart.Series<String, Number>();
        series.setName("Clues Given");
        series.getData().add(new BarChart.Data<>("Blue Team", stats.getBlueTeamClueSubmissions()));
        series.getData().add(new BarChart.Data<>("Red Team", stats.getRedTeamClueSubmissions()));
        barChart.getData().add(series);
        barChart.setVisible(true);
    }

    private void displayCorrectGuessesChart() {
        pieChart.getData().clear();

        // Calculate total clues and correct guesses for Blue Team
        int blueTotalClues = stats.getBlueTeamClueStats().size();
        int blueCorrectGuesses = stats.getBlueTeamClueStats().stream()
            .mapToInt(clue -> clue.getCorrectGuesses()).sum();

        // Calculate total clues and correct guesses for Red Team
        int redTotalClues = stats.getRedTeamClueStats().size();
        int redCorrectGuesses = stats.getRedTeamClueStats().stream()
            .mapToInt(clue -> clue.getCorrectGuesses()).sum();

        // Add data to the PieChart for Blue Team
        if (blueTotalClues > 0) {
            pieChart.getData().add(new PieChart.Data("Blue Team Correct: " + blueCorrectGuesses, blueCorrectGuesses));
            pieChart.getData().add(new PieChart.Data("Blue Team Incorrect: " + (blueTotalClues - blueCorrectGuesses), 
                Math.max(0, blueTotalClues - blueCorrectGuesses)));
        }

        // Add data to the PieChart for Red Team
        if (redTotalClues > 0) {
            pieChart.getData().add(new PieChart.Data("Red Team Correct: " + redCorrectGuesses, redCorrectGuesses));
            pieChart.getData().add(new PieChart.Data("Red Team Incorrect: " + (redTotalClues - redCorrectGuesses), 
                Math.max(0, redTotalClues - redCorrectGuesses)));
        }

        pieChart.setVisible(true);
    }
}
