/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package io.github.b314;

class Player {
    /**
     * Player that this Player is assigned to
     */
    private Player assigned;
    /**
     * Name of this Player
     */
    private String name;

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
     * Sets the name of this Player
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
