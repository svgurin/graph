public class Edge {
    private int node;
    private int nextNode;

    public Edge(int node, int nextNode) {
        this.node = node;
        this.nextNode = nextNode;
    }

    public int getNode() {
        return node;
    }

    public int getNextNode() {
        return nextNode;
    }
}
