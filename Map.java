import java.awt.Color;
import java.util.*;

import javax.swing.JFrame;

/**
 * This class represents our grid world where the pathfinding happens. It keeps
 * track of all the nodes in a 2D grid and handles displaying them.
 */
public class Map {

	// Our grid of nodes - static so it can be accessed from anywhere
	public static Node[][] grid = new Node[10][10];
	int startRow, startCol, endRow, endCol;
	Drawing display;

	/**
	 * Creates a fresh 10x10 grid with empty nodes and sets up the display.
	 */
	public Map() {
		// Create the window where we'll show our map
		display = new Drawing();

		// Make a 10x10 2d array of nodes
		grid = new Node[10][10];
		for (int row = 0; row < grid.length; row++) {
			for (int col = 0; col < grid[row].length; col++) {
				grid[row][col] = new Node(row, col, false, false, false);
			}
		}

	}

	/**
	 * Shows the current state of the map on screen with all the colors
	 */
	public void displayMap() {
		display.displayMap(grid);
	}

	/**
	 * Highlights the path found by the A* algorithm on the map.
	 */
	public void displayPath(ArrayList<Node> list) {
		display.displayPath(list);
	}
}