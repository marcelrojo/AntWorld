package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.gui.World;
import main.java.com.yourpackage.utils.Sprite;

import java.util.List;

/**
 * Represents a Blue Anthill, subclass of an Anthill, which is a type of vertex which is a home o many blue ants
 */
public class Blue_Anthill extends Anthill{
    private int x;
    private int y;
    private int order;
    private int idx;
    private int larvae;
    private List<Vertex> neighbours;
    private Sprite blueAnthillSprite;
    private List<Ant> ants;

    /**
     * Constructs a Blue Anthill object with specified parameters
     * @param x         The x-coordinates of the Blue Anthill
     * @param y         The y-coordinates of the Blue Anthill
     * @param order     The order value of the Blue Anthill
     * @param idx       The index value in said order of the Blue Anthill
     * @param larvae    The amount of larvae in the Blue Anthill
     */
    public Blue_Anthill(int x, int y,int order, int idx, int larvae){
        super(x,y,order,idx,larvae);
        this.blueAnthillSprite=new Sprite("/sprites/Blue_Anthill.png", x, y);
        this.larvae=0;
    }

    /**
     * Gets sprite - graphical representation of a Blue Anthill
     * @return          The sprite associated with Blue Anthill
     */
    @Override
    public Sprite getSprite() {
        return blueAnthillSprite;
    }

    /**
     * sends a Blue Ant from the Blue Anthill into the world
     * Action only available if the simulation is in progress
     * @param ant       The Blue Ant to be sent
     */
    public void sendAnt(Blue_Ant ant){
        if (!World.getEnd()) {
            ant.setAnthill(this);
            World.addAnt(ant);

            Sprite antSprite = ant.getSprite();
            World.getPane().add(antSprite, Integer.valueOf(2));
            antSprite.setVisible(true);

            ant.start();
        }

    }

}
