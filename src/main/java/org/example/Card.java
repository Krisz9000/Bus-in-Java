package org.example;

public class Card {
    //Attributes
    private final Suits suit;
    private final Value value;

    //Getters
    public Suits getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public Card(Suits suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    //TODO maybe use the other constructor in this
    public Card(Deck deck) {
        Card newCard = deck.drawCard();
        this.suit = newCard.getSuit();
        this.value = newCard.getValue();
    }

    @Override
    public String toString() {
        return this.getValue().name() + " of " + this.getSuit().name();
    }
}
