package Algorithm;

import Resource.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AStar {
    public static Iterator execute(Graph graph, Point start, Point end) throws IllegalArgumentException {
        if (graph == null || start == null || end == null) {
            throw new IllegalArgumentException("Argument can't be null");
        }
        ArrayList<Step> steps = new ArrayList<>();

        PriorityQueue<QueuePair> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
        HashMap<Point, Integer> lengths = new HashMap<>();
        HashMap<Point, Point> paths = new HashMap<>();
        Point curr;

        queue.add(new QueuePair(0, start));
        lengths.put(start, 0);
        paths.put(end, null);

        // Основная часть
        while(!queue.isEmpty()) {

            curr = queue.poll().point;

            // Запись итератора
            ArrayList<Point> opened = new ArrayList<>();
            for (QueuePair i : queue) opened.add(i.point);
            ArrayList<Point> closed = new ArrayList<>();
            for (Point i : lengths.keySet()) closed.add(i);
            steps.add(new Step(closed, opened, curr));
            //

            if (curr.equals(end)) break;

            for (Point i : graph.getNeighbors(curr)) {
                Integer newlen = lengths.get(curr) + graph.getEdgeLen(curr, i);
                if (!lengths.containsKey(i) || newlen < lengths.get(i)) {
                    lengths.put(i, newlen);
                    queue.add(new QueuePair(newlen + heuristic(end, i), i));
                    paths.put(i, curr);
                }
            }
        }
        return new Iterator(steps);
    }
    public static int heuristic(Point goal, Point curr) throws IllegalArgumentException {
        if (goal == null || curr == null) {
            throw new IllegalArgumentException();
        }
        return Math.abs(goal.getX() - curr.getX()) + Math.abs(goal.getY() - curr.getY());
    }

    // -- Классы --

    private static class QueuePair {
        public int priority;
        public Point point;

        public QueuePair(int priority, Point point) throws IllegalArgumentException {
            if (point == null) {
                throw new IllegalArgumentException("Argument can't be null");
            }
            this.priority = priority;
            this.point = point;
        }
    }

    public static class Iterator {
        private ArrayList<Step> steps;
        private int index;

        private Iterator(ArrayList<Step> steps) throws IllegalArgumentException {
            if (steps == null) {
                throw new IllegalArgumentException("Argument can't be null");
            }
            this.steps = steps;
            index = 0;
        }

        public Step next() {
            if (index < steps.size() - 1)
                return steps.get(++index);
            return null;
        }
        public Step prev() {
            if (index > 0)
                return steps.get(--index);
            return null;
        }
        public Step curr() {
            return steps.get(index);
        }

        public Step toStart() {
            index = 0;
            return steps.get(index);
        }
        public Step toEnd() {
            index = steps.size() - 1;
            return steps.get(index);
        }
        public Step toIndex(int i) throws IllegalArgumentException {
            if (i < 0 || i > steps.size() - 1) {
                throw new IllegalArgumentException("Index is out of range");
            }
            index = i;
            return steps.get(index);
        }

        public int getIndex() {
            return index;
        }
    }

    public static class Step {
        private ArrayList<Point> closed;
        private ArrayList<Point> opened;
        private Point current;

        public Step(ArrayList<Point> closed, ArrayList<Point> opened, Point current) throws IllegalArgumentException {
            if (closed == null || opened == null || current == null) {
                throw new IllegalArgumentException("Argument can't be null");
            }
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
