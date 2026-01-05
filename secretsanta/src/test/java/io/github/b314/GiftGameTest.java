package io.github.b314;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GiftGameTest {
    /**
     * Tests creating a Game
     */
    @Test
    public void testGiftGame() {
        GiftGame g = new GiftGame("Secret Santa"); 
        assertEquals("Secret Santa", g.getTitle());
        assertEquals(0, g.getPlayers().size()); 
    }
}
