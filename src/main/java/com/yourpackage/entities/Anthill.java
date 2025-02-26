package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

import java.util.List;

/**
 * Represents an abstract Anthill, a type of Vertex which is a home to many ants
 * Subclasses myst implement specific behaviour for sending ants
 */
public abstract class Anthill extends Vertex{
    private int x;
    private int y;
    private int order;
    private int idx;
    private int larvae;
    private List<Vertex> neighbours;
    private Sprite anthillSprite;
    private List<Ant> ants;

    /**
     * Constructs an Anthill object with specified parameters
     * @param x         The x-coordinates of the Anthill
     * @param y         The y-coordinates of the Anthill
     * @param order     The order value of the Anthill
     * @param idx       The index value in said order of the Anthill
     * @param larvae    The amount of larvae in the Anthill
     */
    public Anthill(int x, int y,int order, int idx, int larvae) {
        super(x,y,order,idx,larvae);
        this.anthillSprite = new Sprite("/sprites/Anthill.png", x, y);
    }

    /**
     * Gets sprite - graphical representation of an Anthill
     * @return          The sprite associated with Anthill
     */
    @Override
    public Sprite getSprite() {
        return anthillSprite;
    }

    /**
     * sends an Ant from anthill into the world
     * @param ant       The ant to be sent
     */
    public void sendAnt(Ant ant){

    }

}
