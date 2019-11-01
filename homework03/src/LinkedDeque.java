import java.util.NoSuchElementException;

/**
 * Your implementation of a linked deque.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class LinkedDeque<T> {
    // Do not add new instance variables and do not add a new constructor.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    /**
     * Adds the data to the front of the deque.
     *
     * This method must run in O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null"
                    + " data to deque.");
        }
        if (head == null) {
            head = new LinkedNode<T>(null, data, null);
            tail = head;
        } else {
            LinkedNode<T> node = new LinkedNode(null, data, head);
            head.setPrevious(node);
            head = node;
        }
        size++;
    }

    /**
     * Adds the data to the back of the deque.
     *
     * This method must run in O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null"
                    + " data to deque.");
        }
        if (head == null) {
            head = new LinkedNode<T>(null, data, null);
            tail = head;
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(tail, data, null);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes the data at the front of the deque.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("There is no data to remove.");
        }
        if (size == 1) {
            //only one node in the list
            LinkedNode<T> removed = head;
            head = null;
            tail = null;
            size = 0;
            return removed.getData();
        }
        LinkedNode<T> removed = head;
        LinkedNode<T> newHead = head.getNext();
        newHead.setPrevious(null);
        head = newHead;
        size--;
        return removed.getData();
    }

    /**
     * Removes the data at the back of the deque.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("There is no data to remove.");
        }
        if (size == 1) {
            LinkedNode<T> removed = head;
            head = null;
            tail = null;
            size = 0;
            return removed.getData();
        }
        LinkedNode<T> removed = tail;
        LinkedNode<T> newTail = tail.getPrevious();
        newTail.setNext(null);
        tail = newTail;
        size--;
        return removed.getData();
    }

    /**
     * Returns the number of elements in the deque.
     *
     * Runs in O(1) for all cases.
     * 
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    /**
     * Returns the head node of the linked deque.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the head of the linked deque
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the linked deque.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the tail of the linked deque
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}