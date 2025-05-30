package org.example;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {
    @Test
    public void testDeck() {
        Deck deck = new Deck();
        assertNotNull(deck);
        assertNotNull(deck.getCardsInDeck());
        assertFalse(deck.getCardsInDeck().isEmpty());
        assertEquals(deck.getCardsInDeck(), deck.getCardsLeftInDeck());
    }

    @Test
    public void testDeckEmpty() {
        Deck deck = new Deck();
        deck.clearDeck();
        assertTrue(deck.getCardsInDeck().isEmpty());
    }

    @Test
    public void testAddCard() {
        Deck deck = new Deck();
        deck.clearDeck();
        Card testCard = new Card(Suits.SPADES, Value.ACE);
        deck.getCardsInDeck().add(testCard);
        assertEquals(1, deck.getCardsInDeck().size());
        assertTrue(deck.getCardsInDeck().contains(testCard));
    }

    @Test
    public void testDrawCard() {
        Deck deck = new Deck();
        deck.getCardsLeftInDeck().clear();
        Card testCard = new Card(Suits.SPADES, Value.ACE);
        deck.getCardsLeftInDeck().add(testCard);
        assertEquals(testCard, deck.drawCard());
    }

    @Test
    public void testReshuffle() {
        Deck deck = new Deck();
        ArrayList<Card> expectedCardsLeftInDeck = deck.getCardsLeftInDeck();
        deck.getCardsLeftInDeck().clear();
        deck.reshuffle();
        assertEquals(expectedCardsLeftInDeck.size(), deck.getCardsLeftInDeck().size());
    }
}
