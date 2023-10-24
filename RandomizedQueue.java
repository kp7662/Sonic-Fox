import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    /*
    @citation Adapted from: https://algs4.cs.princeton.edu/13stacks/ResizingArrayStack.java.html
     * princeton/cs/algs4/ResizingArrayStack.java. Accessed 25/09/2022.

    @citation Adapted from: https://edstem.org/us/courses/27701/lessons/43921/slides/253064
     * princeton/cs/algs4/ResizingArrayQueue.java. Accessed 25/09/2022.

    @citation Adapted from: https://drive.google.com/file/d/1K4OqXqFkpY3du6jgoeVdO1z15CJus-Ll/view
     * 1.3 Stacks and Queues Lecture Notes. Accessed 25/09/2022.

    @citation Adapted from: https://drive.google.com/file/d/16Liqsg0DjT1bGOgcGabRuOmnk95oxqVj/view
     * 1.4 Advanced Java Lecture Notes. Accessed 25/09/2022.

     @citation Adapted from: Sedgewick, Robert, and Kevin Wayne. Algorithms 4th Edition.
     Addison-Wesley Professional, 2011, pp. 141.
     */

    private Item[] queue; // array of items
    private int n; // number of elements on queue

    // construct an empty randomized queue
    public RandomizedQueue() {
        queue = (Item[]) new Object[1];
        n = 0;
    }

    // validate if the randomized queue is empty
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // move queue to a new array of size max
    /*
    @citation Adapted from: Sedgewick, Robert, and Kevin Wayne. Algorithms 4th Edition.
    Addison-Wesley Professional, 2011, pp. 141.
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < n; i++) {
            temp[i] = queue[i];
        }
        queue = temp;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }

        // if array is full, double its capacity
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException();
        }

        int k = StdRandom.uniform(n);
        Item item = queue[k];
        /* Since we do not care about the order of the elements in the resizing
        array, we can exchange the last element in the resizing array with the
        item to be removed and set the last element to null to avoid loitering.
         */
        queue[k] = queue[n - 1];
        queue[n - 1] = null; // to avoid loitering
        n--;

        // shrink size of array if necessary
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);

        return item;
    }
    /* @end-citation */

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException();
        }
        int k = StdRandom.uniform(n);
        Item item = queue[k];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // an iterator
    public class RandomizedQueueIterator implements Iterator<Item> {
        private Item[] temp; // array for shuffling
        private int i; // position of element in array

        // copy the queue to a temporary array to avoid mutation to the queue
        public RandomizedQueueIterator() {
            temp = (Item[]) new Object[n];
            i = 0;
            for (int j = 0; j < n; j++) {
                temp[j] = queue[j];
            }
            StdRandom.shuffle(temp);
        }

        public boolean hasNext() {
            return i <= n - 1;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = temp[i++];
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("+")) {
                String item = StdIn.readString();
                randomizedQueue.enqueue(item);
            }

            else if (s.equals("-")) {
                String item = randomizedQueue.dequeue();
                StdOut.println(item);
            }

            else if (s.equals("s")) {
                String item = randomizedQueue.sample();
                StdOut.println(item);
            }

            else if (s.equals("p")) {
                for (String item : randomizedQueue)
                    StdOut.println(item);
            }

            else if (s.equals("#")) {
                StdOut.println(randomizedQueue.size());
            }

            else if (s.equals("?")) {
                StdOut.println(randomizedQueue.isEmpty());
            }
        }
    }
}
