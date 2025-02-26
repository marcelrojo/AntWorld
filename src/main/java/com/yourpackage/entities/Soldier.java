package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

/**
 * Represents a Soldier, subclass of Red Ant
 * Soldier can move in a world represented as a graph of different vertices and attack blue ants
 */
public class Soldier extends Red_Ant {
    private Sprite soldierSprite;

    /**
     * Constructs a Soldier with specified parameters
     * @param name     The name of the Soldier.
     * @param str      The strength value of the Soldier.
     * @param hp       The health value of the Soldier.
     * @param x        The x-coordinate of the Soldier's position.
     * @param y        The y-coordinate of the Soldier's position.
     */
    public Soldier(String name, int str, int hp, int x, int y) {
        super(name, str, hp, x, y);
        this.soldierSprite = new Sprite("/sprites/Soldier.png", x, y);
    }

    /**
     * Returns a string representation of a Soldier
     * @return            A string containing type, name, health, and strength of the Soldier
     */
    @Override
    public String toString() {
        return "Soldier  "+super.toString();
    }

    /**
     * Gets sprite - graphical representation of a Soldier
     * @return          The sprite of Soldier
     */
    @Override
    public Sprite getSprite() {
        return soldierSprite;
    }

    /**
     * Hits enemy ant and reducing its health by the strength of a Soldier
     * Hit can be successful only if the target ant is alive
     * If hit is successful Soldier returns to the anthill
     * @param a         The Ant that is the target of a hit
     * @return          True/False depending on whether the attack was successful
     */
    public boolean hit(Ant a) {
        if (a.getAlive()) {
            a.takeHit(this.strength);
            returnAnthill();
            return true;
        }
        return false;
    }
    /**
     * Overrides the run method of Thread to performs ant action while it is alive
     * The ant moves randomly,attempts to hit an enemy ant on the same vertex
     * and sleep for set time
     */
    @Override
    public void run() {
        while (alive) {
            randomMove();
            if (!(currentVertex() instanceof Leaf)) {
                for (Ant ant : currentVertex().getAnts()) {
                    if (ant instanceof Blue_Ant) {
                        if (hit(ant)) {
                            break;
                        }
                    }

                }
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
        }

    }
}
