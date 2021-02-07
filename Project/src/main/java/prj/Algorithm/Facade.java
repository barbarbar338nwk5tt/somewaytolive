package prj.Algorithm;

import prj.Resource.*;
import javax.swing.*;

public class Facade {

    private Graph graph;
    private Point start, end;
    private AStar.Iterator iterator;

    public Facade() {
        graph = null;
        start = null;
        end = null;
        iterator = null;
    }

    public boolean isPathExist() {

        if (iterator == null) return false;
        int temp = iterator.getIndex();
        boolean result = iterator.toEnd().getCurrent().equals(end);
        iterator.toIndex(temp);
        return result;
    }
    public boolean isLoad() {

        return iterator != null;
    }
    public boolean isGraphEqual(JTable table) {

        Graph tempG = new Graph(graph);
        Point tempS = new Point(start);
        Point tempE = new Point(end);

        loadGraph(table);
        boolean result =  this.graph.equals(tempG) && this.start.equals(tempS) && this.end.equals(tempE);

        this.graph = tempG;
        this.start = tempS;
        this.end = tempE;

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

    public void toEnd() {

        if (iterator == null) return; // throw
        iterator.toEnd();
    }
    public void toStart() {

        if (iterator == null) return; // throw
        iterator.toStart();
    }

    public void drawStep(JTable table) {

        if (iterator == null) return; // throw
        AStar.Step curr = iterator.curr();
        for (Point i : curr.getClosed()) {
            // разукраска закрытых клеток JTable
        }
        for (Point i : curr.getOpened()) {
            // Разукраска открытых клеток JTable
        }
        // Разукраска текущей клетки ...curr.getCurrent();
    }
    public String getStepLog() {

        return "log";
    }

    public void loadGraph(JTable table) {

        Point s = null, e = null;
        int[][] array = new int[table.getRowCount()][table.getColumnCount()];

        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {

                array[i][j] = 1;
                if ("W".equals((String) table.getValueAt(i, j))) array[i][j] = 0;
                if ("S".equals((String) table.getValueAt(i, j))) s = new Point(i, j);
                if ("E".equals((String) table.getValueAt(i, j))) e = new Point(i, j);
            }
        }

        if (s == null || e == null) return;
        graph = new Graph(array);
        start = s;
        end = e;
        iterator = AStar.execute(graph, s, e);
    }
}
