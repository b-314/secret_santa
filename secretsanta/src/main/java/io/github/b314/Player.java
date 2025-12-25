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
     */
    public void addGift(String gift) {
        if(gift == null || "".equals(gift)) {
            throw new IllegalArgumentException("Invalid gift"); 
        }
        gifts.add(gift); 
    }

    /**
     * Represents a Player object with name and gift ideas
     * @return String representation of the Player
     */
    @Override
    public String toString() {
        String giftsString = ""; 
        for(String g : gifts) {
            giftsString += g + ", "; 
        }
        return name + ": " + giftsString; 
    }
}
