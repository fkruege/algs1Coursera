import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
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
    public void test_MixAddRemove3() {
        _deck.addFirst("0");
        _deck.addFirst("1");
        _deck.addFirst("2");
        _deck.addFirst("3");
        _deck.addFirst("4");
        _deck.addFirst("5");

        _deck.addLast("-1");
        _deck.addLast("-2");
        _deck.addLast("-3");
        _deck.addLast("-4");
        _deck.addLast("-5");

        Assert.assertEquals("-5", _deck.removeLast());
        Assert.assertEquals("-4", _deck.removeLast());
        Assert.assertEquals("-3", _deck.removeLast());
        Assert.assertEquals("-2", _deck.removeLast());
        Assert.assertEquals("-1", _deck.removeLast());
        Assert.assertEquals("0", _deck.removeLast());


        Assert.assertEquals("5", _deck.removeFirst());
        Assert.assertEquals("4", _deck.removeFirst());
        Assert.assertEquals("3", _deck.removeFirst());
        Assert.assertEquals("2", _deck.removeFirst());
        Assert.assertEquals("1", _deck.removeFirst());

        Assert.assertTrue(_deck.isEmpty());
        Assert.assertEquals(0, _deck.size());


        _deck.addFirst("0");
        _deck.addFirst("1");
        _deck.addFirst("2");
        _deck.addFirst("3");
        _deck.addFirst("4");
        _deck.addFirst("5");

        Assert.assertEquals("5", _deck.removeFirst());
        Assert.assertEquals("4", _deck.removeFirst());
        Assert.assertEquals("3", _deck.removeFirst());
        Assert.assertEquals("2", _deck.removeFirst());
        Assert.assertEquals("1", _deck.removeFirst());

        _deck.addLast("-1");
        _deck.addLast("-2");
        _deck.addLast("-3");
        _deck.addLast("-4");
        _deck.addLast("-5");
        _deck.addLast("-6");
        _deck.addLast("-7");
        _deck.addLast("-8");
        _deck.addLast("-9");
        _deck.addLast("-10");
        _deck.addLast("-11");
        _deck.addLast("-12");


        Assert.assertEquals("0", _deck.removeFirst());
        Assert.assertEquals("-1", _deck.removeFirst());

        Assert.assertEquals("-12", _deck.removeLast());
        Assert.assertEquals("-2", _deck.removeFirst());


        Assert.assertEquals("-11", _deck.removeLast());
        Assert.assertEquals("-3", _deck.removeFirst());

        Assert.assertEquals("-10", _deck.removeLast());
        Assert.assertEquals("-4", _deck.removeFirst());


        Assert.assertEquals("-9", _deck.removeLast());
        Assert.assertEquals("-5", _deck.removeFirst());

        Assert.assertEquals("-8", _deck.removeLast());
        Assert.assertEquals("-6", _deck.removeFirst());


        Assert.assertEquals("-7", _deck.removeLast());

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
    public void test_general_1() {

        _deck.addFirst("a");
        _deck.addFirst("b");

        Assert.assertEquals("a", _deck.removeLast());
        Assert.assertEquals("b", _deck.removeLast());

        _deck.addFirst("c");
        Assert.assertEquals(1, _deck.size());
    }

    @Test
    public void test_general_2() {

        _deck.addFirst("a");
        _deck.addFirst("b");
        _deck.addFirst("c");

        Assert.assertEquals("a", _deck.removeLast());
        Assert.assertEquals("b", _deck.removeLast());
        Assert.assertEquals("c", _deck.removeLast());

        _deck.addLast("d");
        Assert.assertEquals("d", _deck.removeFirst());
        Assert.assertEquals(0, _deck.size());

        _deck.addLast("a");
        _deck.addLast("b");
        _deck.addLast("c");

        Assert.assertEquals("a", _deck.removeFirst());
        Assert.assertEquals("b", _deck.removeFirst());

        _deck.addFirst("d");

        Assert.assertEquals("c", _deck.removeLast());
        Assert.assertEquals("d", _deck.removeLast());
    }

    @Test
    public void test_general3(){
        _deck.addFirst("a");
        Assert.assertEquals("a", _deck.removeFirst());
        _deck.addFirst("b");
        Assert.assertEquals("b", _deck.removeFirst());


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
    public void test_iterator_empty(){
        Iterator<String> iterator = _deck.iterator();
        Assert.assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator() throws Exception {
        _deck.addFirst("0");
        _deck.addFirst("1");
        _deck.addFirst("2");
        _deck.addFirst("3");
        _deck.addFirst("4");
        _deck.addFirst("5");

        _deck.addLast("-1");
        _deck.addLast("-2");
        _deck.addLast("-3");
        _deck.addLast("-4");
        _deck.addLast("-5");

        Iterator<String> iterator = _deck.iterator();
        String s = "";
        while (iterator.hasNext()) {
            s = s + " " + iterator.next();
        }

        Assert.assertEquals(" 5 4 3 2 1 0 -1 -2 -3 -4 -5", s);
    }


    @Test
    public void iterator_2() throws Exception {
        _deck.addFirst("0");

        Iterator<String> iterator = _deck.iterator();
        Assert.assertEquals("0", iterator.next());

        boolean exceptionCaught = false;

        try {
            Assert.assertFalse(iterator.hasNext());
            iterator.next();
        } catch (NoSuchElementException ex) {
            exceptionCaught = true;
        }

        Assert.assertTrue(exceptionCaught);

    }


}