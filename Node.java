public class Node {
    private int id;
    private int distance;
    
    public Node(int id, int distance) {
        this.id = id;
        this.distance = distance;
    }
    
    public int getId() {
        return id;
    }
    
    public int getDistance() {
        return distance;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Node node = (Node) obj;
        return id == node.id;
    }
    //if you need to compare Node objects based on their id fields, these methods are necessary
    // to ensure that two nodes with the same id are considered equal.
    //Consequences of Rem
    @Override
    public int hashCode() {
        return id;
    }
} 