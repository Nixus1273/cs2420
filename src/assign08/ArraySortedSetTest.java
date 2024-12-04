package assign08;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArraySortedSetTest {

    private ArraySortedSet<Integer> integerArraySet = new ArraySortedSet<Integer>();



//    void setUp(){
//
//
//    }




    @Test
    void testAddMethod(){
        ArrayList<Integer> expected = new ArrayList<>();
        for(int i = 0; i < 18; i++) {
            integerArraySet.add(i);
            expected.add(i);
        }

        assertEquals(expected, integerArraySet.toArrayList());

    }

    @Test
    void testAddAllMethodChanges(){
        Collection<Integer> collection = List.of(1, 4, 3, 6, 2, 7, -1, -5);


        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(-5, -1, 1, 2, 3, 4, 6, 7));

        assertTrue(integerArraySet.addAll(collection));
        assertEquals(expected, integerArraySet.toArrayList());

    }

    @Test
    void testAddAllMethodNoChange(){
        Collection<Integer> collection = List.of(1, 1, 1, 1);
        integerArraySet.add(1);
        System.out.println(Arrays.toString(collection.toArray()));

        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(1));

        assertFalse(integerArraySet.addAll(collection));
        assertEquals(expected, integerArraySet.toArrayList());

    }


    @Test
    void testClearMethod(){
        integerArraySet.add(1);
        assertFalse(integerArraySet.isEmpty());

        integerArraySet.clear();
        ArrayList<Integer> expected = new ArrayList<>();

        assertEquals(expected, integerArraySet.toArrayList());
        assertTrue(integerArraySet.isEmpty());

    }

    @Test
    void testContainsMethod(){
        integerArraySet.add(1);
        integerArraySet.add(-2);
        integerArraySet.add(5);



        assertTrue(integerArraySet.contains(5));

    }

    @Test
    void testContainsAllMethod(){
        Collection<Integer> collection = List.of(1, 5, 3, -1);
        integerArraySet.add(5);
        integerArraySet.add(-1);
        integerArraySet.add(3);
        integerArraySet.add(1);


        assertTrue(integerArraySet.containsAll(collection));

    }


    @Test
    void testIsEmptyMethod(){
        Collection<Integer> collection = List.of(1, 4, 3, 6, 2, 7, -1, -5);
        integerArraySet.addAll(collection);


        assertEquals(-5, integerArraySet.min());
        assertEquals(7  , integerArraySet.max());


    }



}