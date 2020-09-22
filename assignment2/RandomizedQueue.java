import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return (size == 0);
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(size * 2);
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int randIndex = StdRandom.uniform(size);
        Item item = items[randIndex];
        items[randIndex] = items[--size];
        items[size] = null;
        if (size > 0 && size == items.length / 4) resize(items.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return items[StdRandom.uniform(size)];
    }

    private void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = items[i];
        }
        items = temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private final Item[] iteratorItems;
        private int currentItem;

        public RandomIterator() {
            currentItem = 0;
            iteratorItems = (Item[]) new Object[size];
            for (int i = 0; i < size; i++) {
                iteratorItems[i] = items[i];
            }
            StdRandom.shuffle(iteratorItems);
        }

        public boolean hasNext() {
            return currentItem < size;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (currentItem == size) throw new NoSuchElementException();
            return iteratorItems[currentItem++];
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        //This method is intentionally empty.
    }

}
