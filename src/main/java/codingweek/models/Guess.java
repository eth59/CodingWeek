package codingweek.models;

public class Guess {
    private String clue;
    private int nbWords; // Le nom est pas fou

    public Guess(String clue, int nbWords) {
        this.clue = clue;
        this.nbWords = nbWords;
    }

    public String getClue() {
        return clue;
    }

    public int getNbWords() {
        return nbWords;
    }
}
