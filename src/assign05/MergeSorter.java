package assign05;

import java.util.ArrayList;

/**
 * Merge Sort class to sort an array, threshold determines at what point to switch to a different sorting strategy
 *
 * @author Ethan Laynor and Brandon Flickinger
 * @version 10/3/2024
 */
public class MergeSorter<E extends Comparable<? super E>> implements Sorter<E>{
	
	private int threshold;

	public MergeSorter(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * Driver method for the merge sort
	 *
	 * @param list - list to be sorted
	 */
	@Override
	public void sort(ArrayList<E> list) {
		if (list.size() < threshold) {
			this.threshold = list.size();
		}

		ArrayList<E> aux = new ArrayList<>(list);
		mergeSort(list, aux, 0 , list.size() - 1);
	}

	/**
	 * Sort an array with merge sort (recursively), will switch to an insertion sort when the threshold is met
	 *
	 * @param list list to be sorted
	 * @param aux auxiliary list to store values
	 * @param lower lower bound on array or sub array
	 * @param upper upper bound on array or sub array
	 */
	private void mergeSort(ArrayList<E> list, ArrayList<E> aux, int lower, int upper) {
		if(upper - lower < this.threshold) {
			insertionSort(list, lower, upper);
			return;
		}
 
		int mid = (upper + lower) / 2;
		
		mergeSort(list, aux, lower,  mid);
		mergeSort(list, aux,mid + 1,  upper);
		merge(list, aux, lower, mid, upper);
	}

	/**
	 * Merge two sub arrays using the merge sorting method
	 *
	 * @param list list to be sorted
	 * @param aux auxiliary list to store values
	 * @param lower lower bound on sub array
	 * @param mid middle value of sub array
	 * @param upper upper bound on sub array
	 */
	private void merge(ArrayList<E> list, ArrayList<E> aux, int lower, int mid, int upper) {
		int l1 = lower;
		int l2 = mid + 1;

		for (int i = lower; i <= upper; i++) {
			if (l1 > mid) {
				aux.set(i, list.get(l2));
				l2++;
			} else if (l2 > upper) {
				aux.set(i, list.get(l1));
				l1++;
			} else if (list.get(l1).compareTo(list.get(l2)) <= 0) {
				aux.set(i, list.get(l1));
				l1++;
			} else if (list.get(l1).compareTo(list.get(l2)) > 0) {
				aux.set(i, list.get(l2));
				l2++;
			}
		}

		for (int i = lower; i <= upper; i++) {
			list.set(i, aux.get(i));
		}
	}

	/**
	 * Insertion sort method
	 *
	 * @param list list to be sorted
	 * @param lower lower bound on sub array
	 * @param upper upper bound on sub array
	 */
	private void insertionSort(ArrayList<E> list, int lower, int upper) {
		for(int i = lower; i < upper; i++) {
			for(int j = i + 1; j > lower; j--) {
				if(list.get(j).compareTo(list.get(j - 1)) <= 0) {
					E temp = list.get(j);
					list.set(j, list.get(j-1));
					list.set(j-1, temp);
				}
			}
		}
	}
}