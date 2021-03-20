package Resource;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
                {0, 1, 0},
                {1, 0, 1}};
        graph1 = new Graph(array1);
        graph2 = new Graph(array2);
    }

    @Test
    public void testInitialize1() {
        String test = "{0 1={1 1=1, 0 2=1}, 2 0={2 1=1}, 0 2={0 1=1}, 1 1={0 1=1, 2 1=1}, 2 1={2 0=1, 1 1=1}}";
        assertEquals(test, graph1.toString());
    }

    @Test
    public void testInitialize2() {
        String test = "{0 0={}, 2 0={}, 1 1={}, 0 2={}, 2 2={}}";
        assertEquals(test, graph2.toString());
    }

    @Test
    public void testEqualsFalse() {
        // nothing
        assertFalse(graph1.equals(graph2));
    }

    @Test
    public void testEqualsTrue() {
        try {
            graph1 = new Graph(graph2);
            assertTrue(graph1.equals(graph2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPointsTest_1g() {
        ArrayList<Point> exp = new ArrayList<>();
        exp.add(new Point(1, 0));
        exp.add(new Point(2, 0));
        exp.add(new Point(0, 1));
        exp.add(new Point(1, 1));
        exp.add(new Point(2, 1));
        exp.add(new Point(0, 2));
        try {
            graph1.addPoint(new Point(1, 0));
            assertEquals(exp.toString(), graph1.getPoints().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getPointsTest_2g() {
        ArrayList<Point> exp = new ArrayList<>();
        exp.add(new Point(0, 0));
        exp.add(new Point(1, 0));
        exp.add(new Point(2, 0));
        exp.add(new Point(1, 1));
        exp.add(new Point(0, 2));
        exp.add(new Point(2, 2));
        try {
            graph2.addPoint(new Point(1, 0));
            assertEquals(exp.toString(), graph2.getPoints().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}