import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This is a basic set of unit tests for ArrayList. Passing these does
 * NOT guarantee any grade on this assignment. This is only a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * @author CS 1332 TAs, Benjamin Daghir
 * @version 1.1.2
 */
public class ArrayListSharedTests {

    private ArrayList<String> list;

    private static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("4a"); // 4a 3a 2a 1a 0a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());

        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack("3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsGeneral() {
        assertEquals(0, list.size());

        list.addAtIndex(0, "2a"); // 2a
        list.addAtIndex(0, "1a"); // 1a 2a
        list.addAtIndex(2, "4a"); // 1a 2a 4a
        list.addAtIndex(2, "3a"); // 1a 2a 3a 4a

        assertEquals(4, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());
        String temp = "0a"; // For equality checking.
        list.addAtIndex(0, temp); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront()); // 1a 2a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());
        String temp = "5a"; // For equality checking.
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, temp); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack()); // 0a 1a 2a 3a 4a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsGeneral() {
        assertEquals(0, list.size());
        String temp = "2a"; // For equality checking.
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, temp); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());
        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
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
    public void testIsEmptyAndClear() {
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        assertFalse(list.isEmpty());

        // Clearing the list should empty the array and reset size
        list.clear();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackEmptyList() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];

        assertEquals(null, list.removeFromBack());

        assertEquals(0, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontEmptyList() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];

        assertEquals(null, list.removeFromFront());

        assertEquals(0, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testDoubleBackingArrayAddToBack() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 2; i++) {
            expected[i] = i + "";
            list.addToBack(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 2, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testDoubleBackingArrayAddToFront() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 2];
        int j = ArrayList.INITIAL_CAPACITY * 2 - 1;
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 2; i++) {
            expected[j] = i + "";
            j--;
            list.addToFront(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 2, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testQuadBackingArrayAddToBack() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 4; i++) {
            expected[i] = i + "";
            list.addToBack(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 4, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testQuadBackingArrayAddToFront() {
        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        int j = ArrayList.INITIAL_CAPACITY * 4 - 1;
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 4; i++) {
            expected[j] = i + "";
            j--;
            list.addToFront(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 4, list.size());

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexIndexOutOfBounds() {
        try {
            list.addAtIndex(-1, "Bad Add");
        } catch (IndexOutOfBoundsException e) {
            try {
                list.addAtIndex(list.size() + 1, "Bad Add");
            } catch (IndexOutOfBoundsException e2) {
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index > size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexIllegalArgument() {
        try {
            list.addAtIndex(0, null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontIllegalArgument() {
        try {
            list.addToFront( null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackIllegalArgument() {
        try {
            list.addToBack( null);
        } catch (IllegalArgumentException e) {
            return;
        }
        assertEquals("Throw an IllegalArgumentException", "Did not throw IllegalArgumentException");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexIndexOutOfBounds() {
        try {
            list.removeAtIndex(-1);
        } catch (IndexOutOfBoundsException e) {
            try {
                list.removeAtIndex(list.size());
            } catch (IndexOutOfBoundsException e2) {
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index >= size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
    }

    @Test(timeout = TIMEOUT)
    public void testGetIndexOutOfBounds() {
        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            try {
                list.get(list.size());
            } catch (IndexOutOfBoundsException e2) {
                return;
            }
            assertEquals("Throw an IndexOutOfBoundsException on index >= size", "Did not throw IndexOutOfBoundsException");
        }
        assertEquals("Throw an IndexOutOfBoundsException on index < 0", "Did not throw IndexOutOfBoundsException");
    }

    @Test(timeout = 10000)
    public void testAddToBackEfficiency() {
        //  This test assumes INITIAL_CAPACITY to be set to 13.
        //  This test tries to quantify the efficiency of your addToBack
        //  method. You may not pass this test if you have a slower computer.
        //  My MacBook Pro has an i7 at 2.5 GHz. If you fail this test,
        //  that is not to say your method is not O(1). Be wary, read more about
        //  efficiency here! https://www.geeksforgeeks.org/analysis-algorithms-big-o-analysis/ (:

        assertEquals(0, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 16384];
        for (int i = 0; i < ArrayList.INITIAL_CAPACITY * 16384; i++) {
            expected[i] = i + "";
            list.addToBack(i + "");
        }

        assertEquals(ArrayList.INITIAL_CAPACITY * 16384, list.size());

        assertArrayEquals(expected, list.getBackingArray());

    }
}
