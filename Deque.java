import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    /*
    @citation Adapted from: https://algs4.cs.princeton.edu/13stacks/LinkedStack.java.html
     * princeton/cs/algs4/LinkedStack.java. Accessed 25/09/2022.

    @citation Adapted from: https://edstem.org/us/courses/27701/lessons/43921/slides/253063
     * princeton/cs/algs4/LinkedStack.java. Accessed 25/09/2022.

    @citation Adapted from: https://drive.google.com/file/d/1K4OqXqFkpY3du6jgoeVdO1z15CJus-Ll/view
     * 1.3 Stacks and Queues Lecture Notes. Accessed 25/09/2022.

    @citation Adapted from: https://drive.google.com/file/d/16Liqsg0DjT1bGOgcGabRuOmnk95oxqVj/view
     * 1.4 Advanced Java Lecture Notes. Accessed 25/09/2022.
     */

    private int n; // size of the linked list
    private Node first; // first node
    private Node last; // last node

    // helper linked list class
    private class Node {
        private Item item; // content of node
        private Node next; // adress pointer of the next node
        private Node prev; // address pointer of the previous node
    }

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // validate if the deque is empty
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldFirst = first;
        first = new Node();
        first.item = item; // save item to return

        // corner case to deal with empty deque
        if (n == 0) {
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        first.prev = null;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        Node oldLast = last;
        last = new Node();
        last.item = item; // save item to return

        // corner case to deal with empty deque
        if (n == 0) {
            first = last;
        }
        else {
            oldLast.next = last;
            last.prev = oldLast;
        }
        first.prev = null;
        last.next = null;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (n == 0) {
            throw new NoSuchElementException();
        }

        Item item = first.item; // save item to return
        first = first.next; // delete first node
        n--; // reduce size by 1

        // corner case to remove down to an empty Deque
        if (n == 0) {
            first = null;
            last = null;
        }
        else {
            first.prev = null;
        }
        return item; // return the saved item
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (n == 0) {
            throw new NoSuchElementException();
        }

        Item item = last.item; // save item to return
        last = last.prev; // delete last node
        n--; // reduce size by 1

        // corner case to remove down to an empty Deque
        if (n == 0) {
            first = null;
            last = null;
        }
        else {
            last.next = null;
        }
        return item;
    }

    // return an iterator over items in order from first node to last node
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // an iterator
    private class DequeIterator implements Iterator<Item> {
        /*
        @citation Adapted from: https://algs4.cs.princeton.edu/13stacks/LinkedStack.java.html
        * princeton/cs/algs4/LinkedStack.java. Accessed 25/09/2022.
        */

        private Node current = first; // set current pointer to first node

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
        /* @end-citation */
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque = new Deque<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+")) {
                String item = StdIn.readString();
                deque.addFirst(item);
            }
            else if (s.equals("++")) {
                String item = StdIn.readString();
                deque.addLast(item);
            }
            else if (s.equals("-")) {
                String item = deque.removeFirst();
                StdOut.println(item);
            }
            else if (s.equals("--")) {
                String item = deque.removeLast();
                StdOut.println(item);
            }
            else if (s.equals("p")) {
                for (String item : deque)
                    StdOut.println(item);
            }
            else if (s.equals("#")) {
                StdOut.println(deque.size());
            }
            else if (s.equals("?")) {
                StdOut.println(deque.isEmpty());
            }
        }
    }
}
