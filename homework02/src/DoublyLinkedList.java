/**
 * Your implementation of a non-circular doubly linked list with a tail pointer.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class DoublyLinkedList<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    /**
     * Adds the element to the index specified.
     *
     * Adding to indices 0 and {@code size} should be O(1), all other cases are
     * O(n).
     *
     * @param index the requested index for the new element
     * @param data the data for the new element
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add "
                    + "at a negative index.");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot add at "
                    + "an index greater than size.");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            //get the node just before the current index
            LinkedListNode<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            //create new node and link to list
            LinkedListNode<T> newNode = new LinkedListNode(data);
            newNode.setPrevious(current);
            newNode.setNext(current.getNext());
            current.getNext().setPrevious(newNode);
            current.setNext(newNode);
            size++;
        }
    }

    /**
     * Adds the element to the front of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (head == null) {
            head = new LinkedListNode<T>(null, data, null);
            tail = head;
        } else {
            LinkedListNode<T> node = new LinkedListNode(null, data, head);
            head.setPrevious(node);
            head = node;
        }
        size++;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Must be O(1) for all cases.
     *
     * @param data the data for the new element
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (head == null) {
            head = new LinkedListNode(null, data, null);
            tail = head;
        } else {
            LinkedListNode<T> node = new LinkedListNode(tail, data, null);
            tail.setNext(node);
            tail = node;
        }
        size++;
    }

    /**
     * Removes and returns the element from the index specified.
     *
     * Removing from index 0 and {@code size - 1} should be O(1), all other
     * cases are O(n).
     *
     * @param index the requested index to be removed
     * @return the data formerly located at index
     * @throws java.lang.IndexOutOfBoundsException if index is negative or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot remove "
                    + "data at a negative index.");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot remove data at index "
                    + index + " in a list of size " + size);
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            LinkedListNode<T> prevNode = current.getPrevious();
            LinkedListNode<T> nextNode = current.getNext();
            //link nodes on either sides of node to remove
            prevNode.setNext(nextNode);
            nextNode.setPrevious(prevNode);
            size--;
            return current.getData();
        }
    }

    /**
     * Removes and returns the element at the front of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the front, null if empty list
     */
    public T removeFromFront() {
        if (head == null) {
            return null;
        }
        if (size == 1) {
            //only one node in the list
            LinkedListNode<T> removed = head;
            head = null;
            tail = null;
            size = 0;
            return removed.getData();
        }
        LinkedListNode<T> removed = head;
        LinkedListNode<T> temp = head.getNext();
        temp.setPrevious(null);
        head = temp;
        size--;
        return removed.getData();
    }

    /**
     * Removes and returns the element at the back of the list. If the list is
     * empty, return {@code null}.
     *
     * Must be O(1) for all cases.
     *
     * @return the data formerly located at the back, null if empty list
     */
    public T removeFromBack() {
        if (head == null) {
            return null;
        }
        if (size == 1) {
            //only one node in the list
            LinkedListNode<T> removed = head;
            head = null;
            tail = null;
            size = 0;
            return removed.getData();
        }
        LinkedListNode<T> removed = tail;
        LinkedListNode<T> temp = tail.getPrevious();
        temp.setNext(null);
        tail = temp;
        size--;
        return removed.getData();
    }

    /**
     * Returns the index of the last occurrence of the passed in data in the
     * list or -1 if it is not in the list.
     *
     * If data is in the tail, should be O(1). In all other cases, O(n).
     *
     * @param data the data to search for
     * @throws java.lang.IllegalArgumentException if data is null
     * @return the index of the last occurrence or -1 if not in the list
     */
    public int lastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search "
                + "for index of null data.");
        }
        for (int i = size; i > 0; i--) {
            if (this.get(i - 1).equals(data)) {
                return i - 1;
            }
        }
        return -1;
    }

    /**
     * Returns the element at the specified index.
     *
     * Getting the head and tail should be O(1), all other cases are O(n).
     *
     * @param index the index of the requested element
     * @return the object stored at index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot retrieve "
                    + "data at a negative index.");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot retrieve data at index "
                    + index + " in a list of size " + size);
        }
        if (index == 0) {
            return head.getData();
        } else if (index == size - 1) {
            return tail.getData();
        } else {
            LinkedListNode<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getData();
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return an array of length {@code size} holding all of the objects in
     * this list in the same order from head to tail
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            arr[i] = current.getData();
            current = current.getNext();
        }
        return arr;
    }

    /**
     * Returns a boolean value indicating if the list is empty.
     *
     * Must be O(1) for all cases.
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list of all data and resets the size.
     *
     * Must be O(1) for all cases.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Returns the number of elements in the list.
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
     * Returns the head node of the linked list.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the head of the linked list
     */
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail node of the linked list.
     * Normally, you would not do this, but it's necessary for testing purposes.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return node at the tail of the linked list
     */
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}