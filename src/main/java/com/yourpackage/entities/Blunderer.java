package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Represents a Blunderer, a special type of Collector ant which in turn is a subclass of Red Ant
 * Blunderer can move in a world represented as a graph of different vertices and collect larvae from them
 * During its return to anthill it can leave some larvae on the path home
 */
public class Blunderer extends Collector{
    private Sprite blundererSprite;

    /**
     * Constructs a Blunderer with specified parameters
     * @param name     The name of the Blunderer.
     * @param str      The strength value of the Blunderer.
     * @param hp       The health value of the Blunderer.
     * @param x        The x-coordinate of the Blunderer's position.
     * @param y        The y-coordinate of the Blunderer's position.
     */
    public Blunderer(String name, int str, int hp, int x, int y) {
        super(name, str, hp, x, y);
        this.blundererSprite = new Sprite("/sprites/Blunderer.png", x, y);

    }

    /**
     * Returns a string representation of a Blunderer
     * @return            A string containing type, name, health, and strength of the Blunderer
     */
    @Override
    public String toString() {
        return "Blunderer  "+super.toString();
    }

    /**
     * Gets sprite - graphical representation of a Blunderer
     * @return          The sprite of Blunderer
     */
    @Override
    public Sprite getSprite() {
        return blundererSprite;
    }

    /**
     * Blunderer returns to its home - anthill by taking the reverse of the path it has taken so far
     * On each vertex on the way home it can leave a few of larvae it is transporting
     */
    @Override
    public void returnAnthill() {
        List<Vertex> path = new ArrayList<>(pathTaken);
        Collections.reverse(path);
        Random random=new Random();
        for (Vertex vertex : path) {
            int drop=random.nextInt(3);
            if (drop==0){
                int amount=1+random.nextInt(5);
                if (amount>stored){
                    amount=stored;
                }
                synchronized (vertex){
                    vertex.addLarvae(amount);
                    stored-=amount;
                }

            }
            move(vertex);
        }
        synchronized (anthill) {
            anthill.addLarvae(stored);
            stored=0;
        }
        pathTaken = new ArrayList<>();
        pathTaken.add(anthill);
    }
}
