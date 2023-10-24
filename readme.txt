Programming Assignment 2: Deques and Randomized Queues

/* *****************************************************************************
 *  Explain briefly how you implemented the randomized queue and deque.
 *  Which data structure did you choose (array, linked list, etc.)
 *  and why?
 **************************************************************************** */
For Dequeu, I implement a doubly-linked list instead of a singly-linked list
because of the limitation of a singly-linked list in executing removeLast(). In
order to execute remove last, I need to access the node before last, so having
a doubly-linked list allows me to achieve the goal easily. Having a doubly-
linked list also allow me to access the nodes at constant time.

For RandomizedQueue, I implement a resizing array that will grow and shrink
array when necessary. The size of the randomized queue doubles when the number
of elements in the queue equals its size. On the other hand, the size of the
randomized queue reduce by half when the number of elements in the queue equals
1/4 of its size. I choose to implement a resizing array because it provides
flexibility to the size of the queue (array) when the capacity of queue is not
provided by client.

/* *****************************************************************************
 *  How much memory (in bytes) do your data types use to store n items
 *  in the worst case? Use the 64-bit memory cost model from Section
 *  1.4 of the textbook and use tilde notation to simplify your answer.
 *  Briefly justify your answers and show your work.
 *
 *  Do not include the memory for the items themselves (as this
 *  memory is allocated by the client and depends on the item type)
 *  or for any iterators, but do include the memory for the references
 *  to the items (in the underlying array or linked list).
 **************************************************************************** */

Randomized Queue:   ~  8n  bytes
    Each of the element in an array uses ~8 bytes of memory, so an array of
    length n usues ~8n bytes of memory

Deque:              ~  64n  bytes

    private class Node {   // 16 (object overhead)
                           // 8 (inner class overhead)
        private Item item; // 8 (reference to Object)
                           // 16 (Object)
        private Node next; // 8 (reference)
        private Node prev; // 8 (reference)
    }


/* *****************************************************************************
 *  Known bugs / limitations.
 **************************************************************************** */
None.

/* *****************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **************************************************************************** */
Friday Lab TA



/* *****************************************************************************
 *  Describe any serious problems you encountered.
 **************************************************************************** */
None.

/* *****************************************************************************
 *  List any other comments here. Feel free to provide any feedback
 *  on how much you learned from doing the assignment, and whether
 *  you enjoyed doing it.
 **************************************************************************** */
This assignment is slightly challenging than the previous ones because it
requires very structured and logical thinking when implementing the APIs. I find
that drawing out the nodes are super helpful to understand how to add/remove a
node to the front/back. I also find the Lab TA to be super helpful in helping
me identifying the "bugs" in my thinking and guiding me to make my solution
better. I think this assignment is interesting. Thank you for marking my
solutions and please feel free to leave any comments/suggestions that can
improve my code.
