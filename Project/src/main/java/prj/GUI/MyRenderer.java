package prj.GUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyRenderer extends DefaultTableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value, boolean   isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (table.getValueAt(row, column)!=null && table.getValueAt(row, column).equals("W")) {
            c.setBackground(Color.magenta);
            c.setForeground(Color.magenta);
        }
        else if (table.getValueAt(row, column)!=null && table.getValueAt(row, column).equals("c")) {
            c.setBackground(Color.red);
            c.setForeground(Color.red);
        }
        else if (table.getValueAt(row, column)!=null && table.getValueAt(row, column).equals("o")) {
            c.setBackground(Color.green);
            c.setForeground(Color.green);
        }
        else if (table.getValueAt(row, column)!=null && table.getValueAt(row, column).equals("r")) {
            c.setBackground(Color.yellow);
            c.setForeground(Color.yellow);
        }
        else if (table.getValueAt(row, column)!=null && table.getValueAt(row, column).equals("p")) {
            c.setBackground(Color.black);
            c.setForeground(Color.black);
        }
        else if (table.getValueAt(row, column)!=null && (table.getValueAt(row, column).equals("E") ||
                table.getValueAt(row, column).equals("e"))) {
            c.setBackground(Color.yellow);
            c.setForeground(Color.black);
        }
        else if (table.getValueAt(row, column)!=null && (table.getValueAt(row, column).equals("S") ||
                table.getValueAt(row, column).equals("s"))) {
            c.setBackground(Color.yellow);
            c.setForeground(Color.black);
        }
        else
            c.setBackground(new Color(255, 240, 245));

        return c;
    }
}