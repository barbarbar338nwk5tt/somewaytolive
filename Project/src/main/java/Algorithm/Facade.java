package Algorithm;

import GUI.MyRenderer;
import Resource.*;
import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Facade {
    private Graph graph;
    private Point start, end;
    private AStar.Iterator iterator;
    private ArrayList<Point> walls;
    private final Logger logger;

    public Facade() {
        graph = null;
        start = null;
        end = null;
        iterator = null;
        walls = null;
        logger = LogManager.getLogger(Facade.class);
    }
    public void clear() {
        graph = null;
        start = null;
        end = null;
        iterator = null;
        walls = null;
    }

    public boolean isLoad() {
        return iterator != null;
    }

    public boolean next() {
        try {
            AStar.Step curr = iterator.next();
            return curr != null;
        }
        catch (NullPointerException exp) {
            logger.error("Facade was not loaded or loaded incorrectly.");
            return false; // maybe throw
        }
    }
    public boolean prev() {
        try {
            AStar.Step curr = iterator.prev();
            return curr != null;
        }
        catch (NullPointerException exp) {
            logger.error("Facade was not loaded or loaded incorrectly.");
            return false; // maybe throw
        }
    }

    public void toStart() {
        try {
            iterator.toStart();
        }
        catch (NullPointerException exp) {
            logger.error("Facade was not loaded or loaded incorrectly.");
            // maybe throw
        }
    }
    public void toEnd() {
        try {
            iterator.toEnd();
        }
        catch (NullPointerException exp) {
            logger.error("Facade was not loaded or loaded incorrectly.");
            // maybe throw
        }
    }

    public void drawStep(JTable table) {
        try {
            AStar.Step curr = iterator.curr();
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
            for (Point i : walls) {
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
            logger.error("Facade was not loaded or loaded incorrectly.");
            // maybe throw
        }
    }
    public String getStepLog() {
        try {
            return iterator.curr().getCurrent().toString() + "\n";
        }
        catch (NullPointerException exp) {
            logger.error("Facade was not loaded or loaded incorrectly.");
            return "error";
        }
    }

    public void loadGraph(JTable table) {
        clear();
        walls = new ArrayList<>();
        int[][] array = new int[table.getColumnCount()][table.getRowCount()];
        for (int i = 0; i < table.getColumnCount(); i++) {
            for (int j = 0; j < table.getRowCount(); j++) {
                array[i][j] = 1;
                if ("W".equals((String) table.getValueAt(j, i))) {
                    array[i][j] = 0;
                    walls.add(new Point(j ,i));
                }
                if ("S".equals((String) table.getValueAt(j, i))) {
                    start = new Point(j, i);
                }
                if ("E".equals((String) table.getValueAt(j, i))) {
                    end = new Point(j, i);
                }
            }
        }

        try {
            graph = new Graph(array);
            iterator = AStar.execute(graph, start, end);
        }
        catch (Exception exp) {
            clear(); //?
            logger.error("Facade load went incorrectly in " + exp.getStackTrace()[0].getClassName() + ".");
            // maybe throw
        }
    }
}
