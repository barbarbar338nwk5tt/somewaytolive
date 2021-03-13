package prj.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    private HashMap<Point, HashMap<Point, Integer>> map;

    public Graph() {

        this.map = new HashMap<>();
    }
    public Graph(Graph graph) {

        this.map = new HashMap<>();
        if (graph == null) return; // throw
        for (Point i : graph.getMap().keySet()) {
            this.map.put(new Point(i), new HashMap<>());
            for (Point j : graph.getMap().get(i).keySet())
                this.map.get(i).put(new Point(j), graph.getMap().get(i).get(j));
        }
    }
    public Graph(int[][] array) {
        this.map = new HashMap<>();
        if (array == null) return; // throw
        int max_y = array.length;
        int max_x = array[0].length;

        for (int i = 0; i < max_y; i++) {
            for (int j = 0; j < max_x; j++) {
                if (array[i][j] > 0) {
                    this.addPoint(new Point(j, i));
                    if (i > 0 && array[i - 1][j] != 0) this.addEdge(new Point(j, i - 1), new Point(j, i), array[i][j]);
                    if (j > 0 && array[i][j - 1] != 0) this.addEdge(new Point(j - 1, i), new Point(j, i), array[i][j]);
                    if (i < max_y - 1 && array[i + 1][j] != 0) this.addEdge(new Point(j, i + 1), new Point(j, i), array[i][j]);
                    if (j < max_x - 1 && array[i][j + 1] != 0) this.addEdge(new Point(j + 1, i), new Point(j, i), array[i][j]);
                }
            }
        }
    }

    public void addPoint(Point p) {

        if (p == null) return;
        if (!map.containsKey(p))
            map.put(p, new HashMap<>());
    }
    public void addEdge(Point f, Point s, int w) {

        if (f == null || s == null || w <= 0) return;
        this.addPoint(f);
        this.addPoint(s);
        map.get(f).put(s, w);
    }

    public boolean isIn(Point p) {

        return map.containsKey(p);
    }
    public boolean isEmpty() {

        return map.isEmpty();
    }

    private HashMap<Point, HashMap<Point, Integer>> getMap() {

        return map;
    }
    public ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        for (Point i : map.keySet())
            points.add(new Point(i));
        points.sort((o1, o2) -> {
            if (o1.getY() < o2.getY()) return -1;
            if (o1.getY() == o2.getY()) return o1.getX() - o2.getX();
            return 1;
        });
        return points;
    }
    public ArrayList<Point> getNeighbors(Point p) {

        if (map.containsKey(p)) {
            ArrayList<Point> points = new ArrayList<>();
            for (Point i : map.get(p).keySet())
                points.add(new Point(i));
            return points;
        }
        return null;
    }
    public int getEdgeLen(Point f, Point s) {

        if (map.containsKey(f) && map.get(f).containsKey(s))
            return map.get(f).get(s);
        return 0;
    }

    @Override
    public String toString() {

        return map.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return o.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {

        return this.toString().hashCode();
    }
}
