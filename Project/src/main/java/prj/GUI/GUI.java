package prj.GUI;

import javax.swing.*;
import java.awt.*;


public class GUI extends JFrame {

    private Left leftPanel;
    private Right rightPanel;

    public GUI() {
        super("A-Star");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // получить размер экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int W = (int)screenSize.getWidth(); // получить ширину экрана
        int H = (int)screenSize.getHeight(); // получить высоту экрана

        this.setMinimumSize(new Dimension(W, H));
        this.setResizable(false);

        this.getContentPane().setLayout(new GridLayout(1, 2));

        leftPanel = new Left();
        rightPanel = new Right();
        leftPanel.setTable(rightPanel.get_table());
        this.add(leftPanel);
        this.add(rightPanel);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu help = new JMenu("Help");
        menuBar.add(help);
        JMenuItem about = new JMenuItem("About");
        help.add(about);
        this.setVisible(true);
    }

    public Left getLeftPanel() {

        return leftPanel;
    }
    public Right getRightPanel() {

        return rightPanel;
    }
}