/* *****************************************************************************
 *  Name:              Michael Frechtling
 *  Coursera User ID:
 *  Last modified:     16/09/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF quTop;
    private final WeightedQuickUnionUF quBottom;
    private boolean[][] sites;
    private int openSites;
    private final int n;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than zero.");
        }
        sites = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sites[i][j] = false;
            }
        }
        quTop = new WeightedQuickUnionUF((n * n) + 2);
        quBottom = new WeightedQuickUnionUF((n * n) + 1);
        openSites = 0;
        this.n = n;
    }

    private int getIndex(int row, int col) {
        return (((row - 1) * this.n) + col);
    }

    private boolean siteIsValid(int row, int col) {
        return (row > 0 && row <= n && col > 0 && col <= n);
    }

    public void open(int row, int col) {
        if (!siteIsValid(row, col)) throw new IllegalArgumentException();
        if (isOpen(row, col)) return;
        sites[row - 1][col - 1] = true;
        openSites++;
        int index = getIndex(row, col);

        // connect to top site if necessary
        if (row == 1) {
            quTop.union(index, 0);
            quBottom.union(index, 0);
        }
        // connect to bottom site if necessary
        if (row == n) {
            quTop.union(index, n * n + 1);
        }
        // connect adjacent sites if they are valid and open
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            quTop.union(index, getIndex(row - 1, col));
            quBottom.union(index, getIndex(row - 1, col));
        }
        if (row + 1 <= n && isOpen(row + 1, col)) {
            quTop.union(index, getIndex(row + 1, col));
            quBottom.union(index, getIndex(row + 1, col));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            quTop.union(index, getIndex(row, col - 1));
            quBottom.union(index, getIndex(row, col - 1));
        }
        if (col + 1 <= n && isOpen(row, col + 1)) {
            quTop.union(index, getIndex(row, col + 1));
            quBottom.union(index, getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        if (!siteIsValid(row, col)) throw new IllegalArgumentException();
        return sites[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        if (!siteIsValid(row, col)) throw new IllegalArgumentException();
        return (quBottom.find(0) == quBottom.find(getIndex(row, col)));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return (quTop.find(0) == quTop.find(n * n + 1));
    }

    public static void main(String[] args) {
        // this function is intentionally empty
    }
}
