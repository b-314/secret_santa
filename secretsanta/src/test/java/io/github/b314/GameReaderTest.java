package io.github.b314;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameReaderTest {
    public static final File FILE1 = new File("test-files/game1.json"); 

    @Test
    public void testGameReader() {
        Game g = assertDoesNotThrow(() -> GameReader.gameReader(FILE1));  
        assertEquals("Secret Santa 2012", g.getTitle()); 
    }
}
