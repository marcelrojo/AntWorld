package main.java.com.yourpackage.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a Sprite, graphical representation of all objects in the simulation
 */
public class Sprite extends JLabel {
    private int x, y;

    /**
     * Constructs a Sprite of a given object with specified parameters
     *
     * @param imagePath The path with location of the image to be used as a sprite
     * @param x         The x-coordinate of the Sprite's position.
     * @param y         The y-coordinate of the Sprite's position.
     */
    public Sprite(String imagePath, int x, int y) {
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        this.setIcon(imageIcon);
        this.x = x;
        this.y = y;
        this.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
    }

    /**
     * Changes position of a Sprite to a new one
     *
     * @param newX The x-coordinate of new position
     * @param newY The y-coordinate of new position
     */
    public void updatePosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
        this.setBounds(newX, newY, this.getWidth(), this.getHeight());
    }

}
