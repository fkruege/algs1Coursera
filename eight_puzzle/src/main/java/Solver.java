import edu.princeton.cs.algs4.MinPQ;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Comparator;

/**
 * Created by fkruege on 1/16/2017.
 */
public class Solver {
    private Board _initialBoard;
    private MinPQ<SearchNode> _minPQ;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        _initialBoard = initial;
        _minPQ = new MinPQ<SearchNode>(new ManhattanSearchNodeComparator());
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
        throw new NotImplementedException();
    }



    // solve a slider puzzle (given below)
    public static void main(String[] args) {
    }

    private static class SearchNode{

       Board PuzzleBoard = null;
       int Moves = 0;
       SearchNode PreviousSearchNode = null;

       public SearchNode(Board board, int moves, SearchNode previousNode){
           PuzzleBoard = board;
           Moves = moves;
           PreviousSearchNode = previousNode;
       }

    }

    private static class ManhattanSearchNodeComparator implements Comparator<SearchNode>{

        public int compare(SearchNode node1, SearchNode node2) {
            int priority1 = node1.PuzzleBoard.manhattan() + node1.Moves;
            int priority2 = node2.PuzzleBoard.manhattan() + node2.Moves;

            return Integer.compare(priority1, priority2);
        }

    }
}
