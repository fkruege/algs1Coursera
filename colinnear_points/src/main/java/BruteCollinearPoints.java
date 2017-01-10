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

        findCollinearPoints();
    }


    // the number of line segments
    public int numberOfSegments() {
        return _segmentIndex;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] copySegments = new LineSegment[_segmentIndex];
        for(int i = 0; i < _segmentIndex; i++){
            copySegments[i] = _segments[i];
        }
        return copySegments;
    }

    private void findCollinearPoints() {

        for (int i = 0; i < _points.length - 3; i++) {

            Point p = _points[i];
            Point q = _points[i + 1];
            Point r = _points[i + 2];
            Point s = _points[i + 3];

            double p_q_slope = p.slopeTo(q);
            if (p_q_slope == Point.SLOPE_SAME_POINT) {
                continue;
            }

            double p_r_slope = p.slopeTo(r);
            if (p_r_slope == Point.SLOPE_SAME_POINT) {
                continue;
            }

            // verify the p->q and p->r have the same slope
            if (Double.compare(p_q_slope, p_r_slope) != 0) {
                continue;
            }

            double p_s_slope = p.slopeTo(s);
            if (p_s_slope == Point.SLOPE_SAME_POINT) {
                continue;
            }

            if (Double.compare(p_q_slope, p_s_slope) != 0) {
                continue;
            }

            // if we get here then these 4 points are colinnear
            if (_segmentIndex < SEGMENT_SIZE) {
                _segments[_segmentIndex++] = new LineSegment(p, s);
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
