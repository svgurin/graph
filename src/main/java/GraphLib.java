import java.util.*;

public class GraphLib<T> {
    private boolean oriented;
    private List<Edge> edgeList = Collections.synchronizedList(new ArrayList<Edge>());
    private List<Vertex<T>> vertexList = Collections.synchronizedList(new ArrayList<Vertex<T>>());

    public GraphLib(boolean oriented) {
        this.oriented = oriented;
    }

    public boolean addEdge(T node, T next) {
        if(oriented){
            return addEdgeOriented(node, next);
        }
        return addEdgeOriented(node, next) & addEdgeOriented(next, node);
    }

    public boolean addVertex(T vertexValue) {
        Integer vertexId = getIndexVertex(vertexValue);
        if (vertexId != null)
            return false;
        Vertex vertex = new Vertex(vertexValue);
        vertexList.add(vertex);
        return true;
    }

    public List<List<T>> getPath(T from, T to) {
        Integer fromId = getIndexVertex(from);
        Integer toId = getIndexVertex(to);
        if (fromId == null || toId == null || vertexList.get(fromId).getEdge().isEmpty())
            return Collections.EMPTY_LIST;
        int [] markVertex = new int[vertexList.size()];
        Stack<Integer> path = new Stack<Integer>();
        List<List<T>> result = new ArrayList<List<T>>();
        getPathWithId(fromId, toId, markVertex, path);
        while (!path.empty()){
            Integer idEdge = path.pop();
            Edge edge = edgeList.get(idEdge);
            T fromValue = vertexList.get(edge.getNode()).getValue();
            T toValue = vertexList.get(edge.getNextNode()).getValue();
            List<T> pair = new ArrayList<T>();
            pair.add(fromValue);
            pair.add(toValue);
            result.add(0,pair);
        }
        return result;
    }

    private Integer getIndexVertex(T value) {
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i).getValue().equals(value)) return i;
        }
        return null;
    }

    private void getPathWithId(Integer fromId, Integer toId, int[] markVertex, Stack<Integer> path) {
        if (fromId == toId) {
            return;
        }
        markVertex[fromId] = 1;
        for (Integer idEdge: vertexList.get(fromId).getEdge()) {
            Integer nextNode = edgeList.get(idEdge).getNextNode();
            if (markVertex[nextNode] == 0){
                path.push(idEdge);
                getPathWithId(edgeList.get(idEdge).getNextNode() , toId, markVertex, path);
                if(edgeList.get(path.peek()).getNextNode() == toId) return;
                path.pop();
            }
        }
    }

    private boolean addEdgeOriented(T node, T next) {
        Integer nodeId = getIndexVertex(node);
        Integer nextId = getIndexVertex(next);
        if (nodeId == null || nextId == null)
            return false;
        for (int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i).getNode() == nodeId && edgeList.get(i).getNextNode() == nextId){
                return true;
            }
        }
        Edge edge = new Edge(nodeId, nextId);
        Vertex vertex = vertexList.get(nodeId);
        vertex.setEdge(edgeList.size());
        edgeList.add(edge);
        return true;
    }
}
