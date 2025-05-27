package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private final ArrayList<Card> cardsInDeck = new ArrayList<>();

    public Deck() {
        for (Value v : Value.values()) {
            for (Suits s : Suits.values()) {
                this.cardsInDeck.add(new Card(s, v));
            }
        }
    }

    public Card drawCard() {
        Random random = new Random();
        int drawnCardIndex = random.nextInt(0, cardsInDeck.size());
        Card drawnCard = cardsInDeck.get(drawnCardIndex);
        cardsInDeck.remove(drawnCardIndex);
        return drawnCard;
    }

    public void printDeck() {
        cardsInDeck.forEach((c) -> System.out.println(c.toString()));
    }

}
