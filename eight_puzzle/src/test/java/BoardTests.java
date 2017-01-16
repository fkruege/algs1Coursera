import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by fkruege on 1/16/2017.
 */
public class BoardTests {

    Board _board;
    int[][] _input;
    int _dimen = 3;

    @Before
    public void before() {
        _input = new int[_dimen][_dimen];
        _input[0][0] = 8;
        _input[0][1] = 1;
        _input[0][2] = 3;

        _input[1][0] = 4;
        _input[1][1] = 0;
        _input[1][2] = 2;

        _input[2][0] = 7;
        _input[2][1] = 6;
        _input[2][2] = 5;

        _board = new Board(_input);
    }

    @Test
    public void test_dimension() {
        Assert.assertEquals(3, _board.dimension());
    }

    @Test
    public void test_hamming() {
        Assert.assertEquals(5, _board.hamming());
    }

    @Test
    public void test_manhattan() {
        Assert.assertEquals(10, _board.manhattan());
    }

    @Test
    public void test_isGoal() {
        Assert.assertFalse(_board.isGoal());
        setGoalBoard();
        Assert.assertTrue(_board.isGoal());
    }

    @Test
    public void test_twin(){
        Board twin = _board.twin();
        String expected = "3" + "\n" +
                          " 1  8  3 " + "\n" +
                          " 4  0  2 " + "\n" +
                          " 7  6  5 " + "\n ";
        String actual = twin.toString().trim();

        Assert.assertEquals(expected.trim(), actual);

    }

    private void setGoalBoard() {
        _input[0][0] = 1;
        _input[0][1] = 2;
        _input[0][2] = 3;

        _input[1][0] = 4;
        _input[1][1] = 5;
        _input[1][2] = 6;

        _input[2][0] = 7;
        _input[2][1] = 8;
        _input[2][2] = 0;

    }

}
