import edu.princeton.cs.algs4.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.TreeSet;

/**
 * Created by fkruege on 1/28/17.
 */
public class PointSET {

    private TreeSet<Point2D> _set;


    // construct an empty set of points
    public PointSET() {
        _set = new TreeSet<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return _set.isEmpty();
    }

    // number of points in the set
    public int size() {
        return _set.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        validatePoint(p);
        if (!_set.contains(p)) {
            _set.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        validatePoint(p);
        return _set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point :
                _set) {
            StdDraw.point(point.x(), point.y());
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {

        if(rect == null){
            throw new NullPointerException();
        }

        Point2D bottomLeft = new Point2D(rect.xmin(), rect.ymin());
        Point2D topRight = new Point2D(rect.xmax(), rect.ymax());

        Stack<Point2D> pointsInRect = new Stack<Point2D>();

        Point2D candidatePt = _set.ceiling(bottomLeft);
        while (candidatePt != null) {
            if (candidatePt.compareTo(bottomLeft) >= 0
                    && candidatePt.compareTo(topRight) <= 0) {
                pointsInRect.push(candidatePt);
            } else {
                break;
            }

            candidatePt = _set.higher(candidatePt);
        }

        return pointsInRect;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {

        validatePoint(p);

        Point2D higher = _set.higher(p);
        Point2D lower = _set.lower(p);

        if(higher != null && lower != null){
            double higherDistance = higher.distanceTo(p);
            double lowerDistance = lower.distanceTo(p);

            if (Double.compare(higherDistance, lowerDistance) <= 0) {
                return higher;
            } else {
                return lower;
            }
        }

        if(higher == null){
            return lower;
        }

        if(lower == null){
            return higher;
        }

        return null;

    }

    private void validatePoint(Point2D point){
        if(point == null){
            throw new NullPointerException();
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
