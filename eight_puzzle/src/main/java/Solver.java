import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.Console;
import java.util.Comparator;

/**
 * Created by fkruege on 1/16/2017.
 */
public class Solver {
    private Board _initialBoard;
    private SearchNode _solutionNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        _initialBoard = initial;
        _solutionNode = null;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        throw new NotImplementedException();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        throw new NotImplementedException();
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        solveProblem();

        if (_solutionNode == null) {
            return null;
        }

        // iterate through the SearchNode history and return the steps to get to the solution
        Stack<Board> stepsToSolution = new Stack<Board>();
        SearchNode iterator = _solutionNode;
        while (iterator != null) {
            stepsToSolution.push(iterator.PuzzleBoard);
            iterator = iterator.PreviousSearchNode;
        }

        return stepsToSolution;
    }

    private void solveProblem() {

        MinPQ<SearchNode> _minPQ = new MinPQ<SearchNode>(new ManhattanSearchNodeComparator());

        // insert the current board first
        SearchNode firstNode = new SearchNode(_initialBoard, 0, null);
        _minPQ.insert(firstNode);

        boolean isSolved = false;

        while (!isSolved && !_minPQ.isEmpty()) {

            SearchNode minNode = _minPQ.delMin();
            if (isBoardSolved(minNode.PuzzleBoard)) {
                _solutionNode = minNode;
                isSolved = true;
                continue;
            }
            Iterable<Board> neighbors = minNode.PuzzleBoard.neighbors();
            for (Board neighbor : neighbors) {
                SearchNode possibleStep = new SearchNode(neighbor, minNode.Moves + 1, minNode);
                _minPQ.insert(possibleStep);
                System.out.println(neighbor.toString());
            }
        }
    }

    private boolean isBoardSolved(Board board) {
        return board.hamming() == 0;
    }


    private static class SearchNode {

        Board PuzzleBoard = null;
        int Moves = 0;
        SearchNode PreviousSearchNode = null;

        public SearchNode(Board board, int moves, SearchNode previousNode) {
            PuzzleBoard = board;
            Moves = moves;
            PreviousSearchNode = previousNode;
        }

    }

    private static class ManhattanSearchNodeComparator implements Comparator<SearchNode> {

        public int compare(SearchNode node1, SearchNode node2) {
            int priority1 = node1.PuzzleBoard.manhattan() + node1.Moves;
            int priority2 = node2.PuzzleBoard.manhattan() + node2.Moves;

            return Integer.compare(priority1, priority2);
        }

    }


    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }

}
