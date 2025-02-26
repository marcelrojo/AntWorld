package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Red Ant, subclass of an Ant, that can move in a world represented by a graph of different vertices
 */
public class Red_Ant extends Ant{

    private Sprite redSprite;

    /**
     * Constructs a Red Ant object with specified parameters
     * @param name     The name of the Red Ant.
     * @param str      The strength value of the Red Ant.
     * @param hp       The health value of the Red Ant.
     * @param x        The x-coordinate of the Red Ant's position.
     * @param y        The y-coordinate of the Red Ant's position.
     */
    public Red_Ant(String name, int str, int hp, int x, int y ){
        super(name,str,hp,"Red",x,y);
        this.redSprite=new Sprite("/sprites/Red_Ant.png",x,y);
    }

    /**
     * Move ant from current vertex to another and update the pathTaken by adding destination to the path
     * The red ants are positioned on the destination vertex slightly to the left
     * @param v         The destination vertex
     */
    @Override
    public void move(Vertex v) {
        currentVertex().removeAnt(this);
        int targetX = v.getX()-60;
        int targetY = v.getY();
        int frames = 15;
        int delay = 50;

        int deltaX = (targetX - x) / frames;
        int deltaY = (targetY - y) / frames;

        for (int i = 0; i < frames; i++) {
            setPosition(x + deltaX, y + deltaY);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
            }
        }

        pathTaken.add(v);
        v.addAnt(this);
    }

    /**
     * Get sprite - graphical representation of a Red Ant
     * @return          The sprite of Red Ant
     */
    @Override
    public Sprite getSprite(){
        return redSprite;
    }



}
