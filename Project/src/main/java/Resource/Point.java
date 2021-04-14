package Resource;

public class Point {
    private final int x, y;

    public Point() {
        x = 0;
        y = 0;
    }
    public Point(int x, int y) throws IllegalArgumentException {
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Arguments must be positive");
        }
        this.x = x;
        this.y = y;
    }
    public Point(Point point) throws IllegalArgumentException {
        if (point == null) {
            throw new IllegalArgumentException("Argument can't be null");
        }
        this.x = point.getX();
        this.y = point.getY();
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + x + " " + y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return o.toString().equals(this.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}