import java.util.Arrays;

public class BruteCollinearPoints {
    private final LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (!checkValidPoints(points)) throw new IllegalArgumentException();
        Point[] sortedPoints = Arrays.copyOf(points, points.length);
        sort(sortedPoints);
        this.segments = findAllSegments(sortedPoints);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    private static boolean checkValidPoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) return false;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) return false;
            }
        }
        return true;
    }

    private LineSegment[] findAllSegments(Point[] points) {
        LineSegment[] lineSegments = new LineSegment[2];
        int index = 0;
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                double slopeA = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < points.length - 1; k++) {
                    double slopeB = points[i].slopeTo(points[k]);
                    if (slopeA != slopeB) continue;
                    for (int m = k + 1; m < points.length; m++) {
                        double slopeC = points[i].slopeTo(points[m]);
                        if (slopeA != slopeC) continue;
                        if (index >= lineSegments.length) {
                            lineSegments = resize(lineSegments, lineSegments.length * 2);
                        }
                        lineSegments[index++] = new LineSegment(points[i], points[m]);

                    }
                }
            }
        }
        return resize(lineSegments, index);
    }

    private LineSegment[] resize(LineSegment[] array, int capacity) {
        LineSegment[] temp = new LineSegment[capacity];
        for (int i = 0; i < Math.min(array.length, capacity); i++) {
            temp[i] = array[i];
        }
        return temp;
    }

    private static void merge(Point[] a, Point[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];    // Reached end of 'lo' array
            }
            else if (j > hi) {
                a[k] = aux[i++];    // Reached end of 'hi' array
            }
            else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];    // 'hi' element is less than 'lo' element
            }
            else {
                a[k] = aux[i++];    // 'lo' element is lte than 'hi' element
            }
        }
    }

    private static void sort(Point[] a, Point[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    private static void sort(Point[] a) {
        Point[] aux = new Point[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static boolean less(Point a, Point b) {
        return a.compareTo(b) < 0;
    }
}
