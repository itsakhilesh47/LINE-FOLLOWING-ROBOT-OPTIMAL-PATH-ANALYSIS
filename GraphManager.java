import java.util.*;

public class GraphManager {
    private Map<Integer, List<Edge>> adjacencyList;
    private int startNode;
    private int endNode;
    private boolean startNodeSet;
    private boolean endNodeSet;
    
    public GraphManager() {
        adjacencyList = new HashMap<>();
        startNodeSet = false;
        endNodeSet = false;
    }
    
    // Getter methods for GUI
    public Set<Integer> getNodeIds() {
        return adjacencyList.keySet();
    }
    
    public List<Edge> getEdges(Integer nodeId) {
        return adjacencyList.getOrDefault(nodeId, new ArrayList<>());
    }
    
    public boolean isStartNodeSet() {
        return startNodeSet;
    }
    
    public boolean isEndNodeSet() {
        return endNodeSet;
    }
    
    public int getStartNode() {
        return startNode;
    }
    
    public int getEndNode() {
        return endNode;
    }
    
    // Create a predefined graph for testing
    public void createExampleGraph(int exampleNumber) {
        // Clear existing graph
        adjacencyList.clear();
        startNodeSet = false;
        endNodeSet = false;
        
        switch (exampleNumber) {
            case 1: // Simple linear track
                for (int i = 1; i <= 5; i++) {
                    addNode(i);
                }
                addEdge(1, 2, 10);
                addEdge(2, 3, 15);
                addEdge(3, 4, 12);
                addEdge(4, 5, 8);
                setStartNode(1);
                setEndNode(5);
                //1<--10-->2<----15--->3<---12--->4<----8---->5
                System.out.println("Created linear track with 5 nodes.");
                break;
                
            case 2: // Track with alternative paths
                for (int i = 1; i <= 8; i++) {
                    addNode(i);
                }
                // Main path
                addEdge(1, 2, 10);
                addEdge(2, 3, 12);
                addEdge(3, 6, 15);
                addEdge(6, 8, 10);
                
                // Alternative path
                addEdge(1, 4, 5);
                addEdge(4, 5, 20);
                addEdge(5, 7, 18);
                addEdge(7, 8, 8);
                
                // Connecting paths
                addEdge(2, 5, 14);
                addEdge(3, 7, 16);
                
                setStartNode(1);
                setEndNode(8);
                System.out.println("Created track with alternative paths (8 nodes).");
                break;
                
            case 3: // Complex grid-like track
                for (int i = 1; i <= 12; i++) {
                    addNode(i);
                }
                
                // First row
                addEdge(1, 2, 8);
                addEdge(2, 3, 7);
                addEdge(3, 4, 9);
                
                // Second row
                addEdge(5, 6, 10);
                addEdge(6, 7, 12);
                addEdge(7, 8, 6);
                
                // Third row
                addEdge(9, 10, 11);
                addEdge(10, 11, 9);
                addEdge(11, 12, 8);
                
                // Vertical connections
                addEdge(1, 5, 14);
                addEdge(2, 6, 10);
                addEdge(3, 7, 15);
                addEdge(4, 8, 12);
                addEdge(5, 9, 9);
                addEdge(6, 10, 11);
                addEdge(7, 11, 10);
                addEdge(8, 12, 7);
                
                // Diagonal connections
                addEdge(1, 6, 18);
                addEdge(2, 7, 17);
                addEdge(3, 8, 19);
                addEdge(5, 10, 16);
                addEdge(6, 11, 14);
                addEdge(7, 12, 15);
                
                setStartNode(1);
                setEndNode(12);
                System.out.println("Created complex grid-like track (12 nodes).");
                break;
                
            default:
                System.out.println("Invalid example number. No graph created.");
        }
    }
    
    public void addNode(int nodeId) {
        if (!adjacencyList.containsKey(nodeId)) {
            adjacencyList.put(nodeId, new ArrayList<>());
            System.out.println("Node " + nodeId + " added successfully.");
        } else {
            System.out.println("Node " + nodeId + " already exists.");
        }
    }
    
    public void addEdge(int source, int destination, int weight) {
        if (!adjacencyList.containsKey(source)) {
            System.out.println("Source node " + source + " does not exist. Adding it first.");
            addNode(source);
        }
        
        if (!adjacencyList.containsKey(destination)) {
            System.out.println("Destination node " + destination + " does not exist. Adding it first.");
            addNode(destination);
        }
        
        // Add edge (in both directions for undirected graph)
        adjacencyList.get(source).add(new Edge(destination, weight));// SOURCE <----WEIGHT----> DESTINATION 
        adjacencyList.get(destination).add(new Edge(source, weight));// DESTINATION <-----WEIGHT------> SOURCE
        System.out.println("Edge added: " + source + " <--> " + destination + " (weight: " + weight + ")");
    }
    
    public void setStartNode(int nodeId) {
        if (adjacencyList.containsKey(nodeId)) {
            startNode = nodeId;
            startNodeSet = true;
            System.out.println("Start node set to " + nodeId);
        } else {
            System.out.println("Node " + nodeId + " does not exist. Please add it first.");
        }
    }
    
    public void setEndNode(int nodeId) {
        if (adjacencyList.containsKey(nodeId)) {
            endNode = nodeId;
            endNodeSet = true;
            System.out.println("End node set to " + nodeId);
        } else {
            System.out.println("Node " + nodeId + " does not exist. Please add it first.");
        }
    }
    
    public void displayGraph() {
        System.out.println("\nGraph Structure (Adjacency List):");
        for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) {
            System.out.print("Node " + entry.getKey() + " -> ");
            List<Edge> edges = entry.getValue();
            
            if (edges.isEmpty()) {
                System.out.println("No connections");
            } else {
                for (int i = 0; i < edges.size(); i++) {
                    Edge edge = edges.get(i);
                    System.out.print(edge.getDestination() + " (weight: " + edge.getWeight() + ")");
                    if (i < edges.size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
        
        System.out.println("\nStart Node: " + (startNodeSet ? startNode : "Not set"));
        System.out.println("End Node: " + (endNodeSet ? endNode : "Not set"));
    }
    
    public boolean isReadyForAnalysis() {
        return !adjacencyList.isEmpty() && startNodeSet && endNodeSet;
    }
    
    // Breadth-First Search Algorithm
    public PathResult runBFS() {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int nodesExplored = 0;///Counts the number of nodes explored during the search.
        
        queue.add(startNode);
        visited.add(startNode);
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            nodesExplored++;
            
            if (current == endNode) {
                break;
            }
            
            for (Edge edge : adjacencyList.get(current)) {
                int neighbor = edge.getDestination();
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    parent.put(neighbor, current);
                }
            }
        }
        
        // Reconstruct path and calculate length
        List<Integer> path = new ArrayList<>();
        int pathLength = 0;
        
        if (parent.containsKey(endNode) || startNode == endNode) {
            int current = endNode;
            while (current != startNode) {
                path.add(0, current);//This ensures the path is constructed in the correct order from start to end.

                int parentNode = parent.get(current);
                
                // Find edge weight
                for (Edge edge : adjacencyList.get(parentNode)) {
                    if (edge.getDestination() == current) {
                        pathLength += edge.getWeight();
                        break;
                    }
                }
                
                current = parentNode;
            }
            path.add(0, startNode);
        }
        
        return new PathResult(path, pathLength, nodesExplored);
    }
    
    // Depth-First Search Algorithm
    public PathResult runDFS() {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parent = new HashMap<>();
        int[] nodesExplored = new int[1]; // Using array to pass by reference
        
        dfsVisit(startNode, endNode, visited, parent, nodesExplored);
        
        // Reconstruct path and calculate length
        List<Integer> path = new ArrayList<>();
        int pathLength = 0;
        
        if (parent.containsKey(endNode) || startNode == endNode) {
            int current = endNode;
            while (current != startNode) {
                path.add(0, current);
                int parentNode = parent.get(current);
                
                // Find edge weight
                for (Edge edge : adjacencyList.get(parentNode)) {
                    if (edge.getDestination() == current) {
                        pathLength += edge.getWeight();
                        break;
                    }
                }
                
                current = parentNode;
            }
            path.add(0, startNode);
        }
        
        return new PathResult(path, pathLength, nodesExplored[0]);
    }
    
    private boolean dfsVisit(int current, int end, Set<Integer> visited, Map<Integer, Integer> parent, int[] nodesExplored) {
        visited.add(current);
        nodesExplored[0]++;
        
        if (current == end) {
            return true;
        }
        
        for (Edge edge : adjacencyList.get(current)) {
            int neighbor = edge.getDestination();
            if (!visited.contains(neighbor)) {
                parent.put(neighbor, current);
                if (dfsVisit(neighbor, end, visited, parent, nodesExplored)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    // Dijkstra's Algorithm
    public PathResult runDijkstra() {
        Map<Integer, Integer> distance = new HashMap<>();
        Map<Integer, Integer> parent = new HashMap<>();
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(
                Comparator.comparingInt(Node::getDistance));
        Set<Integer> settled = new HashSet<>();
        int nodesExplored = 0;
        
        // Initialize distances
        for (int node : adjacencyList.keySet()) {
            distance.put(node, Integer.MAX_VALUE);
        }
        
        // Distance to start node is 0
        distance.put(startNode, 0);
        priorityQueue.add(new Node(startNode, 0));
        
        while (!priorityQueue.isEmpty()) {
            int current = priorityQueue.poll().getId();
            nodesExplored++;
            
            if (current == endNode) {
                break;
            }
            
            if (settled.contains(current)) {
                continue;
            }
            
            settled.add(current);
            
            for (Edge edge : adjacencyList.get(current)) {
                int neighbor = edge.getDestination();
                
                if (!settled.contains(neighbor)) {
                    int newDistance = distance.get(current) + edge.getWeight();
                    
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                        parent.put(neighbor, current);
                        priorityQueue.add(new Node(neighbor, newDistance));
                    }
                }
            }
        }
        
        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int pathLength = distance.get(endNode);
        
        if (pathLength != Integer.MAX_VALUE) {
            int current = endNode;
            while (current != startNode) {
                path.add(0, current);
                current = parent.get(current);
            }
            path.add(0, startNode);
        }
        
        return new PathResult(path, pathLength, nodesExplored);
    }
    
    // Kruskal's Algorithm
    public PathResult runKruskal() {
        // Implementation of Kruskal's algorithm (adapted to find a path between start and end nodes)
        int nodesExplored = 0;
        List<EdgeWithNodes> allEdges = new ArrayList<>();
        Map<Integer, Integer> parent = new HashMap<>();
        
        // Initialize parent map (each node is its own parent initially)
        for (int node : adjacencyList.keySet()) {
            parent.put(node, node);
        }
        
        // Add all edges to a list, to sort them by weight
        for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) {
            int source = entry.getKey();
            for (Edge edge : entry.getValue()) {
                int destination = edge.getDestination();
                // Add each edge once (undirected graph)
                if (source < destination) {
                    allEdges.add(new EdgeWithNodes(source, destination, edge.getWeight()));
                }
            }
        }
        
        // Sort edges by weight (ascending)
        allEdges.sort(Comparator.comparingInt(EdgeWithNodes::getWeight));
        
        Map<Integer, List<Integer>> mst = new HashMap<>();
        for (int node : adjacencyList.keySet()) {
            mst.put(node, new ArrayList<>());
        }
        
        // Kruskal's algorithm to build MST
        for (EdgeWithNodes edge : allEdges) {
            nodesExplored++;
            
            int sourceRoot = find(parent, edge.getSource());
            int destRoot = find(parent, edge.getDestination());
            
            // If including this edge doesn't create a cycle, include it
            if (sourceRoot != destRoot) {
                mst.get(edge.getSource()).add(edge.getDestination());
                mst.get(edge.getDestination()).add(edge.getSource());
                
                // Union the sets
                parent.put(sourceRoot, destRoot);
            }
        }
        
        // Find a path from start to end using the MST
        List<Integer> path = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        
        // Use DFS to find a path in the MST
        if (dfsPathFind(startNode, endNode, mst, visited, path)) {
            // Calculate path length
            int pathLength = 0;
            for (int i = 0; i < path.size() - 1; i++) {
                int current = path.get(i);
                int next = path.get(i + 1);
                
                for (Edge edge : adjacencyList.get(current)) {
                    if (edge.getDestination() == next) {
                        pathLength += edge.getWeight();
                        break;
                    }
                }
            }
            
            return new PathResult(path, pathLength, nodesExplored);
        }
        
        // No path found, return empty path
        return new PathResult(new ArrayList<>(), Integer.MAX_VALUE, nodesExplored);
    }
    
    // Helper method for finding the root parent in the Union-Find data structure
    private int find(Map<Integer, Integer> parent, int node) {
        if (parent.get(node) != node) {
            // Path compression
            parent.put(node, find(parent, parent.get(node)));
        }
        return parent.get(node);
    }
    
    // Helper method to find a path in the MST using DFS
    private boolean dfsPathFind(int current, int end, Map<Integer, List<Integer>> mst, 
                               Set<Integer> visited, List<Integer> path) {
        visited.add(current);
        path.add(current);
        
        if (current == end) {
            return true;
        }
        
        for (int neighbor : mst.get(current)) {
            if (!visited.contains(neighbor)) {
                if (dfsPathFind(neighbor, end, mst, visited, path)) {
                    return true;
                }
            }
        }
        
        // Backtrack if no path found through this node
        path.remove(path.size() - 1);
        return false;
    }
    
    // Inner class for Kruskal's algorithm
    private class EdgeWithNodes {
        private int source;
        private int destination;
        private int weight;
        
        public EdgeWithNodes(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
        
        public int getSource() {
            return source;
        }
        
        public int getDestination() {
            return destination;
        }
        
        public int getWeight() {
            return weight;
        }
    }
    
    // Helper function for Kruskal's algorithm - simple distance metric to evaluate path quality
    private int heuristic(int from, int to) {
        // Simple distance calculation used for evaluation and compatibility
        return Math.abs(from - to);
    }
} 