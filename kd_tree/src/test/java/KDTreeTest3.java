import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by fkruege on 1/29/17.
 */
public class KDTreeTest3 {

    KdTree _kdTree;
    private Point2D _testPt1;
    private Point2D _testPt2;
    private Point2D _testPt3;

    @Before
    public void before() {

        _kdTree = new KdTree();

        _testPt1 = new Point2D(0.4, 0.4);
        _testPt2 = new Point2D(0.5, 0.5);
        _testPt3 = new Point2D(0.6, 0.6);

        _kdTree.insert(_testPt1);
        _kdTree.insert(_testPt2);
        _kdTree.insert(_testPt3);
    }

    @Test
    public void test_nearest1() {
        Point2D nearest = _kdTree.nearest(new Point2D(0.1, 0.1));
        Assert.assertTrue(nearest.equals(_testPt1));
    }

    @Test
    public void test_nearest2() {
        Point2D nearest = _kdTree.nearest(new Point2D(0.7, 0.7));
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
        Point2D nearest = _kdTree.nearest(new Point2D(0.41, 0.41));
        Assert.assertTrue(nearest.equals(_testPt1));
    }


    @Test
    public void test_nearest5() {
        Point2D nearest = _kdTree.nearest(new Point2D(0.46, 0.46));
        Assert.assertTrue(nearest.equals(_testPt2));
    }


}
