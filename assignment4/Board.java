import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private final int[][] tiles;
    private final int hammingDistance;
    private final int manhattanDistance;
    private Board twin = null;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][];
        for (int i = 0; i < tiles.length; i++) {
            this.tiles[i] = new int[tiles[i].length];
            this.tiles[i] = Arrays.copyOf(tiles[i], tiles[i].length);
        }
        this.hammingDistance = getHammingDistance();
        this.manhattanDistance = getManhattanDistance();
    }

    // string representation of this board
    public String toString() {
        StringBuilder stringTiles = new StringBuilder();
        stringTiles.append(this.tiles.length);
        stringTiles.append("\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                stringTiles.append(tiles[i][j]);
                if (j == dimension() - 1) {
                    stringTiles.append("\n");
                } else {
                    stringTiles.append(" ");
                }
            }
        }
        return stringTiles.toString();
    }

    // board dimension n
    public int dimension() {
        return this.tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        return this.hammingDistance;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return this.manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return (hamming() == 0 && manhattan() == 0);
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null) return false;
        if (this.getClass() != y.getClass()) return false;
        Board that = (Board) y;
        if (this.dimension() != that.dimension()) return false;
        if (this.manhattan() != that.manhattan()) return false;
        if (this.hamming() != that.hamming()) return false;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (this.tiles[i][j] != that.tiles[i][j]) return false;
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        int blankLocation = findBlank();
        int blankRow = blankLocation / dimension();
        int blankColumn = blankLocation % dimension();

        if (blankRow > 0) {
            // Neighbor 1 - exchange from left
            int[][] neighbor = getCopy(this.tiles);
            neighbor[blankRow][blankColumn] = this.tiles[blankRow - 1][blankColumn];
            neighbor[blankRow - 1][blankColumn] = 0;
            neighbors.add(new Board(neighbor));
        }
        if (blankColumn > 0) {
            // Neighbor 2 - exchange from top
            int[][] neighbor = getCopy(this.tiles);
            neighbor[blankRow][blankColumn] = this.tiles[blankRow][blankColumn - 1];
            neighbor[blankRow][blankColumn - 1] = 0;
            neighbors.add(new Board(neighbor));
        }
        if (blankRow < dimension() - 1) {
            // Neighbor 3 - exchange from right
            int[][] neighbor = getCopy(this.tiles);
            neighbor[blankRow][blankColumn] = this.tiles[blankRow + 1][blankColumn];
            neighbor[blankRow + 1][blankColumn] = 0;
            neighbors.add(new Board(neighbor));
        }
        if (blankColumn < dimension() - 1) {
            // Neighbor 4 - exchange from bottom
            int[][] neighbor = getCopy(this.tiles);
            neighbor[blankRow][blankColumn] = this.tiles[blankRow][blankColumn + 1];
            neighbor[blankRow][blankColumn + 1] = 0;
            neighbors.add(new Board(neighbor));
        }
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        // If this.twin is not null, we have already found the twin
        if (this.twin == null) {
            // Always switch the first and last tile (1 and (n*n - 1))
            this.twin = new Board(this.tiles);
            int exchangeRow = 0;
            int exchangeCol = 0;
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (this.twin.tiles[i][j] == 1) {
                        exchangeCol = j;
                        exchangeRow = i;
                    }
                }
            }
            int target = (dimension() * dimension()) - 1;
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (this.twin.tiles[i][j] == target) {
                        this.twin.tiles[exchangeRow][exchangeCol] = target;
                        this.twin.tiles[i][j] = 1;
                        return this.twin;
                    }
                }
            }
        }
        return this.twin;
    }

    private int getHammingDistance() {
        int hamming = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] == 0) continue;
                int expected = (i * dimension()) + j + 1;
                if (this.tiles[i][j] != expected) hamming++;
            }
        }
        return hamming;
    }

    private int getManhattanDistance() {
        int manhattan = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] == 0) continue;
                int expectedRow = (this.tiles[i][j] - 1) / dimension();
                int expectedColumns = (this.tiles[i][j] - 1) % dimension();
                int distance = ((Math.abs(expectedRow - i) + Math.abs(expectedColumns - j)));
                manhattan += distance;
            }
        }
        return manhattan;
    }

    private int findBlank() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (this.tiles[i][j] == 0) return ((i * dimension()) + j);
            }
        }
        throw new IllegalArgumentException();
    }

    private int[][] getCopy(int[][] toCopy) {
        int[][] copy = new int[toCopy.length][];
        for (int i = 0; i < toCopy.length; i++) {
            copy[i] = new int[toCopy[i].length];
            copy[i] = Arrays.copyOf(toCopy[i], toCopy[i].length);
        }
        return copy;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        System.out.print(initial.toString());
        System.out.println(initial.twin().toString());
    }

}
