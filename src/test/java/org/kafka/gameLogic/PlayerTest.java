package org.kafka.gameLogic;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    @Test
    public void testPlayer() {
        Player player = new Player(1);
        assertNotNull(player);
        assertEquals(1, player.getID());
    }

    @Test
    public void testClearHand() {
        Deck deck = new Deck();
        Player player = new Player(1, deck.getCardsPartOfDeck());
        assertFalse(player.getDrawnCards().isEmpty());
        player.clearHand();
        assertTrue(player.getDrawnCards().isEmpty());
    }
}
