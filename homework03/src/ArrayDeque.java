import java.util.NoSuchElementException;

/**
 * Your implementation of an array deque.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class ArrayDeque<T> {
    /**
     * The initial capacity of the ArrayDeque.
     */
    public static final int INITIAL_CAPACITY = 11;

    // Do not add new instance variables.
    private T[] backingArray;
    private int front; //represents index of front element
    private int back; //represents index DIRECTLY AFTER back element
    private int size;

    /**
     * Constructs a new ArrayDeque with an initial capacity of
     * the {@code INITIAL_CAPACITY} constant above.
     */
    public ArrayDeque() {
        backingArray = (T[]) (new Object[INITIAL_CAPACITY]);
        front = 0;
        back = 0;
        size = 0;
    }

    /**
     * Adds the data to the front of the deque.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current capacity. If a regrow is necessary,
     * you should copy elements to the beginning of the new array and reset
     * front to the beginning of the array (and move {@code back}
     * appropriately). After the regrow, the new data should be at index 0 of
     * the array.
     *
     * This method must run in amortized O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addFirst(T data) {
        //add directly before the front index
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null"
                    + " data to deque.");
        }
        if (size == backingArray.length) {
            resizeFront();
        }
        //adjust front index before inserting data
        front = mod(--front, backingArray.length);
        backingArray[front] = data;
        size++;
    }

    /**
     * Adds the data to the back of the deque.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current capacity. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to the beginning of the array (and move {@code back}
     * appropriately).
     *
     * This method must run in amortized O(1) time.
     *
     * @param data the data to add to the deque
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addLast(T data) {
        //add at the back index
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null"
                    + " data to deque.");
        }
        if (size == backingArray.length) {
            resizeBack();
        }
        backingArray[back] = data;
        //adjust back index after inserting data
        back = mod(++back, backingArray.length);
        size++;
    }

    /**
     * resizing when attempting to add to front
     * resizes the array to twice its current length
     * re-positions elements in the array beginning w/ front index
     *  to back index
     * resets front to 1 and back to size + 1
     */
    private void resizeFront() {
        //put values in order from front to back
        //front value will now exist at index 1
        //index 0 left blank for value to be added
        T[] newArray = (T[]) (new Object[backingArray.length * 2]);
        for (int i = 1; i <= backingArray.length; i++) {
            int index = mod(front++, backingArray.length);
            newArray[i] = backingArray[index];
        }
        front = 1;
        back = size + 1;
        backingArray = newArray;
    }

    /**
     * resizing when attempting to add to back
     * resizes the array to twice its current length
     * re-positions elements in the array by arranging array from front index
     *  to back index
     * resets front to 0 and back to size
     */
    private void resizeBack() {
        //put values in order from front to back
        //front value will now exist at index 0
        //new data added at back index
        T[] newArray = (T[]) (new Object[backingArray.length * 2]);
        for (int i = 0; i < backingArray.length; i++) {
            int index = mod(front++, backingArray.length);
            newArray[i] = backingArray[index];
        }
        //when resizing then adding to back, front is set to 0
        front = 0;
        back = size;
        backingArray = newArray;
    }

    /**
     * Removes the data at the front of the deque.
     *
     * Do not shrink the backing array.
     *
     * If the deque becomes empty as a result of this call, you should
     * explicitly reset front and back to the beginning of the array.
     *
     * You should replace any spots that you remove from with null. Failure to
     * do so will result in a major loss of points.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the front of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeFirst() {
        //remove the element at the front index
        if (size == 0) {
            throw new NoSuchElementException("There is no data to remove.");
        }
        T removed = backingArray[front];
        backingArray[front] = null;
        if (size == 1) {
            //last element in the deque
            front = 0;
            back = 0;
            size = 0;
        } else {
            front = mod(++front, backingArray.length);
            size--;
        }
        return removed;
    }

    /**
     * Removes the data at the back of the deque.
     *
     * Do not shrink the backing array.
     *
     * If the deque becomes empty as a result of this call, you should
     * explicitly reset front and back to the beginning of the array.
     *
     * You should replace any spots that you remove from with null. Failure to
     * do so will result in a major loss of points.
     *
     * This method must run in O(1) time.
     *
     * @return the data formerly at the back of the deque
     * @throws java.util.NoSuchElementException if the deque is empty
     */
    public T removeLast() {
        //remove the element directly before the back index
        if (size == 0) {
            throw new NoSuchElementException("There is no data to remove.");
        }
        //get the index before the back index
        int index = mod(back - 1, backingArray.length);
        T removed = backingArray[index];
        backingArray[index] = null;
        if (size == 1) {
            //last element in the deque
            front = 0;
            back = 0;
            size = 0;
        } else {
            //move back to spot that was just removed
            back = index;
            size--;
        }
        return removed;
    }

    /**
     * Returns the smallest non-negative remainder when dividing {@code index}
     * by {@code modulo}. So, for example, if modulo is 5, then this method will
     * return either 0, 1, 2, 3, or 4, depending on what the remainder is.
     *
     * This differs from using the % operator in that the % operator returns
     * the smallest answer with the same sign as the dividend. So, for example,
     * (-5) % 6 => -5, but with this method, mod(-5, 6) = 1.
     *
     * Examples:
     * mod(-3, 5) => 2
     * mod(11, 6) => 5
     * mod(-7, 7) => 0
     *
     * DO NOT MODIFY THIS METHOD. This helper method is here to make the math
     * part of the circular behavior easier to work with.
     *
     * @param index the number to take the remainder of
     * @param modulo the divisor to divide by
     * @return the remainder in its smallest non-negative form
     * @throws java.lang.IllegalArgumentException if the modulo is non-positive
     */
    private static int mod(int index, int modulo) {
        // DO NOT MODIFY!
        if (modulo <= 0) {
            throw new IllegalArgumentException("The modulo must be positive.");
        } else {
            int newIndex = index % modulo;
            return newIndex >= 0 ? newIndex : newIndex + modulo;
        }
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
     * Returns the backing array of this deque.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}