package lab06;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assign03.SortedArrayList;

/**
 * This class contains tests for the use of iterators in Assignment 3's
 * SortedArrayList, (for Lab 6).
 *
 * @author CS 2420 course staff
 * @version September 27, 2024
 */
public class IteratorTest {

	private SortedArrayList<Integer> smallListOfEvenIntegers;
	private SortedArrayList<Integer> largeList;
	private Iterator<Integer> smallIterator;

	@BeforeEach
	void setUp() throws Exception {
		smallListOfEvenIntegers = new SortedArrayList<Integer>();
		for(int half = 1; half <= 10; half++)
			smallListOfEvenIntegers.insert(half*2);
		// backing array should be { 2, 4, 6, ..., 16, 18, 20 }

		smallIterator = smallListOfEvenIntegers.iterator();

		largeList = new SortedArrayList<Integer>();
		for(int i = 1; i <= 100; i++)
			largeList.insert(i);
		// backing array should be { 1, 2, 3, ..., 98, 99, 100 }
	}

	@Test
	public void iterateOverEvenSet() {
		for(int expected = 2; expected <= 20; expected += 2)
			assertEquals(expected, (int)smallIterator.next());
	}

	@Test
	public void expectedNoSuchElementThrown() {
		for(int count = 0; count < 10; count++) {
			smallIterator.next();
		}
		assertThrows(NoSuchElementException.class, () -> { smallIterator.next(); });
	}

	@Test
	public void hasNextReturnsTrue() {
		assertTrue(smallIterator.hasNext());
	}

	@Test
	public void hasNextReturnsFalseAtEnd() {
		for(int count = 0; count < 10; count++) {
			smallIterator.next();
		}
		assertFalse(smallIterator.hasNext());
	}

	@Test
	public void removeMaxElement() {
		for(int i = 0; i < 10; i++)
			smallIterator.next();
		smallIterator.remove();  // removes 20
		assertEquals(18, (int)smallListOfEvenIntegers.max());  // now max is 18
	}

	@Test
	public void removeWithoutCallToNext() {
		assertThrows(IllegalStateException.class, () -> { smallIterator.remove(); });
	}

	@Test
	public void removeEverything() {
		while(smallIterator.hasNext()) {
			smallIterator.next();
			smallIterator.remove();
		}
		assertEquals(0, smallListOfEvenIntegers.size());
	}

	@Test
	public void removeEveryOtherElement() {
		Iterator<Integer> iterator = largeList.iterator();
		while(iterator.hasNext()) {
			iterator.next();
			iterator.remove();
			if(iterator.hasNext()) {
				iterator.next();
			}
		}
		// backing array should now be { 2, 4, 6, ..., 96, 98, 100 }

		assertEquals(50, largeList.size());

		Object[] arr = new Object[50];
		for(int i = 0; i < 50; i++) {
			arr[i] = i * 2 + 2;
		}

		assertArrayEquals(arr, largeList.toArray());
	}
}