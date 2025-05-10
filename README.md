# Line Following Robot Algorithm Analysis

This Java application allows for the analysis of various pathfinding algorithms used in line-following robots. It compares the performance of Breadth-First Search (BFS), Depth-First Search (DFS), Dijkstra's Algorithm, and A* Algorithm on a user-defined graph representing a track.

## Features

- Build a graph representing a robot's track using an adjacency list
- Add nodes and weighted edges representing the track path
- Set start and end points for path calculation
- Analyze and compare the performance of four pathfinding algorithms:
  - Breadth-First Search (BFS)
  - Depth-First Search (DFS)
  - Dijkstra's Algorithm
  - A* Algorithm
- View detailed performance metrics for each algorithm
  - Execution time
  - Path length
  - Number of nodes explored
  - Time complexity
  - Space complexity

## Application Versions

### Console Application

The console application provides a command-line interface with a menu-driven approach.

**How to Run:**
1. Compile the Java files: `javac src/*.java`
2. Run the program: `java -cp src Main`
3. Or use the provided batch script: `.\compile_and_run.bat`

### GUI Application

The GUI version provides a more user-friendly interface with tabs for graph management and algorithm analysis.

**How to Run:**
1. Compile the Java files: `javac src/*.java`
2. Run the program: `java -cp src MainGUI`
3. Or use the provided batch script: `.\compile_and_run_gui.bat`

## Creating a Graph

### Using the Console Application
1. Add nodes representing junctions or decision points on the track
2. Add edges with weights representing distances or complexity between nodes
3. Set a start node (robot's starting position)
4. Set an end node (destination)
5. Run algorithm analysis to see which algorithm performs best
6. Optionally, load example graphs for testing

### Using the GUI Application
1. Use the "Graph Management" tab to add nodes and edges
2. Load example graphs with the "Load Example" button
3. Set start and end nodes
4. Click "Run Algorithm Analysis" to switch to the results tab

## Algorithm Comparison

The application analyzes four algorithms, each with its own strengths:

- **BFS**: Finds the shortest path in terms of number of edges (when all weights are equal)
  - Time Complexity: O(V + E) 
  - Space Complexity: O(V)

- **DFS**: Quick for deep paths, but not optimal for shortest paths
  - Time Complexity: O(V + E)
  - Space Complexity: O(V)

- **Dijkstra's Algorithm**: Finds the shortest path considering edge weights
  - Time Complexity: O(E log V)
  - Space Complexity: O(V)

- **A* Algorithm**: Usually the fastest due to its heuristic-guided search
  - Time Complexity: O(E log V)
  - Space Complexity: O(V)

Where V = number of vertices (nodes) and E = number of edges.

## Implementation Details

- Graph representation using adjacency list
- Detailed path tracing and metrics collection
- Time complexity analysis for algorithmic comparison
- Performance timing using System.nanoTime() 
