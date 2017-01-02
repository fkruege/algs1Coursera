import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.util.resources.uk.CalendarData_uk;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by fkruege on 1/1/2017.
 */
public class RandomizedQueueTest {

    RandomizedQueue<String> _queue;

    @Before
    public void before() {
        _queue = new RandomizedQueue<String>();

    }

    @Test(expected = NoSuchElementException.class)
    public void test_dequeue_whenEmpty() {
        _queue.dequeue();
    }


    @Test(expected = NoSuchElementException.class)
    public void test_sample_whenEmpty() {
        _queue.sample();
    }

    @Test
    public void test_sample2() {
        _queue.enqueue("0");
        Assert.assertEquals("0", _queue.sample());
    }

    @Test(expected = NullPointerException.class)
    public void test_enqueueNull() {
        _queue.enqueue(null);
    }


    @Test
    public void enqueue1() throws Exception {
        Assert.assertTrue(_queue.isEmpty());
        Assert.assertEquals(0, _queue.size());
        _queue.enqueue("a");
        Assert.assertFalse(_queue.isEmpty());
        Assert.assertEquals(1, _queue.size());

        _queue.enqueue("b");
        Assert.assertFalse(_queue.isEmpty());
        Assert.assertEquals(2, _queue.size());
    }

    @Test
    public void enqueue_and_dequeue() throws Exception {
        _queue.enqueue("0");
        _queue.enqueue("1");
        _queue.enqueue("2");
        _queue.enqueue("3");
        _queue.enqueue("4");
        _queue.enqueue("5");
        _queue.enqueue("6");
        _queue.enqueue("7");

        boolean[] results = new boolean[8];

        for (int i = 0; i < 8; i++) {
            int index = Integer.parseInt(_queue.dequeue());
            results[index] = true;
        }

        for (boolean b : results) {
            Assert.assertTrue(b);
        }

        Assert.assertEquals(0, _queue.size());
        Assert.assertTrue(_queue.isEmpty());
    }

    @Test
    public void test_iterator_empty(){
        Iterator<String> iterator = _queue.iterator();
        Assert.assertFalse(iterator.hasNext());
    }


    @Test
    public void test_iterator_1Item(){
        _queue.enqueue("0");

        Iterator<String> iterator = _queue.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("0", iterator.next());
    }

    @Test
    public void test_iterator_2(){
        _queue.enqueue("0");
        _queue.enqueue("1");
        _queue.enqueue("2");
        _queue.enqueue("3");
        _queue.enqueue("4");
        _queue.enqueue("5");
        _queue.enqueue("6");
        _queue.enqueue("7");


        boolean[] results = new boolean[8];
        Iterator<String> iterator = _queue.iterator();

        while(iterator.hasNext()){
            int index = Integer.parseInt(iterator.next());
            results[index] = true;
        }

        for (boolean b : results) {
            Assert.assertTrue(b);
        }

    }


    @Test
    public void test_random() {

        boolean found0 = false;
        boolean found5 = false;
        boolean found6 = false;

        for (int i = 0; i < 10000; i++) {
            int index = StdRandom.uniform(0, 6);
            if (index == 0) {
                found0 = true;
            }

            if (index == 5) {
                found5 = true;
            }

            if (index == 6) {
                found6 = true;
            }
        }

        Assert.assertTrue(found0 && found5);
        Assert.assertFalse(found6);

    }



}