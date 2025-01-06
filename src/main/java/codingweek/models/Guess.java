package codingweek.models;

public class Guess {
    private String hint;
    private int nbWords; // Le nom est pas fou

    public Guess(String hint, int nbWords) {
        this.hint = hint;
        this.nbWords = nbWords;
    }

    public String getHint() {
        return hint;
    }

    public int getNbWords() {
        return nbWords;
    }
}
