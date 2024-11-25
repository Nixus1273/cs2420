package assign04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * This class is static and is used to sort arrays with an insertion sort method by natural ordering, similarity,
 * and similarity groups. It contains 3 nested class comparators to define how we make these comparisons.
 * @author Ethan Laynor and Brandon Flickinger
 * @version September 19, 2024
 */
public class IntegerStringUtility<E> {

	/**
	 * This method runs an insertion sort on an array of integers represented as strings.
	 * This method cannot accept any values that are not positive integers represented as strings.
	 * CANNOT accept: null values, negative values, non-declared array.
	 * @param array the array to be sorted
	 * @param cmp the comparator
	 */
	public static <E> void insertionSort(E[] array, Comparator<? super E> cmp) {
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > 0; j--) {
				if(cmp.compare(array[j], array[j-1]) < 0) {
					E temp = array[j];
					array[j] = array[j-1];
					array[j-1] = temp;
				}
			}
		}
	}

	/**
	 * Finds the largest element of an array after using insertionSort() on a copy of the array
	 * @param array the given array
	 * @param cmp the comparator
	 * @return the last element in the sorted array
	 * @throws NoSuchElementException when array is empty
	 */
	public static <E> E findMax(E[] array, Comparator<? super E> cmp) throws NoSuchElementException{
		if(array.length == 0)
			throw new NoSuchElementException("Array cannot be empty!");

		E[] tempArray = Arrays.copyOf(array, array.length);
		insertionSort(tempArray, cmp);

		return tempArray[tempArray.length - 1];
	}

	/**
	 * This nested class defines the comparison of positive integer values represented as strings, numerically
	 * The behavior of this comparator is undefined if one or both of the strings being compared do not represent
	 * a positive integer value.
	 */
	public static class StringNumericalValueComparator implements Comparator<String>{
		@Override
		public int compare(String obj1, String obj2) {
			String tempobj1 = obj1;
			String tempobj2 = obj2;

			String nonZeroObj1 = tempobj1;
			for (int i = 0; i < tempobj1.length(); i++) {
				if (tempobj1.charAt(i) == '0')
					nonZeroObj1 = tempobj1.substring(i + 1);
				else {
					break;
				}
			}

			String nonZeroObj2 = tempobj2;
			for (int i = 0; i < tempobj2.length(); i++) {
				if (tempobj2.charAt(i) == '0')
					nonZeroObj2 = tempobj2.substring(i + 1);
				else {
					break;
				}
			}

			if (nonZeroObj1.length() == nonZeroObj2.length()) {
				return nonZeroObj1.compareTo(nonZeroObj2);
			} else {
				return nonZeroObj1.length() - nonZeroObj2.length();
			}

		}
	}

	/**
	 * This nested class defines the comparison of strings, by similarity.  When two strings have different lengths,
	 * they are not similar and the shorter string comes before the longer string. When two strings have the same
	 * length but are not similar, the sorted order of the characters are compared lexicographically.
	 */
	public static class StringSimilarityComparator implements Comparator<String>{
		@Override
		public int compare(String obj1, String obj2) {
			if(obj1.length() == obj2.length()) {

				Comparator<String> cmp = new StringNumericalValueComparator();

				String[] obj1Array =  obj1.split("");
				String[] obj2Array =  obj2.split("");

				IntegerStringUtility.insertionSort(obj1Array, cmp);
				IntegerStringUtility.insertionSort(obj2Array, cmp);

				for(int i = 0; i < obj1Array.length; i++) {
					if(obj1Array[i].compareTo(obj2Array[i]) > 0)
						return 1;
					else if(obj1Array[i].compareTo(obj2Array[i]) < 0)
						return -1;
				}
				return 0;
			}else
				return(obj1.length() - obj2.length());
		}
	}

	/**
	 * This nested class defines the comparison of similarity groups by group size (i.e., array length). If two groups
	 * have the same size, the group with the largest integer value (represented as a string) is deemed the largest
	 * group. If both groups are empty, they are deemed equal.
	 */
	public static class StringSimilarityGroupComparator implements Comparator<String[]>{
		/**
		 * @param obj1 the first object to be compared.
		 * @param obj2 the second object to be compared.
		 * @return if lengths are same, return positive int if max value in obj1 is larger than the max value in obj2
		 *  and vice versa if negative
		 * @return difference of lengths
		 */
		@Override
        public int compare(String[] obj1, String[] obj2) {
			if(obj1.length == obj2.length) {
				Comparator<String> cmp = new StringNumericalValueComparator();
				return cmp.compare(IntegerStringUtility.findMax(obj1, cmp), IntegerStringUtility.findMax(obj2, cmp));
			} else {
				return(obj1.length - obj2.length);
			}
		}
	}

	/**
	 * This method returns the similarity groups in the input array.  Each row in the two-dimensional array returned
	 * is a single similarity group, and each string in a row is similar to every other string in the same row
	 * @param array the given array
	 * @return an array of arrays (similar groups)
	 */
	public static String[][] getSimilarityGroups(String[] array){
		StringSimilarityComparator cmp = new StringSimilarityComparator();
		String[] tempBasicArray = Arrays.copyOf(array, array.length);
		insertionSort(tempBasicArray, cmp);

		ArrayList<ArrayList<String>> tempArray = new ArrayList<>();
		tempArray.add(new ArrayList<>());
		tempArray.get(0).add(tempBasicArray[0]);

		int rowIndex = 0;
		for(int i = 1; i < tempBasicArray.length; i++) {
			if(cmp.compare(tempBasicArray[i-1], tempBasicArray[i]) != 0) {
				rowIndex++;
				tempArray.add(new ArrayList<>());
			}
			tempArray.get(rowIndex).add(tempBasicArray[i]);
		}

		String[][] returnArray = new String[tempArray.size()][];
		for(int i = 0; i < tempArray.size(); i++) {
			ArrayList<String> arrayRow = tempArray.get(i);
			returnArray[i] = arrayRow.toArray(new String[0]);
		}

		return returnArray;
	}

	/**
	 * This method uses the StringSimilarityGroupComparator comparator to find the largest similarity group
	 * @param array the given array
	 * @return the largest group
	 * @throws NoSuchElementException when array size is 0
	 */
	public static String[] findMaximumSimilarityGroup(int[] array) throws NoSuchElementException {
		if(array.length == 0)
			throw new NoSuchElementException("Array cannot be empty!");

		String[] tempArray = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			tempArray[i]  = String.valueOf(array[i]);
		}
		StringSimilarityGroupComparator cmp = new StringSimilarityGroupComparator();
		String[][] groupArray = getSimilarityGroups(tempArray);
		return findMax(groupArray, cmp);
	}
}