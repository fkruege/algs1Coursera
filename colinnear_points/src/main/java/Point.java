/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    private static final double SLOPE_HORIZONTAL_LINE = +0.0;
    private static final double SLOPE_VERTICAL_LINE = Double.POSITIVE_INFINITY;
    private static final double SLOPE_SAME_POINT = Double.NEGATIVE_INFINITY;

    /**
     * Initializes a new point.
     *
     * @param x the <em>x</em>-coordinate of the point
     * @param y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if(that == null){
            throw new NullPointerException();
        }

        int deltaY = that.y - this.y;
        int deltaX = that.x - this.x;

        if (deltaY == 0 && deltaX == 0) {
            // same point
            return SLOPE_SAME_POINT;
        } else if (deltaY == 0) {
            // straight horizontal line
            return SLOPE_HORIZONTAL_LINE;
        } else if (deltaX == 0) {
            // vertical line
            return SLOPE_VERTICAL_LINE;
        }

        return (double)deltaY/(double)deltaX;

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     * point (x0 = x1 and y0 = y1);
     * a negative integer if this point is less than the argument
     * point; and a positive integer if this point is greater than the
     * argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */

        if(that == null){
            throw new NullPointerException();
        }

        double slope = this.slopeTo(that);
        if (slope == Double.NEGATIVE_INFINITY) {
            // same point
            return 0;
        }

        // this less than that
        if (this.y < that.y) {
            return -1;
        } else if (this.y == that.y && this.x < that.x) {
            return -1;
        }

        // this is greater than that
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return new SlopeOrder();
    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }

    private class SlopeOrder implements Comparator<Point> {

        public int compare(Point point1, Point point2) {
            double slopeParentAndPoint1 = Point.this.slopeTo(point1);
            double slopeParentAndPoint2 = Point.this.slopeTo(point2);
            return Double.compare(slopeParentAndPoint1, slopeParentAndPoint2);
        }
    }
}
