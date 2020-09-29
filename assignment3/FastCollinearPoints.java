import java.util.Arrays;

public class FastCollinearPoints {

    private final Point[] points;
    private final LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        if (!checkValidPoints(points)) throw new IllegalArgumentException();
        this.points = points.clone();
        this.segments = findAllSegments();
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

    private static boolean checkValidPoints(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null) return false;
            for (int j = i + 1; j < points.length; j++) {
                if (points[j] == null || points[i].compareTo(points[j]) == 0) return false;
            }
        }
        return true;
    }

    private LineSegment[] findAllSegments() {
        if (this.points.length < 3) {
            return new LineSegment[0];
        }
        LineSegment[] lineSegments = new LineSegment[2];
        int index = 0;
        for (int i = 0; i < this.points.length; i++) {
            Arrays.sort(this.points);
            Arrays.sort(this.points, 0, this.points.length, this.points[i].slopeOrder());
            int first = 1;
            int last = 2;

            while (last < this.points.length) {
                while (last < this.points.length
                        && Double.compare(this.points[0].slopeTo(this.points[first]),
                                          this.points[0].slopeTo(this.points[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && this.points[0].compareTo(this.points[first]) < 0) {
                    if (index >= lineSegments.length) {
                        lineSegments = resize(lineSegments, lineSegments.length * 2);
                    }
                    lineSegments[index++] = new LineSegment(this.points[0],
                                                            this.points[last - 1]);
                }
                first = last;
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

    /*
    private static void merge(Comparator<Point> comparator, Point[] a, Point[] aux, int lo, int mid,
                              int hi) {
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
            else if (less(comparator, aux[j], aux[i])) {
                a[k] = aux[j++];    // 'hi' element is less than 'lo' element
            }
            else {
                a[k] = aux[i++];    // 'lo' element is lte than 'hi' element
            }
        }
    }

    private static void sort(Comparator<Point> comparator, Point[] a, Point[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(comparator, a, aux, lo, mid);
        sort(comparator, a, aux, mid + 1, hi);
        merge(comparator, a, aux, lo, mid, hi);
    }

    private void sort(Comparator<Point> comparator) {
        Point[] aux = new Point[this.sortedPoints.length];
        sort(comparator, this.sortedPoints, aux, 0, this.sortedPoints.length - 1);
    }

    private static boolean less(Comparator<Point> comparator, Point a, Point b) {
        return comparator.compare(a, b) < 0;
    }
    */
}
