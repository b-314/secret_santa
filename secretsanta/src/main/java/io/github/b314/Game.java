package io.github.b314;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    /**
     * List of participants
     */
    private ArrayList<Player> players; 

    public void assignPlayer() {
        for(int i = players.size(); i > 1; i--) {
            Random random = new Random(); 
            int j = random.nextInt(i-1); 
            Player temp = players.get(j); 
            players.set(j, players.get(i)); 
            players.set(i, temp); 
        }
    }
}
