package codingweek.models;
import codingweek.Observer;
import java.util.ArrayList;
import java.io.Serializable;

public class Card implements Serializable, Observer {

    // Attributes
    private String word;
    private boolean isRevealed = false;
    private String color; // Color of the card (red, blue, neutral)
    private ArrayList<String> forbiddenWords;

    // Predefined colors with their hash codes
    public static final String RED_COLOR = "#c1121f";
    public static final String BLUE_COLOR = "#003566";
    public static final String NEUTRAL_COLOR = "#f0ead2";

    // Constructor
    public Card(String word, String color) {
        this.forbiddenWords = new ArrayList<>();
        this.word = word;
        this.color = color;
    }

    // Observer method to update the card's color
    @Override
    public void reagir() {
        // Ensure the card retains its assigned color
        System.out.println("Card '" + word + "' reacting: Color is " + this.color);
    }

    // Getters and Setters
    public String getWord() { return this.word; }
    public String getColor() { return this.color; }
    public boolean isRevealed() { return this.isRevealed; }
    public void setWord(String word) { this.word = word; }
    public void setColor(String color) { this.color = color; }
    public void setRevealed(boolean revealed) { this.isRevealed = revealed; }
    public ArrayList<String> getForbiddenWords() { return this.forbiddenWords; }
    public void addForbiddenWords(ArrayList<String> forbiddenWords) { this.forbiddenWords.addAll(forbiddenWords); }
}
