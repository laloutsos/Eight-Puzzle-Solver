import java.util.*;
import java.util.Scanner;

// ManualSolver allows the user to solve the 8-puzzle manually via keyboard inputs
public class ManualSolver extends EightPuzzleSolverBase {

    // Starts the manual play session
    public void play(int[][] initialState, Scanner scan) {
        int[][] state = copyState(initialState); // Create a working copy of the initial state
        int score = 0; // Tracks number of valid moves made by the user

        while (true) {
            printState(state); // Show current puzzle state

            // Check if the current state is the goal
            if (isGoal(state)) {
                System.out.println("Goal reached!\nMoves: " + score);
                break;
            }

            // Prompt user for input
            System.out.println("Enter move:");
            System.out.println("(w=up, s=down, a=left, d=right,");
            System.out.println(" u=up-right, y=up-left, h=down-left, j=down-right, q=quit):");

            String input = scan.next().toLowerCase();

            // Allow quitting the manual mode
            if (input.equals("q")) {
                System.out.println("Exiting manual mode.");
                break;
            }

            // Find the coordinates of the empty tile (0)
            int[] zeroPos = findZero(state);
            int zx = zeroPos[0], zy = zeroPos[1];

            int[][] newState = null;

            // Handle user input and perform corresponding move if valid
            switch (input) {
                case "w": if (zx > 0) newState = swap(state, zx, zy, zx - 1, zy); break; // Move up
                case "s": if (zx < 2) newState = swap(state, zx, zy, zx + 1, zy); break; // Move down
                case "a": if (zy > 0) newState = swap(state, zx, zy, zx, zy - 1); break; // Move left
                case "d": if (zy < 2) newState = swap(state, zx, zy, zx, zy + 1); break; // Move right
                case "u": if (zx > 0 && zy < 2) newState = swap(state, zx, zy, zx - 1, zy + 1); break; // Up-right
                case "y": if (zx > 0 && zy > 0) newState = swap(state, zx, zy, zx - 1, zy - 1); break; // Up-left
                case "h": if (zx < 2 && zy > 0) newState = swap(state, zx, zy, zx + 1, zy - 1); break; // Down-left
                case "j": if (zx < 2 && zy < 2) newState = swap(state, zx, zy, zx + 1, zy + 1); break; // Down-right
                default:
                    System.out.println("Invalid move.");
            }

            // Apply the move if valid
            if (newState != null) {
                score++; // Increment move count
                state = newState;
            } else {
                System.out.println("Move not allowed.");
            }
        }
    }

    // Checks if the current state is the goal state
    private boolean isGoal(int[][] state) {
        return Arrays.deepEquals(state, goalState);
    }

    // Finds the coordinates of the empty tile (value 0)
    private int[] findZero(int[][] state) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (state[i][j] == 0)
                    return new int[]{i, j};
        return null; // Should never reach here if state is valid
    }
}
