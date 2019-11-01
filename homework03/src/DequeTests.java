import org.junit.*;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTests {

    private ArrayDeque<String> array;
    private LinkedDeque<String> linked;
    @Before
    public void setup() {
        array = new ArrayDeque<>();
        linked = new LinkedDeque<>();
    }

    @Test
    public void strictArrayDequeTest() {
        //These first few statements ensure that elements are added to the right ends of the list.
        tA(0, null, null, null, null, null,  null, null, null, null, null, null);
        array.addFirst("A");
        tA(1, null, null, null, null, null, null, null, null, null, null, "A");
        array.addFirst("B");
        tA(2, null, null, null, null, null, null, null, null, null, "B", "A");
        array.addLast("C");
        tA(3, "C", null, null, null, null, null, null, null, null, "B", "A");
        array.addLast("D");
        tA(4, "C", "D", null, null, null, null, null, null, null, "B", "A");
        array.addLast("E");
        tA(5, "C", "D", "E", null, null, null, null, null, null, "B", "A");
        //These next few statements ensure that elements are removed from the right ends of the list.
        assertEquals("B", array.removeFirst());
        tA(4, "C", "D", "E", null, null, null, null, null, null, null, "A");
        assertEquals("E", array.removeLast());
        tA(3, "C", "D", null, null, null, null, null, null, null, null, "A");
        assertEquals("A", array.removeFirst());
        tA(2, "C", "D", null, null, null, null, null, null, null, null, null);
        assertEquals("C", array.removeFirst());
        tA(1, null, "D", null, null, null, null, null, null, null, null, null);
        array.addLast("F");
        tA(2, null, "D", "F", null, null, null, null, null, null, null, null);
        array.addLast("G");
        tA(3, null, "D", "F", "G", null, null, null, null, null, null, null);
        assertEquals("D", array.removeFirst());
        tA(2, null, null, "F", "G", null, null, null, null, null, null, null);
        assertEquals("G", array.removeLast());
        tA(1, null, null, "F", null, null, null, null, null, null, null, null);
        assertEquals("F", array.removeLast());
        tA(0, null, null, null, null, null, null, null, null, null, null, null);
        //This ensures that the head location is properly reset after the list empties.
        array.addLast("00");
        tA(1, "00", null, null, null, null, null, null, null, null, null, null);
        //This ensures that the back location is properly reset after the list empties.
        array.addFirst("10");
        tA(2, "00", null, null, null, null, null, null, null, null, null, "10");
        array.addFirst("09");
        array.addFirst("08");
        array.addFirst("07");
        array.addFirst("06");
        array.addFirst("05");
        array.addFirst("04");
        array.addFirst("03");
        array.addFirst("02");
        array.addFirst("01");
        tA(11, "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10");
        //The following statement will trigger a resize, and sure that the array becomes properly ordered after it.
        array.addFirst("11");
        tA(12, "11", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
                "00", null, null, null, null, null, null, null, null, null, null);
        assertEquals("00", array.removeLast());
        //This removes all but one element.
        for (int i = 0; i < 10; i++) {
            array.removeFirst();
        }
        tA(1, null, null, null, null, null, null, null, null, null, null, "10",
                null, null, null, null, null, null, null, null, null, null, null);
        //This removes the final element, and then ensures that the array is NOT resized.
        assertEquals("10", array.removeFirst());
        tA(0, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
        //These again test that the head and back cursors are properly reset.
        array.addFirst("00");
        array.addLast("21");
        tA(2, "21", null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, "00");
        assertEquals("00", array.removeFirst());
        assertEquals("21", array.removeLast());
        tA(0, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
        //This block of repeated code ensures that the other adding method properly resizes the array.
        array = new ArrayDeque<>();
        array.addLast("00");
        tA(1, "00", null, null, null, null, null, null, null, null, null, null);
        array.addFirst("10");
        tA(2, "00", null, null, null, null, null, null, null, null, null, "10");
        array.addLast("09");
        array.addLast("08");
        array.addLast("07");
        array.addLast("06");
        array.addLast("05");
        array.addLast("04");
        array.addLast("03");
        array.addLast("02");
        array.addLast("01");
        tA(11, "00", "09", "08", "07", "06", "05", "04", "03", "02", "01", "10");
        array.addLast("11");
        tA(12, "10", "00", "09", "08", "07", "06", "05", "04", "03", "02", "01",
                "11", null, null, null, null, null, null, null, null, null, null);
        assertEquals("10", array.removeFirst());
        for (int i = 0; i < 10; i++) {
            array.removeLast();
        }
        tA(1, null, "00", null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
        assertEquals("00", array.removeLast());
        tA(0, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
        //Lastly, this tests that exceptions are properly thrown.
        try {
            array.addFirst(null);
            fail("ArrayDeque.addFirst did not raise an exception when adding null data.");
        } catch (IllegalArgumentException e) { }
        try {
            array.addLast(null);
            fail("ArrayDeque.addLast did not raise an exception when adding null data.");
        } catch (IllegalArgumentException e) { }
        try {
            array.removeFirst();
            fail("ArrayDeque.removeFirst did not raise an exception when removing from an empty deque.");
        } catch (NoSuchElementException e) { }
        try {
            array.removeLast();
            fail("ArrayDeque.removeLast did not raise an exception when removing from an empty deque.");
        } catch (NoSuchElementException e) { }
        //This last bunch of tests ensures that the backing of the ArrayDeque was not affected by the exceptions.
        tA(0, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
        array.addFirst("00");
        array.addLast("21");
        tA(2, "21", null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, "00");
        assertEquals("21", array.removeLast());
        assertEquals("00", array.removeLast());
        tA(0, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
        //One final repetition to make sure that the cursors are reset.
        array.addFirst("00");
        array.addLast("21");
        tA(2, "21", null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, "00");
        assertEquals("00", array.removeFirst());
        assertEquals("21", array.removeFirst());
        tA(0, null, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null, null);
    }

    @Test
    public void strictLinkedDequeTest() {
        //The following block tests adding and removing elements from the front and back,
        //ensuring that nearly every edge case and condition is tested.
        for (int i = 0; i < 16; i++) {
            assertEquals(0, linked.size());
            assertNull(linked.getHead());
            assertNull(linked.getTail());
            //Each of these adds an element.
            switch (i % 4) {
                case 0:
                    linked.addFirst("1");
                    assertEquals("1", linked.getHead().getData());
                    break;
                case 1:
                    linked.addFirst("0");
                    assertEquals("0", linked.getHead().getData());
                    break;
                case 2:
                    linked.addLast("1");
                    assertEquals("1", linked.getHead().getData());
                    break;
                case 3:
                    linked.addLast("0");
                    assertEquals("0", linked.getHead().getData());
                    break;
            }
            assertSame(linked.getHead(), linked.getTail());
            assertEquals(1, linked.size());
            assertNull(linked.getHead().getNext());
            assertNull(linked.getHead().getPrevious());
            //Each of these adds an element such that the list should contain "0" followed by "1".
            switch (i % 4) {
                case 0:
                    linked.addFirst("0");
                    break;
                case 1:
                    linked.addLast("1");
                    break;
                case 2:
                    linked.addFirst("0");
                    break;
                case 3:
                    linked.addLast("1");
                    break;
            }
            //The linked list should look like this:
            // null<-["0"]<->["1"]->null
            // head----^  tail--^
            assertEquals(2, linked.size());
            assertNotEquals(linked.getHead(), linked.getTail());
            assertEquals("0", linked.getHead().getData());
            assertEquals("1", linked.getTail().getData());
            assertSame(linked.getHead(), linked.getTail().getPrevious());
            assertSame(linked.getTail(), linked.getHead().getNext());
            assertNull(linked.getHead().getPrevious());
            assertNull(linked.getTail().getNext());
            //Each of these removes an element.
            switch (i / 4) {
                case 0:
                case 1:
                    assertEquals("0", linked.removeFirst());
                    assertEquals("1", linked.getHead().getData());
                    break;
                case 2:
                case 3:
                    assertEquals("1", linked.removeLast());
                    assertEquals("0", linked.getHead().getData());
                    break;
            }
            assertSame(linked.getHead(), linked.getTail());
            assertEquals(1, linked.size());
            assertNull(linked.getHead().getNext());
            assertNull(linked.getHead().getPrevious());
            //Each of these empties the list.
            switch (i / 4) {
                case 0:
                    assertEquals("1", linked.removeFirst());
                    break;
                case 1:
                    assertEquals("1", linked.removeLast());
                    break;
                case 2:
                    assertEquals("0", linked.removeFirst());
                    break;
                case 3:
                    assertEquals("0", linked.removeLast());
                    break;
            }
            assertEquals(0, linked.size());
            assertNull(linked.getHead());
            assertNull(linked.getTail());
        }
        //This next block just tests the capacity of the list. If the previous checks passed, this probably will also.
        //Firstly, the list is built up:
        assertEquals(0, linked.size());
        linked.addFirst("0");
        linked.addFirst("-1");
        linked.addLast("1");
        linked.addLast("2");
        linked.addFirst("-2");
        linked.addFirst("-3");
        linked.addLast("3");
        //This large block of code tests practically every link.
        assertEquals(7, linked.size());
        assertEquals("-3", linked.getHead().getData());
        assertEquals("-2", linked.getHead().getNext().getData());
        assertEquals("-1", linked.getHead().getNext().getNext().getData());
        assertEquals("0", linked.getHead().getNext().getNext().getNext().getData());
        assertEquals("1", linked.getHead().getNext().getNext().getNext().getNext().getData());
        assertEquals("2", linked.getHead().getNext().getNext().getNext().getNext().getNext().getData());
        assertEquals("3", linked.getHead().getNext().getNext().getNext().getNext().getNext().getNext().getData());
        assertEquals("3", linked.getTail().getData());
        assertEquals("2", linked.getTail().getPrevious().getData());
        assertEquals("1", linked.getTail().getPrevious().getPrevious().getData());
        assertEquals("0", linked.getTail().getPrevious().getPrevious().getPrevious().getData());
        assertEquals("-1", linked.getTail().getPrevious().getPrevious().getPrevious().getPrevious().getData());
        assertEquals("-2", linked.getTail().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getData());
        assertEquals("-3", linked.getTail().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getData());
        assertSame(linked.getTail(), linked.getHead().getNext().getNext().getNext().getNext().getNext().getNext());
        assertSame(linked.getTail().getPrevious(), linked.getHead().getNext().getNext().getNext().getNext().getNext());
        assertSame(linked.getTail().getPrevious().getPrevious(), linked.getHead().getNext().getNext().getNext().getNext());
        assertSame(linked.getTail().getPrevious().getPrevious().getPrevious(), linked.getHead().getNext().getNext().getNext());
        assertSame(linked.getTail().getPrevious().getPrevious().getPrevious().getPrevious(), linked.getHead().getNext().getNext());
        assertSame(linked.getTail().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious(), linked.getHead().getNext());
        assertSame(linked.getTail().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious().getPrevious(), linked.getHead());
        assertNull(linked.getTail().getNext());
        assertNull(linked.getHead().getPrevious());
        //Now the list is emptied:
        assertEquals("-3", linked.removeFirst());
        assertEquals("-2", linked.removeFirst());
        assertEquals("-1", linked.removeFirst());
        assertEquals("0", linked.removeFirst());
        assertEquals("1", linked.removeFirst());
        assertEquals("3", linked.removeLast());
        assertSame(linked.getHead(), linked.getTail());
        assertNull(linked.getHead().getPrevious());
        assertNull(linked.getTail().getNext());
        assertEquals("2", linked.getHead().getData());
        assertEquals(1, linked.size());
        assertEquals("2", linked.removeFirst());
        assertNull(linked.getHead());
        assertNull(linked.getTail());
        assertEquals(0, linked.size());
        //Lastly, some exception handling.
        try {
            linked.addFirst(null);
            fail("LinkedDeque.addFirst did not raise an exception when adding null data.");
        } catch (IllegalArgumentException e) { }
        try {
            linked.addLast(null);
            fail("LinkedDeque.addLast did not raise an exception when adding null data.");
        } catch (IllegalArgumentException e) { }
        try {
            linked.removeFirst();
            fail("LinkedDeque.removeFirst did not raise an exception when removing from an empty deque.");
        } catch (NoSuchElementException e) { }
        try {
            linked.removeLast();
            fail("LinkedDeque.removeLast did not raise an exception when removing from an empty deque.");
        } catch (NoSuchElementException e) { }
    }

    /**
     * This is a shortcut method that ensures that the array exactly matches
     * the guidelines.
     * @param size The expected size of the array.
     * @param elements The expected contents of the array.
     */
    private void tA(int size, String... elements) {
        assertEquals(size, array.size());
        assertArrayEquals(elements, array.getBackingArray());
    }
}