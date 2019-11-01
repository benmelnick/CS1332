import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
/**
 * Min-Heap test cases.
 *
 * @author Joshua Dierberger
 * @version 1.0
 */
public class BetterHeapTests {

    private static final int TIMEOUT = 200;
    // original seed: 278133198784L
    private static final long RANDSEED = 278133198784L;
    private static final int EXCESSIVE_NUMBER = Short.MAX_VALUE * 16;
    private static final boolean VERBOSE = false; // will slow a lot 

    private MinHeap<Integer> minHeap;
    private Random rand = new Random(RANDSEED);

    @BeforeClass
    public static void printInfo() {
        System.out.println("Min-heap better tests. "
                + "Supplementary to student tests."
                + " Student tests included with these tests.");
        System.out.println("Randomization seed: " + RANDSEED);
        System.out.println("Tests should not time out");
        System.out.println("******************************************");
    }

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test()
    public void testExcessivelyManyAddsAndRemoves() {
        System.out.println("*** Testing lots of adds and removes ***");
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        System.out.printf("Adding %d numbers...%n", EXCESSIVE_NUMBER);
        for (int i = 0; i < EXCESSIVE_NUMBER; i++) {
            int r = rand.nextInt();
            pq.add(r);
            minHeap.add(r);
            if (VERBOSE) {
                System.out.printf(
                        "Add (minHeap size, pq size): %11d (%7d, %7d) %n",
                        r, minHeap.size(), pq.size());
            }
        }
        System.out.println("Removing all elements...");
        int n = 0;
        while (!pq.isEmpty() && !minHeap.isEmpty()) {
            int el1 = pq.remove();
            int el2 = minHeap.remove();
            if (VERBOSE) {
                System.out.printf("%dth remove (minHeap, pq): (%d, %d)",
                        n, el1, el2);
                System.out.printf("  MinHeap size: %d", minHeap.size());
                System.out.printf("  pq size: %d%n", pq.size());
            }
            if (el1 != el2) {
                failTest(String.format(
                        "Expected %dth element to be %d but got %d",
                        n, el1, el2));
            }
            n++;
        }
        if (pq.isEmpty() != minHeap.isEmpty()) {
            failTest(String.format("MinHeap is %s but priority queue is %s.",
                    minHeap.isEmpty() ? "empty" : "not empty",
                    pq.isEmpty() ? "empty" : "not empty"));
        }
        System.out.println("*** Finished testing excessive adds/removes ***");
    }

    @Test()
    public void testExceptions() {
        System.out.println("*** Testing exceptions ***");
        try {
            minHeap = new MinHeap<>(null);
            failTest("Expected exception constructing MinHeap with null "
                    + "ArrayList, got none.");
        } catch (IllegalArgumentException iae) {
            if (!iae.getMessage().contains("null")) {
                failTest("Exception message for null ArrayList"
                        + " doesn't mention \"null\".");
            }
        } catch (Throwable t) {
            failTest(String.format(
                    "Expected exception %s for null ArrayList, got %s.",
                    "IllegalArgumentException",
                    t.getClass().getSimpleName()));
        }
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(1);
            list.add(null);
            list.add(2);
            minHeap = new MinHeap<>(list);
            failTest("Expected exception constructing MinHeap with ArrayList "
                    + "containing null, got none.");
        } catch (IllegalArgumentException iae) {
            if (!iae.getMessage().contains("null")) {
                failTest("Exception message for ArrayList containing null"
                        + " doesn't mention \"null\".");
            }
        } catch (Throwable t) {
            failTest(String.format(
                    "Expected exception %s for ArrayList containing null, got %s.",
                    "IllegalArgumentException",
                    t.getClass().getSimpleName()));
        }
        try {
            minHeap = new MinHeap<>();
            minHeap.add(null);
            failTest("Expected exception adding null, got none.");
        } catch (IllegalArgumentException iae) {
            if (!iae.getMessage().contains("null")) {
                failTest("Exception message for adding null doesn't mention "
                        + "\"null\".");
            }
        } catch (Throwable t) {
            failTest(String.format(
                    "Expected exception %s for adding null, got %s.",
                    "IllegalArgumentException",
                    t.getClass().getSimpleName()));
        }
        try {
            minHeap = new MinHeap<>();
            minHeap.remove();
            failTest("Expected exception removing when empty, got none.");
        } catch (NoSuchElementException nsee) {
            if (!nsee.getMessage().contains("empty")) {
                failTest("Exception message removing when empty doesn't "
                        + "mention \"empty\".");
            }
        } catch (Throwable t) {
            failTest(String.format(
                    "Expected exception %s for removing when empty, got %s.",
                    "NoSuchElementException",
                    t.getClass().getSimpleName()));
        }
        System.out.println("*** Finished testing execptions ***");
    }

    @Test()
    public void testGetMin() {
        System.out.println("*** Testing getMin ***");
        minHeap = new MinHeap<>();
        Integer dat = minHeap.getMin();
        if (dat != null) {
            failTest("getMin() should return null when heap is empty, not "
                    + dat + ".");
        }
        minHeap.add(1);
        dat = minHeap.getMin();
        if (!dat.equals(Integer.valueOf(1))) {
            failTest("getMin did not return only value added.");
        }
        dat = minHeap.getMin();
        if (!dat.equals(Integer.valueOf(1))) {
            failTest("getMin did not return same value as previous call when"
                    + " no addition adds or removes happened.");
        }
        minHeap.add(-1);
        dat = minHeap.getMin();
        if (!dat.equals(Integer.valueOf(-1))) {
            failTest("getMin did not return -1 in heap of 1, -1");
        }
        minHeap.remove();
        minHeap.remove();
        dat = minHeap.getMin();
        if (dat != null) {
            failTest("getMin() should return null when heap is empty, not "
                    + dat + ".");
        }
        System.out.println("*** Finished testing getMin ***");
    }

    @Test()
    public void testIsEmpty() {
        System.out.println("*** Testing isEmpty ***");
        minHeap = new MinHeap<>();
        if (!minHeap.isEmpty()) {
            failTest("isEmpty returns false on new heap.");
        }
        minHeap.add(1);
        if (minHeap.isEmpty()) {
            failTest("isEmpty returns true after 1 add.");
        }
        minHeap.getMin();
        if (minHeap.isEmpty()) {
            failTest("isEmpty returns true after 1 add, 1 getMin.");
        }
        minHeap.remove();
        if (!minHeap.isEmpty()) {
            failTest("isEmpty returns false after 1 remove.");
        }
        System.out.println("*** Finished testing isEmpty ***");
    }

    @Test()
    public void testClear() {
        System.out.println("*** Testing clear ***");
        minHeap = new MinHeap<>();
        minHeap.add(1);
        minHeap.add(2);
        minHeap.clear();
        if (!minHeap.isEmpty()) {
            failTest("isEmpty false after clear.");
        }
        if (minHeap.getBackingArray() == null
                || minHeap.getBackingArray().length
                != MinHeap.INITIAL_CAPACITY) {
            failTest("backingArray not returned to initial capacity after "
                    + "clear.");
        }
        if (minHeap.size() != 0) {
            failTest("size() is non-zero after clear.");
        }
        System.out.println("*** Finished testing clear ***");
    }

    /**
     * Fail & print.
     * @param s String to print.
     */
    public static void failTest(String s) {
        System.out.println(s);
        fail(s);
    }

}