import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;


public class GUI extends Thread {
    private AStar test = null;
    private Timer timer;
    private JTextField textField;
    private JTable table;
    private JButton start_button;
    private JButton stop_button;
    private JButton toStart_button;
    private JButton toEnd_button;
    private JButton back_button;
    private JButton next_button;
    private JButton gen_Button;
    private JFrame frame;
    public GUI() {
        /*JFrame */frame = new JFrame("A-Star");
        frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 11));
        frame.getContentPane().setForeground(Color.BLACK);
        frame.getContentPane().setBackground(new Color(230, 230, 250));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1260,730);
        frame.setResizable(false);

        GridBagLayout gridBagLayout = new GridBagLayout();

        gridBagLayout.columnWidths = new int[]{12, 111, 64, 868, 150, 0};
        gridBagLayout.rowHeights = new int[]{16, 8, 0, 0, 0, 0, 0, 35, 32, 17, 100, 100, 100, 100, 59, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut = new GridBagConstraints();
        gbc_horizontalStrut.anchor = GridBagConstraints.NORTH;
        gbc_horizontalStrut.gridwidth = 4;
        gbc_horizontalStrut.insets = new Insets(0, 0, 5, 0);
        gbc_horizontalStrut.gridx = 1;
        gbc_horizontalStrut.gridy = 0;
        frame.getContentPane().add(horizontalStrut, gbc_horizontalStrut);

        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        GridBagConstraints gbc_horizontalStrut_1 = new GridBagConstraints();
        gbc_horizontalStrut_1.gridheight = 14;
        gbc_horizontalStrut_1.insets = new Insets(0, 0, 0, 5);
        gbc_horizontalStrut_1.gridx = 0;
        gbc_horizontalStrut_1.gridy = 1;
        frame.getContentPane().add(horizontalStrut_1, gbc_horizontalStrut_1);

        JLabel input_label = new JLabel("Enter table size m x n:");
        input_label.setFont(new Font("Tahoma", Font.BOLD, 11));
        input_label.setForeground(Color.BLACK);
        GridBagConstraints gbc_input_label = new GridBagConstraints();
        gbc_input_label.insets = new Insets(0, 0, 5, 5);
        gbc_input_label.anchor = GridBagConstraints.EAST;
        gbc_input_label.gridx = 1;
        gbc_input_label.gridy = 1;
        frame.getContentPane().add(input_label, gbc_input_label);

        table = new JTable();
        table.setRowSelectionAllowed(false);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setBackground(new Color(255, 240, 245));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(15);
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        dtm.setColumnCount(10);
        dtm.setRowCount(10);
        TableColumnModel columnModel = table.getColumnModel();
        for (int i=0; i<table.getColumnCount(); ++i)
            columnModel.getColumn(i).setPreferredWidth(15);

        JLabel instruction_label_1 = new JLabel("          Fill table as follows:");
        instruction_label_1.setForeground(Color.BLACK);
        instruction_label_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_instruction_label_1 = new GridBagConstraints();
        gbc_instruction_label_1.anchor = GridBagConstraints.WEST;
        gbc_instruction_label_1.insets = new Insets(0, 0, 5, 0);
        gbc_instruction_label_1.gridx = 4;
        gbc_instruction_label_1.gridy = 1;
        frame.getContentPane().add(instruction_label_1, gbc_instruction_label_1);

        GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.gridheight = 13;
        gbc_table.gridwidth = 3;
        gbc_table.insets = new Insets(0, 0, 0, 5);
        gbc_table.gridx = 1;
        gbc_table.gridy = 2;
        frame.getContentPane().add(table, gbc_table);

        textField = new JTextField();
        textField.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 5);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 2;
        gbc_textField.gridy = 1;
        frame.getContentPane().add(textField, gbc_textField);
        textField.setColumns(10);
        textField.setText("10 10"); //base field size
        /*Action listen"*/
        ActionListener textActList = new TextActionListener();
        textField.addActionListener(textActList);  //listen Enter

        JLabel instruction_label_3 = new JLabel("          1 - obstacle");
        instruction_label_3.setForeground(Color.BLACK);
        instruction_label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_instruction_label_3 = new GridBagConstraints();
        gbc_instruction_label_3.anchor = GridBagConstraints.WEST;
        gbc_instruction_label_3.insets = new Insets(0, 0, 5, 0);
        gbc_instruction_label_3.gridx = 4;
        gbc_instruction_label_3.gridy = 2;
        frame.getContentPane().add(instruction_label_3, gbc_instruction_label_3);

        JLabel instruction_label_4 = new JLabel("          S - start");
        instruction_label_4.setForeground(Color.BLACK);
        instruction_label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
        GridBagConstraints gbc_instruction_label_4 = new GridBagConstraints();
        gbc_instruction_label_4.anchor = GridBagConstraints.WEST;
        gbc_instruction_label_4.insets = new Insets(0, 0, 5, 0);
        gbc_instruction_label_4.gridx = 4;
        gbc_instruction_label_4.gridy = 3;
        frame.getContentPane().add(instruction_label_4, gbc_instruction_label_4);

        JLabel instruction_label_5 = new JLabel("          E - end");
        instruction_label_5.setFont(new Font("Tahoma", Font.BOLD, 11));
        instruction_label_5.setForeground(Color.BLACK);
        GridBagConstraints gbc_instruction_label_5 = new GridBagConstraints();
        gbc_instruction_label_5.anchor = GridBagConstraints.WEST;
        gbc_instruction_label_5.insets = new Insets(0, 0, 5, 0);
        gbc_instruction_label_5.gridx = 4;
        gbc_instruction_label_5.gridy = 4;
        frame.getContentPane().add(instruction_label_5, gbc_instruction_label_5);

        JLabel lblNewLabel = new JLabel(" ");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
        gbc_lblNewLabel.gridx = 4;
        gbc_lblNewLabel.gridy = 5;
        frame.getContentPane().add(lblNewLabel, gbc_lblNewLabel);

        gen_Button = new JButton("Generate");
        gen_Button.setFont(new Font("Tahoma", Font.BOLD, 11));
        gen_Button.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_gen_Button = new GridBagConstraints();
        gbc_gen_Button.insets = new Insets(0, 0, 5, 0);
        gbc_gen_Button.gridx = 4;
        gbc_gen_Button.gridy = 6;
        frame.getContentPane().add(gen_Button, gbc_gen_Button);
        /*Action listen"*/
        ActionListener buttonGenerateActList = new ButtonnGenerateActionListener();
        gen_Button.addActionListener(buttonGenerateActList);

        toStart_button = new JButton("ToStart");
        toStart_button.setFont(new Font("Tahoma", Font.BOLD, 11));
        toStart_button.setForeground(Color.BLACK);
        toStart_button.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_toStart_button = new GridBagConstraints();
        gbc_toStart_button.insets = new Insets(0, 0, 5, 0);
        gbc_toStart_button.gridx = 4;
        gbc_toStart_button.gridy = 10;
        frame.getContentPane().add(toStart_button, gbc_toStart_button);
        ActionListener buttonToStartActList = new ButtonnToStartActionListener();
        toStart_button.addActionListener(buttonToStartActList);

        toEnd_button = new JButton("ToEnd");
        toEnd_button.setFont(new Font("Tahoma", Font.BOLD, 11));
        toEnd_button.setForeground(Color.BLACK);
        toEnd_button.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_toEnd_button = new GridBagConstraints();
        gbc_toEnd_button.insets = new Insets(0, 0, 5, 0);
        gbc_toEnd_button.gridx = 4;
        gbc_toEnd_button.gridy = 11;
        frame.getContentPane().add(toEnd_button, gbc_toEnd_button);
        /*Action listen"*/
        ActionListener buttonToEndActList = new ButtonnToEndActionListener();
        toEnd_button.addActionListener(buttonToEndActList);

        back_button = new JButton("Back");
        back_button.setFont(new Font("Tahoma", Font.BOLD, 11));
        back_button.setForeground(Color.BLACK);
        back_button.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_back_button = new GridBagConstraints();
        gbc_back_button.insets = new Insets(0, 0, 5, 0);
        gbc_back_button.gridx = 4;
        gbc_back_button.gridy = 12;
        frame.getContentPane().add(back_button, gbc_back_button);
        ActionListener buttonBackActList = new ButtonnBackActionListener();
        back_button.addActionListener(buttonBackActList);

        next_button = new JButton("Next");
        next_button.setFont(new Font("Tahoma", Font.BOLD, 11));
        next_button.setBackground(new Color(255, 240, 245));
        next_button.setForeground(Color.BLACK);
        GridBagConstraints gbc_next_button = new GridBagConstraints();
        gbc_next_button.insets = new Insets(0, 0, 5, 0);
        gbc_next_button.gridx = 4;
        gbc_next_button.gridy = 13;
        frame.getContentPane().add(next_button, gbc_next_button);
        /*Action listen"*/
        ActionListener buttonNextActList = new ButtonnNextActionListener();
        next_button.addActionListener(buttonNextActList);

        start_button = new JButton("Start");
        start_button.setFont(new Font("Tahoma", Font.BOLD, 11));
        start_button.setForeground(Color.BLACK);
        start_button.setBackground(new Color(255, 240, 245));

        stop_button = new JButton("Stop");
        stop_button.setFont(new Font("Tahoma", Font.BOLD, 11));
        stop_button.setForeground(Color.BLACK);
        stop_button.setBackground(new Color(255, 240, 245));
        GridBagConstraints gbc_stop_button = new GridBagConstraints();
        gbc_stop_button.gridx = 4;
        gbc_stop_button.gridy = 14;
        frame.getContentPane().add(stop_button, gbc_stop_button);
        stop_button.setVisible(false);
        /*Action listen"*/
        ActionListener buttonStopActList = new ButtonnStopActionListener();
        stop_button.addActionListener(buttonStopActList);

        GridBagConstraints gbc_start_button = new GridBagConstraints();
        gbc_start_button.gridx = 4;
        gbc_start_button.gridy = 14;
        frame.getContentPane().add(start_button, gbc_start_button);
        /*Action listen"*/
        ActionListener buttonStartActList = new ButtonnStartActionListener();
        start_button.addActionListener(buttonStartActList);

        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu help = new JMenu("Help");
        menubar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);

        frame.setVisible(true);
    }

    // add max (65,40)
    public class TextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            if (test != null) {
                test = null;
                for (int i=0; i<table.getRowCount(); i++) {
                    for (int j=0; j<table.getColumnCount(); j++) {
                        table.setValueAt("", i, j);
                    }
                }
            }
            String text = textField.getText();
            textField.selectAll();

            String[] xy = text.split(" ");

            int x, y;

            try {
                x = Integer.parseInt(xy[0]);
                y = Integer.parseInt(xy[1]);
            }
            catch (Throwable e) {
                JOptionPane.showInternalMessageDialog(null, "Error! Enter size of the field m x n.");
                return; //skip
            }
            if (x < 1 || x > 65 || y < 1 || y > 40 || (x == 1 && y == 1)) {
                JOptionPane.showInternalMessageDialog(null, "Error! Enter size of the field in range m <= 65, n <= 40.");
                return;
            }
            DefaultTableModel dtm = (DefaultTableModel) table.getModel();
            dtm.setColumnCount(x);
            dtm.setRowCount(y);
            TableColumnModel columnModel = table.getColumnModel();
            for (int i=0; i<table.getColumnCount(); ++i)
                columnModel.getColumn(i).setPreferredWidth(15);

            //frame.repaint();
        }
    }

    public class ButtonnNextActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (test == null) {
                test = new AStar();
                switch (test.init(table)) {
                    case 0: break;
                    case 1: JOptionPane.showInternalMessageDialog(null, "Error! E or S are not found.");
                        test = null;
                        return;
                    case -1: JOptionPane.showInternalMessageDialog(null, "Error! Please input one Start cell.");
                        test = null;
                        return;
                    case -2: JOptionPane.showInternalMessageDialog(null, "Error! Please input one End cell.");
                        test = null;
                        return;
                }
            }

            int status = test.nextStep(table);
            if (status == 0) JOptionPane.showInternalMessageDialog(null, "Error! You are already at end position.");
            if (status == -1)JOptionPane.showInternalMessageDialog(null, "Error! No available path.");
        }
    }

    public class ButtonnBackActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (test == null) {
                JOptionPane.showInternalMessageDialog(null, "Error! You are already at start position.");
                return;
            }
            int status = test.backStep(table); //if 0 free test
            if (status == 0) test = null;
        }
    }
    public class ButtonnStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (test == null) {
                test = new AStar();
                switch (test.init(table)) {
                    case 0: break;
                    case 1: JOptionPane.showInternalMessageDialog(null, "Error! E or S are not found.");
                        test = null;
                        return;
                    case -1: JOptionPane.showInternalMessageDialog(null, "Error! Please input one Start cell.");
                        test = null;
                        return;
                    case -2: JOptionPane.showInternalMessageDialog(null, "Error! Please input one End cell.");
                        test = null;
                        return;
                }
            }
            stop_button.setVisible(true);
            start_button.setVisible(false);
            toStart_button.setVisible(false);
            toEnd_button.setVisible(false);
            back_button.setVisible(false);
            next_button.setVisible(false);
            gen_Button.setVisible(false);

            repeatNextStep();
        }
    }

    public class ButtonnToEndActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (test == null) {
                test = new AStar();
                switch (test.init(table)) {
                    case 0: break;
                    case 1: JOptionPane.showInternalMessageDialog(null, "Error! E or S are not found.");
                        test = null;
                        return;
                    case -1: JOptionPane.showInternalMessageDialog(null, "Error! Please input one Start cell.");
                        test = null;
                        return;
                    case -2: JOptionPane.showInternalMessageDialog(null, "Error! Please input one End cell.");
                        test = null;
                        return;
                }
            }
            int status = test.nextStep(table);
            if (status == 0) JOptionPane.showInternalMessageDialog(null, "Error! You are already at end position.");
            if (status == -1)JOptionPane.showInternalMessageDialog(null, "Error! No available path.");

            while (test.nextStep(table) == 1) {}
        }
    }
    public class ButtonnToStartActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (test == null) {
                JOptionPane.showInternalMessageDialog(null, "Error! You are already at start position.");
                return;
            }

            while (test.backStep(table) != 0) {}
            test = null;
        }
    }

    public class ButtonnStopActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            stop_button.setVisible(false);
            start_button.setVisible(true);
            toStart_button.setVisible(true);
            toEnd_button.setVisible(true);
            back_button.setVisible(true);
            next_button.setVisible(true);
            gen_Button.setVisible(true);
            timer.cancel();
        }
    }

    public void repeatNextStep(){
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                //System.out.println("repeat:" + table);
                int status = test.nextStep(table);
                if (status == 0) JOptionPane.showInternalMessageDialog(null, "Ready!");
                if (status == -1)JOptionPane.showInternalMessageDialog(null, "Error! No available path.");
                if (status != 1) {
                    stop_button.setVisible(false);
                    start_button.setVisible(true);
                    toStart_button.setVisible(true);
                    toEnd_button.setVisible(true);
                    back_button.setVisible(true);
                    next_button.setVisible(true);
                    gen_Button.setVisible(true);
                    cancel();
                }
            }
        };

        timer = new Timer("Timer");

        timer.scheduleAtFixedRate(repeatedTask, 500L, 500L);
    }

    // add max (65,40)
    public class ButtonnGenerateActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (test != null) {
                test = null;
            }

            int x, y, koef;
            Random random = new Random();
            x = random.nextInt(63) + 2; //gen 2 - 65
            y = random.nextInt(38) + 2; //gen 2 - 40
            textField.setText(x + " " + y);

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