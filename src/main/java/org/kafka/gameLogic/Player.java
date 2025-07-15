package org.kafka.gameLogic;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private final byte ID;
    private final ArrayList<Card> drawnCards;
    private byte numberOfDraws;

    public ArrayList<Card> getDrawnCards() {
        return drawnCards;
    }

    public Player(ArrayList<Player> players) {
        byte id;
        id = 0;
        id += (byte) players.size();
        this.ID = id;
        this.numberOfDraws = 0;
        this.drawnCards = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public byte getNumberOfDraws() {
        return this.numberOfDraws;
    }

    /**
     * Draws a {@code Card} from a {@code Deck} and adds it to the player's hand.
     *
     * @param deck The {@link Deck} the card should be drawn from.
     */
    public void drawCard(Deck deck) {
        drawnCards.add(deck.drawCard());
        numberOfDraws++;
    }

    /**
     * Using a {@link StringBuilder}, build a single line string out of all the cards the player currently has drawn.
     *
     * @return A concatenated {@code String} of all the player's cards.
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
     * Empties the player's hand using {@link ArrayList<>#clear()}
     */
    public void clearHand() {
        this.drawnCards.clear();
        this.numberOfDraws = 0;
    }

    @Override
    public String toString() {
        return "ID = " + this.ID + ", cards drawn: " + this.numberOfDraws + ", drawn cards are: " + printDrawnCards();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return ID == player.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ID);
    }
}
