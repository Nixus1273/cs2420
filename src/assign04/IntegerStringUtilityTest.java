package assign04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Tests for the IntegerStringUtility class
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version September 19, 2024
 */
class IntegerStringUtilityTest {

	private final String[] emptyList = {};
	private final int[] emptyIntList = {};
	private final int[] intArray = {1023, 987, 3021, 789, 1302, 879};
//	private final int[] intLongArray = {10000000000, 1};
	private final String[] stringLongList = {"9223372036854775808", "9223372036854775809", "1"};
	private final String[] stringVarietyList = {"0000000000000000001", "32", "9223372036854775809", "04001", "001092"};
	private final String[] stringSimilarList = {"123", "321", "456", "645", "101", "110", "112"};
	private final String[] stringSimilarList2 = {"456", "645", "101", "110", "123", "321", "112"};
	private final String[] stringBigSimilarList = {"1104", "4011", "7890", "0987", "8970", "2345", "5432"};
	private final String[] stringShortList = {"012", "102", "378"};

	private final IntegerStringUtility.StringNumericalValueComparator numericalCmp =
			new IntegerStringUtility.StringNumericalValueComparator();
	private final IntegerStringUtility.StringSimilarityComparator similarityCmp =
			new IntegerStringUtility.StringSimilarityComparator();
	private final IntegerStringUtility.StringSimilarityGroupComparator groupCmp =
			new IntegerStringUtility.StringSimilarityGroupComparator();

	@Test
	void testInsertionSort() {
		IntegerStringUtility.insertionSort(stringVarietyList, numericalCmp);
		String[] expected = {"0000000000000000001", "32", "001092", "04001", "9223372036854775809"};
		assertArrayEquals(expected, stringVarietyList);
	}

	@Test
	void testFindMax() {
		assertEquals("9223372036854775809", IntegerStringUtility.findMax(stringVarietyList, numericalCmp));
	}

	// testing that findMax doesn't change original array
	@Test
	void testFindMaxStatic() {
		String[] expected = {"0000000000000000001", "32", "9223372036854775809", "04001", "001092"};
		IntegerStringUtility.findMax(stringVarietyList, numericalCmp);

		assertArrayEquals(expected, stringVarietyList);
	}

	@Test
	void testFindMaxEmptyArray() {
		assertThrows(NoSuchElementException.class,
				() -> IntegerStringUtility.findMax(emptyList, Comparator.naturalOrder()));
	}

	@Test
	void testStringSimilarityComparator() {
		IntegerStringUtility.insertionSort(stringSimilarList, similarityCmp);
		String[] expected = {"101", "110", "112", "123", "321", "456", "645"};
		assertArrayEquals(expected, stringSimilarList);
	}

	@Test
	void testStringSimilarityGroupComparatorDifferentLength() {
		assertTrue(groupCmp.compare(stringVarietyList, stringShortList) > 0);
	}

	@Test
	void testStringSimilarityGroupComparatorSameLength() {
		assertTrue(groupCmp.compare(stringSimilarList, stringBigSimilarList) < 0);
	}

	@Test
	void testStringSimilarityGroupComparatorSame() {
        assertEquals(0, groupCmp.compare(stringSimilarList, stringSimilarList2));
	}

	@Test
	void testGetSimilarityGroups() {
		String[][] expected = {{"101", "110"}, {"112"}, {"123", "321"}, {"456", "645"}};
		assertArrayEquals(expected, IntegerStringUtility.getSimilarityGroups(stringSimilarList));
	}

	@Test
	void testStringSimilarityGroupComparatorExample() {
		assertTrue(groupCmp.compare(new String[]{"3421", "1234", "4321"}, new String[]{"987", "789"}) > 0);
	}

	@Test
	void testStringSimilarityGroupComparatorExampleTwo() {
		assertTrue(groupCmp.compare(new String[]{"3421", "1234", "4321"},
				new String[]{"1235", "5321", "2153"}) < 0);
	}

	@Test
	void testFindMaxSimilarityGroup() {
		String[] expected = {"1023", "3021", "1302"};
		assertArrayEquals(expected, IntegerStringUtility.findMaximumSimilarityGroup(intArray));
	}

	@Test
	void testFindMaxSimilarityGroupStatic() {
		int[] expected = {1023, 987, 3021, 789, 1302, 879};
		IntegerStringUtility.findMaximumSimilarityGroup(intArray);

		assertArrayEquals(expected, intArray);
	}

	@Test
	void testFindMaxSimilarityGroupEmptyArray() {
		assertThrows(NoSuchElementException.class,
				() -> IntegerStringUtility.findMaximumSimilarityGroup(emptyIntList));
	}
}