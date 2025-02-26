package main.java.com.yourpackage.gui;

import com.sun.jdi.IntegerValue;
import main.java.com.yourpackage.entities.*;
import main.java.com.yourpackage.utils.Sprite;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Represents the World of Ants, the environment in which the Ant fight for food
 */
public class World extends JFrame {
    private ArrayList<Vertex> vertices;
    private static boolean end;
    private Red_Anthill redAnthill;
    private Blue_Anthill blueAnthill;
    private static ArrayList<Ant> ants;
    private static JLayeredPane layeredPane;
    private JPanel world;
    private Sprite background;
    private Sprite red_win;
    private Sprite blue_win;
    private Sprite draw;
    private Panel controlPanel;
    private static AntPanel  statsPanel;
    private LarvaeLabel total_larvae;

    /**
     * Constructs the World and reset the environment
     */
    World(){
        super("AntWorld");
        this.ants= new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.end=false;

        this.layeredPane = new JLayeredPane();
        setContentPane(layeredPane);

        this.background = new Sprite("/backgrounds/world.png",0,0);
        this.red_win = new Sprite("/backgrounds/Red_Win.png",0,0);
        this.blue_win = new Sprite("/backgrounds/Blue_Win.png",0,0);
        this.draw = new Sprite("/backgrounds/Draw.png",0,0);
        this.layeredPane.add(background, Integer.valueOf(0));

        this.world = new JPanel(new BorderLayout());

        this.world.setPreferredSize(new Dimension(1500, 1000));
        layeredPane.add(world, Integer.valueOf(0));;


        try {
            setIconImage(ImageIO.read(getClass().getResourceAsStream("/backgrounds/icon2.png")));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        setSize(1900, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        resetWorld();
        setVisible(true);


    }

    /**
     * Reset the environment. Add vertices to the World and assign neighbours to each of them to create a graph
     * Adds panels with buttons to send ants, and panel to see stats of each ant
     */
    private void resetWorld(){
        Random random = new Random();
        int x = 0;
        int y = 400;
        this.redAnthill= new Red_Anthill(x, y,0,0,0);
        vertices.add(redAnthill);


        Sprite vertexSprite = redAnthill.getSprite();

        layeredPane.add(vertexSprite, Integer.valueOf(1) );
        vertexSprite.setVisible(true);

        Vertex vertex = null;
        for (int i = 0; i < 4; i++) {
            x = 220;
            y = 270 * i;

            int type=random.nextInt(20);
            int larvae_count=random.nextInt(20);
            if (type==1){
                vertex = new Leaf(x, y,1,i,larvae_count);} else if (type==2) {
                vertex = new Stone(x, y,1,i,larvae_count);
            }else{
                vertex = new Vertex(x, y,1,i,larvae_count);
            }


            vertices.add(vertex);

            vertexSprite = vertex.getSprite();


            layeredPane.add(vertexSprite, Integer.valueOf(1) );
            vertexSprite.setVisible(true);
        }

        for (int i = 0; i < 5; i++) {
            x = 436;
            y = 210 * i;

            int type=random.nextInt(10);
            int larvae_count=random.nextInt(20)+40;
            if (type==1){
                vertex = new Leaf(x, y,2,i,larvae_count);} else if (type==2) {
                vertex = new Stone(x, y,2,i,larvae_count);
            }else{
                vertex = new Vertex(x, y,2,i,larvae_count);
            }
            vertices.add(vertex);

            vertexSprite = vertex.getSprite();


            layeredPane.add(vertexSprite, Integer.valueOf(1) );
            vertexSprite.setVisible(true);
        }

        for (int i = 0; i < 6; i++) {
            x = 652;
            y = 170 * i;

            int type=random.nextInt(5);
            int larvae_count = random.nextInt(40)+80;
            if (type==1){
                vertex = new Leaf(x, y,3,i,larvae_count);} else if (type==2) {
                vertex = new Stone(x, y,3,i,larvae_count);
            }else{
                vertex = new Vertex(x, y,3,i,larvae_count);
            }
            vertices.add(vertex);

            vertexSprite = vertex.getSprite();


            layeredPane.add(vertexSprite, Integer.valueOf(1) );
            vertexSprite.setVisible(true);
        }

        for (int i = 0; i < 5; i++) {
            x = 868;
            y = 210 * i;
            int larvae_count=random.nextInt(20)+40;
            int type=random.nextInt(10);
            if (type==1){
                vertex = new Leaf(x, y,4,i,larvae_count);} else if (type==2) {
                vertex = new Stone(x, y,4,i,larvae_count);
            }else{
                vertex = new Vertex(x, y,4,i,larvae_count);
            }

            vertices.add(vertex);

            vertexSprite = vertex.getSprite();


            layeredPane.add(vertexSprite, Integer.valueOf(1) );
            vertexSprite.setVisible(true);
        }

        for (int i = 0; i < 4; i++) {
            x = 1084;
            y = 270 * i;
            int larvae_count=random.nextInt(20);
            int type=random.nextInt(20);
            if (type==1){
                vertex = new Leaf(x, y,5,i,larvae_count);} else if (type==2) {
                vertex = new Stone(x, y,5,i,larvae_count);
            }else{
                vertex = new Vertex(x, y,5,i,larvae_count);
            }

            vertices.add(vertex);

            vertexSprite = vertex.getSprite();


            layeredPane.add(vertexSprite, Integer.valueOf(1) );
            vertexSprite.setVisible(true);
        }

        x = 1300;
        y = 400;
        this.blueAnthill = new Blue_Anthill(x, y,6,0,0);
        vertices.add(blueAnthill);

        vertexSprite = blueAnthill.getSprite();

        layeredPane.add(vertexSprite, Integer.valueOf(1) );
        vertexSprite.setVisible(true);

        for (Vertex checkVertex : vertices) {
            for (Vertex otherVertex : vertices) {
                if (checkVertex != otherVertex &&
                        (Math.abs(checkVertex.getOrder()-otherVertex.getOrder())==1 ||
                        (Math.abs(checkVertex.getIdx()-otherVertex.getIdx())==1 && checkVertex.getOrder()==otherVertex.getOrder()))) {
                    checkVertex.addNeighbour(otherVertex);
                    otherVertex.addNeighbour(checkVertex);
                }
            }
        }

        background.setVisible(true);
        world.setVisible(true);

        this.controlPanel = new Panel(blueAnthill, redAnthill);
        controlPanel.setBounds(1550,0,300,100);
        layeredPane.add(controlPanel,JLayeredPane.PALETTE_LAYER);
        controlPanel.setVisible(true);

        this.statsPanel = new AntPanel();
        statsPanel.setBounds(1550,150,320,850);
        layeredPane.add(statsPanel, JLayeredPane.PALETTE_LAYER);
        statsPanel.setVisible(true);

        layeredPane.setVisible(true);

    }

    /**
     * Run the simulation and monitor the state of the simulation
     * If all larvae from the world are collected kill all ants and proclaim the winner of the contest
     */
    public void run(){

        int all_food=0;
        for (Vertex v: vertices){
            all_food+=v.getLarvae();
        }

        total_larvae= new LarvaeLabel();
        total_larvae.setText("Larvae left to collect: "+all_food);
        total_larvae.setBounds(0,0,170,30);
        this.layeredPane.add(total_larvae, Integer.valueOf(1000));

        int sum_food=blueAnthill.getLarvae()+redAnthill.getLarvae();

        while(sum_food<all_food-10){
            sum_food=blueAnthill.getLarvae()+redAnthill.getLarvae();
            int larvae_left=all_food-sum_food;
            total_larvae.setText("Larvae left to collect: "+larvae_left);
            this.layeredPane.repaint();

        }
        end=true;


        Iterator<Ant> iterator = ants.iterator();
        ArrayList<Ant> antsToRemove = new ArrayList<>(ants);
        int i=0;
        for (Ant ant: antsToRemove){
            ant.Die();
        };

        if(blueAnthill.getLarvae() > redAnthill.getLarvae()){
            this.layeredPane.add(blue_win, Integer.valueOf(2000));
            blue_win.setVisible(true);
        }else if(redAnthill.getLarvae()>blueAnthill.getLarvae()){
            this.layeredPane.add(red_win, Integer.valueOf(2000));
            red_win.setVisible(true);
        }else{
            this.layeredPane.add(draw, Integer.valueOf(2000));
            draw.setVisible(true);
        }

    }

    /**
     * Removes an Ant from the environment and update the panel with stats of all ants
     * @param a         The ant to be removed from the environment
     */
    public static void removeAnt(Ant a){
        ants.remove(a);
        statsPanel.updateAntStats(ants);
    }

    /**
     * Adds an Ant to the environment and update the panel with stats of all ants
     * @param a         The ant to be added to the environment
     */
    public static void addAnt(Ant a){
        ants.add(a);
        statsPanel.updateAntStats(ants);
    }

    /**
     * Get LayeredPane with all Sprites and Panels that take part in the simulation
     * @return          The Layerd Pane with graphical elements of simulation
     */
    public static JLayeredPane getPane(){
        return layeredPane;
    }

    /**
     * Get the boolean parameter describing the state of the simulation, whether it ended or not
     * @return          The parameter describing if the simulation ended
     */
    public static boolean getEnd(){
        return end;
    }


}
