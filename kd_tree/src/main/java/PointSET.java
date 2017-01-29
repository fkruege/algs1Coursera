import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
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
        if(!_set.contains(p)){
            _set.add(p);
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return _set.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Point2D bottomLeft = new Point2D(rect.xmin(), rect.ymin());
        Point2D topRight = new Point2D(rect.xmax(), rect.ymax());

        Stack<Point2D> pointsInRect = new Stack<Point2D>();

        Point2D candidatePt = _set.ceiling(bottomLeft);
        while(candidatePt != null){
            if(candidatePt.compareTo(bottomLeft) >= 0
               && candidatePt.compareTo(topRight) <= 0){
                pointsInRect.push(candidatePt);
            }else{
                break;
            }

            candidatePt = _set.higher(candidatePt);
        }

        return pointsInRect;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        throw new NotImplementedException();
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
    }
}
