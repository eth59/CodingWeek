package codingweek.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Card implements Serializable{

    // Attributes
    private String word;
    private ArrayList<String> forbiddenWords;
    private boolean isRevealed = false;
    private String color; // Color of the card (red, blue, neutral)

    // Predefined colors with their hash codes
    public static final String RED_COLOR = "#c1121f";
    public static final String BLUE_COLOR = "#003566";
    public static final String NEUTRAL_COLOR = "#f0ead2";

    // Constructor
    public Card(String word, String color) {
        this.forbiddenWords = new ArrayList<String>();
        this.word = word;
        this.color = color;
    }

    // Getter for word
    public String getWord() {
        return this.word;
    }

    // Getter for color
    public String getColor() {
        return this.color;
    }

    // Getter for isRevealed
    public boolean isRevealed() {
        return this.isRevealed;
    }

    // Setter for word
    public void setWord(String word) {
        this.word = word;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // Setter for isRevealed
    public void setRevealed(boolean revealed) {
        this.isRevealed = revealed;
    }

    // Getter for forbiddenWords
    public ArrayList<String> getForbiddenWords() {
        return this.forbiddenWords;
    }

    // Adder for forbiddenWords
    public void addForbiddenWords(ArrayList<String> forbiddenWords) {
        this.forbiddenWords.addAll(forbiddenWords);
    }
}
