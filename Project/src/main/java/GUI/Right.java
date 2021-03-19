package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import javax.swing.border.LineBorder;

public class Right extends JPanel {

    private JTable table;

    public Right() {
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Выпадающий список
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("W");
        comboBox.addItem(" ");
        comboBox.addItem("S");
        comboBox.addItem("E");

        // Таблица
        table = new JTable();
        table.setRowSelectionAllowed(false);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setBackground(new Color(255, 240, 245));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(20);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setColumnCount(5);
        dtm.setRowCount(5);
        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < table.getColumnCount(); ++i) {
            columnModel.getColumn(i).setPreferredWidth(20);
            columnModel.getColumn(i).setCellEditor(new DefaultCellEditor(comboBox));
        }

        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.fill = GridBagConstraints.SOUTH;
        gbc_table.gridheight = 13;
        gbc_table.gridwidth = 3;
        gbc_table.insets = new Insets(0, 0, 15, 5);
        gbc_table.gridx = 1;
        gbc_table.gridy = 1;
        gbc_table.weightx = 1.0f;
        gbc_table.weighty = 3.0f;
        this.add(table, gbc_table);
    }

    public JTable getTable() {
        return table;
    }
}
