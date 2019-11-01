import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Sample JUnit test cases for BST.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class MoreTests {
    private BST<Integer> bst;
    public static final int TIMEOUT = 200;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                      2
                     / \
                    1   3
        */
        bst.add(2);
        bst.add(1);
        bst.add(3);

        assertEquals(3, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 3, bst.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        /*
                      2
                     / \
                    1   3
                         \
                          4
                           \
                            5
        */
        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertEquals((Integer) 3, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight()
                .getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testContains() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
        */
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertTrue(bst.contains(58));
        assertEquals((Integer) 58, bst.get(58));
        assertTrue(bst.contains(12));
        assertEquals((Integer) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Integer) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Integer) 24, bst.get(24));
    }

    @Test(timeout = TIMEOUT)
    public void testGetDifferent() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
        */

        Integer testingInteger = new Integer(12);

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(testingInteger);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertSame(testingInteger, bst.get(new Integer(12)));
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        // Should be [24, 1, 94, 7, 58, 12, 73, 68, 77]
        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(24);
        preorder.add(1);
        preorder.add(7);
        preorder.add(12);
        preorder.add(94);
        preorder.add(58);
        preorder.add(73);
        preorder.add(68);
        preorder.add(77);

        // Should be [24, 1, 7, 12, 94, 58, 73, 68, 77]
        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(7);
        inorder.add(12);
        inorder.add(24);
        inorder.add(58);
        inorder.add(68);
        inorder.add(73);
        inorder.add(77);
        inorder.add(94);

        // Should be [1, 7, 12, 24, 58, 68, 73, 77, 94]
        assertEquals(inorder, bst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        /*
                       24
                    /      \
                   1        94
                    \      /
                     7    58
                      \    \
                      12    73
                            / \
                           68  77
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(12);
        postorder.add(7);
        postorder.add(1);
        postorder.add(68);
        postorder.add(77);
        postorder.add(73);
        postorder.add(58);
        postorder.add(94);
        postorder.add(24);

        // Should be [12, 7, 1, 68, 77, 73, 58, 94, 24]
        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testKLargest() {
        /*
                    50
                  /    \
                25      75
               /  \
              12   37
             /  \    \
            10  15    40
               /
              13
        */

        bst.add(50);
        bst.add(25);
        bst.add(75);
        bst.add(12);
        bst.add(37);
        bst.add(10);
        bst.add(15);
        bst.add(40);
        bst.add(13);

        List<Integer> largest = new ArrayList<>();
        largest.add(37);
        largest.add(40);
        largest.add(50);
        largest.add(75);

        // Should be [37, 40, 50, 75]
        assertEquals(largest, bst.kLargest(4));
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorAndClear() {
        /*
                     24
                    /
                   1
                    \
                     7
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(24);
        toAdd.add(1);
        toAdd.add(7);
        bst = new BST<>(toAdd);

        bst.clear();
        assertEquals(null, bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                     24
                    /
                   1
                    \
                     7
        */

        bst.add(24);
        bst.add(1);
        bst.add(7);

        assertEquals(2, bst.height());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testExceptionEmptyRemove() {
        bst.remove(0);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testExceptionKLargestKOutOfBounds() {
        bst.kLargest(1);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testExceptionKLargestKNegative() {
        bst.kLargest(-1);
    }

    /**
     * This is a one-to-one (modulo q) function: \Z -> \Z (mod q) that returns a
     * uniform distribution over the  range.
     *
     * @param x the input of the mapping.
     * @param p some prime where p >> q
     * @param q some prime where q << p
     * @return a number uniformly distributed on [0, q)
     */
    private int oneToOne(int x, long p, long q) {
        return (int) ((x * p) % (q));
    }

    private void checkValidBST() {
        List<Integer> inOrder = bst.inorder();
        for (int i = 0; i < inOrder.size() - 1; i++) {
            assertTrue(inOrder.get(i) < inOrder.get(i + 1));
        }
    }

    @Test
    public void fuzzAddRemove() {
        Random random = new Random();
        HashSet<Integer> oracle = new HashSet<>();
        long[] pValues = {
                32416189777L, 32416189853L, 32416189859L, 32416189867L,
                32416189877L, 32416189909L, 32416189919L, 32416189987L,
                32416190039L, 32416190071L};
        long[] qValues = {
                179426339L, 179426341L, 179426353L, 179426363L, 179426369L,
                179426407L, 179426447L, 179426453L, 179426491L, 179426549L};

        for (int j = 0; j < 10; j++) {
            long p = pValues[random.nextInt(pValues.length)];
            long q = qValues[random.nextInt(qValues.length)];

            for (int i = 0; i < 10000; i++) {
                assertEquals(oracle.size(), bst.size());
                checkValidBST();

                int searchElement = oneToOne(random.nextInt(i + 1), p, q);
                switch (random.nextInt(3)) {
                    case 0:
                        bst.add(oneToOne(i, p, q));
                        oracle.add(oneToOne(i, p, q));
                        break;
                    case 1:
                        assertEquals(bst.contains(searchElement),
                                oracle.contains(searchElement));
                        break;
                    case 2:
                        if (oracle.contains(searchElement)) {
                            bst.remove(searchElement);
                            oracle.remove(searchElement);
                        } else {
                            assertFalse(bst.contains(searchElement));
                        }
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
