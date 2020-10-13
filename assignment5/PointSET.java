import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {
    private final SET<Point2D> points;

    public PointSET() {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return points.size() == 0;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (!points.contains(p)) points.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return points.contains(p);
    }

    public void draw() {
        for (Point2D p : points) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> result = new ArrayList<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) result.add(p);
        }
        return result;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        Point2D champion = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Point2D point : points) {
            if (p.distanceSquaredTo(point) < dist) {
                champion = point;
                dist = p.distanceSquaredTo(point);
            }
        }
        return champion;
    }

    public static void main(String[] args) {
        // This method is intentionally blank
    }
}
