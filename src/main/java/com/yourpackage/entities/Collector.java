package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.gui.World;
import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Collector  subclass of Red Ant
 * Collector can move in a world represented as a graph of different vertices and collect larvae from them
 */
public class Collector extends Red_Ant {
    private Sprite collectorSprite;
    protected int stored;

    /**
     * Constructs a Collector with specified parameters
     * @param name     The name of the Collector.
     * @param str      The strength value of the Collector.
     * @param hp       The health value of the Collector.
     * @param x        The x-coordinate of the Collector's position.
     * @param y        The y-coordinate of the Collector's position.
     */
    public Collector(String name, int str, int hp, int x, int y) {
        super(name, str, hp, x, y);
        this.collectorSprite = new Sprite("/sprites/Collector.png", x, y);
        this.stored = 0;
    }

    /**
     * Returns a string representation of a Collector
     * @return            A string containing type, name, health, and strength of the Collector
     */
    @Override
    public String toString() {
        return "Collector  "+super.toString();
    }

    /**
     * Gets sprite - graphical representation of a Collector
     * @return          The sprite of Collector
     */
    @Override
    public Sprite getSprite() {
        return collectorSprite;
    }

    /**
     * Takes as many larvae as it can from the vertex. Subtracts desired amount from vertex and adds to stored
     * If it has as many larvae as it has strength it cannot take anymore and returns home
     */
    public void takeLarvae() {
        synchronized (currentVertex()) {
            if (currentVertex().getLarvae() <= strength - stored) {
                stored += currentVertex().getLarvae();
                currentVertex().removeLarvae(currentVertex().getLarvae());

            } else {
                int remain = strength - stored;
                currentVertex().removeLarvae(remain);
                stored += remain;
            }
        }
        if (stored == strength) {
            returnAnthill();
        }
    }

    /**
     * Ant returns to its home - anthill by taking the reverse of the path it has taken so far
     * After reaching its goal it deposits all collected larvae to the anthill
     */
    @Override
    public void returnAnthill() {
        List<Vertex> path = new ArrayList<>(pathTaken);
        Collections.reverse(path);
        for (Vertex vertex : path) {
            move(vertex);
        }
        synchronized (anthill) {
            anthill.addLarvae(stored);
            stored=0;
        }
        pathTaken = new ArrayList<>();
        pathTaken.add(anthill);
    }

    /**
     * Ant dies and is removed from the environment
     * If it held any larvae it is all dropped on the vertex it died on
     */
    @Override
    public void Die() {
        this.getSprite().setVisible(false);
        World.removeAnt(this);
        synchronized (currentVertex()) {
            currentVertex().addLarvae(stored);
            stored=0;
        }
        currentVertex().removeAnt(this);
        alive = false;
    }

    /**
     * Overrides the run method of Thread to performs ant action while it is alive
     * The ant moves randomly, take larvae from the vertex it is on and sleep for set time
     */
    @Override
    public void run() {
        while (alive) {
            randomMove();
            if (!(currentVertex() instanceof Anthill )){takeLarvae();}
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
        }

    }

}
