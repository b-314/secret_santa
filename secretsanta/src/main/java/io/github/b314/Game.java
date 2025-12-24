package io.github.b314;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    /**
     * Name of the Game
     */
    private String name; 
    /**
     * List of participants
     */
    private ArrayList<Player> players; 

    public Game(String name) {
        this.name = name;
        players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void assignPlayer() {
        Player[] assignments = new Player[players.size()];
        Random random = new Random(); 
        for(int i = assignments.length; i > 1; i--) {
            int j = random.nextInt(i-1); 
            Player temp = assignments[j]; 
            assignments[j] = assignments[i]; 
            assignments[i] = temp; 
        }

        int playerNum = 0; 
        for(Player p : players) {
            p.setAssigned(assignments[playerNum]);
        }
    }
}
