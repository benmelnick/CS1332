import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class Sorting {

    /**
     * private helper method for performing swaps in sorting methods
     * @param arr arr to swap items in
     * @param indexA first index
     * @param indexB second index
     * @param <T> data type to sort
     */
    private static <T> void swap(T[] arr, int indexA, int indexB) {
        T temp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = temp;
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null.");
        }
        //boolean tracker to see if swaps are needed
        boolean swapped = true;
        int start = 0;
        int end = arr.length - 1;
        while (start < end && swapped) {
            swapped = false;
            //forward bubble search
            int lastEndSwap = 0; //optimization: keeps track of last swap
            for (int k = start; k < end; k++) {
                if (comparator.compare(arr[k], arr[k + 1]) > 0) {
                    swap(arr, k, k + 1);
                    swapped = true;
                    lastEndSwap = k;
                }
            }
            end = lastEndSwap;
            if (swapped) {
                swapped = false;
                //backwards bubble search
                int lastFrontSwap = 0;
                for (int k = end; k > start; k--) {
                    if (comparator.compare(arr[k], arr[k - 1]) < 0) {
                        swap(arr, k, k - 1);
                        swapped = true;
                        lastFrontSwap = k;
                    }
                }
                start = lastFrontSwap;
            }
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null.");
        }
        for (int k = 1; k < arr.length; k++) {
            int n = k;
            while (n > 0 && comparator.compare(arr[n], arr[n - 1]) < 0) {
                //loop from k to 1, or until a swap is not needed
                swap(arr, n, n - 1);
                n--;
            }
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Note that there may be duplicates in the array.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null.");
        }
        for (int n = arr.length - 1; n > 0; n--) {
            int maxIndex = 0;
            //loop to find the max
            for (int i = 1; i <= n; i++) {
                if (comparator.compare(arr[i], arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }
            swap(arr, n, maxIndex);
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * You can create more arrays to run mergesort, but at the end,
     * everything should be merged back into the original T[]
     * which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Comparator is null.");
        }
        if (arr.length <= 1) {
            return;
        }
        //copy the two halves of the array
        int midIndex = arr.length / 2;
        T[] leftArr = (T[]) (new Object[midIndex]);
        for (int i = 0; i < midIndex; i++) {
            leftArr[i] = arr[i];
        }
        T[] rightArr = (T[]) (new Object[arr.length - midIndex]);
        for (int i = midIndex; i < arr.length; i++) {
            rightArr[i - midIndex] = arr[i];
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        //indices for merging
        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = 0;
        //merge data into original array only while both subarrays are not empty
        while (leftIndex < midIndex && rightIndex < arr.length - midIndex) {
            if (comparator.compare(leftArr[leftIndex],
                    rightArr[rightIndex]) <= 0) {
                arr[currentIndex] = leftArr[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArr[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }
        //handles emptying the other subarray if one became empty in first loop
        while (leftIndex < midIndex) {
            arr[currentIndex] = leftArr[leftIndex];
            leftIndex++;
            currentIndex++;
        }
        while (rightIndex < arr.length - midIndex) {
            arr[currentIndex] = rightArr[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * Do NOT use anything from the Math class except Math.abs
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort null array.");
        }
        int maxNum = arr[0];
        int maxDigits = 1;
        //find the greatest number and its number of digits
        for (int i = 0; i < arr.length; i++) {
            if (Math.abs(arr[i]) > maxNum) {
                maxNum = Math.abs(arr[i]);
            }
        }
        while (maxNum >= 10) {
            maxDigits++;
            maxNum = maxNum / 10;
        }

        //instantiate bucket array
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<Integer>();
        }

        int x = 1; //factor to divide numbers by
        for (int i = 0; i < maxDigits; i++) {
            for (Integer num : arr) {
                int currentDigit = (num / x) % 10 + 9;
                buckets[currentDigit].addLast(num);
            }
            int index = 0;
            for (int k = 0; k < buckets.length; k++) {
                for (Integer item : buckets[k]) {
                    arr[index++] = item;
                }
                buckets[k].clear();
            }
            x *= 10;
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots.
     * For example if you need a pivot between a (inclusive)
     * and b (exclusive) where b > a, use the following code:
     *
     * int pivotIndex = r.nextInt(b - a) + a;
     *
     * It should be:
     *  in-place
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null or k is not in the range of 1 to arr.length
     * @param <T> data type to sort
     * @param k the index to retrieve data from + 1 (due to 0-indexing) if
     *          the array was sorted; the 'k' in "kth select"; e.g. if k ==
     *          1, return the smallest element in the array
     * @param arr the array that should be modified after the method
     * is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @return the kth smallest element
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator,
                                  Random rand) {
        if (arr == null) {
            throw new IllegalArgumentException("Cannot sort a null array.");
        }
        if (comparator == null) {
            throw new IllegalArgumentException("Cannot sort with a "
                + "null comparator.");
        }
        if (rand == null) {
            throw new IllegalArgumentException("Cannot sort with a "
                + "null random.");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is not in "
                    + "the range of the array.");
        }
        return kthSelectHelper(k, arr, rand, comparator, 0, arr.length);
    }

    /**
     * private helper recursive method for kthselect
     * @param k the index to retrieve data from + 1 (due to 0-indexing) if
     *          the array was sorted; the 'k' in "kth select"; e.g. if k ==
     *          1, return the smallest element in the array
     * @param arr the array that should be modified after the method
     * is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param left left bound of subarray
     * @param right right bound of subarray
     * @param <T> data type to sort
     * @return the kth smallest element
     */
    private static <T> T kthSelectHelper(int k, T[] arr, Random rand,
                                         Comparator<T> comparator,
                                         int left, int right) {
        int pivotIndex = rand.nextInt(right - left) + left;
        T pivotVal = arr[pivotIndex];
        swap(arr, left, pivotIndex);
        int i = left + 1;
        int j = right - 1;
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], pivotVal) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], pivotVal) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, j, left);
        if (j == k - 1) {
            return arr[j];
        } else if (j > k - 1) {
            return kthSelectHelper(k, arr, rand, comparator, left, j);
        } else {
            return kthSelectHelper(k, arr, rand, comparator, j + 1, right);
        }
    }
}
