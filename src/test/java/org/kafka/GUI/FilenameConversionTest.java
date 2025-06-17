package org.kafka.GUI;

import org.junit.Test;
import org.kafka.gameLogic.Card;
import org.kafka.gameLogic.Suits;
import org.kafka.gameLogic.Value;

import static org.junit.Assert.assertEquals;

public class FilenameConversionTest {
    @Test
    public void testFilenameConversion() {
        Card card = new Card(Suits.SPADES, Value.ACE);
        assertEquals("ACE_OF_SPADES.png", card.toString().replace(' ', '_').replace("of", "OF").concat(".png"));
    }
}
