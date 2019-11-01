import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*
 * Basic tests for the ArrayDeque and LinkedDeque classes. These are a set of
 * basic tests, make your own JUnits to ensure coverage.
 *
 * @author CS 1332 TAs, Benjamin Daghir
 * @version 1.1.0
 */
public class StudentTests {

    private ArrayDeque<Integer> array;
    private LinkedDeque<Integer> linked;

    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        array = new ArrayDeque<>();
        linked = new LinkedDeque<>();
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeNoWraparound() {
        array.addLast(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);

        Integer[] expected = new Integer[ArrayDeque.INITIAL_CAPACITY];
        expected[0] = 1;
        expected[1] = 2;
        expected[2] = 3;
        expected[3] = 4;
        expected[4] = 5;

        assertEquals(5, array.size());
        assertArrayEquals(expected, array.getBackingArray());

        array.removeFirst();
        array.removeFirst();
        array.removeLast();
        array.removeLast();

        expected[0] = null;
        expected[1] = null;
        expected[3] = null;
        expected[4] = null;

        Object[] arr = array.getBackingArray();
        assertEquals(1, array.size());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeWraparoundBack() {
        array.addFirst(5);
        array.addFirst(4);
        array.addFirst(3);
        array.addFirst(2);
        array.addFirst(1);

        Integer[] expected = new Integer[ArrayDeque.INITIAL_CAPACITY];
        expected[ArrayDeque.INITIAL_CAPACITY - 1] = 5;
        expected[ArrayDeque.INITIAL_CAPACITY - 2] = 4;
        expected[ArrayDeque.INITIAL_CAPACITY - 3] = 3;
        expected[ArrayDeque.INITIAL_CAPACITY - 4] = 2;
        expected[ArrayDeque.INITIAL_CAPACITY - 5] = 1;

        assertEquals(5, array.size());
        assertArrayEquals(expected, array.getBackingArray());

        array.removeLast();
        expected[ArrayDeque.INITIAL_CAPACITY - 1] = null;

        assertEquals(4, array.size());
        assertArrayEquals(expected, array.getBackingArray());

        array.addLast(6);
        array.addLast(7);

        expected[ArrayDeque.INITIAL_CAPACITY - 1] = 6;
        expected[0] = 7;

        assertEquals(6, array.size());
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeAdd() {
        linked.addFirst(2);
        linked.addFirst(1);
        linked.addLast(3);
        linked.addLast(4);

        assertEquals(4, linked.size());

        LinkedNode<Integer> node = linked.getHead();
        LinkedNode<Integer> prev = linked.getHead();
        assertNotEquals(null, node);
        assertEquals(null, node.getPrevious());
        assertEquals(new Integer(1), node.getData());

        node = node.getNext();
        assertNotEquals(null, node);
        assertEquals(prev, node.getPrevious());
        assertEquals(new Integer(2), node.getData());

        prev = node;
        node = node.getNext();
        assertNotEquals(null, node);
        assertEquals(prev, node.getPrevious());
        assertEquals(new Integer(3), node.getData());

        prev = node;
        node = node.getNext();
        assertNotEquals(null, node);
        assertEquals(prev, node.getPrevious());
        assertEquals(new Integer(4), node.getData());
        assertEquals(linked.getTail(), node);
        assertEquals(null, node.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedDequeRemove() {
        linked.addFirst(2);
        linked.addFirst(1);
        linked.addLast(3);
        linked.addLast(4);

        assertEquals(4, linked.size());

        linked.removeFirst();
        linked.removeLast();

        LinkedNode<Integer> node = linked.getHead();
        LinkedNode<Integer> prev = linked.getHead();
        assertNotEquals(null, node);
        assertEquals(null, node.getPrevious());
        assertEquals(new Integer(2), node.getData());

        prev = node;
        node = node.getNext();
        assertNotEquals(null, node);
        assertEquals(prev, node.getPrevious());
        assertEquals(new Integer(3), node.getData());
        assertEquals(linked.getTail(), node);
        assertEquals(null, node.getNext());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddFirstBasic() {
        array.addFirst(0);
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY];
        check[ArrayDeque.INITIAL_CAPACITY - 1] = 0;

        assertEquals(1, array.size());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddFirstIntermediate() {
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY];
        for (int i = ArrayDeque.INITIAL_CAPACITY - 1; i >= 0; i--) {
            array.addFirst(i);
            check[i] = i;
        }

        assertEquals(ArrayDeque.INITIAL_CAPACITY, array.size());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddFirstDifficult() {
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY * 2];
        int j = ArrayDeque.INITIAL_CAPACITY - 1;
        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY; i++) {
            array.addFirst(j);
            check[i + 1] = i;
            j--;
        }
        array.addFirst(-1);
        check[0] = -1;

        for (int i = ArrayDeque.INITIAL_CAPACITY * 2 - 1; i > ArrayDeque.INITIAL_CAPACITY; i--) {
            array.addFirst(i);
            check[i] = i;
        }

        Object[] arr = array.getBackingArray();
        assertEquals(ArrayDeque.INITIAL_CAPACITY * 2, array.size());
        assertArrayEquals(check, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddLastBasic() {
        array.addLast(0);
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY];
        check[0] = 0;

        assertEquals(1, array.size());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddLastIntermediate() {
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY];
        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY; i++) {
            array.addLast(i);
            check[i] = i;
        }

        assertEquals(ArrayDeque.INITIAL_CAPACITY, array.size());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddLastDifficult() {
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY * 2];
        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY; i++) {
            array.addLast(i);
            check[i] = i;
        }
        array.addLast(-1);
        check[ArrayDeque.INITIAL_CAPACITY] = -1;

        for (int i = ArrayDeque.INITIAL_CAPACITY + 1; i < ArrayDeque.INITIAL_CAPACITY * 2; i++) {
            array.addLast(i);
            check[i] = i;
        }

        Object[] arr = array.getBackingArray();
        assertEquals(ArrayDeque.INITIAL_CAPACITY * 2, array.size());
        assertArrayEquals(check, arr);
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeAddingComplex() {
        int j = ArrayDeque.INITIAL_CAPACITY - 1;
        int k = ArrayDeque.INITIAL_CAPACITY;

        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY; i++) {
            Object[] arr = array.getBackingArray();
            if ((i % 2) == 0) {
                array.addLast(k);
                k++;
            } else {
                array.addFirst(j);
                j--;
            }
        }

        array.addLast(-1);

        if (ArrayDeque.INITIAL_CAPACITY % 2 == 0) {
            j = (int) Math.floor(ArrayDeque.INITIAL_CAPACITY / 2) - 1;
            k = (int) Math.floor(ArrayDeque.INITIAL_CAPACITY / 2) + ArrayDeque.INITIAL_CAPACITY + 1;
        } else {
            j = (int) Math.floor(ArrayDeque.INITIAL_CAPACITY / 2);
            k = (int) Math.floor(ArrayDeque.INITIAL_CAPACITY / 2) + ArrayDeque.INITIAL_CAPACITY + 2;
        }

        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY - 1; i++) {
            Object[] arr = array.getBackingArray();
            if ((i % 2) == 0) {
                array.addLast(k);
                k++;
            } else {
                array.addFirst(j);
                j--;
            }
        }

        array.addFirst(-2);

        Object[] check = new Object[ArrayDeque.INITIAL_CAPACITY * 4];
        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY * 2; i++) {
            check[i] = i;
        }
        check[0] = -2;
        if (ArrayDeque.INITIAL_CAPACITY % 2 == 0) {
            check[(int) Math.floor(ArrayDeque.INITIAL_CAPACITY / 2) + ArrayDeque.INITIAL_CAPACITY] = -1;
        } else {
            check[(int) Math.floor(ArrayDeque.INITIAL_CAPACITY / 2) + 1 + ArrayDeque.INITIAL_CAPACITY] = -1;
        }
        check[ArrayDeque.INITIAL_CAPACITY * 2] = ArrayDeque.INITIAL_CAPACITY * 2;

        assertEquals(ArrayDeque.INITIAL_CAPACITY * 2 + 1, array.size());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeRemoveFirstBasic() {
        array.addFirst(-22);
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY];

        assertEquals(new Integer(-22), array.removeFirst());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeRemoveFirstDifficult() {
        Object[] check = new Object[ArrayDeque.INITIAL_CAPACITY * 2];
        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY * 2; i++) {
            array.addLast(i);
            check[i] = i;
        }

        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY * 2; i++) {
            assertEquals(new Integer(i), array.removeFirst());
            check[i] = null;
            assertArrayEquals(check, array.getBackingArray());
        }
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeRemoveLastBasic() {
        array.addLast(-22);
        Integer[] check = new Integer[ArrayDeque.INITIAL_CAPACITY];

        assertEquals(new Integer(-22), array.removeLast());
        assertArrayEquals(check, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeRemoveLastDifficult() {
        Object[] check = new Object[ArrayDeque.INITIAL_CAPACITY * 2];
        for (int i = 0; i < ArrayDeque.INITIAL_CAPACITY * 2; i++) {
            array.addLast(i);
            check[i] = i;
        }

        for (int i = ArrayDeque.INITIAL_CAPACITY * 2 - 1; i >= 0; i--) {
            assertEquals(new Integer(i), array.removeLast());
            check[i] = null;
            assertArrayEquals(check, array.getBackingArray());
        }
    }

    @Test(timeout =  TIMEOUT)
    public void testLinkedDequeAddFirstIllegalArgument() {
        try {
            linked.addFirst(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout =  TIMEOUT)
    public void testLinkedDequeAddLastIllegalArgument() {
        try {
            linked.addLast(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout =  TIMEOUT)
    public void testLinkedDequeRemoveFirstNoSuchElement() {
        try {
            linked.removeFirst();
        } catch (NoSuchElementException e) {
            return;
        }
        assertEquals("Throw a NoSuchElementException", "Did not throw NoSuchElementException");
    }

    @Test(timeout =  TIMEOUT)
    public void testLinkedDequeRemoveLastNoSuchElement() {
        try {
            linked.removeLast();
        } catch (NoSuchElementException e) {
            return;
        }
        assertEquals("Throw a NoSuchElementException", "Did not throw NoSuchElementException");
    }

    @Test(timeout =  TIMEOUT)
    public void testArrayDequeAddFirstIllegalArgument() {
        try {
            array.addFirst(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout =  TIMEOUT)
    public void testArrayDequeAddLastIllegalArgument() {
        try {
            array.addLast(null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout =  TIMEOUT)
    public void testArrayDequeRemoveFirstNoSuchElement() {
        try {
            array.removeFirst();
        } catch (NoSuchElementException e) {
            return;
        }
        assertEquals("Throw a NoSuchElementException", "Did not throw NoSuchElementException");
    }

    @Test(timeout =  TIMEOUT)
    public void testArrayDequeRemoveLastNoSuchElement() {
        try {
            array.removeLast();
        } catch (NoSuchElementException e) {
            return;
        }
        assertEquals("Throw a NoSuchElementException", "Did not throw NoSuchElementException");
    }
}