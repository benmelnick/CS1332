import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class AVLTest {

    private AVL<Integer> tree;
    public static final int FUZZ_LENGTH = 100000;

    @Before
    public void setUp() throws Exception {
        tree = new AVL<>();
    }

    @Test
    public void addAscending() {
        fullTest(0, -1,
                null,
                null,                                          null,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(0);
        fullTest(1, 0,
                0,
                null,                                          null,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(1);
        fullTest(2, 1,
                0,
                null,                                          1,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(2);
        fullTest(3, 1,
                1,
                0,                                          2,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(3);
        fullTest(4, 2,
                1,
                0,                                          2,
                null,                  null,                  null,                  3,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(4);
        fullTest(5, 2,
                1,
                0,                                          3,
                null,                  null,                  2,                  4,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(5);
        fullTest(6, 2,
                3,
                1,                                          4,
                0,                      2,                    null,                   5,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(6);
        fullTest(7, 2,
                3,
                1,                                          5,
                0,                      2,                    4,                   6,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        for(int i=7;i<31;i++)tree.add(i);
        fullTest(31, 4,
                15,
                7,                                          23,
                3,                      11,                    19,                   27,
                1,        5,             9,       13,          17,       21,          25,       29,
                0, 2,      4, 6,         8, 10,   12, 14,      16, 18,    20, 22,     24, 26,   28, 30);
    }

    @Test
    public void addDescending() {
        fullTest(0, -1,
                null,
                null,                                          null,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(30);
        fullTest(1, 0,
                30,
                null,                                          null,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(29);
        fullTest(2, 1,
                30,
                29,                                          null,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(28);
        fullTest(3, 1,
                29,
                28,                                          30,
                null,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(27);
        fullTest(4, 2,
                29,
                28,                                          30,
                27,                  null,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(26);
        fullTest(5, 2,
                29,
                27,                                          30,
                26,                  28,                  null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(25);
        fullTest(6, 2,
                27,
                26,                                          29,
                25,                      null,                    28,                   30,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        tree.add(24);
        fullTest(7, 2,
                27,
                25,                                          29,
                24,                      26,                    28,                   30,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        //The following are duplicates.
        tree.add(27); tree.add(25); tree.add(28);
        fullTest(7, 2,
                27,
                25,                                          29,
                24,                      26,                    28,                   30,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        for(int i=23;i>=0;i--)tree.add(i);
        fullTest(31, 4,
                15,
                7,                                          23,
                3,                      11,                    19,                   27,
                1,        5,             9,       13,          17,       21,          25,       29,
                0, 2,      4, 6,         8, 10,   12, 14,      16, 18,    20, 22,     24, 26,   28, 30);
    }

    @Test
    public void removeGetContains() {
        for(int i=1;i<=6;i++)tree.add(i);
        Integer sevenA = new Integer(7);
        Integer sevenB = new Integer(7);
        tree.add(sevenA);
        fullTest(7, 2,
                4,
                2,                                          6,
                1,                    3,                        5,                   7,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree does not contain an element it should have.", tree.contains(sevenA));
        assertTrue("Your tree does not contain an element it should have."
                + " Note: an object with the same data but a different reference was used.", tree.contains(sevenB));
        assertSame("Your tree did not return the correct element when searching.", sevenA, tree.get(sevenA));
        assertNotSame("Your tree returned the data passed in and not the data in the tree equal to the passed data.", sevenB, tree.get(sevenB));
        assertSame("Your tree did not return the correct element when searching."
                + " Note: an object with the same data but a different reference was used.", sevenA, tree.get(sevenB));
        assertSame("Your tree did not return the correct data during removal.", sevenA, tree.remove(sevenB));
        try {
            tree.remove(sevenA);
            fail("Your tree did not throw an exception when removing nonexistent data.");
        } catch (NoSuchElementException e) {}
        try {
            tree.remove(sevenB);
            fail("Your tree did not throw an exception when removing nonexistent data.");
        } catch (NoSuchElementException e) {}
        try {
            tree.get(sevenB);
            fail("Your tree did not throw an exception when searching for nonexistent data.");
        } catch (NoSuchElementException e) {}
        try {
            tree.get(sevenA);
            fail("Your tree did not throw an exception when searching for nonexistent data.");
        } catch (NoSuchElementException e) {}
        assertFalse("Your tree claims to contain data it should not have.", tree.contains(sevenA));
        assertFalse("Your tree claims to contain data it should not have.", tree.contains(sevenB));
        fullTest(6, 2,
                4,
                2,                                          6,
                1,                    3,                        5,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(2));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)2, tree.get(2));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)2, tree.remove(2));
        try{
            fullTest(5, 2,
                    4,
                    1,                                          6,
                    null,                    3,                        5,                   null,
                    null,       null,      null,       null,       null,       null,      null,      null,
                    null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        } catch (Exception|Error e) {
            System.out.println("Remember to use the predecessor when removing a node with two children.");
            throw e;
        }
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(6));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)6, tree.get(6));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)6, tree.remove(6));
        fullTest(4, 2,
                4,
                1,                                          5,
                null,                    3,                 null,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(5));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)5, tree.get(5));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)5, tree.remove(5));
        //Double rotation: left, then right
        fullTest(3, 1,
                3,
                1,                                          4,
                null,                    null,                 null,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(3));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)3, tree.get(3));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)3, tree.remove(3));
        fullTest(2, 1,
                1,
                null,                                            4,
                null,                  null,                   null,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(1));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)1, tree.get(1));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)1, tree.remove(1));
        fullTest(1, 0,
                4,
                null,                                          null,
                null,                  null,                   null,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(4));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)4, tree.get(4));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)4, tree.remove(4));
        fullTest(0, -1,
                null,
                null,                                          null,
                null,                  null,                   null,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        //The following is to set up a single left rotation caused by removal
        tree.add(5);tree.add(4);tree.add(7);tree.add(9);
        fullTest(4, 2,
                5,
                4,                                               7,
                null,                  null,                   null,                   9,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(4));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)4, tree.get(4));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)4, tree.remove(4));
        fullTest(3, 1,
                7,
                5,                                               9,
                null,                  null,                   null,                   null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        //This will set up a single right rotation
        tree.add(3);
        fullTest(4, 2,
                7,
                5,                                             9,
                3,                    null,                   null,                 null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(9));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)9, tree.get(9));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)9, tree.remove(9));
        fullTest(3, 1,
                5,
                3,                                             7,
                null,                 null,                   null,                 null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        //This will set up a double rotation: right, then left
        tree.add(6);
        fullTest(4, 2,
                5,
                3,                                             7,
                null,                 null,                     6,                 null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(3));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)3, tree.get(3));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)3, tree.remove(3));
        fullTest(3, 1,
                6,
                5,                                             7,
                null,                 null,                   null,                 null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        //This last setup will test for AVL updates caused by the relocation of a predecessor.
        tree.add(2);tree.add(0);tree.add(9);tree.add(11);tree.add(4);tree.add(1);tree.add(-1);tree.add(10);tree.add(-2);
        fullTest(12, 4,
                6,
                2,                                             9,
                0,                    5,                     7,                      11,
                -1,         1,         4,         null,       null,       null,       10,        null,
                -2, null, null, null, null, null, null, null, null, null, null, null,null, null, null, null);
        //Removing 6 will cause it to move 5 to the root position. This will also put 4 in 5's place.
        //This causes 2 to become unbalanced. 2 should still be updated even though it is not above 6.
        //The intermediate step before AVL balancing will look like this:
        //             5
        //         2       9
        //     0     4   7    11
        //  -1   1          10
        //-2
        assertTrue("Your tree claims to not have a value it should have.", tree.contains(6));
        assertEquals("Your tree returned the wrong value when searching.", (Integer)6, tree.get(6));
        assertEquals("Your tree removed the wrong value when searching.", (Integer)6, tree.remove(6));
        fullTest(11, 3,
                5,
                0,                                             9,
                -1,                    2,                      7,                      11,
                -2,        null,          1,          4,        null,       null,        10,         null,
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        try {
            tree.remove(null);
            fail("Your tree did not throw an exception when removing null data.");
        } catch (IllegalArgumentException e){}
    }

    @Test
    public void constructorAndClear(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(4);
        tree = new AVL<>(list);
        fullTest(5, 2,
                3,
                1,                                             5,
                null,                  2,                      4,                 null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
        try {
            tree = new AVL<>(null);
            fail("The constructor that accepts a list failed to throw an exception when a null list was passed in.");
        } catch (IllegalArgumentException e){}
        try {
            list.add(null);
            tree = new AVL<>(list);
            fail("The constructor that accepts a list failed to throw an exception when a list containing null data was passed in.");
        } catch (IllegalArgumentException e){}
        tree.clear();
        fullTest(0, -1,
                null,
                null,                                        null,
                null,                  null,                 null,                  null,
                null,       null,      null,       null,       null,       null,      null,      null,
                null, null, null, null,null, null, null, null, null, null, null, null,null, null, null, null);
    }

    @Test
    public void maxDeepestNode() {
        try {
            assertNull("Your tree's maxDeepestNode method is not returning null when the tree is empty.", tree.maxDeepestNode());
            tree.add(11);
            assertEquals("Your tree's maxDeepestNode method returned the wrong value.", (Integer) 11, tree.maxDeepestNode());
            tree.add(9);
            assertEquals("Your tree's maxDeepestNode method returned the wrong value.", (Integer) 9, tree.maxDeepestNode());
            tree.add(13);
            assertEquals("Your tree's maxDeepestNode method returned the wrong value.", (Integer) 13, tree.maxDeepestNode());
            tree.add(10);
            assertEquals("Your tree's maxDeepestNode method returned the wrong value.", (Integer) 10, tree.maxDeepestNode());
            tree.add(12);
            assertEquals("Your tree's maxDeepestNode method returned the wrong value.", (Integer) 12, tree.maxDeepestNode());
            tree.clear();
        } catch (Exception|Error e) {
            System.out.println(getActualTreeDiagram());
            throw e;
        }
    }

    @Test
    public void deepestCommonAncestor() {
        try {
            addAscending();
        } catch (Exception|Error e) {
            System.out.println("Please debug the add method.");
            throw e;
        }
        //                              15
        //              07                              23
        //      03              11              19              27
        //  01      05      09      13      17      21      25      29
        //00  02  04  06  08  10  12  14  16  18  20  22  24  26  28  30
        for(int i=-1;i<=31;i++){
            for(int j=-1;j<=31;j++){
                if (i == -1 || j == -1 || i == 31 || j == 31) {
                    try {
                        tree.deepestCommonAncestor(i, j);
                        fail("The deepestCommonAncestor method did not throw an exception when searching for the ancestor of a nonexistent element.");
                    } catch (NoSuchElementException e) {}
                } else {
                    assertEquals("The deepestCommonAncestor function returned the wrong value.", getCommonAncestorForBalanced31Tree(i, j), tree.deepestCommonAncestor(i, j));
                }
            }
        }
        try {
            tree.deepestCommonAncestor(null, null);
            fail("The deepestCommonAncestor method did not throw an exception when a null parameter was provided.");
        } catch (IllegalArgumentException e) {}
        try {
            tree.deepestCommonAncestor(null, 15);
            fail("The deepestCommonAncestor method did not throw an exception when a null parameter was provided.");
        } catch (IllegalArgumentException e) {}
        try {
            tree.deepestCommonAncestor(15, null);
            fail("The deepestCommonAncestor method did not throw an exception when a null parameter was provided.");
        } catch (IllegalArgumentException e) {}
    }

    private Integer getCommonAncestorForBalanced31Tree(int a, int b){
        a+=1;
        b+=1;
        if (a==b) return a-1;
        if (a%2==1){
            a+=(a%4-2)*-1;
        }
        if (b%2==1){
            b+=(b%4-2)*-1;
        }
        if (a==b) return a-1;
        if (a%4==2){
            a+=(a%8-4)*-1;
        }
        if (b%4==2){
            b+=(b%8-4)*-1;
        }
        if (a==b) return a-1;
        if (a%8==4){
            a+=(a%16-8)*-1;
        }
        if (b%8==4){
            b+=(b%16-8)*-1;
        }
        if (a==b) return a-1;
        return 15;
    }

    @Test
    public void fuzz(){
        TreeSet<Integer> oracle = new TreeSet<>();
        Random rand = new Random(1000);
        int i = 0;
        try {
            for (i = 0; i < FUZZ_LENGTH; i++) {
                //IF YOUR TEST IS FAILING, do the following:
                //Uncomment the following lines of code.
                //Change the number in the if statement to be the iteration that is failing.
                //(The number of the iteration is printed in the console when this test fails.)
                //Place a breakpoint on the line in this if statement (click the space next to the line number).
                //Debug this test (right click on the circle next to the method declaration, and click Debug Test).
                //Step through your code using the buttons in the debug console, and try to find what went wrong.
//                if (i == 0) {
//                    System.out.println("Iteration "+i+" reached.");
//                }
                int n = rand.nextInt(15);
                if (rand.nextBoolean()) {
                    int oldSize = oracle.size();
                    oracle.add(n);
                    tree.add(n);
                    if (oldSize == oracle.size()) {
                        assertEquals("When a duplicate element, "+n+", was added, your tree's size variable did not match the expected size.", oracle.size(), tree.size());
                        assertEquals("When a duplicate element, "+n+", was added, your tree's size variable did not match the tree's actual size.", getActualSize(tree.getRoot()), tree.size());
                    } else {
                        assertEquals("When a non-duplicate element, "+n+", was added, your tree's size variable did not match the expected size.", oracle.size(), tree.size());
                        assertEquals("When a non-duplicate element, "+n+", was added, your tree's size variable did not match the tree's actual size.", getActualSize(tree.getRoot()), tree.size());
                    }
                } else {
                    if (oracle.contains(n)) {
                        assertEquals("Your tree does not contain an element, "+n+", that it should have.", oracle.contains(n), tree.contains(n));
                        assertEquals("Your tree did not return the correct element, "+n+", when searching for an element.", (Integer) n, tree.get(n));
                        assertEquals("Your tree did not return the correct element, "+n+", when removing an element.", (Integer) n, tree.remove(n));
                        oracle.remove(n);
                        assertEquals("When an element, "+n+", was removed, your tree'e size variable did not match the expected size.", oracle.size(), tree.size());
                        assertEquals("When an element, "+n+", was removed, your tree'e size variable did not match the tree's actual size.", getActualSize(tree.getRoot()), tree.size());
                    } else {
                        assertEquals("Your tree claims to contain an element, "+n+", that it should not have.", oracle.contains(n), tree.contains(n));
                        try {
                            tree.get(n);
                            fail("Your tree failed to throw an exception when searching for an element, "+n+", that it should not have.");
                        } catch (NoSuchElementException e) {
                        }
                        try {
                            tree.remove(n);
                            fail("Your tree failed to throw an exception when removing an element, "+n+", that it should not have.");
                        } catch (NoSuchElementException e) {
                        }
                        assertEquals("When trying to remove a nonexistent element, "+n+", from the tree, your tree properly threw an exception,"
                                + " but its size variable does not match the expected value.", oracle.size(), tree.size());
                        assertEquals("When trying to remove a nonexistent element, "+n+", from the tree, your tree properly threw an exception,"
                                + " but its size variable does not match the tree's actual size.", getActualSize(tree.getRoot()), tree.size());
                    }
                }
                assertTrue("Your tree is unbalanced at some point.", checkBalanced(tree.getRoot()));
                if (i % (FUZZ_LENGTH/100) == 0 && oracle.size() > 0) {
                    Integer[] set = oracle.toArray(new Integer[0]);
                    int a = rand.nextInt(set.length);
                    int b = rand.nextInt(set.length);
                    int c1 = set[a];
                    int c2 = set[b];
                    int ancestor = tree.deepestCommonAncestor(c1, c2);
                    if (c1 > c2) {
                        c1 = c1 ^ c2;
                        c2 = c1 ^ c2;
                        c1 = c1 ^ c2;
                    }
                    if (c1 > ancestor || c2 < ancestor) {
                        fail("Your tree's deepestCommonAncestor method returned a result, "+ancestor+", that cannot possibly be the common ancestor of the two given values, "+c1+" and "+c2+".");
                    }
                }
            }
        } catch (AssertionError|Exception e) {
            System.out.println(getActualTreeDiagram());
            System.out.println("An exception was thrown when i = "+i+".");
            throw e;
        }
    }

    private static boolean checkBalanced(AVLNode node){
        if (node == null) return true;
        else if (Math.abs(node.getBalanceFactor())>1) return false;
        else return checkBalanced(node.getLeft()) && checkBalanced(node.getRight());
    }

    private static int getActualSize(AVLNode node){
        if (node == null) return 0;
        else return 1 + getActualSize(node.getLeft()) + getActualSize(node.getRight());
    }

    /**
     * Tests that the tree has an exact shape and size.
     */
    private void fullTest(int size, int height,Integer root, Integer l, Integer r, Integer ll, Integer lr, Integer rl, Integer rr,
                          Integer lll, Integer llr, Integer lrl, Integer lrr, Integer rll, Integer rlr, Integer rrl, Integer rrr,
                          Integer llll, Integer lllr, Integer llrl,Integer llrr, Integer lrll, Integer lrlr, Integer lrrl, Integer lrrr,
                          Integer rlll, Integer rllr, Integer rlrl, Integer rlrr, Integer rrll, Integer rrlr, Integer rrrl, Integer rrrr) {
        String expected = generateTreeDiagram(size,height,root,l,r,ll,lr,rl,rr,lll,llr,lrl,lrr,rll,rlr,rrl,rrr,llll,lllr,llrl,llrr,lrll,lrlr,lrrl,lrrr,rlll,rllr,rlrl,rlrr,rrll,rrlr,rrrl,rrrr);
        String actual = getActualTreeDiagram();
        if(!expected.equals(actual)){
            fail("Your tree's structure and size variable did not match the expected values.\n"
                    + "Expected diagram:\n\n"
                    + expected + "\n\n"
                    + "Actual diagram:\n\n"
                    + actual + "\n");
            //If your test fails here, check the stack trace and look for the
            //line number inside the test method (not this method, fullTest).
            //That line number is the correct location where the test failed.
            //By looking backwards through the test's code, you should be able
            //to figure out what steps led to your implementation failing.
        }
    }

    private String generateTreeDiagram(int size, int height, Integer root, Integer l, Integer r, Integer ll, Integer lr, Integer rl, Integer rr,
                                       Integer lll, Integer llr, Integer lrl, Integer lrr, Integer rll, Integer rlr, Integer rrl, Integer rrr,
                                       Integer llll, Integer lllr, Integer llrl, Integer llrr, Integer lrll, Integer lrlr, Integer lrrl, Integer lrrr,
                                       Integer rlll, Integer rllr, Integer rlrl, Integer rlrr, Integer rrll, Integer rrlr, Integer rrrl, Integer rrrr) {
        String a = root==null?"NUL":String.format("%03d",root);
        String b = l==null?"NUL":String.format("%03d",l);
        String c = r==null?"NUL":String.format("%03d",r);
        String d = ll==null?"NUL":String.format("%03d",ll);
        String e = lr==null?"NUL":String.format("%03d",lr);
        String f = rl==null?"NUL":String.format("%03d",rl);
        String g = rr==null?"NUL":String.format("%03d",rr);
        String h = lll==null?"NUL":String.format("%03d",lll);
        String i = llr==null?"NUL":String.format("%03d",llr);
        String j = lrl==null?"NUL":String.format("%03d",lrl);
        String k = lrr==null?"NUL":String.format("%03d",lrr);
        String m = rll==null?"NUL":String.format("%03d",rll);
        String n = rlr==null?"NUL":String.format("%03d",rlr);
        String o = rrl==null?"NUL":String.format("%03d",rrl);
        String p = rrr==null?"NUL":String.format("%03d",rrr);

        String q = llll==null?"NUL":String.format("%03d",llll);
        String s = lllr==null?"NUL":String.format("%03d",lllr);
        String t = llrl==null?"NUL":String.format("%03d",llrl);
        String u = llrr==null?"NUL":String.format("%03d",llrr);
        String v = lrll==null?"NUL":String.format("%03d",lrll);
        String w = lrlr==null?"NUL":String.format("%03d",lrlr);
        String x = lrrl==null?"NUL":String.format("%03d",lrrl);
        String y = lrrr==null?"NUL":String.format("%03d",lrrr);
        String z = rlll==null?"NUL":String.format("%03d",rlll);
        String aa = rllr==null?"NUL":String.format("%03d",rllr);
        String bb = rlrl==null?"NUL":String.format("%03d",rlrl);
        String cc = rlrr==null?"NUL":String.format("%03d",rlrr);
        String dd = rrll==null?"NUL":String.format("%03d",rrll);
        String ee = rrlr==null?"NUL":String.format("%03d",rrlr);
        String ff = rrrl==null?"NUL":String.format("%03d",rrrl);
        String gg = rrrr==null?"NUL":String.format("%03d",rrrr);

        return ("  Size: SS                    A--                  Height: HH  "+"\n"+
                "                _______________|_______________                "+"\n"+
                "               |                               |               "+"\n"+
                "              B--                             C--              "+"\n"+
                "        _______|_______                 _______|_______        "+"\n"+
                "       |               |               |               |       "+"\n"+
                "      D--             E--             F--             G--      "+"\n"+
                "    ___|___         ___|___         ___|___         ___|___    "+"\n"+
                "   |       |       |       |       |       |       |       |   "+"\n"+
                "  H--     I--     J--     K--     M--     N--     O--     P--  "+"\n"+
                "  _|_     _|_     _|_     _|_     _|_     _|_     _|_     _|_  "+"\n"+
                " |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   | "+"\n"+
                "Q-- S-- T-- U-- V-- W-- X-- Y-- Z-- AA- BB- CC- DD- EE- FF- GG-")
                .replaceFirst("SS", String.format("%2d", size))
                .replaceFirst("HH", String.format("%2d", height))
                .replaceFirst("A--",a)
                .replaceFirst("B--",b)
                .replaceFirst("C--",c)
                .replaceFirst("D--",d)
                .replaceFirst("E--",e)
                .replaceFirst("F--",f)
                .replaceFirst("G--",g)
                .replaceFirst("H--",h)
                .replaceFirst("I--",i)
                .replaceFirst("J--",j)
                .replaceFirst("K--",k)
                .replaceFirst("M--",m)
                .replaceFirst("N--",n)
                .replaceFirst("O--",o)
                .replaceFirst("P--",p)
                .replaceFirst("Q--",q)
                .replaceFirst("S--",s)
                .replaceFirst("T--",t)
                .replaceFirst("U--",u)
                .replaceFirst("V--",v)
                .replaceFirst("W--",w)
                .replaceFirst("X--",x)
                .replaceFirst("Y--",y)
                .replaceFirst("Z--",z)
                .replaceFirst("AA-",aa)
                .replaceFirst("BB-",bb)
                .replaceFirst("CC-",cc)
                .replaceFirst("DD-",dd)
                .replaceFirst("EE-",ee)
                .replaceFirst("FF-",ff)
                .replaceFirst("GG-",gg);
    }

    private String getActualTreeDiagram(){
        Integer a,b,c,d,e,f,g,h,i,j,k,m,n,o,p,
                q,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg;
        a=b=c=d=e=f=g=h=i=j=k=m=n=o=p=q=s=t=u=v=w=x=y=z=aa=bb=cc=dd=ee=ff=gg=null;
        AVLNode<Integer> node = tree.getRoot();
        if(node!=null){
            a=node.getData();
            if(node.getLeft()!=null){
                b=node.getLeft().getData();
                if(node.getLeft().getLeft()!=null){
                    d=node.getLeft().getLeft().getData();
                    if(node.getLeft().getLeft().getLeft()!=null){
                        h=node.getLeft().getLeft().getLeft().getData();
                        if(node.getLeft().getLeft().getLeft().getLeft()!=null){
                            q=node.getLeft().getLeft().getLeft().getLeft().getData();
                        }
                        if(node.getLeft().getLeft().getLeft().getRight()!=null){
                            s=node.getLeft().getLeft().getLeft().getRight().getData();
                        }
                    }
                    if(node.getLeft().getLeft().getRight()!=null){
                        i=node.getLeft().getLeft().getRight().getData();
                        if(node.getLeft().getLeft().getRight().getLeft()!=null){
                            t=node.getLeft().getLeft().getRight().getLeft().getData();
                        }
                        if(node.getLeft().getLeft().getRight().getRight()!=null){
                            u=node.getLeft().getLeft().getRight().getRight().getData();
                        }
                    }
                }
                if(node.getLeft().getRight()!=null){
                    e=node.getLeft().getRight().getData();
                    if(node.getLeft().getRight().getLeft()!=null){
                        j=node.getLeft().getRight().getLeft().getData();
                        if(node.getLeft().getRight().getLeft().getLeft()!=null){
                            v=node.getLeft().getRight().getLeft().getLeft().getData();
                        }
                        if(node.getLeft().getRight().getLeft().getRight()!=null){
                            w=node.getLeft().getRight().getLeft().getRight().getData();
                        }
                    }
                    if(node.getLeft().getRight().getRight()!=null){
                        k=node.getLeft().getRight().getRight().getData();
                        if(node.getLeft().getRight().getRight().getLeft()!=null){
                            x=node.getLeft().getRight().getRight().getLeft().getData();
                        }
                        if(node.getLeft().getRight().getRight().getRight()!=null){
                            y=node.getLeft().getRight().getRight().getRight().getData();
                        }
                    }
                }
            }
            if(node.getRight()!=null){
                c=node.getRight().getData();
                if(node.getRight().getLeft()!=null){
                    f=node.getRight().getLeft().getData();
                    if(node.getRight().getLeft().getLeft()!=null){
                        m=node.getRight().getLeft().getLeft().getData();
                        if(node.getRight().getLeft().getLeft().getLeft()!=null){
                            z=node.getRight().getLeft().getLeft().getLeft().getData();
                        }
                        if(node.getRight().getLeft().getLeft().getRight()!=null){
                            aa=node.getRight().getLeft().getLeft().getRight().getData();
                        }
                    }
                    if(node.getRight().getLeft().getRight()!=null){
                        n=node.getRight().getLeft().getRight().getData();
                        if(node.getRight().getLeft().getRight().getLeft()!=null){
                            bb=node.getRight().getLeft().getRight().getLeft().getData();
                        }
                        if(node.getRight().getLeft().getRight().getRight()!=null){
                            cc=node.getRight().getLeft().getRight().getRight().getData();
                        }
                    }
                }
                if(node.getRight().getRight()!=null){
                    g=node.getRight().getRight().getData();
                    if(node.getRight().getRight().getLeft()!=null){
                        o=node.getRight().getRight().getLeft().getData();
                        if(node.getRight().getRight().getLeft().getLeft()!=null){
                            dd=node.getRight().getRight().getLeft().getLeft().getData();
                        }
                        if(node.getRight().getRight().getLeft().getRight()!=null){
                            ee=node.getRight().getRight().getLeft().getRight().getData();
                        }
                    }
                    if(node.getRight().getRight().getRight()!=null){
                        p=node.getRight().getRight().getRight().getData();
                        if(node.getRight().getRight().getRight().getLeft()!=null){
                            ff=node.getRight().getRight().getRight().getLeft().getData();
                        }
                        if(node.getRight().getRight().getRight().getRight()!=null){
                            gg=node.getRight().getRight().getRight().getRight().getData();
                        }
                    }
                }
            }
        }
        return generateTreeDiagram(tree.size(),tree.height(),a,b,c,d,e,f,g,h,i,j,k,m,n,o,p,q,s,t,u,v,w,x,y,z,aa,bb,cc,dd,ee,ff,gg);
    }
}