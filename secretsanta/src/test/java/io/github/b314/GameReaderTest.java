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
        String[] names = {"Steve", "Tony", "Bruce", "Thor"}; 
        for(int i = 0; i < names.length; i++) {
            assertEquals(names[i], players.get(i).getName()); 
        }
        assertEquals("Steve, Gift List: Baseball caps, Dumbells", players.get(0).toString()); 
        assertEquals(4, players.size()); 
    }
}
