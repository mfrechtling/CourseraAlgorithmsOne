import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Comparator;

public class Solver {

    private Node goal;

    private class Node {
        private int moves;
        private Board board;
        private Node prev;
        private final int manhattanPriority;

        public Node(Board initial)
        {
            moves = 0;
            prev = null;
            board = initial;
            manhattanPriority = board.manhattan();
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial ==  null) throw new IllegalArgumentException();
        MinPQ<Node> pq = new MinPQ<Node>(new ManhattanComparator());
        MinPQ<Node> twinPq = new MinPQ<Node>(new ManhattanComparator());
        pq.insert(new Node(initial));
        twinPq.insert(new Node(initial.twin()));

        Node min = pq.delMin();
        Node twinMin = twinPq.delMin();

        while (!min.board.isGoal() && !twinMin.board.isGoal()){
            for (Board b : min.board.neighbors()){
                if (min.prev == null || !b.equals(min.prev.board)) {
                    Node n = new Node(b);
                    n.moves = min.moves + 1;
                    n.prev = min;
                    pq.insert(n);
                }
            }
            for (Board b : twinMin.board.neighbors()) {
                if (twinMin.prev == null || !b.equals(twinMin.prev.board)) {
                    Node n = new Node(b);
                    n.moves = twinMin.moves + 1;
                    n.prev = twinMin;
                    twinPq.insert(n);
                }
            }
            min = pq.delMin();
            twinMin = twinPq.delMin();
        }
        if (min.board.isGoal()) goal = min;
        else goal = null;
        /*
        Board prev = null;
        Board twinPrev = null;
        Board result = null;
        Board twinResult = null;
        while ((result == null || !result.isGoal())) {
            this.moves++;
            if (result != null) {
                for (Board n : result.neighbors()) {
                    if (n.isGoal()) {
                        this.solution.add(n);
                        return;
                    }
                    if (!n.equals(prev)) {
                        pq.insert(n);
                    }
                }
            }
            if (twinResult != null) {
                for (Board n : twinResult.neighbors()) {
                    if (n.isGoal())
                    {
                        this.solution = null;
                        this.moves = -1;
                        return;
                    }
                    if (!n.equals(twinPrev)) {
                        twinPq.insert(n);
                    }
                }
            }
            twinPrev = twinResult;
            twinResult = twinPq.delMin();
            if (twinResult.isGoal()) {
                // Board is unsolvable:
                this.solution = null;
                this.moves = -1;
                return;
            }

            prev = result;
            result = pq.delMin();
            this.solution.add(result);
        }*/
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return goal != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!isSolvable()) return -1;
        return goal.moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;
        Stack<Board> s = new Stack<Board>();
        for (Node n = goal; n != null; n = n.prev)
        {
            s.push(n.board);
        }
        return s;
    }

    private class ManhattanComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return Integer.compare(a.manhattanPriority + a.moves, b.manhattanPriority + b.moves);
        }
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
