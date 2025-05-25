import java.util.*;

public abstract class EightPuzzleSolverBase {
	
	public static final String YELLOW = "\u001B[33m";
	public static final String RESET  = "\u001B[0m";

    protected PriorityQueue<Node> priorityQueue;  // Frontier nodes to explore (priority-based)
    protected Set<Node> settled;                  // Set of already visited/expanded nodes
    protected Map<Node, Integer> distances;       // Map storing the cost (g) from start to each node

    // The goal configuration of the 8-puzzle
    protected int[][] goalState = {
            {6, 5, 4},
            {7, 0, 3},
            {8, 1, 2}
    };

    // Possible moves for the empty tile (up, down, left, right, diagonals)
    protected int[] dx = {0, 1, 0, -1, 1, 1, -1, -1};
    protected int[] dy = {1, 0, -1, 0, 1, -1, -1, 1};

    protected List<int[][]> optimalPath = new ArrayList<>();  // Stores the solution path from start to goal
    protected int extensionsCount = 0;                        // Number of nodes expanded during search
    protected int finalCost = -1;                             // Total cost of the optimal solution path

    // Builds the optimal path by following parent links from the goal node to the start node
    protected void buildOptimalPath(Node node) {
        optimalPath.clear();
        while (node != null) {
            optimalPath.add(0, node.state);  // Insert at the beginning to reverse the order
            node = node.parent;
        }
    }

    // Checks if the given coordinates are within puzzle bounds
    protected boolean isValid(int x, int y) {
        return x >= 0 && x < 3 && y >= 0 && y < 3;
    }

    // Swaps the empty tile with a neighbor to generate a new state
    protected int[][] swap(int[][] state, int x1, int y1, int x2, int y2) {
        int[][] newState = copyState(state);  // Create a deep copy of the state
        int temp = newState[x1][y1];
        newState[x1][y1] = newState[x2][y2];
        newState[x2][y2] = temp;
        return newState;
    }

    // Creates a deep copy of a 3x3 puzzle state
    protected int[][] copyState(int[][] state) {
        int[][] newState = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(state[i], 0, newState[i], 0, 3);
        }
        return newState;
    }

    // Prints a single puzzle state
    public static void printState(int[][] state) {
        for (int[] row : state) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("=========");
    }

    // Prints all states in the path from start to goal
    public static void printPath(List<int[][]> path) {
		System.out.println(YELLOW);
        for (int[][] state : path) {
            printState(state);
            try { Thread.sleep(600); } catch (InterruptedException e) { e.printStackTrace(); }
        }
		System.out.println(RESET);
		
    }

    // Returns how many nodes were expanded during the search
    public int getExtensionsCount() {
        return extensionsCount;
    }

    // Returns the path of states from initial to goal
    public List<int[][]> getOptimalPath() {
        return optimalPath;
    }

    // Returns the cost of the final solution path
    public int getCost() {
        return finalCost;
    }
}
