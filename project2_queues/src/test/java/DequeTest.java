import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Created by fkruege on 1/1/2017.
 */
public class DequeTest {

    Deque<String> _deck;

    @Before
    public void before() {
        _deck = new Deque<String>();
    }

    @Test
    public void test_initialCtor() {
        Assert.assertTrue(_deck.isEmpty());
        Assert.assertEquals(0, _deck.size());
    }

    @Test
    public void test_Add_Remove_First1() {
        _deck.addFirst("a");
        Assert.assertEquals(1, _deck.size());
        Assert.assertFalse(_deck.isEmpty());
        String first = _deck.removeFirst();
        Assert.assertEquals("a", first);


        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
    }


    @Test
    public void test_Add_Remove_First2() {
        _deck.addFirst("a");
        _deck.addFirst("b");
        _deck.addFirst("c");


        Assert.assertEquals(3, _deck.size());
        Assert.assertFalse(_deck.isEmpty());

        Assert.assertEquals("c", _deck.removeFirst());
        Assert.assertEquals("b", _deck.removeFirst());
        Assert.assertEquals("a", _deck.removeFirst());

        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
    }


    @Test
    public void test_Add_Remove_Last1() {
        _deck.addLast("a");
        Assert.assertEquals(1, _deck.size());
        Assert.assertFalse(_deck.isEmpty());
        Assert.assertEquals("a", _deck.removeLast());

        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
    }


    @Test
    public void test_Add_Remove_Last2() {
        _deck.addLast("a");
        _deck.addLast("b");
        _deck.addLast("c");


        Assert.assertEquals(3, _deck.size());
        Assert.assertFalse(_deck.isEmpty());

        Assert.assertEquals("c", _deck.removeLast());
        Assert.assertEquals("b", _deck.removeLast());
        Assert.assertEquals("a", _deck.removeLast());

        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
    }

    @Test
    public void test_MixAddRemove1() {
        _deck.addFirst("a");
        _deck.addLast("b");
        _deck.addLast("c");

        Assert.assertEquals(3, _deck.size());
        Assert.assertFalse(_deck.isEmpty());

        Assert.assertEquals("a", _deck.removeFirst());
        Assert.assertEquals("b", _deck.removeFirst());
        Assert.assertEquals("c", _deck.removeFirst());


        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
    }


    @Test
    public void test_MixAddRemove2() {
        _deck.addFirst("a");
        _deck.addLast("b");
        _deck.addFirst("c");

        Assert.assertEquals(3, _deck.size());
        Assert.assertFalse(_deck.isEmpty());

        Assert.assertEquals("c", _deck.removeFirst());
        Assert.assertEquals("a", _deck.removeFirst());
        Assert.assertEquals("b", _deck.removeFirst());

        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
    }

    @Test
    public void test_removeFirst_ThenException() {
        _deck.addFirst("a");
        _deck.addFirst("b");
        _deck.addFirst("c");


        Assert.assertEquals("c", _deck.removeFirst());
        Assert.assertEquals("b", _deck.removeFirst());
        Assert.assertEquals("a", _deck.removeFirst());

        Assert.assertEquals(0, _deck.size());
        Assert.assertTrue(_deck.isEmpty());
        boolean exceptionCaught = false;

        try {
            _deck.removeFirst();
        } catch (NoSuchElementException ex) {
            exceptionCaught = true;
        }

        Assert.assertTrue(exceptionCaught);
    }

    @Test
    public void test_general_1(){

        _deck.addFirst("a");
        _deck.addFirst("b");

        Assert.assertEquals("a", _deck.removeLast());
        Assert.assertEquals("b", _deck.removeLast());

        _deck.addFirst("c");
        Assert.assertEquals(1, _deck.size());
    }

    @Test
    public void test_general_2(){

        _deck.addFirst("a");
        _deck.addFirst("b");
        _deck.addFirst("c");

        Assert.assertEquals("a", _deck.removeLast());
        Assert.assertEquals("b", _deck.removeLast());
        Assert.assertEquals("c", _deck.removeLast());

        _deck.addLast("d");
        Assert.assertEquals("d", _deck.removeFirst());
    }



    @Test
    public void isEmpty_afterInitialCtor() throws Exception {
    }

    @Test
    public void size() throws Exception {

    }

    @Test(expected = NullPointerException.class)
    public void addFirst_Null() throws Exception {
        _deck.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void addLast_Null() throws Exception {
        _deck.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirst_EmptyDeck() throws Exception {
        _deck.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLast_EmptyDeck() throws Exception {
        _deck.removeLast();
    }

    @Test
    public void iterator() throws Exception {

    }

}