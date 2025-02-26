package main.java.com.yourpackage.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Represents a label to display the amount of larvae stored on a given Vertex
 */
public class LarvaeLabel extends JLabel {

    /**
     * Constructs an empty Larvae Label
     */
    public LarvaeLabel() {
        setText("0");
        setOpaque(true);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setHorizontalAlignment(SwingConstants.CENTER);
    }
}
