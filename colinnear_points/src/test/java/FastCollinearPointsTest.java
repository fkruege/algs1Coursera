import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fkruege on 1/12/17.
 */
public class FastCollinearPointsTest {


    @Test
    public void test_findColinnearPoints1() {
        Point p = new Point(0, 0);
        Point q = new Point(1, 1);
        Point r = new Point(2, 2);
        Point s = new Point(3, 3);
        Point t = new Point(6, 1);

        Point[] points = new Point[5];
        points[0] = p;
        points[1] = q;
        points[2] = r;
        points[3] = s;
        points[4] = t;

        FastCollinearPoints fastPoints = new FastCollinearPoints(points);

        assertEquals(1, fastPoints.numberOfSegments());

        LineSegment[] segments = fastPoints.segments();
        assertEquals(1, segments.length);
        String pToString = "(0, 0)";
        String sToString = "(3, 3)";
        String expectedLineSegment = pToString + " -> " + sToString;

        assertEquals(expectedLineSegment, segments[0].toString());
    }


    @Test
    public void test_findColinnearPoints2() {
        Point p = new Point(0, 0);
        Point q = new Point(1, 1);
        Point r = new Point(2, 2);
        Point s = new Point(3, 3);

        Point t = new Point(0, 1);
        Point u = new Point(1, 2);
        Point v = new Point(2, 3);
        Point w = new Point(3, 4);


        Point[] points = new Point[8];
        points[0] = p;
        points[1] = q;
        points[2] = r;
        points[3] = s;
        points[4] = t;
        points[5] = u;
        points[6] = v;
        points[7] = w;

        FastCollinearPoints fastPoints = new FastCollinearPoints(points);
        assertEquals(2, fastPoints.numberOfSegments());

        LineSegment[] segments = fastPoints.segments();
        assertEquals(2, segments.length);
        assertEquals("(0, 0)" + " -> " + "(3, 3)", segments[0].toString());
        assertEquals("(0, 1)" + " -> " + "(3, 4)", segments[1].toString());
    }


    @Test
    public void test_findColinnearPoints3() {
        Point p = new Point(5, -6);
        Point q = new Point(1, 1);
        Point r = new Point(2, 2);
        Point s = new Point(3, 3);

        Point t = new Point(4, 4);
        Point u = new Point(1, 2);
        Point v = new Point(2, 3);
        Point w = new Point(3, 4);


        Point[] points = new Point[8];
        points[0] = p;
        points[1] = q;
        points[2] = r;
        points[3] = s;
        points[4] = t;
        points[5] = u;
        points[6] = v;
        points[7] = w;

        FastCollinearPoints fastPoints = new FastCollinearPoints(points);
        assertEquals(1, fastPoints.numberOfSegments());

        LineSegment[] segments = fastPoints.segments();
        assertEquals(1, segments.length);
        assertEquals("(1, 1)" + " -> " + "(4, 4)", segments[0].toString());
    }


    @Test
    public void test_findColinnearPoints4() {
        Point p = new Point(1, 1);
        Point q = new Point(2, 2);
        Point r = new Point(3, 3);
        Point s = new Point(4, 4);

        Point t = new Point(3, 1);
        Point u = new Point(1, 3);
        Point v = new Point(0, 4);


        Point[] points = new Point[7];
        points[0] = p;
        points[1] = q;
        points[2] = r;
        points[3] = s;
        points[4] = t;
        points[5] = u;
        points[6] = v;

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(2, fastCollinearPoints.numberOfSegments());

        LineSegment[] segments = fastCollinearPoints.segments();
        assertEquals(2, segments.length);
        assertEquals("(1, 1)" + " -> " + "(4, 4)", segments[0].toString());
        assertEquals("(3, 1)" + " -> " + "(0, 4)", segments[1].toString());
    }


    @Test
    public void test_findColinnearPoints5() {
        Point p = new Point(1, 1);
        Point q = new Point(2, 2);
        Point r = new Point(3, 3);
        Point t = new Point(3, 1);
        Point u = new Point(1, 3);


        Point[] points = new Point[5];
        int index = 0;
        points[index++] = p;
        points[index++] = q;
        points[index++] = r;
        points[index++] = t;
        points[index++] = u;

        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        assertEquals(0, fastCollinearPoints.numberOfSegments());

        LineSegment[] segments = fastCollinearPoints.segments();
        assertEquals(0, segments.length);
    }


    @Test
    public void test_findColinnearPoints6() {
        Point p = new Point(0, 0);
        Point q = new Point(1, 0);
        Point r = new Point(2, 0);
        Point s = new Point(3, 0);

        Point t = new Point(0, 1);
        Point u = new Point(0, 2);
        Point v = new Point(0, 3);


        Point[] points = new Point[7];
        int index = 0;
        points[index++] = p;
        points[index++] = q;
        points[index++] = r;
        points[index++] = s;
        points[index++] = t;
        points[index++] = u;
        points[index++] = v;


        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        LineSegment[] segments = fastCollinearPoints.segments();
        assertEquals(2, segments.length);

        assertEquals(2, fastCollinearPoints.numberOfSegments());


        assertEquals("(0, 0)" + " -> " + "(3, 0)", segments[0].toString());
        assertEquals("(0, 0)" + " -> " + "(0, 3)", segments[1].toString());
    }



    @Test
    public void test_findColinnearPoints7() {
        Point p = new Point(0, 0);
        Point q = new Point(1, 1);
        Point r = new Point(3, 3);
        Point s = new Point(5, 5);
        Point t = new Point(6, 6);
        Point u = new Point(9, 9);


        Point[] points = new Point[6];
        int index = 0;
        points[index++] = p;
        points[index++] = q;
        points[index++] = r;
        points[index++] = s;
        points[index++] = t;
        points[index++] = u;


        FastCollinearPoints fastCollinearPoints = new FastCollinearPoints(points);
        LineSegment[] segments = fastCollinearPoints.segments();
        assertEquals(1, segments.length);

        assertEquals(1, fastCollinearPoints.numberOfSegments());

        assertEquals("(0, 0)" + " -> " + "(9, 9)", segments[0].toString());
    }

}