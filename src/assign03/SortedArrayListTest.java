package assign03;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains the test cases for the SortedArrayList.java file
 *
 * @author Brandon Flickinger and Ethan Laynor
 * @version September 11, 2024
 */
class SortedArrayListTest {

    private SortedArrayList<String> listOfStrings;
    private SortedArrayList<Integer> emptyIntList;
    private SortedArrayList<Character> fullCharList;
    private Comparator<Integer> cmp;

    @BeforeEach
    void setUp() {
        cmp = (Integer obj1, Integer obj2)->obj2-obj1;

        listOfStrings = new SortedArrayList<>();
        listOfStrings.insert("Pizza");
        listOfStrings.insert("Lemon");
        listOfStrings.insert("Apple");  // index = 2
        listOfStrings.insert("Watermelon");
        listOfStrings.insert("Bread");

        emptyIntList = new SortedArrayList<>(cmp);

        fullCharList = new SortedArrayList<>();
        fullCharList.insert('a');
        fullCharList.insert('b');
        fullCharList.insert('c');
        fullCharList.insert('d');
        fullCharList.insert('e');
        fullCharList.insert('"');
        fullCharList.insert(':');
        fullCharList.insert('%');
        fullCharList.insert('!');
        fullCharList.insert('@');
        fullCharList.insert('A');
        fullCharList.insert('B');
        fullCharList.insert('C');
        fullCharList.insert('D');
        fullCharList.insert('E');
        fullCharList.insert('F');
    }


    // test(s) for the clear method
    @Test
    void testClear() {
        listOfStrings.clear();
        assertEquals(0, listOfStrings.size());
    }

    @Test
    void testEmptyClear() {
        emptyIntList.clear();
        assertEquals(0, emptyIntList.size());
    }


    // test(s) for the contains method
    @Test
    void testContains() {
        assertTrue(listOfStrings.contains("Lemon"));
    }

    @Test
    void testNotContain() {
        assertFalse(listOfStrings.contains("Egg"));
    }

    @Test
    void testNullContain() {
        assertThrows(IllegalArgumentException.class, () -> {listOfStrings.contains(null); });
    }

    @Test
    void testEmptyContain() {
        assertFalse(emptyIntList.contains(5));
    }


    // test(s) for the countEntries method
    @Test
    void testCountEntries() {
        listOfStrings.insert("Lemon");
        listOfStrings.insert("Lemon");
        listOfStrings.insert("Lemon");
        assertEquals(4, listOfStrings.countEntries("Lemon"));
    }

    @Test
    void testCountNotEntries() {
        assertEquals(0, listOfStrings.countEntries("Grape"));
    }

    @Test
    void testEmptyCountEntries() {
        assertEquals(0, emptyIntList.countEntries(4));
    }

    @Test
    void testCountNullEntries() {
        assertThrows(IllegalArgumentException.class, () -> {listOfStrings.contains(null); });
    }

    @Test
    void testOutOfBounds() {
        emptyIntList.insert(0);
        emptyIntList.insert(0);
        emptyIntList.insert(0);
        emptyIntList.insert(0);

        assertEquals(4, emptyIntList.countEntries(0));
    }

    @Test
    void test4096() {
        for (int i = 0; i < 4; i++) {
            emptyIntList.insert(0);
        }

        assertEquals(0, emptyIntList.countEntries(1));
    }


    // tests for the insert method
    @Test
    void testInsertFull() {
        fullCharList.insert('2');
        assertEquals(17, fullCharList.size());
    }

    @Test
    void testInsertNull() {
        assertThrows(IllegalArgumentException.class, () -> {listOfStrings.insert(null); });
    }

    @Test
    void testInsertCmp() {
        emptyIntList.insert(0);
        emptyIntList.insert(10);
        emptyIntList.insert(30);
        emptyIntList.insert(20);

        Integer[] copy = new Integer[]{30, 20, 10, 0};

        assertArrayEquals(copy, emptyIntList.toArray());
    }

    @Test
    void testCorrectInsert() {
        String[] copy = new String[]{"Apple", "Bread", "Lemon", "Pizza", "Watermelon"};

        assertArrayEquals(copy, listOfStrings.toArray());
    }


    // tests for isEmpty method
    @Test
    void testIsEmpty() {
        assertFalse(listOfStrings.isEmpty());
    }

    @Test
    void testIsNotEmpty() {
        assertTrue(emptyIntList.isEmpty());
    }


    // tests for max method
    @Test
    void testMaxString() {
        assertEquals("Watermelon", listOfStrings.max());
    }

    @Test
    void testMaxEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> {emptyIntList.max(); });
    }


    // tests for the median method
    @Test
    void testOddMedian() {
        assertEquals("Lemon", listOfStrings.median());
    }

    @Test
    void testEvenMedian() {
        listOfStrings.insert("Nuts");
        assertEquals("Nuts", listOfStrings.median());
    }

    @Test
    void testMedianEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> {emptyIntList.median(); });
    }


    // tests for the min method
    @Test
    void testMinChar() {
        assertEquals('!', fullCharList.min());
    }

    @Test
    void testMinEmpty() {
        assertThrows(java.util.NoSuchElementException.class, () -> {emptyIntList.min(); });
    }


    // tests for size method
    @Test
    void testSize() {
        assertEquals(5, listOfStrings.size());
    }

    @Test
    void testSizeEmpty() {
        assertEquals(0, emptyIntList.size());
    }


    // tests for the toArray method
    @Test
    void testToArray() {
        assertEquals(listOfStrings.size(), listOfStrings.toArray().length);
    }

    @Test
    void testEmptyToArray() {
        assertEquals(emptyIntList.size(), emptyIntList.toArray().length);
    }

}