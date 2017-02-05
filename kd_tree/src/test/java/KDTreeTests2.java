import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by fkruege on 2/4/2017.
 */
public class KDTreeTests2 {

    KdTree _kdTree;

    Point2D pt1, pt2, pt3, pt4;

    Point2D _goalPt;


    @Before
    public void before() {
        _kdTree = new KdTree();
        pt1 = new Point2D(0.5, 0.5);
        pt2 = new Point2D(0.1, 0.1);
        pt3 = new Point2D(0.51, 0.8);

        _goalPt = new Point2D(.1, .8);
    }


    @Test
    public void test_findNearest1(){
        _kdTree.insert(pt1);
        Point2D nearest = _kdTree.nearest(_goalPt);
        Assert.assertTrue(nearest.equals(pt1));
    }


    @Test
    public void test_findNearest2(){
        _kdTree.insert(pt1);
        _kdTree.insert(pt2);
        Point2D nearest = _kdTree.nearest(_goalPt);
        Assert.assertTrue(nearest.equals(pt1));
    }


    @Test
    public void test_findNearest3(){
        _kdTree.insert(pt1);
        _kdTree.insert(pt2);
        _kdTree.insert(pt3);
        Point2D nearest = _kdTree.nearest(_goalPt);
        Assert.assertTrue(nearest.equals(pt3));
    }
}
