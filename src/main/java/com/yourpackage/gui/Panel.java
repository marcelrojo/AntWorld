package main.java.com.yourpackage.gui;

import main.java.com.yourpackage.entities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * Represents a panel with buttons for sending ants from anthills
 */
public class Panel extends JPanel {
    private Blue_Anthill blueAnthill;
    private Red_Anthill redAnthill;
    private int blue_count;
    private int red_count;

    /**
     * Constructs a Panel with buttons for sending ants from anthills
     * @param blueAnthill       The blue anthill used to send blue ants into the world
     * @param redAnthill        The red anthill used to send red ants into the world
     */
    public Panel(Blue_Anthill blueAnthill, Red_Anthill redAnthill){
        this.blueAnthill=blueAnthill;
        this.redAnthill=redAnthill;
        this.blue_count=0;
        this.red_count=0;

        Color red = new Color(194,24,7);
        Color blue = new Color(0, 128, 255);

        JLabel descriptionLabel = new JLabel("Send Ant");
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor=GridBagConstraints.NORTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = 0;
        labelConstraints.gridwidth = 3;
        labelConstraints.insets = new Insets(10, 0, 10, 0);
        add(descriptionLabel, labelConstraints);


        JButton button1 = new JButton("Soldier");
        JButton button2 = new JButton("Collector");
        JButton button3 = new JButton("Blunderer");
        JButton button4 = new JButton("Worker");
        JButton button5 = new JButton("Drone");

        button1.setBackground(red);
        button2.setBackground(red);
        button3.setBackground(red);
        button4.setBackground(blue);
        button5.setBackground(blue);

        Dimension buttonSize = new Dimension(100, 50); // Adjust width and height as needed
        button1.setPreferredSize(buttonSize);
        button2.setPreferredSize(buttonSize);
        button3.setPreferredSize(buttonSize);
        button4.setPreferredSize(buttonSize);
        button5.setPreferredSize(buttonSize);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(button1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(button4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(button2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(button5, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(button3, gbc);

        Random random = new Random();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rand_str=10+random.nextInt(10);
                int rand_hp=10+random.nextInt(10);
                Red_Ant ant = new Soldier("Red_Ant"+red_count, rand_str,rand_hp,redAnthill.getX(),redAnthill.getY());
                red_count++;
                redAnthill.sendAnt(ant);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rand_str=10+random.nextInt(10);
                int rand_hp=10+random.nextInt(10);
                Red_Ant ant = new Collector("Red_Ant"+red_count, rand_str,rand_hp,redAnthill.getX(),redAnthill.getY());
                red_count++;
                redAnthill.sendAnt(ant);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rand_str=10+random.nextInt(10);
                int rand_hp=10+random.nextInt(10);
                Red_Ant ant = new Blunderer("Red_Ant"+red_count, rand_str,rand_hp,redAnthill.getX(),redAnthill.getY());
                red_count++;
                redAnthill.sendAnt(ant);
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rand_str=10+random.nextInt(10);
                int rand_hp=10+random.nextInt(10);
                Blue_Ant ant = new Worker("Blue_Ant"+blue_count, rand_str,rand_hp,blueAnthill.getX(),blueAnthill.getY());
                blue_count++;
                blueAnthill.sendAnt(ant);
            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rand_str=10+random.nextInt(10);
                int rand_hp=10+random.nextInt(10);;
                Blue_Ant ant = new Drone("Blue_Ant"+blue_count, rand_str,rand_hp,blueAnthill.getX(),blueAnthill.getY());
                blue_count++;
                blueAnthill.sendAnt(ant);
            }
        });

    }
}
