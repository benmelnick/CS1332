import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

/**
 * These are basic tests that compound onto one another in order
 * to build a balanced AVL. Please read some of the comments
 * as I tried my best to give good explanations and examples
 * of each step.
 *
 * These tests also act as a tutorial for AVLs, so please
 * code your AVL to complete each test in the order
 * it appears in this file (top to bottom). Each test builds on
 * itself until you can successfully balance an AVL with rotations.
 *
 * Happy Coding! :D
 *
 * @author Benjamin Daghir
 * @version 1.0.1
 */
public class  AVLTutorial {
    private static final int TIMEOUT = 200000;
    private AVL<Integer> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<>();
    }


    /**
     * Start Here!
     *
     * This is the first test which simply adds a node to a tree.
     * The nodes in this test are added in such a way that there
     * will never need to be any rebalancing.
     *
     * This should look familiar. Maybe reuse some code from
     * homework 04? ;)
     */

    @Test(timeout = TIMEOUT)
    public void testAddNoRotation() {
        /*
                        50
                     /      \
                    25      75
                   /  \     /  \
                  10  33   60  100
         */

        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(75);
        avlTree.add(10);
        avlTree.add(33);
        avlTree.add(60);
        avlTree.add(100);

        assertEquals(7, avlTree.size());

        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 10, root.getLeft().getLeft().getData());
        assertEquals((Integer) 33, root.getLeft().getRight().getData());
        assertEquals((Integer) 60, root.getRight().getLeft().getData());
        assertEquals((Integer) 100, root.getRight().getRight().getData());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getLeft().getRight().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getLeft());

    }

    /**
     * Cool!
     *
     * Now we need to calculate the heights of each node as we
     * add them. The addition of a node affects all of the parent
     * nodes' heights above it. Some maybe wait to calculate the new
     * heights in the tree after the node is added. Would that
     * be on the way down or up the tree?
     *
     * (Remember this is a recursive function, what comes up must
     * come down... or more like what goes down must come back
     * up for a tree).
     */

    @Test(timeout = TIMEOUT)
    public void testAddHeightCalculations() {

        /*
                        50
                     /      \
                    25      75
                   /  \        \
                  10  33       100
                        \
                        44
               Heights of Each Node....
                        3
                     /      \
                    2        1
                   /  \        \
                  0    1        0
                        \
                         0
         */

        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(75);
        avlTree.add(10);
        avlTree.add(33);
        avlTree.add(100);
        avlTree.add(44);

        assertEquals(7, avlTree.size());

        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 10, root.getLeft().getLeft().getData());
        assertEquals((Integer) 33, root.getLeft().getRight().getData());
        assertEquals((Integer) 100, root.getRight().getRight().getData());
        assertEquals((Integer) 44, root.getLeft().getRight().getRight().getData());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getRight().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight().getRight());
        assertEquals(null, root.getLeft().getRight().getRight().getLeft());

        // Check heights
        assertEquals(3, root.getHeight());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(1, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getRight().getHeight());


    }

    /**
     * Sweet!
     *
     * Now we need to be able to calculate the balance factor of
     * the nodes. Similarly, adding a node can affect the
     * balance factor. Should you recalculate the balance factors
     * on the way down the tree or up?
     *
     */
    @Test(timeout = TIMEOUT)
    public void testAddBalanceFactorCalculations() {

        /*
                        50
                     /      \
                    25      75
                   /  \        \
                  10  33       100
                        \
                        44
            Balance Factors of Each Node....
                        1
                     /      \
                    -1       -1
                   /  \        \
                  0   -1        0
                        \
                         0
         */

        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(75);
        avlTree.add(10);
        avlTree.add(33);
        avlTree.add(100);
        avlTree.add(44);

        assertEquals(7, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 10, root.getLeft().getLeft().getData());
        assertEquals((Integer) 33, root.getLeft().getRight().getData());
        assertEquals((Integer) 100, root.getRight().getRight().getData());
        assertEquals((Integer) 44, root.getLeft().getRight().getRight().getData());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getRight().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight().getRight());
        assertEquals(null, root.getLeft().getRight().getRight().getLeft());

        // Check heights used in Balance Factor Calculations
        assertEquals(3, root.getHeight());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(1, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getRight().getHeight());

        // Check Balance Factors
        assertEquals(1, root.getBalanceFactor());
        assertEquals(-1, root.getLeft().getBalanceFactor());
        assertEquals(-1, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(-1, root.getLeft().getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getRight().getBalanceFactor());

    }


    /**
     * Superb!
     *
     * Now you have all the ingredients to execute your rotations!
     * Remember there are 4 different rotations that will ALWAYS balance an AVL
     * (as long as its been a balanced AVL from the start).
     *
     * The four rotations are:
     *
     *      1. Left Rotation (LL)
     *      2. Right Rotation (RR)
     *      3. Left Right Rotation (LR)
     *      4. Right Left Rotation (RL)
     *
     * We will test simple forms of these four rotations now. After those have be passed
     * we can see how it handles more complex trees.
     */


    /**
     * Test for Simple LL. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testAddLeftRotation() {
        /*
                    1                  2
                     \                / \
                      2      ->      1   3
                       \
                        3
              Another great way of looking at this
              is with variables!
                        a
                         \
                          b
                           \
                            c
              Even without knowing the values, based on BST
              hierarchy we know:
                               a < b < c
              Therefore in order to "balance" around a pivot,
              we can rewrite the tree as:
                                    b
                                   / \
                                  a   c
         */


        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(3);

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 3, root.getRight().getData());
        assertEquals((Integer) 1, root.getLeft().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * Test for Simple RR. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testAddRightRotation() {
        /*
                    3                  2
                   /                  / \
                  2          ->      1   3
                 /
                1
              Let's look at this with variables!
                            a
                           /
                          b
                         /
                        c
              Even without knowing the values, based on BST
              hierarchy we know:
                               a > b > c
              Therefore in order to "balance" around a pivot,
              we can rewrite the tree as:
                                    b
                                   / \
                                  c   a
         */


        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(1);

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 3, root.getRight().getData());
        assertEquals((Integer) 1, root.getLeft().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * Test for Simple LR. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testAddLeftRightRotationRoot() {
        /*
                3                3             2
               /                /             / \
              1     Left->     2   Right->   1   3
               \              /
                2            1
             Let's look at this with variables again!
                            a
                           /
                          b
                           \
                            c
              Even without knowing the values, based on BST
              hierarchy we know:
                                a > c > b
              Therefore in order to "balance" around a pivot,
              we can rewrite the tree as:
                                    c
                                   / \
                                  b   a
         */
        avlTree.add(3);
        avlTree.add(1);
        avlTree.add(2);

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 1, root.getLeft().getData());
        assertEquals((Integer) 3, root.getRight().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * Test for Simple RL. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testAddRightLeftRotationRoot() {
        /*
                1             1                2
                 \             \              / \
                  3  Right->    2   Left->   1   3
                 /               \
                2                 3
             Let's look at this with variables again!
                            a
                             \
                              b
                             /
                            c
              Even without knowing the values, based on BST
              hierarchy we know:
                                a < c < b
              Therefore in order to "balance" around a pivot,
              we can rewrite the tree as:
                                    c
                                   / \
                                  a   b
         */
        avlTree.add(1);
        avlTree.add(3);
        avlTree.add(2);

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 1, root.getLeft().getData());
        assertEquals((Integer) 3, root.getRight().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * If you've passed all the tests without hardcoding
     * it looks like you are good to go! Any one of those four rotations
     * could be called to completely balance the AVL every time you add a
     * new node!
     *
     * Here are some extra rotations tests to see how well your code can
     * handle some more complex trees.
     */

    @Test(timeout = TIMEOUT)
    public void testAddVariousRotation1() {
        /*
        Before Rotations:
                            50
                         /      \
                        22       99
                       /        /
                      20       82
                       \      /
                       21    55
         After Rotations:
                            50
                         /      \
                        21       82
                       /  \     /  \
                      20  22   55  99
         */

        avlTree.add(50);
        avlTree.add(22);
        avlTree.add(82);
        avlTree.add(20);
        avlTree.add(99);
        avlTree.add(21); //Rotation 1
        avlTree.add(55); //Rotation 2

        assertEquals(7, avlTree.size());

        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 21, root.getLeft().getData());
        assertEquals((Integer) 82, root.getRight().getData());
        assertEquals((Integer) 20, root.getLeft().getLeft().getData());
        assertEquals((Integer) 22, root.getLeft().getRight().getData());
        assertEquals((Integer) 99, root.getRight().getRight().getData());
        assertEquals((Integer) 55, root.getRight().getLeft().getData());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check Height
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getHeight());


        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testAddVariousRotation2() {
        /*
        Before Rotations:
                          22
                            \
                             45
                               \
                                89
                                  \
                                  99
                                  /
                                95
         After Rotations:
                            45
                          /    \
                        22      95
                               /   \
                              89   99
         */

        avlTree.add(22);
        avlTree.add(45);
        avlTree.add(89); //Rotation 1
        avlTree.add(99);
        avlTree.add(95); //Rotation 2

        assertEquals(5, avlTree.size());

        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 45, root.getData());
        assertEquals((Integer) 22, root.getLeft().getData());
        assertEquals((Integer) 95, root.getRight().getData());
        assertEquals((Integer) 89, root.getRight().getLeft().getData());
        assertEquals((Integer) 99, root.getRight().getRight().getData());
        assertEquals(null, root.getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check Height
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());


        //Check Balance
        assertEquals(-1, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testAddVariousRotation3() {
        /*
        Before Rotations:
                          22
                       /      \
                      3        62
                       \         \
                        6         102
                       / \        /
                      4   9      69
                           \
                           10
         After Rotations:
                            22
                         /      \
                        4       69
                       /  \     /  \
                      3    9   62   102
                          / \
                         6  10
         */

        avlTree.add(22);
        avlTree.add(3);
        avlTree.add(62);
        avlTree.add(6);
        avlTree.add(102);
        avlTree.add(4); // Rotation 1
        avlTree.add(69); // Rotation 2
        avlTree.add(9);
        avlTree.add(10); // Rotation 3

        assertEquals(9, avlTree.size());

        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 22, root.getData());
        assertEquals((Integer) 4, root.getLeft().getData());
        assertEquals((Integer) 69, root.getRight().getData());
        assertEquals((Integer) 3, root.getLeft().getLeft().getData());
        assertEquals((Integer) 9, root.getLeft().getRight().getData());
        assertEquals((Integer) 102, root.getRight().getRight().getData());
        assertEquals((Integer) 62, root.getRight().getLeft().getData());
        assertEquals((Integer) 6, root.getLeft().getRight().getLeft().getData());
        assertEquals((Integer) 10, root.getLeft().getRight().getRight().getData());
        assertEquals(null, root.getLeft().getRight().getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight().getLeft().getRight());
        assertEquals(null, root.getLeft().getRight().getRight().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check Height
        assertEquals(3, root.getHeight());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(1, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getRight().getHeight());


        //Check Balance
        assertEquals(1, root.getBalanceFactor());
        assertEquals(-1, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getRight().getBalanceFactor());

    }

    /****************************************************************************************
     *                             Removal Tutorial and Tests
     ****************************************************************************************/

    /**
     * PHEW!
     *
     * That was a lot of work! But oh no! We have to do it all
     * over again for Remove! Welp ¯\_(ツ)_/¯.... not exactly.
     * We can reuse all of our four rotations from before,
     * just implement them for removing shtufff.
     *
     * Same situation as before, if your're stuck, complete your AVL code
     * in the chronological order of these tests (top to bottom).
     *
     * And obvi we assume your insertion is all good to go since we
     * need to put stuff in before we take it out.
     *
     * Leggo.
     */


    /**
     * Just like a BST removal, we need to remove for three cases
     *
     *      1. No Children
     *      2. One Child
     *      3. Two Children
     *
     *  Maybe reuse some code from homework 04? (:
     *
     *  ¡¡¡BUT They want you to use the predecessor this time!!!
     *  ¡¡¡NOT successor!!! (I made this mistake)
     *
     * Let's not even worry about rotations, just get your removal down.
     *
     * I'm going to also check for recalculated height and balance factors too,
     * because otherwise there will be too many repeated tests.
     */

    /**
     * Test for no children. No Rotation.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveNoRotationZeroChildren() {
        /*
            Before
                        50
                     /      \
                    25      75
                   /  \     /  \
                  10  33   60  100
            After
                        50
                     /      \
                    25       75
                   /  \        \
                  10  33       100
         */

        Integer removal = 60;
        avlTree.add(50);
        avlTree.add(25);
        avlTree.add(75);
        avlTree.add(10);
        avlTree.add(33);
        avlTree.add(removal);
        avlTree.add(100);

        assertEquals(7, avlTree.size());

        assertSame(removal, avlTree.remove(60));

        assertEquals(6, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 10, root.getLeft().getLeft().getData());
        assertEquals((Integer) 33, root.getLeft().getRight().getData());
        assertEquals((Integer) 100, root.getRight().getRight().getData());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getLeft().getRight().getRight());
        assertEquals(null, root.getRight().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check heights
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());

        // Check balance factors
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(-1, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());


    }

    /**
     * Test for 1 child. No Rotation.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveNoRotationOneChild() {
        /*
            Before
                        50
                     /      \
                    25      75
                   /       /  \
                  10      60  100
            After
                        50
                     /      \
                    10       75
                            /  \
                           60  100
         */

        Integer removal = 25;
        avlTree.add(50);
        avlTree.add(removal);
        avlTree.add(75);
        avlTree.add(10);
        avlTree.add(60);
        avlTree.add(100);

        assertEquals(6, avlTree.size());

        assertSame(removal, avlTree.remove(25));

        assertEquals(5, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 10, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 100, root.getRight().getRight().getData());
        assertEquals(null, root.getLeft().getLeft());
        assertEquals(null, root.getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check heights
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());

        // Check balance factors
        assertEquals(-1, root.getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());

    }

    /**
     * Test for two children. No Rotation.
     *
     * Please remember to replace with Predecessor!
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveNoRotationTwoChildren() {
        /*
            Before
                        50
                     /      \
                    25      75
                   /  \     /  \
                  10  33   60  100
            After
                        33
                     /      \
                    25       75
                   /        /  \
                  10       60  100
         */

        Integer removal = 50;
        avlTree.add(removal);
        avlTree.add(25);
        avlTree.add(75);
        avlTree.add(10);
        avlTree.add(33);
        avlTree.add(60);
        avlTree.add(100);

        assertEquals(7, avlTree.size());

        assertSame(removal, avlTree.remove(50));

        assertEquals(6, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 33, root.getData());
        assertEquals((Integer) 25, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 10, root.getLeft().getLeft().getData());
        assertEquals((Integer) 60, root.getRight().getLeft().getData());
        assertEquals((Integer) 100, root.getRight().getRight().getData());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check heights
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());


        // Check balance factors
        assertEquals(0, root.getBalanceFactor());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());

    }

    /**
     * Adjective! ;)
     *
     * Almost there! We just need to implement the
     * LL, RR, LR, and RL rotations for the removal!
     * You shouldn't have to rewrite these methods! You just
     * need to decide when to call them and on what nodes.
     *
     * Hopefully seeing some examples will help you with that!
     */

    /**
     * Test for Simple LL. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveLeftRotation() {
        /*
                    1                      1
                   / \                      \
                  0    2     Remove 0 ->     2
                        \                     \
                         3                     3
              Look familiar???
                        a
                         \
                          b
                           \
                            c
              Where:
                       a < b < c
              Balanced:
                          b
                         / \
                        a   c
              Actual:
                          2
                         / \
                        1   3
         */


        Integer removal = 0;
        avlTree.add(1);
        avlTree.add(2);
        avlTree.add(removal);
        avlTree.add(3);

        assertEquals(4, avlTree.size());

        assertSame(removal, avlTree.remove(0));

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 3, root.getRight().getData());
        assertEquals((Integer) 1, root.getLeft().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * Test for Simple RR. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveRightRotation() {
        /*
                    3                         3
                   / \                       /
                  2   4     Remove 4 ->     2
                 /                         /
                1                         1
              Look familiar???
                            a
                           /
                          b
                         /
                        c
              Where:
                       a > b > c
              Balanced:
                          b
                         / \
                        c   a
              Actual:
                          2
                         / \
                        1   3
         */


        Integer removal = 4;
        avlTree.add(3);
        avlTree.add(2);
        avlTree.add(removal);
        avlTree.add(1);

        assertEquals(4, avlTree.size());

        assertSame(removal, avlTree.remove(4));

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 3, root.getRight().getData());
        assertEquals((Integer) 1, root.getLeft().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * Test for Simple LR. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveLeftRightRotationRoot() {
        /*
                    3                         3
                  /   \                      /
                 1     4     Remove 4 ->    1
                  \                          \
                   2                          2
              Look familiar???
                            a
                           /
                          b
                           \
                            c
              Where:
                       a > c > b
              Balanced:
                          c
                         / \
                        b   a
              Actual:
                          2
                         / \
                        1   3
         */

        Integer removal = 4;
        avlTree.add(3);
        avlTree.add(1);
        avlTree.add(removal);
        avlTree.add(2);

        assertEquals(4, avlTree.size());

        assertSame(removal, avlTree.remove(4));

        assertEquals(3, avlTree.size());

        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 1, root.getLeft().getData());
        assertEquals((Integer) 3, root.getRight().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * Test for Simple RL. See Notes in Test Method For Help.
     */
    @Test(timeout = TIMEOUT)
    public void testRemoveRightLeftRotationRoot() {
        /*
                    1                      1
                  /   \                     \
                 0     3     Remove 0 ->     3
                      /                     /
                     2                     2
              Look familiar???
                        a
                         \
                          b
                         /
                        c
              Where:
                       a < c < b
              Balanced:
                          c
                         / \
                        a   b
              Actual:
                          2
                         / \
                        1   3
         */

        Integer removal = 0;

        avlTree.add(1);
        avlTree.add(3);
        avlTree.add(removal);
        avlTree.add(2);

        assertEquals(4, avlTree.size());

        assertSame(removal, avlTree.remove(0));

        assertEquals(3, avlTree.size());
        // Check Tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 2, root.getData());
        assertEquals((Integer) 1, root.getLeft().getData());
        assertEquals((Integer) 3, root.getRight().getData());

        // Check Height
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getLeft().getHeight());

        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());

    }

    /**
     * WOW!
     *
     * Can you believe it. Me neither I'm exhausted from writing this LOL.
     * Here's a couple more removal test for more complex trees. Like before,
     * if the tree is already balanced before removal you will be able
     * to rebalance it after removal with one of the four rotations.
     *
     */

    /**
     * If you've passed all the tests without hardcoding
     * it looks like you are good to go! Any one of those four rotations
     * could be called to completely balance the AVL every time you add a
     * new node!
     *
     * Here are some extra rotations tests to see how well your code can
     * handle some more complex trees.
     */

    @Test(timeout = TIMEOUT)
    public void testRemoveVariousRotation1() {
        /*
        Before Removals:
                            50
                         /      \
                        25       82
                       /        /  \
                      20       55  99
                     /  \        \
                    0    21       75
        After Removals (25, 99) but Before Rotations:
                            50
                         /      \
                        21       82
                       /        /
                      20       55
                     /          \
                    0            75
         After Rotations:
                            50
                         /      \
                        20       75
                       /  \     /  \
                      0  21    55  82
         */

        Integer removal1 = 25;
        Integer removal2 = 99;

        avlTree.add(50);
        avlTree.add(removal1);
        avlTree.add(82);
        avlTree.add(20);
        avlTree.add(55);
        avlTree.add(removal2);
        avlTree.add(0);
        avlTree.add(21);
        avlTree.add(75);


        assertEquals(9, avlTree.size());

        assertSame(removal1, avlTree.remove(25));

        assertEquals(8, avlTree.size());

        assertSame(removal2, avlTree.remove(99));

        assertEquals(7, avlTree.size());

        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 50, root.getData());
        assertEquals((Integer) 20, root.getLeft().getData());
        assertEquals((Integer) 75, root.getRight().getData());
        assertEquals((Integer) 0, root.getLeft().getLeft().getData());
        assertEquals((Integer) 21, root.getLeft().getRight().getData());
        assertEquals((Integer) 82, root.getRight().getRight().getData());
        assertEquals((Integer) 55, root.getRight().getLeft().getData());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check Height
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getHeight());


        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveVariousRotation2() {
        /*
         Before Removal:
                            45
                          /    \
                        22      95
                          \    /   \
                          35  89   99
                                  /
                                 96
         After Removal (45), But Before Rotation:
                            35
                          /    \
                        22      95
                               /   \
                              89   99
                                  /
                                 96
         After Rotation:
                               95
                            /      \
                           35      99
                          /  \     /
                         22  89   96
         */

        Integer removal = 45;

        avlTree.add(removal);
        avlTree.add(22);
        avlTree.add(95);
        avlTree.add(35);
        avlTree.add(89);
        avlTree.add(99);
        avlTree.add(96);

        assertEquals(7, avlTree.size());


        assertSame(removal, avlTree.remove(45));

        assertEquals(6, avlTree.size());

        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 95, root.getData());
        assertEquals((Integer) 35, root.getLeft().getData());
        assertEquals((Integer) 99, root.getRight().getData());
        assertEquals((Integer) 22, root.getLeft().getLeft().getData());
        assertEquals((Integer) 89, root.getLeft().getRight().getData());
        assertEquals((Integer) 96, root.getRight().getLeft().getData());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getRight());



        // Check Height
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());


        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(1, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());

    }

    @Test(timeout = TIMEOUT)
    public void testRemoveVariousRotation3() {
        /*
         Before Removal:
                            22
                         /      \
                        4       69
                       /  \     /  \
                      3    9   62   102
                          / \
                         6  10
          After Removal (4), But Before Rotations:
                            22
                         /      \
                        3       69
                          \     /  \
                           9   62   102
                          / \
                         6   10
          After Rotation 1:
                            22
                         /      \
                        9       69
                       /  \     /  \
                      3    10   62   102
                       \
                        6
          After Removal (22), But Before Rotations:
                            10
                         /      \
                        9       69
                       /        /  \
                      3        62  102
                       \
                        6
          After Rotation 2 (This is when 22 is removed):
                            10
                          /    \
                         6      69
                        / \    /  \
                       3   9  62  102
         */

        Integer removal1 = 4;
        Integer removal2 = 22;


        avlTree.add(removal2);
        avlTree.add(removal1);
        avlTree.add(69);
        avlTree.add(3);
        avlTree.add(9);
        avlTree.add(62);
        avlTree.add(102);
        avlTree.add(6);
        avlTree.add(10);

        assertEquals(9, avlTree.size());

        assertSame(removal1, avlTree.remove(4));

        assertEquals(8, avlTree.size());

        assertSame(removal2, avlTree.remove(22));

        assertEquals(7, avlTree.size());


        // Check tree
        AVLNode<Integer> root = avlTree.getRoot();
        assertEquals((Integer) 10, root.getData());
        assertEquals((Integer) 6, root.getLeft().getData());
        assertEquals((Integer) 69, root.getRight().getData());
        assertEquals((Integer) 3, root.getLeft().getLeft().getData());
        assertEquals((Integer) 9, root.getLeft().getRight().getData());
        assertEquals((Integer) 102, root.getRight().getRight().getData());
        assertEquals((Integer) 62, root.getRight().getLeft().getData());
        assertEquals(null, root.getLeft().getRight().getLeft());
        assertEquals(null, root.getLeft().getRight().getRight());
        assertEquals(null, root.getLeft().getLeft().getLeft());
        assertEquals(null, root.getLeft().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getRight());
        assertEquals(null, root.getRight().getLeft().getLeft());
        assertEquals(null, root.getRight().getRight().getRight());
        assertEquals(null, root.getRight().getRight().getLeft());

        // Check Height
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getRight().getHeight());


        //Check Balance
        assertEquals(0, root.getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

    }


    /**
     * Okay so there that last one is especially tricky. If you fail it, make sure
     * you are recalculating your heights, balance factors, etc. as you search for a predecessor.
     * If you don't constantly rebalance your tree will fall apart.
     *
     * "But Ben that's so inefficient why would I do that??"
     *
     * Correct me if I'm wrong (probably), but calculating the height, balance factor, and
     * rebalancing are all O(1) operations. They take exactly the same amount of time for every entry.
     * So it doesn't change your efficiency at all! Cool.
     *
     *
     * Okay so if you made it this far and appreciated this please hit up this Soundcloud link:
     *
     * **************************************************************************************
     *      https://soundcloud.com/n04hmusic/a-hakendrick-lamar-take-on-backseat-mashup
     * **************************************************************************************
     *
     * LOL jk, I don't have a Soundcloud but you should reward yourself by going to it anyway... I
     * PROMISE you will not be disappointed. ;)
     */
}