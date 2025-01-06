package codingweek.models;

public class BlueCard implements Card{

    // Attributes
    private String path; // Path to the image of the card

    // Constructor
    public BlueCard(String path){
        this.path = path;
    }

    // Getter
    public String getPath(){
        return this.path;
    }
}
