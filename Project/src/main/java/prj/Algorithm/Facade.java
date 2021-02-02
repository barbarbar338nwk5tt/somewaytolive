package prj.Algorithm;

import prj.Resource.*;

import javax.swing.*;

public class Facade {

    private Graph graph;
    private Point start, end;
    private AStar.Iterator iterator;

    public Facade() {
        graph = new Graph();
        start = new Point();
        end = new Point();
        iterator = null;
    }

    public void clearAll() {
        graph = new Graph();
        start = new Point();
        end = new Point();
        iterator = null;
    }

    public void calculatePath() {

        if (!graph.isEmpty() && graph.isIn(start) && graph.isIn(end))
            iterator = AStar.execute(graph, start, end);
        // throw
    }
    public boolean isPathExist() {

        int temp = iterator.getIndex();
        boolean result = iterator.toEnd().equals(end);
        iterator.toIndex(temp);
        return result;
    }

    public boolean next() {

        if (iterator == null) return false; // throw
        AStar.Step curr = iterator.next();
        if (curr == null) return false;
        return true;
    }
    public boolean prev() {

        if (iterator == null) return false; // throw
        AStar.Step curr = iterator.prev();
        if (curr == null) return false;
        return true;
    }

    public void drawStep(JTable table) {

        // draw
    }

    public void loadGraph(JTable table) {

        int[][] array = new int[table.getRowCount()][table.getColumnCount()];
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {

                array[i][j] = 1;
                if ("W".equals((String) table.getValueAt(i, j))) array[i][j] = 0;
            }
        }
        graph = new Graph(array);
    }
    public void setSE(Point s, Point e) {

        if (s == null || e == null) return; // throw
        if (s.equals(e)) return; // throw
        if (!graph.isIn(s) || !graph.isIn(e)) return; // throw
        start = s;
        end = e;
    }
}
