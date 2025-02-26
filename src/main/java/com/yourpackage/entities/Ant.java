package main.java.com.yourpackage.entities;

import main.java.com.yourpackage.gui.World;
import main.java.com.yourpackage.utils.Sprite;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Represents an Ant that can move in a world represented by a graph of different vertices
 */
public class Ant extends Thread{

    protected String name;
    protected int strength;
    protected int health;
    protected String color;
    protected List<Vertex> pathTaken;
    protected Vertex anthill;
    protected Sprite sprite;
    protected int x;
    protected int y;
    protected boolean alive;

    /**
     * Constructs an Ant object with specified parameters.
     * @param name     The name of the ant.
     * @param strength The strength value of the ant.
     * @param health   The health value of the ant.
     * @param color    The color of the ant.
     * @param x        The x-coordinate of the ant's position.
     * @param y        The y-coordinate of the ant's position.
     */
    public Ant(String name, int strength, int health, String color, int x, int y){
        this.name=name;
        this.strength=strength;
        this.health=health;
        this.color=color;
        this.x=x;
        this.y=y;
        this.pathTaken = new ArrayList<>();
        this.sprite = new Sprite("/sprites/Ant.png",x,y);
        this.alive=true;
    }

    /**
     * Sets the anthill to which ant will return to, and adds it to the beginning of pathTaken
     * @param v         The vertex representing an anthill
     */
    public void setAnthill(Vertex v){
        anthill=v;
        pathTaken.add(v);
    }

    /**
     * Returns a string representation of an Ant
     * @return            A string containing name, health, and strength of the Ant
     */
    public String toString() {
        return name+"  "+health+"hp  "+strength+"str";
    }

    /**
     * Returns a name of an Ant
     * @return            The name of Ant
     */
    public String returnName() {
        return name;
    }

    /**
     * Gets strength of an Ant
     * @return           The strength of Ant
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Gets health of an Ant
     * @return           The health of Ant
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets color of an Ant
     * @return           The color of Ant
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets current vertex that the ant is currently on
     * @return           The vertex if present, null otherwise
     */
    public Vertex currentVertex() {
        if (pathTaken.isEmpty()) {
            return anthill;
        }
        return pathTaken.getLast();
    }

    /**
     * Move ant from current vertex to another and update the pathTaken by adding destination to the path
     * @param v         The destination vertex
     */
    public void move(Vertex v) {
        currentVertex().removeAnt(this);
        int targetX = v.getX();
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
     * Ant is making random move. It takes random neighbour of current vertex that has not been yet visited and moves to it
     * If there is no such vertex that the ant may go to it returns to its anthill
     * If the Ant is currently on the Stone vertex it also returns to its anthill
     */
    public void randomMove() {

        Vertex current=currentVertex();

        if (current instanceof Stone) {
            returnAnthill();
            return;
        }

        List<Vertex> neighbours=current.getNeighbours();
        Collections.shuffle(neighbours);
        boolean moved = false;
        for (Vertex vertex: neighbours){
            if (!pathTaken.contains(vertex) && !(vertex instanceof Anthill)){
                moved=true;
                move(vertex);
                break;
            }
        }
        if (!moved){
            returnAnthill();
        }
    }

    /**
     * Ant returns to its home - anthill by taking the reverse of the path it has taken so far
     */
    public void returnAnthill() {
        List<Vertex> path= new ArrayList<>(pathTaken);
        Collections.reverse(path);
        for (Vertex vertex: path){
            move(vertex);
        }
        pathTaken=new ArrayList<>();
        pathTaken.add(anthill);
    }

    /**
     * Ant is taking damage and reducing its health. If health is reduced below 0 the ant dies.
     * @param dmg           The amount of damage ant is taking
     */
    public synchronized void takeHit(int dmg) {
        health -= dmg;
        if (health <= 0 && !World.getEnd()) {
            Die();
        }
    }

    /**
     * Ant dies and is removed from the environment
     */
    public void Die() {
        this.getSprite().setVisible(false);
        World.removeAnt(this);
        currentVertex().removeAnt(this);
        alive=false;
    }

    /**
     * Get sprite - graphical representation of an Ant
     * @return          The sprite of Ant
     */
    public Sprite getSprite(){
        return sprite;
    }

    /**
     * Get location of an Ant
     * @return          The point representing current coordinate of an Ant
     */
    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Change position of an Ant to a new one, and move its sprite in the process
     * @param newX          The x-coordinate of new position
     * @param newY          The y-coordinate of new position
     */
    public void setPosition(int newX, int newY){
        x=newX;
        y=newY;
        this.getSprite().updatePosition(newX,newY);
    }

    /**
     * Get the boolean parameter describing whether the ant is alive or not
     * @return              The parameter describing if an Ant is alive
     */
    public boolean getAlive(){
        return alive;
    }

    /**
     * Overrides the run method of Thread to performs ant action while it is alive
     * The ant moves randomly and sleep for set time
     */
    @Override
    public void run(){
        while(alive){
            randomMove();
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
            }
        }

    }

}
