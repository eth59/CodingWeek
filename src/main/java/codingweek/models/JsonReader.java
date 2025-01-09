package codingweek.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileWriter;
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
    @SuppressWarnings({"rawtypes", "unchecked"})
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

    // Méthode pour récupérer la liste des catégories du fichier JSON
    public static Map<String, Category> getCategories(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }

        Root root = objectMapper.readValue(inputStream, Root.class);
        return root.categories;
    }

    // Méthode pour ajouter un mot à une catégorie et réécrire le fichier JSON
    public static void addWordToCategory(String filePath, String categoryName, String newWord) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = JsonReader.class.getClassLoader().getResourceAsStream(filePath);

        if (inputStream == null) {
            throw new IllegalArgumentException("File not found: " + filePath);
        }

        Root root = objectMapper.readValue(inputStream, Root.class);

        // Récupérer la catégorie
        Category category = root.categories.get(categoryName);
        if (category != null) {
            // Créer un nouveau mot et l'ajouter à la catégorie
            Word word = new Word();
            word.mot = newWord;
            word.interdits = new ArrayList<>();
            word.chemin = ""; // Vous pouvez ajouter un chemin ici si nécessaire
            category.mots.add(word);

            // Réécrire les catégories dans le fichier JSON
            try (FileWriter fileWriter = new FileWriter(filePath)) {
            
                objectMapper.writeValue(fileWriter, root);
            }
        } else {
            throw new IllegalArgumentException("Category not found: " + categoryName);
        }
    }
}
