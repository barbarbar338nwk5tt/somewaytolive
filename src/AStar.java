import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class AStar {
    private List<Pair> close; 		//close list
    private List<Pair> open;  		//open list
    private Map<Pair, Pair> from; 	//track map
    private Map<Pair, Float> G;		//stores the cost of paths from the starting peak
    private Map<Pair, Float> F;		//estimates f (x) for each vertex
    private Pair currPosition;
    private Pair endPosition;
    private Pair startPosition;
    private Stack<List<Pair> > changes;
    private List<Pair> NewOpen;

    public AStar() {
        /* */
        close = new LinkedList<Pair>();
        open = new LinkedList<Pair>();
        from = new LinkedHashMap<Pair, Pair>();
        G = new LinkedHashMap<Pair, Float>();
        F = new LinkedHashMap<Pair, Float>();
        changes = new Stack<List<Pair> >();
        NewOpen = new LinkedList<Pair>();
        //System.out.print(table.getRowCount());

        //System.out.print(heuristic(currPosition));
    }

    public int init(JTable table) {
        /* 1 - error code
         * -1 - a lot of E
         * -2 - a lot of S
         */
        int countS = 0;
        int countE = 0;
        for (int i=0; i<table.getRowCount(); i++) {
            for (int j=0; j<table.getColumnCount(); j++) {
                String value = (String) table.getValueAt(i, j);
                //System.out.println(value);
                if (value == null) {
                    table.getColumnModel().getColumn(j).setCellRenderer(new MyRenderer());
                    table.updateUI();
                    continue;
                }
                if (value.compareToIgnoreCase("E") == 0 /*value.equals("E")*/) {
                    endPosition = new Pair(i, j);
                    countE++;
                }
                if (value.compareToIgnoreCase("S") == 0 /*value.equals("S")*/) {
                    currPosition = new Pair(i, j);
                    startPosition = new Pair(i, j);
                    countS++;
                }

                table.getColumnModel().getColumn(j).setCellRenderer(new MyRenderer());
                table.updateUI();
            }
        }

        if (endPosition == null || currPosition == null) {
            return 1;
        }

        if (countS > 1) return -1;
        if (countE > 1) return -2;

        G.put(currPosition, (float) 0);
        F.put(currPosition, G.get(currPosition) + heuristic(currPosition));
        open.add(currPosition);
        repaint(table, currPosition, "o");

        return 0;
    }

    private Pair minF(List<Pair> open, Map<Pair, Float> F) {
        Pair res = open.get(0);
        float min = F.get(res);

        for (Pair var : open) {
            if (F.get(var) < min) {
                res = var;
                min = F.get(var);
            }
        }

        return res;
    }

    private boolean inList(List<Pair> _list, Pair point) {
        for (Pair var : _list)
            if (var.x == point.x && var.y == point.y) return true;
        return false;
    }

    private void repaint(JTable table, Pair _pair, String color_type) {
        //System.out.println("repaint:" + table);
        if (_pair == null) return;
        table.setValueAt(color_type, _pair.x, _pair.y);
        table.getColumnModel().getColumn(_pair.y).setCellRenderer(new MyRenderer());
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                table.updateUI();
            }
        });
        //table.updateUI();
    }

    private void reconstruction(JTable table, Map<Pair, Pair> _map) {
        //System.out.println("reconstruction:" + table);

        Pair elem = null;
        for (Pair var : _map.keySet())
            if (var.x == endPosition.x && var.y == endPosition.y) {
                elem = var;
            }

        while (elem.x != startPosition.x || elem.y != startPosition.y) {
            repaint(table, elem, "r");
            elem = _map.get(elem);
        }

        repaint(table, startPosition, "r");
    }

    private void ReturnToAlgo(JTable table, Map<Pair, Pair> _map)
    {
        Pair elem = null;
        for (Pair var : _map.keySet())
            if (var.x == endPosition.x && var.y == endPosition.y) {
                elem = var;
            }

        while (elem.x != startPosition.x || elem.y != startPosition.y) {
            repaint(table, elem, "c");
            elem = _map.get(elem);
        }

        repaint(table, startPosition, "c");
    }

    private List<Pair> getNeighbors(JTable table, Pair point) {
        //System.out.println("getNeg:" + table);
        int max_x = table.getRowCount();
        int max_y = table.getColumnCount();
        List<Pair> neighbors = new LinkedList<Pair>();

        if (point.x + 1 < max_x) {
            if (table.getValueAt(point.x + 1, point.y) == null)
                neighbors.add(new Pair(point.x + 1, point.y));
            else if (!table.getValueAt(point.x + 1, point.y).equals("1"))
                neighbors.add(new Pair(point.x + 1, point.y));
        }
        if (point.x - 1 > -1) {
            if (table.getValueAt(point.x - 1, point.y) == null)
                neighbors.add(new Pair(point.x - 1, point.y));
            else if (!table.getValueAt(point.x - 1, point.y).equals("1"))
                neighbors.add(new Pair(point.x - 1, point.y));
        }
        if (point.y + 1 < max_y) {
            if (table.getValueAt(point.x, point.y + 1) == null)
                neighbors.add(new Pair(point.x, point.y + 1));
            else if (!table.getValueAt(point.x, point.y + 1).equals("1"))
                neighbors.add(new Pair(point.x, point.y + 1));
        }
        if (point.y - 1 > -1) {
            if (table.getValueAt(point.x, point.y - 1) == null)
                neighbors.add(new Pair(point.x, point.y - 1));
            else if (!table.getValueAt(point.x, point.y - 1).equals("1"))
                neighbors.add(new Pair(point.x, point.y - 1));
        }
        if (point.x - 1 > -1 && point.y + 1 < max_y) {
            if (table.getValueAt(point.x - 1, point.y + 1) == null)
                neighbors.add(new Pair(point.x - 1, point.y + 1));
            else if (!table.getValueAt(point.x - 1, point.y + 1).equals("1"))
                neighbors.add(new Pair(point.x - 1, point.y + 1));
        }
        if (point.x - 1 > -1 && point.y - 1 > -1) {
            if (table.getValueAt(point.x - 1, point.y - 1) == null)
                neighbors.add(new Pair(point.x - 1, point.y - 1));
            else if (!table.getValueAt(point.x - 1, point.y - 1).equals("1"))
                neighbors.add(new Pair(point.x - 1, point.y - 1));
        }
        if (point.x + 1 < max_x && point.y + 1 < max_y) {
            if (table.getValueAt(point.x + 1, point.y + 1) == null)
                neighbors.add(new Pair(point.x + 1, point.y + 1));
            else if (!table.getValueAt(point.x + 1, point.y + 1).equals("1"))
                neighbors.add(new Pair(point.x + 1, point.y + 1));
        }
        if (point.x + 1 < max_x && point.y - 1 > -1) {
            if (table.getValueAt(point.x + 1, point.y - 1) == null)
                neighbors.add(new Pair(point.x + 1, point.y - 1));
            else if (!table.getValueAt(point.x + 1, point.y - 1).equals("1"))
                neighbors.add(new Pair(point.x + 1, point.y - 1));
        }

        return neighbors;
    }

    public int nextStep(JTable table) {
        /*  0 - complete
         *  1 - in process
         * -1 - no way
         */
        //System.out.println("nextStep:" + table);
        NewOpen = new LinkedList<Pair>();
        if (open.isEmpty()) return -1; //end

        currPosition = minF(open, F);

        if (currPosition.x == endPosition.x && currPosition.y == endPosition.y) {
            reconstruction(table, from);
            return 0; //end
        }

        open.remove(currPosition);
        close.add(currPosition);
        /*paint*/
        repaint(table, currPosition, "c");

        for (Pair neighbor : getNeighbors(table, currPosition)) {
            if (inList(close, neighbor)) continue;

            float tmpG = G.get(currPosition) + (float) Math.sqrt(Math.pow(neighbor.x - currPosition.x, 2)
                    + Math.pow(neighbor.y - currPosition.y, 2));

            if (!inList(open, neighbor)) {
                from.put(neighbor, currPosition);
                G.put(neighbor, tmpG);
                F.put(neighbor, G.get(neighbor) + heuristic(neighbor));
            }

            for (Pair var : G.keySet())
                if (var.x == neighbor.x && var.y == neighbor.y)
                    neighbor = var;

            if (tmpG < G.get(neighbor)) {
                from.put(neighbor, currPosition);
                G.put(neighbor, tmpG);
                F.put(neighbor, G.get(neighbor) + heuristic(neighbor));
            }

            if (!inList(open, neighbor)) {
                open.add(neighbor); /*paint*/
                NewOpen.add(neighbor);
                repaint(table, neighbor, "o");
            }
        }
        //System.out.println(NewOpen);
        changes.push(NewOpen);
        NewOpen=null;
        return 1;
    }
    public int backStep(JTable table)
    {
        /*  0 - in the start
         *  1 - in process
         */

        if (currPosition.x == startPosition.x && startPosition.y == currPosition.y) {
            for (Pair neighbor : changes.pop())
            {
                //System.out.println(neighbor.x + " =x " + neighbor.y + " =y ");
                open.remove(neighbor);
                repaint(table, neighbor, "");
            }

            repaint(table, startPosition, "");
            table.setValueAt("S", startPosition.x, startPosition.y);

            return 0; //cannot
        }

        if (currPosition.x == endPosition.x && endPosition.y == currPosition.y) {
            ReturnToAlgo(table,from);
            repaint(table, currPosition, "o");
            currPosition = close.get(close.size()-1);
            return -1;
        }

        close.remove(currPosition);
        open.add(currPosition);
        repaint(table, currPosition, "o");

        for (Pair neighbor : changes.pop())
        {
            //System.out.println(neighbor.x + " =x " + neighbor.y + " =y ");
            open.remove(neighbor);
            repaint(table, neighbor, "");
        }

        currPosition = close.get(close.size()-1);

        if (table.getValueAt(endPosition.x, endPosition.y).equals(""))
            table.setValueAt("E", endPosition.x, endPosition.y);

        return 1;
    }

    private float heuristic(Pair point) {
        return (float) Math.sqrt(Math.pow(point.x - endPosition.x, 2) + Math.pow(point.y - endPosition.y, 2));
    }

    public void test(JTable table) {
        reconstruction(table, from);
    }
}

class Pair {
    public int x = 0;
    public int y = 0;

    public Pair(int _x, int _y) { x = _x; y = _y;}
}
