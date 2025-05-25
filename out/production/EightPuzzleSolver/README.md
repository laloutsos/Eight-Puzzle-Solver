
# 🧩 8-Puzzle Solver — Java Command-Line App

Solve the classic 8-puzzle problem with three modes:

✅ **Uniform Cost Search (UCS)**  
⭐ **A* Search Algorithm**  
🕹️ **Manual Play Mode**

## 🎯 Objective  
Rearrange the tiles to match the goal state using legal moves (up/down/left/right) with the least cost or steps.

## 🚀 Features  
- Choose from 10 pre-configured initial states  
- Console display of puzzle state and results  
- Compare UCS vs. A* (cost, time, node expansions)  
- Manual mode to play the puzzle yourself  
- Clean terminal UI with input validation  
- Option to run multiple tests in one session

## 🎮 How It Works  
When you launch the app:
1. Goal state is displayed  
2. Choose from 10 initial states  
3. Select a mode:  
   1️⃣ UCS  
   2️⃣ A*  
   3️⃣ Manual  
4. Solver runs and prints:  
   - ✔ Optimal path  
   - ✔ Total cost  
   - ✔ Nodes expanded  
   - ✔ Execution time  
5. Prompt to rerun another test

## 🛠️ Built With  
- Java (Standard Library only)  
- Console I/O — no GUI, simple and effective

## 📂 Project Structure  
```
PuzzleSolver.java         // Main driver class  
EightPuzzleSolver.java    // UCS implementation  
EightPuzzleSolverAstr.java // A* implementation  
ManualSolver.java         // Manual play logic
EigthPuzzleSolverBase.java //Abstract Super class of EightPuzzleSolver.java and EightPuzzleSolverAstr.java
Node.java // Node object for implementation of the methods
```

## 🧠 Example Usage
```
Open the folder of the project and:
$ javac *.java  
$ java PuzzleSolver  
```
Follow the on-screen instructions to explore and compare solving strategies!

## 📌 Goal State  
```
[6, 5, 4]  
[7, 0, 3]  
[8, 1, 2]  
```

## 💡 Future Ideas  
- Add more A* heuristics  
- Export results (path, time, cost) to a file  
