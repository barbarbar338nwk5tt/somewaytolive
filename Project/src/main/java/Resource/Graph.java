package Resource;

import java.util.HashMap;

public class Graph {

    private HashMap<Point, HashMap<Point, Integer>> map;

    public Graph() {

        this.map = new HashMap<>();
    }
    public Graph(Graph graph) {

        this.map = new HashMap<>();
        for (Point i : graph.getMap().keySet()) {
            this.map.put(new Point(i), new HashMap<>());
            for (Point j : graph.getMap().get(i).keySet())
                this.map.get(i).put(new Point(j), graph.getMap().get(i).get(j));
        }
    }
    public Graph(int[][] array) {

        int max_x = array.length;
        int max_y = array[0].length;

        for (int i = 0; i < max_x; i++) {
            for (int j = 0; j < max_y; j++) {

                if (array[i][j] > 0) {

                    if (i > 0) this.addEdge(new Point(i - 1, j), new Point(i, j), array[i][j]);
                    if (j > 0) this.addEdge(new Point(i, j - 1), new Point(i, j), array[i][j]);
                    if (i < max_x - 1) this.addEdge(new Point(i + 1, j), new Point(i, j), array[i][j]);
                    if (j < max_y - 1) this.addEdge(new Point(i, j + 1), new Point(i, j), array[i][j]);
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

    public HashMap<Point, HashMap<Point, Integer>> getMap() {

        return map;
    }

    @Override
    public String toString() {

        return map.toString();
    }
}
