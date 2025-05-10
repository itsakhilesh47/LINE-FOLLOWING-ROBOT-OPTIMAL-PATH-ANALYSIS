import java.util.List;

public class PathResult {
    private List<Integer> path;
    private int pathLength;
    private int nodesExplored;
    
    public PathResult(List<Integer> path, int pathLength, int nodesExplored) {
        this.path = path;
        this.pathLength = pathLength;
        this.nodesExplored = nodesExplored;
    }
    
    public List<Integer> getPath() {
        return path;
    }
    
    public int getPathLength() {
        return pathLength;
    }
    
    public int getNodesExplored() {
        return nodesExplored;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        
        if (path.isEmpty()) {
            sb.append("No path found");
        } else {
            for (int i = 0; i < path.size(); i++) {
                sb.append(path.get(i));
                if (i < path.size() - 1) {
                    sb.append(" -> ");
                }
            }
        }
        
        sb.append("\nPath Length: ").append(pathLength);
        sb.append("\nNodes Explored: ").append(nodesExplored);
        
        return sb.toString();
    }
} 