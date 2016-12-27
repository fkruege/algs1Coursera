import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by fkruege on 12/26/2016.
 */
public class NeighborHelperTest {

    int _gridSize = 5;
    Percolation.NeighborHelper _neighborHelper;

    @Before
    public void before(){
        _neighborHelper = new Percolation.NeighborHelper(_gridSize);
    }


    @Test
    public void getNeighbors1() throws Exception {
        Percolation.NeighborsIndex neighbors = _neighborHelper.getNeighbors(1, 1);
        Assert.assertEquals(-1, neighbors.getLeftNeighbor());
        Assert.assertEquals(-1, neighbors.getTopNeighbor());

        Assert.assertEquals(1, neighbors.getRightNeighbor());
        Assert.assertEquals(5, neighbors.getBottomNeighbor());
    }


    @Test
    public void getNeighbors2() throws Exception {
        Percolation.NeighborsIndex neighbors = _neighborHelper.getNeighbors(5, 5);
        Assert.assertEquals(23, neighbors.getLeftNeighbor());
        Assert.assertEquals(19, neighbors.getTopNeighbor());

        Assert.assertEquals(-1, neighbors.getRightNeighbor());
        Assert.assertEquals(-1, neighbors.getBottomNeighbor());
    }


    @Test
    public void getNeighbors3() throws Exception {
        Percolation.NeighborsIndex neighbors = _neighborHelper.getNeighbors(3, 3);
        Assert.assertEquals(11, neighbors.getLeftNeighbor());
        Assert.assertEquals(7, neighbors.getTopNeighbor());

        Assert.assertEquals(13, neighbors.getRightNeighbor());
        Assert.assertEquals(17, neighbors.getBottomNeighbor());
    }

    @Test
    public void getNeighbors_8grid1() throws Exception {
        Percolation.NeighborHelper neighbor8 = new Percolation.NeighborHelper(8);
        Percolation.NeighborsIndex neighbors = neighbor8.getNeighbors(2, 8);
        Assert.assertEquals(14, neighbors.getLeftNeighbor());
        Assert.assertEquals(7, neighbors.getTopNeighbor());

        Assert.assertEquals(-1, neighbors.getRightNeighbor());
        Assert.assertEquals(23, neighbors.getBottomNeighbor());
    }

    @Test
    public void getNeighbors_8grid2() throws Exception {
        Percolation.NeighborHelper neighbor8 = new Percolation.NeighborHelper(8);
        Percolation.NeighborsIndex neighbors = neighbor8.getNeighbors(8, 1);

        Assert.assertEquals(-1, neighbors.getLeftNeighbor());
        Assert.assertEquals(48, neighbors.getTopNeighbor());

        Assert.assertEquals(57, neighbors.getRightNeighbor());
        Assert.assertEquals(-1, neighbors.getBottomNeighbor());
    }


    @Test
    public void translateRowColIntoIndex() throws Exception {

        Assert.assertEquals(0, _neighborHelper.translateRowColIntoIndex(1,1));
        Assert.assertEquals(12, _neighborHelper.translateRowColIntoIndex(3,3));
        Assert.assertEquals(24, _neighborHelper.translateRowColIntoIndex(5,5));

    }

    @Test
    public void isTopRow() throws Exception {
        Assert.assertTrue(_neighborHelper.isTopRow(1));
        Assert.assertFalse(_neighborHelper.isTopRow(2));
        Assert.assertFalse(_neighborHelper.isTopRow(5));
    }

    @Test
    public void isBottomRow() throws Exception {
        Assert.assertTrue(_neighborHelper.isBottomRow(5));
        Assert.assertFalse(_neighborHelper.isBottomRow(2));
        Assert.assertFalse(_neighborHelper.isBottomRow(1));

    }

}