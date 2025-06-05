import java.awt.*;
import java.util.*;

/**
 * Main class that controls the flow of our pathfinding program.
 * Handles user interactions and coordinates between UI, Map, and RoutePlanner.
 */
public class Main {
    // Program state: what the user is currently doing
    public static int state = 0; // 0=init, 1=set start, 2=set end, 3=add obstacles, 4=run algorithm
    
    // Mouse click coordinates from the Drawing class
    public static int x = 0;
    public static int y = 0;
    
    
    // Main nodes that the user will place on the grid
    public static Node startNode;
    public static Node endNode;
    public static Node obstacleNode;

   public static void main(String[] args) {
        // Keep track of previous mouse position to detect changes
        int prevRow = 0;
        int prevCol = 0;

        // Create the control panel window
        UI frame = new UI();
        
        // Initialize our special nodes (will be placed by user later)
        startNode = new Node(0, 0, false, false, false);
        endNode = new Node(0, 0, false, false, false);
        obstacleNode = new Node(0, 0, false, false, false);

        int row;
        int col;

        Map map = new Map();
        map.displayMap();

        // Main program loop - keeps running until we find a path
        while (true) {
            // Convert mouse coordinates to grid row/column
            col = Math.min((int)((Main.y - 40)/75), 10);
            row = Math.min((int)((Main.x - 20)/75),10);
            
            // State 1: User is placing the start node
            if (state == 1) {
                startNode = new Node(row, col, false, true, false);

                if (!(prevRow == row && prevCol == col)) {
                    for (int i = 0; i < Map.grid.length; i++) {
                        for (int j = 0; j < Map.grid[0].length; j++) {
                            if (Map.grid[i][j].isStart()) {
                                Map.grid[i][j].setStart(false);
                            }
                        }
                    }
                }

                Map.grid[row][col] = new Node(row, col, false, true, false);
            // State 2: User is placing the end node
            } else if (state == 2) {
                endNode = new Node(row, col, false, false, true);


                if (!(prevRow == row && prevCol == col)) {
                    for (int i = 0; i < Map.grid.length; i++) {
                        for (int j = 0; j < Map.grid[0].length; j++) {
                            if (Map.grid[i][j].isEnd()) {
                                Map.grid[i][j].setEnd(false);
                            }
                        }
                    }
                }

                if (!(Map.grid[endNode.getRow()][endNode.getCol()].equals(Map.grid[startNode.getRow()][startNode.getCol()])))
                    Map.grid[row][col] = new Node(row, col, false, false, true);
            // State 3: User is placing obstacles
            } else if (state == 3) {
                obstacleNode = new Node(row, col, true, false, false);
                if (!(Map.grid[endNode.getRow()][endNode.getCol()].equals(Map.grid[obstacleNode.getRow()][obstacleNode.getCol()])) && !(Map.grid[startNode.getRow()][startNode.getCol()].equals(Map.grid[obstacleNode.getRow()][obstacleNode.getCol()])))
                    Map.grid[row][col] = new Node(row, col, true, false, false);
            // State 4: Running the A* algorithm to find the path
            } else if (state == 4) {
                System.out.println("Route planning rn");
                
                // Create a route planner with our start and end points
                RoutePlanner planner = new RoutePlanner(startNode, endNode);
                
                ArrayList<Node> path = planner.findPath();
                
                // Mark all nodes in the path as route nodes (for display)
                for (Node n: path) {
                    n.setIsRoute(true);
                }
                
                System.out.println(path);
                break;  // Exit the loop once we've found the path
            }

            map.displayMap(); 

            prevRow = row;
            prevCol = col;
        }

 
        }
   }
   
  