import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
/**
  * Simple test cases for a min heap.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author CS 1332 TAs
  * @version 1.0
  */
public class MinHeapStudentTests {

    private static final int TIMEOUT = 200;
    private MinHeap<Integer> minHeap;

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(35);
        passedIn.add(40);
        passedIn.add(30);
        passedIn.add(25);
        passedIn.add(10);

        Integer[] expected = new Integer[11];
        expected[1] = 10;
        expected[2] = 25;
        expected[3] = 30;
        expected[4] = 35;
        expected[5] = 40;

        minHeap = new MinHeap<>(passedIn);
        assertEquals(5, minHeap.size());
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        minHeap.add(43);
        minHeap.add(89);
        minHeap.add(17);
        minHeap.add(64);
        minHeap.add(5);

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY];
        expected[1] = 5;
        expected[2] = 17;
        expected[3] = 43;
        expected[4] = 89;
        expected[5] = 64;
        assertEquals(5, minHeap.size());
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer five = new Integer(5);
        minHeap.add(43);
        minHeap.add(89);
        minHeap.add(17);
        minHeap.add(64);
        minHeap.add(five);

        assertSame(five, minHeap.remove());
        assertEquals(new Integer(17), minHeap.remove());
        assertEquals(new Integer(43), minHeap.remove());
        assertEquals(2, minHeap.size());

        Integer[] expected = new Integer[MinHeap.INITIAL_CAPACITY];
        expected[1] = new Integer(64);
        expected[2] = new Integer(89);
        assertArrayEquals(expected, minHeap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testMiscellaneous() {
        assertEquals(true, minHeap.isEmpty());
        minHeap.add(43);
        minHeap.add(89);
        minHeap.add(17);
        minHeap.add(64);
        minHeap.add(5);

        assertEquals(false, minHeap.isEmpty());
        assertEquals((Integer) 5, minHeap.getMin());

        minHeap.clear();
        assertEquals(true, minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        assertArrayEquals(new Integer[MinHeap.INITIAL_CAPACITY],
            minHeap.getBackingArray());
    }
}