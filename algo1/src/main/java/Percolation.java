import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by fkruege on 12/26/2016.
 */
public class Percolation {

    private static final String ROW = "Row";
    private static final String COLUMN = "Column";
    private int _gridSize;
    private int _virtualTop;
    private int _virtualBottom;
    private boolean[] _openSites;
    private final NeighborHelper _neighborHelper;
    private final WeightedQuickUnionUF _union;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be greater than 0");
        }

        _gridSize = n;
        _neighborHelper = new NeighborHelper(_gridSize);

        // add 2 for the virtual top and botoom
        int totalUnionSize = (n * n) + 2;
        _virtualTop = totalUnionSize - 2;
        _virtualBottom = totalUnionSize - 1;
        _union = new WeightedQuickUnionUF(totalUnionSize);

        // create an array of open sites and mark
        // the virtual top and bottom as open
        _openSites = new boolean[totalUnionSize];
        _openSites[_virtualTop] = true;
        _openSites[_virtualBottom] = true;

    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRowCol(row, col);

        int index = _neighborHelper.translateRowColIntoIndex(row, col);

        // mark this new index as an open site
        _openSites[index] = true;

        if (_neighborHelper.isTopRow(row)) {
            _union.union(_virtualTop, index);
        }

        if (_neighborHelper.isBottomRow(row)) {
            _union.union(_virtualBottom, index);
        }

        NeighborsIndex neighbors = _neighborHelper.getNeighbors(row, col);
        unionNeighbor(index, neighbors.getLeftNeighbor());
        unionNeighbor(index, neighbors.getTopNeighbor());
        unionNeighbor(index, neighbors.getRightNeighbor());
        unionNeighbor(index, neighbors.getBottomNeighbor());
    }

    private void unionNeighbor(int home, int neighbor) {

        // verify that the neighbor index is valid
        if (neighbor == NeighborsIndex.NO_NEIGHBOR) {
            return;
        }

        // verify that the neighbor is actually open
        if (!_openSites[neighbor]) {
            return;
        }

        _union.union(home, neighbor);
    }


    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRowCol(row, col);

        int index = _neighborHelper.translateRowColIntoIndex(row, col);
        return _openSites[index];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRowCol(row, col);

        int index = _neighborHelper.translateRowColIntoIndex(row, col);
        return _union.connected(_virtualTop, index);
    }

    // does the system percolate?
    public boolean percolates() {
        return _union.connected(_virtualBottom, _virtualTop);
    }

    private void validateRowCol(int row, int col) {
        checkParameter(row, ROW);
        checkParameter(col, COLUMN);
    }

    private void checkParameter(int parameter, String parameterName) {
        if (parameter <= 0 || parameter > _gridSize) {
            throw new IndexOutOfBoundsException(parameterName + " is out of bounds");
        }
    }

    public static class NeighborHelper {

        public static final int UNKNOWN_INDEX = -1;

        private int _gridSize;
        private int _maxSize;

        public NeighborHelper(int gridSize) {
            _gridSize = gridSize;
            _maxSize = _gridSize * gridSize;
        }

        public NeighborsIndex getNeighbors(int row, int col) {

            NeighborsIndex neighbors = new NeighborsIndex();

            neighbors.setTopNeighbor(translateRowColIntoIndex(row - 1, col));
            neighbors.setBottomNeighbor(translateRowColIntoIndex(row + 1, col));
            neighbors.setLeftNeighbor(translateRowColIntoIndex(row, col - 1));
            neighbors.setRightNeighbor(translateRowColIntoIndex(row, col + 1));

            return neighbors;
        }


        public int translateRowColIntoIndex(int row, int col) {

            // check to see that the row is within bounds
            if (row <= 0 || row > _gridSize) {
                return UNKNOWN_INDEX;
            }

            if (col <= 0 || col > _gridSize) {
                return UNKNOWN_INDEX;
            }

            row--;
            col--;

            int index = (row * _gridSize) + (col);

            return index;
        }

        public boolean isTopRow(int row) {
            return (row == 1);
        }

        public boolean isBottomRow(int row) {
            return (row == _gridSize);
        }

    }

    public static class NeighborsIndex {
        public static final int NO_NEIGHBOR = -1;

        private int _leftNeighbor = NO_NEIGHBOR;
        private int _rightNeighbor = NO_NEIGHBOR;
        private int _topNeighbor = NO_NEIGHBOR;
        private int _bottomNeighbor = NO_NEIGHBOR;

        public NeighborsIndex() {

        }

        public int getLeftNeighbor() {
            return _leftNeighbor;
        }

        public int getRightNeighbor() {
            return _rightNeighbor;
        }

        public int getTopNeighbor() {
            return _topNeighbor;
        }

        public int getBottomNeighbor() {
            return _bottomNeighbor;
        }

        public void setLeftNeighbor(int _leftNeighbor) {
            this._leftNeighbor = _leftNeighbor;
        }

        public void setRightNeighbor(int _rightNeighbor) {
            this._rightNeighbor = _rightNeighbor;
        }

        public void setTopNeighbor(int _topNeighbor) {
            this._topNeighbor = _topNeighbor;
        }

        public void setBottomNeighbor(int _bottomNeighbor) {
            this._bottomNeighbor = _bottomNeighbor;
        }
    }


}
