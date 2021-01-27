import Resource.*;
import Algorithm.*;
import GUI.*;

import javax.swing.*;

public class Main {

    private AStar astar;
    private GUI window;
    private AStar.Iterator iterator;

    public static void main(String[] args) {

        Main app = new Main();
    }

    public Main() {
        astar = new AStar();
        window = new GUI();
        window.setVisible(true);
        iterator = astar.getIterator();
    }

    public static Graph JTableToGraph(JTable table) {

        int[][] array = new int[table.getRowCount()][table.getColumnCount()];
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 0; j < table.getColumnCount(); j++) {

                array[i][j] = 1;
                if ("W".equals((String) table.getValueAt(i, j))) array[i][j] = 0;
            }
        }
        return new Graph(array);
    }
}