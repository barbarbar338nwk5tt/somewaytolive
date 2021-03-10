package prj.GUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.io.File;

import prj.Algorithm.Facade;

public class Left extends JPanel {

    private JTextField textField1;
    private JTextField textField2;
    private JSlider slid;
    private Button loadButton;
    private Button startButton;
    private Button stopButton;
    private Button toStartButton;
    private Button toEndButton;
    private Button backButton;
    private Button nextButton;
    private Button genButton;
    private Button changeButton;
    private JComboBox<String> comboBox;
    private JTextArea textLog;
    private Timer timer;

    private Right rightPointer;
    private Facade facadePointer;

    public Left() {
        super();
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);

        // Таймер
        timer = new Timer(600, new ButtonNextActionListener());

        // выпадающий список
        comboBox = new JComboBox<>();
        comboBox.addItem("W");
        comboBox.addItem(" ");
        comboBox.addItem("S");
        comboBox.addItem("E");

        JLabel instruction_label_1 = new JLabel("W - wall, S - start, E - Exit");
        instruction_label_1.setForeground(Color.BLACK);
        instruction_label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_instruction_label_1 = new GridBagConstraints();
        gbc_instruction_label_1.anchor = GridBagConstraints.WEST;
        gbc_instruction_label_1.insets = new Insets(0, 0, 15, 20);
        gbc_instruction_label_1.gridx = 0;
        gbc_instruction_label_1.gridy = 0;
        this.add(instruction_label_1, gbc_instruction_label_1);

        // лейбл с Heights
        JLabel input_label1 = new JLabel("Heihgt:");
        input_label1.setFont(new Font("Tahoma", Font.BOLD, 11));
        input_label1.setForeground(Color.BLACK);
        GridBagConstraints gbc_input_label = new GridBagConstraints();
        // отступы
        gbc_input_label.insets = new Insets(0, 0, 5, 5);
        // прижаться к краю ячейки
        gbc_input_label.anchor = GridBagConstraints.WEST;
        // номер ячейки в сетке
        gbc_input_label.gridx = 0;
        gbc_input_label.gridy = 1;
        this.add(input_label1, gbc_input_label);
        // Поле Heights
        textField1 = new JTextField();
        textField1.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.WEST;
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 1;
        this.add(textField1, gbc_textField);
        textField1.setColumns(5);
        textField1.setText("5"); //base field size

        // лейбл с Heights
        JLabel input_label2 = new JLabel("Width:");
        input_label2.setFont(new Font("Tahoma", Font.BOLD, 11));
        input_label2.setForeground(Color.BLACK);
        GridBagConstraints gbc_input_label2 = new GridBagConstraints();
        // отступы
        gbc_input_label2.insets = new Insets(0, 0, 5, 5);
        // прижаться к краю ячейки
        gbc_input_label2.anchor = GridBagConstraints.WEST;
        // номер ячейки в сетке
        gbc_input_label2.gridx = 0;
        gbc_input_label2.gridy = 2;
        this.add(input_label2, gbc_input_label2);
        // Поле Heights
        textField2 = new JTextField();
        textField2.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_textField2 = new GridBagConstraints();
        gbc_textField2.insets = new Insets(0, 0, 5, 5);
        gbc_textField2.fill = GridBagConstraints.WEST;
        gbc_textField2.gridx = 0;
        gbc_textField2.gridy = 2;
        this.add(textField2, gbc_textField2);
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
        slid.setOrientation(SwingConstants.VERTICAL);
        slid.setPaintTicks(true);
        slid.setPaintLabels(true);
        slid.setMajorTickSpacing(1);

        GridBagConstraints  slid_gbc = new GridBagConstraints();
        slid_gbc.insets = new Insets(20, 20, 5, 5);
        slid_gbc.gridx = 1;
        slid_gbc.gridy = 3;
        slid_gbc.gridheight = 7;
        this.add(slid, slid_gbc);
        slid.addChangeListener(new SliderChangeListener());

        // Кнопки
        changeButton = new Button("Change state");
        changeButton.attachTo(this, 0, 11);
        changeButton.addActionListener(new ButtonChangeActionListener());
        changeButton.setEnabled(false);

        loadButton = new Button("Load");
        loadButton.attachTo(this, 0, 3);
        loadButton.addActionListener(new ButtonLoadActionListener());

        genButton = new Button("Generate");
        genButton.attachTo(this, 0, 4);
        genButton.addActionListener(new ButtonGenerateActionListener());

        toStartButton = new Button("ToStart");
        toStartButton.attachTo(this, 0, 5);
        toStartButton.addActionListener(new ButtonToStartActionListener());

        toEndButton = new Button("ToEnd");
        toEndButton.attachTo(this, 0, 6);
        toEndButton.addActionListener(new ButtonToEndActionListener());

        nextButton = new Button("Next");
        nextButton.attachTo(this, 0, 7);
        nextButton.addActionListener(new ButtonNextActionListener());

        backButton = new Button("Back");
        backButton.attachTo(this, 0, 8);
        backButton.addActionListener(new ButtonBackActionListener());

        startButton = new Button("Start");
        startButton.attachTo(this, 0, 9);
        startButton.addActionListener(new ButtonStartActionListener());

        stopButton = new Button("Stop");
        stopButton.attachTo(this, 0, 10);
        stopButton.addActionListener(new ButtonStopActionListener());
        stopButton.setEnabled(false);

        // Текстовое поле
        JTextArea textLog = new JTextArea(8, 10);
        textLog.setFont(new Font("Dialog", Font.PLAIN, 14));
        textLog.setTabSize(10);

        GridBagConstraints  scroll_gbc = new GridBagConstraints();
        scroll_gbc.insets = new Insets(30, 5, 5, 5);
        scroll_gbc.fill = GridBagConstraints.HORIZONTAL;
        scroll_gbc.gridx = 0;
        scroll_gbc.gridy = 12;
        scroll_gbc.gridwidth = 2;
        JScrollPane sr_panel =  new JScrollPane(textLog);
        sr_panel.setPreferredSize(new Dimension(500,100));
        this.add(sr_panel, scroll_gbc);
    }

    public void setRight(Right right) {

        this.rightPointer = right;
    }
    public void setFacade(Facade facade) {

        this.facadePointer = facade;
    }

    // -- Обработчики --

    public class ButtonChangeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            genButton.setEnabled(true);
            changeButton.setEnabled(false);

            facadePointer.toStart();
            facadePointer.drawStep(rightPointer.getTable());
            facadePointer.clear();
        }
    }

    public class SliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent evt) {
            timer.setDelay((4 - slid.getValue()) * 300);
            System.out.println("Slider: value = " + slid.getValue());
        }
    }

    public class TextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            String textY = textField1.getText();
            String textX = textField2.getText();
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
            if ((x < 2 || x > 30) || (y < 2 || y > 30)) {
                JOptionPane.showInternalMessageDialog(null, "Error! Enter size of the field in range [3, 30].");
                return;
            }

            DefaultTableModel dtm = (DefaultTableModel) rightPointer.getTable().getModel();
            dtm.setColumnCount(x);
            dtm.setRowCount(y);
            TableColumnModel columnModel = rightPointer.getTable().getColumnModel();
            for (int i = 0; i < rightPointer.getTable().getColumnCount(); ++i) {
                columnModel.getColumn(i).setPreferredWidth(20);
                columnModel.getColumn(i).setCellEditor(new DefaultCellEditor(comboBox));
                rightPointer.getTable().getColumnModel().getColumn(i).setCellRenderer(new MyRenderer());
                rightPointer.getTable().updateUI();
            }
        }
    }

    public class ButtonLoadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOpen = new JFileChooser();
            int ret = fileOpen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {

                File file = fileOpen.getSelectedFile();
            }
        }
        // Пока просто окошко
    }

    public class ButtonNextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!facadePointer.isLoad()) {
                facadePointer.loadGraph(rightPointer.getTable());
                System.out.println("Not Load or New Graph");
            }
            if (facadePointer.isLoad()) {
                genButton.setEnabled(false);
                changeButton.setEnabled(true);

                if (!facadePointer.next()) {
                    // конец
                    System.out.println("Come to End");
                }
                facadePointer.drawStep(rightPointer.getTable());
                System.out.println("Draw now");
                //textLog.setText(facadePointer.getStepLog());
            }
        }
    }

    public class ButtonBackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!facadePointer.isLoad()) {
                facadePointer.loadGraph(rightPointer.getTable());
                System.out.println("Not Load or New Graph");
            }
            if (facadePointer.isLoad()) {
                genButton.setEnabled(false);
                changeButton.setEnabled(true);

                if (!facadePointer.prev()) {
                    // конец
                    System.out.println("Come to Start");
                }
                facadePointer.drawStep(rightPointer.getTable());
                System.out.println("Draw now");
                //textLog.setText(facadePointer.getStepLog());
            }
        }
    }

    public class ButtonToStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!facadePointer.isLoad()) {
                facadePointer.loadGraph(rightPointer.getTable());
                System.out.println("Not Load or New Graph");
            }
            if (facadePointer.isLoad()) {
                genButton.setEnabled(false);
                changeButton.setEnabled(true);

                facadePointer.toStart();
                facadePointer.drawStep(rightPointer.getTable());
                System.out.println("Draw now");
                //textLog.setText(facadePointer.getStepLog());
            }
        }
    }

    public class ButtonToEndActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!facadePointer.isLoad()) {
                facadePointer.loadGraph(rightPointer.getTable());
                System.out.println("Not Load or New Graph");
            }
            if (facadePointer.isLoad()) {
                genButton.setEnabled(false);
                changeButton.setEnabled(true);

                facadePointer.toEnd();
                facadePointer.drawStep(rightPointer.getTable());
                System.out.println("Draw now");
                //textLog.setText(facadePointer.getStepLog());
            }
        }
    }

    public class ButtonStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            timer.start();
            stopButton.setEnabled(true);
            startButton.setEnabled(false);
            toStartButton.setEnabled(false);
            toEndButton.setEnabled(false);
            backButton.setEnabled(false);
            nextButton.setEnabled(false);
            genButton.setEnabled(false);
            loadButton.setEnabled(false);

            // ввод таймера
        }
    }

    public class ButtonStopActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            timer.restart();
            timer.stop();
            stopButton.setEnabled(false);
            startButton.setEnabled(true);
            toStartButton.setEnabled(true);
            toEndButton.setEnabled(true);
            backButton.setEnabled(true);
            nextButton.setEnabled(true);
            loadButton.setEnabled(true);

            // Остановка таймера
        }
    }

    public class ButtonGenerateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            int x, y, koef;
            Random random = new Random();
            String textY = textField1.getText();
            String textX = textField2.getText();
            textField1.selectAll();
            try {
                x = Integer.parseInt(textX);
                y = Integer.parseInt(textY);
            }
            catch (NumberFormatException e) {
                JOptionPane.showInternalMessageDialog(null, "Error! Wrong number format.");
                return;
            }
            if ((x < 2 || x > 30) || (y < 2 || y > 30)) {
                JOptionPane.showInternalMessageDialog(null, "Error! Enter size of the field in range [3, 30].");
                return;
            }
            DefaultTableModel dtm = (DefaultTableModel) rightPointer.getTable().getModel();
            dtm.setColumnCount(x);
            dtm.setRowCount(y);
            TableColumnModel columnModel = rightPointer.getTable().getColumnModel();
            for (int i = 0; i < rightPointer.getTable().getColumnCount(); ++i)
                columnModel.getColumn(i).setPreferredWidth(20);

            for (int i = 0; i < rightPointer.getTable().getRowCount(); i++) {
                for (int j = 0; j < rightPointer.getTable().getColumnCount(); j++) {
                    koef = random.nextInt(5) ;
                    if (koef == 0)
                        rightPointer.getTable().setValueAt("W", i, j);
                    else
                        rightPointer.getTable().setValueAt("", i, j);
                }
            }

            int koord_xS, koord_yS;
            koord_xS = random.nextInt(x - 1); //gen 2 - x
            koord_yS = random.nextInt(y - 1); //gen 2 - y

            rightPointer.getTable().setValueAt("S", koord_yS, koord_xS);

            int koord_xE, koord_yE;

            koord_xE = random.nextInt(x - 1) + 1; //gen 2 - x
            koord_yE = random.nextInt(y - 1) + 1; //gen 2 - y

            while (koord_xS == koord_xE && koord_yS == koord_yE) {
                koord_xE = random.nextInt(x - 1) + 1; //gen 2 - x
                koord_yE = random.nextInt(y - 1) + 1; //gen 2 - y
            }

            rightPointer.getTable().setValueAt("E", koord_yE, koord_xE);

            for (int j = 0; j < rightPointer.getTable().getColumnCount(); j++) {
                columnModel.getColumn(j).setCellEditor(new DefaultCellEditor(comboBox));
                rightPointer.getTable().getColumnModel().getColumn(j).setCellRenderer(new MyRenderer());
                rightPointer.getTable().updateUI();
            }
        }
    }
}
