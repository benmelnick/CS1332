/**
 * Node class used for implementing your LinkedDeque.
 *
 * DO NOT MODIFY THIS FILE!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class LinkedNode<T> {
    private T data;
    private LinkedNode<T> previous;
    private LinkedNode<T> next;

    /**
     * Creates a new LinkedNode with the given T object and node references.
     *
     * @param previous the previous node in the list
     * @param data the data stored in the new node
     * @param next the next node in the list
     */
    public LinkedNode(LinkedNode<T> previous, T data,
            LinkedNode<T> next) {
        this.previous = previous;
        this.data = data;
        this.next = next;
    }

    /**
     * Creates a new LinkedNode with only the given T object.
     *
     * @param data the data stored in the new node
     */
    public LinkedNode(T data) {
        this(null, data, null);
    }

    /**
     * Gets the data stored in the node.
     *
     * @return the data in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Gets the next node.
     *
     * @return the next node
     */
    public LinkedNode<T> getNext() {
        return next;
    }

    /**
     * Sets the next node.
     *
     * @param next the new next node
     */
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    /**
     * Gets the previous node.
     *
     * @return the previous node
     */
    public LinkedNode<T> getPrevious() {
        return previous;
    }

    /**
     * Sets the previous node.
     *
     * @param previous the new previous node
     */
    public void setPrevious(LinkedNode<T> previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Node containing: " + data;
    }
}