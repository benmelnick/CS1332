import org.junit.Test;
import org.junit.Before;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * This is a basic set of unit tests for DoublyLinkedList.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author The 1332 TAs
 * @version 1.0
 */
public class BigOTests {
    private static final int BIG_O_DATAPOINTS = 100;
    private static final int BIG_O_MULTIPLIER = 1000;
    private DoublyLinkedList<String> list;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        list = new DoublyLinkedList<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsIndex() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsFront() {
        assertEquals(0, list.size());

        list.addToFront("0a"); // 0a
        list.addToFront("1a"); // 1a 0a
        list.addToFront("2a"); // 2a 1a 0a
        list.addToFront("3a"); // 3a 2a 1a 0a
        list.addToFront("4a"); // 4a 3a 2a 1a 0a
        list.addToFront("5a"); // 5a 4a 3a 2a 1a 0a

        assertEquals(6, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("5a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("4a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("0a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testAddStringsBack() {
        assertEquals(0, list.size());
        assertNull(list.getHead());

        list.addToBack("0a"); //0a
        list.addToBack("1a"); //0a 1a
        list.addToBack("2a"); //0a 1a 2a
        list.addToBack("3a"); //0a 1a 2a 3a

        assertEquals(4, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsIndex() {
        assertEquals(0, list.size());
        String temp = new String("2a");
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, temp); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a
        list.addAtIndex(5, "5a"); // 0a 1a 2a 3a 4a 5a

        assertEquals(6, list.size());

        assertEquals(temp, list.removeAtIndex(2)); // 0a 1a 3a 4a 5a

        assertEquals(5, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("4a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("5a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsFront() {
        assertEquals(0, list.size());
        String temp = new String("0a");
        list.addToBack(temp); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack("3a"); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        assertEquals(temp, list.removeFromFront()); // 1a 2a 3a

        assertEquals(3, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("1a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("3a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveStringsBack() {
        assertEquals(0, list.size());
        String temp = new String("3a");
        list.addToBack("0a"); // 0a
        list.addToBack("1a"); // 0a 1a
        list.addToBack("2a"); // 0a 1a 2a
        list.addToBack(temp); // 0a 1a 2a 3a

        assertEquals(4, list.size());

        assertEquals(temp, list.removeFromBack()); // 0a 1a 2a

        assertEquals(3, list.size());

        LinkedListNode<String> current = list.getHead();
        assertNotNull(current);
        assertNull(current.getPrevious());
        assertEquals("0a", current.getData());

        LinkedListNode<String> prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("1a", current.getData());

        prev = current;
        current = current.getNext();
        assertNotNull(current);
        assertSame(prev, current.getPrevious());
        assertEquals("2a", current.getData());
        assertSame(list.getTail(), current);

        current = current.getNext();
        assertNull(current);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, new String("3a")); // 0a 1a 2a 3a
        list.addAtIndex(4, new String("3a")); // 0a 1a 2a 3a 3a
        list.addAtIndex(5, "4a"); // 0a 1a 2a 3a 3a 4a

        assertEquals(1, list.lastOccurrence(new String("1a")));
        assertEquals(4, list.lastOccurrence(new String("3a")));
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
    public void testToArray() {
        String[] expectedItems = new String[10];

        for (int x = 0; x < expectedItems.length; x++) {
            expectedItems[x] = x + "a";
            list.addToBack(expectedItems[x]);
        }

        Object[] array = list.toArray();
        assertArrayEquals(expectedItems, array);
    }

    @Test(timeout = TIMEOUT)
    public void testClearIsEmpty() {
        list.addAtIndex(0, "0a"); // 0a
        list.addAtIndex(1, "1a"); // 0a 1a
        list.addAtIndex(2, "2a"); // 0a 1a 2a
        list.addAtIndex(3, "3a"); // 0a 1a 2a 3a
        list.addAtIndex(4, "4a"); // 0a 1a 2a 3a 4a

        list.clear();
        assertEquals(0, list.size());
        assertNull(list.getHead());
        assertNull(list.getTail());
        assertTrue(list.isEmpty());
    }

    @Test
    public void testBigO() throws IOException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        DoublyLinkedList<String>[] arr = generateDLLArr(
                BIG_O_DATAPOINTS, BIG_O_MULTIPLIER);
        // dLLContainer contains 100 DLLs, each index increasing in size by 10
        long elapsedTime = -1; //set to -1 to make sure elapsed time is set
        File csv = new File("BigOTests.csv"); //set name of write file
        boolean wasDeleted = csv.delete(); //delete file if it exists
        FileWriter csvWriter = new FileWriter(csv, true); //init writer

        //Ordered Map of Methods which will be invoked in runtime test
        LinkedHashMap<String, Method> methodMap = new LinkedHashMap<>();
        methodMap.put("addAtIndex", DoublyLinkedList.class.getMethod(
                "addAtIndex", int.class, Object.class));
        methodMap.put("addToFront", DoublyLinkedList.class.getMethod(
                "addToFront", Object.class));
        methodMap.put("addToBack", DoublyLinkedList.class.getMethod(
                "addToBack", Object.class));
        methodMap.put("removeAtIndex", DoublyLinkedList.class.getMethod(
                "removeAtIndex", int.class));
        methodMap.put("removeFromFront", DoublyLinkedList.class.getMethod(
                "removeFromFront"));
        methodMap.put("removeFromBack", DoublyLinkedList.class.getMethod(
                "removeFromBack"));
        methodMap.put("get", DoublyLinkedList.class.getMethod(
                "get", int.class));

        //Writes headers for data to CSV file
        Set<String> methodSet = methodMap.keySet();
        for (String name : methodSet) {
            csvWriter.write(name + ", , ");
        }
        csvWriter.write("\n"); //move onto next row

        for (String name: methodSet) {
            csvWriter.write("n, Runtime (ns), ");
        }
        csvWriter.write("\n"); //move onto next row

        //tests given methods for runtime and writes data to CSV file
        for (DoublyLinkedList<String> dll : arr) {
            elapsedTime = testFunctionTime(dll, methodMap.get("addAtIndex"),
                    (dll.size() - 1) / 2, "A");
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");

            elapsedTime = testFunctionTime(dll, methodMap.get("addToFront"),
                    "A");
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");

            elapsedTime = testFunctionTime(dll, methodMap.get("addToBack"),
                    "A");
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");

            elapsedTime = testFunctionTime(dll, methodMap.get("removeAtIndex"),
                    (dll.size() - 1) / 2);
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");

            elapsedTime = testFunctionTime(dll,
                    methodMap.get("removeFromFront"));
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");

            elapsedTime = testFunctionTime(dll,
                    methodMap.get("removeFromBack"));
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");

            //ensure that there's at least 1 element to get in the dll
            dll.addToFront("A");
            elapsedTime = testFunctionTime(dll, methodMap.get("get"),
                    (dll.size() - 1) / 2);
            csvWriter.write(Integer.toString(dll.size()) + ", "
                    + Long.toString(elapsedTime) + ", ");
            csvWriter.write("\n"); //move onto next record/row
        }
        //writes to CSV file on close
        csvWriter.close();
    }

    /**
     * Method to generate Array of DoublyLinkedList for testing
     * @param numDLLs number of DLLs to include in test
     * @param multiplier determines how large the DLLs should be
     * @return DoublyLinkedList array to test
     */
    private DoublyLinkedList<String>[] generateDLLArr(int numDLLs,
                                                      int multiplier) {
        DoublyLinkedList<String>[] dLLContainer = new DoublyLinkedList[numDLLs];
        for (int i = 0; i < numDLLs; i++) {
            DoublyLinkedList<String> temp = new DoublyLinkedList<>();
            for (int j = 0; j < (i * multiplier); j++) {
                temp.addToFront("A");
            }
            dLLContainer[i] = temp;
        }
        return dLLContainer;
    }

    /*
    THESE OVERLOADS ARE REQUIRED FOR SEAMLESS INVOCATION OF METHODS WITH
    DIFFERENT NUMBER OF PARAMETERS
     */

    /**
     * Tests the runtime of the given Method, only for comparison
     * @param dll DoublyLinkedList to add to, use the same for all timing if
     *            testing for comparison
     * @param method Method which is to be timed
     * @return long equal to runtime in ns
     * @throws IllegalAccessException if Method cannot be accessed
     * @throws InvocationTargetException if Method invocation fails
     */
    private long testFunctionTime(DoublyLinkedList<String> dll,
                                  Method method) throws IllegalAccessException,
            InvocationTargetException {
        long startTime = 0;
        long endTime = 0;
        long elapsedTime = -1;
        startTime = System.nanoTime();
        method.invoke(dll);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        return elapsedTime;
    }

    /**
     * Tests the runtime of the given Method, only for comparison
     * @param dll DoublyLinkedList to add to, use the same for all timing if
     *            testing for comparison
     * @param method Method which is to be timed
     * @param arg String argument to pass into @code method
     * @return long equal to runtime in ns
     * @throws IllegalAccessException if Method cannot be accessed
     * @throws InvocationTargetException if Method invocation fails
     */
    private long testFunctionTime(DoublyLinkedList<String> dll,
                                  Method method,
                                  String arg) throws IllegalAccessException,
            InvocationTargetException {
        long startTime = 0;
        long endTime = 0;
        long elapsedTime = -1;
        startTime = System.nanoTime();
        method.invoke(dll, arg);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        return elapsedTime;
    }

    /**
     * Tests the runtime of the given Method, only for comparison
     * @param dll DoublyLinkedList to add to, use the same for all timing if
     *            testing for comparison
     * @param method Method which is to be timed
     * @param index index to pass into @code method
     * @param arg String argument to pass into @code method
     * @return long equal to runtime in ns
     * @throws IllegalAccessException if Method cannot be accessed
     * @throws InvocationTargetException if Method invocation fails
     */
    private long testFunctionTime(DoublyLinkedList<String> dll,
                                  Method method, int index,
                                  String arg) throws IllegalAccessException,
            InvocationTargetException {
        long startTime = 0;
        long endTime = 0;
        long elapsedTime = -1;
        startTime = System.nanoTime();
        method.invoke(dll, index, arg);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        return elapsedTime;
    }
    /**
     * Tests the runtime of the given Method, only for comparison
     * @param dll DoublyLinkedList to add to, use the same for all timing if
     *            testing for comparison
     * @param method Method which is to be timed
     * @param index index to pass into @code method
     * @return long equal to runtime in ns
     * @throws IllegalAccessException if Method cannot be accessed
     * @throws InvocationTargetException if Method invocation fails
     */
    private long testFunctionTime(DoublyLinkedList<String> dll, Method method,
                                  int index) throws IllegalAccessException,
            InvocationTargetException {
        long startTime = 0;
        long endTime = 0;
        long elapsedTime = -1;
        startTime = System.nanoTime();
        method.invoke(dll, index);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        return elapsedTime;
    }
}