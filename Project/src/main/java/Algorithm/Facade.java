package Algorithm;

import GUI.MyRenderer;
import Resource.*;
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

    public boolean isLoad() {
        return iterator != null;
    }

    public boolean next() {
        try {
            AStar.Step curr = iterator.next();
            if (curr == null) return false;
            return true;
        }
        catch (NullPointerException exp) {
            System.out.println("log4j2: Facade not upload!");
            return false; // maybe throw
        }
    }
    public boolean prev() {
        try {
            AStar.Step curr = iterator.prev();
            if (curr == null) return false;
            return true;
        }
        catch (NullPointerException exp) {
            System.out.println("log4j2: Facade not upload!");
            return false; // maybe throw
        }
    }

    public void toStart() {
        try {
            iterator.toStart();
        }
        catch (NullPointerException exp) {
            System.out.println("log4j2: Facade not upload!");
            // maybe throw
        }
    }
    public void toEnd() {
        try {
            iterator.toEnd();
        }
        catch (NullPointerException exp) {
            System.out.println("log4j2: Facade not upload!");
            // maybe throw
        }
    }

    public ArrayList<Point> getWalls() {
        try {
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
        catch (NullPointerException exp) {
            System.out.println("log4j2: Facade not upload!");
            return new ArrayList<>(); // maybe throw
        }
        catch (Exception exp) {
            exp.printStackTrace();
            return new ArrayList<>(); // maybe throw
        }
    }

    public void drawStep(JTable table) {
        try {
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
        }
        catch (NullPointerException exp) {
            System.out.println("log4j2: Facade not upload!");
            // maybe throw
        }
    }
    public String getStepLog() {
        if (iterator == null) throw new NullPointerException(); //
        return iterator.curr().getCurrent().toString() + "\n";
    }

    public void loadGraph(JTable table) {
        clear();
        int[][] array = new int[table.getColumnCount()][table.getRowCount()];

        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {

                array[i][j] = 1;
                if ("W".equals((String) table.getValueAt(j, i))) array[i][j] = 0;
                if ("S".equals((String) table.getValueAt(j, i))) start = new Point(j, i);
                if ("E".equals((String) table.getValueAt(j, i))) end = new Point(j, i);
            }
        }

        // check to unnecessary
        try {
            graph = new Graph(array);
            iterator = AStar.execute(graph, start, end);
        }
        catch (Exception exp) {
            exp.printStackTrace();
            // maybe throw
        }
    }
}
