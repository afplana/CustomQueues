/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty randomized queue
    public RandomizedQueue() {
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> l = this.last;
        Node<Item> n = new Node<>(l, item, null);
        this.last = n;
        if (l != null) l.next = n;
        else this.first = n;
        ++size;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) throw new NoSuchElementException();
        int i = StdRandom.uniform(size);
        return remove(node(i));
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) throw new NoSuchElementException();
        return node(StdRandom.uniform(size)).item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueItr();
    }

    // Remove a provided node
    private Item remove(Node<Item> x) {
        Item item = x.item;
        Node<Item> next = x.next;
        Node<Item> prev = x.prev;

        if (prev == null) this.first = next;
        else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) this.last = prev;
        else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        --size;
        return item;
    }

    // return the node in a position
    private Node<Item> node(int x) {
        Node<Item> node;
        int i;

        if (x < size >> 1) {
            node = first;
            for (i = 0; i < x; ++i) node = node.next;
            return node;
        }
        else {
            node = last;
            for (i = this.size - 1; i > x; --i) node = node.prev;
            return node;
        }
    }

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

        Node(Node<Item> prev, Item element, Node<Item> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private class RandomizedQueueItr implements Iterator<Item> {
        private Node<Item> node = first;

        public boolean hasNext() {
            return node != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = node.item;
            node = node.next;
            return item;
        }
    }
}
