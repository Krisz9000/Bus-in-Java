package org.kafka;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CardTest {
    @Test
    public void testCard() {
        Card card = new Card(Suits.SPADES, Value.ACE);
        assertNotNull(card);
        assertEquals(Suits.SPADES, card.getSuit());
        assertEquals(Value.ACE, card.getValue());
    }

    @Test
    public void testToString() {
        Card card = new Card(Suits.SPADES, Value.ACE);
        assertNotNull(card.toString());
        assertEquals("ACE of SPADES", card.toString());
    }

    @Test
    public void testGetValue() {
        Card card = new Card(Suits.SPADES, Value.ACE);
        assertEquals(Value.ACE, card.getValue());
    }

    @Test
    public void testGetSuit() {
        Card card = new Card(Suits.SPADES, Value.ACE);
        assertEquals(Suits.SPADES, card.getSuit());
    }
}
