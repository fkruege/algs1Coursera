import edu.princeton.cs.algs4.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.util.resources.cldr.ssy.CalendarData_ssy_ER;

import java.util.Arrays;
import java.util.Iterator;
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

        if (rect == null) {
            throw new NullPointerException();
        }

        Point2D bottomLeft = new Point2D(rect.xmin(), rect.ymin());
        Point2D topRight = new Point2D(rect.xmax(), rect.ymax());

        Stack<Point2D> pointsInRect = new Stack<Point2D>();

//        Point2D candidatePt = _set.ceiling(bottomLeft);
        Point2D candidatePt = _set.floor(topRight);
        while (candidatePt != null) {
//            if(rect.contains(candidatePt))
            if (rect.contains(candidatePt)) {
                pointsInRect.push(candidatePt);
            }
//            if (candidatePt.compareTo(bottomLeft) >= 0
//                    && candidatePt.compareTo(topRight) <= 0) {
//                pointsInRect.push(candidatePt);
//            } else {
//                break;
//            }

            candidatePt = _set.lower(candidatePt);
        }

        return pointsInRect;
    }
//
//    // a nearest neighbor in the set to point p; null if the set is empty
//    public Point2D nearest(Point2D p) {
//
//        validatePoint(p);
//
//
//        Point2D higher = _set.higher(p);
//        Point2D lower = _set.lower(p);
//
//        if (higher != null && lower != null) {
//            double higherDistance = higher.distanceSquaredTo(p);
//            double lowerDistance = lower.distanceSquaredTo(p);
//
//            if (Double.compare(higherDistance, lowerDistance) <= 0) {
//                return higher;
//            } else {
//                return lower;
//            }
//        }
//
//        if (higher == null) {
//            return lower;
//        }
//
//        if (lower == null) {
//            return higher;
//        }
//        return null;
//    }

    public Point2D nearest(Point2D p) {
        Iterator<Point2D> iterator = _set.iterator();
        Point2D nearestPoint = null;
        double nearestDistance = Double.MAX_VALUE;

        while(iterator.hasNext()){
            Point2D next = iterator.next();
            double distance = next.distanceTo(p);
            if(distance < nearestDistance){
                nearestDistance = distance;
                nearestPoint = next;
            }
        }

        return nearestPoint;
    }
//
//    public Point2D nearest(Point2D p) {
//
//        double nearestHigherDistance = Double.MAX_VALUE;
//        Point2D closestHighPoint = null;
//
//        Point2D currentHigher = _set.higher(p);
//        while (currentHigher != null) {
//            double distance = currentHigher.distanceSquaredTo(p);
//            if (distance < nearestHigherDistance) {
//                nearestHigherDistance = distance;
//                closestHighPoint = currentHigher;
//            } else {
//                break;
//            }
//            currentHigher = _set.higher(currentHigher);
//        }
//
//        double nearestLowerDistance = Double.MAX_VALUE;
//        Point2D closestLowPoint = null;
//        Point2D currentLower = _set.lower(p);
//
//        while (currentLower != null) {
//            double distance = currentLower.distanceSquaredTo(p);
//            if (distance < nearestLowerDistance) {
//                nearestLowerDistance = distance;
//                closestLowPoint = currentLower;
//            } else {
//                break;
//            }
//            currentLower = _set.lower(currentLower);
//        }
//
//
//        if(closestHighPoint == null && closestLowPoint == null){
//            return null;
//        }
//
//
//        if(closestHighPoint != null && closestLowPoint == null){
//            return closestHighPoint;
//        }
//
//        if(closestLowPoint != null && closestHighPoint == null){
//            return closestLowPoint;
//        }
//
//        if(nearestHigherDistance < nearestLowerDistance){
//            return closestHighPoint;
//        }else{
//            return closestLowPoint;
//        }
//
//
//
//
//    }
//


    private void validatePoint(Point2D point) {
        if (point == null) {
            throw new NullPointerException();
        }
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
