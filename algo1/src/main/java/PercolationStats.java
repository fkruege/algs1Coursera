import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.IOException;

/**
 * Created by fkruege on 12/26/2016.
 */
public class PercolationStats {

    private int _gridSize;
    private int _trials;

    private double _mean;
    private double _stddev;
    private double _confidenceLo;
    private double _confidenceHigh;


    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0) {
            throw new IllegalArgumentException("n is invalid");
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("trials is invalid");
        }

        _gridSize = n;
        _trials = trials;
        double[] results= executeTests();
        calculateResults(results);
    }

    private double[] executeTests() {

        int fromRange = 1;
        int toRange = _gridSize + 1;
        int gridSizeSquared = _gridSize * _gridSize;
        double[] thresholdResults  = new double[_trials];

        for (int i = 0; i < _trials; i++) {
            Percolation percolation = new Percolation(_gridSize);
            int openSitesCount = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(fromRange, toRange);
                int col = StdRandom.uniform(fromRange, toRange);

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSitesCount++;
                }
            }
            thresholdResults[i] = (double) openSitesCount / (double) gridSizeSquared;
        }

        return thresholdResults;


    }


    private void calculateResults(double[] results){
        _mean = StdStats.mean(results);
        _stddev = StdStats.stddev(results);
        _confidenceLo = _mean - ((1.96 * _stddev) / (Math.sqrt(_trials)));
        _confidenceHigh = _mean + ((1.96 * _stddev) / (Math.sqrt(_trials)));
    }

    // sample mean of percolation threshold
    public double mean() {
        return _mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return _stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return _confidenceLo;
    }


    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return _confidenceHigh;
    }

    // test client (described below)
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = " + stats.confidenceLo() + ", " + stats.confidenceHi());

    }

}
