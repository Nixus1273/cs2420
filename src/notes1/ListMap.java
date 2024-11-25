package notes1;

import java.util.List;
import java.util.ArrayList;

import assign09.Map;
import assign09.MapEntry;

/**
 * This class demonstrates how to implement the Map interface, using
 * a simple ArrayList. (This is not a hash table.)
 * 
 * @author Aaron Wood
 * @version 2024-10-31
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 */
public class ListMap<K, V> implements Map<K, V> {
	
	private final ArrayList<MapEntry<K, V>> list;
	
	public ListMap() {
		list = new ArrayList<MapEntry<K, V>>();
	}

	/**
	 * Removes all mappings from this map.
	 * @implNote O(1) for a list
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * Determines whether this map contains the specified key.
	 * @implNote O(N) for a list, where N is the size of the list
	 * 
	 * @param key - key to search for
	 * @return true if this map contains the key, false otherwise
	 */
	public boolean containsKey(K key) {
		for(MapEntry<K, V> e : list)
			if(e.getKey().equals(key))
				return true;
		return false;
	}

	/**
	 * Determines whether this map contains the specified value.
	 * @implNote O(N) for a list, where N is the size of the list
	 * 
	 * @param value - value to search for
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	public boolean containsValue(V value) {
		for(MapEntry<K, V> e : list)
			if(e.getValue().equals(value))
				return true;
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering of 
	 * mapping in the list is insignificant.
	 * @implNote O(N) for a list, where N is the size of the list
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	public List<MapEntry<K, V>> entries() {
		List<MapEntry<K, V>> copy = new ArrayList<MapEntry<K, V>>();
		for(MapEntry<K, V> e : list)
			copy.add(e);
		return copy;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * @implNote O(N) for a list, where N is the size of the list
	 * 
	 * @param key - key for which to retrieve its value
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	public V get(K key) {
		for(MapEntry<K, V> e : list)
			if(e.getKey().equals(key))
				return e.getValue();
		return null;
	}
	
	/**
	 * Determines whether this map contains any mappings.
	 * @implNote O(1) for a list
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Associates the specified value with the specified key in this map.
	 * (I.e., if the key already exists in this map, resets the value; 
	 * otherwise adds the specified key-value pair.)
	 * @implNote O(N) for a list, where N is the size of the list
	 * 
	 * @param key - key given
	 * @param value - value given
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V put(K key, V value) {
		for(MapEntry<K, V> e : list)
			if(e.getKey().equals(key)) {
				// if the key already exists in this map, resets the value
				V oldValue = e.getValue();
				e.setValue(value);
				return oldValue;
			}
		// otherwise adds the specified key-value pair
		list.add(new MapEntry<K, V>(key, value));
		return null;
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * @implNote O(N) for a list, where N is the size of the list
	 *
	 * @param key - key to remove
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	public V remove(K key) {
		for(int i = 0; i < list.size(); i++) {
			MapEntry<K, V> e = list.get(i);
			if(e.getKey().equals(key)) {
				V oldValue = e.getValue();
				list.remove(i);
				return oldValue;
			}
		}
		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 * @implNote O(1) for a list
	 * 
	 * @return the number of entries in this map
	 */
	public int size() {
		return list.size();
	}
}