import java.util.Arrays;

/**
 * Created by fkruege on 1/10/2017.
 */
public class BruteCollinearPoints {

    private NodeList<LineSegment> _segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        verifyPointsArray(points);
        _segments = new NodeList<LineSegment>();
        Point[] copyPoints = copyPoints(points);

        findColinnearPoints2(copyPoints);
    }


    private Point[] copyPoints(Point[] points){
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
        LineSegment[] copySegments = new LineSegment[_segments.getCount()];
        _segments.populateArray(copySegments);
        return copySegments;
    }

    private void findColinnearPoints2( Point[] points) {

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            boolean segmentFound = false;
            for (int j = i + 1; j < points.length && !segmentFound; j++) {
                Point p = points[i];
                Point q = points[j];
                double p_q_slope = p.slopeTo(q);
                for (int k = j + 1; k < points.length; k++) {
                    Point r = points[k];
                    double p_r_slope = p.slopeTo(r);

                    if (Double.compare(p_q_slope, p_r_slope) == 0) {

                        for (int l = k + 1; l < points.length && !segmentFound; l++) {
                            Point s = points[l];
                            double p_s_slope = p.slopeTo(s);

                            if (Double.compare(p_r_slope, p_s_slope) == 0) {
                                _segments.add(  new LineSegment(p, s));
                            }
                        }
                    }
                }
            }
        }

    }


    private void verifyPointsArray(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        for (Point p :
                points) {
            if (p == null) {
                throw new NullPointerException();
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
