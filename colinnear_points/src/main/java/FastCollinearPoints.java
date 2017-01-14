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

        findCollinearPoints(points);
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
        Point[] pointsCopy = new Point[points.length];

        for (int i = 0; i < points.length; i++) {
            pointsCopy[i] = points[i];
        }

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            // sort all the points by slope in relation to point p
            Arrays.sort(pointsCopy, p.slopeOrder());

            int matches = 0;
            double currentMatch = Double.NEGATIVE_INFINITY;
            Point endPoint = null;
            for (int j = 1; j < pointsCopy.length; j++) {
                Point q = pointsCopy[j];

                // if q is less than p skip it in order to avoid double recording points
                if (q.compareTo(p) <= 0) {
                    continue;
                }
                double slope = p.slopeTo(q);
                if (Double.compare(currentMatch, slope) == 0) {
                    matches++;
                    if (q.compareTo(endPoint) == 1) {
                        endPoint = q;
                    }
                } else {
                    // match chain has ended but check to see if the number of matches
                    // meets the minimum criteria
                    if (matches >= MIN_ADJACENT_POINTS) {
                        _segments.add(new LineSegment(p, endPoint));
                    }
                    currentMatch = slope;
                    matches = 1;
                    endPoint = q;
                }
            }

            if (matches >= MIN_ADJACENT_POINTS) {
                _segments.add(new LineSegment(p, endPoint));
            }


        }

    }
//
//    private void findCollinearPoints2(Point[] points) {
//
//
//        for (int i = 0; i < points.length; i++) {
//            Arrays.sort(points);
//            Point p = points[i];
//
//            if (i + MIN_ADJACENT_POINTS >= points.length) {
//                break;
//            }
//
//            // sort the elements to the right of p by slope in relation to point p
//            Arrays.sort(points, i + 1, points.length , p.slopeOrder());
//
//            int matches = 0;
//            double currentMatch = Double.NEGATIVE_INFINITY;
//            Point endPoint = null;
//            for (int j = i + 1; j < points.length; j++) {
//                Point q = points[j];
//                double slope = p.slopeTo(q);
//                if (Double.compare(currentMatch, slope) == 0) {
//                    matches++;
//                    endPoint = q;
//                } else {
//                    if (matches >= MIN_ADJACENT_POINTS) {
//                        break;
//                    }
//                    currentMatch = slope;
//                    matches = 1;
//                }
//            }
//
//            if (matches >= MIN_ADJACENT_POINTS) {
//                _segments[_segmentIndex++] = new LineSegment(p, endPoint);
//            }
//
//        }
//
//    }

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
