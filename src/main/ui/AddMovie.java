package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddMovie extends JInternalFrame {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private static final int LOC = 100;
    //private static int sensorCount = 0;
    //private Sensor theSensor;
    private JTextField openCloseStatus;
    private String location;

    /**
     * Constructor sets up user interface for a given sensor
     //* @param s   the sensor
     //* @param parent  the parent component
     */
    public AddMovie(Component parent) {
        super(String.valueOf(parent.getLocation()), false, false, false, false);
        //openCloseStatus.setEditable(false);
        //openCloseStatus.setAlignmentX(CENTER_ALIGNMENT);

        JButton openClose = new JButton();
        openClose.setAlignmentX(CENTER_ALIGNMENT);
        Container cp = getContentPane();
        cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
        //cp.add(openCloseStatus);
        //cp.add(openClose);
        setSize(WIDTH, HEIGHT);
        setPosition(parent);
        //sensorCount++;
        setVisible(true);
    }

    /**
     * Sets the position of this sensor UI relative to parent component
     * @param parent  the parent component
     */
    private void setPosition(Component parent) {
        setLocation(LOC, parent.getHeight() / 2 + LOC / 5);
    }

    /**
     * Represents the action to be taken when sensor is opened or closed
     */
    /*private class OpenCloseAction extends AbstractAction {
        OpenCloseAction() {
            super("Toggle");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (theSensor.isOpen()) {
                theSensor.close();
                openCloseStatus.setText(location + " is closed");
            }
            else {
                theSensor.open();
                openCloseStatus.setText(location + " is open");
            }
        }
    }*/
}

