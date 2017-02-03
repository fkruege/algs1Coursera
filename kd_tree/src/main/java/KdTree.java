
import edu.princeton.cs.algs4.DoublingRatio;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.NoSuchElementException;

public class KdTree<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST
    private int _size = 0;

    private static class Boundaries {

        double MinX, MinY;
        double MaxX, MaxY;

        public Boundaries() {
            MinX = 0;
            MinY = 0;
            MaxX = 1;
            MaxY = 1;
        }

    }

    private static class Node {
        private Point2D point;    // sorted by key
        private RectHV rect;      // the axis-aligned rectangle corresponding to this node
        private Node left, right; // left and right subtrees

        public Node(Point2D point, boolean xAxis, Boundaries boundaries) {
            this.point = point;

            if (xAxis) {
                rect = new RectHV(point.x(), boundaries.MinY,
                        point.x(), boundaries.MaxY);
            } else {
                rect = new RectHV(boundaries.MinX, point.y(),
                        boundaries.MaxX, point.y());
            }
        }


    }

    /**
     * Initializes an empty symbol table.
     */
    public KdTree() {
        _size = 0;
        root = null;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return _size;
    }

    /**
     * Does this symbol table contain the given key?
     *
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     * {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Point2D key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     * and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    private Point2D get(Point2D key) {
        return get(root, key, true);
    }

    private Point2D get(Node x, Point2D key, boolean xAxis) {
        if (x == null) return null;
        int cmp = compare(key, x.point, xAxis);
        if (cmp < 0) return get(x.left, key, !xAxis);
        else if (cmp > 0) return get(x.right, key, !xAxis);
        else return x.point;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */


    public void insert(Point2D point) {
        if (point == null) throw new IllegalArgumentException("first argument to put() is null");
//        if (val == null) {
//            delete(key);
//            return;
//        }
        root = put(root, point, true, new Boundaries());
    }

    private Node put(Node x, Point2D point, boolean xAxis, Boundaries boundaries) {
        if (x == null) {
            _size++;
            return new Node(point, xAxis, boundaries);
        }

        int cmp = compare(point, x.point, xAxis);

        if (cmp < 0) {
            boundaries.MaxX = x.point.x();
            boundaries.MaxY = x.point.y();
            x.left = put(x.left, point, !xAxis, boundaries);
        } else if (cmp > 0) {
            boundaries.MinX = x.point.x();
            boundaries.MinY = x.point.y();
            x.right = put(x.right, point, !xAxis, boundaries);
        } else {
            x.point = point;
        }

        return x;
    }

    private int compare(Point2D point1, Point2D point2, boolean xAxis) {

        int cmp = 0;

        if (xAxis) {
            cmp = Double.compare(point1.x(), point2.x());
        } else {
            cmp = Double.compare(point1.y(), point2.y());
        }

        return cmp;
    }


    /**
     * Unit tests the {@code BST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

    }
}

