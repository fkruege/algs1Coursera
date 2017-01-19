import edu.princeton.cs.algs4.Stack;

/**
 * Created by fkruege on 1/16/2017.
 */
public class Board {

    private static final int EMPTY = 0;

    private int[][] _blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        _blocks = blocks;
    }

    // board dimension n
    public int dimension() {
        return _blocks.length;
    }


    // number of blocks out of place
    public int hamming() {
        int hamming = 0;

        for (int i = 0; i < _blocks.length; i++) {
            for (int j = 0; j < _blocks.length; j++) {
                int expectedValue = (i * _blocks.length) + j + 1;
                int currentValue = _blocks[i][j];
                if (currentValue != EMPTY && currentValue != expectedValue) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;

        for (int i = 0; i < _blocks.length; i++) {
            for (int j = 0; j < _blocks.length; j++) {
                int value = _blocks[i][j];
                if (value == EMPTY) {
                    continue;
                }
                int expectedJ = getExpectedJ(value);
                int expectedI = (value - expectedJ - 1) / _blocks.length;

                int distance = Math.abs(expectedI - i) + Math.abs(expectedJ - j);
                manhattan += distance;
            }
        }

        return manhattan;
    }


    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int[][] copyBlocks = copyBlocks();
        boolean block1Found = false;
        int i1 = -1;
        int j1 = -1;

        boolean block2Found = false;
        int i2 = -1;
        int j2 = -1;

        for (int i = 0; i < copyBlocks.length; i++) {
            for (int j = 0; j < copyBlocks.length; j++) {
                int value = copyBlocks[i][j];
                if (value == EMPTY) {
                    continue;
                }

                if (!block1Found) {
                    i1 = i;
                    j1 = j;
                    block1Found = true;
                    continue;
                }

                if (!block2Found) {
                    i2 = i;
                    j2 = j;
                    block2Found = true;
                    break;
                }
            }

            if (block1Found && block2Found) {
                break;
            }
        }
        swap(copyBlocks, i1, j1, i2, j2);

        return new Board(copyBlocks);
    }


    // does this board equal y?
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Board that = (Board) other;
        return AreBlocksSame(that);
    }

    private boolean AreBlocksSame(Board other) {
        int dimen = _blocks.length;

        if (dimen != other._blocks.length) {
            return false;
        }

        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                if (_blocks[i][j] != other._blocks[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * @return an integer hash code for this Board
     */
    @Override
    public int hashCode() {
        int dimen = _blocks.length;
        int hash = 17;
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                hash = 31 * hash + _blocks[i][j];
            }
        }
        return hash;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Coordinates emptyBlock = findEmptyBlock();

        Stack<Board> neighboringBoards = new Stack<Board>();


        int limit = _blocks.length;
        // try swapping up
        if (emptyBlock.i - 1 >= 0) {
            int[][] copyBlocks = copyBlocks();
            swap(copyBlocks, emptyBlock.i, emptyBlock.j, emptyBlock.i - 1, emptyBlock.j);
            neighboringBoards.push(new Board(copyBlocks));
        }

        // try swapping down
        if (emptyBlock.i + 1 < limit) {
            int[][] copyBlocks = copyBlocks();
            swap(copyBlocks, emptyBlock.i, emptyBlock.j, emptyBlock.i + 1, emptyBlock.j);
            neighboringBoards.push(new Board(copyBlocks));
        }

        // try swapping left
        if (emptyBlock.j - 1 >= 0) {
            int[][] copyBlocks = copyBlocks();
            swap(copyBlocks, emptyBlock.i, emptyBlock.j, emptyBlock.i, emptyBlock.j - 1);
            neighboringBoards.push(new Board(copyBlocks));

        }

        // try swapping right
        if (emptyBlock.j + 1 < limit) {
            int[][] copyBlocks = copyBlocks();
            swap(copyBlocks, emptyBlock.i, emptyBlock.j, emptyBlock.i, emptyBlock.j + 1);
            neighboringBoards.push(new Board(copyBlocks));
        }

        return neighboringBoards;
    }


    // string representation of this board (in the output format specified below)
    public String toString() {
        int len = _blocks.length;
        StringBuilder s = new StringBuilder();
        s.append(len + "\n");
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                s.append(String.format("%2d ", _blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int getExpectedJ(int value) {
        int modulus = value % _blocks.length;
        if (modulus == 0) {
            // last column
            return _blocks.length - 1;
        } else {
            return modulus - 1;
        }
    }

    private int[][] copyBlocks() {
        int dimen = _blocks.length;
        int[][] newBlocks = new int[dimen][dimen];
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                newBlocks[i][j] = _blocks[i][j];
            }
        }

        return newBlocks;
    }

    private void swap(int[][] blocks, int i1, int j1, int i2, int j2) {
        int save = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = save;
    }

    private Coordinates findEmptyBlock() {
        Coordinates coordinates = new Coordinates();
        int dimen = _blocks.length;
        for (int i = 0; i < dimen; i++) {
            for (int j = 0; j < dimen; j++) {
                if (_blocks[i][j] == EMPTY) {
                    coordinates.i = i;
                    coordinates.j = j;
                    return coordinates;
                }
            }
        }

        return coordinates;

    }

    private static class Coordinates {
        public int i = -1;
        public int j = -1;
    }

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
