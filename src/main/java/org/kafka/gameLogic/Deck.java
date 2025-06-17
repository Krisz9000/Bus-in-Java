package org.kafka.gameLogic;

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
     * @return All the {@code Cards} this {@code Deck} should have when full
     */
    public ArrayList<Card> getCardsInDeck() {
        return cardsInDeck;
    }

    /**
     * @return All the {@code Cards} that are still left in this {@code Deck}
     */
    public ArrayList<Card> getCardsLeftInDeck() {
        return cardsLeftInDeck;
    }

    /**
     * Takes the first (uppermost) card from this deck, then removes it from this deck
     *
     * @return A {@code Card} object, retrieved from the list of cards in this deck
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
    //TODO Does not take into account cards that are currently in the hands of players
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
     * Clears the {@code Deck} using {@code ArrayList<>.clear()} however this
     * only empties the list of cards that should be in the deck.<br>Cards that are still
     * in its {@code cardsLeftInDeck} can still be drawn.<p>
     * If you want to get rid of the drawable cards, see {@code emptyDeck()} instead.
     * @see Deck#emptyDeck()
     */
    public void clearDeck() {
        cardsInDeck.clear();
    }

    /**
     * Empties the {@code Deck} by clearing its {@code cardsLeftInDeck} using
     * {@code ArrayList.clear()}.<p>After this, a draw attempt will result in a reshuffle
     * before drawing a new card due to the implementation of {@link Deck#drawCard()}.<p>
     * If you want to remove the Cards that are supposed to be part of the Deck,
     * see {@link Deck#clearDeck()} instead.
     */
    public void emptyDeck() {
        cardsLeftInDeck.clear();
    }

    /**
     * Adds a Card to this Deck. <br>
     * The Card is added to the "bottom" of the Deck, also known
     * as the last element of the Array. <p>
     * Does not shuffle the Deck afterward.
     *
     * @param card The Card to add to the Deck.
     */
    public void addCard(Card card) {
        this.cardsInDeck.add(card);
        this.cardsLeftInDeck.add(card);
    }

    /**
     * Adds a Card to this Deck specified by its suit and value. <br>
     * The Card is added to the "bottom" of the Deck, also known
     * as the last element of the Array. <p>
     * Does not shuffle the Deck afterward.
     *
     * @param s The Suit of the Card to be added. {@link Suits}
     * @param v The Value of the Card to be added. {@link Value}
     */
    public void addCard(Suits s, Value v) {
        Card card = new Card(s, v);
        this.addCard(card);
    }
}
