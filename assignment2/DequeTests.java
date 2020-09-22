public class DequeTests {

    private final int[] queueTest = {101, 102, 103};

    public void dequeTest() {
        System.out.println("Running test DequeTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
        } catch (Exception ex) {
            assert false : "TEST Deque constructor failed with error: " + ex.toString();
        }
        System.out.println("Test passed");
    }

    public void isEmptyTest() {
        System.out.println("Running test isEmptyTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            assert deq.isEmpty() : "TEST isEmptyTest failed: new deque is not empty";
            for (int i : queueTest) {

                deq.addFirst(i);
            }
            assert !deq.isEmpty() : "TEST isEmptyTest failed: non-empty deque returned empty";
            for (int i : queueTest) {
                deq.removeFirst();
            }
            assert deq.isEmpty() : "TEST isEmptyTest failed: empty deque returned non-empty";
        } catch (Exception ex) {
            assert false : "TEST isEmptyTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed");
    }

    public void sizeTest() {
        System.out.println("Running test sizeTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            for (int i : queueTest) {
                deq.addFirst(i);
            }
            assert deq.size() == queueTest.length : "Test sizeTest failed: size is incorrect.";
        } catch (Exception ex) {
            assert false : "TEST sizeTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void addFirstTest() {
        System.out.println("Running test addFirstTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            for (int i : queueTest) {
                deq.addFirst(i);
            }
        } catch (Exception ex) {
            assert false : "TEST addFirstTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void addLastTest() {
        System.out.println("Running test addLastTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            for (int i : queueTest) {
                deq.addLast(i);
            }
        } catch (Exception ex) {
            assert false : "TEST addLastTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void removeFirstTest() {
        System.out.println("Running test removeFirstTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            for (int i : queueTest) {
                deq.addLast(i);
            }
            for (int i : queueTest) {
                int res = deq.removeFirst();
                System.out.println(res);
                assert res == i : "TEST removeFirstTest removed the wrong value.";
            }
        } catch (Exception ex) {
            assert false : "TEST removeFirstTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void removeLastTest() {
        System.out.println("Running test removeLastTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            for (int i : queueTest) {
                deq.addFirst(i);
            }
            for (int i : queueTest) {
                int res = deq.removeLast();
                assert res == i : "TEST removeLastTest removed the wrong value.";
            }
        } catch (Exception ex) {
            assert false : "TEST removeLastTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void iteratorTest() {
        System.out.println("Running test iteratorTest");
        try {
            Deque<Integer> deq = new Deque<Integer>();
            for (int i : queueTest) {
                deq.addLast(i);
            }
            int index = 0;
            for (int i : deq) {
                System.out.println(i);
                assert i == queueTest[index] : "TEST iteratorTest failed, iterator returned unexpected value.";
                index++;
            }
            assert (index - 1 == queueTest.length - 1) : "TEST iteratorTest failed, iterator did not return all values";
        } catch (Exception ex) {
            assert false : "TEST iteratorTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public static void main(String[] args) {
        System.out.println("Running deque tests");
        DequeTests tests = new DequeTests();
        try {
            tests.dequeTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.isEmptyTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.sizeTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.addFirstTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.addLastTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.removeFirstTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.removeLastTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.iteratorTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
    }
}
