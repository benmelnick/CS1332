import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>> {

    public static final int INITIAL_CAPACITY = 13;

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field provided. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
        size = 0;
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The data in the backingArray should be in the same order as it appears
     * in the ArrayList before you start the Build Heap Algorithm.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MinHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot build heap "
                + "with a null list.");
        }
        backingArray = (T[]) (new Comparable[2 * data.size() + 1]);
        for (T item : data) {
            if (item == null) {
                throw new IllegalArgumentException("Lists in a "
                        + "build a heap cannot contain null data.");
            }
            //populate array
            backingArray[size + 1] = item;
            size++;
        }
        //heapify down on all nodes starting with the end
        for (int i = size / 2; i > 0; i--) {
            T item = backingArray[i];
            heapifyDown(item, i, i * 2, i * 2 + 1);
        }
    }

    /**
     * Adds an item to the heap. If the backing array is full and you're trying
     * to add a new item, then double its capacity.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot add "
                + "null data.");
        }
        if (size == backingArray.length - 1) {
            //resize at length - 1 b/c array
            // will never be full (indx 0 always empty)
            resize();
        }
        if (size == 0) {
            backingArray[1] = item;
            size++;
        } else {
            backingArray[size + 1] = item;
            //heapify up
            int parentIndex = (size + 1) / 2;
            int itemIndex = size + 1;
            heapifyUp(item, parentIndex, itemIndex);
            size++;
        }
    }

    /**
     * recursive helper method to add
     * performs heapify up operation
     * @param item item to add to heap
     * @param indexP index of parent item's parent
     * @param indexI index of item
     */
    private void heapifyUp(T item, int indexP, int indexI) {
        if (indexP == 0) {
            //base case: reached top of tree
            return;
        }
        if (backingArray[indexP].compareTo(item) < 0) {
            //base case: parent less than item
            return;
        } else if (backingArray[indexP].compareTo(item) > 0) {
            //parent greater than item
            //swap and check again
            swap(item, indexP, indexI);
            heapifyUp(item, indexP / 2, indexI / 2);
        }
    }

    /**
     * resizes backing array to twice the current length
     */
    private void resize() {
        T[] newArr = (T[]) (new Comparable[backingArray.length * 2]);
        for (int i = 0; i < backingArray.length; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }

    /**
     * Removes and returns the min item of the heap. Null out all elements not
     * existing in the heap after this operation. Do not decrease the capacity
     * of the backing array.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the removed item
     */
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty.");
        }
        T removed = backingArray[1];
        backingArray[1] = backingArray[size];
        heapifyDown(backingArray[1], 1, 2, 3);
        backingArray[size] = null;
        size--;
        return removed;
    }

    /**
     * recursive helper method to remove
     * performs heapify down operation
     * @param item item to move down the min heap
     * @param indexI index of item
     * @param indexR index of item's right child
     * @param indexL index of item's left child
     */
    private void heapifyDown(T item, int indexI, int indexL, int indexR) {
        if (indexI == size || indexL >= backingArray.length
                || indexR >= backingArray.length) {
            //base case: end of heap
            //need this case when left/right index out of bounds of array
            return;
        }
        if (backingArray[indexL] == null && backingArray[indexR] == null) {
            //base case: reached bottom level
            return;
        }
        //edge case: element w/ only one child
        if (backingArray[indexL] == null) {
            //only need to check right element
            if (backingArray[indexI].compareTo(backingArray[indexR]) > 0) {
                //swap, then return
                swap(item, indexR, indexI);
            }
            return;
        } else if (backingArray[indexR] == null) {
            //only need to check left element
            if (backingArray[indexI].compareTo(backingArray[indexL]) > 0) {
                swap(item, indexL, indexI);
            }
            return;
        }
        if (backingArray[indexI].compareTo(backingArray[indexL]) < 0
                && backingArray[indexI].compareTo(backingArray[indexR]) < 0) {
            //base case: order satisfied
            return;
        } else {
            //swapping necessary - swap w/ smaller child
            if (backingArray[indexR].compareTo(backingArray[indexL]) > 0) {
                swap(item, indexL, indexI);
                indexI = indexL;
            } else {
                swap(item, indexR, indexI);
                indexI = indexR;
            }
            heapifyDown(item, indexI, indexI * 2, indexI * 2 + 1);
        }
    }

    /**
     * private helper method to swap indices in backing array
     * @param item element to be swapped w/ element at indexB
     * @param indexA index of item
     * @param indexB index of element b
     */
    private void swap(T item, int indexA, int indexB) {
        T temp = backingArray[indexA];
        backingArray[indexA] = item;
        backingArray[indexB] = temp;
    }

    /**
     * Returns the minimum element in the heap.
     *
     * @return the minimum element, null if the heap is empty
     */
    public T getMin() {
        if (size == 0) {
            return null;
        }
        return backingArray[1];
    }

    /**
     * Returns if the heap is empty or not.
     *
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap and returns array to {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        size = 0;
        backingArray = (T[]) (new Comparable[INITIAL_CAPACITY]);
    }

    /**
     * THIS METHOD IS ONLY FOR TESTING PURPOSES.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the number of elements in the heap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Return the backing array for the heap.
     *
     * For grading purposes only. DO NOT USE THIS METHOD IN YOUR CODE!
     *
     * @return the backing array for the heap
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}