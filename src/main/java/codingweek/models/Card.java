package codingweek.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

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
        this.forbiddenWords = new ArrayList<String>(Arrays.asList("un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix", "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf", "vingt", "vingt-et-un", "vingt-deux", "vingt-trois", "vingt-quatre", "vingt-cinq", "vingt-six", "vingt-sept", "vingt-huit", "vingt-neuf", "trente", "trente-et-un", "trente-deux", "trente-trois", "trente-quatre", "trente-cinq", "trente-six", "trente-sept", "trente-huit", "trente-neuf", "quarante", "quarante-et-un", "quarante-deux", "quarante-trois", "quarante-quatre", "quarante-cinq", "quarante-six", "quarante-sept", "quarante-huit", "quarante-neuf", "cinquante", "cinquante-et-un", "cinquante-deux", "cinquante-trois", "cinquante-quatre", "cinquante-cinq", "cinquante-six", "cinquante-sept", "cinquante-huit", "cinquante-neuf", "soixante", "soixante-et-un", "soixante-deux", "soixante-trois", "soixante-quatre", "soixante-cinq", "soixante-six", "soixante-sept", "soixante-huit", "soixante-neuf", "soixante-dix", "soixante-et-onze", "soixante-douze", "soixante-treize", "soixante-quatorze", "soixante-quinze", "soixante-seize", "soixante-dix-sept", "soixante-dix-huit", "soixante-dix-neuf", "quatre-vingts", "quatre-vingt-un", "quatre-vingt-deux", "quatre-vingt-trois", "quatre-vingt-quatre", "quatre-vingt-cinq", "quatre-vingt-six", "quatre-vingt-sept", "quatre-vingt-huit", "quatre-vingt-neuf", "quatre-vingt-dix", "quatre-vingt-onze", "quatre-vingt-douze", "quatre-vingt-treize", "quatre-vingt-quatorze", "quatre-vingt-quinze", "quatre-vingt-seize", "quatre-vingt-dix-sept", "quatre-vingt-dix-huit", "quatre-vingt-dix-neuf", "cent"));
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
