package prj.Resource;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    private Graph graph1;
    private Graph graph2;

    @Before
    public void setUp() throws Exception {

        int[][] array1 = {
                {0, 0, 1},
                {1, 1, 1},
                {1, 0, 0}};
        int[][] array2 = {
                {1, 0, 1},
                {1, 1, 0},
                {1, 0, 1}};
        graph1 = new Graph(array1);
        graph2 = new Graph(array2);
    }

    @Test
    public void testInitialize1() {

        String test = "{1 0={1 1=1, 2 0=1}, 0 2={1 2=1}, 2 0={1 0=1}, 1 1={1 0=1, 1 2=1}, 1 2={0 2=1, 1 1=1}}";
        assertEquals(test, graph1.toString());
    }

    @Test
    public void testInitialize2() {

        String test = "{0 0={1 0=1}, 1 0={0 0=1, 1 1=1, 2 0=1}, 2 0={1 0=1}, 1 1={1 0=1}}";
        assertEquals(test, graph2.toString());
    }

    @Test
    public void testEqualsFalse() {

        assertFalse(graph1.equals(graph2));
    }

    @Test
    public void testEqualsTrue() {

        graph1 = new Graph(graph2);
        assertTrue(graph1.equals(graph2));
    }
}