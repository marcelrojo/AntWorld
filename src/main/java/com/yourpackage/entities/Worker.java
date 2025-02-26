package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.gui.World;
import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Worker,  a subclass of Blue Ant
 * Drone can move in a world represented as a graph of different vertices and collect larvae from them
 * Additionally it can attack red ants
 */
public class Worker extends Blue_Ant{
    private Sprite workerSprite;
    private int stored;

    /**
     * Constructs a Worker with specified parameters
     * @param name     The name of the Worker.
     * @param str      The strength value of the Worker.
     * @param hp       The health value of the Worker.
     * @param x        The x-coordinate of the Worker's position.
     * @param y        The y-coordinate of the Worker's position.
     */
    public Worker(String name, int str, int hp, int x, int y){
        super(name, str, hp, x, y);
        this.workerSprite=new Sprite("/sprites/Worker.png", x, y);
        this.stored=0;
    }

    /**
     * Returns a string representation of a Worker
     * @return            A string containing type, name, health, and strength of the Worker
     */
    @Override
    public String toString() {
        return "Worker  "+super.toString();
    }

    /**
     * Gets sprite - graphical representation of a Worker
     * @return          The sprite of Worker
     */
    @Override
    public Sprite getSprite() {
        return workerSprite;
    }

    /**
     * Hits enemy ant and reducing its health by the strength of a Worker
     * Hit can be successful only if the target ant is alive
     * If hit is successful Worker returns to the anthill
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
     * The ant moves randomly, take larvae from the vertex it is on,
     * attempts to hit an enemy ant on the same vertex and sleep for set time
     */
    @Override
    public void run() {
        while (alive) {
            randomMove();
            if (!(currentVertex() instanceof Anthill )){takeLarvae();}
            if (!(currentVertex() instanceof Leaf)) {
                for (Ant ant : currentVertex().getAnts()) {
                    if (ant instanceof Red_Ant) {
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
