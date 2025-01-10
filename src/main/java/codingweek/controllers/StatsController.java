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
import javafx.scene.control.ListView;

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
    private ListView<String> blueTeamClueStatsList;

    @FXML
    private ListView<String> redTeamClueStatsList;

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
        gamesLaunchedLabel.setText("Games Launched: " + stats.getGamesLaunched());
        blueTeamWinsLabel.setText("Blue Team Wins: " + stats.getBlueTeamWins());
        redTeamWinsLabel.setText("Red Team Wins: " + stats.getRedTeamWins());
        blueTeamClueSubmissionsLabel.setText("Blue Team Clues Given: " + stats.getBlueTeamClueSubmissions());
        redTeamClueSubmissionsLabel.setText("Red Team Clues Given: " + stats.getRedTeamClueSubmissions());

        // Populate Blue Team clue details
        blueTeamClueStatsList.getItems().clear();
        stats.getBlueTeamClueStats().forEach(clue -> {
            blueTeamClueStatsList.getItems().add("For " + clue.getClueNb() + " clue(s) given : " + clue.getCorrectGuesses() + " correct guesses");
        });

        // Populate Red Team clue details
        redTeamClueStatsList.getItems().clear();
        stats.getRedTeamClueStats().forEach(clue -> {
            redTeamClueStatsList.getItems().add("For " + clue.getClueNb() + " clue(s) given : " + clue.getCorrectGuesses() + " correct guesses");
        });
    }

    private void setupDataSelector() {
        dataSelector.setItems(FXCollections.observableArrayList(
            "Games Launched",
            "Team Wins",
            "Clues Given",
            "Correct Guesses"
        ));
        dataSelector.valueProperty().addListener((observable, oldValue, newValue) -> updateChart(newValue));
    }

    private void updateChart(String selectedData) {
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
        stats.getBlueTeamClueStats().forEach(clue -> {
            series.getData().add(new BarChart.Data<>("Blue Clue " + clue.getClueNb(), clue.getCorrectGuesses()));
        });
        stats.getRedTeamClueStats().forEach(clue -> {
            series.getData().add(new BarChart.Data<>("Red Clue " + clue.getClueNb(), clue.getCorrectGuesses()));
        });
        barChart.getData().add(series);
        barChart.setVisible(true);
    }

    private void displayCorrectGuessesChart() {
        pieChart.getData().clear();
        stats.getBlueTeamClueStats().forEach(clue -> {
            pieChart.getData().add(new PieChart.Data("Blue Clue " + clue.getClueNb(), clue.getCorrectGuesses()));
        });
        stats.getRedTeamClueStats().forEach(clue -> {
            pieChart.getData().add(new PieChart.Data("Red Clue " + clue.getClueNb(), clue.getCorrectGuesses()));
        });
        pieChart.setVisible(true);
    }
}
