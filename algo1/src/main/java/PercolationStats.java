import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.IOException;

/**
 * Created by fkruege on 12/26/2016.
 */
public class PercolationStats {

    private int _gridSize;
    private int _trials;

    private int[] _testResults;
    private double[] _thresholdResults;

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
        _testResults = new int[trials];
        _thresholdResults = new double[trials];
        executeTests();
    }

    private void executeTests() {

        int fromRange = 1;
        int toRange = _gridSize + 1;
        int gridSizeSquared = _gridSize * _gridSize;

        for (int i = 0; i < _trials; i++) {
//            System.out.println("Starting trial: " + i);
            Percolation percolation = new Percolation(_gridSize);
            int openSitesCount = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(fromRange, toRange);
                int col = StdRandom.uniform(fromRange, toRange);

//                System.out.println(row + ", " + col);

                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSitesCount++;
                }
            }
//            System.out.println("Trial Result: " + openSitesCount);
            _testResults[i] = openSitesCount;
            _thresholdResults[i] = (double) openSitesCount / (double) gridSizeSquared;
        }

        for (int i = 0; i < _trials; i++) {
            System.out.println("Opensites: " + _testResults[i] + " Threshold: " + _thresholdResults[i]);

        }

    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(_thresholdResults);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(_thresholdResults);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return 0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return 0;
    }

    // test client (described below)
    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean            = " + stats.mean());
        System.out.println("stddev          = " + stats.stddev());

//        for(int i = 0; i < 100000; i++){
//            int x = StdRandom.uniform(1, 5);
//            if(x == 1 || x == 5){
//                System.out.println("Random: " + x);
//            }
//        }

    }

}
