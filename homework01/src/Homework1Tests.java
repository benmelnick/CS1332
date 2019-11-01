import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * This is a more exhaustive set of tests for homework 1. Passing
 * these still does not guarantee full credit, as it does not
 * test efficiency or semantics, just that your code does
 * what it's supposed to. Testing for exception throwing is included.
 * Respond on Piazza if there are problems with these tests.
 *
 * 1.0.1 changes: Tests are no longer based on a hard-coded
 * INITIAL_CAPACITY of 13. They now are based on the value of
 * INITIAL_CAPACITY and change with it. Also changed file name
 * to reflect that these are tests for homework 1.
 *
 * @author Justin Higgins
 * @version 1.0.1
 */
public class Homework1Tests {
    private ArrayList<String> list;

    @Before
    public void createList() {
        list = new ArrayList<String>();
        assertEquals(list.size(), 0);
    }

    @Test
    public void testAdd() {
        list.addAtIndex(0, "b");
        list.addToBack("c");
        list.addToBack("e");
        list.addAtIndex(2, "d");
        list.addToFront("a");
        String[] exp = {"a", "b", "c", "d", "e"};
        for (int i = 0; i < 5; i++) {
            assertEquals(exp[i], list.get(i));
        }
    }

    @Test
    public void testRemove() {
        list.addToFront("a");
        list.addToBack("b");
        list.addToBack("c");
        list.addToBack("d");
        list.addToBack("e");
        String[] exp = {"a", "e", "d"};
        String[] act = new String[3];
        act[0] = list.removeFromFront();
        act[1] = list.removeFromBack();
        act[2] = list.removeAtIndex(2);
        assertArrayEquals(exp, act);
    }

    @Test
    public void testRegrow() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        String[] exp = new String[list.getBackingArray().length * 2];
        list.addToBack("a");
        for (int i = 0; i < list.size() - 1; i++) {
            exp[i] = Integer.toString(i);
        }
        exp[list.size() - 1] = "a";
        assertEquals(list.size(), list.INITIAL_CAPACITY + 1);
        assertArrayEquals(exp, list.getBackingArray());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testClear() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        assertFalse(list.isEmpty());
        list.clear();
        assertTrue(list.isEmpty());
        list.get(0);
    }

    @Test
    public void testIsEmpty() {
        assertTrue(list.isEmpty());
        list.addToBack("a");
        assertFalse(list.isEmpty());
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddIndexGreaterThanSize() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.addAtIndex(list.INITIAL_CAPACITY + 1, "a");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testAddNegativeIndex() {
        list.addAtIndex(-1, "a");
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetIndexGreaterThanSize() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.get(list.INITIAL_CAPACITY + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testGetNegativeIndex() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.get(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNullAdd() {
        list.addToFront(null);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveIndexGreaterThanSize() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.removeAtIndex(list.INITIAL_CAPACITY + 1);
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void testRemoveNegativeIndex() {
        for (int i = 0; i < list.INITIAL_CAPACITY; i++) {
            list.addToBack(Integer.toString(i));
        }
        list.removeAtIndex(-1);
    }
}