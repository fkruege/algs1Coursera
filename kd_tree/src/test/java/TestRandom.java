import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Console;

/**
 * Created by fkruege on 2/5/2017.
 */
public class TestRandom {

    KdTree _kdTree;
    PointSET _pointSet;

    @Before
    public void before(){
        _kdTree = new KdTree();
        _pointSet = new PointSET();
    }

    @Test
    public void test_random5(){

        int limit = 1000;

        for(int i = 0 ; i < limit; i++){
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);

            Point2D point = new Point2D(x, y);
            System.out.println("Adding point: " + point);
            _kdTree.insert(point);
            _pointSet.insert(point);
        }

         for(int i = 0 ; i < limit; i++){
            double x = StdRandom.uniform(0.0, 1.0);
            double y = StdRandom.uniform(0.0, 1.0);

            Point2D point = new Point2D(x, y);
             System.out.println("Search nearest: " + point);
             Point2D kdTreeNearest = _kdTree.nearest(point);
             Point2D pointSetNearest = _pointSet.nearest(point);

             System.out.println("KDTree nearest: " + kdTreeNearest);
             System.out.println("PointSET nearest: " + pointSetNearest);
             Assert.assertEquals(kdTreeNearest, pointSetNearest);
         }



    }
}
