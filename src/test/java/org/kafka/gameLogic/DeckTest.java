package org.kafka.gameLogic;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DeckTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void testDeck() {
        Deck deck = new Deck();
        assertNotNull(deck);
        assertNotNull(deck.getCardsPartOfDeck());
        assertFalse(deck.getCardsPartOfDeck().isEmpty());
        assertEquals(deck.getCardsPartOfDeck(), deck.getCardsLeftInDeck());
    }

    @Test
    public void testDeckEmpty() {
        Deck deck = new Deck();
        deck.clearDeck();
        assertTrue(deck.getCardsPartOfDeck().isEmpty());
    }

    @Test
    public void testAddCard() {
        Deck deck = new Deck();
        deck.clearDeck();
        Card testCard = new Card(Suits.SPADES, Value.ACE);
        deck.getCardsPartOfDeck().add(testCard);
        assertEquals(1, deck.getCardsPartOfDeck().size());
        assertTrue(deck.getCardsPartOfDeck().contains(testCard));
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

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintDeck() {
        Deck deck = new Deck();
        deck.clearDeck();
        deck.emptyDeck();
        assertTrue(deck.getCardsPartOfDeck().isEmpty());
        assertTrue(deck.getCardsLeftInDeck().isEmpty());
        deck.addCard(Suits.SPADES, Value.ACE);
        deck.addCard(new Card(Suits.DIAMONDS, Value.KING));
        assertFalse(deck.getCardsPartOfDeck().isEmpty());
        assertFalse(deck.getCardsLeftInDeck().isEmpty());

        deck.printDeck();
        assertEquals("The two Strings didnt match!", "ACE of SPADES\r\nACE of SPADES\r\nKING of DIAMONDS\r\nKING of DIAMONDS\r\n", outContent.toString());

    }
}
