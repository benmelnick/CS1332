import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/*
 * Basic tests for the ArrayDeque and LinkedDeque classes. These are a set of
 * basic tests, make your own JUnits to ensure coverage.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class DequeStudentTests {

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
        expected[10] = 5;
        expected[9] = 4;
        expected[8] = 3;
        expected[7] = 2;
        expected[6] = 1;

        assertEquals(5, array.size());
        assertArrayEquals(expected, array.getBackingArray());

        array.removeLast();
        expected[10] = null;

        assertEquals(4, array.size());
        assertArrayEquals(expected, array.getBackingArray());

        array.addLast(6);
        array.addLast(7);

        expected[10] = 6;
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
}