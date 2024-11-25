package assign08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private final BinarySearchTree<Integer> tree = new BinarySearchTree<>();

    @BeforeEach
    void setUp(){
        tree.add(10);
        tree.add(5);
        tree.add(20);
        tree.add(7);
        tree.add(9);
        tree.add(8);
        tree.add(3);
        tree.add(11);
        tree.add(13);
        tree.add(12);
    }


    @Test
    void testAddMethod() {
        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 5, 7, 8, 9, 10, 11, 12, 13, 20));

        assertEquals(expected, tree.toArrayList());
    }


    @Test
    void testAddAllMethodChanges(){
        Collection<Integer> collection = List.of(5, 20, 7, 9, 8, 3, 11, 13, 12);

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(3, 5, 7, 8, 9, 10, 11, 12, 13, 20));

        assertFalse(tree.addAll(collection));
        assertEquals(expected, tree.toArrayList());
        assertEquals(10, tree.size());
    }


    @Test
    void testAddAllMethodChangesStill(){
        Collection<Integer> collection = List.of(26, 11, 3, 5);
        assertTrue(tree.addAll(collection));
    }


    @Test
    void testContainsMethod() {
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(9));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(13));
        assertTrue(tree.contains(10));
        assertFalse(tree.contains(6));
    }

    @Test
    void testMinMaxMethod() {
        assertEquals(3, tree.min());
        assertEquals(20, tree.max());
    }

    @Test
    void testRemoveMethod() {
        tree.remove(8);
        assertFalse(tree.contains(8));

        tree.remove(7);
        assertFalse(tree.contains(7));

        tree.remove(5);
        assertFalse(tree.contains(5));

        tree.remove(11);
        assertFalse(tree.contains(11));

        tree.remove(20);
        assertFalse(tree.contains(20));

        tree.remove(10);
        assertFalse(tree.contains(10));

        assertTrue(tree.contains(9));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(13));
        assertTrue(tree.contains(12));
    }

    @Test
    void testRemoveAllMethod() {
        Collection<Integer> collection = List.of(26, 11, 3, 5);
        assertTrue(tree.removeAll(collection));

        assertEquals(7, tree.size());
    }

    @Test
    void testEmpty() {
        ArrayList<Integer> expected = new ArrayList<>();
        tree.clear();
        assertEquals(expected, tree.toArrayList());

        assertFalse(tree.remove(0));
    }
}