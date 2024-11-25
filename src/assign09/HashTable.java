package assign09;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a hash table, a collection of key value pairs.
 *
 * @author Brandon Flickinger and Ethan Laynor
 * @version November 14, 2024
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 */
public class HashTable<K, V> implements Map<K, V> {

    private Object[] array;
    private boolean[] graveyard;
    private int size;
    private int collisions;

    /**
     * Constructs a hash table.
     */
    public HashTable() {
        this.array = new Object[23];
        this.graveyard = new boolean[23];
        this.size = 0;
        this.collisions = 0;
    }

    /**
     * Removes all mappings from this map.
     * O(1) for a quadratic-probing hash table
     */
    @Override
    public void clear() {
        this.array = new Object[23];
        this.graveyard = new boolean[23];
        this.size = 0;
        this.collisions = 0;
    }

    /**
     * Determines whether this map contains the specified key.
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key being searched for
     * @return true if this map contains the key, false otherwise
     */
    @Override
    public boolean containsKey(K key) {
        return this.graveyard[search(key)];
    }

    /**
     * Determines whether this map contains the specified value.
     * O(table length) for a quadratic-probing hash table
     *
     * @param value - the value being searched for
     * @return true if this map contains one or more keys to the specified value,
     * false otherwise
     */
    @Override
    public boolean containsValue(V value) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i] != null) {
                if (this.graveyard[i] && getElement(i).getValue().equals(value))
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns a list view of the mappings contained in this map, where the ordering of
     * mapping in the list is insignificant.
     * O(table length) for a quadratic-probing hash table
     *
     * @return a List object containing all mapping (i.e., entries) in this map
     */
    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> list = new ArrayList<>();
        for (int i = 0; i < this.array.length; i++) {
            if (this.graveyard[i]) {
                list.add(getElement(i));
            }
        }
        return list;
    }

    /**
     * Gets the value to which the specified key is mapped.
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key for which to get the mapped value
     * @return the value to which the specified key is mapped, or null if this map
     * contains no mapping for the key
     */
    @Override
    public V get(K key) {
        int index = search(key);
        if (this.graveyard[index]) {
            return getElement(index).getValue();
        }
        return null;
    }

    /**
     * Determines whether this map contains any mappings.
     * O(1) for a quadratic-probing hash table
     *
     * @return true if this map contains no mappings, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * (i.e., if the key already exists in this map, resets the value;
     * otherwise adds the specified key-value pair.)
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key for which to update the value (if exists) or to be added to the table
     * @param value - the value to be mapped to the key
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V put(K key, V value) {
        if ((double) (this.size + 1) / this.array.length >= 0.5) {
            int newSize = this.array.length * 2;
            while (!isPrime(newSize)) {
                newSize++;
            }

            List<MapEntry<K, V>> entries = entries();
            this.array = new Object[newSize];
            this.graveyard = new boolean[newSize];
            this.size = 0;

            for (MapEntry<K, V> entry : entries) {
                put(entry.getKey(), entry.getValue());
            }
        }

        int hash =  Math.abs(key.hashCode()) % this.array.length;
        int index = hash;
        int i = 1;

        while (this.graveyard[index]) {
            MapEntry<K, V> entry = getElement(index);
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                this.collisions += i - 1;
                return oldValue;
            }
            index = (hash + (i * i)) % this.array.length;
            i++;
        }

        this.array[index] = new MapEntry<>(key, value);
        this.graveyard[index] = true;
        this.size++;
        this.collisions += i - 1;
        return null;
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * O(1) for a quadratic-probing hash table
     *
     * @param key - the key to be removed
     * @return the previous value associated with key, or null if there was no
     * mapping for key
     */
    @Override
    public V remove(K key) {
        int index = search(key);
        if (this.graveyard[index]) {
            this.graveyard[index] = false;
            this.size--;
            return getElement(index).getValue();
        } else {
            return null;
        }
    }

    /**
     * Determines the number of mappings in this map.
     * O(1) for a quadratic-probing hash table
     *
     * @return the number of mappings in this map
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Get collision count
     *
     * @return number of collisions
     */
    public int collisions() {
        return this.collisions;
    }

    /**
     * Helper method to get a value cast to MapEntry
     *
     * @param index - index in array
     * @return MapEntry at index
     */
    @SuppressWarnings("unchecked")
    private MapEntry<K, V> getElement(int index) {
        return (MapEntry<K, V>) array[index];
    }

    /**
     * Helper method to check if an integer is prime
     *
     * @return true if prime, false if not
     */
    private boolean isPrime(int n){
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
    
    /**
     * Helper method to search the hash table with quadratic probing
     * 
     * @param key - key searching for
     * @return - index
     */
    private int search(K key){
        int hash =  Math.abs(key.hashCode()) % this.array.length;
        int index = hash;
        int i = 1;

        while (graveyard[index]) {
            if (getElement(index).getKey().equals(key) && graveyard[index]) {
                return index; // returns index to the matching key
            }
            index = (hash + (i * i)) % this.array.length;
            i++;
        }
        return index; // returns index to an open slot
    }
}
