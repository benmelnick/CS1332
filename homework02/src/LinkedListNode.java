/**
 * Node class used for implementing your DoublyLinkedList.
 *
 * DO NOT ALTER THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class LinkedListNode<T> {
    private T data;
    private LinkedListNode<T> previous;
    private LinkedListNode<T> next;

    /**
     * Creates a new LinkedListNode with the given T object and node references.
     *
     * @param previous The previous node in the list.
     * @param data The data stored in the new node.
     * @param next The next node in the list.
     */
    public LinkedListNode(LinkedListNode<T> previous, T data,
                          LinkedListNode<T> next) {
        this.previous = previous;
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new LinkedListNode with only the given T object.
     *
     * @param data The data stored in the new node.
     */
    public LinkedListNode(T data) {
        this(null, data, null);
    }

    /**
     * Gets the previous node.
     *
     * @return The previous node.
     */
    public LinkedListNode<T> getPrevious() {
        return previous;
    }

    /**
     * Gets the data stored in the node.
     *
     * @return The data in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the next node.
     *
     * @return The next node.
     */
    public LinkedListNode<T> getNext() {
        return next;
    }

    /**
     * Sets the previous node.
     *
     * @param previous The new previous node.
     */
    public void setPrevious(LinkedListNode<T> previous) {
        this.previous = previous;
    }

    /**
     * Sets the next node.
     *
     * @param next The new next node.
     */
    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }


    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}