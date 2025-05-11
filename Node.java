import java.util.*;

public class Node {
    int[][] state;     // The current puzzle state (3x3 matrix)
    int emptyX;        // Row index of the empty tile (0)
    int emptyY;        // Column index of the empty tile (0)
    Node parent;       // Reference to the parent node (used for path tracing)

    // Constructor that initializes a node with the given state
    // and automatically finds the position of the empty tile
    public Node(int[][] state, int cost) {
        this.state = state;
        findEmptyPosition(state);
    }

    // Constructor that initializes a node with more information
    public Node(int[][] state, int cost, int emptyX, int emptyY, Node parent) {
        this.state = state;
        this.emptyX = emptyX;
        this.emptyY = emptyY;
        this.parent = parent;
    }

    // Finds the position (row and column) of the empty tile (represented by 0)
    private void findEmptyPosition(int[][] state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    emptyX = i;
                    emptyY = j;
                    return;
                }
            }
        }
    }

    // Two nodes are equal if their puzzle states are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Arrays.deepEquals(state, node.state);
    }

    // Use deepHashCode to generate consistent hash for 2D array
    @Override
    public int hashCode() {
        return Arrays.deepHashCode(state);
    }
}
