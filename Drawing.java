import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// import org.w3c.dom.events.MouseEvent;

import javax.swing.JButton;
// import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import java.awt.FlowLayout;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import javax.swing.JOptionPane;

import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
// import javax.swing.SwingUtilities;

public class Drawing extends JFrame {
	int row;
	int col;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int offsetX = 20;
	int offsetY = 40;
	int squareSize = 75;
	Node node;

	String mode;
	Node[][] grid;
	ArrayList<Node> pathList;

	public static void main(String[] args) {
		Main exe = new Main();
		exe.main(null);
	}

	public Drawing() {
		// Set up the J frame
		super("Drawing Thing");
		setSize(795, 815);

		// set the mode to initialize so when the paint method runs nothing will happen.
		mode = "initialize";

		// records the mouse data in the frame
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				System.out.println("Clicked at: (" + x + ", " + y + ")");
				Main.x = x;
				Main.y = y;
			}
		});

		setVisible(true);
		repaint();

	}

	public void displayMap(Node[][] grid) {
		// Handler to display the map cause I dont have acess to the Graphics g object
		// in this method and cannot pass what I dont have to the method. it sends
		// needed data to variables to then be used by the helper methods.
		mode = "displayMap";
		this.grid = grid;
		repaint();
	}

	public void displayPath(ArrayList<Node> list) {
		// Handler to display the map cause I dont have acess to the Graphics g object
		// in this method and cannot pass what I dont have to the method. it sends
		// needed data to variables to then be used by the helper methods.
		mode = "displayPath";
		pathList = list;
		repaint();
	}

	public void displayMapHelper(Graphics g) {
		// scrolls through the 2d grid list and draws a black outline and fills in the square with the color specificed in the node class.
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				g.setColor(grid[row][col].getColor());
				g.fillRect(row * squareSize + offsetX, col * squareSize + offsetY, squareSize, squareSize);
				g.setColor(new Color(0, 0, 0));
				g.drawRect(row * squareSize + offsetX, col * squareSize + offsetY, squareSize, squareSize);
			}
		}
	}

	public void displayPathHelperRecursion(Graphics g, int idx) {
		// no possible path check
		if (pathList.size() == 0) {
			JOptionPane.showMessageDialog(null, "Path not possible.");
			return;
		}
		//base case
		if (idx >= pathList.size()) {
			return;
		}
		int row = pathList.get(idx).getRow();
		int col = pathList.get(idx).getCol();
		g.setColor(new Color(155, 20, 255));
		g.fillRect(row * squareSize + offsetX, col * squareSize + offsetY, squareSize, squareSize);
		displayPathHelperRecursion(g, idx + 1);
	}

	public void paint(Graphics g) {
		// the main paint method which sends the graphics g object to the helper methods so they can display stuff the mode variable is used so the paint method knows which function to call.
		switch (mode) {
		case "initialize":
			// do nothing
			break;
		case "displayMap":
			displayMapHelper(g);
			break;
		case "displayPath":
			displayMapHelper(g);
			displayPathHelperRecursion(g, pathList.size());
			break;
		}
	}
}