/**
 * Created by fkruege on 12/26/2016.
 */
//public class NeighborHelper {
//
//    public static final int UNKNOWN_INDEX = -1;
//
//    private int _gridSize;
//    private int _maxSize;
//
//    public NeighborHelper(int gridSize) {
//        _gridSize = gridSize;
//        _maxSize = _gridSize * gridSize;
//    }
//
//    public NeighborsIndex getNeighbors(int row, int col) {
//
//        NeighborsIndex neighbors = new NeighborsIndex();
//
//        neighbors.setTopNeighbor(translateRowColIntoIndex(row - 1, col));
//        neighbors.setBottomNeighbor(translateRowColIntoIndex(row + 1, col));
//        neighbors.setLeftNeighbor(translateRowColIntoIndex(row, col - 1));
//        neighbors.setRightNeighbor(translateRowColIntoIndex(row, col + 1));
//
//        return neighbors;
//    }
//
//
//    public int translateRowColIntoIndex(int row, int col) {
//
//        // check to see that the row is within bounds
//        if(row <= 0 || row > _gridSize){
//            return UNKNOWN_INDEX;
//        }
//
//        if(col <= 0 || col > _gridSize){
//            return UNKNOWN_INDEX;
//        }
//
//        row--;
//        col--;
//
//        int index = (row * _gridSize) + (col);
//
//        return index;
//    }
//
//    public boolean isTopRow(int row) {
//        return (row == 1);
//    }
//
//    public boolean isBottomRow(int row) {
//        return (row == _gridSize);
//    }
//
//}
