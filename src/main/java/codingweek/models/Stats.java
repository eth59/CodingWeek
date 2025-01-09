package codingweek.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Stats implements Serializable {
    private int blueTeamWins;
    private int redTeamWins;
    private int gamesLaunched;
    private int totalCorrectGuesses;
    private int blueTeamClueSubmissions;
    private int redTeamClueSubmissions;

    private Map<Integer, ClueStats> blueTeamClueStats;
    private Map<Integer, ClueStats> redTeamClueStats;

    public Stats() {
        this.blueTeamWins = 0;
        this.redTeamWins = 0;
        this.gamesLaunched = 0;
        this.totalCorrectGuesses = 0;
        this.blueTeamClueSubmissions = 0;
        this.redTeamClueSubmissions = 0;
        this.blueTeamClueStats = new HashMap<>();
        this.redTeamClueStats = new HashMap<>();
    }

    public void incrementBlueTeamWins() {
        this.blueTeamWins++;
    }

    public void incrementRedTeamWins() {
        this.redTeamWins++;
    }

    public void incrementGamesLaunched() {
        this.gamesLaunched++;
    }

    public void addCorrectGuesses(int correctGuesses) {
        this.totalCorrectGuesses += correctGuesses;
    }

    public void addBlueTeamClue(int clueNb, int correctGuesses) {
        this.blueTeamClueSubmissions++;
        blueTeamClueStats.put(clueNb, new ClueStats(clueNb, correctGuesses));
    }

    public void addRedTeamClue(int clueNb, int correctGuesses) {
        this.redTeamClueSubmissions++;
        redTeamClueStats.put(clueNb, new ClueStats(clueNb, correctGuesses));
    }

    public void updateBlueTeamCorrectGuesses(int clueNb, int correctGuesses) {
        blueTeamClueStats.computeIfPresent(clueNb, (key, stats) -> {
            stats.setCorrectGuesses(correctGuesses);
            return stats;
        });
    }

    public void updateRedTeamCorrectGuesses(int clueNb, int correctGuesses) {
        redTeamClueStats.computeIfPresent(clueNb, (key, stats) -> {
            stats.setCorrectGuesses(correctGuesses);
            return stats;
        });
    }

    @Override
    public String toString() {
        return "Stats:\n" +
                "Blue Team Wins: " + blueTeamWins + "\n" +
                "Red Team Wins: " + redTeamWins + "\n" +
                "Games Launched: " + gamesLaunched + "\n" +
                "Total Correct Guesses: " + totalCorrectGuesses + "\n" +
                "Blue Team Clue Submissions: " + blueTeamClueSubmissions + "\n" +
                "Red Team Clue Submissions: " + redTeamClueSubmissions + "\n" +
                "Blue Team Clue Stats: " + blueTeamClueStats.values() + "\n" +
                "Red Team Clue Stats: " + redTeamClueStats.values();
    }

    public static class ClueStats {
        private int clueNb;
        private int correctGuesses;

        public ClueStats(int clueNb, int correctGuesses) {
            this.clueNb = clueNb;
            this.correctGuesses = correctGuesses;
        }

        public int getClueNb() {
            return clueNb;
        }

        public int getCorrectGuesses() {
            return correctGuesses;
        }

        public void setCorrectGuesses(int correctGuesses) {
            this.correctGuesses = correctGuesses;
        }

        @Override
        public String toString() {
            return "Clue " + clueNb + ": " + correctGuesses + " correct guesses";
        }
    }
}
