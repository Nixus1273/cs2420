package notes1;

/**
 * HashTable implementation for CustomString data type using open addressing and linear probing.
 *
 * @author Aaron Wood
 * @version 2024-10-31
 */
public class HashTable {
    private CustomString[] table;
    private int size;
    private int collisionCount;
    private int comparisonCount;
    private boolean searching;

    /**
     * Construct empty hash table with backing table of a given capacity.
     * @param capacity - backing table capacity
     */
    public HashTable(int capacity) {
        table = new CustomString[capacity];
        size = 0;
        collisionCount = 0;
        searching = false;
    }

    /**
     * Construct empty hash table with backing table of capacity 100.
     */
    public HashTable() {
        this(100);
    }

    /**
     * @return the number of elements in the table
     */
    public int size() {
        return size;
    }

    public double loadFactor() {
        return ((double) size) / table.length;
    }

    /**
     * @return the total number of collisions when adding
     */
    public int collisionCount() {
        return collisionCount;
    }

    /**
     * @return the total number of comparisons when searching
     */
    public int comparisonCount() {
        return comparisonCount;
    }

    /**
     * Determine whether an item is contained in the hash table
     * @param item - item to searc for
     * @return true if item is present, false otherwise
     */
    public boolean contains(CustomString item) {
        searching = true;
        comparisonCount = 0;
        return table[findIndex(item)] != null;
    }

    /**
     * Adds a new item to the hash table.
     * @param item - item to add
     * @return true if item is added, false if it was already present
     */
    public boolean add(CustomString item) {
        searching = false;
        int index = findIndex(item);

        if (table[index] != null) {
            return false;
        }

        if (size == table.length) {
            System.err.println("Oh no!! We need to resize");
        }

        table[index] = item;
        size++;
        return true;
    }

    /**
     * Helper method to find the correct index for the item.
     * @implNote Uses linear probing to search for a matching cell or an empty cell
     * @implNote Updates comparisonCount and collisionCount as appropriate
     * @param item - item for which an index is needed
     * @return index of where item exists or where it could be added
     */
    private int findIndex(CustomString item) {
        int index = Math.abs(item.hashCode()) % table.length;

        while (table[index] != null) {
            if (searching) {
                comparisonCount++;
            }

            if (item.equals(table[index])) {
                break;
            }
            if (!searching) {
                collisionCount++;
            }
            index = (index + 1) % table.length;
        }

        return index;
    }
}
