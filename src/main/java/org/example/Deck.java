package org.example;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private final ArrayList<Card> cardsInDeck = new ArrayList<>();
    private ArrayList<Card> cardsLeftInDeck;

    public Deck() {
        for (Value v : Value.values()) {
            for (Suits s : Suits.values()) {
                this.cardsInDeck.add(new Card(s, v));
            }
        }
        this.cardsLeftInDeck = this.cardsInDeck;
    }

    /**
     * @return All the {@code Cards} the {@code Deck} should have when full
     */
    public ArrayList<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    /**
     * @return All the {@code Cards} that are still left in the {@code Deck}
     */
    public ArrayList<Card> getCardsLeftInDeck() {
        return cardsLeftInDeck;
    }

    /**
     * Takes the first (uppermost) card from the deck, then removes it from the deck
     *
     * @return A {@code Card} object, retrieved from the list of cards in the deck
     */
    public Card drawCard() {
        Card drawnCard = cardsLeftInDeck.getFirst();
        cardsLeftInDeck.removeFirst();
        if (cardsLeftInDeck.isEmpty()) {
            System.out.println("NOTE: The deck has been reshuffled because it was empty.");
            this.reshuffle();
        }
        return drawnCard;
    }

    /**
     * Debug function, prints out the list of cards in the deck
     */
    public void printDeck() {
        cardsInDeck.forEach((c) -> System.out.println(c.toString()));
    }

    /**
     * Puts all the {@code Cards} back into the Deck and shuffles it
     */
    public void reshuffle() {
        cardsLeftInDeck = cardsInDeck;
        Collections.shuffle(cardsLeftInDeck);
    }

    /**
     * Shuffles all the {@code Cards} in the {@code Deck}, that are still left in
     * it using {@code Collections.shuffle()}
     */
    public void shuffle() {
        Collections.shuffle(cardsLeftInDeck);
    }

    /**
     * Empties the {@code Deck} using {@code ArrayList<>.clear()}
     */
    public void clearDeck() {
        cardsInDeck.clear();
    }
}
