import java.util.Scanner;

public class Main {
    private static AnalysisViewer analysisViewer;
    
    public static void main(String[] args) {
        GraphManager graphManager = new GraphManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        // Create the analysis viewer but don't show it yet
        analysisViewer = new AnalysisViewer();
        
        System.out.println("Line Following Robot Algorithm Analysis");
        System.out.println("======================================");
        
        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add a node");
            System.out.println("2. Add an edge");
            System.out.println("3. Set start node");
            System.out.println("4. Set end node");
            System.out.println("5. Display graph");
            System.out.println("6. Run algorithm analysis");
            System.out.println("7. Load example graph");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    System.out.print("Enter node ID: ");
                    int nodeId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    graphManager.addNode(nodeId);
                    break;
                    
                case 2:
                    System.out.print("Enter source node: ");
                    int source = scanner.nextInt();
                    System.out.print("Enter destination node: ");
                    int destination = scanner.nextInt();
                    System.out.print("Enter weight/distance: ");
                    int weight = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    graphManager.addEdge(source, destination, weight);
                    break;
                    
                case 3:
                    System.out.print("Enter start node: ");
                    int startNode = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    graphManager.setStartNode(startNode);
                    break;
                    
                case 4:
                    System.out.print("Enter end node: ");
                    int endNode = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    graphManager.setEndNode(endNode);
                    break;
                    
                case 5:
                    graphManager.displayGraph();
                    break;
                    
                case 6:
                    runAlgorithmAnalysis(graphManager);
                    break;
                
                case 7:
                    loadExampleGraph(graphManager, scanner);
                    break;
                    
                case 8:
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void loadExampleGraph(GraphManager graphManager, Scanner scanner) {
        System.out.println("\nLoad Example Graph");
        System.out.println("1. Simple linear track (5 nodes)");
        System.out.println("2. Track with alternative paths (8 nodes)");
        System.out.println("3. Complex grid-like track (12 nodes)");
        System.out.print("Enter example number: ");
        
        int example = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        graphManager.createExampleGraph(example);
    }
    
    private static void runAlgorithmAnalysis(GraphManager graphManager) {
        if (!graphManager.isReadyForAnalysis()) {
            System.out.println("Please ensure the graph has nodes, a start node, and an end node defined.");
            return;
        }
        
        System.out.println("\nRunning Algorithm Analysis...");
        
        // BFS Analysis
        long startTime = System.nanoTime();
        PathResult bfsResult = graphManager.runBFS();
        long endTime = System.nanoTime();
        long bfsDuration = (endTime - startTime) / 1000000; // Convert to milliseconds
        
        // DFS Analysis
        startTime = System.nanoTime();
        PathResult dfsResult = graphManager.runDFS();
        endTime = System.nanoTime();
        long dfsDuration = (endTime - startTime) / 1000000;
        
        // Dijkstra Analysis
        startTime = System.nanoTime();
        PathResult dijkstraResult = graphManager.runDijkstra();
        endTime = System.nanoTime();
        long dijkstraDuration = (endTime - startTime) / 1000000;
        
        // Kruskal Analysis
        startTime = System.nanoTime();
        PathResult aStarResult = graphManager.runKruskal();
        endTime = System.nanoTime();
        long aStarDuration = (endTime - startTime) / 1000000;
        
        // Display brief summary in terminal
        System.out.println("\nAnalysis complete! Summary:");
        System.out.println("BFS:      Time: " + bfsDuration + " ms, Path Length: " + bfsResult.getPathLength());
        System.out.println("DFS:      Time: " + dfsDuration + " ms, Path Length: " + dfsResult.getPathLength());
        System.out.println("Dijkstra: Time: " + dijkstraDuration + " ms, Path Length: " + dijkstraResult.getPathLength());
        System.out.println("Kruskal:  Time: " + aStarDuration + " ms, Path Length: " + aStarResult.getPathLength());
        System.out.println("\nDetailed results are displayed in the GUI window.");
        
        // Display detailed results in GUI
        analysisViewer.displayResults(
            bfsResult, bfsDuration,
            dfsResult, dfsDuration,
            dijkstraResult, dijkstraDuration,
            aStarResult, aStarDuration
        );
    }
} 