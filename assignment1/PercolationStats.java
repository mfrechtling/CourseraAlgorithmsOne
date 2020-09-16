/* *****************************************************************************
 *  Name:              Michael Frechtling
 *  Coursera User ID:
 *  Last modified:     16/09/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static double confConst = 1.96;
    private final double[] results;
    private final int t;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        this.t = trials;
        results = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                perc.open(row, col);
            }
            results[i] = (double) perc.numberOfOpenSites() / (double) (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return (mean() - ((confConst * stddev()) / (Math.sqrt(t))));
    }

    public double confidenceHi() {
        return (mean() + ((confConst * stddev()) / (Math.sqrt(t))));
    }

    public static void main(String[] args) {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]),
                                                      Integer.parseInt(args[1]));
        System.out.println("mean\t\t\t= " + Double.toString(stats.mean()));
        System.out.println("stddev\t\t\t= " + Double.toString(stats.stddev()));
        System.out.println(
                "95% confidence interval\t= [" + Double.toString(stats.confidenceLo()) + ", "
                        + Double.toString(stats.confidenceHi()) + "]");
    }
}
