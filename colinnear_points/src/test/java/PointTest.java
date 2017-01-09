import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by fkruege on 1/9/17.
 */
public class PointTest {

    Point _pt_0_0;
    Point _pt_5_0;
    Point _pt_5_5;

    @Before
    public void before(){
        _pt_0_0 = new Point(0, 0);
        _pt_5_0 = new Point(5, 0);
        _pt_5_5 = new Point(5, 5);

    }

    @Test
    public void slopeTo_Tests() throws Exception {

        // test horizontal line
        assertEquals(0, _pt_0_0.slopeTo(_pt_5_0), 0);

        // test vertical line
        assertEquals(Double.POSITIVE_INFINITY, _pt_5_0.slopeTo(_pt_5_5), 0);

        // test same point
        assertEquals(Double.NEGATIVE_INFINITY, _pt_5_5.slopeTo(_pt_5_5), 0);

        assertEquals(1, _pt_0_0.slopeTo(_pt_5_5), 0);
    }

    @Test
    public void compareTo_Test(){
        // test same point
        assertEquals(0, _pt_0_0.compareTo(_pt_0_0));

        // test point is less than
        assertEquals(-1,_pt_0_0.compareTo(_pt_5_5) );
        assertEquals(-1,_pt_0_0.compareTo(_pt_5_0) );

        // test point is greater than
        assertEquals(1,_pt_5_5.compareTo(_pt_0_0) );
    }

    @Test
    public void slopeOrderTest(){
        Comparator<Point> pointComparator = _pt_0_0.slopeOrder();
        int compareLessThan = pointComparator.compare(_pt_5_0, _pt_5_5);
        assertEquals(-1, compareLessThan);

        int compareGreaterThan = pointComparator.compare(_pt_5_5, _pt_5_0);
        assertEquals(1, compareGreaterThan);

        int compareEqual = pointComparator.compare(_pt_0_0, _pt_0_0);
        assertEquals(0, compareEqual);

    }

}