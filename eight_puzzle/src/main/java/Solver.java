import edu.princeton.cs.algs4.LinearProbingHashST;
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
        Board twin = _initialBoard.twin();

        SolveBoard solveInitialBoard = new SolveBoard(_initialBoard);
        SolveBoard solveTwinBoard = new SolveBoard(twin);

        while (!solveInitialBoard.IsBoardSolved() && !solveTwinBoard.IsBoardSolved()) {
            solveInitialBoard.makeAMove();
            solveTwinBoard.makeAMove();
        }

        return solveInitialBoard.IsBoardSolved();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (_solutionNode == null) {
            solveProblem();
        }

        return _solutionNode.Moves;
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

        SolveBoard solveBoard = new SolveBoard(_initialBoard);
        while (!solveBoard.IsBoardSolved()) {
            solveBoard.makeAMove();
        }

        _solutionNode = solveBoard.SolutionNode();

    }


    private static class SolveBoard {

        private boolean _isSolved = false;
        private SearchNode _SolutionNode = null;
        private Board _board;

        public SolveBoard(Board board) {
            _board = board;
        }

        public boolean IsBoardSolved() {
            return _isSolved;
        }

        public SearchNode SolutionNode() {
            return _SolutionNode;
        }

        public void makeAMove() {

            MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>(new ManhattanSearchNodeComparator());

            // insert the current board first
            SearchNode firstNode = new SearchNode(_board, 0, null);
            minPQ.insert(firstNode);

            while (!_isSolved && !minPQ.isEmpty()) {

                SearchNode minNode = minPQ.delMin();

                int priority = minNode.Moves + minNode.PuzzleBoard.manhattan();
//                System.out.println("Min Node Dequeued.  Priority: " + priority + "    Manhattan: " + minNode.PuzzleBoard.manhattan() + "  Hamming:" + minNode.PuzzleBoard.hamming());
//                System.out.println(minNode.PuzzleBoard.toString());

                if (minNode.PuzzleBoard.isGoal()) {
                    _SolutionNode = minNode;
                    _isSolved = true;
                    continue;
                }
                Iterable<Board> neighbors = minNode.PuzzleBoard.neighbors();
                int index = 0;
                for (Board neighbor : neighbors) {
                    index++;
//                    System.out.println("Inserting Neighbor " + index + ":   Manhattan: " + neighbor.manhattan() + "  Hamming:" + neighbor.hamming());
//                    System.out.println(neighbor.toString());

                    if (!IsBoardSameAsPrevious(neighbor, minNode.PreviousSearchNode)) {

                        SearchNode possibleStep = new SearchNode(neighbor, minNode.Moves + 1, minNode);
                        minPQ.insert(possibleStep);
                    }

                }
            }
        }


        private boolean IsBoardSameAsPrevious(Board current, SearchNode previous) {
            SearchNode iterator = previous;
            while (iterator != null) {
                if (iterator.PuzzleBoard == null) {
                    return false;
                }
                if (iterator.PuzzleBoard.equals(current)) {
                    return true;
                }
                iterator = iterator.PreviousSearchNode;
            }

            return false;

        }

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
            int manhattan1 = node1.PuzzleBoard.manhattan();
            int manhattan2 = node2.PuzzleBoard.manhattan();


            int priority1 = manhattan1 + node1.Moves;
            int priority2 = manhattan2 + node2.Moves;

            if (priority1 > priority2) {
                return 1;
            } else if (priority1 < priority2) {
                return -1;
            }

            if(manhattan1 > manhattan2){
                return 1;
            }else if(manhattan1 < manhattan2){
                return -1;
            }

            int hamming1 = node1.PuzzleBoard.hamming();
            int hamming2 = node2.PuzzleBoard.hamming();

            if(hamming1 > hamming2){
                return 1;
            }else if(hamming1 < hamming2){
                return -1;
            }

            return 0;
//
//            int compare = Integer.compare(priority1, priority2);
//
//            if (compare == 0) {
//                return Integer.compare(node1.PuzzleBoard.manhattan(), node2.PuzzleBoard.manhattan());
//            } else {
//                return compare;
//            }
        }

    }


    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }

}
