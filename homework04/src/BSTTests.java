import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class BSTTests {

    BST<Integer> tree;
    @Before
    public void setUp() throws Exception {
        tree = new BST<>();
    }

    @Test
    public void construct(){
        ArrayList<Integer> arr = new ArrayList<>(7);
        arr.add(0);
        arr.add(1);
        arr.add(-1);
        arr.add(3);
        arr.add(2);
        arr.add(-2);
        arr.add(-3);
        tree = new BST<>(arr);
        fullTest(7,
                0,
                -1,1,
                -2,null,null,3,
                -3,null,null,null,null,null,2,null);
    }

    @Test
    public void add() {
        tree.add(100);
        fullTest(1,
                100,
                null,null,
                null,null,null,null,
                null,null,null,null,null,null,null,null);
        tree.add(50);
        fullTest(2,
                100,
                50,null,
                null,null,null,null,
                null,null,null,null,null,null,null,null);
        tree.add(75);
        fullTest(3,
                100,
                50,null,
                null,75,null,null,
                null,null,null,null,null,null,null,null);
        tree.add(500);
        fullTest(4,
                100,
                50,500,
                null,75,null,null,
                null,null,null,null,null,null,null,null);
        tree.add(500);  //duplicate
        fullTest(4,
                100,
                50,500,
                null,75,null,null,
                null,null,null,null,null,null,null,null);
        tree.add(600);
        fullTest(5,
                100,
                50,500,
                null,75,null,600,
                null,null,null,null,null,null,null,null);
        tree.add(400);
        fullTest(6,
                100,
                50,500,
                null,75,400,600,
                null,null,null,null,null,null,null,null);
        tree.add(10);
        fullTest(7,
                100,
                50,500,
                10,75,400,600,
                null,null,null,null,null,null,null,null);
        tree.add(10);   //duplicate
        fullTest(7,
                100,
                50,500,
                10,75,400,600,
                null,null,null,null,null,null,null,null);
        tree.add(5);
        fullTest(8,
                100,
                50,500,
                10,75,400,600,
                5,null,null,null,null,null,null,null);
        tree.add(100);  //duplicate
        fullTest(8,
                100,
                50,500,
                10,75,400,600,
                5,null,null,null,null,null,null,null);
        tree.add(1000);
        fullTest(9,
                100,
                50,500,
                10,75,400,600,
                5,null,null,null,null,null,null,1000);
        tree.add(25);
        fullTest(10,
                100,
                50,500,
                10,75,400,600,
                5,25,null,null,null,null,null,1000);
        tree.add(300);
        fullTest(11,
                100,
                50,500,
                10,75,400,600,
                5,25,null,null,300,null,null,1000);
        tree.add(70);
        fullTest(12,
                100,
                50,500,
                10,75,400,600,
                5,25,70,null,300,null,null,1000);
        tree.add(90);
        fullTest(13,
                100,
                50,500,
                10,75,400,600,
                5,25,70,90,300,null,null,1000);
        tree.add(450);
        fullTest(14,
                100,
                50,500,
                10,75,400,600,
                5,25,70,90,300,450,null,1000);
        tree.add(550);
        fullTest(15,
                100,
                50,500,
                10,75,400,600,
                5,25,70,90,300,450,550,1000);
        try {
            tree.add(null);
            fail("Your implementation did not through an exception when adding null data.");
        } catch(IllegalArgumentException e){

        }
    }

    @Test
    public void remove() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the remove() method.",e);
        }
        fullTest(15,
                100,
                50,500,
                10,75,400,600,
                5,25,70,90,300,450,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)25, tree.remove(25));
        fullTest(14,
                100,
                50,500,
                10,75,400,600,
                5,null,70,90,300,450,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)10, tree.remove(10));
        fullTest(13,
                100,
                50,500,
                5,75,400,600,
                null,null,70,90,300,450,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)75, tree.remove(75));
        fullTest(12,
                100,
                50,500,
                5,90,400,600,
                null,null,70,null,300,450,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)90, tree.remove(90));
        fullTest(11,
                100,
                50,500,
                5,70,400,600,
                null,null,null,null,300,450,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)300, tree.remove(300));
        fullTest(10,
                100,
                50,500,
                5,70,400,600,
                null,null,null,null,null,450,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)450, tree.remove(450));
        fullTest(9,
                100,
                50,500,
                5,70,400,600,
                null,null,null,null,null,null,550,1000);
        assertEquals("The value of the object removed was not the expected value.", (Integer)600, tree.remove(600));
        fullTest(8,
                100,
                50,500,
                5,70,400,1000,
                null,null,null,null,null,null,550,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)550, tree.remove(550));
        fullTest(7,
                100,
                50,500,
                5,70,400,1000,
                null,null,null,null,null,null,null,null);
        Integer a = new Integer(101);
        Integer b = new Integer(101);
        assertNotSame("The test failed because two comparison integers were the same. This is an error with the test, not your code.",a,b);
        tree.add(a);
        fullTest(8,
                100,
                50,500,
                5,70,400,1000,
                null,null,null,null,101,null,null,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)100, tree.remove(100));
        fullTest(7,
                101,
                50,500,
                5,70,400,1000,
                null,null,null,null,null,null,null,null);
        try {
            Integer aa = tree.remove(b);
            assertEquals("The value of the object removed was not the expected value.", (Integer)101, aa);
            assertSame("Your implementation did not return the data originally added. It is likely that the"
                    + " passed data was returned instead of the data stored in the list.", aa, a);

        } catch (NoSuchElementException e) {
            fail("A NoSuchElementException was returned by your method, likely because your implementation did not "
                    + "locate a piece of data that has identical elements to the passed data, but not the "
                    + "same reference.");
        }
        fullTest(6,
                400,
                50,500,
                5,70,null,1000,
                null,null,null,null,null,null,null,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)400, tree.remove(400));
        fullTest(5,
                500,
                50,1000,
                5,70,null,null,
                null,null,null,null,null,null,null,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)500, tree.remove(500));
        fullTest(4,
                1000,
                50,null,
                5,70,null,null,
                null,null,null,null,null,null,null,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)1000, tree.remove(1000));
        fullTest(3,
                50,
                5,70,
                null,null,null,null,
                null,null,null,null,null,null,null,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)50, tree.remove(50));
        fullTest(2,
                70,
                5,null,
                null,null,null,null,
                null,null,null,null,null,null,null,null);
        assertEquals("The value of the object removed was not the expected value.", (Integer)70, tree.remove(70));
        fullTest(1,
                5,
                null,null,
                null,null,null,null,
                null,null,null,null,null,null,null,null);
        try {
            tree.remove(0);
            fail("Your implementation did not throw an exception when removing nonexistent data.");
        } catch (NoSuchElementException e){

        }
        try {
            tree.remove(null);
            fail("Your implementation did not throw an exception when attempting to remove null data.");
        } catch (IllegalArgumentException e){

        }
        assertEquals("The value of the object removed was not the expected value.", (Integer)5, tree.remove(5));
        fullTest(0,
                null,
                null,null,
                null,null,null,null,
                null,null,null,null,null,null,null,null);
        try {
            tree.remove(0);
            fail("Your implementation did not throw an exception when removing from an empty list.");
        }  catch (NoSuchElementException e) {

        }
    }

    @Test
    public void get() {
        Integer a1 = new Integer(1);
        Integer b1 = new Integer(1);
        Integer a2 = new Integer(2);
        Integer b2 = new Integer(2);
        Integer a3 = new Integer(3);
        Integer b3 = new Integer(3);
        Integer a4 = new Integer(4);
        Integer b4 = new Integer(4);
        Integer a5 = new Integer(5);
        Integer b5 = new Integer(5);
        Integer a6 = new Integer(6);
        Integer b6 = new Integer(6);
        Integer a7 = new Integer(7);
        Integer b7 = new Integer(7);
        try {
            tree.add(a4);
            tree.add(a2);
            tree.add(a6);
            tree.add(a1);
            tree.add(a3);
            tree.add(a5);
            tree.add(a7);
            fullTest(7,
                    4,
                    2,6,
                    1,3,5,7,
                    null,null,null,null,null,null,null,null);
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the get() method.",e);
        }
        try {
            Integer aa1 = tree.get(b1);
            assertEquals("get() did not return an element with the expected data.",a1,aa1);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a1, aa1);
            Integer aa2 = tree.get(b2);
            assertEquals("get() did not return an element with the expected data.",a2,aa2);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a2, aa2);
            Integer aa3 = tree.get(b3);
            assertEquals("get() did not return an element with the expected data.",a3,aa3);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a3, aa3);
            Integer aa4 = tree.get(b4);
            assertEquals("get() did not return an element with the expected data.",a4,aa4);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a4, aa4);
            Integer aa5 = tree.get(b5);
            assertEquals("get() did not return an element with the expected data.",a5,aa5);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a5, aa5);
            Integer aa6 = tree.get(b6);
            assertEquals("get() did not return an element with the expected data.",a6,aa6);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a6, aa6);
            Integer aa7 = tree.get(b7);
            assertEquals("get() did not return an element with the expected data.",a7,aa7);
            assertSame("get() did not return the data that was previously added; it likely just returned "
                    + "the passed data.", a7, aa7);
        } catch (NoSuchElementException e) {
            fail("A NoSuchElementException was thrown by the get() method; this likely occurred because the method "
                    + "was asked to find an element with the same data as the passed value, even though the "
                    + "elements had different references.");
        }
    }

    @Test
    public void contains() {
        Integer a1 = new Integer(1);
        Integer b1 = new Integer(1);
        Integer a2 = new Integer(2);
        Integer b2 = new Integer(2);
        Integer a3 = new Integer(3);
        Integer b3 = new Integer(3);
        Integer a4 = new Integer(4);
        Integer b4 = new Integer(4);
        Integer a5 = new Integer(5);
        Integer b5 = new Integer(5);
        Integer a6 = new Integer(6);
        Integer b6 = new Integer(6);
        Integer a7 = new Integer(7);
        Integer b7 = new Integer(7);
        try {
            tree.add(a4);
            tree.add(a2);
            tree.add(a6);
            tree.add(a1);
            tree.add(a3);
            tree.add(a5);
            tree.add(a7);
            fullTest(7,
                    4,
                    2,6,
                    1,3,5,7,
                    null,null,null,null,null,null,null,null);
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the contains() method.",e);
        }
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a1));
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a2));
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a3));
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a4));
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a5));
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a6));
        assertTrue("The contains() method returned false when searching for an "
                + "element in the tree, using the exact same reference as the element previously "
                + "added.", tree.contains(a7));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b1));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b2));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b3));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b4));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b5));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b6));
        assertTrue("The contains() method did not return true when searching for"
                + " an element that should be in the list; this is likely because the element "
                + "in the tree and the passed element have the same data, but not "
                + "the same reference.", tree.contains(b7));
        assertFalse("The contains() method returned true when searching for "
                + "an element that is not in the tree.", tree.contains(8));
    }

    @Test
    public void preorder() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the preorder() method.",e);
        }
        assertArrayEquals("Your implementation's preorder traversal was inaccurate.",
                new Integer[]{100,50,10,5,25,75,70,90,500,400,300,450,600,550,1000}, tree.preorder().toArray(new Integer[0]));
    }

    @Test
    public void inorder() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the preorder() method.",e);
        }
        assertArrayEquals("Your implementation's inorder traversal was inaccurate.",
                new Integer[]{5,10,25,50,70,75,90,100,300,400,450,500,550,600,1000}, tree.inorder().toArray(new Integer[0]));
    }

    @Test
    public void postorder() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the preorder() method.",e);
        }
        assertArrayEquals("Your implementation's postorder traversal was inaccurate.",
                new Integer[]{5,25,10,70,90,75,50,300,450,400,550,1000,600,500,100}, tree.postorder().toArray(new Integer[0]));
    }

    @Test
    public void levelorder() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the preorder() method.",e);
        }
        assertArrayEquals("Your implementation's levelorder traversal was inaccurate.",
                new Integer[]{100,50,500,10,75,400,600,5,25,70,90,300,450,550,1000}, tree.levelorder().toArray(new Integer[0]));
    }

    @Test
    public void kLargest() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the kLargest() method.",e);
        }
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{5,10,25,50,70,75,90,100,300,400,450,500,550,600,1000}, tree.kLargest(15).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{10,25,50,70,75,90,100,300,400,450,500,550,600,1000}, tree.kLargest(14).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{25,50,70,75,90,100,300,400,450,500,550,600,1000}, tree.kLargest(13).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{50,70,75,90,100,300,400,450,500,550,600,1000}, tree.kLargest(12).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{70,75,90,100,300,400,450,500,550,600,1000}, tree.kLargest(11).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{75,90,100,300,400,450,500,550,600,1000}, tree.kLargest(10).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{90,100,300,400,450,500,550,600,1000}, tree.kLargest(9).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{100,300,400,450,500,550,600,1000}, tree.kLargest(8).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{300,400,450,500,550,600,1000}, tree.kLargest(7).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{400,450,500,550,600,1000}, tree.kLargest(6).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{450,500,550,600,1000}, tree.kLargest(5).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{500,550,600,1000}, tree.kLargest(4).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{550,600,1000}, tree.kLargest(3).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{600,1000}, tree.kLargest(2).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[]{1000}, tree.kLargest(1).toArray(new Integer[0]));
        assertArrayEquals("Your implementation's kLargest method did return the correct list of elements.",
                new Integer[0], tree.kLargest(0).toArray(new Integer[0]));
        try {
            tree.kLargest(16);
            fail("Your implementation's kLargest method failed to throw an array when the value of k "
                    + "exceeded the size of the array.");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void clear() {
        try {
            add();
        } catch (Exception e) {
            throw new RuntimeException("Please debug the add() method before testing the clear() method.",e);
        }
        tree.clear();
        fullTest(0,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null);
    }

    @Test
    public void height() {
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                -1, tree.height());
        tree.add(8);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                0, tree.height());
        tree.add(4);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                1, tree.height());
        tree.add(12);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                1, tree.height());
        tree.add(2);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                2, tree.height());
        tree.add(14);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                2, tree.height());
        tree.add(15);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                3, tree.height());
        tree.add(13);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                3, tree.height());
        tree.add(1);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                3, tree.height());
        tree.add(0);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                4, tree.height());
        tree.add(-2);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                5, tree.height());
        tree.add(-1);
        assertEquals("Your implementation's height() method did not provide the correct height of the root node.",
                6, tree.height());
    }

    /**
     * Tests that the tree has an exact shape and size.
     */
    private void fullTest(int size, Integer root, Integer l, Integer r, Integer ll, Integer lr, Integer rl, Integer rr,
                          Integer lll, Integer llr, Integer lrl, Integer lrr, Integer rll, Integer rlr, Integer rrl, Integer rrr) {
        String expected = generateTreeDiagram(size,root,l,r,ll,lr,rl,rr,lll,llr,lrl,lrr,rll,rlr,rrl,rrr);
        Integer a,b,c,d,e,f,g,h,i,j,k,m,n,o,p;
        a=b=c=d=e=f=g=h=i=j=k=m=n=o=p=null;
        BSTNode<Integer> node = tree.getRoot();
        if(root!=null){
            a=node.getData();
            if(node.getLeft()!=null){
                b=node.getLeft().getData();
                if(node.getLeft().getLeft()!=null){
                    d=node.getLeft().getLeft().getData();
                    if(node.getLeft().getLeft().getLeft()!=null){
                        h=node.getLeft().getLeft().getLeft().getData();
                    }
                    if(node.getLeft().getLeft().getRight()!=null){
                        i=node.getLeft().getLeft().getRight().getData();
                    }
                }
                if(node.getLeft().getRight()!=null){
                    e=node.getLeft().getRight().getData();
                    if(node.getLeft().getRight().getLeft()!=null){
                        j=node.getLeft().getRight().getLeft().getData();
                    }
                    if(node.getLeft().getRight().getRight()!=null){
                        k=node.getLeft().getRight().getRight().getData();
                    }
                }
            }
            if(node.getRight()!=null){
                c=node.getRight().getData();
                if(node.getRight().getLeft()!=null){
                    f=node.getRight().getLeft().getData();
                    if(node.getRight().getLeft().getLeft()!=null){
                        m=node.getRight().getLeft().getLeft().getData();
                    }
                    if(node.getRight().getLeft().getRight()!=null){
                        n=node.getRight().getLeft().getRight().getData();
                    }
                }
                if(node.getRight().getRight()!=null){
                    g=node.getRight().getRight().getData();
                    if(node.getRight().getRight().getLeft()!=null){
                        o=node.getRight().getRight().getLeft().getData();
                    }
                    if(node.getRight().getRight().getRight()!=null){
                        p=node.getRight().getRight().getRight().getData();
                    }
                }
            }
        }
        String actual = generateTreeDiagram(tree.size(),a,b,c,d,e,f,g,h,i,j,k,m,n,o,p);
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

    private String generateTreeDiagram(int size, Integer root, Integer l, Integer r, Integer ll, Integer lr, Integer rl, Integer rr,
                                       Integer lll, Integer llr, Integer lrl, Integer lrr, Integer rll, Integer rlr, Integer rrl, Integer rrr) {
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

        return  ("Size: SS      AAA              "+"\n"+
                "        _______|_______        "+"\n"+
                "       |               |       "+"\n"+
                "      BBB             CCC      "+"\n"+
                "    ___|___         ___|___    "+"\n"+
                "   |       |       |       |   "+"\n"+
                "  DDD     EEE     FFF     GGG  "+"\n"+
                "  _|_     _|_     _|_     _|_  "+"\n"+
                " |   |   |   |   |   |   |   | "+"\n"+
                "HHH III JJJ KKK MMM NNN OOO PPP")
                .replaceFirst("SS", String.format("%2d", size))
                .replaceFirst("AAA",a)
                .replaceFirst("BBB",b)
                .replaceFirst("CCC",c)
                .replaceFirst("DDD",d)
                .replaceFirst("EEE",e)
                .replaceFirst("FFF",f)
                .replaceFirst("GGG",g)
                .replaceFirst("HHH",h)
                .replaceFirst("III",i)
                .replaceFirst("JJJ",j)
                .replaceFirst("KKK",k)
                .replaceFirst("MMM",m)
                .replaceFirst("NNN",n)
                .replaceFirst("OOO",o)
                .replaceFirst("PPP",p);
    }
}