import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by fkruege on 2/4/2017.
 */
public class KDTreeTests {

    KdTree _kdTree;

    Point2D pt1, pt2, pt3, pt4;
    private RectHV _rectangle;


    @Before
    public void before() {
        _kdTree = new KdTree();
        pt1 = new Point2D(0.4, 0.4);
        pt2 = new Point2D(0.5, 0.5);
        pt3 = new Point2D(0.6, 0.6);
        pt4 = new Point2D(0.55, 0.55);

        _rectangle = new RectHV(0.3, 0.3, 0.7, 0.7);
    }

    @Test
    public void test_insert1() {

        Assert.assertEquals(0, _kdTree.size());

        _kdTree.insert(pt2);
        Assert.assertEquals(1, _kdTree.size());

        _kdTree.insert(pt1);
        Assert.assertEquals(2, _kdTree.size());

        _kdTree.insert(pt3);
        Assert.assertEquals(3, _kdTree.size());

        Assert.assertTrue(_kdTree.contains(pt2));
        Assert.assertTrue(_kdTree.contains(pt1));
        Assert.assertTrue(_kdTree.contains(pt3));
    }

    @Test
    public void test_range1a() {
        _kdTree.insert(pt2);
        _kdTree.insert(pt1);
        _kdTree.insert(pt3);

        Stack<Point2D> range = (Stack<Point2D>) _kdTree.range(_rectangle);
        Assert.assertEquals(3, range.size());
        Assert.assertTrue(range.pop().equals(pt3));
        Assert.assertTrue(range.pop().equals(pt1));
        Assert.assertTrue(range.pop().equals(pt2));
    }

    @Test
    public void test_range1b() {
        _kdTree.insert(pt1);
        _kdTree.insert(pt2);
        _kdTree.insert(pt3);

        Stack<Point2D> range = (Stack<Point2D>) _kdTree.range(_rectangle);
        Assert.assertEquals(3, range.size());
        Assert.assertTrue(range.pop().equals(pt3));
        Assert.assertTrue(range.pop().equals(pt2));
        Assert.assertTrue(range.pop().equals(pt1));
    }


    @Test
    public void test_range1c() {

        Point2D testPoint = new Point2D(0.5, .9);
        _kdTree.insert(testPoint);
        _kdTree.insert(pt1);
        _kdTree.insert(pt3);

        Stack<Point2D> range = (Stack<Point2D>) _kdTree.range(_rectangle);
        Assert.assertEquals(2, range.size());
        Assert.assertTrue(range.pop().equals(pt3));
        Assert.assertTrue(range.pop().equals(pt1));
    }

    @Test
    public void test_range2() {
        _kdTree.insert(pt2);
        _kdTree.insert(pt1);
        _kdTree.insert(pt3);

        _rectangle = new RectHV(0.1, 0.1, 0.2, 0.2);

        Stack<Point2D> range = (Stack<Point2D>) _kdTree.range(_rectangle);
        Assert.assertEquals(0, range.size());
    }

    @Test
    public void test_insert() {
        Point2D a = new Point2D(0.425781, 0.652344);
        Point2D b = new Point2D(0.703125, 0.544922);
        Point2D c = new Point2D(0.742188, 0.285156);

        _kdTree.insert(a);
        _kdTree.insert(b);
        _kdTree.insert(c);

        Assert.assertEquals(3, _kdTree.size());
    }

    @Test
    public void test_nearest1() {
        Point2D a = new Point2D(0.9, 0.5);
        Point2D b = new Point2D(0.2, 0.5);
        Point2D c = new Point2D(0.3, 0.5);

        _kdTree.insert(a);
        _kdTree.insert(b);
        _kdTree.insert(c);
        Point2D nearest = _kdTree.nearest(new Point2D(0.1, 0.1));
        Assert.assertEquals(b, nearest);
    }

    @Test
    public void test_nearest2() {
        Point2D a = new Point2D(0.186352591670494, 0.005134896554771506);
        Point2D b = new Point2D(0.25925487654906776, 0.6670414296430585);
        Point2D c = new Point2D(0.36651842211199914, 0.4246831583238142);
        Point2D d = new Point2D(0.33262462900485035, 0.7299806072166836);
        Point2D e = new Point2D(0.5300339662144646, 0.7999193310391862);

//
//        Search nearest: (0.6074562269921329, 0.11866352124923152)
//        KDTree nearest: (0.186352591670494, 0.005134896554771506)
//        PointSET nearest: (0.36651842211199914, 0.4246831583238142)
//


        _kdTree.insert(a);
        _kdTree.insert(b);
        _kdTree.insert(c);
        _kdTree.insert(d);
        _kdTree.insert(e);

        Point2D nearest = _kdTree.nearest(new Point2D(0.6074562269921329, 0.11866352124923152));
        Assert.assertEquals(c, nearest);
    }


    @Test
    public void test_contains1() {
        Point2D a = new Point2D(0.9, 0.5);
        Point2D b = new Point2D(0.2, 0.5);
        Point2D c = new Point2D(0.3, 0.5);

        _kdTree.insert(a);
        _kdTree.insert(b);
        _kdTree.insert(c);

        boolean contains = _kdTree.contains(c);
        Assert.assertTrue(contains);
    }

    @Test
    public void test_contains2() {
        Point2D a = new Point2D(0.9, 0.5);
        Point2D b = new Point2D(0.2, 0.5);
        Point2D c = new Point2D(0.3, 0.5);

        _kdTree.insert(a);
        _kdTree.insert(b);
        _kdTree.insert(c);

        Point2D search = new Point2D(0.4, 0.5);
        boolean contains = _kdTree.contains(search);
        Assert.assertFalse(contains);
    }

}
