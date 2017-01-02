import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by fkruege on 1/1/17.
 */
public class Deque<Item> implements Iterable<Item> {
    private static final int DEFAULT_SIZE = 1;
    private static final int DEFAULT_FIRST_LAST_INDEX = 0;

    private int _size = 0;
    private int _firstIndex = DEFAULT_FIRST_LAST_INDEX;
    private int _lastIndex = DEFAULT_FIRST_LAST_INDEX;

    private Item[] _items;

    // construct an empty deque
    public Deque() {
        _items = (Item[]) new Object[DEFAULT_SIZE];
        _size = 0;

        initFirstLast();
    }

    public Iterator<Item> iterator() {
        return new FrontToEndIterator(_items, _firstIndex, _lastIndex);
    }


    private void initFirstLast() {
        _firstIndex = DEFAULT_FIRST_LAST_INDEX;
        _lastIndex = DEFAULT_FIRST_LAST_INDEX;
    }


    // is the deque empty?
    public boolean isEmpty() {
        return _size <= 0;
    }

    // return the number of _items on the deque
    public int size() {
        return _size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItemAdded(item);
        _size++;

        if (_size == 1) {
            initFirstLast();
        } else {
            if (_firstIndex + 1 >= _items.length) {
                doubleAndCopyItems();
            }
            _firstIndex++;
        }

        _items[_firstIndex] = item;
    }

    // add the item to the end
    public void addLast(Item item) {
        checkItemAdded(item);
        _size++;

        if (_size == 1) {
            initFirstLast();
        } else {
            if (_lastIndex - 1 < 0) {
                doubleAndCopyItems();
            }
            _lastIndex--;
        }

        _items[_lastIndex] = item;
    }


    // remove and return the item from the front
    public Item removeFirst() {

        if (_size <= 0) {
            throw new NoSuchElementException("");
        }

        Item value = _items[_firstIndex];

        _items[_firstIndex] = null;
        _size--;
        _firstIndex--;

        if(isItemsTooSmall()){
            halfAndCopyItems();
        }

        return value;

    }

    // remove and return the item from the end
    public Item removeLast() {

        if (_size <= 0) {
            throw new NoSuchElementException("");
        }

        Item value = _items[_lastIndex];

        _items[_lastIndex] = null;
        _size--;
        _lastIndex++;

        return value;
    }

    private void doubleAndCopyItems() {
        int doubleSize = (_items.length * 2) + 1;

        Item[] newItems = (Item[]) new Object[doubleSize];

        int newMiddle = doubleSize / 2;
        int offset = _items.length / 2;

        int newLastIndex = newMiddle - offset;
        int newIndex = newLastIndex;

        // copy all elements starting at the head of the old _items array
        for (int i = _lastIndex; i <= _firstIndex; i++) {
            newItems[newIndex++] = _items[i];
        }

        // point to the new array and initialize the first and _last indexes
        _items = newItems;
        _firstIndex = newIndex - 1;
        _lastIndex = newLastIndex;
    }

    private boolean isItemsTooSmall() {
        return _size > 0 && _size <= _items.length / 4;

    }

    private void halfAndCopyItems() {
        int halfSize = _items.length / 2;

        Item[] newItems = (Item[]) new Object[halfSize];

        int newMiddle = halfSize / 2;
        int offset = _size / 2;

        int newLastIndex = newMiddle - offset;
        int newIndex = newLastIndex;

        // copy all elements starting at the head of the old _items array
        for (int i = _lastIndex; i <= _firstIndex; i++) {
            newItems[newIndex++] = _items[i];
        }

        // point to the new array and initialize the first and _last indexes
        _items = newItems;
        _firstIndex = newIndex - 1;
        _lastIndex = newLastIndex;

    }


    private void checkItemAdded(Item item) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }
    }

    private class FrontToEndIterator implements Iterator<Item> {

        private Item[] _items;
        private int _first;
        private int _last;

        public FrontToEndIterator(Item[] items, int first, int last) {

            _items = items;
            _first = first + 1;
            _last = last;
        }

        public boolean hasNext() {
            return _first - 1 >= _last;
        }

        public Item next() {
            _first--;

            if (_first < _last) {
                throw new NoSuchElementException();
            }

            return _items[_first];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
    }


}
