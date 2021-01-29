package prj;

import prj.Resource.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Main app = new Main();
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