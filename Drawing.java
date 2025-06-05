import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

    // Testing purposes
    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(i%3 == 0){
                    Map.grid[i][j] = new Node(i, j, true, false, false);

                }if(i%3 == 1){
                    Map.grid[i][j] = new Node(i, j, false, true, false);

                }if(i%3 == 2){
                    Map.grid[i][j] = new Node(i, j, false, false, true);

                }
            }
        }
        drawing.displayMap(Map.grid);
        //drawing.repaint();
    }

	public Drawing() {
		super("Drawing Thing");
		// Get the screen size
		setSize(screenSize.width, screenSize.height);
		mode = "initialize";

        // Finds clicks made by mouse
        addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            System.out.println("Clicked at: (" + x + ", " + y + ")");
            Main.x = x;
            Main.y = y;
            }
        });
        
        
		setVisible(true);
		// repaint();
	}

	public void displayMap(Node[][] grid) {
		// Handler to display the map cause I dont have acess to the Graphics g object
		// in this method and cannot pass what I dont have to the method.
		mode = "displayMap";
		this.grid = grid;
		repaint(); // Add this to trigger paint() method
	}

	public void displayPath(ArrayList<Node> list) {
		// Handler to display the map cause I dont have acess to the Graphics g object
		// in this method and cannot pass what I dont have to the method.
		mode = "displayPath";
		pathList = list;
		repaint(); // Add this to trigger paint() method
	}

	public void displayMapHelper(Graphics g) {
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
		if (idx >= pathList.size()) {
			return;
		}
		int row = pathList.get(idx).getRow();
		int col = pathList.get(idx).getCol();
		g.setColor(new Color(0, 200, 0));
		g.fillRect(row * squareSize + offsetX, col * squareSize + offsetY, squareSize, squareSize);
		displayPathHelperRecursion(g, idx + 1);
	}


	public void paint(Graphics g) {
		switch (mode) {
		case "initialize":
			// Custom loading screen here if you want
			break;
		case "displayMap":
			displayMapHelper(g);
			break;
		case "displayPath":
			// displayMapHelper(g);
			displayMapHelper(g);
			displayPathHelperRecursion(g, pathList.size());
			break;
		}
	}
}