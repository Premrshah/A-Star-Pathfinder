import java.util.*;

/**
 * This class implements the A* pathfinding algorithm to find the shortest path
 * between a start node and a goal node on a grid.
 * 
 * A* uses heuristics to guide the search toward the goal more efficiently.
 */
public class RoutePlanner {
    private PriorityQueue<Node> openSet;  // what to check
    private List<Node> closedSet;         // what we've already checked

    private Node goal;                    

    /**
     * Creates a new route planner with a start and goal node.
     */
    public RoutePlanner(Node start, Node goal) {
        openSet = new PriorityQueue<>(new NodeComparator());  // sorts nodes by their fCost
        openSet.add(start);                                   // start with just the start node
        closedSet = new ArrayList<>();                        // no nodes checked yet
        this.goal = goal;                                     
    }

    /**
     * Gets the valid neighboring nodes (up, down, left, right) for a given node.
     * Doesn't include diagonal neighbors or nodes outside the grid.
     */
    public List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        
        // These represent the 4 directions: up, down, left, right
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 

        for (int[] d : deltas) {
            int newRow = node.getRow() + d[0];
            int newCol = node.getCol() + d[1];

            // Skip if outside the grid boundaries
            if (newRow < 0 || newRow >= Map.grid.length || newCol < 0 || newCol >= Map.grid[0].length) {
                continue;
            }

            Node neighbor = Map.grid[newRow][newCol];
            neighbors.add(neighbor);
        }

        return neighbors;
    }

    /**
     * The main part of our A* algorithm - finds the shortest path from start to goal.
     */
    public ArrayList<Node> findPath() {
        // Keep going as long as we have nodes to check
        while (openSet.size() > 0) {
            // Grab the best node (lowest fCost)
            Node current = openSet.poll();
            closedSet.add(current);  // Mark node as checked

            // found goal
            if (current.equals(goal)) {
                // trace back through the parents to get the full path
                ArrayList<Node> path = new ArrayList<>();
                Node temp = current;
                while (temp != null) {
                    path.add(0, temp); 
                    temp = temp.parent;  
                }
                return path;
            }

            // Check all the neighbors of our current node
            for (Node neighbor : getNeighbors(current)) {
                // Skip neighbors we've already checked or that are obstacles
                if (closedSet.contains(neighbor) || neighbor.isObstacle()) {
                    continue;
                }

                // Calculate the cost to reach this neighbor through current node
                // (just using 1 as the distance between adjacent nodes)
                int tentativeGCost = current.getGcost() + 1;

                // Condition to check if this path to neighbor is better than any previous one
                if (!openSet.contains(neighbor) || tentativeGCost < neighbor.getGcost()) {
                    // Update the neighbor with better path info
                    neighbor.setgCost(tentativeGCost);
                    
                    // Manhattan distance calculation to get hcost which calculates distance to goal
                    int hCost = Math.abs(neighbor.getRow() - goal.getRow()) + 
                                Math.abs(neighbor.getCol() - goal.getCol());
                    neighbor.sethCost(hCost);
                    neighbor.calculateFcost();
                    
                    // Remember how we got here for backtracking so we adding the nodes we went through for the final arraylist
                    neighbor.parent = current;

                    // Add to our to-do list to check the neighbors if they aren't already there
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    } else {
                        // Java's PriorityQueue doesn't auto-resort when elements change
                        // So we need to remove and re-add to get it in the right order
                        openSet.remove(neighbor);
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // If we get here, we tried everything and there's no path to the goal
        return new ArrayList<Node>();
    }

    

    /**
     * This little helper class tells the PriorityQueue how to sort our nodes.
     * We want the node with the lowest fCost to be at the front of the line to 
     * ensure we choose the best path (this is part of the algorithm to choose the most efficent path).
     */
    class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            // lower fCost gets priority
            return Integer.compare(a.getFCost(), b.getFCost());
        }
    }

    /**
     * Previous testing
     */
    public static void main(String[] args) {
        // Node start = new Node(1, 1, false, true, false);
        // Node goal = new Node(9, 9, false, false, true);
        // for (int i = 0; i < Map.grid.length; i++) {
        //     for (int j = 0; j < Map.grid[0].length; j++) {
        //         Map.grid[i][j] = new Node(i, j, false, false, false);
        //     }
        // }

        // Map.grid[1][1] = start;
        // Map.grid[9][9] = goal;
        // RoutePlanner planner = new RoutePlanner(start, goal);
        // List<Node> path = planner.findPath();
        // for (Node node : path) {
        //     System.out.println("(" + node.getRow() + ", " + node.getCol() + ")");
        // }
    }

}