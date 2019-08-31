import java.util.*;

public class GraphLib<T> {
    private boolean oriented;
    private List<Vertex> vertexList = Collections.synchronizedList(new ArrayList<Vertex>());
    private List<Edge<T>> edgeList = Collections.synchronizedList(new ArrayList<Edge<T>>());

    public GraphLib(boolean oriented) {
        this.oriented = oriented;
    }

    public synchronized boolean addVertex(T node, T next) {
        if(oriented){
            return addVertexOriented(node, next);
        }
        return addVertexOriented(node, next) && addVertexOriented(next, node);
    }

    public synchronized boolean addEdge(T edgeValue) {
        Integer edgeId = getIndexEdge(edgeValue);
        if (edgeId != null)
            return false;
        Edge edge = new Edge(edgeValue);
        edgeList.add(edge);
        return true;
    }

    public List<List<T>> getPath(T from, T to) {
        Integer fromId = getIndexEdge(from);
        Integer toId = getIndexEdge(to);
        if (fromId == null || toId == null || edgeList.get(fromId).getVertex().isEmpty())
            return Collections.EMPTY_LIST;
        int [] markEdge = new int[edgeList.size()];
        Stack<Integer> path = new Stack<Integer>();
        List<List<T>> result = new ArrayList<List<T>>();
        getPathWithId(fromId, toId, markEdge, path);
        while (!path.empty()){
            Integer idVertex = path.pop();
            Vertex vertex = vertexList.get(idVertex);
            T fromValue = edgeList.get(vertex.getNode()).getValue();
            T toValue = edgeList.get(vertex.getNextNode()).getValue();
            List<T> pair = new ArrayList<T>();
            pair.add(fromValue);
            pair.add(toValue);
            result.add(0,pair);
        }
        return result;
    }

    private Integer getIndexEdge(T value) {
        for (int i = 0; i < edgeList.size(); i++) {
            if(edgeList.get(i).getValue().equals(value)) return i;
        }
        return null;
    }

    private void getPathWithId(Integer fromId, Integer toId, int[] markEdge, Stack<Integer> path) {
        if (fromId == toId) {
            return;
        }
        markEdge[fromId] = 1;
        for (Integer idVertex: edgeList.get(fromId).getVertex()) {
            Integer nextNode = vertexList.get(idVertex).getNextNode();
            if (markEdge[nextNode] == 0){
                path.push(idVertex);
                getPathWithId(vertexList.get(idVertex).getNextNode() , toId, markEdge, path);
                if(vertexList.get(path.peek()).getNextNode() == toId) return;
                path.pop();
            }
        }
    }

    private boolean addVertexOriented(T node, T next) {
        Integer nodeId = getIndexEdge(node);
        Integer nextId = getIndexEdge(next);
        if (nodeId == null || nextId == null)
            return false;
        for (int i = 0; i < vertexList.size(); i++) {
            if(vertexList.get(i).getNode() == nodeId && vertexList.get(i).getNextNode() == nextId){
                return true;
            }
        }
        Vertex vertex = new Vertex(nodeId, nextId);
        Edge edge = edgeList.get(nodeId);
        edge.setVertex(vertexList.size());
        vertexList.add(vertex);
        return true;
    }
}
