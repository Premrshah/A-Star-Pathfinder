import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



/**
 * User interface class that creates a control panel with buttons
 * for selecting start/end points, adding obstacles, and running the algorithm (Had to search up how to do set the buttons and mouse up).
 */
public class UI extends JFrame {
    // Buttons for user interaction
    private JButton start;     // select start point
    private JButton end;       // select end point
    private JButton obstacle;  // add obstacles
    private JButton run;       // run the pathfinding algorithm


    /**
     * Creates the control panel window with buttons for the user to interact with.
     */
    public UI() {
        // Set up the window properties
        setTitle("My Frame");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the buttons
        start = new JButton("Start");
        end = new JButton("End");
        obstacle = new JButton("Obstacle");
        run = new JButton("Run!");

        // Use a simple layout that places components in a row
        setLayout(new FlowLayout());

        // Add all the buttons to the window
        getContentPane().add(start);
        getContentPane().add(end);
        getContentPane().add(obstacle);
        getContentPane().add(run);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.state = 1;
                JOptionPane.showMessageDialog(UI.this, "Start chosen!");
            } 
        });
        end.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.state = 2;
                JOptionPane.showMessageDialog(UI.this, "End chosen!");
            }
        });
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.state = 4;
                JOptionPane.showMessageDialog(UI.this, "Running...");
            }
        });
        obstacle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.state = 3;
                JOptionPane.showMessageDialog(UI.this, "Obstalce chosen!");
            }
        });


        setVisible(true);
    }
}