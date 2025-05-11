import java.util.*;

// Solver class for the 8-puzzle using Uniform Cost Search (UCS)
public class EightPuzzleSolver extends EightPuzzleSolverBase {

    public EightPuzzleSolver() {
        // Set to keep track of explored (settled) nodes
        this.settled = new HashSet<>();
        
        // Priority queue ordered by the cost (distance) to reach each node
        this.priorityQueue = new PriorityQueue<>(Comparator.comparingInt(dist -> distances.getOrDefault(dist, Integer.MAX_VALUE)));
        
        // Map to store the cost (distance) from start node to each node
        this.distances = new HashMap<>();
    }

    // Performs Uniform Cost Search to solve the 8-puzzle
    public int[][] UCS(int[][] initialState) {
        // Create the starting node with cost 0
        Node start = new Node(initialState, 0);
        priorityQueue.add(start);
        distances.put(start, 0);

        while (!priorityQueue.isEmpty()) {
            // Remove the node with the lowest cost
            Node current = priorityQueue.poll();
            extensionsCount++;

            // Check if we have reached the goal state
            if (Arrays.deepEquals(current.state, goalState)) {
                buildOptimalPath(current); // Construct the path from goal to start
                finalCost = distances.get(current); // Record the cost of the solution
                return current.state;
            }

            // Mark current node as explored
            settled.add(current);

            // Explore all 8 possible moves of the empty tile (even though only 4 are usually valid)
            for (int i = 0; i < 8; i++) {
                int newX = current.emptyX + dx[i];
                int newY = current.emptyY + dy[i];

                // Check if the move is within bounds
                if (isValid(newX, newY)) {
                    // Create a new state by swapping the empty tile with the adjacent one
                    int[][] newState = swap(current.state, current.emptyX, current.emptyY, newX, newY);
                    int newCost = distances.get(current) + 1;

                    // Create a new node for the generated state
                    Node newNode = new Node(newState, newCost, newX, newY, current);

                    // Add the node to the queue if not settled and if it's a better path
                    if (!settled.contains(newNode) && (!distances.containsKey(newNode) || newCost < distances.get(newNode))) {
                        distances.put(newNode, newCost); // Update the cost
                        priorityQueue.add(newNode); // Add to priority queue
                    }
                }
            }
        }
        return null; // Return null if no solution found
    }
}
