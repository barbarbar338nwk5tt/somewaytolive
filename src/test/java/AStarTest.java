import javax.swing.JTable;
import static org.junit.Assert.*;

public class AStarTest {

    private AStar astar;
    private JTable table;

    @org.junit.Before
    public void setUp() throws Exception {
        table = new JTable(3, 3);
        table.setRowSelectionAllowed(false);
        table.setValueAt("S", 0, 0);
        table.setValueAt("E", 2, 2);
        table.setValueAt("1", 0, 1);
        table.setValueAt("1", 1, 1);
        table.setValueAt("1", 2, 1);

        astar = new AStar();
        astar.init(table);
    }

    @org.junit.Test
    public void nextStep_3step() {
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);

        Pair act = astar.getCurrPosition();
        Pair res = new Pair(2, 0);
        assertEquals(res.toString(), act.toString());
    }

    @org.junit.Test
    public void nextStep_5step() {
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);

        Pair act = astar.getCurrPosition();
        Pair res = new Pair(2, 0);
        assertEquals(res.toString(), act.toString());
    }

    @org.junit.Test
    public void nextStep_RetCheck() {
        int act = astar.nextStep(table);
        act = astar.nextStep(table);
        act = astar.nextStep(table);
        act = astar.nextStep(table);
        act = astar.nextStep(table);

        int res = -1;
        assertEquals(res, act);
    }

    @org.junit.Test
    public void backStep_from20_1step() {
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);

        astar.backStep(table);

        Pair act = astar.getCurrPosition();
        Pair res = new Pair(1, 0);
        assertEquals(res.toString(), act.toString());
    }

    @org.junit.Test
    public void backStep_from20_3step() {
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);

        astar.backStep(table);
        astar.backStep(table);
        astar.backStep(table);

        Pair act = astar.getCurrPosition();
        Pair res = new Pair(0, 0);
        assertEquals(res.toString(), act.toString());
    }

    @org.junit.Test
    public void backStep_RetCheck() {
        astar.nextStep(table);
        astar.nextStep(table);
        astar.nextStep(table);

        int act = astar.backStep(table);
        act = astar.backStep(table);
        act = astar.backStep(table);

        int res = 0;
        assertEquals(res, act);
    }
}