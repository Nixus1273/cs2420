package assign06;

import static org.junit.jupiter.api.Assertions.*;

import assign04.IntegerStringUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTest {
    private final SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
    private final LinkedListQueue<Integer> queue = new LinkedListQueue<>();

    private SinglyLinkedList<Integer> fullList = new SinglyLinkedList<>();

    @BeforeEach
    void setup() {
        fullList.insertFirst(0);
        fullList.insert(1, 1);
        fullList.insert(2, 2);
        fullList.insert(3, 3);
        fullList.insert(4, 4);
    }

    @Test
    void testClear() {
        list.insertFirst(0);
        list.insertLast(1);
        list.clear();
        assertEquals(0, list.size);
    }

    @Test
    void testDelete() {
        Integer[] expected = {};

        list.insertFirst(0);
        list.insertFirst(-1);

        list.deleteFirst();

        list.insertLast(24);
        list.insertLast(25);
        list.insert(1, 1);

        list.delete(2);

        list.insert(2, 2);
        list.insertLast(3);

        list.delete(0);
        list.delete(1);
        list.delete(1);
        list.clear();

        assertArrayEquals(expected, list.toArray());
    }

    @Test
    void testDeleteIndex() {
        Integer[] expected = {0, 1, 2};
        list.insertFirst(0);
        list.insert(1, 1);
        list.insert(2, 2);
        list.insertLast(3);
        list.delete(2);
        System.out.println(Arrays.toString(list.toArray()));
        assertEquals(1, list.get(1));
    }

    @Test
    void testDeleteLast() {
        Integer[] expected = {0, 1, 2};
        list.insertFirst(0);
        list.insert(1, 1);
        list.insert(2, 2);
        list.insertLast(3);
        list.delete(3);
        assertEquals(3, list.size);
    }

    @Test
    void testDeleteFirst() {
        Integer[] expected = {1, 2, 3};
        list.insertFirst(0);
        list.insert(1, 1);
        list.insert(2, 2);
        list.insertLast(3);
        list.deleteFirst();
        assertEquals(1, list.deleteFirst());
    }

    @Test
    void testInsertFirst() {
        Integer[] expected = {2, 1, 0};
        list.insertFirst(0);
        list.insertFirst(1);
        list.insertFirst(2);
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    void testInsertLast() {
        Integer[] expected = {0, 1, 1};
        list.insertFirst(0);
        list.insertLast(1);
        list.insertLast(1);
        list.delete(1);
        list.insertLast(1);
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    void testInsert() {
        Integer[] expected = {0, 1, 3};
        list.insertLast(0);
        list.insert(1, 1);
        list.insert(2, 2);
        list.delete(2);
        list.insert(2, 3);
        list.insertFirst(-1);
        list.delete(0);
        list.insertLast(4);
        list.delete(3);
        list.insertLast(1);
        list.delete(3);
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    void testGet() {
        list.insertFirst(0);
        list.insertFirst(1);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(Arrays.toString(list.toArray()));
        list.insert(2, 2);
        list.delete(1);
        assertEquals(2, list.get(1));
    }

    @Test
    void testGetLast() {
        list.insertFirst(0);
        list.insert(1, 1);
        list.insertLast(3);
        list.insert(2, 2);
        list.delete(2);
        assertEquals(2, list.getLast());
    }

    @Test
    void testIndexOf() {
        list.insertFirst(0);
        list.insert(1, 1);
        list.insert(2, 2);
        list.insert(3, 3);
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    void testQueue() {
        Integer[] expected = {0, 1, 2, 3};
        queue.offer(0);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertEquals(4, queue.size());
    }

    @Test
    void testIterator() {
        Iterator<Integer> iterator = fullList.iterator();
//        while (iterator.hasNext()) {
//            iterator.next();
//            iterator.remove();
//            System.out.println(iterator.hasNext());
//        }
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    void testIteratorRemove() {
        Iterator<Integer> iterator = fullList.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            System.out.println(iterator.hasNext());
        }
        iterator.remove();
        assertEquals(3, fullList.getLast());
    }
}