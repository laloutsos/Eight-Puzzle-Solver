import java.util.*;
import java.util.Scanner;

// ManualSolver allows the user to solve the 8-puzzle manually via keyboard inputs
public class ManualSolver extends EightPuzzleSolverBase {
	
	public static final String RED = "\u001B[31m";
	public static final String RESET  = "\u001B[0m";

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

			boolean validInput = true;

			switch (input) {
				case "w": if (zx > 0) newState = swap(state, zx, zy, zx - 1, zy); break;
				case "s": if (zx < 2) newState = swap(state, zx, zy, zx + 1, zy); break;
				case "a": if (zy > 0) newState = swap(state, zx, zy, zx, zy - 1); break;
				case "d": if (zy < 2) newState = swap(state, zx, zy, zx, zy + 1); break;
				case "u": if (zx > 0 && zy < 2) newState = swap(state, zx, zy, zx - 1, zy + 1); break;
				case "y": if (zx > 0 && zy > 0) newState = swap(state, zx, zy, zx - 1, zy - 1); break;
				case "h": if (zx < 2 && zy > 0) newState = swap(state, zx, zy, zx + 1, zy - 1); break;
				case "j": if (zx < 2 && zy < 2) newState = swap(state, zx, zy, zx + 1, zy + 1); break;
				case "q": System.exit(0); break;
				default:
					validInput = false;
			}

			if (!validInput) {
				System.out.print(RED);
				System.out.println("Invalid move.");
				System.out.print(RESET);
			} else if (newState != null) {
				score++;
				state = newState;
			} else {
				System.out.print(RED);
				System.out.println("Move not allowed.");
				System.out.print(RESET);
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
