package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.utils.Sprite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Blue Ant, subclass of an Ant, that can move in a world represented by a graph of different vertices
 */
public class Blue_Ant extends Ant{

    private Sprite blueSprite;

    /**
     * Constructs a Blue Ant object with specified parameters
     * @param name     The name of the ant.
     * @param str      The strength value of the ant.
     * @param hp       The health value of the ant.
     * @param x        The x-coordinate of the ant's position.
     * @param y        The y-coordinate of the ant's position.
     */
    public Blue_Ant(String name, int str, int hp, int x, int y ){
        super(name,str,hp,"Blue",x,y);
        this.blueSprite=new Sprite("/sprites/Blue_Ant.png",x,y);
    }

    /**
     * Move ant from current vertex to another and update the pathTaken by adding destination to the path
     * The blue ants are positioned on the destination vertex slightly to the right
     * @param v         The destination vertex
     */
    @Override
    public void move(Vertex v) {
        v.removeAnt(this);
        int targetX = v.getX()+100;
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
     * Get sprite - graphical representation of a Blue Ant
     * @return          The sprite of Blue Ant
     */
    @Override
    public Sprite getSprite(){
        return blueSprite;
    }


}
