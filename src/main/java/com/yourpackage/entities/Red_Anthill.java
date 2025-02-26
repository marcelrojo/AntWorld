package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.gui.World;
import main.java.com.yourpackage.utils.Sprite;

import java.util.List;

/**
 * Represents a Red Anthill, subclass of an Anthill, which is a type of vertex which is a home o many red ants
 */
public class Red_Anthill extends Anthill {
    private int x;
    private int y;
    private int order;
    private int idx;
    private int larvae;
    private List<Vertex> neighbours;
    private Sprite redAnthillSprite;
    private List<Ant> ants;

    /**
     * Constructs a Red Anthill object with specified parameters
     * @param x         The x-coordinates of the Red Anthill
     * @param y         The y-coordinates of the Red Anthill
     * @param order     The order value of the Red  Anthill
     * @param idx       The index value in said order of the Red  Anthill
     * @param larvae    The amount of larvae in the Red  Anthill
     */
    public Red_Anthill(int x, int y, int order, int idx, int larvae) {
        super(x, y, order, idx, larvae);
        this.redAnthillSprite = new Sprite("/sprites/Red_Anthill.png", x, y);
        this.larvae = 0;
    }

    /**
     * Gets sprite - graphical representation of a Red Anthill
     * @return          The sprite associated with Red Anthill
     */
    @Override
    public Sprite getSprite() {
        return redAnthillSprite;
    }

    /**
     * sends a Red Ant from the Red Anthill into the world
     * Action only available if the simulation is in progress
     * @param ant       The Red Ant to be sent
     */
    public void sendAnt(Red_Ant ant) {

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
