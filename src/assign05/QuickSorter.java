package assign05;

import java.util.ArrayList;

/**
 * Quick sort class to sort an Array, uses a PivotChooser, chooser, to determine how to pick the pivot index
 *
 * @param <E>
 */
public class QuickSorter <E extends Comparable<? super E>> implements Sorter<E> {

	private final PivotChooser<E> chooser;

	public QuickSorter(PivotChooser<E> chooser) {
		this.chooser = chooser;
	}

	/**
	 * Driver method for our quick sort
	 *
	 * @param list - list to be sorted
	 */
	@Override
	public void sort(ArrayList<E> list) {
		quickSort(list, 0, list.size() - 1);
	}

	/**
	 * Quick sort method (recursive)
	 *
	 * @param list list to be sorted
	 * @param lower lower bound of sub array
	 * @param upper upper bound of sub array
	 */
	private void quickSort(ArrayList<E> list, int lower, int upper) {
		if (upper - lower <= 0) {
			return;
		}

		int pivot = partition(list, lower, upper);

		quickSort(list, lower, pivot - 1);
		quickSort(list, pivot + 1, upper);
	}

	/**
	 * This method sorts two halves of an array or sub array using a pivot index and puts all the items less than
	 * the pivot on the left and all items greater on the right
	 *
	 * @param list list to be sorted
	 * @param lower lower bound of sub array
	 * @param upper upper bound of sub array
	 * @return return the integer value where pivot ends up
	 */
	private int partition(ArrayList<E> list, int lower, int upper) {

		int pivot = chooser.getPivotIndex(list, lower, upper);
		final int finalPivot = upper;

		E temp = list.get(upper);
		list.set(upper, list.get(pivot));
		list.set(pivot, temp);

		while (lower < upper) {
			while (lower < upper && list.get(lower).compareTo(list.get(finalPivot)) < 0) {
				lower++;
			}

			while (upper >= lower && list.get(upper).compareTo(list.get(finalPivot)) >= 0) {
				upper--;
			}

			if (lower < upper) {
				E temp2 = list.get(upper);
				list.set(upper, list.get(lower));
				list.set(lower, temp2);
			}
		}

		E temp3 = list.get(lower);
		list.set(lower, list.get(finalPivot));
		list.set(finalPivot, temp3);

		return lower;
	}
}