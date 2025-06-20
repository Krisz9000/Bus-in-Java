package org.kafka.gameLogic;

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

    @Override
    public String toString() {
        return this.getValue().name() + " of " + this.getSuit().name();
    }
}
