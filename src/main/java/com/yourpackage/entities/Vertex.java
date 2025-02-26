package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.gui.LarvaeLabel;
import main.java.com.yourpackage.gui.World;
import main.java.com.yourpackage.utils.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents a Vertex, the main part of the graph representing the world of the Ants.
 * Ants can move between vertices, collects larvae from them and fight on them
 */
public class Vertex {
    private int x;
    private int y;
    private int order;
    private int idx;
    private int  larvae;
    private List<Vertex> neighbours;
    private Sprite sprite;
    private List<Ant> ants;
    protected LarvaeLabel label;

    /**
     * Constructs a Vertex object with specified parameters and adds to it a label counting the amount of larvae on it
     * @param x         The x-coordinates of the Vertex
     * @param y         The y-coordinates of the Vertex
     * @param order     The order value of the Vertex
     * @param idx       The index value in said order of the Vertex
     * @param larvae    The amount of larvae in the Vertex
     */
    public Vertex(int x, int y, int order, int idx, int larvae) {
        this.x = x;
        this.y = y;
        this.order=order;
        this.idx=idx;
        this.larvae = larvae;
        this.neighbours = new ArrayList<>();
        this.ants = new ArrayList<>();
        this.sprite = new Sprite("/sprites/Vertex.png", x, y);

        this.label = new LarvaeLabel();
        label.setText(String.valueOf(larvae));
        label.setBounds(x, y, 30, 30);
        World.getPane().add(label, Integer.valueOf(1000));

    }

    /**
     * Adds larvae to the vertex and updates the value displayed on the label
     * @param amount    The amount of larvae to be added to the vertex
     */
    public void addLarvae(int amount) {
        larvae += amount;
        label.setText(String.valueOf(larvae));
        World.getPane().repaint();
    }

    /**
     * Removes larvae from the vertex and updates the value displayed on the label
     * @param amount    The amount of larvae to be removed from the vertex
     */
    public void removeLarvae(int amount) {
        larvae -= amount;
        label.setText(String.valueOf(larvae));
        World.getPane().repaint();
    }

    /**
     * Adds a neighbour to the array containing all neighbours of a vertex
     * @param v         The vertex to be added as a neighbour
     */
    public void addNeighbour(Vertex v) {
        neighbours.add(v);
    }

    /**
     * Removes a neighbour from the array containing all neighbours of a vertex
     * @param v         The vertex to be removed as a neighbour
     */
    public void removeNeighbour(Vertex v) {
        neighbours.remove(v);
    }

    /**
     * Gets the x-coordinates of the Vertex
     * @return          The x-coordinates of the Vertex
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinates of the Vertex
     * @return          The y-coordinates of the Vertex
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the order of the Vertex
     * @return          The order of the Vertex
     */
    public int getOrder(){return order;}

    /**
     * Gets the index of the Vertex
     * @return          The index of the Vertex
     */
    public int getIdx(){return idx;}

    /**
     * Gets the amount of larvae on the Vertex
     * @return          The amount of larvae on the Vertex
     */
    public int getLarvae() {
        return larvae;
    }

    /**
     * gets the List of all vertices that are neighbours to the Vertex
     * @return          The list of all neighbours
     */
    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    /**
     * Adds an ant to the list of all ants currently on the Vertex
     * @param a         The ant to be added to the Vertex
     */
    public void addAnt(Ant a) {
        ants.add(a);
    }

    /**
     * Removes an ant from the list of all ants currently on the Vertex
     * @param a         The ant to be removed from the Vertex
     */
    public void removeAnt(Ant a) {
        ants.remove(a);
    }

    /**
     * Gets a List of all ants that are currently present on the Vertex
     * @return          The list of ants present on the Vertex
     */
    public List<Ant> getAnts() {
        return ants;
    }

    /**
     * Gets sprite - graphical representation of a vertex
     * @return          The sprite associated with Vertex
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Get location of a Vertex
     * @return          The point representing current coordinate of a Vertex
     */
    public Point getPosition() {
        return new Point(x, y);
    }

}
