package codingweek.models;

public class BlackCard implements Card{

    // Attributes
    private String path; // Path to the image of the card

    // Constructor
    public BlackCard(String path){
        this.path = path;
    }

    // Getter
    public String getPath(){
        return this.path;
    }
}
