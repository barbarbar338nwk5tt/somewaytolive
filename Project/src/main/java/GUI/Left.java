package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.File;


public class Left extends JPanel {

    private JTextField textField1;
    private JTextField textField2;
    private JSlider slid;
    private JTable table;
    private Button loadButton;
    private Button startButton;
    private Button stopButton;
    private Button toStartButton;
    private Button toEndButton;
    private Button backButton;
    private Button nextButton;
    private Button genButton;
    private JComboBox<String> comboBox;

    public Left() {
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Выпадающий список
        comboBox = new JComboBox<>();
        comboBox.addItem("W");
        comboBox.addItem(" ");
        comboBox.addItem("S");
        comboBox.addItem("E");

        // лейбл с Heights
        JLabel input_label1 = new JLabel("Width:");
        input_label1.setFont(new Font("Tahoma", Font.BOLD, 11));
        input_label1.setForeground(Color.BLACK);
        GridBagConstraints gbc_input_label = new GridBagConstraints();
        // отступы
        gbc_input_label.insets = new Insets(0, 0, 5, 5);
        // прижаться к краю ячейки
        gbc_input_label.anchor = GridBagConstraints.WEST;
        // номер ячейки в сетке
        gbc_input_label.gridx = 0;
        gbc_input_label.gridy = 0;
        this.add(input_label1, gbc_input_label);
        // Поле Heights
        textField1 = new JTextField();
        textField1.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.WEST;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        this.add(textField1, gbc_textField);
        textField1.setColumns(5);
        textField1.setText("5"); //base field size

        // лейбл с Widths
        JLabel input_label2 = new JLabel("Height:");
        input_label2.setFont(new Font("Tahoma", Font.BOLD, 11));
        input_label2.setForeground(Color.BLACK);
        GridBagConstraints gbc_input_label1 = new GridBagConstraints();
        // отступы
        gbc_input_label1.insets = new Insets(0, 0, 5, 5);
        // прижаться к краю ячейки
        gbc_input_label1.anchor = GridBagConstraints.WEST;
        // номер ячейки в сетке
        gbc_input_label1.gridx = 0;
        gbc_input_label1.gridy = 1;
        this.add(input_label2, gbc_input_label1);
        // Поле Widths
        textField2 = new JTextField();
        textField2.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_textField1 = new GridBagConstraints();
        gbc_textField1.insets = new Insets(0, 0, 5, 5);
        gbc_textField1.anchor = GridBagConstraints.WEST;
        gbc_textField1.gridx = 1;
        gbc_textField1.gridy = 1;
        this.add(textField2, gbc_textField1);
        textField2.setColumns(5);
        textField2.setText("5"); //base field size

        /*Action listen"*/
        ActionListener textActList = new TextActionListener();
        textField1.addActionListener(textActList);
        textField2.addActionListener(textActList);

        // Слайдер
        slid = new JSlider(1, 3, 2);
        slid.setSnapToTicks(true);
        slid.setPaintTrack(true);

        slid.setPaintTicks(true);
        slid.setPaintLabels(true);
        slid.setMajorTickSpacing(1);

        GridBagConstraints  slid_gbc = new GridBagConstraints();
        slid_gbc.insets = new Insets(20, 0, 5, 5);
        slid_gbc.anchor = GridBagConstraints.WEST;
        slid_gbc.gridx = 0;
        slid_gbc.gridy = 8;
        this.add(slid, slid_gbc);

        // Выпадающий список
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("W");
        comboBox.addItem(" ");
        comboBox.addItem("S");
        comboBox.addItem("E");

        JLabel instruction_label_1 = new JLabel("W - wall, S - start, E - Exit");
        instruction_label_1.setForeground(Color.BLACK);
        instruction_label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_instruction_label_1 = new GridBagConstraints();
        gbc_instruction_label_1.anchor = GridBagConstraints.WEST;
        gbc_instruction_label_1.insets = new Insets(0, 0, 5, 20);
        gbc_instruction_label_1.gridx = 0;
        gbc_instruction_label_1.gridy = 9;
        this.add(instruction_label_1, gbc_instruction_label_1);

        loadButton = new Button("Load");
        loadButton.attachTo(this, 0, 2);
        loadButton.addActionListener(new ButtonLoadActionListener());

        genButton = new Button("Generate");
        genButton.attachTo(this, 0, 3);
        genButton.addActionListener(new ButtonGenerateActionListener());

        toStartButton = new Button("ToStart");
        toStartButton.attachTo(this, 0, 4);
        toStartButton.addActionListener(new ButtonToStartActionListener());

        toEndButton = new Button("ToEnd");
        toEndButton.attachTo(this, 1, 4);
        toEndButton.addActionListener(new ButtonToEndActionListener());

        nextButton = new Button("Next");
        nextButton.attachTo(this, 0, 5);
        nextButton.addActionListener(new ButtonNextActionListener());

        backButton = new Button("Back");
        backButton.attachTo(this, 1, 5);
        backButton.addActionListener(new ButtonBackActionListener());

        startButton = new Button("Start");
        startButton.attachTo(this, 0, 6);
        startButton.addActionListener(new ButtonStartActionListener());

        stopButton = new Button("Stop");
        stopButton.attachTo(this, 1, 6);
        stopButton.addActionListener(new ButtonStopActionListener());
        stopButton.setEnabled(false);
    }

    public void setTable(JTable table) {

        this.table = table;
    }

    //

    public class TextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String textX = textField1.getText();
            String textY = textField2.getText();
            textField1.selectAll();
            int x, y;
            try {
                x = Integer.parseInt(textX);
                y = Integer.parseInt(textY);
            }
            catch (NumberFormatException e) {
                JOptionPane.showInternalMessageDialog(null, "Error! Wrong number format.");
                return;
            }
            if (x < 2 || x > 30) {
                JOptionPane.showInternalMessageDialog(null, "Error! Enter size of the field in range [2, 30].");
                return;
            }

            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setColumnCount(x);
            dtm.setRowCount(y);
            TableColumnModel columnModel = table.getColumnModel();
            for (int i = 0; i < table.getColumnCount(); ++i) {
                columnModel.getColumn(i).setPreferredWidth(20);
                columnModel.getColumn(i).setCellEditor(new DefaultCellEditor(comboBox));
            }
        }
    }

    public class ButtonLoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {

                File file = fileopen.getSelectedFile();
            }
        }
    }

    public class ButtonNextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ButtonBackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ButtonStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            stopButton.setEnabled(true);
            startButton.setEnabled(false);
            toStartButton.setEnabled(false);
            toEndButton.setEnabled(false);
            backButton.setEnabled(false);
            nextButton.setEnabled(false);
            genButton.setEnabled(false);
            loadButton.setEnabled(false);
        }
    }

    public class ButtonToEndActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ButtonToStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ButtonStopActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            toStartButton.setEnabled(true);
            toEndButton.setEnabled(true);
            backButton.setEnabled(true);
            nextButton.setEnabled(true);
            genButton.setEnabled(true);
            loadButton.setEnabled(true);
        }
    }

    public class ButtonGenerateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int x, y, koef;
            Random random = new Random();
            x = random.nextInt(28) + 2;
            y = random.nextInt(28) + 2;
            textField1.setText(x + " " + y);

            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setColumnCount(x);
            dtm.setRowCount(y);
            TableColumnModel columnModel = table.getColumnModel();
            for (int i=0; i<table.getColumnCount(); ++i)
                columnModel.getColumn(i).setPreferredWidth(15);


            for (int i=0; i<table.getRowCount(); i++) {
                for (int j=0; j<table.getColumnCount(); j++) {
                    koef = random.nextInt(5);
                    if (koef == 0) table.setValueAt("1", i, j);
                    else table.setValueAt("", i, j);
                }
            }

            int koord_xS, koord_yS;
            koord_xS = random.nextInt(x - 1) + 1; //gen 2 - x
            koord_yS = random.nextInt(y - 1) + 1; //gen 2 - y

            table.setValueAt("S", koord_yS, koord_xS);

            int koord_xE, koord_yE;

            koord_xE = random.nextInt(x - 1) + 1; //gen 2 - x
            koord_yE = random.nextInt(y - 1) + 1; //gen 2 - y

            while (koord_xS == koord_xE && koord_yS == koord_yE) {
                koord_xE = random.nextInt(x - 1) + 1; //gen 2 - x
                koord_yE = random.nextInt(y - 1) + 1; //gen 2 - y
            }

            table.setValueAt("E", koord_yE, koord_xE);

            for (int j=0; j<table.getColumnCount(); j++) {
                table.getColumnModel().getColumn(j).setCellRenderer(new MyRenderer());
                table.updateUI();
            }
        }
        }
}