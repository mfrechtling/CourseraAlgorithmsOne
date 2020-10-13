import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.ArrayList;

public class KdTree {

    private Node root;
    private int size;

    private class Node {
        private final Point2D point;
        private RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV r) {
            this.point = p;
            if (r == null) {
                this.rect = new RectHV(0, 0, 1, 1);
            }
            else {
                this.rect = r;
            }
        }
    }

    public KdTree() {                             // construct an empty set of points
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {                      // is the set empty?
        return this.root == null;
    }

    public int size() {                             // number of points in the set
        return this.size;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (contains(p)) {
            return;
        }
        if (isEmpty()) {
            root = insertV(root, p, null);
        }
        else {
            root = insertV(root, p, root.rect);
        }
    }

    public boolean contains(Point2D p) {            // does the set contain point p?
        if (p == null) throw new IllegalArgumentException();
        return contains(root, p, true);
    }

    public void draw() {                            // draw all points to standard draw
        draw(this.root, true);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        ArrayList<Point2D> result = new ArrayList<Point2D>();
        range(root, rect, result);
        return result;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        if (isEmpty()) return null;
        return nearest(root, p, root.point, true);
    }

    private Point2D nearest(Node n, Point2D p, Point2D nearest, boolean vert) {
        Point2D champion = nearest;
        if (champion == null || (p.distanceSquaredTo(n.point) < p.distanceSquaredTo(champion))) {
            champion = n.point;
        }
        double compare = 0;
        if (vert) {
            compare = Point2D.X_ORDER.compare(p, n.point);
        }
        else {
            compare = Point2D.Y_ORDER.compare(p, n.point);
        }
        if (compare > 0) {
            // Right/Top first
            if (n.rt != null && n.rt.rect.distanceSquaredTo(p) < p
                    .distanceSquaredTo(champion)) {
                champion = nearest(n.rt, p, champion, !vert);
            }
            if (n.lb != null && n.lb.rect.distanceSquaredTo(p) < p
                    .distanceSquaredTo(champion)) {
                champion = nearest(n.lb, p, champion, !vert);
            }
            return champion;
        }
        else {
            // Left/Bottom first
            if (n.lb != null && n.lb.rect.distanceSquaredTo(p) < p
                    .distanceSquaredTo(champion)) {
                champion = nearest(n.lb, p, champion, !vert);
            }
            if (n.rt != null && n.rt.rect.distanceSquaredTo(p) < p
                    .distanceSquaredTo(champion)) {
                champion = nearest(n.rt, p, champion, !vert);
            }
        }
        return champion;
    }

    private void range(Node n, RectHV r, ArrayList<Point2D> list) {
        if (n == null) return;
        if (r.contains(n.point)) list.add(n.point);
        if (n.lb != null && r.intersects(n.lb.rect)) range(n.lb, r, list);
        if (n.rt != null && r.intersects(n.rt.rect)) range(n.rt, r, list);
    }

    private void draw(Node n, boolean vert) {
        if (n == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(n.point.x(), n.point.y());
        StdDraw.setPenRadius();
        if (vert) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.point.x(), n.rect.ymin(), n.point.x(), n.rect.ymax());
        }
        else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(n.rect.xmin(), n.point.y(), n.rect.xmax(), n.point.y());
        }

        if (n.lb != null) draw(n.lb, !vert);
        if (n.rt != null) draw(n.rt, !vert);
    }

    private boolean contains(Node n, Point2D p, boolean isVertical) {
        if (n == null) return false;
        if (n.point.equals(p)) return true;
        int compare;
        if (isVertical) {
            compare = Point2D.X_ORDER.compare(n.point, p);
        }
        else {
            compare = Point2D.Y_ORDER.compare(n.point, p);
        }
        if (compare > 0) return contains(n.lb, p, !isVertical);
        return contains(n.rt, p, !isVertical);
    }

    private Node insertH(Node n, Point2D p, RectHV r) {
        if (n == null) {
            size++;
            return new Node(p, r);
        }

        if (p.equals(n.point)) return n;

        if (Point2D.Y_ORDER.compare(n.point, p) > 0) {
            // BELOW
            if (n.lb == null) {
                n.lb = insertV(null, p, new RectHV(r.xmin(), r.ymin(), r.xmax(), n.point.y()));
            }
            else {
                n.lb = insertV(n.lb, p, n.lb.rect);
            }
        }
        else {
            // ABOVE
            if (n.rt == null) {
                n.rt = insertV(null, p, new RectHV(r.xmin(), n.point.y(), r.xmax(), r.ymax()));
            }
            else {
                n.rt = insertV(n.rt, p, n.rt.rect);
            }
        }
        return n;
    }

    private Node insertV(Node n, Point2D p, RectHV r) {
        if (n == null) {
            size++;
            return new Node(p, r);
        }

        if (p.equals(n.point)) return n;

        if (Point2D.X_ORDER.compare(n.point, p) > 0) {
            // LEFT
            if (n.lb == null) {
                n.lb = insertH(null, p, new RectHV(r.xmin(), r.ymin(), n.point.x(), r.ymax()));
            }
            else {
                n.lb = insertH(n.lb, p, n.lb.rect);
            }
        }
        else {
            // RIGHT
            if (n.rt == null) {
                n.rt = insertH(null, p, new RectHV(n.point.x(), r.ymin(), r.xmax(), r.ymax()));
            }
            else {
                n.rt = insertH(n.rt, p, n.rt.rect);
            }
        }
        return n;
    }

    public static void main(String[] args) {
        // This method is intentionally blank
    }
}
