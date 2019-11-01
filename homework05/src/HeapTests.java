import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class HeapTests {

    private MinHeap<Integer> heap;
    public static final int DECK_SIZE = 1000000;
    public static final int FUZZ_ITERATIONS = 10000;
    private static ArrayList shuffledDeck = new ArrayList(DECK_SIZE){
        {
            for(int i=0;i<DECK_SIZE;i++){
                this.add(i);
            }
            Collections.shuffle(this);
        }
    };


    @Before
    public void setUp() {
        heap = new MinHeap<>();
    }

    @Test
    public void buildHeap(){
        ArrayList<Integer> list = new ArrayList<>(5);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);
        heap = new MinHeap<>(list);
        //Note: this test may fail here if your implementation is slightly different.
        //If you think your implementation is correct, try commenting this next line out.
        //If the test passes after removing this line, your implementation should be fine;
        //the heap is functioning as intended.
        tA(5, null,1, 2,3, 5,4,null,null, null,null,null);

        assertEquals("Your heap returned an incorrect value when removing the root.",1, (int)heap.remove());
        assertEquals("Your heap returned an incorrect value when removing the root.",2, (int)heap.remove());
        assertEquals("Your heap returned an incorrect value when removing the root.",3, (int)heap.remove());
        assertEquals("Your heap returned an incorrect value when removing the root.",4, (int)heap.remove());
        assertEquals("Your heap returned an incorrect value when removing the root.",5, (int)heap.remove());
        tA(0, null,null,null,null,null,null,null,null,null,null,null);
        try {
            heap = new MinHeap<>(null);
            fail("Your implementation did not throw an exception when initialized with a null list.");
        } catch (IllegalArgumentException e) {}
        try {
            list.add(null);
            heap = new MinHeap<>(list);
            fail("Your implementation did not throw an exception when initialized with a list containing null data.");
        } catch (IllegalArgumentException e){}
    }

    @Test
    public void add() {
        tA(0,null,null,null,null,null,null,null,null,null,null,null,null,null);
        heap.add(100);
        tA(1,null,100,null,null,null,null,null,null,null,null,null,null,null);
        heap.add(200);
        tA(2,null,100,200,null,null,null,null,null,null,null,null,null,null);
        heap.add(50);
        tA(3,null,50,200,100,null,null,null,null,null,null,null,null,null);
        heap.add(25);
        tA(4,null,25,50,100,200,null,null,null,null,null,null,null,null);
        heap.add(75);
        tA(5,null,25,50,100,200,75,null,null,null,null,null,null,null);
        heap.add(80);
        tA(6,null,25,50,80,200,75,100,null,null,null,null,null,null);
        heap.add(5);
        tA(7,null,5, 50,25, 200,75,100,80, null,null,null,null,null);
        heap.add(90);
        tA(8,null,5, 50,25, 90,75,100,80, 200,null,null,null,null);
        heap.add(85);
        tA(9,null,5, 50,25, 85,75,100,80, 200,90,null,null,null);
        heap.add(10);
        tA(10,null,5, 10,25, 85,50,100,80, 200,90,75,null,null);
        heap.add(999);
        tA(11,null,5, 10,25, 85,50,100,80, 200,90,75,999,null);
        heap.add(0);
        tA(12,null,0, 10,5, 85,50,25,80, 200,90,75,999,100);
        try{
            heap.add(null);
            fail("Your heap did not raise an exception when attempting to add null data.");
        } catch(IllegalArgumentException e){}
    }

    @Test
    public void remove_and_getMin() {
        try{
            add();
        }catch(Exception e){
            fail("Please debug the add() method before testing the remove() method.");
        }
        tA(12,null,0, 10,5, 85,50,25,80, 200,90,75,999,100);
        assertEquals("Your heap returned an incorrect value when looking at the root.",0, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",0, (int)heap.remove());
        tA(11,null,5, 10,25, 85,50,100,80, 200,90,75,999,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",5, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",5, (int)heap.remove());
        tA(10,null,10, 50,25, 85,75,100,80, 200,90,999,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",10, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",10, (int)heap.remove());
        tA(9,null,25, 50,80, 85,75,100,999, 200,90,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",25, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",25, (int)heap.remove());
        tA(8,null,50, 75,80, 85,90,100,999, 200,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",50, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",50, (int)heap.remove());
        tA(7,null,75, 85,80, 200,90,100,999, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",75, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",75, (int)heap.remove());
        tA(6,null,80, 85,100, 200,90,999,null, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",80, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",80, (int)heap.remove());
        tA(5,null,85, 90,100, 200,999,null,null, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",85, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",85, (int)heap.remove());
        tA(4,null,90, 200,100, 999,null,null,null, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",90, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",90, (int)heap.remove());
        tA(3,null,100, 200,999, null,null,null,null, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",100, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",100, (int)heap.remove());
        tA(2,null,200, 999,null, null,null,null,null, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",200, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",200, (int)heap.remove());
        tA(1,null,999, null,null, null,null,null,null, null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",999, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",999, (int)heap.remove());
        tA(0,null,null, null,null, null,null,null,null, null,null,null,null,null);
        assertNull(heap.getMin());
        try{
            heap.remove();
            fail("Your heap failed to throw an exception when attempting to remove an element while it was empty.");
        } catch (NoSuchElementException e){}
    }

    @Test
    public void isEmpty_and_clear() {
        assertTrue("Your heap says it is not empty when it is empty.", heap.isEmpty());
        try{
            add();
        } catch (Exception e){
            fail("Please debug the add() method before testing the remove() method.");
        }
        assertFalse("Your heap says it is empty when it is not empty.", heap.isEmpty());
        heap.clear();
        tA(0,null,null, null,null, null,null,null,null, null,null,null,null,null);
        assertTrue("Your heap says it is still not empty after running the clear() method.", heap.isEmpty());
        try{
            remove_and_getMin();
        } catch (Exception e){
            fail("Please debug the add() and remove_and_getMin() tests before running this test.");
        }
        assertTrue("Your heap says it is still not empty after removing every element.", heap.isEmpty());
    }

    @Test
    public void addResize() {
        try{
            add();
        } catch (Exception e){
            fail("Please debug the add() method before running this test.");
        }
        tA(12,null,0, 10,5, 85,50,25,80, 200,90,75,999,100);
        heap.add(500);
        tA(13,null,0, 10,5, 85,50,25,80, 200,90,75,999,100,500,null,null, null,null,null,null,null,null,null,null,null,null);
        heap.add(2);
        tA(14,null,0, 10,2, 85,50,25,5, 200,90,75,999,100,500,80,null, null,null,null,null,null,null,null,null,null,null);
    }

    @Test
    public void removeResize() {
        try{
            addResize();
        } catch (Exception e){
            fail("Please debug the addResize() method before running this test.");
        }
        assertEquals("Your heap returned an incorrect value when looking at the root.",0, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",0, (int)heap.remove());
        tA(13,null,2, 10,5, 85,50,25,80, 200,90,75,999,100,500,null,null, null,null,null,null,null,null,null,null,null,null);
        assertEquals("Your heap returned an incorrect value when looking at the root.",2, (int)heap.getMin());
        assertEquals("Your heap returned an incorrect value when removing the root.",2, (int)heap.remove());
        tA(12,null,5, 10,25, 85,50,100,80, 200,90,75,999,500,null,null,null, null,null,null,null,null,null,null,null,null,null);
    }

    @Test
    public void clearResize() {
        try{
            addResize();
        } catch (Exception e){
            fail("Please debug the addResize() method before running this test.");
        }
        assertFalse("Your heap says it is empty when it is not empty.", heap.isEmpty());
        heap.clear();
        tA(0,null,null, null,null, null,null,null,null, null,null,null,null,null);
        assertTrue("Your heap says it is still not empty after running the clear() method.", heap.isEmpty());
        try{
            addResize();
            for(int i=0;i<14;i++)heap.remove();
        } catch (Exception e){
            fail("Please debug the add() test and the remove() method before running this test.");
        }
        assertTrue("Your heap says it is still not empty after removing every element.", heap.isEmpty());
        tA(0,null,null, null,null, null,null,null,null, null,null,null,null,null,null,null,null, null,null,null,null,null,null,null,null,null,null);
    }

    @Test
    /**
     * This method fills the heap with the numbers 0 to 99, and then removes them in ascending order.
     */
    public void fillAndEmpty100(){
        ArrayList<Integer> deck = new ArrayList<>(100);
        for(int i=0;i<100;i++)deck.add(i);
        Collections.shuffle(deck);
        for(int i=0;i<100;i++)heap.add(deck.get(i));
        Integer[] a = new Integer[heap.getBackingArray().length];
        for(int i=0;i<a.length;i++)a[i]= (Integer) heap.getBackingArray()[i];
        assertEquals("Your heap did not resize to the correct value (assuming INITIAL_CAPACITY = 13).",
                104, heap.getBackingArray().length);
        System.out.println(generateHeapDiagram(heap.size(), a));
        for(int i=0;i<100;i++){
            assertEquals(i, (int)heap.remove());
        }
    }

    @Test
    /**
     * This method fills the heap with the numbers 0 to 99, and then removes them in ascending order.
     */
    public void fillAndEmpty100BuildHeap(){
        ArrayList<Integer> deck = new ArrayList<>(100);
        for(int i=0;i<100;i++)deck.add(i);
        Collections.shuffle(deck);
        heap = new MinHeap<>(deck);
        Integer[] a = new Integer[heap.getBackingArray().length];
        for(int i=0;i<a.length;i++)a[i]= (Integer) heap.getBackingArray()[i];
        assertEquals("Your heap did not resize to the correct value (assuming INITIAL_CAPACITY = 13).",
                201, heap.getBackingArray().length);
        System.out.println(generateHeapDiagram(heap.size(), a));
        for(int i=0;i<100;i++){
            assertEquals(i, (int)heap.remove());
        }
    }

    @Test
    /**
     * This method fills the heap with a shuffled deck, which can be adjusted in the setUp() function.
     */
    public void fill(){
        for(int i=0;i<DECK_SIZE;i++)heap.add((Integer) shuffledDeck.get(i));
    }

    @Test
    /**
     * This method fills the heap with a shuffled deck, which can be adjusted in the setUp() function.
     */
    public void fillBuildHeap(){
        heap = new MinHeap<>(shuffledDeck);
    }

    @Test
    public void fuzz(){
        PriorityQueue<Integer> oracle = new PriorityQueue<>();
        Random rand = new Random();
        boolean[] usage = new boolean[1000];
        for(int i=0;i<FUZZ_ITERATIONS;i++){
            int next = rand.nextInt(1000);
            if(rand.nextBoolean() && !usage[next]){
                oracle.add(next);
                heap.add(next);
                usage[next] = true;
            } else if (oracle.size() == 0) {
                try {
                    heap.remove();
                    fail("No exception was thrown when removing from an empty heap.");
                } catch (NoSuchElementException e) {}
            } else {
                assertEquals(oracle.remove(), heap.remove());
            }
        }
    }

    /**
     * Generates a tree diagram based on the backing array of the heap.
     * NOTE: this makes a few assumptions when printing your heap.
     * 1. Index 0 is never looked at.
     * 2. The number of values displayed is NOT capped by your heap's size variable.
     * Your heap's size should be able to be inferred by counting the number of non null elements.
     * 3. If a null value is found in the heap, no values beyond that value will be printed.
     * 4. If the backing array's end is reached, no more values will be printed.
     * In short, your heap will only be correctly printed if the shape property is met.
     */
    public static String generateHeapDiagram(int size, Integer[] backing) {
        ArrayList<String> formatted = new ArrayList<>(backing.length);
        for(int i=1;i<backing.length && backing[i]!=null;i++){
            formatted.add(String.format("%03d",backing[i]));
        }
        String str = "Size Variable: "+size+" \t Actual Size: "+formatted.size()+"\n";
        int s = formatted.size();
        for(int i=1;i<=s;i++){
            if(fp(i)==i){
                if(i!=1){
                    str+="\n";
                    str+=mc(' ',pow2(log2(s)-log2(i)+1));//First bunch of spaces
                    for(int j=0;j<pow2(log2(i)-1);j++){
                        if(j!=0){
                            str+=mc(' ',pow2(log2(s)-log2(i)+2)+1);//Separating bunches of spaces
                        }
                        str+=mc('_',pow2(log2(s)-log2(i)+1)-1)+"|";//__|
                        if(i+j*2+1>s)break;
                        str+=mc('_',pow2(log2(s)-log2(i)+1)-1);//__
                        if(i+j*2+2>s)break;
                    }
                    str+="\n";
                    str+=mc(' ',pow2(log2(s)-log2(i)+1)-1);//Starting spaces
                    for(int j=0;j<i && i+j<=s;j++){
                        if(j!=0){
                            str+=mc(' ',pow2(log2(s)-log2(i)+2)-1);
                        }
                        str+="|";
                    }
                    str+="\n";
                }
                str+=mc(' ',pow2(log2(s)-log2(i)+1)-2);//Starting spaces
            }else{
                str+=mc(' ',pow2(log2(s)-log2(i)+2)-3);//space between numbers
            }
            str+=formatted.get(i-1);
        }
        return str;
    }

    /**
     * Floors x to the nearest power of 2.
     */
    private static int fp(int x){
        return pow2(log2(x));
    }

    /**
     * Returns the base 2 logarithm of x, floored to the nearest integer.
     * If x=0, returns -1.
     */
    private static int log2(int x){
        int i=-1;
        while(x>0){
            x/=2;
            i++;
        }
        return i;
    }

    /**
     * Returns 2^x.
     */
    private static int pow2(int x){
        return 1<<x;
    }

    /**
     * Returns a string containing the character c printed f times.
     */
    private static String mc(char c, int f){
        String s = "";
        for(int i=0;i<f;i++)s=s+c;
        return s;
    }

    /**
     * Tests an array.
     */
    private void tA(int size, Integer... elements) {
        try {
            assertEquals(size, heap.size());
            assertArrayEquals(elements, heap.getBackingArray());
        } catch(AssertionError e){
            Integer[] a = new Integer[heap.getBackingArray().length];
            for(int i=0;i<a.length;i++)a[i]= (Integer) heap.getBackingArray()[i];
            System.out.println("Your heap's backing array did not match the expected array.\n");
            System.out.println("Visualization of your heap:\n"+generateHeapDiagram(heap.size(), a)+"\n");
            System.out.println("Visualization of expected heap:\n"+generateHeapDiagram(size, elements)+"\n");
            throw e;
        }
    }
}