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
        PriorityQueue<QueuePair> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.priority));
        HashMap<Point, Integer> lengths = new HashMap<>();
        HashMap<Point, Point> paths = new HashMap<>();
        Point curr;

        queue.add(new QueuePair(0, start));
        lengths.put(start, 0);
        paths.put(end, null);

        ArrayList<Step> steps = new ArrayList<>();
        while(!queue.isEmpty()) {
            curr = queue.poll().point;

            Step step = new Step();
            step.closed.addAll(lengths.keySet());
            for (QueuePair i : queue) step.opened.add(i.point);
            for (Point i = curr; !i.equals(start); i = paths.get(i)) step.path.add(i);
            step.current = curr;
            step.previous = paths.get(curr);
            step.pathLen = lengths.get(curr);
            step.heuristic = heuristic(end, curr);
            steps.add(step);

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

        public Iterator(ArrayList<Step> steps) throws IllegalArgumentException {
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
    }

    public static class Step {
        private ArrayList<Point> closed;
        private ArrayList<Point> opened;
        private ArrayList<Point> path;
        private Point current, previous;
        private int pathLen, heuristic;

        private Step() {
            closed = new ArrayList<>();
            opened = new ArrayList<>();
            path = new ArrayList<>();
            current = new Point();
            previous = new Point();
            pathLen = 0;
            heuristic = 0;
        }

        public ArrayList<Point> getOpened() {
            return opened;
        }
        public ArrayList<Point> getClosed() {
            return closed;
        }
        public ArrayList<Point> getPath() {
            return path;
        }
        public Point getCurrent() {
            return current;
        }
        public Point getPrevious() {
            return previous;
        }
        public int getPathLen() {
            return pathLen;
        }
        public int getHeuristic() {
            return heuristic;
        }

        @Override
        public String toString() {
            return "current point: " + current + " get from point: " + previous +
                    "\n    path length: " + pathLen +
                    "\n    heuristic: " + heuristic;
        }
    }
}
