import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphLibIntegerTest {
    private GraphLibInteger graph = new GraphLibInteger(false);

    @Test
    public void testAddVertex(){
       assertTrue(graph.addVertex(0));
    }

    @Test
    public void testAddEdge(){
        graph.addVertex(0);
        graph.addVertex(1);
        assertTrue(graph.addEdge(0, 1));
    }

    @Test
    public void testGetPath(){
        List<List<Integer>> listVertex = new ArrayList<List<Integer>>();
        assertEquals(graph.getPath(0,9),listVertex);
        for (int i = 1; i < 8; i++) {
            graph.addVertex(i);
            graph.addVertex(i + 1);
            graph.addEdge(i, i + 1);
            List path = new ArrayList();
            path.add(i);
            path.add(i + 1);
            listVertex.add(path);
        }
        assertEquals(graph.getPath(1,8),listVertex);
    }
}
