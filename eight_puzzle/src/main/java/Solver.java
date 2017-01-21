import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.HashMap;

/**
 * Created by fkruege on 1/16/2017.
 */
public class Solver {
    private Board _initialBoard;
    private SearchNode _solutionNode;
    private boolean _isBoardSolveable = true;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {

        if (initial == null) {
            throw new NullPointerException();
        }

        _initialBoard = initial;
        _solutionNode = null;
        _isBoardSolveable = true;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        Board twin = _initialBoard.twin();

        SolveBoard solveInitialBoard = new SolveBoard(_initialBoard);
        SolveBoard solveTwinBoard = new SolveBoard(twin);

        while (solveInitialBoard.nextStep() && solveTwinBoard.nextStep()) {
            solveInitialBoard.makeAMove();
            solveTwinBoard.makeAMove();
        }

        if (solveInitialBoard.IsBoardSolved()) {
            _solutionNode = solveInitialBoard.SolutionNode();
            return true;
        }

        _isBoardSolveable = false;
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {

        if (solveProblem()) {
            return _solutionNode.Moves;
        } else {
            return -1;
        }

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        if (solveProblem()) {
            return getIterableBoard(_solutionNode);
        } else {
            return null;
        }


    }


    private Iterable<Board> getIterableBoard(SearchNode solutionNode) {
        // iterate through the SearchNode _history and return the steps to get to the solution
        Stack<Board> stepsToSolution = new Stack<Board>();
        SearchNode iterator = solutionNode;
        while (iterator != null) {
            stepsToSolution.push(iterator.PuzzleBoard);
            iterator = iterator.PreviousSearchNode;
        }

        return stepsToSolution;
    }

    private boolean solveProblem() {

        // problem already solved
        if (_solutionNode != null) {
            return true;
        }

        // check if board is unsolveable
        if (!_isBoardSolveable) {
            return false;
        }

        // if we get here this is the first time trying to solve the board
        SolveBoard solveBoard = new SolveBoard(_initialBoard);
        while (solveBoard.nextStep()) {
            solveBoard.makeAMove();
        }

        if (solveBoard.IsBoardSolved()) {
            _solutionNode = solveBoard.SolutionNode();
            return true;
        } else {
            _isBoardSolveable = false;
            return false;
        }

    }


    private static class SolveBoard {

        private boolean _isSolved = false;
        private boolean _isBoardSolveable = true;
        private SearchNode _SolutionNode = null;
        private Board _board;

        MinPQ<SearchNode> _minPQ;
        HashMap<Integer, String> _history;
        long _delMinCount = 0;

        public SolveBoard(Board board) {
            _board = board;
            _minPQ = new MinPQ<SearchNode>(new ManhattanSearchNodeComparator());
            _history = new HashMap<Integer, String>();
            makeInitialMove();
        }

        private void makeInitialMove() {
            SearchNode firstNode = new SearchNode(_board, 0, null);
            _minPQ.insert(firstNode);
        }

        public boolean nextStep() {
            if (_isSolved) {
                return false;
            }

            if (!_isBoardSolveable) {
                return false;
            }

            return true;


        }

        public boolean IsBoardSolved() {
            return _isSolved;
        }

        public boolean IsBoardSolveable() {
            return _isBoardSolveable;
        }

        public SearchNode SolutionNode() {
            return _SolutionNode;
        }

        public void makeAMove() {

            if (_isSolved) {
                return;
            }

            if (!_isSolved && _minPQ.isEmpty()) {
                _isBoardSolveable = false;
                return;
            }

            SearchNode minNode = _minPQ.delMin();
            _delMinCount++;
//            if (_delMinCount % 1000 == 0) {
//                int priority = minNode.Moves + minNode.PuzzleBoard.manhattan();
//                System.out.println("Delmin: " + _delMinCount + " Min Node.  Prior: " + priority
//                        + "    Manhat: " + minNode.PuzzleBoard.manhattan()
//                        + "  Hamm:" + minNode.PuzzleBoard.hamming()
//                        + " minPq: " + _minPQ.size() + " map: " + _history.size()
//                );
//            }

//                System.out.println(minNode.PuzzleBoard.toString());

            if (minNode.PuzzleBoard.isGoal()) {
                _SolutionNode = minNode;
                _isSolved = true;
                return;
            }

            Iterable<Board> neighbors = minNode.PuzzleBoard.neighbors();
            for (Board neighbor : neighbors) {

                Integer key = new Integer(neighbor.toString().hashCode());

                if (!_history.containsKey(key)) {
                    _history.put(key, neighbor.toString());

                    SearchNode possibleStep = new SearchNode(neighbor, minNode.Moves + 1, minNode);
                    _minPQ.insert(possibleStep);

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

            if (manhattan1 > manhattan2) {
                return 1;
            } else if (manhattan1 < manhattan2) {
                return -1;
            }

            int hamming1 = node1.PuzzleBoard.hamming();
            int hamming2 = node2.PuzzleBoard.hamming();

            if (hamming1 > hamming2) {
                return 1;
            } else if (hamming1 < hamming2) {
                return -1;
            }

            return 0;
//
        }

    }


    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }

}
