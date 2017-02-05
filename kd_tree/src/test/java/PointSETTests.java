import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by fkruege on 1/29/17.
 */
public class PointSETTests {

    PointSET _pointSet;
    private RectHV _rectangle;
    private Point2D _testPt1;
    private Point2D _testPt2;
    private Point2D _testPt3;

        @Before
    public void before() {


        _pointSet = new PointSET();


        _rectangle = new RectHV(0.3, 0.3, 0.7, 0.7);

        _testPt1 = new Point2D(0.4, 0.4);
        _testPt2 = new Point2D(0.5, 0.5);
        _testPt3 = new Point2D(0.6, 0.6);

        _pointSet.insert(_testPt1);
        _pointSet.insert(_testPt2);
        _pointSet.insert(_testPt3);
    }

    @Test
    public void test_range1() {
        Stack<Point2D> range = (Stack<Point2D>) _pointSet.range(_rectangle);
        Assert.assertEquals(3, range.size());
        Assert.assertTrue(range.pop().equals(_testPt1));
        Assert.assertTrue(range.pop().equals(_testPt2));
        Assert.assertTrue(range.pop().equals(_testPt3));
    }

    @Test
    public void test_range2() {
        _rectangle = new RectHV(0.1, 0.1, 0.2, 0.2);
        Stack<Point2D> range = (Stack<Point2D>) _pointSet.range(_rectangle);
        Assert.assertEquals(0, range.size());
    }

    @Test
    public void test_range3() {

        PointSET pointSet = new PointSET();
        Point2D ptA = new Point2D(0.9, 0.5);
        pointSet.insert(ptA);
        pointSet.insert(new Point2D(0.2, 0.5));

        RectHV rect = new RectHV(0.87, 0.375, 1.05, 0.6);

        Stack<Point2D> range = (Stack<Point2D>) pointSet.range(rect);
        Assert.assertEquals(1, range.size());
        Assert.assertTrue(range.pop().equals(ptA));

    }


    @Test
    public void test_nearest1() {
        Point2D nearest = _pointSet.nearest(new Point2D(0.1, 0.1));
        Assert.assertTrue(nearest.equals(_testPt1));
    }

    @Test
    public void test_nearest2() {
        Point2D nearest = _pointSet.nearest(new Point2D(0.7, 0.7));
        Assert.assertTrue(nearest.equals(_testPt3));
    }

    @Test
    public void test_nearest3() {
        PointSET emptyPointSet = new PointSET();
        Point2D nearest = emptyPointSet.nearest(new Point2D(0.7, 0.7));
        Assert.assertNull(nearest);
    }

    @Test
    public void test_nearest4() {
        Point2D nearest = _pointSet.nearest(new Point2D(0.41, 0.41));
        Assert.assertTrue(nearest.equals(_testPt1));
    }


    @Test
    public void test_nearest5() {
        Point2D nearest = _pointSet.nearest(new Point2D(0.46, 0.46));
        Assert.assertTrue(nearest.equals(_testPt2));
    }

    @Test
    public void test_nearest6() {

        PointSET pointSet = new PointSET();
        Point2D ptA = new Point2D(0.9, 0.5);
        pointSet.insert(ptA);
        pointSet.insert(new Point2D(0.2, 0.5));

        Point2D nearest = pointSet.nearest(new Point2D(0.8, 0.4));
        Assert.assertEquals(ptA, nearest);


    }


}
