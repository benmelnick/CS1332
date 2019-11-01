import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

/**
 * MinHeap tests for CS1332 HW6 Fall 2018.
 *
 * If assertArrayEquals() shows that two elements aren't equal but the
 * toString()s are, it means the removed status of the entries are different
 *
 * Sorry for all the side effect assertations, they save a bunch of space. All
 * of these tests rely on put() working correctly so make that work first
 *
 * @author Shishir Bhat
 * @version 1.0
 */
public class HashMapTests {
    private HashMap<Integer, String> map;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        map = new HashMap<>();
    }

    @Test
    public void constructor1() {
        assertArrayEquals(
                new MapEntry[] {null, null, null, null, null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(0, map.size());
    }

    @Test
    public void constructor2() {
        map = new HashMap<>(4);
        assertArrayEquals(
                new MapEntry[] {null, null, null, null},
                map.getTable()
        );
        assertEquals(0, map.size());
    }

    @Test
    public void put() {
        assertNull(map.put(1, "a"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), null, null, null,
                        null, null, null, null, null, null, null, null},
                map.getTable()
        );

        assertNull(map.put(2, "b"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), entry(2, "b"),
                        null, null, null, null, null, null,
                        null, null, null, null},
                map.getTable()
        );

        assertNull(map.put(3, "c"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), entry(2, "b"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(3, map.size());

        assertEquals("a", map.put(1, "c"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "c"), entry(2, "b"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(3, map.size());

        assertNull(map.put(14, "d"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "c"), entry(2, "b"),
                        entry(3, "c"), entry(14, "d"), null,
                        null, null, null, null, null, null, null},
                map.getTable()
        );
        assertEquals(4, map.size());

        map = new HashMap<>(1);
        map.put(1, "a");
        map.put(2, "b");

        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), entry(2, "b")},
                map.getTable()
        );
        assertEquals(2, map.size());
    }

    @Test
    public void putException1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        map.put(null, "a");
    }

    @Test
    public void putException2() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        map.put(3, null);
    }

    @Test
    public void remove() {
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        assertEquals("a", map.put(1, "c"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "c"), entry(2, "b"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );

        assertEquals("b", map.remove(2));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "c"), rem(2, "b"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(2, map.size());

        assertNull(map.put(14, "d"));
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "c"), entry(14, "d"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(3, map.size());

        assertEquals("c", map.remove(1));
        assertArrayEquals(
                new MapEntry[] {null, rem(1, "c"), entry(14, "d"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(2, map.size());

        assertEquals("d", map.put(14, "e"));
        assertArrayEquals(
                new MapEntry[] {null, rem(1, "c"), entry(14, "e"),
                        entry(3, "c"), null, null, null, null,
                        null, null, null, null, null},
                map.getTable()
        );
        assertEquals(2, map.size());
    }

    @Test
    public void removeException1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expect(notNullValue(String.class));
        map.remove(null);
    }

    @Test
    public void removeException2() {
        thrown.expect(NoSuchElementException.class);
        thrown.expect(notNullValue(String.class));
        map.put(1, "a");
        map.remove(1);
        map.remove(1);
    }

    @Test
    public void get() {
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        assertEquals("a", map.get(1));
        assertEquals("c", map.get(3));
        map.put(14, "d");
        assertEquals("d", map.get(14));
        map.remove(1);
        assertEquals("d", map.get(14));
        map.put(12, "e");
        map.put(25, "f");
        assertEquals("f", map.get(25));
        map.remove(12);
        assertEquals("f", map.get(25));
    }

    @Test
    public void getException1() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        map.get(null);
    }

    @Test
    public void getException2() {
        thrown.expect(NoSuchElementException.class);
        thrown.expectMessage(notNullValue(String.class));
        map.put(1, "a");
        map.remove(1);
        map.get(1);
    }

    @Test
    public void containsKey() {
        map.put(1, "a");
        map.put(2, "b");
        assertTrue(map.containsKey(1));
        assertTrue(map.containsKey(2));
        map.put(14, "c");
        map.remove(1);
        assertFalse(map.containsKey(1));
        assertTrue(map.containsKey(14));
        assertFalse(map.containsKey(0));
    }

    @Test
    public void containsKeyException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        map.containsKey(null);
    }

    @Test
    public void keySet() {
        assertEquals(new HashSet<Integer>(){}, map.keySet());

        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");

        assertEquals(new HashSet<Integer>(){{
            add(1); add(2); add(3);
        }}, map.keySet());

        map.put(14, "d");
        assertEquals(new HashSet<Integer>(){{
            add(1); add(2); add(3); add(14);
        }}, map.keySet());

        map.remove(1);
        assertEquals(new HashSet<Integer>(){{
            add(2); add(3); add(14);
        }}, map.keySet());
    }

    @Test
    public void values() {
        assertTrue(listEquals(new LinkedList<>(), map.values()));

        map.put(3, "a");
        assertTrue(listEquals(new LinkedList<String>() {{
            add("a");
        }}, map.values()));

        map.put(2, "a");
        assertTrue(listEquals(new LinkedList<String>() {{
            add("a"); add("a");
        }}, map.values()));

        map.put(6, "b");
        map.remove(6);

        assertTrue(listEquals(new LinkedList<String>() {{
            add("a"); add("a");
        }}, map.values()));
    }

    @Test
    public void resizeBackingTable() {
        map.put(1, "a");
        map.resizeBackingTable(1);
        assertArrayEquals(
                new MapEntry[] {entry(1, "a")},
                map.getTable()
        );
        assertEquals(1, map.size());

        map.put(2, "b");
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), entry(2, "b")},
                map.getTable()
        );
        assertEquals(2, map.size());

        map.resizeBackingTable(2);
        assertArrayEquals(
                new MapEntry[] {entry(2, "b"), entry(1, "a")},
                map.getTable()
        );
        assertEquals(2, map.size());
    }

    @Test
    public void resizeBackingTableException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(notNullValue(String.class));
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.resizeBackingTable(2);
    }

    @Test
    public void clear() {
        map.put(1, "a");
        map.put(2, "b");
        map.remove(2);
        map.clear();

        assertArrayEquals(new MapEntry[13], map.getTable());
        assertEquals(0, map.size());

        map = new HashMap<>(5);
        map.clear();
        assertArrayEquals(new MapEntry[13], map.getTable());
        assertEquals(0, map.size());
    }

    /**
     * Making sure all the methods work after a resize
     */
    @Test
    public void resize() {
        map.put(1, "a");
        map.resizeBackingTable(1);
        assertFalse(map.containsKey(3));
        map.put(2, "b");
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), entry(2, "b")},
                map.getTable()
        );
        map.remove(2);
        assertArrayEquals(
                new MapEntry[] {null, entry(1, "a"), rem(2, "b")},
                map.getTable()
        );
        assertEquals(new HashSet<Integer>(){{add(1);}}, map.keySet());
        assertTrue(listEquals(
                new LinkedList<String>(){{add("a");}},
                map.values()
        ));
        assertFalse(map.containsKey(4));
        assertEquals(1, map.size());
    }

    @Test
    public void smallInputSize() {
        map = new HashMap<>(1);
        assertEquals(0, map.size());
        map.put(1, "a");
        assertArrayEquals(
                new MapEntry[]{null, entry(1, "a"), null},
                map.getTable()
        );
        assertEquals(1, map.size());
    }

    @Test
    public void largeInputSize() {
        java.util.Map<Integer, String> javaMap = new java.util.HashMap<>();
        Random rand = new Random(
                Stream.of("1331 TAs > 2110 TAs > 1332 TAs".split(""))
                        .mapToInt(Objects::hashCode)
                        .sum()
        );

        int inputs = 0xFFFF;

        for (int i = 0; i < inputs; i++) {
            int x = rand.nextInt();
            javaMap.put(x, i + "");
            map.put(x, i + "");
        }
        for (Integer x: javaMap.keySet()) {
            assertEquals(javaMap.get(x), map.get(x));
        }
    }

    ////////////////////////////////////////////////////////////////////////
    // Helper methods                                                     //
    ////////////////////////////////////////////////////////////////////////

    /**
     * My way of creating MapEntrys without all the hassle
     * @param s The key
     * @param i The value
     * @return a new MapEntry with the key and value
     */
    private MapEntry<Integer, String> entry(Integer i, String s) {
        return new MapEntry<>(i, s);
    }

    /**
     * My way of creating removed MapEntrys without all the hassle
     * @param s The key
     * @param i The value
     * @return a new MapEntry
     */
    private MapEntry<Integer, String> rem(Integer i, String s) {
        MapEntry<Integer, String> m = new MapEntry<>(i, s);
        m.setRemoved(true);
        return m;
    }

    /**
     * My way of checking that two lists have the same elements
     * @param list1 The first list
     * @param list2 The second list
     * @param <A> The type of each list
     * @return true if lists contain same elements, false otherwise
     */
    private <A> boolean listEquals(List<A> list1, List<A> list2) {
        Objects.requireNonNull(list1);
        Objects.requireNonNull(list2);
        list1 = new LinkedList<>(list1);
        list2 = new LinkedList<>(list2);
        if (list1.size() != list2.size()) {
            return false;
        }
        for (A a: list1) {
            list2.remove(a);
        }
        return list2.size() == 0;
    }

    ////////////////////////////////////////////////////////////////////////
    // This part is just testing that you didn't modify the signatures of //
    // the given methods                                                  //
    ////////////////////////////////////////////////////////////////////////

    interface Map<K, V> {
        V put(K k, V v);
        V remove(K k);
        V get(K k);
        boolean containsKey(K k);
        Set<K> keySet();
        List<V> values();
        void resizeBackingTable(int i);
        void clear();
        int size();
        MapEntry<K, V>[] getTable();
    }

    class Child<K, V> extends HashMap<K, V> implements Map<K, V> {}
}