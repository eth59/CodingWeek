package codingweek.models;

public class BasicCard implements Card{

    // Attributes
    private String word; // Path to the image of the card

    // Constructor
    public BasicCard(String word){
        this.word = word;
    }

    // Getter
    public String getWord(){
        return this.word;
    }

    // Setter
    public void setWord(String word){
        this.word = word;
    }
}
