import java.awt.Color;
import java.util.Objects;

/**
 * This class represents a single node/cell in our grid for the A* pathfinding algorithm.
 * Each node knows its position, what type it is (start, end, obstacle, etc.),
 * and keeps track of the costs used by the A* algorithm.
 */
public class Node {
    private int row, col;                  // position in the grid
    private boolean isObstacle = false;    
    private boolean isStart = false;       
    private boolean isEnd = false;         
    private boolean isRoute = false;
    
    // A* algorithm costs:
	private int fCost, gCost, hCost;       // f = g + h (total cost, distance from start, estimated distance to end)
	Node parent;                           // used to trace back the path once we find the end
    
    public Node(int row, int col, boolean isObstacle, boolean isStart, boolean isEnd) {
        this.row = row;
        this.col = col;
        this.isObstacle = isObstacle;
        this.isStart = isStart;
        this.isEnd = isEnd;
    }


    /**
     * Calculates the total cost (fCost) by adding gCost and hCost.
     * In A*, fCost helps us decide which node to explore next.
     */
    public void calculateFcost() {
        this.fCost = gCost + hCost;  // total cost = cost from start + estimated cost to goal
    }

    /**
     * Get/Is something methods
     */
    public int getRow() {
		return row;
	}

    public boolean isRoute() {
        return this.isRoute;
    }

	public int getCol() {
		return col;
	}

	public int gethCost() {
		return hCost;
	}

    public boolean isObstacle() {
        return isObstacle;
    }


    public int getGcost() {
		return gCost;
	}

	public int getFCost() {
		return this.fCost;
	}

    public boolean isStart() {
        return isStart;
    }

    public boolean isEnd() {
        return isEnd;
    }

	public Node getParent() {
		return parent;
	}

	// Setter methods
	public void setRow(int row) {
		this.row = row;
	}

    public void setStart(boolean isStart) {
        this.isStart = isStart;
    }

    public void setIsRoute(boolean isRoute) {
        this.isRoute = isRoute;
    }

	public void setCol(int col) {
		this.col = col;
	}

    public void setEnd(boolean end) {
        this.isEnd = end;
    }

	public void setCoordinates(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public void setObstacle(boolean obstacle) {
		this.isObstacle = obstacle;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}

	public void setgCost(int gCost) {
		this.gCost = gCost;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
    
    /**
     * Returns the color to display this node based on its type.
     * Green for start, red for end, black for obstacles,
     * purple for the path, and white for empty spaces.
     */
    public Color getColor() {
        Color color = new Color(0,0,0);
        if (isStart) {
            color = new Color(5,250,5);      // bright green for start
        } else if (isEnd) {
            color = new Color(245,5,5);      // bright red for end
        } else if (isObstacle) {
            color = new Color(0,0,0);        // black for obstacles
        } else if (isRoute) {
            color = new Color(155,20,255);   // purple for the path
        } else {
            color = new Color(255,255,255);  // white for empty spaces
        }
        return color;        
    }

    public void resetColor() {
        this.isStart = false;
        this.isStart = false;
        this.isObstacle = false;
        this.isRoute = false;
    }

    /**
     * Checks if two nodes are the same by comparing their positions.
     * We need to override this method from Object class so our contains() checks work.
     * 
     * Basically saying If two nodes are at the same spot in the grid,
     * they're the same node as far as we care
     */
    public boolean equals(Object obj) {
        // Quick check - if it's literally the same object in memory
        if (this == obj) 
            return true;
        
        // Cast to Node so we can access its fields
        Node other = (Node) obj;
        
        // Two nodes are equal if they have the same row and column
        return this.row == other.getRow() && this.col == other.getCol();
    }
    
    /**
     * This goes hand-in-hand with equals()
     * If two objects are equal, they MUST have the same hash code.
     * 
     * We have this to make sure each position in the grid has "a unique ID number".
     * This will make sure the HashSets and PriorityQueues work and don't get confused.
     */
    @Override
    public int hashCode() {
        // Objects.hash is a neat way to combine multiple values into one hash code
        return Objects.hash(row, col);
    }

}