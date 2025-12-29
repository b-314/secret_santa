package io.github.b314;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    /**
     * Name of the Game
     */
    private String title; 
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
     * @throws IllegalArgumentException if string is empty or null
     */
    public Game(String title) {
        if(title == null || "".equals(title)) {
            throw new IllegalArgumentException("Invalid name"); 
        }
        this.title = title;
        players = new ArrayList<>();
    }

    /**
     * Gets the Title of the Game
     * @return game title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the current list of Players
     * @return ArrayList of Players
     */
    public ArrayList<Player> getPlayers() {
        return players; 
    }

    /**
     * Adds a new Player to the game
     * @param name name of the Game
     */
    public void addPlayer(String name) {
        Player p = new Player(name); 
        players.add(p); 
        current = p; 
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
     * @param gift idea for a gift
     */
    public void addGift(String gift) {
        current.addGift(gift); 
    }

    /**
     * Assigns Players another player randomly such that no player is assigned themselves
     */
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
