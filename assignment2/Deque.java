import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0, first = 0, last = 0;

    public Deque() {
        items = (Item[]) new Object[2];
        size = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(items.length * 2);
        if (first == last && items[last] == null) {
            //Empty array
            items[first] = item;
        } else if (first == 0) {
            //Non empty array with first element at 0, loop
            first = items.length - 1;
            items[first] = item;
        } else {
            first--;
            items[first] = item;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(items.length * 2);
        if (first == last && items[first] == null) {
            //Empty array
            items[last] = item;
        } else if (last == items.length - 1) {
            //Non empty array with last item at end of array, loop
            last = 0;
            items[last] = item;
        } else {
            last++;
            items[last] = item;
        }
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = items[first];
        items[first] = null;
        size--;
        if (first == last) {
            first = 0;
            last = 0;
        } else if (first == items.length - 1) {
            first = 0;
        } else {
            first++;
        }
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = items[last];
        items[last] = null;
        size--;
        if (last == first) {
            first = 0;
            last = 0;
        } else if (last == 0) {
            last = items.length - 1;
        } else {
            last--;
        }
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    public Iterator<Item> iterator() {
        return new ResizingArrayIterator();
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[(first + i) % items.length];
        }
        first = 0;
        last = size - 1;
        items = temp;
    }

    private class ResizingArrayIterator implements Iterator<Item> {

        private int index = 0;

        public boolean hasNext() {
            return index < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (index == size) throw new NoSuchElementException();
            Item item = items[(first + index) % items.length];
            index++;
            return item;
        }
    }

    public static void main(String[] args) {
        //This method is intentionally empty.
    }

}
