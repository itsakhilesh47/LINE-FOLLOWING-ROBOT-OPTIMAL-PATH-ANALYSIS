public class Edge {
    private int destination;
    private int weight;
    
    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
    
    public int getDestination() {
        return destination;
    }
    
    public int getWeight() {
        return weight;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Edge edge = (Edge) obj;
        return destination == edge.destination && weight == edge.weight;
    }
    
    @Override
    public int hashCode() {
        return 31 * destination + weight;
    }
} 