package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Leaf, a type of Vertex on which no fighting can happen
 */
public class Leaf extends Vertex{
    private int x;
    private int y;
    private int order;
    private int idx;
    private int larvae;
    private List<Vertex> neighbours;
    private Sprite leafSprite;
    private List<Ant> ants;

    /**
     * Constructs a Leaf object with specified parameters
     * @param x         The x-coordinates of the Leaf
     * @param y         The y-coordinates of the Leaf
     * @param order     The order value of the Leaf
     * @param idx       The index value in said order of the Leaf
     * @param larvae    The amount of larvae in the Leaf
     */
    public Leaf(int x, int y, int order, int idx,int larvae) {
        super(x,y,order,idx,larvae);
        this.leafSprite = new Sprite("/sprites/Leaf.png", x, y);
    }

    /**
     * Gets sprite - graphical representation of a Leaf
     * @return          The sprite associated with Leaf
     */
    @Override
    public Sprite getSprite() {
        return leafSprite;
    }
}
