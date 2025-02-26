package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

/**
 * Represents a Drone,  a subclass of Blue Ant
 * Drone can move in a world represented as a graph of different vertices
 */
public class Drone extends Blue_Ant {
    private Sprite droneSprite;

    /**
     * Constructs a Drone with specified parameters
     * @param name     The name of the Drone.
     * @param str      The strength value of the Drone.
     * @param hp       The health value of the Drone.
     * @param x        The x-coordinate of the Drone's position.
     * @param y        The y-coordinate of the Drone's position.
     */
    public Drone(String name, int str, int hp, int x, int y) {
        super(name, str, hp, x, y);
        this.droneSprite = new Sprite("/sprites/Drone.png", x, y);

    }

    /**
     * Returns a string representation of a Drone
     * @return            A string containing type, name, health, and strength of the Drone
     */
    @Override
    public String toString() {
        return "Drone  "+super.toString();
    }

    /**
     * Gets sprite - graphical representation of a Drone
     * @return          The sprite of Drone
     */
    @Override
    public Sprite getSprite() {
        return droneSprite;
    }
}
