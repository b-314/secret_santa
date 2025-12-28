package io.github.b314;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameTest {
    /**
     * Tests creating a Game
     */
    @Test
    public void testGame() {
        Game g = new Game("Secret Santa"); 
        assertEquals("Secret Santa", g.getTitle());
    }
}
