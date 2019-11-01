import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for DoublyLinkedList.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author The 1332 TAs, Alexander Gurung
 * @version 1.1
 */
public class LinkedListStudentTestsAG {
    private DoublyLinkedList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("4a"); // 4a 3a 2a 1a 0a
        list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("5a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("4a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("0a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addToBack("0a"); //0a
        list.addToBack("1a"); //0a 1a
        list.addToBack("2a"); //0a 1a 2a
        list.addToBack("3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsIndex() {
        assertEquals(0, list.size());
        String temp = new String("2a");
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, temp); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("4a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("5a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());
        String temp = new String("0a");
        list.addToBack(temp); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack("3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        assertEquals(temp, list.removeFromFront()); // 1a 2a 3a

        assertEquals(3, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("1a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());
        String temp = new String("3a");
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack(temp); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        assertEquals(temp, list.removeFromBack()); // 0a 1a 2a

        assertEquals(3, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, new String("3a")); // 0a 1a 2a 3a
        list.addAtIndex(4, new String("3a")); // 0a 1a 2a 3a 3a
        list.addAtIndex(5, "4a"); // 0a 1a 2a 3a 3a 4a

        assertEquals(1, list.lastOccurrence(new String("1a")));
        assertEquals(4, list.lastOccurrence(new String("3a")));
    }

    @Test(timeout = TIMEOUT)
    public void testGetGeneral() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(timeout = TIMEOUT)
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = x + "a";
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void testClearIsEmpty() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

        list.clear();
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertTrue(list.isEmpty());
    }


    /*
     * Following tests added by Alex Gurung, rest were created by the 1332 TAs
     */


    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexLowerIndexOutOfBoundsException() {
        list.addAtIndex(-1, "0a");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexUpperIndexOutOfBoundsException() {
        list.clear();
        assertEquals(0, list.size());
        list.addAtIndex(2, "0a");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddAtIndexIllegalArgumentException() {
        list.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToFrontIllegalArgumentException() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToBackIllegalArgumentException() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexLowerIndexOutOfBoundsException() {
        list.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexUpperIndexOutOfBoundsException() {
        list.clear();
        assertEquals(0, list.size());
        list.removeAtIndex(2);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testLastOccurenceIllegalArgumentException() {
        list.lastOccurrence(null);
    }


}