package Algorithm;

import Resource.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar {

    private Point start, end;
    private Graph graph;
    private ArrayList<Step> steps;

    public AStar() {
        start = new Point();
        end = new Point();
        graph = new Graph();
        steps = new ArrayList<>();
    }

    public void setGraph(Graph graph) {

        this.graph = graph;
        this.execute();
    }
    public void setStart(Point start) {

        this.start = start;
        this.execute();
    }
    public void setEnd(Point end) {

        this.end = end;
        this.execute();
    }

    public Graph getGraph() {

        return graph;
    }
    public Point getStart() {

        return start;
    }
    public Point getEnd() {

        return end;
    }

    public boolean execute() {

        this.steps = new ArrayList<>();

        PriorityQueue<QueuePair> queue = new PriorityQueue<>(new Comparator<QueuePair>() {
            @Override
            public int compare(QueuePair o1, QueuePair o2) {
                return o1.priority - o2.priority;
            }
        });
        HashMap<Point, Integer> lengths = new HashMap<>();
        HashMap<Point, Point> paths = new HashMap<>();
        Point curr;

        queue.add(new QueuePair(0, start));
        lengths.put(start, 0);
        paths.put(end, null);

        // Основная часть

        while(!queue.isEmpty()) {

            curr = queue.poll().point;
            if (curr.equals(end)) break;

            for (Point i : graph.getMap().get(curr).keySet()) {

                Integer newlen = lengths.get(curr) + graph.getMap().get(curr).get(i);
                if (!lengths.containsKey(i) || newlen < lengths.get(i)) {

                    lengths.put(i, newlen);
                    queue.add(new QueuePair(newlen + heuristic(end, i), i));
                    paths.put(i, curr);
                }
            }

            ArrayList<Point> opened = new ArrayList<>();
            for (QueuePair i : queue) opened.add(i.point);
            ArrayList<Point> closed = new ArrayList<>();
            for (Point i : lengths.keySet()) closed.add(i);
            steps.add(new Step(closed, opened, curr));
        }

        return paths.get(end) != null;
    }
    public static int heuristic(Point goal, Point curr) {

        return Math.abs(goal.getX() - curr.getX()) + Math.abs(goal.getY() - curr.getY());
    }

    public Iterator getIterator() {

        return new Iterator(steps);
    }

    // -- Классы --

    private static class QueuePair {

        public int priority;
        public Point point;

        public QueuePair(int priority, Point point) {
            this.priority = priority;
            this.point = point;
        }
    }

    public static class Iterator {

        private ArrayList<Step> steps;
        private int index;

        private Iterator(ArrayList<Step> steps) {
            this.steps = steps;
            index = 0;
        }

        public boolean next(Step s) {

            if (index < steps.size() - 1) index++;
            s = steps.get(index);
            return index != steps.size() - 1;
        }
        public boolean prev(Step s) {

            if (index > 0) index--;
            s = steps.get(index);
            return index != 0;
        }
        public Step curr() {

            return steps.get(index);
        }

        public boolean isEmpty() {

            return steps.isEmpty();
        }
    }

    public static class Step {

        private ArrayList<Point> closed;
        private ArrayList<Point> opened;
        private Point current;

        public Step() {
            this.closed = new ArrayList<>();
            this.opened = new ArrayList<>();
            this.current = new Point();
        }
        public Step(ArrayList<Point> closed, ArrayList<Point> opened, Point current) {
            this.closed = closed;
            this.opened = opened;
            this.current = current;
        }

        public ArrayList<Point> getOpened() {

            return opened;
        }
        public ArrayList<Point> getClosed() {

            return closed;
        }
        public Point getCurrent() {

            return current;
        }
    }
}
