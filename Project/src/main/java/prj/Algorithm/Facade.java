package prj.Algorithm;

import prj.Resource.*;
import prj.GUI.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

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

    public void clear() {

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

        if (graph == null || start == null || end == null) return false;
        Graph tempG = new Graph(graph);
        Point tempS = new Point(start);
        Point tempE = new Point(end);

        boolean result = false;
        loadGraph(table);
        if (graph != null && start != null && end != null)
            result = graph.equals(tempG) && start.equals(tempS) && end.equals(tempE);

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

    public ArrayList<Point> getWalls() {

        ArrayList<Point> walls = new ArrayList<>();
        ArrayList<Point> points = graph.getPoints();
        int x = points.get(points.size() - 1).getX();
        int y = points.get(points.size() - 1).getY();
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if (!points.contains(new Point(i, j)))
                    walls.add(new Point(i, j));
            }
        }
        return walls;
    }

    public void drawStep(JTable table) {

        if (iterator == null) return; // throw
        AStar.Step curr = iterator.curr();
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        TableColumnModel columnModel = table.getColumnModel();
        // очистка поля
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {
                table.setValueAt("", i, j);
            }
        }
        // закрытые - красный
        for (Point i : curr.getClosed()) {
            table.setValueAt("c", i.getX(), i.getY());
        }
        // открытые - зеленый
        for (Point i : curr.getOpened()) {
            table.setValueAt("o", i.getX(), i.getY());
        }
        // стены - розовый
        for (Point i : this.getWalls()) {
            table.setValueAt("W", i.getX(), i.getY());
        }
        // текущая клетка - черный
        table.setValueAt("p", curr.getCurrent().getX(), curr.getCurrent().getY());
        table.setValueAt("S", start.getX(), start.getY());
        table.setValueAt("E", end.getX(), end.getY());
        // отрисовка
        for (int i = 0; i < table.getColumnCount(); ++i) {
            columnModel.getColumn(i).setPreferredWidth(20);
            table.getColumnModel().getColumn(i).setCellRenderer(new MyRenderer());
            table.updateUI();
        }
        System.out.println("Facade: walls count = " + this.getWalls().size());
    }
    public String getStepLog() {

        return "log";
    }

    public void loadGraph(JTable table) {

        Point s = null, e = null;
        int[][] array = new int[table.getColumnCount()][table.getRowCount()];

        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {

                array[i][j] = 1;
                if ("W".equals((String) table.getValueAt(j, i))) array[i][j] = 0;
                if ("S".equals((String) table.getValueAt(j, i))) s = new Point(j, i);
                if ("E".equals((String) table.getValueAt(j, i))) e = new Point(j, i);
            }
        }

        if (s == null || e == null) {
            graph = null;
            start = null;
            end = null;
            iterator = null;
        }
        else {
            graph = new Graph(array);
            start = s;
            end = e;
            iterator = AStar.execute(graph, s, e);
        }
    }
}
