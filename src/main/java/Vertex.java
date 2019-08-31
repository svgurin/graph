public class Vertex {
    private int node;
    private int nextNode;

    public Vertex(int node, int nextNode) {
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
