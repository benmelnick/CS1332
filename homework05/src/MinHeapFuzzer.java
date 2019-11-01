import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Random;

public class MinHeapFuzzer {
    private static final int TIMEOUT = 2000;
    private MinHeap<Integer> minHeap;
    private PriorityQueue<Integer> priorityQueue;

    @Before
    public void setUp() {
        minHeap = new MinHeap<>();
        priorityQueue = new PriorityQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void fuzz() {
        Random random = new Random();
        final int NUM_RUNS = 10000;

        for (int i = 0; i < NUM_RUNS; i++) {
            /*
            System.out.print("[");
            for (int j = 0; j < minHeap.getBackingArray().length; j++) {
                System.out.print(minHeap.getBackingArray()[j] + ", ");
            }
            System.out.println("]");
            */
            Integer value = random.nextInt(100);
            switch (random.nextInt(5)) {
                case 0:
                    minHeap.add(value);
                    priorityQueue.add(value);
                    assertEquals(priorityQueue.size(), minHeap.size());
                    assertArrayEquals(getArray(minHeap), priorityQueue.toArray());
                    break;
                case 1:
                    assertEquals(priorityQueue.isEmpty(), minHeap.isEmpty());
                    if (!priorityQueue.isEmpty()) {
                        int pQ = priorityQueue.remove();
                        int mH = minHeap.remove();
                        assertEquals(pQ, mH);
                    } else {
                        minHeap.add(value);
                        priorityQueue.add(value);
                    }
                    assertEquals(priorityQueue.size(), minHeap.size());
                    assertArrayEquals(getArray(minHeap), priorityQueue.toArray());
                    break;
                case 2:
                    minHeap.clear();
                    priorityQueue.clear();
                    assertArrayEquals(getArray(minHeap), priorityQueue.toArray());

                    boolean thrown = false;
                    try {
                        minHeap.remove();
                    } catch (NoSuchElementException e) {
                        thrown = true;
                    }

                    assertTrue(thrown);
                    break;
                case 3:
                    ArrayList<Integer> data = new ArrayList<>();

                    for (int j = 0; j < value; j++) {
                        data.add(random.nextInt(1000));
                    }

                    minHeap = new MinHeap<>(data);
                    priorityQueue = new PriorityQueue<>(data);

                    assertEquals(priorityQueue.size(), minHeap.size());
                    System.out.println();
                    System.out.println(generateHeapDiagram(minHeap.size(), getArray(minHeap)));
                    Integer[] b = new Integer[priorityQueue.toArray().length];
                    for(int j=0;j<b.length;j++)b[j]= (Integer) priorityQueue.toArray()[j];
                    System.out.println(generateHeapDiagram(priorityQueue.size(), b));
                    print(minHeap, priorityQueue);
                    assertArrayEquals(getArray(minHeap), priorityQueue.toArray());
                    break;
                case 4:
                    ArrayList<Integer> list = null;

                    boolean wasThrown = false;
                    try {
                        minHeap = new MinHeap<>(list);
                    } catch (IllegalArgumentException e) {
                        wasThrown = true;
                    }

                    assertTrue(wasThrown);

                    list = new ArrayList<>();

                    list.add(1);
                    list.add(null);
                    list.add(2);

                    try {
                        minHeap = new MinHeap<>(list);
                    } catch (IllegalArgumentException e) {
                        wasThrown = true;
                    }

                    assertTrue(wasThrown);

                    minHeap = new MinHeap<>();
                    priorityQueue = new PriorityQueue<>();

                    try {
                        minHeap.add(null);
                    } catch (IllegalArgumentException e) {
                        wasThrown = true;
                    }

                    assertTrue(wasThrown);
            }
        }
    }

    private Integer[] getArray(MinHeap<Integer> minHeap) {
        Integer[] ints = new Integer[minHeap.size()];

        for (int i = 1; i < minHeap.getBackingArray().length; i++) {
            Object obj = minHeap.getBackingArray()[i];

            if (obj == null) {
                break;
            }

            ints[i - 1] = (Integer) obj;
        }

        return ints;
    }

    private void print(MinHeap<Integer> heap, PriorityQueue<Integer> queue) {
        for (int i = 1; i <= minHeap.size(); i++) {
            System.out.print("Heap: " + minHeap.getBackingArray()[i] + ", Queue: " + queue.toArray()[i - 1]);
            System.out.println(minHeap.getBackingArray()[i].equals(queue.toArray()[i - 1]) ? "" : " X");
        }
    }

    public static String generateHeapDiagram(int size, Integer[] backing) {
        ArrayList<String> formatted = new ArrayList<>(backing.length);
        for(int i=0;i<backing.length && backing[i]!=null;i++){
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
}