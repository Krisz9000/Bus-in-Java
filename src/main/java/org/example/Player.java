package org.example;

import java.util.ArrayList;

public class Player {
    private final int ID;
    private ArrayList<Card> drawnCards = new ArrayList<>();

    public ArrayList<Card> getDrawnCards() {
        return drawnCards;
    }

    public Player(int ID, ArrayList<Card> drawnCards) {
        this.ID = ID;
        this.drawnCards = drawnCards;
    }

    public Player(int ID) {
        this.ID = ID;
    }

    /**
     * Draws a {@code Card} from a {@code Deck} and adds it to player`s hand.
     *
     * @param deck The {@code Deck} the card should be drawn from.
     */
    public void drawCard(Deck deck) {
        drawnCards.add(deck.drawCard());
    }

    /**
     * Using a {@code StringBuilder}, build a single line string out of all the cards the player currently has drawn.
     *
     * @return A concatenated {@code String} of all the player`s cards.
     */
    public String printDrawnCards() {
        StringBuilder sb = new StringBuilder();
        for (Card c : this.drawnCards) {
            sb.append(c.toString());
            sb.append(", ");
        }
        return sb.toString();
    }

    /**
     * Empties the player's hand using {@code ArrayList<>.clear()}
     */
    public void clearHand() {
        this.drawnCards.clear();
    }
}
