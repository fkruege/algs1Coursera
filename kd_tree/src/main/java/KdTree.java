
import edu.princeton.cs.algs4.*;

import java.util.NoSuchElementException;

public class KdTree {
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
        else {
            if (key.equals(x.point)) {
                return x.point;
            }
            return get(x.right, key, !xAxis);
        }
//        else return x.point;
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


    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new NullPointerException();
        }

        Point2D bottomLeft = new Point2D(rect.xmin(), rect.ymin());
        Point2D topRight = new Point2D(rect.xmax(), rect.ymax());

        Stack<Point2D> pointsInRect = new Stack<Point2D>();

        findPointsInRect(root, bottomLeft, topRight, true, pointsInRect);

        return pointsInRect;
    }


    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {

        if (p == null) {
            throw new NullPointerException();
        }

        if (_size <= 0) {
            return null;
        }

        Point2D maxPoint = new Point2D(Double.MAX_VALUE, Double.MAX_VALUE);
        RectHV searchRectangle = new RectHV(0.0, 0.0, 1.0, 1.0);

        return findNearest(p, maxPoint, root, searchRectangle, true);

//        return findNearest(root, p, Double.MAX_VALUE, true);
    }

    private Point2D findNearest(Point2D goalPoint, Point2D currentNearest, Node node,
                                RectHV searchRectangle, boolean xAxis) {
        if (node == null) {
            return currentNearest;
        }

        if(!searchRectangle.contains(goalPoint)){
           return currentNearest;
        }

        Point2D nodePoint = node.point;
        double currentNearestDistance = currentNearest.distanceSquaredTo(goalPoint);
        double nodePointDistance = nodePoint.distanceSquaredTo(goalPoint);

        if (nodePointDistance < currentNearestDistance) {
            currentNearestDistance = nodePointDistance;
            currentNearest = nodePoint;
        }


        Point2D leftNearest = null;
        Point2D rightNearest = null;
        leftNearest = findNearest(goalPoint, currentNearest, node.left, !xAxis);

        // check to see if the right subtree needs to be traversed
//        if (node.right != null) {
//            if()
//            double rightDistance = node.right.point.distanceSquaredTo(goalPoint);
//            if (rightDistance < currentNearestDistance) {
                rightNearest = findNearest(goalPoint, currentNearest, node.right, !xAxis);
//            }
//        }

        double leftNearestDistance = Double.MAX_VALUE;
        double rightNearestDistance = Double.MAX_VALUE;

        if (leftNearest != null) {
            leftNearestDistance = leftNearest.distanceSquaredTo(goalPoint);
        }

        if (rightNearest != null) {
            rightNearestDistance = rightNearest.distanceSquaredTo(goalPoint);
        }

        if (leftNearestDistance < currentNearestDistance && leftNearestDistance < rightNearestDistance) {
            currentNearest = leftNearest;
        } else if (rightNearestDistance < currentNearestDistance && rightNearestDistance < leftNearestDistance) {
            currentNearest = rightNearest;
        }

        return currentNearest;
    }

    private void findPointsInRect(Node node, Point2D bottomLeft, Point2D topRight, boolean xAxis, Stack<Point2D> pointsInRect) {

        if (node == null) {
            return;
        }

        Point2D nodePoint = node.point;

        // check that the X coordinate of the point is between the rectangle bounds
        if (Double.compare(nodePoint.x(), bottomLeft.x()) >= 0
                &&
                Double.compare(nodePoint.x(), topRight.x()) <= 0
                &&
                Double.compare(nodePoint.y(), bottomLeft.y()) >= 0
                &&
                Double.compare(nodePoint.y(), topRight.y()) <= 0
                ) {

            pointsInRect.push(nodePoint);
        }

        if (compare(nodePoint, bottomLeft, xAxis) >= 0 && compare(nodePoint, topRight, xAxis) <= 0) {
            findPointsInRect(node.left, bottomLeft, topRight, !xAxis, pointsInRect);
            findPointsInRect(node.right, bottomLeft, topRight, !xAxis, pointsInRect);
        } else if (compare(nodePoint, bottomLeft, xAxis) > 0 && compare(nodePoint, topRight, xAxis) > 0) {
            findPointsInRect(node.left, bottomLeft, topRight, !xAxis, pointsInRect);
        } else {
            findPointsInRect(node.right, bottomLeft, topRight, !xAxis, pointsInRect);
        }


    }


    private Node put(Node x, Point2D point, boolean xAxis, Boundaries boundaries) {
        if (x == null) {
            _size++;
            return new Node(point, xAxis, boundaries);
        }


        int cmp = compare(point, x.point, xAxis);

        if (cmp < 0) {
            boundaries.MaxX = x.rect.xmax();
            boundaries.MaxY = x.rect.ymax();
            x.left = put(x.left, point, !xAxis, boundaries);
        } else if (cmp >= 0) {
            if (!point.equals(x.point)) {
                boundaries.MinX = x.rect.xmin();
                boundaries.MinY = x.rect.ymin();
                x.right = put(x.right, point, !xAxis, boundaries);
            }

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


    // draw all points to standard draw
    public void draw() {
        Iterable<Node> nodePoints = levelOrder();

        for (Node node :
                nodePoints) {
            StdDraw.point(node.point.x(), node.point.y());
            StdDraw.line(
                    node.rect.xmin(),
                    node.rect.ymin(),
                    node.rect.xmax(),
                    node.rect.ymax()
            );

        }
    }


    /**
     * Returns the keys in the BST in level order (for debugging).
     *
     * @return the keys in the BST in level order traversal
     */
    private Iterable<Node> levelOrder() {
        Queue<Node> keys = new Queue<Node>();
        Queue<Node> queue = new Queue<Node>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null) continue;
            keys.enqueue(x);
            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }


    /**
     * Unit tests the {@code BST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

    }
}

