package assign10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the BinaryMinHeap Class and the GraphUtility Class
 *
 * @author Brandon Flickinger and Ethan Laynor
 * @version November 20, 2024
 */
public class BinaryMinHeapTest {
    private BinaryMinHeap<Integer> binaryMinHeap;
    BinaryMinHeap<Integer> binaryMaxHeap = new BinaryMinHeap<>((i1, i2) -> i2 - i1);

    private BinaryMinHeap<Integer> binaryMinHeapEmpty = new BinaryMinHeap<>();

    List<Character> src = List.of('a', 'a', 'a', 'b', 'c', 'e', 'f', 'g');
    List<Character> dst = List.of('e', 'c', 'b', 'c', 'd', 'c', 'd', 'e');
    List<Double> wght = List.of(2.0, 32.0, 4.3, 10.0, 2.2, 1.1, 2.2, 1.0);

    @BeforeEach
    void setUp() {
        binaryMinHeap = new BinaryMinHeap<>();
        binaryMinHeap.add(85);
        binaryMinHeap.add(69);
        binaryMinHeap.add(41);
        binaryMinHeap.add(32);
        binaryMinHeap.add(31);
        binaryMinHeap.add(35);
        binaryMinHeap.add(13);
    }

    @Test
    void testAdd() {
        Object[] expected = new Object[]{13, 32, 31, 85, 41, 69, 35};
        assertArrayEquals(expected, binaryMinHeap.toArray());
        assertEquals(7, binaryMinHeap.size());
    }

    @Test
    void testAddOverload() {
        for (int i = 0; i < 33; i ++) {
            binaryMinHeap.add(i);
        }
        assertEquals(40, binaryMinHeap.size());
    }

    @Test
    void testPeek() {
        assertEquals(13, binaryMinHeap.peek());
    }

    @Test
    void testExtract() {
        Object[] expected = new Object[]{13, 31, 32, 35, 41, 69, 85};
        Object[] actual = new Object[7];
        assertEquals(7, binaryMinHeap.size());

        for (int i = 0; i < expected.length; i++) {
            actual[i] = binaryMinHeap.extract();
        }
        assertArrayEquals(expected, actual);
        assertTrue(binaryMinHeap.isEmpty());
    }

    @Test
    void testExtractNull() {
        assertThrows(NoSuchElementException.class, () -> binaryMinHeapEmpty.extract());
    }

    @Test
    void testClear() {
        binaryMinHeap.clear();
        assertTrue(binaryMinHeap.isEmpty());
        assertThrows(NoSuchElementException.class, () -> binaryMinHeap.extract());
    }

    @Test
    void testBuildHeap() {
        List<Integer> list = new ArrayList<>(Arrays.asList(85, 69, 41, 32, 31, 35, 13));
        binaryMinHeap = new BinaryMinHeap<>(list);
        Object[] expected = new Object[]{13, 31, 35, 32, 69, 85, 41};
        assertArrayEquals(expected, binaryMinHeap.toArray());
    }

    @Test
    void testBuildReverse() {
        binaryMaxHeap.add(85);
        binaryMaxHeap.add(69);
        binaryMaxHeap.add(41);
        binaryMaxHeap.add(32);
        binaryMaxHeap.add(31);
        binaryMaxHeap.add(35);
        binaryMaxHeap.add(13);

        Object[] expected = new Object[]{85, 69, 41, 35, 32, 31, 13};
        Object[] actual = new Object[7];
        for (int i = 0; i < expected.length; i++) {
            actual[i] = binaryMaxHeap.extract();
        }
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDijkstra() {
        assertEquals(GraphUtility.shortestWeightedPathWithSorting(src, dst, wght, 'a', 'd'),
                GraphUtility.shortestWeightedPathWithPriorityQueue(src, dst, wght, 'a', 'd'));
    }
}
