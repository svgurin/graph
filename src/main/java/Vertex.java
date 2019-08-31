import java.util.*;

public class Vertex<T> {
    private T value;
    private List<Integer> edge;

    public Vertex(T value) {
        this.value = value;
        this.edge = new ArrayList<Integer>();
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Integer> getEdge() {
        return edge;
    }

    public void setEdge(Integer edge) {
        this.edge.add(edge);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return value.equals(vertex.value);
    }
}
