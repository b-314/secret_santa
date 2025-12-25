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
    /**
     * Current player
     */
    private Player current; 

    /**
     * Creates a Game with a name and empty list of Players
     * @param name name of the Game
     */
    public Game(String name) {
        if(name == null || "".equals(name)) {
            throw new IllegalArgumentException("Invalid name"); 
        }
        this.name = name;
        players = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a new Player to the game
     * @param name name of the Game
     */
    public void addPlayer(String name) {
        players.add(new Player(name)); 
        current = players.getLast(); 
    }

    /**
     * Changes the current Player based on selection
     * @param player new current player
     */
    public void selectCurrent(Player player) {
        current = player; 
    }

    /**
     * Adds a gift idea for the current player
     */
    public void addGift(String gift) {
        current.addGift(gift); 
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
