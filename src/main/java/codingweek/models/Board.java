package codingweek.models;

import java.util.ArrayList;

public class Board {
    private ArrayList<Card> cards; // Doit être de taille boardSize * boardSize (25 par défaut)
    private Board instance;

    private Board() {
        this.cards = new ArrayList<Card>();
    }

    public Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }
    // Il y a pas forcément besoin de setCards et addCard, 
    public void addCard(Card card) {
        cards.add(card);
    }
}
