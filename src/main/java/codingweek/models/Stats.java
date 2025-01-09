package codingweek.models;

import java.io.Serializable;

public class Stats implements Serializable {
    private int blueTeamWins;
    private int redTeamWins;
    private int gamesLaunched;
    private int totalCorrectGuesses;

    // Constructor
    public Stats() {
        this.blueTeamWins = 0;
        this.redTeamWins = 0;
        this.gamesLaunched = 0;
        this.totalCorrectGuesses = 0;
    }

    // Increment the win count for the Blue Team
    public void incrementBlueTeamWins() {
        this.blueTeamWins++;
    }

    // Increment the win count for the Red Team
    public void incrementRedTeamWins() {
        this.redTeamWins++;
    }

    // Increment the number of games launched
    public void incrementGamesLaunched() {
        this.gamesLaunched++;
    }

    // Increment the total number of correct guesses
    public void addCorrectGuesses(int correctGuesses) {
        this.totalCorrectGuesses += correctGuesses;
        System.out.println("Total correct guesses: " + this.totalCorrectGuesses);
    }

    // Getters
    public int getBlueTeamWins() {
        return blueTeamWins;
    }

    public int getRedTeamWins() {
        return redTeamWins;
    }

    public int getGamesLaunched() {
        return gamesLaunched;
    }

    public int getTotalCorrectGuesses() {
        return totalCorrectGuesses;
    }

    @Override
    public String toString() {
        return String.format(
            "Stats:\nBlue Team Wins: %d\nRed Team Wins: %d\nGames Launched: %d\nTotal Correct Guesses: %d",
            blueTeamWins, redTeamWins, gamesLaunched, totalCorrectGuesses
        );
    }
}
