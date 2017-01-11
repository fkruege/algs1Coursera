import java.util.Arrays;

/**
 * Created by fkruege on 1/10/2017.
 */
public class BruteCollinearPoints {

    private final int SEGMENT_SIZE = 5;
    private Point[] _points;
    private LineSegment[] _segments;
    private int _segmentIndex;


    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        verifyPointsArray(points);
        _points = points;
        _segments = new LineSegment[SEGMENT_SIZE];
        _segmentIndex = 0;

        findColinnearPoints2();
    }


    // the number of line segments
    public int numberOfSegments() {
        return _segmentIndex;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copySegments = new LineSegment[_segmentIndex];
        for (int i = 0; i < _segmentIndex; i++) {
            copySegments[i] = _segments[i];
        }
        return copySegments;
    }

    private void findColinnearPoints2() {

        Arrays.sort(_points );

        for (int i = 0; i < _points.length; i++) {
            boolean segmentFound = false;
            for (int j = i + 1; j < _points.length && !segmentFound; j++) {
                Point p = _points[i];
                Point q = _points[j];
                double p_q_slope = p.slopeTo(q);
                for (int k = j + 1; k < _points.length && !segmentFound; k++) {
                    Point r = _points[k];
                    double p_r_slope = p.slopeTo(r);

                    if (Double.compare(p_q_slope, p_r_slope) == 0) {

                        for (int l = k + 1; l < _points.length && !segmentFound; l++) {
                            Point s = _points[l];
                            double p_s_slope = p.slopeTo(s);

                            if (Double.compare(p_r_slope, p_s_slope) == 0) {
                                _segments[_segmentIndex++] = new LineSegment(p, s);
                                segmentFound = true;
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
}
