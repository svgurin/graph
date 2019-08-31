import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Edge<T> {
    private T value;
    private List<Integer> vertex;

    public Edge(T value) {
        this.value = value;
        this.vertex = new ArrayList<>();
    }

    public List<Integer> getVertex() {
        return vertex;
    }

    public void setVertex(Integer vertex) {
        this.vertex.add(vertex);
    }

    public T getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return value.equals(edge.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
