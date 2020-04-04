/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node<Item> first;
    private Node<Item> last;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> f = this.first;
        Node<Item> n = new Node<>(null, item, f);
        this.first = n;
        if (f != null) f.prev = n;
        else this.last = n;
        ++size;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node<Item> l = this.last;
        Node<Item> n = new Node<>(l, item, null);
        this.last = n;
        if (l != null) l.next = n;
        else this.first = n;
        ++size;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Item item = this.first.item;
        Node<Item> next = this.first.next;
        this.first.item = null;
        this.first.next = null;
        this.first = next;

        if (next != null) next.prev = null;
        else this.last = null;

        --size;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Item item = this.last.item;
        Node<Item> prev = this.last.prev;
        this.last.item = null;
        this.last.prev = null;
        this.last = prev;

        if (prev != null) prev.next = null;
        else this.first = null;

        --size;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeItr();
    }

    private class DequeItr implements Iterator<Item> {
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
}
