import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

/**
 * Created by fkruege on 1/18/2017.
 */
public class SolverTests {

    @Before
    public void before() {


    }

    @Test
    public void test_AlreadySolvedBoard() {
        Board board = createSolvedBoard();
        Solver solver = new Solver(board);
        Iterable<Board> solution = solver.solution();
        Iterator<Board> iterator = solution.iterator();

        int numberOfBoards = 0;
        while (iterator.hasNext()) {
            Board next = iterator.next();
            String expected = "";
            String actual = "";

            if (numberOfBoards == 0) {
                expected = "3 1 2 3 4 5 6 7 8 0";
            }

            actual = stripSpaces(next.toString());
            Assert.assertEquals(expected.trim(), actual);
            numberOfBoards++;
        }
        Assert.assertEquals(1, numberOfBoards);
        Assert.assertEquals(0, solver.moves());
    }


    @Test
    public void test_BoardSolutionIn2Steps() {
        Board board = create2StepBoard();
        Solver solver = new Solver(board);
        Iterable<Board> solution = solver.solution();
        Iterator<Board> iterator = solution.iterator();

        int numberOfBoards = 0;
        while (iterator.hasNext()) {
            Board next = iterator.next();
            String expected = "";
            String actual = "";

            if (numberOfBoards == 0) {
                expected = "3 1 2 3 4 5 6 7 0 8";
            } else if (numberOfBoards == 1) {
                expected = "3 1 2 3 4 5 6 7 8 0";
            }

            actual = stripSpaces(next.toString());
            Assert.assertEquals(expected.trim(), actual);
            numberOfBoards++;
        }

        Assert.assertEquals(2, numberOfBoards);
        Assert.assertEquals(1, solver.moves());
    }

    @Test
    public void test_BoardSolutionIn4Steps() {
        Board board = create4StepBoard();
        Solver solver = new Solver(board);
        Iterable<Board> solution = solver.solution();
        Iterator<Board> iterator = solution.iterator();

        int numberOfBoards = 0;
        while (iterator.hasNext()) {
            Board next = iterator.next();
            String expected = "";
            String actual = "";

            if (numberOfBoards == 0) {
                expected = "3 0 1 3 4 2 5 7 8 6";
            } else if (numberOfBoards == 1) {
                expected = "3 1 0 3 4 2 5 7 8 6";
            } else if (numberOfBoards == 2) {
                expected = "3 1 2 3 4 0 5 7 8 6";
            } else if (numberOfBoards == 3) {
                expected = "3 1 2 3 4 5 0 7 8 6";
            } else if (numberOfBoards == 4) {
                expected = "3 1 2 3 4 5 6 7 8 0";
            }
            actual = stripSpaces(next.toString());
            Assert.assertEquals(expected.trim(), actual);
            numberOfBoards++;
        }

        Assert.assertEquals(5, numberOfBoards);
        Assert.assertEquals(4, solver.moves());
    }

//    @Test
//    public void test_UnsolveableBoard() {
//        Board board = createUnsolveableBoard();
//        Solver solver = new Solver(board);
//
//        Assert.assertFalse(solver.isSolvable());
//
//
////        Iterable<Board> solution = solver.solution();
////        Iterator<Board> iterator = solution.iterator();
////
////        int numberOfBoards = 0;
////        while (iterator.hasNext()) {
////            numberOfBoards++;
////        }
////        Assert.assertTrue(numberOfBoards > 0);
//    }

    private Board createUnsolveableBoard() {
        int dimen = 3;

        int[][] blocks = new int[dimen][dimen];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;

        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;

        blocks[2][0] = 8;
        blocks[2][1] = 7;
        blocks[2][2] = 0;

        Board board = new Board(blocks);
        return board;
    }


    private Board create4StepBoard() {
        int dimen = 3;

        int[][] blocks = new int[dimen][dimen];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;

        blocks[1][0] = 4;
        blocks[1][1] = 2;
        blocks[1][2] = 5;

        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 6;

        Board board = new Board(blocks);
        return board;
    }

    private Board create2StepBoard() {
        int dimen = 3;

        int[][] blocks = new int[dimen][dimen];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;

        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;

        blocks[2][0] = 7;
        blocks[2][1] = 0;
        blocks[2][2] = 8;

        Board board = new Board(blocks);
        return board;
    }


    private Board createSolvedBoard() {
        int dimen = 3;

        int[][] blocks = new int[dimen][dimen];
        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;

        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;

        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 0;

        Board board = new Board(blocks);
        return board;
    }

    private String stripSpaces(String input) {
        String stripped = input.trim().replaceAll("\n", "");
        stripped = stripped.replaceAll("  ", " ");
        return stripped;
    }

}
