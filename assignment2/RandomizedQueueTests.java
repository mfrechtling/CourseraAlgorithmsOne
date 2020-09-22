public class RandomizedQueueTests {
    private final int[] queueTest = {101, 102, 103, 104, 105, 106, 107, 108, 109, 110};

    public void randomizedQueueTest() {
        System.out.println("Running test RandomizedQueueTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        } catch (Exception ex) {
            assert false : "TEST RandomizedQueue constructor failed with error: " + ex.toString();
        }
        System.out.println("Test passed");
    }

    public void isEmptyTest() {
        System.out.println("Running test isEmptyTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            assert rq.isEmpty() : "TEST isEmptyTest failed: new RandomizedQueue is not empty";
            for (int i : queueTest) {
                rq.enqueue(i);
            }
            assert !rq.isEmpty() : "TEST isEmptyTest failed: non-empty RandomizedQueue returned empty";
            for (int i : queueTest) {
                rq.dequeue();
            }
            assert rq.isEmpty() : "TEST isEmptyTest failed: empty RandomizedQueue returned non-empty";
        } catch (Exception ex) {
            assert false : "TEST isEmptyTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed");
    }

    public void sizeTest() {
        System.out.println("Running test sizeTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            for (int i : queueTest) {
                rq.enqueue(i);
            }
            assert rq.size() == queueTest.length : "Test sizeTest failed: size is incorrect.";
        } catch (Exception ex) {
            assert false : "TEST sizeTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void enqueueTest() {
        System.out.println("Running test enqueueTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            for (int i : queueTest) {
                rq.enqueue(i);
            }
        } catch (Exception ex) {
            assert false : "TEST enqueueTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void dequeueTest() {
        System.out.println("Running test dequeueTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            for (int i : queueTest) {
                rq.enqueue(i);
            }
            for (int i : queueTest) {
                int res = rq.dequeue();
            }
            assert rq.size() == 0 : "TEST dequeueTest failed: did not remove all elements";
        } catch (Exception ex) {
            assert false : "TEST dequeueTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void sampleTest() {
        System.out.println("Running test sampleTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            for (int i : queueTest) {
                rq.enqueue(i);
            }
            for (int i : queueTest) {
                int res = rq.sample();
            }
            assert rq.size() == queueTest.length : "TEST sampleTest failed: sample removed elements";
        } catch (Exception ex) {
            assert false : "TEST sampleTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public void iteratorTest() {
        System.out.println("Running test iteratorTest");
        try {
            RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
            for (int i : queueTest) {
                rq.enqueue(i);
            }
            int count = 0;
            for (int i : rq) {
                System.out.println(i);
                count++;
            }
            assert count - 1 == queueTest.length - 1 : "TEST iteratorTest failed: incorrect number of elements returned from iterator.";
        } catch (Exception ex) {
            assert false : "TEST iteratorTest failed with error: " + ex.toString();
        }
        System.out.println("Test passed.");
    }

    public static void main(String[] args) {
        System.out.println("Running RandomizedQueue tests");
        RandomizedQueueTests tests = new RandomizedQueueTests();
        try {
            tests.randomizedQueueTest();
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
            tests.enqueueTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.dequeueTest();
        } catch (AssertionError ax) {
            System.out.println(ax.getMessage());
        }
        try {
            tests.sampleTest();
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
