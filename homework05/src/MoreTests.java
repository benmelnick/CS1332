import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * MinHeap tests for CS1332 HW5 Fall 2018
 * @author Shishir
 * @version 2.0
 */
public final class MoreTests {
    private MinHeap<Integer> minHeap;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
    }

    @Test
    public void constructor1() {
        assertArrayEquals(
                new Integer[]{null, null, null, null, null, null, null, null,
                        null, null, null, null, null},
                minHeap.getBackingArray()
        );
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    public void constructor2() {
        minHeap = new MinHeap<>(
                Arrays.stream(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0})
                        .collect(ArrayList::new, List::add, List::addAll)
        );
        assertEquals(10, minHeap.size());
        assertFalse(minHeap.isEmpty());
        assertArrayEquals(
                new Integer[]{null, 0, 1, 3, 2, 5, 4, 7, 9, 6, 8, null, null,
                        null, null, null, null, null, null, null, null},
                minHeap.getBackingArray()
        );
    }

    @Test
    public void basicHeapTest1() {
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        minHeap.add(10);
        assertEquals(1, minHeap.size());
        assertEquals(10, minHeap.getBackingArray()[1]);
        minHeap.add(7);
        assertEquals(2, minHeap.size());
        assertEquals(10, minHeap.getBackingArray()[2]);
        minHeap.add(15);
        assertEquals(3, minHeap.size());

        assertArrayEquals(
                new Integer[]{null, 7, 10, 15, null, null, null, null, null,
                        null, null, null, null},
                minHeap.getBackingArray()
        );

        assertEquals(Integer.valueOf(7), minHeap.remove());

        assertArrayEquals(
                new Integer[]{null, 10, 15, null, null, null, null, null, null,
                        null, null, null, null},
                minHeap.getBackingArray()
        );

        assertEquals(Integer.valueOf(10), minHeap.remove());
        assertEquals(1, minHeap.size());
        assertEquals(15, minHeap.getBackingArray()[1]);
        assertArrayEquals(
                new Integer[]{null, 15, null, null, null, null, null, null,
                        null, null, null, null, null},
                minHeap.getBackingArray()
        );
        assertEquals(Integer.valueOf(15), minHeap.remove());
        assertEquals(0, minHeap.size());
        assertTrue(minHeap.isEmpty());
        assertArrayEquals(
                new Integer[]{null, null, null, null, null, null, null, null,
                        null, null, null, null, null},
                minHeap.getBackingArray()
        );
    }

    @Test
    public void basicHeapTest2() {
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        Stream.of(3, 1, 4, 15, 9, 2, 6, 5, 35, 8, 97, 93).forEach(minHeap::add);
        assertEquals(12, minHeap.size());
        assertFalse(minHeap.isEmpty());
        assertArrayEquals(
                new Integer[]{null, 1, 3, 2, 5, 8, 4, 6, 15, 35, 9, 97, 93},
                minHeap.getBackingArray()
        );
        Stream.of(1, 2, 3, 4, 5, 6, 8, 9, 15, 35, 93, 97).forEach(val ->
                assertEquals(val, minHeap.remove())
        );
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        assertArrayEquals(
                new Integer[]{null, null, null, null, null, null, null, null,
                        null, null, null, null, null},
                minHeap.getBackingArray()
        );
    }



    /**
     * Even if your code is correct, this test may fail if your heap cannot
     * handle duplicates. Since you are not expected to handle duplicates,
     * you can comment this test out if that is the reason why its failing
     */
    @Test
    public void largeInputSize() {
        Random random = new Random(1331);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        int size = 0xFFFF;

        for (int i = 0; i < size; i++) {
            int next = random.nextInt();
            minHeap.add(next);
            priorityQueue.add(next);
        }

        for (int i = 0; i < size; i++) {
            assertEquals(priorityQueue.remove(), minHeap.remove());
        }
    }

    /**
     * These are the cases where the initial capacity is pretty small due to
     * a small ArrayList being passed in
     */
    @Test
    public void smallInputSize() {
        // Capacity of 3
        minHeap = new MinHeap<>(new ArrayList<Integer>(){{add(1);}});
        assertArrayEquals(
                new Integer[] {null, 1, null},
                minHeap.getBackingArray()
        );
        assertFalse(minHeap.isEmpty());
        assertEquals(1, minHeap.size());
        assertEquals(Integer.valueOf(1), minHeap.getMin());
        assertEquals(Integer.valueOf(1), minHeap.remove());
        assertTrue(minHeap.isEmpty());
        minHeap.add(2);
        minHeap.add(3);
        assertArrayEquals(
                new Integer[] {null, 2, 3},
                minHeap.getBackingArray()
        );
        minHeap.add(4);
        assertArrayEquals(
                new Integer[] {null, 2, 3, 4, null, null},
                minHeap.getBackingArray()
        );

        // Capacity of 1
        minHeap = new MinHeap<>(new ArrayList<Integer>());
        assertTrue(minHeap.isEmpty());
        assertNull(minHeap.getMin());
        assertEquals(0, minHeap.size());
        assertArrayEquals(
                new Integer[] {null},
                minHeap.getBackingArray()
        );
        minHeap.add(1);
        minHeap.add(2);
        assertArrayEquals(
                new Integer[] {null, 1, 2, null},
                minHeap.getBackingArray()
        );
    }



    @Test
    public void removeException() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(notNullValue(String.class));
        minHeap.remove();
    }

    @Test
    public void removeException2() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(notNullValue(String.class));
        for (int i = 0; i < 40; i++) {
            minHeap.add(i);
        }
        minHeap.clear();
        minHeap.remove();
    }

    @Test
    public void constructorException1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        minHeap = new MinHeap<>(null);
    }

    @Test
    public void constructorException2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        minHeap = new MinHeap<>(new ArrayList<Integer>(){{add(null);}});
    }

    @Test
    public void addException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        minHeap.add(null);
    }

    @Test
    public void clear() {
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        minHeap.clear();
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        minHeap.add(~0);
        minHeap.clear();
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        assertArrayEquals(
                new Integer[]{null, null, null, null, null, null, null, null,
                        null, null, null, null, null},
                minHeap.getBackingArray()
        );
        for (int i = 0; i < 30; i++) {
            minHeap.add(i);
        }
        minHeap.clear();
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
        assertArrayEquals(
                new Integer[]{null, null, null, null, null, null, null, null,
                        null, null, null, null, null},
                minHeap.getBackingArray()
        );
    }

    @Test
    public void isEmpty() {
        assertTrue("New heap isEmpty() broken", minHeap.isEmpty());
        minHeap.add(4);
        assertFalse(minHeap.isEmpty());
        minHeap.remove();
        assertTrue(minHeap.isEmpty());
        for (int i = 0; i < 30; i++) {
            minHeap.add(i);
        }
        for (int i = 0; i < 30; i++) {
            minHeap.remove();
        }
        assertTrue("Heap with resized backing array isEmpty() broken",
                minHeap.isEmpty());
    }

    @Test
    public void getMin() {
        assertNull(minHeap.getMin());
        minHeap.add(0xDEADBEEF);
        assertEquals(Integer.valueOf(0xDEADBEEF), minHeap.getMin());
        assertEquals(1, minHeap.size());
        assertFalse(minHeap.isEmpty());
        minHeap.remove();
        assertNull(minHeap.getMin());
    }

    ////////////////////////////////////////////////////////////////////////
    // This part is just testing that you didn't modify the signatures of //
    // the given methods                                                  //
    ////////////////////////////////////////////////////////////////////////

    interface Heap<T extends Comparable<? super T>> {
        void add(T t);
        T remove();
        T getMin();
        boolean isEmpty();
        void clear();
        int size();
        Object[] getBackingArray();
    }

    class Child<T extends Comparable<? super T>>
            extends MinHeap<T> implements Heap<T> {}
}