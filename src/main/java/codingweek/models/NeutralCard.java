package codingweek.models;

public class NeutralCard implements Card{

    // Attributes
    private String path; // Path to the image of the card

    // Constructor
    public NeutralCard(String path){
        this.path = path;
    }

    // Getter
    public String getPath(){
        return this.path;
    }
}
