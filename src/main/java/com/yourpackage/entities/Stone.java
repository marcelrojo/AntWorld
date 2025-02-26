package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Stone, a type of Vertex that cannot be walked through
 */
public class Stone extends Vertex{
    private int x;
    private int y;
    private int order;
    private int idx;
    private int larvae;
    private List<Vertex> neighbours;
    private Sprite stoneSprite;
    private List<Ant> ants;

    /**
     * Constructs a Stone object with specified parameters
     * @param x         The x-coordinates of the Stone
     * @param y         The y-coordinates of the Stone
     * @param order     The order value of the Stone
     * @param idx       The index value in said order of the Stone
     * @param larvae    The amount of larvae in the Stone
     */
    public Stone(int x, int y,int order, int idx,int larvae) {
        super(x,y,order,idx,larvae);
        this.stoneSprite = new Sprite("/sprites/Stone.png", x, y);
    }

    /**
     * Gets sprite - graphical representation of a Stone
     * @return          The sprite associated with Stone
     */
    @Override
    public Sprite getSprite() {
        return stoneSprite;
    }
}
