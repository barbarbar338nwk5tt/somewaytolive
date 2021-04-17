package Resource;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class GraphTest {
    private Graph graph1;
    private Graph graph2;
    private Graph graph3;
    private Graph graph4;

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
        graph3 = new Graph();
        graph4 = new Graph(array2);
    }

    @Test
    public void testEqualsFalse() {
        assertFalse(graph1.equals(graph2));
    }

    @Test
    public void testEqualsTrue() {
        assertTrue(graph2.equals(graph4));
    }

    @Test
    public void graphInitTest_1g() {
        ArrayList<Point> exp = new ArrayList<>();
        exp.add(new Point(2, 0));
        exp.add(new Point(0, 1));
        exp.add(new Point(1, 1));
        exp.add(new Point(2, 1));
        exp.add(new Point(0, 2));
        assertEquals(exp.toString(), graph1.getPoints().toString());
    }

    @Test
    public void graphInitTest_2g() {
        ArrayList<Point> exp = new ArrayList<>();
        exp.add(new Point(0, 0));
        exp.add(new Point(2, 0));
        exp.add(new Point(1, 1));
        exp.add(new Point(0, 2));
        exp.add(new Point(2, 2));
        assertEquals(exp.toString(), graph2.getPoints().toString());
    }

    @Test
    public void isEmptyTest_1() {
        assertFalse(graph1.isEmpty());
    }

    @Test
    public void isEmptyTest_2() {
        assertTrue(graph3.isEmpty());
    }

    @Test
    public void isInTest_1() {
        Point a = new Point(1, 1);
        assertTrue(graph1.isIn(a));
    }

    @Test
    public void isInTest_2() {
        Point a = new Point(6, 1);
        assertFalse(graph1.isIn(a));
    }

    @Test
    public void getEdgeLenTest_0() {
        int test = 0;
        Point a = new Point(0, 0);
        Point b = new Point(0, 1);
        assertEquals(test, graph1.getEdgeLen(a, b));
    }

    @Test
    public void getEdgeLenTest_1() {
        int test = 1;
        Point a = new Point(1, 1);
        Point b = new Point(0, 1);
        assertEquals(test, graph1.getEdgeLen(a, b));
    }
}