import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GraphLibIntegerTest {
    private GraphLibInteger graph = new GraphLibInteger(false);

    @Test
    public void testAddEdge(){
       assertTrue(graph.addEdge(0));
    }

    @Test
    public void testAddVertex(){
        graph.addEdge(0);
        graph.addEdge(1);
        assertTrue(graph.addVertex(0, 1));
    }

    @Test
    public void testGetPath(){
        List<List<Integer>> listEdge = new ArrayList<List<Integer>>();
        assertEquals(graph.getPath(0,9),listEdge);
        for (int i = 1; i < 8; i++) {
            graph.addEdge(i);
            graph.addEdge(i + 1);
            graph.addVertex(i, i + 1);
            List path = new ArrayList();
            path.add(i);
            path.add(i + 1);
            listEdge.add(path);
        }
        assertEquals(graph.getPath(1,8),listEdge);
    }
}
