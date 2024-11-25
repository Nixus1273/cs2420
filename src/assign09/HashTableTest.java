package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test HashTable class
 *
 * @author Brandon Flickinger and Ethan Laynor
 * @version November 14, 2024
 */
public class HashTableTest {
    private HashTable<Integer, String> hashTable;
    private HashTable<Integer, String> emptyTable;
    private HashTable<Integer, String> fullTable;

    @BeforeEach
    public void setUp() {
        emptyTable = new HashTable<>();

        hashTable = new HashTable<>();
        hashTable.put(1, "one");
        hashTable.put(2, "two");
        hashTable.put(3, "three");
        hashTable.put(4, "four");
        hashTable.put(5, "five");

        fullTable = new HashTable<>();
        for (int i = 0; i < 50; i++) {
            fullTable.put(i, "test");
        }
    }

    @Test
    public void testClear() {
        hashTable.clear();
        assertEquals(0, hashTable.size());
        List<MapEntry<Integer, String>> expected = List.of();
        assertEquals(expected, hashTable.entries());
    }

    @Test
    public void testClearOnEmpty() {
        emptyTable.clear();
        assertEquals(0, emptyTable.size());
        List<MapEntry<Integer, String>> expected = List.of();
        assertEquals(expected, emptyTable.entries());
    }

    @Test
    public void testContainsKey() {
        assertTrue(hashTable.containsKey(1));
        hashTable.remove(1);
        assertFalse(hashTable.containsKey(1));
    }

    @Test
    public void testContainsValue() {
        assertTrue(hashTable.containsValue("three"));
        hashTable.remove(3);
        assertFalse(hashTable.containsValue("three"));
    }


    @Test
    public void testEntries() {
        List<MapEntry<Integer, String>> expected = List.of(
                new MapEntry<>(1, "one"),
                new MapEntry<>(3, "three"),
                new MapEntry<>(4, "four"),
                new MapEntry<>(5, "five"));
        hashTable.remove(2);

        assertEquals(expected, hashTable.entries());
    }


    @Test
    public void testGet() {
        assertEquals("one", hashTable.get(1));
        hashTable.remove(1);
        assertNull(hashTable.get(1));
    }


    @Test
    public void testIsEmpty() {
        assertTrue(emptyTable.isEmpty());
        assertFalse(hashTable.isEmpty());
        hashTable.remove(1);
        hashTable.remove(2);
        hashTable.remove(3);
        hashTable.remove(4);
        hashTable.remove(5);
        assertTrue(hashTable.isEmpty());
    }


    @Test
    public void testPut() {
        StudentBadHash alan = new StudentBadHash(1019999, "Alan", "Turing");
        StudentBadHash ada = new StudentBadHash(1004203, "Adal", "Lovelace");
        StudentBadHash adam = new StudentBadHash(1019998, "Adam", "Sandler");
        StudentBadHash grace = new StudentBadHash(1019941, "Gray", "Hopper");
        StudentBadHash dave = new StudentBadHash(1019241, "Dave", "Hanks");

        HashTable<StudentBadHash, Double> studentGpaTable = new HashTable<>();
        studentGpaTable.put(alan, 3.2);
        studentGpaTable.put(adam, 3.2);
        studentGpaTable.put(ada, 3.5);
        studentGpaTable.put(grace, 4.0);
        studentGpaTable.put(dave, 4.0);

        assertEquals(5, studentGpaTable.size());
    }

    @Test
    public void testPutOverload() {
        for (int i = 0; i < 50; i++) {
            assertTrue(fullTable.containsKey(i));
        }
    }

    @Test
    public void testPutDeleted() {
        hashTable.remove(1  );
        hashTable.put(1, "two");

        assertEquals("two", hashTable.get(1));
    }

    @Test
    public void testPutOverride() {
        hashTable.put(1, "two");
        assertEquals("two", hashTable.get(1));
    }

    @Test
    public void testPutNull() {
        emptyTable.put(1, null);
        emptyTable.remove(1);
        assertEquals(0, emptyTable.size());
    }

    @Test
    public void testCollision() {
        emptyTable.put(0, "a"); // 0 collisions
        emptyTable.put(23, "b"); // 1 collisions
        emptyTable.put(46, "c"); // 2 collisions
        emptyTable.put(69, "d"); // 3 collisions
        emptyTable.put(92, "e"); // 4 collisions

        assertEquals(10, emptyTable.collisions());
    }
}
