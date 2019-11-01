/**
 * Your implementation of an ArrayList.
 *
 * @author Ben Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class ArrayList<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * The initial capacity of the array list.
     */
    public static final int INITIAL_CAPACITY = 13;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
    }

    /**
     * Adds the element to the index specified.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Adding to index {@code size} should be amortized O(1),
     * all other adds are O(n).
     *
     * @param index The index where you want the new element.
     * @param data The data to add to the list.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add at a "
                    + "negative index.");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Cannot add at "
                    + "an index greater than the current size.");
        }
        if (index == size) {
            backingArray[index] = data;
            size++;
        } else {
            if (size == backingArray.length) {
                backingArray = resize();
            }
            //shift all the elements that exist after index
            for (int i = size; i > index; i--) {
                backingArray[i] = backingArray[i - 1];
            }
            backingArray[index] = data;
            size++;
        }
    }

    /**
     * Add the given data to the front of your array list.
     *
     * Remember that this add may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data.");
        }
        if (size == backingArray.length) {
            backingArray = resize();
        }
        for (int i = size; i > 0; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        backingArray[0] = data;
        size++;
    }

    /**
     * Add the given data to the back of your array list.
     *
     * Must be amortized O(1).
     *
     * @param data The data to add to the list.
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null "
                    + "data to a list.");
        }
        if (size == backingArray.length) {
            backingArray = resize();
        }
        backingArray[size] = data;
        size++;
    }

    /**
     *  private helper method that resizes the backing
     *  array to twice its current length
     * @return resized array that contains data from original array
     */
    private T[] resize() {
        T[] newArray = (T[]) (new Object[backingArray.length * 2]);
        for (int i = 0; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }
        return newArray;
    }

    /**
     * Removes and returns the element at index.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * This method should be O(1) for index {@code size - 1} and O(n) in
     * all other cases.
     *
     * @param index The index of the element
     * @return The object that was formerly at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add at "
                    + "a negative index.");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot add at "
                    + "an index greater than the current size.");
        }
        T removed = backingArray[index];
        if (index == size - 1) {
            backingArray[index] = null;
        } else {
            backingArray[index] = null;
            for (int i = index; i < size; i++) {
                backingArray[i] = backingArray[i + 1];
            }
        }
        size--;
        return removed;
    }

    /**
     * Remove the first element in the list and return it.
     *
     * Remember that this remove may require elements to be shifted.
     *
     * Must be O(n).
     *
     * @return The data from the front of the list or null if the list is empty
     */
    public T removeFromFront() {
        if (size > 0) {
            T removed = backingArray[0];
            backingArray[0] = null;
            for (int i = 0; i < size; i++) {
                backingArray[i] = backingArray[i + 1];
            }
            size--;
            return removed;
        }
        return null;
    }

    /**
     * Remove the last element in the list and return it.
     *
     * Must be O(1).
     *
     * @return The data from the back of the list or null if the list is empty
     */
    public T removeFromBack() {
        T removed = null;
        if (size > 0) {
            removed = backingArray[size - 1];
            backingArray[size - 1] = null;
            size--;
        }
        return removed;
    }

    /**
     * Returns the element at the given index.
     *
     * Must be O(1).
     *
     * @param index The index of the element
     * @return The data stored at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size
     */
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Cannot add at "
                    + "a negative index.");
        }
        if (index >= size) {
            throw new IndexOutOfBoundsException("Cannot add at "
                    + "an index greater than the current size.");
        }
        return backingArray[index];
    }

    /**
     * Return a boolean value representing whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clear the list. Reset the backing array to a new array of the initial
     * capacity.
     *
     * Must be O(1).
     */
    public void clear() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Return the size of the list as an integer.
     *
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Return the backing array for this list.
     *
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the backing array for this list
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
