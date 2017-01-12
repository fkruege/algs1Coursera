import java.util.Arrays;

/**
 * Created by fkruege on 1/11/17.
 */
public class FastCollinearPoints {


    public static final int MIN_ADJACENT_POINTS = 3;
    LineSegment[] _segments;
    int _segmentIndex = 0;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        _segments = new LineSegment[5];
        _segmentIndex = 0;

        findCollinearPoints(points);
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

    private void findCollinearPoints(Point[] points) {

        Arrays.sort(points);

        for (int i = 0; i < points.length; i++) {
            Point p = points[i];

            int copySize = (points.length - i) - 1;
            Point[] pointsCopy = new Point[copySize];

            int copyIndex = 0;
            for(int j = i +1; j < points.length; j++){
                pointsCopy[copyIndex++] = points[j];
            }

            // sort all the points by slope in relation to point p
            Arrays.sort(pointsCopy, p.slopeOrder());
            int matches = 0;
            double currentMatch = Double.NEGATIVE_INFINITY;
            Point endPoint = null;
            for(int j = 0; j < pointsCopy.length; j++){
                Point q = pointsCopy[j];
                double slope = p.slopeTo(q);
                if(Double.compare(currentMatch, slope) == 0){
                    matches++;
                    endPoint = q;
                }else{
                    if(matches >= MIN_ADJACENT_POINTS){
                        break;
                    }
                    currentMatch = slope;
                    matches = 1;
                }
            }

            if(matches >= MIN_ADJACENT_POINTS){
                _segments[_segmentIndex++] = new LineSegment(p, endPoint);
            }

        }

    }


//    private void findCollinearPoints(Point[] points) {
//
//        Arrays.sort(points);
//
//        for (int i = 0; i < points.length; i++) {
//
//            Point p = points[i];
//
//            int slopeArraySize = (points.length - i) - 1;
//            if (slopeArraySize > 0) {
//
//                double[] pToAllSlopes = new double[slopeArraySize];
//                int slopeIndex = 0;
//
//                for(int j = i+1; j < points.length; j++){
//                    Point q = points[j];
//                    double p_q_slope = p.slopeTo(q);
//                    pToAllSlopes[slopeIndex++] = p_q_slope;
//                }
//
//            }
//
//        }
//
//    }


}
