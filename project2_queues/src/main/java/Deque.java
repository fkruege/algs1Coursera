import java.util.Iterator;

/**
 * Created by fkruege on 1/1/17.
 */
public class Deque<Item> {
    private static final int DEFAULT_SIZE = 1;

    private int _firstIndex = 0;
    private int _lastIndex = 0;

    private Item[] _items;

    // construct an empty deque
    public Deque() {
        _items = (Item[]) new Object[DEFAULT_SIZE];
        _firstIndex = 0;
        _lastIndex = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {

    }

    // return the number of items on the deque
    public int size() {
    }

    // add the item to the front
    public void addFirst(Item item) {
        checkItemAdded(item);
    }

    // add the item to the end
    public void addLast(Item item) {
        checkItemAdded(item);
    }

    // remove and return the item from the front
    public Item removeFirst() {
    }

    // remove and return the item from the end
    public Item removeLast() {
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
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
