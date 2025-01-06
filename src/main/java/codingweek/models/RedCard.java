package codingweek.models;

public class RedCard implements Card{

    // Attributes
    private String path; // Path to the image of the card

    // Constructor
    public RedCard(String path){
        this.path = path;
    }

    // Getter
    public String getPath(){
        return this.path;
    }
}
