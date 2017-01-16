import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    private int getExpectedJ(int value) {
        int modulus = value % _blocks.length;
        if (modulus == 0) {
            // last column
            return _blocks.length - 1;
        } else {
            return modulus - 1;
        }
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

    private void swap(int[][] blocks, int i1, int j1, int i2, int j2) {
        int save = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = save;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        throw new NotImplementedException();

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

    // unit tests (not graded)
    public static void main(String[] args) {

    }
}
