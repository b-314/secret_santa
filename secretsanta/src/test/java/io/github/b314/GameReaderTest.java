package io.github.b314;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameReaderTest {
    public static final File FILE1 = new File("test-files/game1.json"); 

    @Test
    public void testGameReader() {
        GiftGame gg = assertDoesNotThrow(() -> GiftGameReader.gameReader(FILE1));  
        assertEquals("Secret Santa 2012", gg.getTitle()); 
        ArrayList<Player> players = gg.getPlayers(); 
        assertEquals("Steve", players.get(0).getName()); 
        assertEquals("Steve, Gift List: Baseball caps, Dumbells", players.get(0).toString()); 
        assertEquals("Tony", players.get(1).getName()); 
        assertEquals("Bruce", players.get(2).getName()); 
        assertEquals("Thor", players.get(3).getName()); 
        assertEquals(4, players.size()); 
    }
}
