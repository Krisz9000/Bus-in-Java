package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cardsInDeck = new ArrayList<>();

    public Deck() {
        for (Value v : Value.values()) {
            for (Suits s : Suits.values()) {
                this.cardsInDeck.add(new Card(s, v));
            }
        }
    }

    public Card drawCard() {
        Card drawnCard = cardsInDeck.get(0);
        cardsInDeck.remove(0);
        if (cardsInDeck.isEmpty()) {
            cardsInDeck = new Deck().cardsInDeck;
        }
        return drawnCard;
    }

    public void printDeck() {
        cardsInDeck.forEach((c) -> System.out.println(c.toString()));
    }

    public Deck reshuffle() {
        Deck newDeck = new Deck();
        newDeck.shuffle();
        return newDeck;
    }

    public void shuffle() {
        Collections.shuffle(cardsInDeck);
    }
}
