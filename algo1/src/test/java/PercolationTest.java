import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by fkruege on 12/26/2016.
 */
public class PercolationTest {

    @Test(expected = IllegalArgumentException.class)
    public void test_ctor1() {
        Percolation percolate = new Percolation(0);
    }

    @Test
    public void test_ctor2() {

        Percolation percolate = new Percolation(1);
    }

    // test 1 sized grid without any open sites
    @Test
    public void test_1_sizedGrid_NoOpenSites() {
        Percolation percolate = new Percolation(1);
        Assert.assertFalse(percolate.percolates());
        Assert.assertFalse(percolate.isOpen(1, 1));
        Assert.assertFalse(percolate.isFull(1, 1));
    }


    // test 1 sized grid without any open sites
    @Test
    public void test_1_sizedGrid_With1OpenSite() {
        Percolation percolate = new Percolation(1);
        percolate.open(1, 1);
        Assert.assertTrue(percolate.percolates());
        Assert.assertTrue(percolate.isOpen(1, 1));
        Assert.assertTrue(percolate.isFull(1, 1));
    }


    // test 3 sized grid without any open sites
    @Test
    public void test_3_sizedGrid_WithNoOpenSite() {
        Percolation percolate = new Percolation(3);
        Assert.assertFalse(percolate.percolates());

        percolate.open(1, 1);
        Assert.assertTrue(percolate.isOpen(1, 1));
        Assert.assertFalse(percolate.percolates());


        percolate.open(2, 2);
        Assert.assertTrue(percolate.isOpen(2, 2));
        Assert.assertFalse(percolate.percolates());

        percolate.open(3, 3);
        Assert.assertTrue(percolate.isOpen(3, 3));
        Assert.assertFalse(percolate.percolates());
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void open_exception1() throws Exception {
        Percolation percolate = new Percolation(3);
        percolate.open(3, 4);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void open_exception2() throws Exception {
        Percolation percolate = new Percolation(3);
        percolate.open(4, 3);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void isFull_exception1() throws Exception {
        Percolation percolate = new Percolation(3);
        percolate.isFull(3, 4);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void isFull_exception2() throws Exception {
        Percolation percolate = new Percolation(3);
        percolate.isFull(4, 3);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpen_exception1() throws Exception {
        Percolation percolate = new Percolation(3);
        percolate.isOpen(3, 4);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void isOpen_exception2() throws Exception {
        Percolation percolate = new Percolation(3);
        percolate.isOpen(4, 3);
    }





}