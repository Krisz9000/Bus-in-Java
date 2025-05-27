package org.example;

import java.util.ArrayList;

public class Player {
    private final int ID;
    private ArrayList<Card> drawnCards = new ArrayList<>();

    public ArrayList<Card> getDrawnCards() {
        return drawnCards;
    }

    //TODO sort out ID to be unique
    public Player(int ID, ArrayList<Card> drawnCards) {
        this.ID = ID;
        this.drawnCards = drawnCards;
    }

    public Player(int ID) {
        this.ID = ID;
    }

    public boolean drawCard(Deck deck) {
        return drawnCards.add(deck.drawCard());
    }
}
