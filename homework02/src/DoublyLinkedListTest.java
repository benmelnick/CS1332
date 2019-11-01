import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    private DoublyLinkedList<String> list;
    @Before
    public void setUp() throws Exception {
        list = new DoublyLinkedList<>();
    }

    @Test
    public void addAtIndex() {
        for (int i = 0; i < 10; i++) {
            list.addAtIndex(i, "Initial String #" + i);
        }
        list.addAtIndex(3, "String added at index 3");
        list.addAtIndex(7, "String added at index 7");
        list.addAtIndex(9, "String added at index 9");
        assertEquals(13, list.size());
        assertArrayEquals(list.toArray(), new String[]{
                "Initial String #0", "Initial String #1", "Initial String #2",
                "String added at index 3", "Initial String #3",
                "Initial String #4", "Initial String #5",
                "String added at index 7", "Initial String #6",
                "String added at index 9", "Initial String #7",
                "Initial String #8", "Initial String #9"
        });
        list.addAtIndex(0, "Beginning String");
        assertEquals("Beginning String", list.getHead().getData());
        assertEquals("Initial String #9", list.getTail().getData());
        assertEquals(14, list.size());
        assertEquals("Beginning String", list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexNegativeIndex() {
        list.addAtIndex(-1, "Test");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addAtIndexLargeIndex() {
        list.addToFront("Test");
        list.addAtIndex(1, "Legal Index");
        list.addAtIndex(3, "Illegal Index");
    }

    @Test(expected = IllegalArgumentException.class)
    public void addAtIndexNullData() {
        list.addAtIndex(0, null);
    }

    @Test
    public void addToFront() {
        list.addToFront("First added");
        assertEquals(list.getHead(), list.getTail());
        list.addToFront("Second added");
        list.addToFront("Third added");
        list.addToFront("Last added");
        assertEquals("Last added", list.getHead().getData());
        assertEquals("First added", list.getTail().getData());
        assertEquals("Third added", list.get(1));
        assertEquals(4, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToFrontNullData() {
        list.addToFront(null);
    }

    @Test
    public void addToBack() {
        list.addToBack("First added");
        assertEquals(list.getHead(), list.getTail());
        list.addToBack("Second added");
        list.addToBack("Third added");
        list.addToBack("Last added");
        assertEquals("Last added", list.getTail().getData());
        assertEquals("First added", list.getHead().getData());
        assertEquals("Second added", list.get(1));
        assertEquals(4, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToBackNullData() {
        list.addToBack(null);
    }

    @Test
    public void removeAtIndex() {
        list.addToBack("1");
        list.addToBack("2");
        list.addToBack("3");
        assertEquals("1", list.removeAtIndex(0));
        assertEquals("3", list.removeAtIndex(1));
        list.addToBack("4");
        list.addToBack("6");
        assertEquals("4", list.removeAtIndex(1));
        assertEquals(2, list.size());
        assertEquals("6", list.removeAtIndex(1));
        assertEquals(list.getHead(), list.getTail());
        assertEquals("2", list.removeAtIndex(0));
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndexNegative() {
        list.removeAtIndex(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeAtIndexLarge() {
        list.addToFront("Test");
        list.removeAtIndex(1);
    }

    @Test
    public void removeFromFront() {
        list.addToBack("Element 0");
        list.addToBack("Element 1");
        list.addToBack("Element 2");
        assertEquals("Element 0", list.removeFromFront());
        assertEquals("Element 1", list.removeFromFront());
        assertEquals("Element 2", list.removeFromFront());
        assertNull(list.removeFromFront());
    }

    @Test
    public void removeFromBack() {
        list.addToBack("Element 0");
        list.addToBack("Element 1");
        list.addToBack("Element 2");
        assertEquals("Element 2", list.removeFromBack());
        assertEquals("Element 1", list.removeFromBack());
        assertEquals("Element 0", list.removeFromBack());
        assertNull(list.removeFromBack());
    }

    @Test
    public void lastOccurrence() {
        for (int i = 0; i < 10; i++) {
            list.addToBack("Element " + i);
        }
        assertEquals(5, list.lastOccurrence("Element 5"));
        assertEquals(7, list.lastOccurrence("Element 7"));
        list.addToFront("Element 5");
        assertEquals(6, list.lastOccurrence("Element 5"));
        list.addToBack("Element 6");
        assertEquals(11, list.lastOccurrence("Element 6"));
        assertEquals(-1, list.lastOccurrence("String that does not exist"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void lastOccurrenceNull() {
        list.lastOccurrence(null);
    }

    @Test
    public void get() {
        for (int i = 0; i < 10; i++) {
            list.addToBack("Element " + i);
        }
        assertEquals("Element 2", list.get(2));
        assertEquals("Element 0", list.get(0));
        assertEquals("Element 9", list.get(9));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getNegative() {
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getLarge() {
        list.addToFront("Test");
        list.get(1);
    }

    @Test
    public void toArray() {
        assertArrayEquals(list.toArray(), new String[]{});
        for (int i = 0; i < 10; i++) {
            list.addToBack("" + i);
        }
        assertArrayEquals(list.toArray(), new String[]{
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        });
        list.removeFromFront();
        assertArrayEquals(list.toArray(), new String[]{
                "1", "2", "3", "4", "5", "6", "7", "8", "9"
        });
        list.addToBack("10");
        assertArrayEquals(list.toArray(), new String[]{
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"
        });
    }

    @Test
    public void isEmpty() {
        assertTrue(list.isEmpty());
        list.addToBack("Test");
        assertFalse(list.isEmpty());
        list.removeFromFront();
        assertTrue(list.isEmpty());
    }

    @Test
    public void clear() {
        assertTrue(list.isEmpty());
        for (int i = 0; i < 10; i++) {
            list.addToBack("" + i);
        }
        list.clear();
        assertTrue(list.isEmpty());
        assertNull(list.getHead());
        assertNull(list.getTail());
    }
}