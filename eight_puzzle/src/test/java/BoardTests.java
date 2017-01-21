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

        Board boardGoal = new Board(_input);

        Assert.assertTrue(boardGoal.isGoal());

    }

    @Test
    public void test_twin() {
        Board twin = _board.twin();
        String expected = "3" + "\n" +
                " 1  8  3 " + "\n" +
                " 4  0  2 " + "\n" +
                " 7  6  5 " + "\n ";
        String actual = twin.toString().trim();

        Assert.assertEquals(expected.trim(), actual);
    }

    @Test
    public void test_EqualsHashCoce() {
        Board boardA = new Board(_input);
        Board boardB = new Board(_input);

        Assert.assertEquals(boardA, boardB);
        Assert.assertEquals(boardA.toString().hashCode(), boardB.toString().hashCode());

        Board boardC = boardA.twin();
        Assert.assertNotEquals(boardA, boardC);
        Assert.assertNotEquals(boardA.toString().hashCode(), boardC.toString().hashCode());
    }

    @Test
    public void test_neighbors() {
        Iterable<Board> neighbors = _board.neighbors();
        int index = 0;
        for (Board board :
                neighbors) {

            if (index == 0) {
                String expected = "3\n" +
                        " 8  1  3 \n" +
                        " 4  2  0 \n" +
                        " 7  6  5";
                Assert.assertEquals(expected.trim(), board.toString().trim());
                index++;
            }

            else if (index == 1) {
                String expected = "3\n" +
                        " 8  1  3 \n" +
                        " 0  4  2 \n" +
                        " 7  6  5";
                Assert.assertEquals(expected.trim(), board.toString().trim());
                index++;
            }

            else if (index == 2) {
                String expected = "3\n" +
                        " 8  1  3 \n" +
                        " 4  6  2 \n" +
                        " 7  0  5";
                Assert.assertEquals(expected.trim(), board.toString().trim());
                index++;
            }else{
                 String expected = "3\n" +
                        " 8  0  3 \n" +
                        " 4  1  2 \n" +
                        " 7  6  5";
                Assert.assertEquals(expected.trim(), board.toString().trim());
                index++;
            }

        }

        Assert.assertEquals(4, index);

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
