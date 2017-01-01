import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by fkruege on 1/1/17.
 */
public class Deque<Item> {
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

    private void initFirstLast() {
        _firstIndex = DEFAULT_FIRST_LAST_INDEX;
        _lastIndex = DEFAULT_FIRST_LAST_INDEX;
    }


    // is the deque empty?
    public boolean isEmpty() {
        return _size <= 0;
    }

    // return the number of items on the deque
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


    private void doubleAndCopyItems() {
        int doubleSize = (_items.length * 2) + 1;

        Item[] newItems = (Item[]) new Object[doubleSize];

        int newMiddle = doubleSize / 2;
        int offset = _items.length / 2;

        int newLastIndex = newMiddle - offset;
        int newIndex = newLastIndex;

        // copy all elements starting at the head of the old items array
        for (int i = _lastIndex; i <= _firstIndex; i++) {
            newItems[newIndex++] = _items[i];
        }

        // point to the new array and initialize the first and last indexes
        _items = newItems;
        _firstIndex = newIndex - 1;
        _lastIndex = newLastIndex;
    }


    // remove and return the item from the front
    public Item removeFirst() {

        Item value = _items[_firstIndex];

        if (value == null) {
            throw new NoSuchElementException("");
        }

        _items[_firstIndex] = null;
        _size--;
        _firstIndex--;

        return value;

    }

    // remove and return the item from the end
    public Item removeLast() {

        Item value = _items[_lastIndex];

        if (value == null) {
            throw new NoSuchElementException("");
        }

        _items[_lastIndex] = null;
        _size--;
        _lastIndex++;

        return value;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return null;
    }

    private void checkItemAdded(Item item) {
        if (item == null) {
            throw new NullPointerException("Item is null");
        }
    }

    // unit testing
    public static void main(String[] args) {
    }


}
