public class TestBench {

    public static void TestSlopeTo() {
        Point a = new Point(263, 107);
        Point b = new Point(263, 107);
        assert a.slopeTo(b) == Double.NEGATIVE_INFINITY :
                "FAILED: incorrect slope for equal points.";
    }

    public static void main(String[] args) {
        System.out.println("Test Point.SlopeTo:");
        TestSlopeTo();
    }
}
