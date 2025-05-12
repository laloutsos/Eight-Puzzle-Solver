import java.util.*;

// Main class to run and test 8-puzzle solver with different algorithms
public class PuzzleSolver { 

	public static final String YELLOW = "\u001B[33m";
	public static final String RESET  = "\u001B[0m";
	public static final String CYAN = "\u001B[36m";

	// Predefined goal state of the 8-puzzle
	private static int[][] goalState = {
        {6, 5, 4},
        {7, 0, 3},
        {8, 1, 2}
    };	

    // Utility function to print a puzzle state
    public static void printState(int[][] state) {
        for (int[] row : state) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("=========");
	}
    

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Input scanner
		int alg = 0;

		// Predefined initial puzzle states for testing
        int[][] initialState1 = { {6, 0, 4}, {1, 7, 3}, {8, 5, 2} };
		int[][] initialState2 = { {4, 0, 8}, {1, 7, 6}, {3, 5, 2} };
		int[][] initialState3 = { {6, 5, 0}, {7, 1, 3}, {8, 4, 2} };
		int[][] initialState4 = { {6, 5, 4}, {3, 2, 7}, {8, 1, 0} };
		int[][] initialState5 = { {6, 5, 4}, {0, 1, 3}, {8, 7, 2} };
		int[][] initialState6 = { {1, 2, 3}, {4, 5, 6}, {0, 7, 8} };
		int[][] initialState7 = { {7, 2, 4}, {5, 0, 6}, {8, 3, 1} };
		int[][] initialState8 = { {0, 8, 7}, {6, 5, 4}, {3, 2, 1} };
		int[][] initialState9 = { {2, 8, 3}, {1, 6, 4}, {7, 0, 5} };
		int[][] initialState10 = { {8, 6, 7}, {2, 5, 4}, {3, 0, 1} };
		
		int choice = 0;

		while (true) {
			System.out.println("Welcome to Eight Puzzle!");
			
			System.out.println(YELLOW + "Goal State is:");
			
			printState(goalState); // Display goal state
			
			System.out.print(RESET);
			
			
			System.out.println("Choose the initial state: ");
			
			System.out.print(CYAN);
			
			// List of all predefined initial states
			int[][][] allStates = {
				initialState1, initialState2, initialState3, initialState4, initialState5,
				initialState6, initialState7, initialState8, initialState9, initialState10
			};

			// Display initial state options grouped neatly
			for (int rowGroup = 0; rowGroup < allStates.length; rowGroup += 5) {
				for (int i = rowGroup; i < rowGroup + 5 && i < allStates.length; i++) {
					System.out.print("Option " + (i + 1) + ":\t");
				}
				System.out.println();
				for (int row = 0; row < 3; row++) {
					for (int i = rowGroup; i < rowGroup + 5 && i < allStates.length; i++) {
						System.out.print(Arrays.toString(allStates[i][row]) + "\t");
					}
					System.out.println();
				}
				System.out.println("=".repeat(80));
			}

			System.out.println(RESET);
			
			// Get user input for initial state choice
			while (true) {
				System.out.print("Enter your choice (1-10): ");
				if (scan.hasNextInt()) {
					choice = scan.nextInt();
					if (choice > 0 && choice < 11) {
						break;
					} else {
						System.out.println("Invalid choice, please enter 1 to 10.");
					}
				} else {
					System.out.println("Invalid input. Please enter a number.");
					scan.next(); // consume invalid input
				}
			}	
			
			
			int[][] initialState = allStates[choice - 1];
			System.out.println("Initial State is:");
			System.out.print(CYAN);
			printState(initialState); // Display chosen initial state
			System.out.print(RESET);
			
			// Prompt for algorithm selection
			while (true) {
				System.out.print("Choose the solution algorithm: \nUCS: 1\nA*: 2\nManual play: 3\n");
				if (scan.hasNextInt()) {
					choice = scan.nextInt();
					if (choice == 1 || choice == 2 || choice == 3) {
						break;
					} else {
						System.out.println("Invalid choice, please enter 1, 2, or 3.");
					}
				} else {
					System.out.println("Invalid input. Please enter a number.");
					scan.next();
				}
			}
			alg = choice;
			
			if (alg == 1) {
				// UCS selected
				EightPuzzleSolver solver = new EightPuzzleSolver();
				long startTime = System.nanoTime(); 
				System.out.println("Finding solution...");
				int[][] solution = solver.UCS(initialState);
				long endTime = System.nanoTime();
				double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;

				// Print results
				if (solution != null) {
					EightPuzzleSolver.printPath(solver.getOptimalPath());
					System.out.print(CYAN);
					System.out.println("Cost: " + solver.getCost());
					System.out.println("---------");
					System.out.println("Number of node extensions: " + solver.getExtensionsCount());
					System.out.println("---------");
					System.out.printf("Execution time: %.4f seconds%n", durationInSeconds);
					System.out.println("---------");
					System.out.print(RESET);
				} else {
					System.out.println("No solution found.");
				}
				
			} else if (alg == 2) {
				// A* selected
				EightPuzzleSolverAstr solver = new EightPuzzleSolverAstr();
				long startTime = System.nanoTime();
				System.out.println("Finding solution...");
				int[][] solution = solver.Astar(initialState);
				long endTime = System.nanoTime();
				double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;

				// Print results
				if (solution != null) {
					EightPuzzleSolverAstr.printPath(solver.getOptimalPath());
					System.out.print(CYAN);
					System.out.println("Cost: " + solver.getCost());
					System.out.println("---------");
					System.out.println("Number of node extensions: " + solver.getExtensionsCount());
					System.out.println("---------");
					System.out.printf("Execution time: %.4f seconds%n", durationInSeconds);
					System.out.println("---------");
					System.out.print(RESET);
					
				} else {
					System.out.println("No solution found.");
				}

			} else if (alg == 3) {
				// Manual play selected
				System.out.println("The fewer moves you make, the better!");
				System.out.println( YELLOW + "IMPORTANT: YOU ALWAYS MOVE THE EMPTY POSITION (0) TOWARDS A PUZZLE PIECE.\nBy this way,the positions are switched. " + RESET);
				ManualSolver manualSolver = new ManualSolver();
				manualSolver.play(initialState, scan);
			}			

			// Ask user if they want to run again
			choice = 0;
			while (true) {
				System.out.println("Run again?\nYes: 1\nNo: 2");
				if (scan.hasNextInt()) {
					choice = scan.nextInt();
					if (choice == 1 || choice == 2) {
						break;
					} else {
						System.out.println("Invalid choice, please enter 1 or 2.");
					}
				} else {
					System.out.println("Invalid input. Please enter a number.");
					scan.next();
				}
			}

			// Restart or exit
			if (choice == 1) {
				System.out.println("Running again in:");
				for (int i = 3; i >= 1; i--) {
					System.out.print(i);
					try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
					System.out.print(".");
					try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
					System.out.print(".");
					try { Thread.sleep(150); } catch (InterruptedException e) { e.printStackTrace(); }
					System.out.print(".");
				}
				System.out.println("");
			} else {
				System.out.println("Goodbye!");
				scan.close(); // Clean up scanner resource
				break;
			}	
		}
	}
}
