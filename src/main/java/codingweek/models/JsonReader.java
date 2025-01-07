package codingweek.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class Root {
    @JsonProperty("categories")
    public Map<String, Category> categories;
}

class Category {
    @JsonProperty("mots")
    public List<Word> mots;
}

class Word {
    @JsonProperty("mot")
    public String mot;

    @JsonProperty("interdits")
    public List<String> interdits;

    @JsonProperty("chemin")
    public String chemin;
}

public class JsonReader {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static ArrayList<Card> jsonReader(String filePath, String category) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }

        Root root = objectMapper.readValue(inputStream, Root.class);

        ArrayList<Card> cardList = new ArrayList<>();
        Card card;
        Category cat = root.categories.get(category);
        if (cat != null) {
            for (Word word : cat.mots) {
                card = new Card(word.mot, Card.NEUTRAL_COLOR);
                card.addForbiddenWords((ArrayList) word.interdits);
                cardList.add(card);
            }
        } else {
            throw new IllegalArgumentException("Category not found: " + category);
        }
        return cardList;
    }
}
