/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.b314;

import java.util.ArrayList;

public class Player {
    /**
     * Player that this Player is assigned to
     */
    private Player assigned;
    /**
     * Name of this Player
     */
    private String name;
    /**
     * Gift ideas for the Player
     */
    private ArrayList<String> gifts; 

    /**
     * Sets the name of the Player and assigned to null
     * @param name name of the player
     * @throws IllegalArgumentException if string is empty or null
     */
    public Player (String name) {
        assigned = null;
        if(name == null || "".equals(name)) {
            throw new IllegalArgumentException("Invalid name"); 
        }
        this.name = name;
        gifts = new ArrayList<>(); 
    }

    /**
     * Gets the Player this player is assigned to
     */
    public Player getAssigned() {
        return assigned;
    }

    /**
     * Gets the name of this player
     */
    public String getName() {
        return name;
    } 

    /**
     * Sets the Player this Player is assigned to
     */
    public void setAssigned(Player assigned) {
        this.assigned = assigned;
    }

    /**
     * Adds gift ideas for the Player
     * @param gift gift idea for the Player
     * @throws IllegalArgumentException if string is empty or null
     */
    public void addGift(String gift) {
        if(gift == null || "".equals(gift)) {
            throw new IllegalArgumentException("Invalid gift"); 
        }
        gifts.add(gift); 
    }

    /**
     * Returns the list of gift ideas for the Player
     * @return gifts the list of gift ideas
     */
    public ArrayList<String> getGifts() {
        return gifts; 
    }

    /**
     * Clears the list of gifts for the Player
     */
    public void clearGifts() {
        gifts.clear(); 
    }

    /**
     * Returns a String of gift ideas for the Player
     * @return gifts the String of gift ideas
     */
    public String getGiftsString() {
        String giftsString = ""; 
        for(int i = 0; i < gifts.size() - 1; i++) {
            giftsString += gifts.get(i) + "\n "; 
        }
        giftsString += gifts.getLast(); 
        return giftsString; 
    }

    /**
     * Represents a Player object with name and gift ideas
     * @return String representation of the Player
     */
    @Override
    public String toString() {
        return name + getGiftsString();  
    }
}
