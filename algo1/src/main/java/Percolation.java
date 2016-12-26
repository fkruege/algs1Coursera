import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by fkruege on 12/26/2016.
 */
public class Percolation {

    public static final String ROW = "Row";
    public static final String COLUMN = "Column";
    private int _size;
    private final WeightedQuickUnionUF _union;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if(n <= 0){
            throw new IllegalArgumentException("n must be greater than 0");
        }

        _size = n;
        int unionSize = n * n;
        _union = new WeightedQuickUnionUF(unionSize);

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkParameter(row, ROW);
        checkParameter(col, COLUMN);
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkParameter(row, ROW);
        checkParameter(col, COLUMN);

        return true;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkParameter(row, ROW);
        checkParameter(col, COLUMN);

        return true;
    }

    // does the system percolate?
    public boolean percolates() {

        return true;
    }


    private void checkParameter(int parameter, String parameterName){
         if(parameter <= 0 || parameter > _size ){
            throw new IndexOutOfBoundsException(parameterName + " is out of bounds");
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
