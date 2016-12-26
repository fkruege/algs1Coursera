import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by fkruege on 12/26/2016.
 */
public class Percolation {

    public static final String ROW = "Row";
    public static final String COLUMN = "Column";
    private int _gridSize;
    private int _virtualTop;
    private int _virtualBottom;
    private final WeightedQuickUnionUF _union;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        _gridSize = n;

        // add 2 for the virtual top and botoom
        int unionSize = (n * n) + 2;
        _virtualTop = unionSize + 1;
        _virtualBottom = unionSize + 2;
        _union = new WeightedQuickUnionUF(unionSize);

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        checkRowCol(row, col);

        int index = getRowColIndex(row, col);
        if(isTopRow(row)){
            _union.union(index, _virtualTop);
        }

        if(isBottomRow(row)){
            _union.union(index, _virtualBottom);
        }


    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkRowCol(row, col);

        return true;
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        checkRowCol(row, col);

        return true;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    private void checkRowCol(int row, int col) {
        checkParameter(row, ROW);
        checkParameter(col, COLUMN);
    }

    private void checkParameter(int parameter, String parameterName) {
        if (parameter <= 0 || parameter > _gridSize) {
            throw new IndexOutOfBoundsException(parameterName + " is out of bounds");
        }
    }

    private int getRowColIndex(int row, int col) {
        int row = row--;
        int col = col--;

        int index = (row * _gridSize) + (col);
        return index;
    }

    private boolean isTopRow(int row) {
        return (row == 1);
    }

    private boolean isBottomRow(int row){
        return (row == _gridSize);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
