import java.util.Arrays;

/**
 * Created by fkruege on 1/11/17.
 */
public class FastCollinearPoints {


    private static final int MIN_ADJACENT_POINTS = 3;
    private NodeList<LineSegment> _segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        _segments = new NodeList<LineSegment>();

        Point[] copyPoints = copyPoints(points);

        findCollinearPoints(copyPoints);
    }

    private Point[] copyPoints(Point[] points) {
        Point[] pointsCopy = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            pointsCopy[i] = points[i];
        }

        return pointsCopy;
    }

    // the number of line segments
    public int numberOfSegments() {
        return _segments.getCount();
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] lineSegments = new LineSegment[_segments.getCount()];
        _segments.populateArray(lineSegments);
        return lineSegments;
    }

    private void findCollinearPoints(Point[] points) {

        Arrays.sort(points);
//        Point[] pointsCopy = copyPoints(points);


        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            // copy the points to preserve the natural order.
            // If I don't do this it becomes harder to determine duplicate segments
            Point[] pointsCopy = copyPoints(points);

            // sort all the points by slope in relation to point p
            Arrays.sort(pointsCopy, p.slopeOrder());

            int matches = 0;
            double currentMatch = Double.NEGATIVE_INFINITY;
            Point endPoint = null;
            double oldSlope = Double.NEGATIVE_INFINITY;
            double slope = Double.NEGATIVE_INFINITY;
            for (int j = 1; j < pointsCopy.length; j++) {
                Point q = pointsCopy[j];

                if (q.compareTo(p) <= 0) {
                    oldSlope = p.slopeTo(q);
                }

                slope = p.slopeTo(q);

                if (Double.compare(currentMatch, slope) == 0 && Double.compare(oldSlope, slope) != 0) {
                    matches++;
                    if (q.compareTo(endPoint) == 1) {
                        endPoint = q;
                    }
                } else {
                    // match chain has ended but check to see if the number of matches
                    // meets the minimum criteria
                    if (matches >= MIN_ADJACENT_POINTS) {
                        _segments.add(new LineSegment(p, endPoint));
                        oldSlope = Double.NEGATIVE_INFINITY;
                    }
                    currentMatch = slope;
                    matches = 1;
                    endPoint = q;

                }
            }

            if (matches >= MIN_ADJACENT_POINTS) {
                _segments.add(new LineSegment(p, endPoint));
                oldSlope = Double.NEGATIVE_INFINITY;
            }


        }

    }

    private static class Node<T> {
        public T Value;
        public Node<T> Next;
    }

    private static class NodeList<T> {

        private int _count = 0;
        private Node<T> _head;
        private Node<T> _current;

        public NodeList() {
            _count = 0;
            _head = null;
            _current = null;
        }

        public int getCount() {
            return _count;
        }

        public void add(T newValue) {
            _count++;

            Node<T> newNode = new Node<T>();
            newNode.Value = newValue;
            newNode.Next = null;


            if (_head == null) {
                _head = newNode;
                _current = newNode;
            } else {
                _current.Next = newNode;
                _current = newNode;
            }
        }

        public void populateArray(T[] array) {
            Node<T> iterator = _head;
            for (int i = 0; i < _count; i++) {
                array[i] = iterator.Value;
                iterator = iterator.Next;
            }
        }


    }

}
