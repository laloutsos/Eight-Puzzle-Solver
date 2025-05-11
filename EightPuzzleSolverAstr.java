import java.util.*;

// Solver class for the 8-puzzle using A* Search Algorithm
public class EightPuzzleSolverAstr extends EightPuzzleSolverBase {

    public EightPuzzleSolverAstr() {
        // Set to track explored nodes (visited states)
        this.settled = new HashSet<>();

        // Map to store the actual cost (g(n)) from start to each node
        this.distances = new HashMap<>();

        // Priority queue ordered by total estimated cost f(n) = g(n) + h(n)
        this.priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(this::getTotalCost)
                        .thenComparingInt(node -> calculateHeuristic(node.state)) // Tie-breaker: lower heuristic
        );
    }

    // A* search algorithm implementation
    public int[][] Astar(int[][] initialState) {
        // Create start node with cost 0
        Node start = new Node(initialState, 0);
        priorityQueue.add(start);
        distances.put(start, 0);

        while (!priorityQueue.isEmpty()) {
            // Remove node with the lowest f(n)
            Node current = priorityQueue.poll();
            extensionsCount++;

            // Check if goal state is reached
            if (Arrays.deepEquals(current.state, goalState)) {
                finalCost = distances.get(current); // Save final cost
                buildOptimalPath(current); // Build the solution path
                return current.state;
            }

            // Mark node as explored
            settled.add(current);

            // Explore all 8 possible directions (4 valid for 8-puzzle)
            for (int i = 0; i < 8; i++) {
                int newX = current.emptyX + dx[i];
                int newY = current.emptyY + dy[i];

                // Skip invalid moves
                if (isValid(newX, newY)) {
                    // Generate new puzzle state after move
                    int[][] newState = swap(current.state, current.emptyX, current.emptyY, newX, newY);
                    int newCost = distances.get(current) + 1; // g(n) = parent cost + 1

                    // Create a new node
                    Node newNode = new Node(newState, newCost, newX, newY, current);
                    int totalCost = newCost + calculateHeuristic(newState); // f(n) = g(n) + h(n)

                    // If node not settled and new path is cheaper, add to queue
                    if (!settled.contains(newNode) && distances.getOrDefault(newNode, Integer.MAX_VALUE) > newCost) {
                        distances.put(newNode, newCost); // Update cost
                        priorityQueue.add(newNode); // Add to priority queue
                    }
                }
            }
        }

        return null; // No solution found
    }

    // Calculates total cost f(n) = g(n) + h(n)
    private int getTotalCost(Node node) {
        return distances.getOrDefault(node, 0) + calculateHeuristic(node.state);
    }

    // Heuristic: Manhattan distance for each tile from goal position
    private int calculateHeuristic(int[][] state) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != goalState[i][j] && state[i][j] != 0) {
                    // Calculate expected coordinates in goal state
                    int goalX = (state[i][j] - 1) / 3;
                    int goalY = (state[i][j] - 1) % 3;
                    count += Math.abs(i - goalX) + Math.abs(j - goalY); // Manhattan distance
                }
            }
        }
        return count;
    }
}
