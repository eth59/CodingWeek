package codingweek.models;

public class Card {

    // Attributes
    private String word; // Path to the image of the card
    private boolean isRevealed = false;
    private String color; // Color of the card (blue/red/neutral/assassin)

    // Constructor
    public Card(String word, String color){
        this.word = word;
        this.color = color;
    }

    // Getter
    public String getWord(){
        return this.word;
    }
    public String getColor(){
        return this.color;
    }

    // Setter
    public void setWord(String word){
        this.word = word;
    }
    public void setColor(String color){
        this.color = color;
    }
}
