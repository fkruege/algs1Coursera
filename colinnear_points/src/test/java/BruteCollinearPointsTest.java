import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fkruege on 1/10/2017.
 */
public class BruteCollinearPointsTest {

    @Test(expected = NullPointerException.class)
    public void test_constructor() {
        BruteCollinearPoints brutePoints = new BruteCollinearPoints(null);
    }

    @Test
    public void test_findColinnearPoints1() {
        Point p = new Point(0, 0);
        Point q = new Point(1, 1);
        Point r = new Point(2, 2);
        Point s = new Point(3, 3);

        Point[] points = new Point[4];
        points[0] = p;
        points[1] = q;
        points[2] = r;
        points[3] = s;

        BruteCollinearPoints brutePoints = new BruteCollinearPoints(points);
        assertEquals(1, brutePoints.numberOfSegments());

        LineSegment[] segments = brutePoints.segments();
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

        BruteCollinearPoints brutePoints = new BruteCollinearPoints(points);
        assertEquals(2, brutePoints.numberOfSegments());

        LineSegment[] segments = brutePoints.segments();
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

        BruteCollinearPoints brutePoints = new BruteCollinearPoints(points);
        assertEquals(1, brutePoints.numberOfSegments());

        LineSegment[] segments = brutePoints.segments();
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

        BruteCollinearPoints brutePoints = new BruteCollinearPoints(points);
        assertEquals(2, brutePoints.numberOfSegments());

        LineSegment[] segments = brutePoints.segments();
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

        BruteCollinearPoints brutePoints = new BruteCollinearPoints(points);
        assertEquals(0, brutePoints.numberOfSegments());

        LineSegment[] segments = brutePoints.segments();
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


        BruteCollinearPoints collinearPoints = new BruteCollinearPoints(points);
        LineSegment[] segments = collinearPoints.segments();
        assertEquals(2, segments.length);

        assertEquals(2, collinearPoints.numberOfSegments());


        assertEquals("(0, 0)" + " -> " + "(3, 0)", segments[0].toString());
        assertEquals("(0, 0)" + " -> " + "(0, 3)", segments[1].toString());
    }

}