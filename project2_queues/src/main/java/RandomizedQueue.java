import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by fkruege on 1/1/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item>{
    private static final int DEFAULT_SIZE = 2;
    private int _size = 0;
    private int _index = 0;

    private Item[] _items;

    // construct an empty deque
    public RandomizedQueue() {
        _items = (Item[]) new Object[DEFAULT_SIZE];
        _size = 0;
        _index = 0;
    }

    // add the item
    public void enqueue(Item item) {
        checkItemAdded(item);
        _size++;

        if (IsItemsBig()) {
            doubleAndCopyItems();
        }

        int index = getEnqueueItemIndex();
        _items[index] = item;
    }

    private int getEnqueueItemIndex() {
        int index = StdRandom.uniform(0, _items.length);
        if (_items[index] == null) {
            return index;
        } else {
            return getEnqueueItemIndex();
        }
    }

    // remove and return a random item
    public Item dequeue() {
        throwIfEmpty();

        _size--;

        if (IsItemsSmall()) {
            halfAndCopyItems();
        }

        int index = getDequeuetemIndex();
        Item item = _items[index];
        _items[index] = null;

        return item;
    }


    private int getDequeuetemIndex() {
        int index = StdRandom.uniform(0, _items.length);
        if (_items[index] != null) {
            return index;
        } else {
            return getDequeuetemIndex();
        }
    }

    // return (but do not remove) a random item
    public Item sample() {
        throwIfEmpty();

        int index = getDequeuetemIndex();
        Item item = _items[index];

        return item;
    }

    private boolean IsItemsBig() {
        return (_size > 0 && _size >= _items.length);
    }

    private void doubleAndCopyItems() {
        int doubleSize = (_items.length * 2);

        Item[] newItems = (Item[]) new Object[doubleSize];

        // copy all elements starting at the head of the old _newItems array
        for (int i = 0; i < _items.length; i++) {
            newItems[i] = _items[i];
        }

        // point to the new array and initialize the first and _last indexes
        _items = newItems;
    }


    private boolean IsItemsSmall() {
        return (_size > DEFAULT_SIZE && _size <= _items.length / 4);
    }


    private void halfAndCopyItems() {
        int halfSize = (_items.length / 2);

        Item[] newItems = (Item[]) new Object[halfSize];
        int newItemsIndex = 0;

        // copy all non null elements starting at the head of the old _newItems array
        for (int i = 0; i < _items.length; i++) {
            if (_items[i] != null) {
                newItems[newItemsIndex++] = _items[i];
            }
        }

        // point to the new array and initialize the first and _last indexes
        _items = newItems;
    }

    private void throwIfEmpty() {
        if (_size <= 0) {
            throw new NoSuchElementException();
        }
    }


    // is the deque empty?
    public boolean isEmpty() {
        return _size <= 0;
    }

    // return the number of _newItems on the deque
    public int size() {
        return _size;
    }

    private void checkItemAdded(Item item) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }
    }

    public Iterator<Item> iterator() {
        return new RandomIterator(_items, _size);
    }

    private class RandomIterator implements Iterator<Item> {

        private Item[] _newItems;
        private int _index = -1;

        public RandomIterator(Item[] items, int size) {
            _newItems = (Item[]) new Object[size];
            _index = -1;

            for (int i = 0; i < items.length; i++) {
                Item item = items[i];
                if (item != null) {
                    int index = getNewItemIndex();
                    _newItems[index] = item;
                }
            }
        }

        private int getNewItemIndex() {
            int index = StdRandom.uniform(0, _newItems.length);
            if (_newItems[index] == null) {
                return index;
            } else {
                return getNewItemIndex();
            }
        }


        public boolean hasNext() {
            return _index + 1 < _newItems.length;
        }

        public Item next() {
            _index++;

            if (_index >= _newItems.length) {
                throw new NoSuchElementException();
            }

            return _newItems[_index];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
    }


}
