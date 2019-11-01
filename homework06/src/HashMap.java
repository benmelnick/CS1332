import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * Your implementation of HashMap.
 * 
 * @author Benjamin Melnick
 * @userid bmelnick3
 * @GTID 903305201
 * @version 1.0
 */
public class HashMap<K, V> {

    // DO NOT MODIFY OR ADD NEW GLOBAL/INSTANCE VARIABLES
    public static final int INITIAL_CAPACITY = 5;
    public static final double MAX_LOAD_FACTOR = 0.5;
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
    }

    /**
     * Adds the given key-value pair to the HashMap.
     * If an entry in the HashMap already has this key, replace the entry's
     * value with the new one passed in.
     *
     * In the case of a collision, use linear probing as your resolution
     * strategy.
     *
     * At the start of the method, you should check to see if the array would
     * violate the max load factor after adding the data (regardless of
     * duplicates). For example, let's say the array is of length 5 and the
     * current size is 3 (LF = 0.6). For this example, assume that no elements
     * are removed in between steps. If another entry is attempted to be added,
     * before doing anything else, you should check whether (3 + 1) / 5 = 0.8
     * is larger than the max LF. It is, so you would trigger a resize before
     * you even attempt to add the data or figure out if it's a duplicate.
     *
     * When regrowing, resize the length of the backing table to
     * 2 * old length + 1. You must use the resizeBackingTable method to do so.
     *
     * Return null if the key was not already in the map. If it was in the map,
     * return the old value associated with it.
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @throws IllegalArgumentException if key or value is null
     * @return null if the key was not already in the map. If it was in the
     * map, return the old value associated with it
     */
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot put null keys.");
        }
        if (value == null) {
            throw new IllegalArgumentException("Cannot put null values.");
        }
        double loadFactor = (size + 1.0) / table.length;
        if (loadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        //linear probing
        int index = linearProbe(key);
        if (table[index] != null && !table[index].isRemoved()) {
            //the spot was already occupied - just change the value
            MapEntry<K, V> pair = table[index];
            V oldVal = pair.getValue();
            table[index].setValue(value);
            return oldVal;
        }
        table[index] = new MapEntry<K, V>(key, value);
        size++;
        return null;
    }

    /**
     * calculates the index to add a pair in the table
     * performs linear probing
     * @param key the key being added to the table
     * @return index in the table to add the pair
     */
    private int linearProbe(K key) {
        int hash = key.hashCode() % table.length;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        int probeCount = 0;
        int delMarker = 0; //tracker for DEL marker
        while (table[hash] != null && probeCount < table.length) {
            if (table[hash].getKey().equals(key)) {
                //key already exists
                return hash;
            }
            if (table[hash].isRemoved()) {
                //encountered a removed index
                delMarker = hash;
            }
            hash = ++hash % table.length;
            probeCount++;
        }
        if (delMarker != 0) {
            return delMarker;
        }
        return hash;
    }

    /**
     * performs a linear probe to find the index of an existing key
     * @param key a key that already exists in table
     * @throws NoSuchElementException if the key was not found in the table
     * @return index of key
     */
    private int findKeyIndex(K key) {
        int hash = key.hashCode() % table.length;
        if (hash < 0) {
            hash = Math.abs(hash);
        }
        int probeCount = 0;
        while (table[hash] != null && probeCount < table.length) {
            if (table[hash].getKey().equals(key) && !table[hash].isRemoved()) {
                return hash;
            }
            hash = ++hash % table.length;
            probeCount++;
        }
        throw new NoSuchElementException("This key does "
                + "not exist in the table.");
    }

    /**
     * Removes the entry with a matching key from the HashMap.
     *
     * @param key the key to remove
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key does not exist
     * @return the value previously associated with the key
     */
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot remove "
                    + "values with null keys.");
        }
        int index = findKeyIndex(key);
        table[index].setRemoved(true);
        size--;
        return table[index].getValue();
    }

    /**
     * Gets the value associated with the given key.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @throws java.util.NoSuchElementException if the key is not in the map
     * @return the value associated with the given key
     */
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot retrieve "
                    + "values from null keys.");
        }
        int index = findKeyIndex(key);
        return table[index].getValue();
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key the key to search for
     * @throws IllegalArgumentException if key is null
     * @return whether or not the key is in the map
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot check "
                    + "to see if a null key is in a table.");
        }
        try {
            int index = findKeyIndex(key);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Returns a Set view of the keys contained in this map.
     * Use {@code java.util.HashSet}.
     *
     * @return set of keys in this map
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                keys.add(table[i].getKey());
            }
        }
        return keys;
    }

    /**
     * Returns a List view of the values contained in this map.
     *
     * Use {@code java.util.ArrayList} or {@code java.util.LinkedList}.
     *
     * You should iterate over the table in order of increasing index and add 
     * entries to the List in the order in which they are traversed.
     *
     * @return list of values in this map
     */
    public List<V> values() {
        List<V> values = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                values.add(table[i].getValue());
            }
        }
        return values;
    }

    /**
     * Resize the backing table to {@code length}.
     *
     * Disregard the load factor for this method. So, if the passed in length is
     * smaller than the current capacity, and this new length causes the table's
     * load factor to exceed MAX_LOAD_FACTOR, you should still resize the table
     * to the specified length and leave it at that capacity.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Remember that you cannot just simply copy the entries over to the new
     * array.
     *
     * Also, since resizing the backing table is working with the non-duplicate
     * data already in the table, you shouldn't need to check for duplicates.
     *
     * @param length new length of the backing table
     * @throws IllegalArgumentException if length is less than the number of
     * items in the hash map
     */
    public void resizeBackingTable(int length) {
        //When resizing, the table's length changes so you have to recalculate
        // the index of every single index.
        if (length < size) {
            throw new IllegalArgumentException("Cannot resize "
                    + "table to less than the current number of elements.");
        }
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[length];
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && !oldTable[i].isRemoved()) {
                //recalculate index
                int index = linearProbe(oldTable[i].getKey());
                table[index] = oldTable[i];
            }
        }
    }

    /**
     * Clears the table and resets it to {@code INITIAL_CAPACITY}.
     */
    public void clear() {
        table = new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }
    
    /**
     * Returns the number of elements in the map.
     *
     * DO NOT USE OR MODIFY THIS METHOD!
     *
     * @return number of elements in the HashMap
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * DO NOT USE THIS METHOD IN YOUR CODE. IT IS FOR TESTING ONLY.
     *
     * @return the backing array of the data structure, not a copy.
     */
    public MapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }
}