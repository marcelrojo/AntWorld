package main.java.com.yourpackage.gui;

import main.java.com.yourpackage.entities.Ant;
import main.java.com.yourpackage.entities.Red_Ant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Represents a panel to display information about living ants and buttons to kill them
 */
public class AntPanel extends JPanel {
    private JPanel statsPanel;
    private JScrollPane scrollPane;
    private Color red = new Color(194, 24, 7);
    private Color blue = new Color(0, 128, 255);

    /**
     * Constructs an AntPanel and sets up panel components
     */
    public AntPanel() {
        setLayout(new BorderLayout());

        statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(statsPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Updates the stats of Ants being displayed on the panel
     * @param ants      The array of ants that are being displayed
     */
    public void updateAntStats(ArrayList<Ant> ants) {
        statsPanel.removeAll();

        for (Ant ant : ants) {
            JButton dieButton = new JButton("KILL");

            if (ant instanceof Red_Ant) {
                dieButton.setBackground(red);
            } else {
                dieButton.setBackground(blue);
            }
            dieButton.addActionListener(new DieButtonListener(ant));

            JPanel antPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            antPanel.add(new JLabel(ant.toString()));
            antPanel.add(dieButton);

            statsPanel.add(antPanel);
        }

        statsPanel.revalidate();
        statsPanel.repaint();
    }

    /**
     * ActionListener class for the button associated with killing ants
     */
    private class DieButtonListener implements ActionListener {
        private Ant ant;

        public DieButtonListener(Ant ant) {
            this.ant = ant;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ant.Die();
        }
    }
}